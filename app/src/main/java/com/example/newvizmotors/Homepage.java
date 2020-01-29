package com.example.newvizmotors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newvizmotors.Adapters.FeedItemAdapter;
import com.example.newvizmotors.DataModels.Feeditem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Homepage extends AppCompatActivity {
//    DatabaseReference databaseReference;

    private ListView listView;
    private FeedItemAdapter feedItemAdapter;
    private ArrayList<Feeditem> feeditems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        listView = findViewById(R.id.list_home);
        feeditems = new ArrayList<>();



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("/feedItem");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                collectAllCards((Map<String, Object>) dataSnapshot.getValue());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(Homepage.this, DetailViewer.class);
                intent.putExtra("name",feeditems.get(i).getName());
                intent.putExtra("image",feeditems.get(i).getImage());
                intent.putExtra("status",feeditems.get(i).getStatus());
                intent.putExtra("profilePic",feeditems.get(i).getProflePic());
                intent.putExtra("timestamp",feeditems.get(i).getTimestamp());
                Toast.makeText(Homepage.this, feeditems.get(i).getCategory(), Toast.LENGTH_SHORT).show();
                intent.putExtra("category",feeditems.get(i).getCategory());
                startActivity(intent);
            }
        });


    }





    private void collectAllCards(Map<String, Object> cards) {

        for (Map.Entry<String, Object> entry : cards.entrySet()) {

//            Toast.makeText(Homepage.this,"data "+entry.getKey(),Toast.LENGTH_SHORT).show();
            Map singleCard = (Map) entry.getValue();
            //Get phone field and append to list
            final Feeditem item = new Feeditem();
//            Toast.makeText(CardsListPage.this,entry.getKey(),Toast.LENGTH_SHORT).show();
            Feeditem singleOne = item.toFeedItem(singleCard);

            feeditems.add(singleOne);




//            Toast.makeText(Homepage.this,"data model"+singleOne.getName(),Toast.LENGTH_SHORT).show();
        }

        feedItemAdapter = new FeedItemAdapter(Homepage.this,feeditems);
        listView.setAdapter(feedItemAdapter);




    }

















}
