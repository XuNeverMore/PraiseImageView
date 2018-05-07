package com.xunevermore.praiseimageview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by Administrator on 2018/4/8 0008.
 */

public class PraiseTextView extends android.support.v7.widget.AppCompatTextView {

    private Paint mPaint;
    private int colorSelected = 0xffff0000;//
    private int pointRadius = 5;
    private int pointPadding = 20;
    private int pointCount = 8;

    public PraiseTextView(Context context) {
        super(context);
        init();
    }


    public PraiseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PraiseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(colorSelected);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void setSelected(boolean selected) {

        super.setSelected(selected);
        if (selected) {
            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1.2f,1);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1.2f,1);
            ObjectAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY);
            scaleAnimator.setDuration(400);

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "fraction", 0f, 1f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    setFraction(0);
                }
            });

            AnimatorSet set = new AnimatorSet();
            set.setInterpolator(new AccelerateInterpolator());
            set.playSequentially(scaleAnimator, objectAnimator);
            set.start();
        }

    }

    private float fraction = 0;

    public float getFraction() {
        return fraction;
    }

    public void setFraction(float fraction) {
        this.fraction = fraction;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (fraction != 0) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();

            Drawable[] compoundDrawables = getCompoundDrawables();
            int min = compoundDrawables[0].getIntrinsicWidth() / 2;

            int radius = (int) ((width / 2 - pointPadding-min) * fraction)+min;

            mPaint.setAlpha((int) (255*(1-fraction)));
            for (int i = 0; i < pointCount; i++) {

                double angle = i * 2 * Math.PI / pointCount;
                float x = (float) Math.cos(angle) * radius + width / 2;
                float y = (float) Math.sin(angle) * radius + height / 2;
                canvas.drawCircle(x, y, pointRadius, mPaint);

            }
        }
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);


    }
}
