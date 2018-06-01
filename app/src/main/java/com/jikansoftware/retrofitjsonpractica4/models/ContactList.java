package com.jikansoftware.retrofitjsonpractica4.models;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactList {

    @SerializedName("contacts")
    @Expose
    private ArrayList<Contact> contacts = null;

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

}