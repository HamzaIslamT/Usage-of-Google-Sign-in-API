package com.example.signup_task;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

GoogleSignInOptions gso;
GoogleSignInClient gsc;
ImageView googlebtn;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    EditText username = findViewById(R.id.username);
    EditText password = findViewById(R.id.userpassword);
    MaterialButton signupbtn = findViewById(R.id.signupbtn);

    gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
    gsc = GoogleSignIn.getClient(this,gso);
    googlebtn = findViewById(R.id.googlebtn);

    signupbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (username.getText().toString().equalsIgnoreCase("DUMMY") && password.getText().toString().equalsIgnoreCase("DUMMY")) {
                nextActivity();
                Toast.makeText(MainActivity.this, " Sign in successfully! ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Error while signing in!", Toast.LENGTH_SHORT).show();
            }
        }
    });

    googlebtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SignIn();
        }
    });

}

void SignIn(){

    Intent signInIntent = gsc.getSignInIntent();
    startActivityForResult(signInIntent, 1000);
}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if(requestCode == 1000){
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

        try{
            task.getResult(ApiException.class);
            nextActivity();
        } catch (ApiException e){
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
     }

   }

        void nextActivity(){
            finish();
            Intent intent = new Intent(MainActivity.this,nextActivity.class);
            startActivity(intent);

        }

}


