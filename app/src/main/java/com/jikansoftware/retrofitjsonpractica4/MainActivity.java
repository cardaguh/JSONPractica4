package com.jikansoftware.retrofitjsonpractica4;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import com.jikansoftware.retrofitjsonpractica4.adapter.ContactAdapter;
import com.jikansoftware.retrofitjsonpractica4.helper.CustomRVItemTouchListener;
import com.jikansoftware.retrofitjsonpractica4.helper.RecyclerViewItemClickListener;
import com.jikansoftware.retrofitjsonpractica4.models.Contact;
import com.jikansoftware.retrofitjsonpractica4.models.ContactList;
import com.jikansoftware.retrofitjsonpractica4.reposervice.ApiService;
import com.jikansoftware.retrofitjsonpractica4.retrofit.RetroClient;
import com.jikansoftware.retrofitjsonpractica4.utils.InternetConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadContactsData();
    }

    protected void loadContactsData() {

        /**
         * Checking Internet Connection
         */
        if (InternetConnection.checkConnection(getApplicationContext())) {

            final ProgressDialog dialog;

            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Getting JSON data");
            dialog.setMessage("Please wait...");
            dialog.show();

            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            Call<ContactList> call = api.getContacts();

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<ContactList>() {
                @Override
                public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if(response.isSuccessful()) {

                        /**
                         * Got Successfully
                         */
                        // Log.d("LOGGG", "onResponse: " + response.body().getContacts());
                        contactList = response.body().getContacts();

                        /**
                         * Binding that List to Adapter
                         */
                        RecyclerView recyclerContacts = (RecyclerView) findViewById(R.id.recyclerContacts);
                        ContactAdapter adapter = new ContactAdapter(contactList, MainActivity.this);
                        recyclerContacts.setAdapter(adapter);
                        recyclerContacts.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        /**
                         * Add listener to every recycler view items
                         */
                        recyclerContacts.addOnItemTouchListener(new CustomRVItemTouchListener(MainActivity.this, recyclerContacts, new RecyclerViewItemClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                Snackbar.make(findViewById(R.id.layoutMain), "onClick at position : " + position, Snackbar.LENGTH_LONG).show();

                            }

                            @Override
                            public void onLongClick(View view, int position) {
                                Snackbar.make(findViewById(R.id.layoutMain), "onLongClick at position : " + position, Snackbar.LENGTH_LONG).show();
                            }
                        }));

                    } else {
                        Snackbar.make(findViewById(R.id.layoutMain), "Something going wrong!", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ContactList> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        } else {
            Snackbar.make(findViewById(R.id.layoutMain), "Check your internet connection!", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.settings){
            Snackbar.make(findViewById(R.id.layoutMain), "Se presionó settings", Snackbar.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
