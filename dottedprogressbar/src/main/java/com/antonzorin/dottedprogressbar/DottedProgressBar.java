package com.antonzorin.dottedprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created: Zorin A.
 * Date: 010 10.04.16.
 */
public class DottedProgressBar extends View {

    private Paint mPaint;

    private float mMaxCircleSize;
    private final int mCirclesCount = 12;
    private float mCenter = 0;
    private float mDegreePerCircle;
    private float mMinCircleSize;
    private float mDeltaSize;
    private float mMinuteArrowTopPadding;
    private float mHourArrowTopPadding;
    private float mArrowWidth;
    private float mMituteArrowSpeed = 2f;
    private float mHourArrowSpeed = 0.5f;
    private float mMinuteArrowAngle;
    private float mHourArrowAngle;

    int iterator;
    float time;
    float timeThreshold = 3f;

    public DottedProgressBar(Context context) {
        super(context);
        init();
    }

    public DottedProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DottedProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void init() {
        Resources r = getResources();

        int defaultColor = r.getColor(R.color.defaultColor);

        mDegreePerCircle = 360 / mCirclesCount;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.defaultColor));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int result = Math.min(w, h);

        mCenter = result * 0.5f;

        mMaxCircleSize = result / 100 * 9.5f;//9.5% of  size
        mMinCircleSize = result / 100 * 3f; //3% of  size
        mDeltaSize = (mMaxCircleSize - mMinCircleSize) / mCirclesCount;

        mMinuteArrowTopPadding = result / 100 * 20f;//20% of  size
        mHourArrowTopPadding = result / 100 * 25f;//25% of  size
        mArrowWidth = result / 100 * 7f;//7% of  size

        setMeasuredDimension(result, result);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = (0 + iterator); i <= (mCirclesCount + iterator); i++) {
            float circleSize = mMaxCircleSize - mDeltaSize * (i - iterator);
            canvas.save();
            float d = mDegreePerCircle * i;
            canvas.rotate(-d, mCenter, mCenter);
            canvas.drawCircle(mCenter, circleSize, circleSize, mPaint);
            invalidate();
            canvas.restore();
        }

        time += 0.5f;
        if (time >= timeThreshold) {
            iterator++;
            time = 0;
        }

        //draw and animate minute arrow
        canvas.save();
        canvas.rotate(mMinuteArrowAngle += mMituteArrowSpeed, mCenter, mCenter);
        canvas.drawRect(mCenter - mArrowWidth * 0.5f, mMinuteArrowTopPadding, mCenter + mArrowWidth * 0.5f, mCenter, mPaint);
        invalidate();
        canvas.restore();

        //draw and animate hour arrow
        canvas.save();
        canvas.rotate(mHourArrowAngle += mHourArrowSpeed, mCenter, mCenter);
        canvas.drawRect(mCenter - mArrowWidth * 0.5f, mHourArrowTopPadding, mCenter + mArrowWidth * 0.5f, mCenter, mPaint);
        invalidate();
        canvas.restore();

        //draw center circle
        canvas.drawCircle(mCenter, mCenter, mArrowWidth * 0.5f, mPaint);
    }
}
