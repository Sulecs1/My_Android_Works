package com.suleakcay.instaclonefirabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecylerAdapter extends RecyclerView.Adapter<FeedRecylerAdapter.PostHolder> {
    private ArrayList<String>userEmailList;
    private ArrayList<String> userCommentList;
    private  ArrayList<String> userImageList;

    public FeedRecylerAdapter(ArrayList<String> userEmailList, ArrayList<String> userCommentList, ArrayList<String> userImageList) {
        this.userEmailList = userEmailList;
        this.userCommentList = userCommentList;
        this.userImageList = userImageList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {  //Oluşturulunca ne yapacagım
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new PostHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) { //baglanınca ne yapacagım
        holder.userEmailText.setText(userEmailList.get(position));
        holder.commentText.setText(userCommentList.get(position));
       // userEmailList.get(position);
        Picasso.get().load(userImageList.get(position)).into(holder.imageView);

    }

    @Override
    public int getItemCount() { //kac tane row olacak yazacagız
        return userEmailList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{ //
        ImageView imageView;
        TextView userEmailText;
        TextView commentText;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.recyclerView_row_imageView);
            userEmailText=itemView.findViewById(R.id.recyclerView_row_useremail_text);
            commentText=itemView.findViewById(R.id.commentText);

        }
    }

}
