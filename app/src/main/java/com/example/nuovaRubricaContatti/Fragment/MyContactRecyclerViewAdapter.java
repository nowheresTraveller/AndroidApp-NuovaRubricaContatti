package com.example.nuovaRubricaContatti.Fragment;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nuovaRubricaContatti.Fragment.placeholder.PlaceholderContent;
import com.example.nuovaRubricaContatti.databinding.*;
import com.example.nuovaRubricaContatti.Fragment.placeholder.PlaceholderContent.PlaceholderItem;



import java.util.List;


public class MyContactRecyclerViewAdapter extends RecyclerView.Adapter<MyContactRecyclerViewAdapter.ViewHolder> {

    private  List<PlaceholderItem> mValues;

    public MyContactRecyclerViewAdapter(List<PlaceholderItem> items) {
        mValues = items;
    }

    public void setFilteredList(List <PlaceholderContent.PlaceholderItem> list){
        this.mValues=list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mFirstContentView.setText(mValues.get(position).firstContent);

        Log.d("onBindViewHolder","sono qui");

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mFirstContentView;

        public final Button lookButton;
        public final Button editButton;
        public final Button cancelButton;
        public PlaceholderItem mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mFirstContentView = binding.firstText;

            lookButton = binding.lookButton;
            editButton = binding.secondEditButton;
            cancelButton = binding.cancelButton;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mFirstContentView.getText() + "'";
        }
    }
}