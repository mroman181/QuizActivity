package com.example.dam2a21.quizactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;

public class QuizSplashActivity extends QuizActivity {


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

       // textView = (TextView) findViewById(R.id.textView);

        SharedPreferences settings =
                getSharedPreferences(GAME_PREFERENCES, MODE_PRIVATE);

        Calendar rightNow = Calendar.getInstance();
        int minute = rightNow.get(Calendar.MINUTE);

        if (settings.contains("LastTime") == true) {
            // We have a user name

            String user = settings.getString("UserName", "Default");
            Log.i("time", "Last time " + user + " : "+minute);
           // textView.setText(minute);

        }
            SharedPreferences.Editor prefEditor = settings.edit();
            prefEditor.putString("UserName", "JaneDoe");
            prefEditor.putInt("LastTime", minute);
            prefEditor.commit();

        TextView logo1 = (TextView) findViewById(R.id.TextViewTopTitle);
        Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo1.startAnimation(fade1);

        TextView logo2 = (TextView) findViewById(R.id.TextViewBottomTitle);
        Animation fade2 = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
        logo2.startAnimation(fade2);

        Animation spinin = AnimationUtils.loadAnimation(this, R.anim.custom_anim);
        LayoutAnimationController controller =
                new LayoutAnimationController(spinin);
        TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            row.setLayoutAnimation(controller);
        }


       fade2.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(QuizSplashActivity.this,
                        QuizMenuActivity.class));
                QuizSplashActivity.this.finish();
            }

           public void onAnimationRepeat(Animation animation){}
           public void onAnimationStart(Animation animation){}
       });

    }

    @Override
    protected void onPause() {
        super.onPause();
// Stop the animation
        TextView logo1 = (TextView) findViewById(R.id.TextViewTopTitle);
        logo1.clearAnimation();
        TextView logo2 = (TextView) findViewById(R.id.TextViewBottomTitle);
        logo2.clearAnimation();
// ... stop other animations

        TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            row.clearAnimation();
        }
    }


}
