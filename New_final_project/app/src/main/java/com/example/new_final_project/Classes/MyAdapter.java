package com.example.new_final_project.Classes;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.new_final_project.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<User_builder> list;

    // the constractor of the adapter
    public MyAdapter(Context context, ArrayList<User_builder> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Upload = User_builder

        User_builder user_to_show = list.get(position);
        holder.facebook.setText("instagram : " + user_to_show.getFacebook());
        holder.phone.setText("Phone : " + user_to_show.getPhone());
        holder.user_name.setText("User name : " + user_to_show.getHeading());
        holder.date.setText("Price Per Hour : " + user_to_show.getDate());

        // דחיפה של התמונה מהסטורג'
        Picasso.with(context)
                .load(user_to_show.getImgageAccessToken())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.image_to_show);

//        holder.image_Button =
    }



    // will return the size of the array
    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        // כאן נכניס את מה שנרצה שיהיה על ה list item
        //הורדתי את השתיים כי הם לא מופיעים ברילטיים דטא בייס
//        ShapeableImageView titleImage;
//        TextView tvHeading;
        TextView facebook;
        TextView email;
        TextView user_name;
        TextView phone;
        TextView date;
        ImageView image_to_show;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.tvHeading);
            facebook = itemView.findViewById(R.id.Facebook);
            phone = itemView.findViewById(R.id.Phone);
            image_to_show = itemView.findViewById(R.id.title_image);
            date = itemView.findViewById(R.id.NewDate);

        }
    }
}
