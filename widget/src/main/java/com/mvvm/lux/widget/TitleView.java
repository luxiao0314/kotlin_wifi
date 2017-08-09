package com.mvvm.lux.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @Description titleView
 * @Author lucio
 * @Email xiao.lu@magicwindow.cn
 * @Date 09/08/2017 2:40 PM
 * @Version 1.0.0
 */
public class TitleView extends FrameLayout implements View.OnClickListener {

    ImageView cusTitleViewBackImg;
    TextView cusTitleViewLabel;
    ImageView cusTitleViewRightImg;
    TextView cusTitleViewNumTip;
    RelativeLayout cusTitleViewRightRl;
    TextView cusTitleViewRightTv;

    private boolean backImgShow;
    private int backImgSrc;
    private String centerText;
    private String rightLableText;
    private boolean rightLabelShow;
    private boolean rightRlShow;
    private boolean rightImgShow;
    private int rightImgSrc;
    private int rightLableImgSrc;
    private boolean rightNumShow;
    private String rightNumText;
    /* ID */
    private static final int ID_LEFT_BUTTON = 0x100001;
    private static final int ID_RIGHT_BUTTON = 0x100002;
    private static final int ID_RIGHT_IMAGE = 0x100003;
    private OnTitleClickListener mOnTitleClickListener;

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initFromAttributes(context, attrs);

