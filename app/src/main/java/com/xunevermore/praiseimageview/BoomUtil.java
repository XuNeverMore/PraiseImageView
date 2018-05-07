package com.xunevermore.praiseimageview;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class BoomUtil {

    private Paint mPaint;
    private int pointCount;//爆炸时的粒子数量
    private int radiusMax;//最大爆炸半径
    private int pointRadius;//粒子半径


    public BoomUtil(int pointCount, int radiusMax, int pointRadius) {
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.pointCount = pointCount;
        this.radiusMax = radiusMax;
        this.pointRadius = pointRadius;
    }

    public void drawBoom(Canvas canvas, int cx,int cy,float fraction,int color) {
        mPaint.setColor(color);
        mPaint.setAlpha((int) (255 * (1 - fraction)));
        int radius = (int) (radiusMax * fraction);
        for (int i = 0; i < pointCount; i++) {
            double angle = i * 2 * Math.PI / pointCount;
            float x = (float) Math.cos(angle) * radius+cx;
            float y = (float) Math.sin(angle) * radius+cy;
            canvas.drawCircle(x, y, pointRadius, mPaint);
        }
    }
}
