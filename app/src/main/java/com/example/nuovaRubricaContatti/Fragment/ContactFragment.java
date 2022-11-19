package com.example.nuovaRubricaContatti.Fragment;

import com.example.nuovaRubricaContatti.Fragment.MyContactRecyclerViewAdapter.SecondaClasseInnestata;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.nuovaRubricaContatti.AddContactActivity;
import com.example.nuovaRubricaContatti.EditContactActivity;
import com.example.nuovaRubricaContatti.MainActivity;
import com.example.nuovaRubricaContatti.R;
import com.example.nuovaRubricaContatti.Fragment.placeholder.PlaceholderContent;

import java.util.ArrayList;


/**
 * A fragment representing a list of Items.
 */
public class ContactFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    public static ArrayList<PlaceholderContent.PlaceholderItem> itemArrayList;
    public static MyContactRecyclerViewAdapter myRecyclerView;


    public ContactFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ContactFragment newInstance(int columnCount) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }


            View secondView = inflater.inflate(R.layout.fragment_item, container, false);
            TextView firstText = secondView.findViewById(R.id.firstText);

            itemArrayList = new ArrayList<>();
            itemArrayList.add(new PlaceholderContent.PlaceholderItem("0", "mattino", "clicca", "clicca", "clicca"));
            itemArrayList.add(new PlaceholderContent.PlaceholderItem("1", "ciao", "clicca", "clicca", "clicca"));
            itemArrayList.add(new PlaceholderContent.PlaceholderItem("2", "mondo", "clicca", "clicca", "clicca"));
            itemArrayList.add(new PlaceholderContent.PlaceholderItem("3", "sera", "clicca", "clicca", "clicca"));
            myRecyclerView = new MyContactRecyclerViewAdapter(itemArrayList, new MyContactRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(PlaceholderContent.PlaceholderItem item) {
                    Log.d("contatto cliccato: ", "" + item.firstContent);
                }
            });
            recyclerView.setAdapter(myRecyclerView);
        }

        SecondaClasseInnestata s= new SecondaClasseInnestata();

        return view;
    }


}