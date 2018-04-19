package com.hb.zll.dijiag.view.refresh.header;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.hb.zll.dijiag.R;
import com.hb.zll.dijiag.view.refresh.GoogleCircleProgressView;

/**
 * Created by superMoon on 2017/7/13.
 */
public class GoogleCircleHookRefreshHeaderView extends FrameLayout implements SwipeTrigger, SwipeRefreshTrigger {
    private GoogleCircleProgressView progressView;

    private int mTriggerOffset;

    private int mFinalOffset;

    public GoogleCircleHookRefreshHeaderView(Context context) {
        this(context, null);
    }

    public GoogleCircleHookRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoogleCircleHookRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTriggerOffset = context.getResources().getDimensionPixelOffset(R.dimen.dp_100);
        mFinalOffset = context.getResources().getDimensionPixelOffset(R.dimen.dp_150);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        progressView = (GoogleCircleProgressView) findViewById(R.id.googleProgress);
        progressView.setColorSchemeResources(
                R.color.google_blue,
                R.color.google_red,
                R.color.google_yellow,
                R.color.google_green);
        progressView.setStartEndTrim(0, (float) 0.75);
    }

    @Override
    public void onRefresh() {
        progressView.start();
    }

    @Override
    public void onPrepare() {
        progressView.setStartEndTrim(0, (float) 0.75);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        float alpha = y / (float) mTriggerOffset;
        ViewCompat.setAlpha(progressView, alpha);
        if (!isComplete) {
            progressView.setProgressRotation(y / (float) mFinalOffset);
        }
    }

    @Override
    public void onRelease() {

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public void onComplete() {
        progressView.animate().scaleX(0).scaleY(0).setDuration(300);
    }

    @Override
    public void onReset() {
        progressView.stop();
        ViewCompat.setAlpha(progressView, 1f);
        ViewCompat.setScaleX(progressView, 1f);
        ViewCompat.setScaleY(progressView, 1f);
    }

}
