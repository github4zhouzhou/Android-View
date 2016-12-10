package com.v.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.v.view.R;

public class ActivityLayoutAnimation extends Activity {

    private String[] data={"One","Two","Three","Four",
            "Five","Six","Seven","Eight","Nine",
            "ten","eleven","twelve","thirteen"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(ActivityLayoutAnimation.this,
                android.R.layout.simple_list_item_1,data);

        ListView listView = (ListView) findViewById(R.id.lv_animation);
        listView.setAdapter(adapter);

//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_slide_left);
//        LayoutAnimationController controller = new LayoutAnimationController(animation);
//        controller.setDelay(0.5f);
//        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        listView.setLayoutAnimation(controller);
    }
}
