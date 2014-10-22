package cn.pedant.SweetAlert.ui;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.pedant.SweetAlert.R;

public class SweetAlertDialog extends Dialog implements View.OnClickListener {
    private View mDialogView;
    private AnimationSet mScaleInAnim;
    private AnimationSet mScaleOutAnim;
    private Animation mErrorInAnim;
    private Animation mErrorXInAnim;
    private AnimationSet mSuccessLayoutAnimSet;
    private Animation mSuccessBowAnim;
    private TextView mTitleTextView;
    private TextView mContentTextView;
    private String mTitleText;
    private String mContentText;
    private int mAlertType;
    private FrameLayout mErrorFrame;
    private SuccessTickView mSuccessTick;
    private ImageView mErrorX;
    private View mSuccessRightMask;

    public static final int NORMAL_TYPE = 0;
    public static final int ERROR_TYPE = 1;
    public static final int SUCCESS_TYPE = 2;
    public static final int WARNING_TYPE = 3;

    public SweetAlertDialog(Context context) {
        this(context, NORMAL_TYPE);
    }

    public SweetAlertDialog(Context context, int alertType) {
        super(context, R.style.alert_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        mAlertType = alertType;
        mScaleInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.dialog_scale_in);
        mScaleOutAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.dialog_scale_out);
        mScaleOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.setVisibility(View.GONE);
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        SweetAlertDialog.super.dismiss();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        if (mAlertType == ERROR_TYPE) {
            mErrorInAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.error_frame_in);
            mErrorXInAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.error_x_in);
        } else if (mAlertType == SUCCESS_TYPE) {
            mSuccessBowAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.success_bow_roate);
            mSuccessLayoutAnimSet = (AnimationSet)OptAnimationLoader.loadAnimation(getContext(), R.anim.success_mask_layout);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog);
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mTitleTextView = (TextView)findViewById(R.id.title_text);
        mContentTextView = (TextView)findViewById(R.id.content_text);
        mErrorFrame = (FrameLayout)findViewById(R.id.error_frame);
        mErrorX = (ImageView)mErrorFrame.findViewById(R.id.error_x);
        FrameLayout successFrame = (FrameLayout)findViewById(R.id.success_frame);
        mSuccessTick = (SuccessTickView)successFrame.findViewById(R.id.success_tick);
        mSuccessRightMask = successFrame.findViewById(R.id.mask_right);

        View okButton = findViewById(R.id.ok_button);
        okButton.setOnClickListener(this);

        setTitleText(mTitleText);
        setContentText(mContentText);

        switch (mAlertType) {
            case ERROR_TYPE:
                mErrorFrame.setVisibility(View.VISIBLE);
                break;
            case SUCCESS_TYPE:
                successFrame.setVisibility(View.VISIBLE);
                // initial rotate layout of success mask
                findViewById(R.id.mask_left).startAnimation(mSuccessLayoutAnimSet.getAnimations().get(0));
                mSuccessRightMask.startAnimation(mSuccessLayoutAnimSet.getAnimations().get(1));
                break;
            case WARNING_TYPE:
                okButton.setVisibility(View.GONE);
                findViewById(R.id.warning_frame).setVisibility(View.VISIBLE);
                findViewById(R.id.warning_buttons).setVisibility(View.VISIBLE);
                findViewById(R.id.cancel_button).setOnClickListener(this);
                findViewById(R.id.delete_button).setOnClickListener(this);
                break;
        }
    }

    public void setTitleText (String text) {
        mTitleText = text;
        if (mTitleTextView != null && mTitleText != null) {
            mTitleTextView.setText(mTitleText);
        }
    }

    public void setContentText (String text) {
        mContentText = text;
        if (mContentTextView != null && mContentText != null) {
            mContentTextView.setVisibility(View.VISIBLE);
            mContentTextView.setText(mContentText);
        }
    }

    protected void onStart() {
        mDialogView.startAnimation(mScaleInAnim);
        if (mAlertType == ERROR_TYPE) {
            mErrorFrame.startAnimation(mErrorInAnim);
            mErrorX.startAnimation(mErrorXInAnim);
        } else if (mAlertType == SUCCESS_TYPE) {
            mSuccessTick.startTickAnim();
            mSuccessRightMask.startAnimation(mSuccessBowAnim);
        }
    }

    public void dismiss() {
        mDialogView.startAnimation(mScaleOutAnim);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok_button:
                dismiss();
                break;
            case R.id.cancel_button:
                dismiss();
                break;
        }
    }
}
