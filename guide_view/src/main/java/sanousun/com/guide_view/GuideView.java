package sanousun.com.guide_view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.ArrayList;

/**
 * @author dashu
 * @date 2017/12/17
 * 引导视图
 */

public class GuideView extends ViewGroup {

    private GuideConfig mGuideConfig;
    private Rect mTargetRect;
    private RectF mTargetShowRectF;

    private OnDismissListener mSystemDismissListener;

    private Paint mEraser;
    private Bitmap mEraserBitmap;
    private Canvas mEraserCanvas;

    private boolean mIsAnimatorDoing = false;
    private boolean mIsTargetDecorView = false;

    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener
            = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            fixLayout();
            getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
        }
    };

    void setGuideConfig(GuideConfig guideConfig) {
        mGuideConfig = guideConfig;
    }

    /**
     * 提供给{@link Guide#showGuide()}方法的 dismiss 方法
     *
     * @param systemDismissListener 移除监听
     */
    void setSystemDismissListener(OnDismissListener systemDismissListener) {
        mSystemDismissListener = systemDismissListener;
    }

    GuideView(Context context) {
        this(context, null);
    }

    GuideView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    GuideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    GuideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        Point size = new Point();
        size.x = getResources().getDisplayMetrics().widthPixels;
        size.y = getResources().getDisplayMetrics().heightPixels;
        mEraserBitmap = Bitmap.createBitmap(size.x, size.y, Bitmap.Config.ARGB_8888);
        mEraserCanvas = new Canvas(mEraserBitmap);
        mEraser = new Paint(Paint.ANTI_ALIAS_FLAG);
        mEraser.setColor(0xFFFFFFFF);
        mEraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        mTargetRect = new Rect();
        mTargetShowRectF = new RectF();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            clearFocus();
            mEraserCanvas.setBitmap(null);
            mEraserBitmap = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int w = MeasureSpec.getSize(widthMeasureSpec);
        final int h = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(w, h);
        final int count = getChildCount();
        View child;
        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child != null) {
                measureChild(child,
                        MeasureSpec.makeMeasureSpec(w, MeasureSpec.AT_MOST),
                        MeasureSpec.makeMeasureSpec(h, MeasureSpec.AT_MOST));
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int left, top, right, bottom;
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            if (!mIsTargetDecorView) {
                switch (mGuideConfig.mGuideAnchorType) {
                    case GuideConfig.ANCHOR_CENTER:
                        left = mTargetRect.centerX() - width / 2;
                        top = mTargetRect.centerY() - height / 2;
                        break;
                    case GuideConfig.ANCHOR_LEFT:
                        left = mTargetRect.left - width;
                        top = mTargetRect.centerY() - height / 2;
                        break;
                    case GuideConfig.ANCHOR_TOP:
                        left = mTargetRect.centerX() - width / 2;
                        top = mTargetRect.top - height;
                        break;
                    case GuideConfig.ANCHOR_RIGHT:
                        left = mTargetRect.right;
                        top = mTargetRect.centerY() - height / 2;
                        break;
                    default:
                    case GuideConfig.ANCHOR_BOTTOM:
                        left = mTargetRect.centerX() - width / 2;
                        top = mTargetRect.bottom;
                }
            } else {
                switch (mGuideConfig.mGuideAnchorType) {
                    case GuideConfig.ANCHOR_CENTER:
                        left = mTargetRect.centerX() - width / 2;
                        top = mTargetRect.centerY() - height / 2;
                        break;
                    case GuideConfig.ANCHOR_LEFT:
                        left = mTargetRect.left;
                        top = mTargetRect.centerY() - height / 2;
                        break;
                    case GuideConfig.ANCHOR_TOP:
                        left = mTargetRect.centerX() - width / 2;
                        top = mTargetRect.top;
                        break;
                    case GuideConfig.ANCHOR_RIGHT:
                        left = mTargetRect.right - width;
                        top = mTargetRect.centerY() - height / 2;
                        break;
                    default:
                    case GuideConfig.ANCHOR_BOTTOM:
                        left = mTargetRect.centerX() - width / 2;
                        top = mTargetRect.bottom - height;
                }
            }
            right = left + width;
            bottom = top + height;
            child.layout(
                    left + mGuideConfig.mGuideOffsetX,
                    top + mGuideConfig.mGuideOffsetY,
                    right + mGuideConfig.mGuideOffsetX,
                    bottom + mGuideConfig.mGuideOffsetY);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        mEraserBitmap.eraseColor(Color.TRANSPARENT);
        mEraserCanvas.drawColor(mGuideConfig.mShadowColor);
        if (!mIsTargetDecorView) {
            mTargetShowRectF.set(mTargetRect);
            switch (mGuideConfig.mTargetShape) {
                case GuideConfig.SHAPE_OVAL:
                    mEraserCanvas.drawOval(mTargetShowRectF, mEraser);
                    break;
                case GuideConfig.SHAPE_RECTANGLE:
                default:
                    mEraserCanvas.drawRoundRect(mTargetShowRectF,
                            mGuideConfig.mTargetCorner, mGuideConfig.mTargetCorner, mEraser);
                    break;
            }
        }
        canvas.drawBitmap(mEraserBitmap, 0, 0, null);
        super.dispatchDraw(canvas);
    }

    /**
     * 展示引导视图
     */
    void show() {
        Activity activity = (Activity) getContext();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        if (mGuideConfig.mTargetViewList == null) {
            mGuideConfig.mTargetViewList = new ArrayList<>();
        }
        if (mGuideConfig.mTargetViewList.size() == 0) {
            mGuideConfig.mTargetViewList.add(activity.getWindow().getDecorView());
            mIsTargetDecorView = true;
        }
        View target = mGuideConfig.mTargetViewList.get(0);
        if (target.getWidth() == 0 && target.getHeight() == 0) {
            getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
        } else {
            fixLayout();
        }
        ViewGroup parent = (ViewGroup) activity.getWindow().getDecorView();
        parent.addView(GuideView.this);
        if (mGuideConfig.mAnimatorShow > 0) {
            mIsAnimatorDoing = true;
            Animator animator = AnimatorInflater.loadAnimator(getContext(), mGuideConfig.mAnimatorShow);
            animator.setTarget(GuideView.this);
            animator.start();
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mIsAnimatorDoing = false;
                }
            });
        }
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 对外暴露的引导层消失操作
     * 如果手动调用请确保当前蒙层是最前面的蒙层
     */
    public void dismiss() {
        if (mIsAnimatorDoing) {
            return;
        }
        if (mGuideConfig.mAnimatorDismiss > 0) {
            mIsAnimatorDoing = true;
            Animator animator = AnimatorInflater.loadAnimator(getContext(), mGuideConfig.mAnimatorDismiss);
            animator.setTarget(GuideView.this);
            animator.start();
            animator.addListener(new AnimatorListenerAdapter() {
                                     @Override
                                     public void onAnimationEnd(Animator animation) {
                                         super.onAnimationEnd(animation);
                                         mIsAnimatorDoing = false;
                                         remove();
                                     }
                                 }
            );
        } else {
            remove();
        }
    }

    /**
     * 引导层移除操作
     */
    private void remove() {
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(GuideView.this);
        }
        if (mSystemDismissListener != null) {
            mSystemDismissListener.onDismiss();
        }
        if (mGuideConfig.mOnDismissListeners != null) {
            for (OnDismissListener onDismissListener : mGuideConfig.mOnDismissListeners) {
                if (onDismissListener != null) {
                    onDismissListener.onDismiss();
                }
            }
        }
    }

    public void fixLayout() {
        // 将该布局提到最前面
        bringToFront();
        Rect border = calculateTargetBorder();
        if (mGuideConfig.mTargetShape == GuideConfig.SHAPE_OVAL) {
            int centerX = (border.right + border.left) / 2;
            int centerY = (border.top + border.bottom) / 2;
            int radioX, radioY;
            if ((border.right - border.left) > (border.bottom - border.top)) {
                radioX = (border.right - border.left) / 2;
                radioY = (int) (radioX * mGuideConfig.mTargetRadio);
            } else {
                radioY = (border.bottom - border.top) / 2;
                radioX = (int) (radioY * mGuideConfig.mTargetRadio);
            }
            mTargetRect.set(
                    centerX - radioX, centerY - radioY,
                    centerX + radioX, centerY + radioY);
        } else {
            mTargetRect.set(border);
        }

        Log.i("target", "left: " + mTargetRect.left +
                ", top: " + mTargetRect.top +
                ", right: " + mTargetRect.right +
                ", bottom: " + mTargetRect.bottom);

        // 部分可见或者不可见的处理
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        Rect deviceRect = new Rect(0, 0, metrics.widthPixels, metrics.heightPixels);

        int oLeft = mTargetRect.left - deviceRect.left;
        int oTop = mTargetRect.top - deviceRect.top;
        int oRight = mTargetRect.right - deviceRect.right;
        int oBottom = mTargetRect.bottom - deviceRect.bottom;
        // 正常应该是一正一负，考虑到目标是大于窗口的请况可能会-负-零或者一零一零
        // 不正常的情况则是两正或者两负
        if (oLeft * oRight <= 0 && oTop * oBottom <= 0
                || mGuideConfig.mOnOutOfRangeListener == null) {
            requestLayout();
        } else {
            int offX, offY;
            offX = Math.min(Math.abs(oLeft), Math.abs(oRight));
            if (oLeft < 0) {
                offX = -offX;
            }
            offY = Math.min(Math.abs(oTop), Math.abs(oBottom));
            if (oTop < 0) {
                offY = -offY;
            }
            mGuideConfig.mOnOutOfRangeListener.onOutOfRange(offX, offY);
            fixLayout();
        }
    }

    /**
     * 计算目标view的范围
     */
    private Rect calculateTargetBorder() {
        int[] location = new int[2];
        int left = Integer.MAX_VALUE, top = Integer.MAX_VALUE,
                right = Integer.MIN_VALUE, bottom = Integer.MIN_VALUE;
        for (View view : mGuideConfig.mTargetViewList) {
            view.getLocationInWindow(location);
            int targetWidth = view.getWidth();
            int targetHeight = view.getHeight();
            left = Math.min(left, location[0] - mGuideConfig.getTargetPaddingLeft());
            top = Math.min(top, location[1] - mGuideConfig.getTargetPaddingTop());
            right = Math.max(right, location[0] + targetWidth + mGuideConfig.getTargetPaddingRight());
            bottom = Math.max(bottom, location[1] + targetHeight + mGuideConfig.getTargetPaddingBottom());
        }
        return new Rect(left, top, right, bottom);
    }

    public interface OnDismissListener {
        /**
         * 蒙层消失时的回调
         */
        void onDismiss();
    }

    public interface OnOutOfRangeListener {
        /**
         * 目标超出可视界面
         */
        void onOutOfRange(int offsetX, int offsetY);
    }
}