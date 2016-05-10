package com.antonzorin.dottedprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created: Zorin A.
 * Date: 010 10.04.16.
 */
public class DottedProgressBar extends View {

    private Paint mDotsPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mArrowsPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mCenterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mIterator;
    private float mCenter;
    private float mTimeInterval;
    private float mDeltaSize;
    private boolean isClockWiseDots;
    private boolean isCounterClockWiseArrows;
    private boolean hideArrows;
    private int mDotsCount;
    private int mMaxDotSizePercent;
    private int mMinDotSizePercent;
    private int mArrowWidthPercent;
    private int mArrowHourPaddingPercent;
    private int mArrowMinutePaddingPercent;
    private float mRotationSpeed;
    private float mMaxCircleSize;
    private float mSpaceBetweenDots;
    private float mMinuteArrowTopPadding;
    private float mHourArrowTopPadding;
    private float mArrowWidth;
    private float mHourArrowSpeed;
    private float mMinuteArrowSpeed;
    private float mMinuteArrowAngle;
    private float mHourArrowAngle;
    private float DEFAULT_SIZE = 80;

    //region constructor
    public DottedProgressBar(Context context) {
        this(context, null);
    }

    public DottedProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.dpStyle);

    }

    public DottedProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }
    //endregion

    //region getters setters

    public int getBaseColor() {
        return mDotsPaint.getColor();
    }

    public void setBaseColor(int color) {
        mDotsPaint.setColor(color);
        invalidate();
    }

    public int getArrowsColor() {
        return mArrowsPaint.getColor();
    }

    public void setArrowsColor(int color) {
        mArrowsPaint.setColor(color);
        invalidate();
    }

    public int getCenterColor() {
        return mCenterPaint.getColor();
    }

    public void setCenterColor(int color) {
        mCenterPaint.setColor(color);
        invalidate();
    }

    public boolean isClockWiseDots() {
        return isClockWiseDots;
    }

    public void setClockWiseDots(boolean clockWiseDots) {
        isClockWiseDots = clockWiseDots;
        invalidate();
    }

    public boolean isCounterClockWiseArrows() {
        return isCounterClockWiseArrows;
    }

    public void setCounterClockWiseArrows(boolean counterClockWiseArrows) {
        isCounterClockWiseArrows = counterClockWiseArrows;
        invalidate();
    }

    public boolean isHideArrows() {
        return hideArrows;
    }

    public void setHideArrows(boolean hideArrows) {
        this.hideArrows = hideArrows;
        invalidate();
    }

    public int getMaxDotSizePercent() {
        return mMaxDotSizePercent;
    }

    public void setMaxDotSizePercent(int maxDotSizePercent) {
        mMaxDotSizePercent = maxDotSizePercent;
        invalidate();
    }

    public int getMinDotSizePercent() {
        return mMinDotSizePercent;
    }

    public void setMinDotSizePercent(int minDotSizePercent) {
        mMinDotSizePercent = minDotSizePercent;
        invalidate();
    }

    public int getArrowWidthPercent() {
        return mArrowWidthPercent;
    }

    public void setArrowWidthPercent(int arrowWidthPercent) {
        mArrowWidthPercent = arrowWidthPercent;
        invalidate();
    }

    public int getArrowHourPaddingPercent() {
        return mArrowHourPaddingPercent;
    }

    public void setArrowHourPaddingPercent(int arrowHourPaddingPercent) {
        mArrowHourPaddingPercent = arrowHourPaddingPercent;
        invalidate();
    }

    public int getArrowMinutePaddingPercent() {
        return mArrowMinutePaddingPercent;
    }

    public void setArrowMinutePaddingPercent(int arrowMinutePaddingPercent) {
        mArrowMinutePaddingPercent = arrowMinutePaddingPercent;
        invalidate();
    }

    public float getRotationSpeed() {
        return mRotationSpeed;
    }

    public void setRotationSpeed(float rotationSpeed) {
        mRotationSpeed = rotationSpeed;
        invalidate();
    }

    public float getMaxCircleSize() {
        return mMaxCircleSize;
    }

    public void setMaxCircleSize(float maxCircleSize) {
        mMaxCircleSize = maxCircleSize;
        invalidate();
    }

    public float getSpaceBetweenDots() {
        return mSpaceBetweenDots;
    }

    public void setSpaceBetweenDots(float spaceBetweenDots) {
        mSpaceBetweenDots = spaceBetweenDots;
        invalidate();
    }

    public float getMinuteArrowTopPadding() {
        return mMinuteArrowTopPadding;
    }

    public void setMinuteArrowTopPadding(float minuteArrowTopPadding) {
        mMinuteArrowTopPadding = minuteArrowTopPadding;
        invalidate();
    }

    public float getHourArrowTopPadding() {
        return mHourArrowTopPadding;
    }

    public void setHourArrowTopPadding(float hourArrowTopPadding) {
        mHourArrowTopPadding = hourArrowTopPadding;
        invalidate();
    }

    public float getArrowWidth() {
        return mArrowWidth;
    }

    public void setArrowWidth(float arrowWidth) {
        mArrowWidth = arrowWidth;
        invalidate();
    }

    public float getHourArrowSpeed() {
        return mHourArrowSpeed;
    }

    public void setHourArrowSpeed(float hourArrowSpeed) {
        mHourArrowSpeed = hourArrowSpeed;
        invalidate();
    }

    public float getMinuteArrowSpeed() {
        return mMinuteArrowSpeed;
    }

    public void setMinuteArrowSpeed(float minuteArrowSpeed) {
        mMinuteArrowSpeed = minuteArrowSpeed;
        invalidate();
    }

    public float getMinuteArrowAngle() {
        return mMinuteArrowAngle;
    }

    public void setMinuteArrowAngle(float minuteArrowAngle) {
        mMinuteArrowAngle = minuteArrowAngle;
        invalidate();
    }

    public float getHourArrowAngle() {
        return mHourArrowAngle;
    }

    public void setHourArrowAngle(float hourArrowAngle) {
        mHourArrowAngle = hourArrowAngle;
        invalidate();
    }
    //endregion

    void init(Context context, AttributeSet attrs, int defStyleAttr) {
        Resources r = getResources();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DottedProgressBar, defStyleAttr, R.style.Widget_DottedProgressBar);

        int defaultColor = r.getColor(R.color.defaultColor);
        int defaultDotsCount = r.getInteger(R.integer.defaultDotsCount);
        int defaultMaxDotSize = r.getInteger(R.integer.defaultMaxDotSize);
        int defaultMinDotSize = r.getInteger(R.integer.defaultMinDotSize);
        int defaultArrowWidthSize = r.getInteger(R.integer.defaultArrowWidthSize);
        int defaultArrowHourPadding = r.getInteger(R.integer.defaultHourArrowPadding);
        int defaultArrowMinutePadding = r.getInteger(R.integer.defaultMinuteArrowPadding);
        int defaultHourArrowSpeed = r.getInteger(R.integer.defaultHourArrowSpeed);
        int defaultMinuteArrowSpeed = r.getInteger(R.integer.defaultMinuteArrowSpeed);
        int defaultRotationSpeed = r.getInteger(R.integer.defaultRotationSpeed);

        mDotsPaint.setColor(a.getColor(R.styleable.DottedProgressBar_dpBaseColor, defaultColor));
        mCenterPaint.setColor(a.getColor(R.styleable.DottedProgressBar_dpCenterColor, a.getColor(R.styleable.DottedProgressBar_dpBaseColor, defaultColor)));
        mArrowsPaint.setColor(a.getColor(R.styleable.DottedProgressBar_dpArrowsColor, a.getColor(R.styleable.DottedProgressBar_dpBaseColor, defaultColor)));
        isClockWiseDots = a.getBoolean(R.styleable.DottedProgressBar_dpClockwiseDots, false);
        isCounterClockWiseArrows = a.getBoolean(R.styleable.DottedProgressBar_dpCounterClockwiseArrows, false);
        hideArrows = a.getBoolean(R.styleable.DottedProgressBar_dpHideArrows, false);
        mDotsCount = a.getInteger(R.styleable.DottedProgressBar_dpDotsCount, defaultDotsCount);
        mMaxDotSizePercent = a.getInteger(R.styleable.DottedProgressBar_dpMaxDotsSizePercent, defaultMaxDotSize);
        mMinDotSizePercent = a.getInteger(R.styleable.DottedProgressBar_dpMinDotsSizePercent, defaultMinDotSize);
        mArrowWidthPercent = a.getInteger(R.styleable.DottedProgressBar_dpArrowWidthPercent, defaultArrowWidthSize);
        mArrowHourPaddingPercent = a.getInteger(R.styleable.DottedProgressBar_dpArrowHourPaddingPercent, defaultArrowHourPadding);
        mArrowMinutePaddingPercent = a.getInteger(R.styleable.DottedProgressBar_dpArrowMinutePaddingPercent, defaultArrowMinutePadding);
        mHourArrowSpeed = a.getInteger(R.styleable.DottedProgressBar_dpArrowHourSpeed, defaultHourArrowSpeed);
        mHourArrowSpeed *= 0.1f;
        mMinuteArrowSpeed = a.getInteger(R.styleable.DottedProgressBar_dpArrowMinuteSpeed, defaultMinuteArrowSpeed);
        mMinuteArrowSpeed *= 0.1f;
        mRotationSpeed = a.getInteger(R.styleable.DottedProgressBar_dpRotationSpeed, defaultRotationSpeed);
        mRotationSpeed *= 0.1f;
        mSpaceBetweenDots = 360 / mDotsCount;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = mIterator; i < (mDotsCount + mIterator); i++) {
            float circleSize = mMaxCircleSize - mDeltaSize * (i - mIterator);
            canvas.save();
            float speed = mSpaceBetweenDots * i;
            canvas.rotate(isClockWiseDots ? speed : -speed, mCenter, mCenter);
            canvas.drawCircle(mCenter, circleSize, circleSize, mDotsPaint);
            invalidate();
            canvas.restore();
        }

        mTimeInterval += mRotationSpeed;
        int updateThreshold = 3;
        if (mTimeInterval >= updateThreshold) {
            mIterator++;
            mTimeInterval = 0;
        }
        if (!hideArrows) {
            //draw and animate minute arrow
            canvas.save();
            float mSpeed = mMinuteArrowAngle += mMinuteArrowSpeed;
            canvas.rotate(isCounterClockWiseArrows ? -mSpeed : mSpeed, mCenter, mCenter);
            canvas.drawRect(mCenter - mArrowWidth * 0.5f, mMinuteArrowTopPadding, mCenter + mArrowWidth * 0.5f, mCenter, mArrowsPaint);
            invalidate();
            canvas.restore();

            //draw and animate hour arrow
            canvas.save();
            float hSpeed = mHourArrowAngle += mHourArrowSpeed;
            canvas.rotate(isCounterClockWiseArrows ? -hSpeed : hSpeed, mCenter, mCenter);
            canvas.drawRect(mCenter - mArrowWidth * 0.5f, mHourArrowTopPadding, mCenter + mArrowWidth * 0.5f, mCenter, mArrowsPaint);
            invalidate();
            canvas.restore();

            //draw center circle
            canvas.drawCircle(mCenter, mCenter, mArrowWidth * 0.5f, mCenterPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);

        int smallestSide;
        if (hMode == MeasureSpec.EXACTLY || wMode == MeasureSpec.EXACTLY) {
            int w = MeasureSpec.getSize(widthMeasureSpec);
            int h = MeasureSpec.getSize(heightMeasureSpec);
            smallestSide = Math.min(w, h);
        } else {
            float density = getContext().getResources().getDisplayMetrics().density;
            smallestSide = (int) (DEFAULT_SIZE * density);
        }

        mCenter = smallestSide * 0.5f;
        mMaxCircleSize = smallestSide / 100 * mMaxDotSizePercent;//10% of  size
        float minCircleSize = smallestSide / 100 * mMinDotSizePercent;
        mDeltaSize = (mMaxCircleSize - minCircleSize) / mDotsCount;
        mHourArrowTopPadding = smallestSide / 100 * mArrowHourPaddingPercent;//28% of  size
        mMinuteArrowTopPadding = smallestSide / 100 * mArrowMinutePaddingPercent;//23% of  size
        mArrowWidth = smallestSide / 100 * mArrowWidthPercent;//7% of  size

        setMeasuredDimension(smallestSide, smallestSide);
    }
}
