package mbds.socialcardprojetnfc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;

public class MyCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);
        Switch fb = (Switch) findViewById(R.id.fb);
        Switch insta = (Switch) findViewById(R.id.insta);
        Switch email = (Switch) findViewById(R.id.email);
        Switch tel = (Switch) findViewById(R.id.tel);
        email.setChecked(true);

    }
}
