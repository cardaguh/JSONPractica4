package com.jikansoftware.retrofitjsonpractica4.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jikansoftware.retrofitjsonpractica4.R;

public class ContactHolder extends RecyclerView.ViewHolder {

    public CardView cardContact;
    public TextView txtName;
    public TextView txtEmail;
    public ImageView imgContact;

    public ContactHolder(View itemView) {
        super(itemView);
        cardContact = (CardView) itemView.findViewById(R.id.cardContact);
        txtName = (TextView) itemView.findViewById(R.id.txtName);
        txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
        imgContact = (ImageView) itemView.findViewById(R.id.imgContact);
    }
}
