package com.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ArcCircleProgressBar extends View {
    private Paint mPaint;
    private Paint mArcPaint;

    private RectF mArcRect;
    private int mBoderWidth = 30;
    private int mRadius = 50;

    public ArcCircleProgressBar(Context context) {
        super(context);
        init(context, null);
    }

    public ArcCircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ArcCircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ArcCircleProgressBar);
            mRadius = (int) array.getDimension(R.styleable.ArcCircleProgressBar_radiusWidth,50);
            mBoderWidth = (int) array.getDimension(R.styleable.ArcCircleProgressBar_strokeWidth,30);
            array.recycle();
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBoderWidth);


        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(Color.BLUE);
        mArcPaint.setStrokeWidth(mBoderWidth);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);

        mArcRect = new RectF();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        canvas.drawCircle(width >> 1, height >> 1, mRadius, mPaint);

        canvas.drawArc(mArcRect, -90, 90, false, mArcPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int left = getMeasuredWidth() / 2 - mRadius;
        int top = getMeasuredHeight() / 2 - mRadius;
        int right = getMeasuredHeight() / 2 + mRadius;
        int bottom = getMeasuredHeight() / 2 + mRadius;
        mArcRect = new RectF(left, top, right, bottom);
    }
}
