package com.complus.community;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.complus.community.models.EarnEvent;
import com.complus.community.models.RewardEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RewardActivity extends AppCompatActivity {

    private static final String TAG = "RewardActivity";
    TextView rewardTitle, rewardPoints,rewardDesc;
    ImageView rewardpic;
    Button redeem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        redeem = (Button)findViewById(R.id.reward_redeem_button);
        String rid = getIntent().getStringExtra("ID");
        Log.d(TAG, "onCreate: "+rid);

        rewardTitle = (TextView) findViewById(R.id.reward_title);
        rewardPoints = (TextView) findViewById(R.id.reward_points_detail);
        rewardDesc = (TextView) findViewById(R.id.reward_desc);
        rewardpic = (ImageView) findViewById(R.id.reward_pic);
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("reward-events").child(rid);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RewardEvent re = dataSnapshot.getValue(RewardEvent.class);
                rewardTitle.setText(re.getTitle());
                rewardPoints.setText(re.getPoints());
                rewardDesc.setText(re.getDesc());
                Glide.with(getApplicationContext()).load(re.getPiclink()).into(rewardpic);
            }

        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
