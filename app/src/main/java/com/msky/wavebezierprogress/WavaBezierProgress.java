package com.msky.wavebezierprogress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by AIRY on 1/8/2016.
 */

public class WavaBezierProgress extends View implements View.OnClickListener {

    private final int DEFAULT_LOCATION_TESTSIZE=100;
    private final int DEFAULT_PROGRESS_TESTSIZE = 10;
    private Paint mPaint;
    private Paint mTextPaint;

    private Path mPath;
    private int mWaveLength = 1000;
    private int mOffset;
    private int mscreenHeight;
    private int mscreenWidth;
    private int mWaveCount;
    private int mCenterY;
    private int c = 10;
    private int textc;
    private int progressColor;
    private int textProgressColor;
    private int progerssTestSize;
    private int progerssTestLocation;

   /* onProgress monProgress;


    public interface onProgress{
         void setprogress(int progress);

    }
    public void setonProgress(onProgress monProgress){
        this.monProgress=monProgress;
    }*/

    /*
    *
    * 设置进度多少
    * */

    public void setProgress(int a) {

        this.c = 100 - a;
        if (c < 0) c = 0;
        if (c > 100) c = 100;
        textc = a;
        mCenterY = (mscreenHeight / 100)*c;


    }

    public WavaBezierProgress(Context context) {
        super(context);

    }

    /*
    *
    * 测量初始化精度条
    * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mscreenWidth = MeasureSpec.getSize(widthMeasureSpec);
        mscreenHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWaveCount = (int) Math.round(mscreenWidth / mWaveLength + 1.5);
        mCenterY = (mscreenHeight / 100) * c;

    }
    /*
    *
    * 初始化
    * */

    public WavaBezierProgress(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.WavaBezierProgress);
        textProgressColor=array.getColor
                (R.styleable.WavaBezierProgress_progressTextColor,Color.YELLOW);
        progressColor=array.getColor
                (R.styleable.WavaBezierProgress_progressColor,Color.RED);

        progerssTestSize = array.getDimensionPixelSize
                (R.styleable.WavaBezierProgress_progressTextSize,DEFAULT_PROGRESS_TESTSIZE);
        progerssTestLocation=array.getDimensionPixelSize
                (R.styleable.WavaBezierProgress_progressTextLocation,DEFAULT_LOCATION_TESTSIZE);
        array.recycle();


        mPath = new Path();

        mTextPaint = new Paint();
        mTextPaint.setColor(textProgressColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(progerssTestSize);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(progressColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        /*
        * 初始化动画发生器
        * */

        ValueAnimator animator = ValueAnimator.ofInt(0, mWaveLength);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();


    }


    public WavaBezierProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(-mWaveLength + mOffset, mCenterY);
        for (int i = 0; i < mWaveCount; i++) {//50是波纹的大小

            mPath.quadTo((-mWaveLength * 3 / 4) + (i * mWaveLength) + mOffset, mCenterY + 50,
                    (-mWaveLength / 2) + (i * mWaveLength) + mOffset, mCenterY);

            mPath.quadTo((-mWaveLength / 4) + (i * mWaveLength) + mOffset, mCenterY - 50, i * mWaveLength + mOffset, mCenterY);
        }

        mPath.lineTo(mscreenWidth, mscreenHeight);
        mPath.lineTo(0, mscreenHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

        canvas.drawText("" + textc + "%", progerssTestLocation, progerssTestLocation, mTextPaint);

    }

    @Override
    public void onClick(View v) {
       /* c--;
        mCenterY = (mscreenHeight / 100) * c;*/
    }
}
