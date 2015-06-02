package com.mlaffan.giftpal.sqlite.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mlaffan.giftpal.R;
import com.mlaffan.giftpal.sqlite.db.Person;

import java.util.ArrayList;

/**
 * Created by Mark on 04/05/2015.
 */
public class PersonAdapter extends ArrayAdapter<Person> {
    private int resourceId;

    public PersonAdapter(Context context, int resourceId, ArrayList<Person> people){
        super(context, resourceId, people);
        this.resourceId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(resourceId, null);
        }

        Person person = getItem(position);
        if(person != null){
            TextView firstName = (TextView) v.findViewById(R.id.firstName);
            TextView lastName = (TextView) v.findViewById(R.id.lastName);

            firstName.setText(person.getFirstName());
            lastName.setText(person.getLastName());
        }

        return v;
    }
}
