package com.v.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.v.view.R;

public class ActivityMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.button2) {
            Intent intent = new Intent(this, ActivitySlideConflict_1.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button3) {
            Intent intent = new Intent(this, ActivitySlideConflict_2.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_animation) {
            Intent intent = new Intent(this, ActivityAnimation.class);
            startActivity(intent);
            //overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
        } else if (v.getId() == R.id.btn_layout_animation) {
            Intent intent = new Intent(this, ActivityLayoutAnimation.class);
            startActivity(intent);
        }
    }
}
