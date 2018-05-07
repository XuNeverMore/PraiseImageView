package com.xunevermore.praiseimageview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import java.util.Random;

public class BoomActivity extends AppCompatActivity {

    private int color;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_boom);
        random = new Random();
        final BoomView boomView = (BoomView) findViewById(R.id.boom);
        final ImageView imageView = (ImageView) findViewById(R.id.image);


        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(boomView, "flyFraction", 0, 1);
        objectAnimator.setDuration(700);

        objectAnimator.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(boomView, "boomFraction", 0, 1);
        objectAnimator1.setDuration(400);

        final AnimatorSet set = new AnimatorSet();
        set.playSequentially(objectAnimator, objectAnimator1);

        boomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                set.start();

            }
        });

        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                //改变颜色
                color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
                boomView.setColor(color);
                DrawableCompat.setTint(imageView.getDrawable(),color);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //循环播放
                set.start();
            }
        });
    }


}
