package com.v.view.activities;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.v.view.R;
import com.v.view.animation.InterpolatorEvaluator;

public class ActivityAnimation extends Activity implements View.OnClickListener {
    private final String TAG = "ActivityAnimation";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        Button btn = (Button) findViewById(R.id.btn_start);
        btn.setOnClickListener(this);

        Button btnAnimation = (Button)findViewById(R.id.btn_perform_animation);
        btnAnimation.setOnClickListener(this);

        mImageView = (ImageView) findViewById(R.id.iv_frame);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {

            loadAnimator();
            startFrameAnimation();
            //loadAnimatorFromXml();
            //useAnimatorUpdateListener();
        } else if (v.getId() == R.id.btn_perform_animation) {
            LoadAnimation();
            //startFrameAnimation();
            //LoadAnimationFromXml();
        }
    }


    private void useAnimatorUpdateListener() {
        final Point ptStart = new Point((int)mImageView.getX(), (int)mImageView.getY());
        final Point ptEnd = new Point(ptStart.x + 400, ptStart.y + 400);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private BounceInterpolator mInterpolator = new BounceInterpolator();
            private TypeEvaluator<Point> mEvaluator = InterpolatorEvaluator.getEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                // 获得当前动画的进度值，整型 1~100 之间
                int currentValue = (Integer) animator.getAnimatedValue();
                //Log.d(TAG, "current value = " + currentValue);

                // 获取当前进度占整个动画过程的比例，浮点型 0~1 之间
                float fraction = animator.getAnimatedFraction();
                //float fraction = mInterpolator.getInterpolation(animator.getAnimatedFraction());
                //Log.d(TAG, "fraction  = " + fraction);

                Point pt = mEvaluator.evaluate(fraction, ptStart, ptEnd);
                mImageView.setX(pt.x);
                mImageView.setY(pt.y);
                mImageView.requestLayout();
            }
        });
        valueAnimator.setDuration(3 * 1000).start();
    }

    private void loadAnimatorFromXml() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater
                .loadAnimator(this, R.animator.animator_set);

        // Animator Listener
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "onAnimationStart " + animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onAnimationEnd " + animation);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG, "onAnimationCancel " + animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG, "onAnimationRepeat " + animation);
            }
        });
        set.setTarget(mImageView);
        set.start();
    }

    private void loadAnimator() {
//        ObjectAnimator.ofInt(mImageView, "backgroundColor",
//                0xFFFFFFFF, 0xFF000000, 0xFFFFFFFF).setDuration(2*1000).start();

        AnimatorSet set = new AnimatorSet();
//        set.playTogether(
//                ObjectAnimator.ofFloat(mImageView, "rotationX", 0, 360),
//                ObjectAnimator.ofFloat(mImageView, "rotationY", 0, 180),
//                ObjectAnimator.ofFloat(mImageView, "rotation", 0, -90),
//                ObjectAnimator.ofFloat(mImageView, "translationX", 0, 200),
//                ObjectAnimator.ofFloat(mImageView, "translationY", 0, 300),
//                ObjectAnimator.ofFloat(mImageView, "scaleX", 1, 1.5f),
//                ObjectAnimator.ofFloat(mImageView, "scaleY", 1, 1.5f),
//                ObjectAnimator.ofFloat(mImageView, "alpha", 1, 0.25f, 1)
//        );
//        set.setDuration(5 * 1000).start();


        set.playTogether(
                ObjectAnimator.ofFloat(mImageView, "translationX", 0, 300),
                ObjectAnimator.ofFloat(mImageView, "translationY", 0, 300)
        );
        set.setInterpolator(InterpolatorEvaluator.getInterpolator());
        set.setDuration(3 * 1000).start();
    }

    // 帧动画
    private void startFrameAnimation() {
        AnimationDrawable drawable = (AnimationDrawable) mImageView.getDrawable();
        drawable.start();
    }

    // 从 xml 文件加载 View 动画
    private void LoadAnimationFromXml() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
        Button btn = (Button) findViewById(R.id.btn_perform_animation);
        btn.startAnimation(animation);
    }

    // 动态加载 View 动画
    private void LoadAnimation() {
        AnimationSet set = new AnimationSet(false);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.5f, 0.0f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setDuration(3*1000);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());

        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 720.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnimation.setDuration(3*1000);

        set.addAnimation(scaleAnimation);
        set.addAnimation(rotateAnimation);

        Button btn = (Button) findViewById(R.id.btn_perform_animation);
        btn.startAnimation(set);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AnimationDrawable drawable = (AnimationDrawable) mImageView.getDrawable();
        if (drawable != null && drawable.isRunning()) {
            drawable.stop();
        }
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
    }
}
