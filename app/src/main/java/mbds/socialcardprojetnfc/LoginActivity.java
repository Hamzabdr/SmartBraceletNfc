package mbds.socialcardprojetnfc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText Email;
    EditText Password;
    Button Connect;
    Button Register;
    ProgressDialog bar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Email", "hamzaboudradar@gmail.com");
        editor.putString("Password", "hamzaoui");
        editor.commit();

        bar = new ProgressDialog(this);
        Email = (EditText) findViewById(R.id.login_box);
        Password = (EditText) findViewById(R.id.pass_box);
        Connect = (Button) findViewById(R.id.valid_btn);
        Register = (Button) findViewById(R.id.register_btn);

        String email = preferences.getString("Email", "");
        String password = preferences.getString("Password", "");
        Email.setText(email);
        Password.setText(password);
        Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectUser();


            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }

    private void connectUser(){
        String email = Email.getText().toString();
        String pass = Password.getText().toString();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter your email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter your password",Toast.LENGTH_SHORT).show();
            return;

        }
        bar.setMessage("Connexion...");
        bar.show();
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    bar.cancel();
                    Toast.makeText(LoginActivity.this,"Connected !", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(LoginActivity.this,MenuActivity.class);
                    I.putExtra("Email", Email.getText().toString());
                    finish();
                    startActivity(I);

                }else {
                    Toast.makeText(LoginActivity.this,"Connexion failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    bar.cancel();
                }

            }
        });
    }





}
