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

    public interface OnItemClickListener {
        void onItemClick(PlaceholderItem item);
    }


    private List<PlaceholderItem> mValues;
    private OnItemClickListener listener;


    public MyContactRecyclerViewAdapter(List<PlaceholderItem> items, OnItemClickListener listener) {
        mValues = items;
        this.listener = listener;

    }


    public void setFilteredList(List<PlaceholderContent.PlaceholderItem> list) {
        this.mValues = list;
        notifyDataSetChanged();


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.contentText.setText(mValues.get(position).firstContent);
        holder.bind(holder.mItem, listener);
        Log.d("onBindViewHolder", "sono qui");
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView contentText;
        public final Button lookButton;
        public final Button editButton;
        public final Button cancelButton;
        public PlaceholderItem mItem;


        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());

            contentText = binding.firstText;
            lookButton = binding.lookButton;
            editButton = binding.editButton;
            cancelButton = binding.cancelButton;
        }


        //Mio Metodo per gestire l'evento sul singolo "ViewHolder" della RecyclerView
        public void bind(final PlaceholderItem item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("editButton: ","ok");
                    Log.d("testo: ",""+contentText.getText());
                }
            });
        }


        @Override
        public String toString() {
            return super.toString() + " '" + contentText.getText() + "'";
        }
    }


    public static class SecondaClasseInnestata{
        public SecondaClasseInnestata(){}
    }


}