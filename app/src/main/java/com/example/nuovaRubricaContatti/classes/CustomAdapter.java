package com.example.nuovaRubricaContatti.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.nuovaRubricaContatti.R;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contact> {

    public List <Contact> contacts;

    public CustomAdapter(Context context, int textViewResourceId, List <Contact> objects) {
        super(context, textViewResourceId, objects);
        contacts= objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_of_listview, null);
            viewHolder = new CustomAdapter.ViewHolder(
                    convertView.findViewById(R.id.contactName)
            );
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        Contact contact= getItem(position);
        viewHolder.getContactName().setText(contact.getNome());
        return convertView;

    }


    public List<Contact> getContacts() {
        return contacts;
    }

    public void setFilteredList(List <Contact> list) {
        super.clear();
        super.addAll(list);
        notifyDataSetChanged();
    }


    public class ViewHolder{

        private TextView contactName;

        public ViewHolder(TextView contactName) {
            this.contactName = contactName;
        }

        public TextView getContactName() {
            return contactName;
        }
    }

}
