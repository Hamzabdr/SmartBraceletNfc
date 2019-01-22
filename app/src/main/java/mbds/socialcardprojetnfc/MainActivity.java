package mbds.socialcardprojetnfc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText Email;
    EditText Password;
    EditText Password2;
    Button Connect;
    Button Register;
    ProgressDialog bar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        bar = new ProgressDialog(this);
        Email = (EditText) findViewById(R.id.login_box);
        Password = (EditText) findViewById(R.id.pass_box);
        Password2 = (EditText) findViewById(R.id.pass_box2);

        Connect = (Button) findViewById(R.id.valid_btn);
        Register = (Button) findViewById(R.id.register_btn);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                //basicReadWrite("message1","97aab!");
            }
        });
    }
    // Write a message to the database
    public void basicReadWrite(String k, String s) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(k);
        myRef.setValue(s);
        Toast.makeText(this,"Saved !",Toast.LENGTH_SHORT).show();

    }
    private void registerUser(){
            String email = Email.getText().toString();
            String pass = Password.getText().toString();
            String pass2 = Password2.getText().toString();

    if (TextUtils.isEmpty(email)){
        Toast.makeText(this,"Please enter your email",Toast.LENGTH_SHORT).show();
        return;
    }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter your password",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass2)){
            Toast.makeText(this,"Please confirm your password",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(pass2)){
            Toast.makeText(this,"your passwords don't match",Toast.LENGTH_SHORT).show();
            return;
        }
     bar.setMessage("Saving...");
    bar.show();
    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                bar.cancel();
                if (firebaseAuth.getCurrentUser() != null) {
                    finish();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }
                Toast.makeText(MainActivity.this,"Registered succesfully", Toast.LENGTH_SHORT).show();

            }else {
                bar.cancel();
                Toast.makeText(MainActivity.this,"Registered failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    });
    }





}
