package com.example.ellie.instacut_master;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A User-Registering Activity
 * Created by ellie on 02/10/2019
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button Sign_in_button;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewRegister;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

   @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);

       //Initilaize Firebase Auth Object
       firebaseAuth = firebaseAuth.getInstance();

       //track if user is logged in

       if (firebaseAuth.getCurrentUser() != null ){
           //start profile activity here
       }


       editTextEmail = (EditText) findViewById(R.id.email);
       editTextPassword = (EditText) findViewById(R.id.password);
       Sign_in_button = (Button) findViewById(R.id.sign_in_button);
       textViewRegister = (TextView) findViewById(R.id.register_button);

       progressDialog = new ProgressDialog(this);

       Sign_in_button.setOnClickListener(this);
       textViewRegister.setOnClickListener(this);

   }

   private void userlogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //checking if email and password are empty
       if (TextUtils.isEmpty(email)) {
           //email is empty
           Toast.makeText(this, "Please Enter email", Toast.LENGTH_SHORT).show();
           return;
       }
       if (TextUtils.isEmpty(password)) {
           //password is empty
           Toast.makeText(this, "Please Enter password", Toast.LENGTH_SHORT).show();
           return;
       }
       //if email and password are not empty
       //display progress dialog
       progressDialog.setMessage("Registering User...");
       progressDialog.show();

       firebaseAuth.signInWithCredential(email, password)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       progressDialog.dismiss();

                       if(task.isSuccessful()){
                          // start profile activity
                       }

                   }
               })
   }

   @Override
    public void onClick(View View){
     if(View == Sign_in_button ){
         userlogin();
     }

     if(View == textViewRegister){
         finish();
         startActivity(new Intent(this,LoginActivity.class));
     }


   }

}






