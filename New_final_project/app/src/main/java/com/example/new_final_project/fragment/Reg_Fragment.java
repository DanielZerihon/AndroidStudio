package com.example.new_final_project.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.new_final_project.R;
import com.example.new_final_project.activitys.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Reg_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reg_Fragment extends Fragment {



    public static Reg_Fragment newInstance(String param1, String param2) {
        Reg_Fragment fragment = new Reg_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_reg_,container,false);
        Button new_reg_button = view.findViewById(R.id.button_reg_in_regF);

        EditText email = view.findViewById(R.id.editTextEmailReg);
        EditText password = view.findViewById(R.id.editTextPassReg);
        EditText userName = view.findViewById(R.id.editTextName);
        EditText dateText = view.findViewById(R.id.editTextDate);
        EditText facebook = view.findViewById(R.id.editTextFacebook);



        new_reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String userNameText = userName.getText().toString();
                String dateTextText = dateText.getText().toString();
                String facebookText = facebook.getText().toString();

                ((MainActivity) getActivity()).reg_func(emailText, passwordText, userNameText, dateTextText, facebookText);
//                Navigation.findNavController(view).navigate(R.id.action_reg_Fragment_to_home_Fragment);
            }
        });

        return view;
    }
}