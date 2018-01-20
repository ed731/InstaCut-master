package com.example.ellie.instacut_master;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends Activity {

    //images and texts used in splash screen
    ImageView clippers;
    ImageView scissors;
    ImageView clippers_1;
    ImageView scissors_1;
    TextView insta;
    TextView cuts;

    //timer of splash
    private static int SPLASH_TIME = 5500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //instantiate images and text
        clippers = (ImageView)findViewById(R.id.clippers);
        scissors = (ImageView)findViewById(R.id.scissors);
        clippers_1 = (ImageView)findViewById(R.id.clippers_1);
        scissors_1 = (ImageView)findViewById(R.id.scissors_1);
        insta = (TextView)findViewById(R.id.insta);
        cuts = (TextView)findViewById(R.id.cuts);

        //animations
        final Animation fade_letters = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fade_out);
        final Animation move_right = AnimationUtils.loadAnimation(getBaseContext(),R.anim.horizontal_motion_right);
        final Animation move_left = AnimationUtils.loadAnimation(getBaseContext(),R.anim.horizontal_motion_left);

        //begin to fade out letters
        insta.startAnimation(fade_letters);
        cuts.startAnimation(fade_letters);

        //center clippers and scissors are initially invisible
        clippers_1.setVisibility(View.INVISIBLE);
        scissors_1.setVisibility(View.INVISIBLE);

        fade_letters.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //after fading out letters, set letters to invisible
                insta.setVisibility(View.INVISIBLE);
                cuts.setVisibility(View.INVISIBLE);

                //start to move start clippers and scissors to center
                clippers.startAnimation(move_right);
                scissors.startAnimation(move_left);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        move_right.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //after moving clippers and scissors to center, set start clippers and scissors invisible
                clippers.setVisibility(View.INVISIBLE);
                scissors.setVisibility(View.INVISIBLE);

                //center clippers and scissors are now visible
                clippers_1.setVisibility(View.VISIBLE);
                scissors_1.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        //runs LoginActivity after the specified time is finished, 5.5s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                finish();
            }
        },SPLASH_TIME);


    }
}
