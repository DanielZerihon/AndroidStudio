package com.example.new_final_project.activitys;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.new_final_project.R;
import com.example.new_final_project.Classes.User;
import com.example.new_final_project.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

    }


    public void login_func(View view) {
        EditText email = findViewById(R.id.editText_log_in_Email);
        String emailText = email.getText().toString();
        EditText password = findViewById(R.id.editText_log_in_Password);
        String passwordText = password.getText().toString();

//        Toast.makeText(getApplicationContext(), emailText,Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), passwordText,Toast.LENGTH_SHORT).show();

        mAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Yes to log",Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_home_Fragment_to_welcome_Fragment2);
                        } else {
                            Toast.makeText(getApplicationContext(), "No to log",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void reg_func(String emailText, String passwordText, String userNameText, String dateTextText, String facebookText) {

//        Toast.makeText(getApplicationContext(), emailText,Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), passwordText,Toast.LENGTH_SHORT).show();

        mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            addData(emailText, passwordText, userNameText, dateTextText, facebookText);
                            Toast.makeText(getApplicationContext(), "Yes to reg",Toast.LENGTH_SHORT).show();
//                            Navigation.findNavController(view).navigate(R.id.action_home_Fragment_to_welcome_Fragment2);

                        } else {
                            Exception res = task.getException();
                            System.out.println("The error is:");
                            System.out.println(res);
                            Toast.makeText(getApplicationContext(), "no to reg",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // פוקנציה זאת מוסיפה איברים לתוך הדטא בייס של הג'ייסון
    public void addData(String emailText, String passwordText, String userNameText, String dateTextText, String facebookText){
        // מה שעשיתי עם אפי זה הגדרתי את המשתנים במיין של reg והעברתי אותם by val
//        Toast.makeText(getApplicationContext(), "after hagdarot",Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), userName_Text_s,Toast.LENGTH_SHORT).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(userNameText.toString());
        User user = new User(userNameText.toString(), emailText.toString(), facebookText.toString());
        myRef.setValue(user);
    }


}