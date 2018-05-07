package com.xunevermore.praiseimageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class BoomView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float flyFraction = 0;
    private float boomFraction = 0;
    private BoomUtil boomUtil;
    private Random random;
    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public BoomView(Context context) {
        super(context);
        initBoomUtil();
    }

    public BoomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBoomUtil();
    }

    private void initBoomUtil() {
        boomUtil = new BoomUtil(12, 200, 12);

    }

    public float getBoomFraction() {
        return boomFraction;
    }

    public void setBoomFraction(float boomFraction) {
        this.boomFraction = boomFraction;
        invalidate();
    }

    public float getFlyFraction() {
        return flyFraction;
    }

    public void setFlyFraction(float flyFraction) {
        this.flyFraction = flyFraction;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(flyFraction!=1){
            int y = (int) ((1 - flyFraction * 3 / 4) * canvas.getHeight());
            int length = (int) (10 * (1 - flyFraction));
            mPaint.setColor(color);
            canvas.drawRect(canvas.getWidth() / 2 - 5, y, canvas.getWidth() / 2 + 5, y + 10, mPaint);
        }

        if (boomFraction != 0) {
            boomUtil.drawBoom(canvas, canvas.getWidth() / 2, canvas.getHeight() / 4, boomFraction, color);
        }
    }
}
