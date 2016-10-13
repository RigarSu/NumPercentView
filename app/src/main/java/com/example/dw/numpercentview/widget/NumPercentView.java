package com.example.dw.numpercentview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.dw.numpercentview.R;

/**
 * Created by RigarSu on 2016/10/12.
 * E-mail: 16338556@qq.com
 * Comment
 */
public class NumPercentView extends View {
    private static final String TAG = "NumPercentView";

    public static final int DEFAULT_TEXT_COLOR = Color.BLACK;
    public static final int DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    public static final int DEFAULT_STRIP_COLOR = Color.BLACK;
    public static final int DEFAULT_PERCENT_COLOR = 0xff0bc1f0;
    public static final int DEFAULT_TOTAL_VALUE = 0;
    public static final int DEFAULT_CURRENT_VUALE = 0;

    /**
     * text size
     */
    private float mTextSize;

    /**
     * text color
     */
    private int mTextColor;

    /**
     * background color
     */
    private int mBackgroudColor;

    /**
     * strip color
     */
    private int mStripColor;

    /**
     * strip width
     */
    private int mStripWidth = 1;

    /**
     * percent value color
     */
    private int mPercentColor;

    /**
     * total num value
     */
    private int mTotalValue;

    /**
     * current num value
     */
    private int mCurrentValue;

    /**
     * pervent value
     */
    private float mPercentValue;

    /**
     * text to draw
     */
    private String mText;

    /**
     * paint to draw text
     */
    private Paint mTextPaint;

    /**
     * the bound of text
     */
    private Rect mTextBound;

    /**
     * paint to draw rects and lines
     */
    private Paint mPaint;

    public NumPercentView(Context context) {
        this(context, null);
    }

    public NumPercentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumPercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NumPercentView, defStyleAttr, 0);
        try {
            mTextSize = a.getDimensionPixelSize(R.styleable.NumPercentView_textColor, (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            mTextColor = a.getColor(R.styleable.NumPercentView_textColor, DEFAULT_TEXT_COLOR);
            mBackgroudColor = a.getColor(R.styleable.NumPercentView_backgroundColor, DEFAULT_BACKGROUND_COLOR);
            mStripColor = a.getColor(R.styleable.NumPercentView_stripColor, DEFAULT_STRIP_COLOR);
            mPercentColor = a.getColor(R.styleable.NumPercentView_percentColor, DEFAULT_PERCENT_COLOR);
            mTotalValue = a.getInteger(R.styleable.NumPercentView_totalValue, DEFAULT_TOTAL_VALUE);
            mCurrentValue = a.getInteger(R.styleable.NumPercentView_currentValue, DEFAULT_CURRENT_VUALE);
            mPercentValue = mTotalValue == 0 ? 0 : (float) mCurrentValue / mTotalValue;
        } finally {
            a.recycle();
        }

        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setAntiAlias(true);

        mPaint = new Paint();
        mTextBound = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //get the measure mode
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //get the measure size
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        mText = mCurrentValue + "/" + mTotalValue;
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            float textWidth = mTextBound.width();
            width = (int) (getPaddingLeft() + getPaddingRight() + textWidth + 2 * mStripWidth);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            float textHeight = mTextBound.height();
            height = (int) (getPaddingTop() + getPaddingBottom() + textHeight + 2 * mStripWidth);
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw background
        mPaint.setColor(mBackgroudColor);
        canvas.drawRect(mStripWidth, mStripWidth, getMeasuredWidth() - mStripWidth, getMeasuredHeight() - mStripWidth, mPaint);

        //draw rect
        mPaint.setColor(mStripColor);
        mPaint.setStrokeWidth(mStripWidth);
        canvas.drawRect(0, 0, getWidth() - mStripWidth, mStripWidth, mPaint);
        canvas.drawRect(0, 0, mStripWidth, getHeight() - mStripWidth, mPaint);
        canvas.drawRect(0, getHeight() - mStripWidth, getWidth(), getHeight(), mPaint);
        canvas.drawRect(getWidth() - mStripWidth, 0, getWidth(), getHeight() - mStripWidth, mPaint);

        //draw percent color
        mPaint.setColor(mPercentColor);
        canvas.drawRect(mStripWidth, mStripWidth, mPercentValue * (getMeasuredWidth() - mStripWidth), getMeasuredHeight() - mStripWidth, mPaint);

        //draw text
        mText = mCurrentValue + "/" + mTotalValue;
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
        canvas.drawText(mText, getWidth() / 2 - mTextBound.width() / 2, getHeight() / 2 + mTextBound.height() / 2, mTextPaint);
    }


    public void setTotalValue(int totalValue) {
        if (totalValue < 0)
            return;
        mTotalValue = totalValue;
        mCurrentValue = mTotalValue == 0 ? 0 : mCurrentValue;
        mPercentValue = mTotalValue == 0 ? 0 : (float) mCurrentValue / mTotalValue;
        invalidate();
    }

    public void setCurrentValue(int currentValue) {
        if (currentValue < 0)
            return;
        mCurrentValue = mTotalValue == 0 ? 0 : currentValue;
        mPercentValue = mTotalValue == 0 ? 0 : (float) mCurrentValue / mTotalValue;
        invalidate();
    }
}
