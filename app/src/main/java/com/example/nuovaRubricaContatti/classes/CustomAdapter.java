package com.example.nuovaRubricaContatti.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nuovaRubricaContatti.R;

public class CustomAdapter extends ArrayAdapter<ContactOnListView> {

    public CustomAdapter(Context context, int textViewResourceId, ContactOnListView [] objects) {
        super(context, textViewResourceId, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_of_listview, null);
        TextView nomeContatto = (TextView)convertView.findViewById(R.id.contactName);
        ContactOnListView c = getItem(position);
        nomeContatto.setText(c.getNome());
        return convertView;
    }

}
