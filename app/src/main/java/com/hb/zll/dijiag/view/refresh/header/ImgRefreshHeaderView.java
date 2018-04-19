package com.hb.zll.dijiag.view.refresh.header;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.hb.zll.dijiag.R;

/**
 * Created by superMoon on 2017/7/13.
 */
public class ImgRefreshHeaderView extends RelativeLayout implements SwipeTrigger, SwipeRefreshTrigger {

    private ImageView ivSpeed;

    private ImageView ivRefresh;

    private AnimationDrawable mAnimDrawable;

    private Animation mTwinkleAnim;

    private int mTriggerOffset;


    public ImgRefreshHeaderView(Context context) {
        super(context);
    }

    public ImgRefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImgRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTriggerOffset = context.getResources().getDimensionPixelOffset(R.dimen.dp_100);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivRefresh = (ImageView) findViewById(R.id.ivRefresh);
        ivSpeed = (ImageView) findViewById(R.id.ivSpeed);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        mTwinkleAnim = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_img);
    }

    @Override
    public void onRefresh() {
        ivSpeed.setVisibility(VISIBLE);
        ivSpeed.startAnimation(mTwinkleAnim);
        if (!mAnimDrawable.isRunning()){
            mAnimDrawable.start();
        }
    }

    @Override
    public void onPrepare() {
        ivSpeed.clearAnimation();
        ivSpeed.setVisibility(GONE);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
    }

    @Override
    public void onRelease() {
        if (!mAnimDrawable.isRunning()){
            mAnimDrawable.start();
        }
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onReset() {
        mAnimDrawable.stop();
        ivSpeed.clearAnimation();
        ivSpeed.setVisibility(GONE);
    }
}
