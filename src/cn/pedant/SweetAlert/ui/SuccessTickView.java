package cn.pedant.SweetAlert.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class SuccessTickView extends View {
    private float mDensity = -1;
    private Paint mPaint;
    private final float CONST_RADIUS = dip2px(1.2f);
    private final float CONST_RECT_WEIGHT = dip2px(3);
    private final float CONST_LEFT_RECT_W = dip2px(15);
    private final float CONST_RIGHT_RECT_W = dip2px(25);

    private float mLeftAddWidth;
    private float mRightAddWidth;
    private boolean mLeftLineGrowMode;

    public SuccessTickView(Context context) {
        super(context);
        init();
    }

    public SuccessTickView(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    private void init () {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#A5DC86"));
        mLeftAddWidth = CONST_LEFT_RECT_W;
        mRightAddWidth = CONST_RIGHT_RECT_W;
        mLeftLineGrowMode = false;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int totalW = getWidth();
        int totalH = getHeight();
        // rotate canvas first
        canvas.rotate(45, totalW / 2, totalH / 2);

        totalW /= 1.2;
        totalH /= 1.4;
        RectF leftRect = new RectF();
        if (mLeftLineGrowMode) {
            leftRect.left = 0;
            leftRect.right = leftRect.left + mLeftAddWidth;
            leftRect.top = (totalH + CONST_RIGHT_RECT_W) / 2;
            leftRect.bottom = leftRect.top + CONST_RECT_WEIGHT;
        } else {
            leftRect.right = (totalW + CONST_LEFT_RECT_W) / 2 + CONST_RECT_WEIGHT - 1;
            leftRect.left = leftRect.right - mLeftAddWidth;
            leftRect.top = (totalH + CONST_RIGHT_RECT_W) / 2;
            leftRect.bottom = leftRect.top + CONST_RECT_WEIGHT;
        }

        canvas.drawRoundRect(leftRect, CONST_RADIUS, CONST_RADIUS, mPaint);

        RectF rightRect = new RectF();
        rightRect.bottom = (totalH + CONST_RIGHT_RECT_W) / 2 + CONST_RECT_WEIGHT - 1;
        rightRect.left = (totalW + CONST_LEFT_RECT_W) / 2;
        rightRect.right = rightRect.left + CONST_RECT_WEIGHT;
        rightRect.top = rightRect.bottom - mRightAddWidth;
        canvas.drawRoundRect(rightRect, CONST_RADIUS, CONST_RADIUS, mPaint);
    }

    public float dip2px(float dpValue) {
        if(mDensity == -1) {
            mDensity = getResources().getDisplayMetrics().density;
        }
        return dpValue * mDensity + 0.5f;
    }

    public void startTickAnim () {
        // hide tick
        mLeftAddWidth = 0;
        mRightAddWidth = 0;
        invalidate();
        Animation tickAnim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);

                if (0.54 < interpolatedTime && 0.7 >= interpolatedTime) {
                    mLeftLineGrowMode = true;
                    mLeftAddWidth = ((getWidth() / 1.2f + CONST_LEFT_RECT_W) / 2 + CONST_RECT_WEIGHT - 1) * ((interpolatedTime - 0.54f) / 0.16f);
                    if (0.65 < interpolatedTime) {
                        mRightAddWidth = (CONST_RIGHT_RECT_W + 10) * ((interpolatedTime - 0.65f) / 0.19f);
                    }
                    invalidate();
                } else if (0.7 < interpolatedTime && 0.84 >= interpolatedTime) {
                    mLeftLineGrowMode = false;
                    mLeftAddWidth = ((getWidth() / 1.2f + CONST_LEFT_RECT_W) / 2 + CONST_RECT_WEIGHT - 1) * (1 - ((interpolatedTime - 0.7f) / 0.14f));
                    mLeftAddWidth = mLeftAddWidth < 5 ? 5 : mLeftAddWidth;
                    mRightAddWidth = (CONST_RIGHT_RECT_W + 10) * ((interpolatedTime - 0.65f) / 0.19f);
                    invalidate();
                } else if (0.84 < interpolatedTime && 1 >= interpolatedTime) {
                    mLeftLineGrowMode = false;
                    mLeftAddWidth = 5 + (CONST_LEFT_RECT_W - 5) * ((interpolatedTime - 0.84f) / 0.16f);
                    mRightAddWidth = CONST_RIGHT_RECT_W + 10 * (1 - ((interpolatedTime - 0.84f) / 0.16f));
                    invalidate();
                }
            }
        };
        tickAnim.setDuration(750);
        startAnimation(tickAnim);
    }
}