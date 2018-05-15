package com.example.android.firestoredemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class DetailsActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText referralEditText;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String name;
    private String email;
    private String referral;
    private String TAG="DetailsActivity";
    private Button submit;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nameEditText=(EditText)findViewById(R.id.name_editText);
        emailEditText=(EditText)findViewById(R.id.email_editText);
        referralEditText=(EditText)findViewById(R.id.referral_editText);
        submit=(Button)findViewById(R.id.submit);
        next=(Button)findViewById(R.id.next);

        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=nameEditText.getText().toString();
                email=emailEditText.getText().toString();
                referral=referralEditText.getText().toString();
                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email))
                    Toast.makeText(DetailsActivity.this,"Field(s) cannot be blank",Toast.LENGTH_LONG).show();
                else
                {
                    System.out.println("Name: "+name);
                    System.out.println("Email: "+email);
                    System.out.println("Referral: "+referral);
                    User user=new User(name,email,referral,100);
                    db.collection("Users").document(mAuth.getCurrentUser().getUid()).set(user, SetOptions.merge())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.e(TAG,"Successfully added details");
                                    Toast.makeText(DetailsActivity.this,"Details added successfully",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e(TAG, "Error adding document", e);
                                }
                            });
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailsActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

}
