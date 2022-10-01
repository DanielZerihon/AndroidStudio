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
import android.widget.TextView;

import com.example.new_final_project.Classes.MyAdapter;
import com.example.new_final_project.Classes.News;
import com.example.new_final_project.R;

import java.sql.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Welcome_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Welcome_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerview;
    private ArrayList<News> newArrayList;
    private int[] imageResourceID;
    private int[] ID_PICTURES;
    private String[] names;
    private String[] facebook;
    private String[] phone;
    private String[] date;
    private int[] ID_Images;



    public Welcome_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Welcome_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Welcome_Fragment newInstance(String param1, String param2) {
        Welcome_Fragment fragment = new Welcome_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome_, container, false);
    }

    // כאן נממש את הקוד של ה recyclerView
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // הפונקציה הזאת מושכת את הנתונים א הפראגמנט
        datainitialize();

        recyclerview = view.findViewById(R.id.recycler_view_id);

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerview.setHasFixedSize(true);

        MyAdapter MyAdapter = new MyAdapter(getContext(), newArrayList);

        recyclerview.setAdapter(MyAdapter);

        MyAdapter.notifyDataSetChanged();


    }

    private void datainitialize() {

        newArrayList = new ArrayList<>();



        names = new String[]{
                getString(R.string.head_1),
                getString(R.string.head_2),
                getString(R.string.head_3),
                getString(R.string.head_4),
                getString(R.string.head_5),
                getString(R.string.head_6),
                getString(R.string.head_7),
        };

        facebook = new String[]{
                getString(R.string.AviMosheFacebook),
                getString(R.string.DaniAshkenziFacebook),
                getString(R.string.DavidCohenFacebook),
                getString(R.string.HenMalakoFacebook),
                getString(R.string.MatanPerezFacebook),
                getString(R.string.MosheBitonFacebook),
                getString(R.string.TaliaOvadiaFacebook),
        };

        phone = new String[]{
                getString(R.string.avi_phone),
                getString(R.string.danie_phone),
                getString(R.string.david_phone),
                getString(R.string.hen_phone),
                getString(R.string.matan_phone),
                getString(R.string.moshe_phone),
                getString(R.string.talia_phone),
        };

        date = new String[]{
                getString(R.string.avi_date),
                getString(R.string.danie_date),
                getString(R.string.david_date),
                getString(R.string.hen_date),
                getString(R.string.matan_date),
                getString(R.string.moshe_date),
                getString(R.string.talia_date),
        };


        imageResourceID = new int[]{
                R.drawable.avi,
                R.drawable.dani,
                R.drawable.david,
                R.drawable.hen,
                R.drawable.matan,
                R.drawable.moshe,
                R.drawable.talia,
        };

        ID_Images = new int[]{
                R.drawable.dani,
                R.drawable.dani,
                R.drawable.dani,
                R.drawable.dani,
                R.drawable.dani,
                R.drawable.dani,
                R.drawable.dani,
        };

        for (int i=0; i<names.length;i++){
            News news = new News(
                    "Name : " + names[i],
                    imageResourceID[i],
                    "Facebook : " + facebook[i],
                    "phone : " + phone[i],
                    "Joined in : " + date[i],
                    ID_Images[i]
            );
            newArrayList.add(news);
        }
    }
}