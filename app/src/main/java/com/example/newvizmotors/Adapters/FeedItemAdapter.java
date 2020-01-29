package com.example.newvizmotors.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.newvizmotors.DataModels.Feeditem;
import com.example.newvizmotors.DetailViewer;
import com.example.newvizmotors.Homepage;
import com.example.newvizmotors.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FeedItemAdapter extends ArrayAdapter<Feeditem> {

    private Context mContext;
    private List<Feeditem> homeFeedItems;


    public FeedItemAdapter(Context context, ArrayList<Feeditem> list) {
        super(context, 0,list);
        mContext = context;
        homeFeedItems = list;


    }

//    public ItemClicked getItem(int position){
//        return items.get(position);
//    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.card_item,parent,false);

        Feeditem currentFeedItem = homeFeedItems.get(position);
        TextView category = listItem.findViewById(R.id.category);
        if(currentFeedItem.getCategory()==null){

        }else{
            Log.v("feed item category is",currentFeedItem.getCategory());
            category.setText(currentFeedItem.getCategory());
            }

        ImageView profilePic =  listItem.findViewById(R.id.profilePic);
        Picasso.get().load(currentFeedItem.getProflePic()).into(profilePic);
//        profilePic.setImageResource(currentFeedItem.getDrawableProfilePic());

        TextView name =  listItem.findViewById(R.id.name);
        name.setText(currentFeedItem.getName());

        TextView status = listItem.findViewById(R.id.txtStatusMsg);
        status.setText(currentFeedItem.getStatus());

        TextView timestamp = listItem.findViewById(R.id.timestamp);
        timestamp.setText(currentFeedItem.getTimestamp());

        ImageView image =  listItem.findViewById(R.id.feedImage1);
        Picasso.get().load(currentFeedItem.getImage()).into(image);

//

//        if(currentFeedItem.getCategory()!=null){
//
//
//        }

        return listItem;
    }
}