        viewInit(context);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initFromAttributes(context, attrs);
        viewInit(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case ID_LEFT_BUTTON:
                if (mOnTitleClickListener != null) {
                    mOnTitleClickListener.onLeftClick(v);
                    return;
                }
                sendKeyBackEvent();
                break;
            case ID_RIGHT_BUTTON:
                if (mOnTitleClickListener != null) {
                    mOnTitleClickListener.onRightClick(v);
                }
                break;
            case ID_RIGHT_IMAGE:
                if (mOnTitleClickListener != null) {
                    mOnTitleClickListener.onRightClick(v);
                }
            default:
                // do nothing
                break;
        }
    }

    private void sendKeyBackEvent() {
        final Context context = getContext();
        if (context instanceof Activity) {
            KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK);
            ((Activity) context).onKeyDown(KeyEvent.KEYCODE_BACK, keyEvent);
        }
    }


    public interface OnTitleClickListener {
        void onLeftClick(View v);

        void onRightClick(View v);
    }

    /**
     * 设置控件点击响应的通知listener
     *
     * @param listener 响应事件对象
     */
    public void setOnTitleClickListener(OnTitleClickListener listener) {
        mOnTitleClickListener = listener;
    }

    private void initFromAttributes(Context context, AttributeSet attrs) {
        if (null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);

            backImgShow = typedArray.getBoolean(R.styleable.TitleView_title_back_isShow, true);
            backImgSrc = typedArray.getResourceId(R.styleable.TitleView_title_back_src, R.drawable.ic_back);
            centerText = typedArray.getString(R.styleable.TitleView_title_center_text);
            rightLableText = typedArray.getString(R.styleable.TitleView_title_right_label);
            rightLabelShow = typedArray.getBoolean(R.styleable.TitleView_title_right_label_isShow, false);
            rightRlShow = typedArray.getBoolean(R.styleable.TitleView_title_right_rl_isShow, false);
            rightImgSrc = typedArray.getResourceId(R.styleable.TitleView_title_right_img_src, 0);
            rightLableImgSrc = typedArray.getResourceId(R.styleable.TitleView_title_right_label_img_src, 0);
            rightImgShow = typedArray.getBoolean(R.styleable.TitleView_title_right_img_isShow, false);
            rightNumShow = typedArray.getBoolean(R.styleable.TitleView_title_right_num_isShow, false);
            rightNumText = typedArray.getString(R.styleable.TitleView_title_right_num_text);

            typedArray.recycle();
        } else {
            centerText = null;
            rightLableText = null;
            rightNumText = null;
        }
    }

    private void viewInit(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.cus_title_view, this, true);
        cusTitleViewBackImg = (ImageView) view.findViewById(R.id.cus_title_view_back_img);
        cusTitleViewRightImg = (ImageView) view.findViewById(R.id.cus_title_view_right_img);
        cusTitleViewLabel = (TextView) view.findViewById(R.id.cus_title_view_label);
        cusTitleViewNumTip = (TextView) view.findViewById(R.id.cus_title_view_num_tip);
        cusTitleViewRightTv = (TextView) view.findViewById(R.id.cus_title_view_right_tv);
        cusTitleViewRightRl = (RelativeLayout) view.findViewById(R.id.cus_title_view_right_rl);

        if (null != cusTitleViewBackImg) {
            cusTitleViewBackImg.setVisibility(backImgShow ? VISIBLE : INVISIBLE);
            if (backImgShow) {
                cusTitleViewBackImg.setImageResource(backImgSrc);
                cusTitleViewBackImg.setId(ID_LEFT_BUTTON);
                cusTitleViewBackImg.setClickable(true);
                cusTitleViewBackImg.setOnClickListener(this);
            }
        }

        setTitleText(centerText);
        setLeftLabelShow(backImgShow);
        setRightLableText(rightLableText);
        setRightLabelShow(rightLabelShow);
        setRightLableSrc(rightLableImgSrc);
        setRightRlShow(rightRlShow);
        setRightImgShow(rightImgShow);
        setRightImgSrc(rightImgSrc);
        setRightNumShow(rightNumShow);
        setRightNumText(rightNumText);
    }

    /**
     * 设置标题Title
     *
     * @param strTitle
     */
    public void setTitleText(String strTitle) {
        if (null != cusTitleViewLabel) {
            cusTitleViewLabel.setText(strTitle);
        }
    }

    /**
     * 设置座边标签的隐现
     *
     * @param isShow
     */
    public void setLeftLabelShow(boolean isShow) {
        if (null != cusTitleViewBackImg) {
            cusTitleViewBackImg.setVisibility(isShow ? VISIBLE : INVISIBLE);
        }
    }

    /**
     * 设置右侧标签的显示
     *
     * @param text
     */
    public void setRightLableText(String text) {
        if (null != cusTitleViewRightTv) {
            cusTitleViewRightTv.setText(text);
            cusTitleViewRightTv.setId(ID_RIGHT_BUTTON);
            cusTitleViewRightTv.setClickable(true);
            cusTitleViewRightTv.setOnClickListener(this);
        }
    }

    /**
     * 设置右侧标签图标
     *
     * @param resourceId
     */
    public void setRightLableSrc(int resourceId) {
        if (null != cusTitleViewRightTv && rightLabelShow) {
            cusTitleViewRightTv.setBackgroundResource(resourceId);
        }
    }

    /**
     * 设置右边标签的隐现
     *
     * @param isShow
     */
    public void setRightLabelShow(boolean isShow) {
        if (null != cusTitleViewRightTv) {
            cusTitleViewRightTv.setVisibility(isShow ? VISIBLE : GONE);
        }
    }

    /**
     * 设置右侧消息提示的布局隐现
     *
     * @param isShow
     */
    public void setRightRlShow(boolean isShow) {
        if (null != cusTitleViewRightRl) {
            cusTitleViewRightRl.setVisibility(isShow ? VISIBLE : GONE);
        }
    }

    /**
     * 设置右侧图标的隐现性
     *
     * @param isShow
     */
    public void setRightImgShow(boolean isShow) {
        if (null != cusTitleViewRightImg) {
            cusTitleViewRightImg.setVisibility(isShow ? VISIBLE : GONE);
        }

    }

    /**
     * 设置右侧图标
     *
     * @param resourceId
     */
    public void setRightImgSrc(int resourceId) {
        if (null != cusTitleViewRightImg && rightImgShow) {
            cusTitleViewRightImg.setImageResource(resourceId);
            cusTitleViewRightImg.setId(ID_RIGHT_IMAGE);
            cusTitleViewRightImg.setClickable(true);
            cusTitleViewRightImg.setOnClickListener(this);
        }
    }

    /**
     * 设置右侧数字提示的
     *
     * @param isShow
     */
    public void setRightNumShow(boolean isShow) {
        if (null != cusTitleViewNumTip) {
            cusTitleViewNumTip.setVisibility(isShow ? VISIBLE : GONE);
        }
    }

    /**
     * 设置右侧数字提示文本
     *
     * @param text
     */
    public void setRightNumText(String text) {
        if (null != cusTitleViewNumTip && rightNumShow) {
            cusTitleViewNumTip.setText(text);
        }
    }

    /**
     * 获取标题Title
     */
    public String getTitleText() {

        return cusTitleViewLabel.getText().toString();
    }
}
