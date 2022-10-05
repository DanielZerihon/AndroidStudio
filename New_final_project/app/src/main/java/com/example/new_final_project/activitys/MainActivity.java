package com.example.new_final_project.activitys;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.new_final_project.Classes.MediaPlayerService;
import com.example.new_final_project.Classes.User_builder;
import com.example.new_final_project.R;
import com.example.new_final_project.Classes.User;
//import com.example.new_final_project.databinding.ActivityMainBinding;
import com.example.new_final_project.fragment.Reg_Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;
    private FirebaseStorage mStorageRef;
    private StorageTask mUploadTask;
    private Button start;
    Boolean isPlaying = false;
    private static ImageView sound_image_view;
    private int current_image;
    int[] images={R.drawable.sound_off, R.drawable.sound_on};

    Button insta;

    // הפונקציה רצה בלופ תמיד
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // הפונקציה שולחת את הנתונים האלו בלופ ומנסה לעשות set image
        Reg_Fragment.setImage(requestCode, resultCode, data, RESULT_OK);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        sound_image_view = (ImageView) findViewById(R.id.sound_imageView);
        sound_image_view.setOnClickListener(this);

    }

    private void gotourl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }


    @Override
    public void onClick(View view) {
        sound_image_view=(ImageView) findViewById(R.id.sound_imageView);
        current_image++;
        current_image=current_image % images.length;
        sound_image_view.setImageResource(images[current_image]);

        if (!isPlaying) {
            startService(new Intent(this, MediaPlayerService.class));
            isPlaying = true;
        } else {
            stopService(new Intent(this, MediaPlayerService.class));
            isPlaying = false;
        }
    }


    public void login_func(View view) {
        EditText email = findViewById(R.id.editText_log_in_Email);
        String emailText = email.getText().toString();
        EditText password = findViewById(R.id.editText_log_in_Password);
        String passwordText = password.getText().toString();

        mAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Yes to log", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_home_Fragment_to_welcome_Fragment2);
                        } else {
                            Toast.makeText(getApplicationContext(), "No to log", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void reg_func(String emailText, String passwordText, String userNameText, String dateTextText, String facebookText, String Phone_Number) {
        mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            uploadFile(emailText, passwordText, userNameText, dateTextText, facebookText, Phone_Number);
                            Toast.makeText(getApplicationContext(), "Yes to reg", Toast.LENGTH_SHORT).show();
//                            Navigation.findNavController(view).navigate(R.id.action_home_Fragment_to_welcome_Fragment2);
                        } else {
                            Exception res = task.getException();
                            Toast.makeText(getApplicationContext(), "no to reg", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(String emailText, String passwordText, String userNameText, String dateTextText, String facebookText, String Phone_Number) {

        if (Reg_Fragment.getmImageUri() != null) {
            mStorageRef = FirebaseStorage.getInstance();
            StorageReference fileReference = mStorageRef.getReference().child(System.currentTimeMillis()
                    + "." + getFileExtension(Reg_Fragment.getmImageUri()));

            mUploadTask = fileReference.putFile(Reg_Fragment.getmImageUri())
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            boolean flag = false;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            }, 500);

                            Toast.makeText(MainActivity.this, "Upload successful", Toast.LENGTH_LONG).show();
                            flag = true;

                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    User_builder user_build = new User_builder.Builder()
                                            .heading(userNameText.toString())
                                            .Email(emailText.toString())
                                            .facebook(facebookText.toString())
                                            .date(dateTextText.toString())
                                            .phone(Phone_Number.toString())
                                            .imgageAccessToken(uri.toString())
                                            .build();
                                    System.out.println(user_build);
                                    database.getReference("users").child(userNameText.toString()).setValue(user_build);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

}
