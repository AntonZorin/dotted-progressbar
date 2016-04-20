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

    private float mMaxCircleSize;
    private int mDotsCount;
    private float mCenter;
    private float mSpaceBetweenDots;
    private float mMinCircleSize;
    private float mDeltaSize;
    private float mMinuteArrowTopPadding;
    private float mHourArrowTopPadding;
    private float mArrowWidth;
    private float mHourArrowSpeed;
    private float mMinuteArrowSpeed;
    private float mMinuteArrowAngle;
    private float mHourArrowAngle;
    private boolean isClockWiseDots;
    private boolean isCounterClockWiseArrows;
    private boolean hideArrows;
    private int mMaxDotSizePercent;
    private int mMinDotSizePercent;
    private int mArrowWidthPercent;
    private int mArrowHourPaddingPercent;
    private int mArrowMinutePaddingPercent;
    private float mRotationSpeed;

    private int mIterator;
    private float mTime;
    private final float UPDATE_THRESHOLD = 3f;

    public DottedProgressBar(Context context) {
        super(context);
        init(context, null);
    }

    public DottedProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DottedProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    void init(Context context, AttributeSet attrs) {
        Resources r = getResources();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DottedProgressBar);
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

        mDotsPaint.setColor(a.getColor(R.styleable.DottedProgressBar_baseColor, defaultColor));
        mCenterPaint.setColor(a.getColor(R.styleable.DottedProgressBar_centerColor, a.getColor(R.styleable.DottedProgressBar_baseColor, defaultColor)));
        mArrowsPaint.setColor(a.getColor(R.styleable.DottedProgressBar_arrowsColor, a.getColor(R.styleable.DottedProgressBar_baseColor, defaultColor)));
        isClockWiseDots = a.getBoolean(R.styleable.DottedProgressBar_clockwiseDots, false);
        isCounterClockWiseArrows = a.getBoolean(R.styleable.DottedProgressBar_counterClockwiseArrows, false);
        hideArrows = a.getBoolean(R.styleable.DottedProgressBar_hideArrows, false);
        mDotsCount = a.getInteger(R.styleable.DottedProgressBar_dotsCount, defaultDotsCount);
        mMaxDotSizePercent = a.getInteger(R.styleable.DottedProgressBar_maxDotsSizePercent, defaultMaxDotSize);
        mMinDotSizePercent = a.getInteger(R.styleable.DottedProgressBar_minDotsSizePercent, defaultMinDotSize);
        mArrowWidthPercent = a.getInteger(R.styleable.DottedProgressBar_arrowWidthPercent, defaultArrowWidthSize);
        mArrowHourPaddingPercent = a.getInteger(R.styleable.DottedProgressBar_arrowHourPaddingPercent, defaultArrowHourPadding);
        mArrowMinutePaddingPercent = a.getInteger(R.styleable.DottedProgressBar_arrowMinutePaddingPercent, defaultArrowMinutePadding);
        mHourArrowSpeed = a.getInteger(R.styleable.DottedProgressBar_arrowHourSpeed, defaultHourArrowSpeed);
        mHourArrowSpeed *= 0.1f;
        mMinuteArrowSpeed = a.getInteger(R.styleable.DottedProgressBar_arrowMinuteSpeed, defaultMinuteArrowSpeed);
        mMinuteArrowSpeed *= 0.1f;
        mRotationSpeed = a.getInteger(R.styleable.DottedProgressBar_rotationSpeed, defaultRotationSpeed);
        mRotationSpeed *= 0.1f;
        mSpaceBetweenDots = 360 / mDotsCount;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int smallestSide = Math.min(w, h);

        mCenter = smallestSide * 0.5f;
        mMaxCircleSize = smallestSide / 100 * mMaxDotSizePercent;//10% of  size
        mMinCircleSize = smallestSide / 100 * mMinDotSizePercent; //3% of  size
        mDeltaSize = (mMaxCircleSize - mMinCircleSize) / mDotsCount;
        mHourArrowTopPadding = smallestSide / 100 * mArrowHourPaddingPercent;//28% of  size
        mMinuteArrowTopPadding = smallestSide / 100 * mArrowMinutePaddingPercent;//23% of  size
        mArrowWidth = smallestSide / 100 * mArrowWidthPercent;//7% of  size
        setMeasuredDimension(smallestSide, smallestSide);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = (0 + mIterator); i <= (mDotsCount + mIterator); i++) {
            float circleSize = mMaxCircleSize - mDeltaSize * (i - mIterator);
            canvas.save();
            float speed = mSpaceBetweenDots * i;
            canvas.rotate(isClockWiseDots ? speed : -speed, mCenter, mCenter);
            canvas.drawCircle(mCenter, circleSize, circleSize, mDotsPaint);
            invalidate();
            canvas.restore();
        }

        mTime += mRotationSpeed;
        if (mTime >= UPDATE_THRESHOLD) {
            mIterator++;
            mTime = 0;
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
}
