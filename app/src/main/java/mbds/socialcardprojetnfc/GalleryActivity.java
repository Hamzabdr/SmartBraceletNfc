package mbds.socialcardprojetnfc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class GalleryActivity extends AppCompatActivity {

    ListView cards;
    ArrayList<SocialCard> cardss = new ArrayList<SocialCard> ();
    FirebaseAuth mAuth;
    FirebaseUser user;
    String userID;
    CardsAdapter cA;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userID = user.getUid();

        cards = (ListView) findViewById(R.id.listCards);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                basicRead(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(GalleryActivity.this,ScanActivity.class);
                startActivity(I);
                finish();
            }
        });

    }




    @Override
    protected void onResume() {
        super.onResume();

    }

    public void basicRead(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            for(long j=0;j<=ds.getChildrenCount();j++){
                SocialCard sc = new SocialCard();
                if (ds.child(userID).child("Card "+j).getValue(SocialCard.class) != null) {
                sc.setEmail(ds.child(userID).child("Card "+j).getValue(SocialCard.class).getEmail());
                sc.setFacebook(ds.child(userID).child("Card "+j).getValue(SocialCard.class).getFacebook());
                sc.setInstagram(ds.child(userID).child("Card "+j).getValue(SocialCard.class).getInstagram());
                sc.setTelephone(ds.child(userID).child("Card "+j).getValue(SocialCard.class).getTelephone());
                cardss.add(sc);
                cA = new CardsAdapter(this, cardss);
                cA.data = cardss;
                cards.setAdapter(cA);
            }
            }
        }
    }
}
