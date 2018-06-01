package com.jikansoftware.retrofitjsonpractica4.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import com.jikansoftware.retrofitjsonpractica4.R;
import com.jikansoftware.retrofitjsonpractica4.holder.ContactHolder;
import com.jikansoftware.retrofitjsonpractica4.models.Contact;
import com.jikansoftware.retrofitjsonpractica4.models.ContactList;

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {

    List<Contact> list = Collections.emptyList();
    Context context;

    public ContactAdapter(List<Contact> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_contact_row_layout, parent, false);

        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {

        // Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.txtName.setText(list.get(position).getName());
        holder.txtEmail.setText(list.get(position).getEmail());
        Picasso.get().load(list.get(position).getProfilePic()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imgContact);

        // animate(holder);
    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Contact data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Contact data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

}