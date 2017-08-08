package com.sunfusheng.marqueeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunfusheng on 16/5/31.
 */
public class MarqueeView extends ViewFlipper {

    private List<? extends CharSequence> notices = new ArrayList<>();
    private boolean isSetAnimDuration = false;
    private OnItemClickListener onItemClickListener;

    private int interval = 2000;
    private int animDuration = 500;
    private int textSize = 14;
    private int textColor = 0xffffffff;

    private boolean singleLine = false;
    private int gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
    private static final int GRAVITY_LEFT = 0;
    private static final int GRAVITY_CENTER = 1;
    private static final int GRAVITY_RIGHT = 2;

    private int position;

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MarqueeViewStyle, defStyleAttr, 0);
        interval = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvInterval, interval);
        isSetAnimDuration = typedArray.hasValue(R.styleable.MarqueeViewStyle_mvAnimDuration);
        animDuration = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvAnimDuration, animDuration);
        singleLine = typedArray.getBoolean(R.styleable.MarqueeViewStyle_mvSingleLine, false);
        if (typedArray.hasValue(R.styleable.MarqueeViewStyle_mvTextSize)) {
            textSize = (int) typedArray.getDimension(R.styleable.MarqueeViewStyle_mvTextSize, textSize);
            textSize = Utils.px2sp(context, textSize);
        }
        textColor = typedArray.getColor(R.styleable.MarqueeViewStyle_mvTextColor, textColor);
        int gravityType = typedArray.getInt(R.styleable.MarqueeViewStyle_mvGravity, GRAVITY_LEFT);
        switch (gravityType) {
            case GRAVITY_CENTER:
                gravity = Gravity.CENTER;
                break;
            case GRAVITY_RIGHT:
                gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
                break;
        }
        typedArray.recycle();
        setFlipInterval(interval);
    }

    /**
     * 根据字符串，启动翻页公告
     *
     * @param notice 字符串
     */
    public void startWithText(String notice) {
        startWithText(notice, R.anim.anim_bottom_in, R.anim.anim_top_out);
    }

    /**
     * 根据字符串，启动翻页公告
     *
     * @param notice       字符串
     * @param inAnimResId  进入动画的resID
     * @param outAnimResID 离开动画的resID
     */
    @SuppressWarnings("deprecation")
    public void startWithText(final String notice, final @AnimRes int inAnimResId, final @AnimRes int outAnimResID) {
        if (TextUtils.isEmpty(notice)) return;
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                startWithFixedWidth(notice, inAnimResId, outAnimResID);
            }
        });
    }

    /**
     * 根据字符串和宽度，启动翻页公告
     *
     * @param notice 字符串
     */
    private void startWithFixedWidth(String notice, @AnimRes int inAnimResId, @AnimRes int outAnimResID) {
        int noticeLength = notice.length();
        int width = Utils.px2dip(getContext(), getWidth());
        if (width == 0) {
            throw new RuntimeException("Please set the width of MarqueeView !");
        }
        int limit = width / textSize;
        List list = new ArrayList();

        if (noticeLength <= limit) {
            list.add(notice);
        } else {
            int size = noticeLength / limit + (noticeLength % limit != 0 ? 1 : 0);
            for (int i = 0; i < size; i++) {
                int startIndex = i * limit;
                int endIndex = ((i + 1) * limit >= noticeLength ? noticeLength : (i + 1) * limit);
                list.add(notice.substring(startIndex, endIndex));
            }
        }

        if (notices == null) notices = new ArrayList<>();
        notices.clear();
        notices.addAll(list);
        start(inAnimResId, outAnimResID);
    }

    /**
     * 根据字符串列表，启动翻页公告
     *
     * @param notices 字符串列表
     */
    public void startWithList(List<? extends CharSequence> notices) {
        startWithList(notices, R.anim.anim_bottom_in, R.anim.anim_top_out);
    }

    /**
     * 根据字符串列表，启动翻页公告
     *
     * @param notices      字符串列表
     * @param inAnimResId  进入动画的resID
     * @param outAnimResID 离开动画的resID
     */
    public void startWithList(List<? extends CharSequence> notices, @AnimRes int inAnimResId, @AnimRes int outAnimResID) {
        if (Utils.isEmpty(notices)) return;
        setNotices(notices);
        start(inAnimResId, outAnimResID);
    }

    private boolean start(@AnimRes int inAnimResId, @AnimRes int outAnimResID) {
        removeAllViews();
        clearAnimation();

        position = 0;
        addView(createTextView(notices.get(position)));

        if (notices.size() > 1) {
            setInAndOutAnimation(inAnimResId, outAnimResID);
            startFlipping();
        }

        getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                position++;
                if (position >= notices.size()) {
                    position = 0;
                }
                View view = createTextView(notices.get(position));
                if (view.getParent() == null) {
                    addView(view);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        return true;
    }

    private TextView createTextView(CharSequence text) {
        TextView textView = (TextView) getChildAt(getDisplayedChild() + 1);
        if (textView == null) {
            textView = new TextView(getContext());
            textView.setGravity(gravity);
            textView.setTextColor(textColor);
            textView.setTextSize(textSize);
            textView.setSingleLine(singleLine);
        }
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(getPosition(), (TextView) v);
                }
            }
        });
        textView.setText(text);
        textView.setTag(position);
        return textView;
    }

    public int getPosition() {
        return (int) getCurrentView().getTag();
    }

    public List<? extends CharSequence> getNotices() {
        return notices;
    }

    public void setNotices(List<? extends CharSequence> notices) {
        this.notices = notices;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, TextView textView);
    }

    /**
     * 设置进入动画和离开动画
     *
     * @param inAnimResId  进入动画的resID
     * @param outAnimResID 离开动画的resID
     */
    private void setInAndOutAnimation(@AnimRes int inAnimResId, @AnimRes int outAnimResID) {
        Animation inAnim = AnimationUtils.loadAnimation(getContext(), inAnimResId);
        if (isSetAnimDuration) inAnim.setDuration(animDuration);
        setInAnimation(inAnim);

        Animation outAnim = AnimationUtils.loadAnimation(getContext(), outAnimResID);
        if (isSetAnimDuration) outAnim.setDuration(animDuration);
        setOutAnimation(outAnim);
    }

}
