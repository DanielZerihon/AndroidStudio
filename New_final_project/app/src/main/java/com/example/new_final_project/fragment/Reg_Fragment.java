package com.example.new_final_project.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.new_final_project.R;
import com.example.new_final_project.activitys.MainActivity;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Reg_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reg_Fragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static Uri mImageUri;
    private static Object chooseImage;
    private static Context context;
    private static View staticView;

    public static Uri getmImageUri() {
        return mImageUri;
    }

    //private Uri mImageUri;
    private EditText email;
    private EditText password;
    private EditText userName;
    private EditText dateText;
    private EditText facebook;
    private EditText PhoneNumber;
    //private ImageView chooseImage;
    private Intent data;

    public static Reg_Fragment newInstance(String param1, String param2) {
        Reg_Fragment fragment = new Reg_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    // הפונקציה שרצה בלופ
    public static void setImage(int requestCode, int resultCode, Intent data, int RESULT_OK) {

        // מקבל את התמונה עצמה
            mImageUri = data.getData();
        // השמה של התמונה עצמה אל השדה הרצוי
            Picasso.with(context).load(mImageUri).into((ImageView) staticView.findViewById(R.id.Selfi_pic_fragment));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_reg_,container,false);
        Button new_reg_button = view.findViewById(R.id.button_reg_in_regF);

        email = view.findViewById(R.id.editTextEmailReg);
        password = view.findViewById(R.id.editTextPassReg);
        userName = view.findViewById(R.id.editTextName);
        dateText = view.findViewById(R.id.editTextDate);
        facebook = view.findViewById(R.id.editTextFacebook);
        PhoneNumber = view.findViewById(R.id.editTextPhone);

        context = ((MainActivity) getActivity()).getApplicationContext();
        chooseImage = view.findViewById(R.id.Selfi_pic_fragment);

        view.findViewById(R.id.Selfi_pic_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        new_reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String userNameText = userName.getText().toString();
                String dateTextText = dateText.getText().toString();
                String facebookText = facebook.getText().toString();
                String Phone_Number = PhoneNumber.getText().toString();

                if (emailText.matches("") || passwordText.matches("") ||
                        userNameText.matches("") || dateTextText.matches("") ||
                        facebookText.matches("") || Phone_Number.matches("")){
                    Toast.makeText(getActivity(), "Please fill in all that Cells", Toast.LENGTH_SHORT).show();
                }else{
                    ((MainActivity) getActivity()).reg_func(emailText, passwordText, userNameText, dateTextText, facebookText, Phone_Number);
                }
//                Navigation.findNavController(view).navigate(R.id.action_reg_Fragment_to_home_Fragment);
            }
        });
        staticView = view;
        return view;
    }
}