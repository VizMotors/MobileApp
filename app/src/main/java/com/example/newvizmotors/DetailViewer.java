package com.example.newvizmotors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newvizmotors.DataModels.Feeditem;
import com.github.florent37.materialleanback.MaterialLeanBack;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailViewer extends AppCompatActivity {
    public String vehicleType = "Toyota CHR";
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    MaterialLeanBack materialLeanBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_viewer);
        Feeditem itemFromIntentExtras = new Feeditem();

        itemFromIntentExtras.setName(getIntent().getStringExtra("name"));

        Toast.makeText(this, "Name is"+itemFromIntentExtras.getName(), Toast.LENGTH_SHORT).show();
//        itemFromIntentExtras.setProflePic(getIntent().getStringExtra("profilePic"));
        itemFromIntentExtras.setStatus(getIntent().getStringExtra("status"));
//        itemFromIntentExtras.setTimestamp(getIntent().getStringExtra("timestamp"));
        itemFromIntentExtras.setImage(getIntent().getStringExtra("image"));
        itemFromIntentExtras.setCategory(getIntent().getStringExtra("category"));

        TextView companyName = findViewById(R.id.vehicle_model);
        Toast.makeText(this, itemFromIntentExtras.getStatus(), Toast.LENGTH_SHORT).show();
        companyName.setText(itemFromIntentExtras.getCategory());

        TextView vehicleModel = findViewById(R.id.company_name);
        vehicleModel.setText(itemFromIntentExtras.getName());

        ImageView carImage = findViewById(R.id.carImg);
        Picasso.get().load(itemFromIntentExtras.getImage()).into(carImage);

        TextView description = findViewById(R.id.description);
        description.setText(itemFromIntentExtras.getStatus());


        databaseReference = firebaseDatabase.getInstance().getReference().child("spareParts");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()
                ) {
                    DatabaseReference ref = databaseReference.child(data.getKey()).child("category");
//                   ref.child("category").equalTo("Toyota CHR").addValueEventListener(new ValueEventListener() {
//                       @Override
//                       public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
//                           Toast.makeText(DetailViewer.this, dataSnapshot1.getKey(), Toast.LENGTH_SHORT).show();
//                           for (DataSnapshot dt: dataSnapshot1.getChildren()
//                                ) {
//                               Toast.makeText(DetailViewer.this, dt.getValue().toString(), Toast.LENGTH_SHORT).show();
//                           }
//                       }
//
//                       @Override
//                       public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                       }
//                   });
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {

                            Toast.makeText(DetailViewer.this, "I am 1  " + dataSnapshot1.getValue().toString(), Toast.LENGTH_SHORT).show();

                            String type = dataSnapshot1.getValue().toString();
                            if (type.equals(vehicleType)) {

                                Toast.makeText(DetailViewer.this, "I am " + dataSnapshot1.getValue().toString(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(DetailViewer.this, "You cant see me", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });









    }



}
