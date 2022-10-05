package com.example.new_final_project.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.new_final_project.Classes.MyAdapter;
//import com.example.new_final_project.Classes.News_short;
import com.example.new_final_project.Classes.User;
import com.example.new_final_project.Classes.User_builder;
import com.example.new_final_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Welcome_Fragment extends Fragment {


    DatabaseReference database;
    private RecyclerView recyclerview;
    ArrayList<User_builder> sitter_list;


    public Welcome_Fragment() {
        // Required empty public constructor
    }

    public static Welcome_Fragment newInstance(String param1, String param2) {
        Welcome_Fragment fragment = new Welcome_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_welcome_, container, false);
        return v;
    }

    // כאן נממש את הקוד של ה recyclerView
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerview = view.findViewById(R.id.recycler_view_id);
        database = FirebaseDatabase.getInstance().getReference("users");
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        sitter_list = new ArrayList<>();

        MyAdapter MyAdapter = new MyAdapter(getContext(), sitter_list);
        recyclerview.setAdapter(MyAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    System.out.println(user.heading);
                    User_builder new_user_builder = new User_builder.Builder()
                            .heading(user.heading)
                            .Email(user.email)
                            .facebook(user.facebook)
                            .date(user.date)
                            .phone(user.phone)
                            .imgageAccessToken(user.imgageAccessToken)
                            .build();
                    sitter_list.add(new_user_builder);
                }
                MyAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Welcome_Fragment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}