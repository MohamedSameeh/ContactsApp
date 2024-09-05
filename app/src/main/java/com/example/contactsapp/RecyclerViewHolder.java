package com.example.contactsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewHolder extends RecyclerView.Adapter<RecyclerViewHolder.ContactsViewHolder> {

    private ArrayList<Contacts> contacts;
    private Context context;

    public RecyclerViewHolder(Context context, ArrayList<Contacts> contacts) {
        this.contacts = contacts;
        this.context = context;
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView name_tv;
        ImageButton call,del;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            name_tv = itemView.findViewById(R.id.contacts_tv);
            call = itemView.findViewById(R.id.call_btn);
            del = itemView.findViewById(R.id.del_btn);
        }
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_view, parent, false);
        return new ContactsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        Contacts contact = contacts.get(position);
        holder.name_tv.setText(contact.getName());

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "tel:" + contact.getNumber();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(phoneNumber));
                if (context.checkSelfPermission(android.Manifest.permission.CALL_PHONE) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    context.startActivity(intent);
                } else {
                    // Request the permission if it hasn't been granted
                    if (context instanceof Activity) {
                        Activity activity = (Activity) context;
                        ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                    }
                }
            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBase db = new MyDataBase(context);
                db.deleteData(contact.getId()); // Delete from the database
                contacts.remove(position); // Remove the contact from the list
                notifyItemRemoved(position); // Notify the RecyclerView that the item was removed
                notifyItemRangeChanged(position, contacts.size()); // Notify the RecyclerView to update the remaining items
            }
        });
    }



    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
        
