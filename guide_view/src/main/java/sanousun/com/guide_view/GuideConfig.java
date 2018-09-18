package sanousun.com.guide_view;

import android.support.annotation.AnimatorRes;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.Px;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * @author dashu
 * 2017/12/17
 * 引导视图的配置项
 */

public class GuideConfig {

    public static final int SHAPE_RECTANGLE = 0, SHAPE_OVAL = 1;
    public static final int ANCHOR_LEFT = 0, ANCHOR_TOP = 1, ANCHOR_RIGHT = 2, ANCHOR_BOTTOM = 3, ANCHOR_CENTER = 4;

    @IntDef({ANCHOR_LEFT, ANCHOR_TOP,
            ANCHOR_RIGHT, ANCHOR_BOTTOM, ANCHOR_CENTER})
    @Retention(RetentionPolicy.SOURCE)
    @interface AnchorType {
    }

    @IntDef({SHAPE_RECTANGLE, SHAPE_OVAL})
    @Retention(RetentionPolicy.SOURCE)
    @interface ShapeType {
    }

    /**
     * 目标高亮view
     */
    public List<View> mTargetViewList;
    /**
     * 目标高亮形状
     */
    @ShapeType
    public int mTargetShape;
    /**
     * 目标高亮形状圆角
     * 仅在矩形条件下有用
     */
    @Px
    public int mTargetCorner;
    /**
     * 目标高亮形状宽高比例
     * 仅在圆形条件下有用
     */
    public float mTargetRadio = 1f;
    /* **目标离遮罩层的边距，优先级递增** */
    /**
     * 目标离遮罩层的边距
     */
    @Px
    public int mTargetPadding;
    /**
     * 目标离遮罩层的水平边距
     */
    @Px
    public int mTargetPaddingHorizontal;
    /**
     * 目标离遮罩层的垂直边距
     */
    @Px
    public int mTargetPaddingVertical;
    /**
     * 目标离遮罩层的左边距
     */
    @Px
    public int mTargetPaddingLeft;
    /**
     * 目标离遮罩层的上边距
     */
    @Px
    public int mTargetPaddingTop;
    /**
     * 目标离遮罩层的右边距
     */
    @Px
    public int mTargetPaddingRight;
    /**
     * 目标离遮罩层的下边距
     */
    @Px
    public int mTargetPaddingBottom;

    public int getTargetPaddingLeft() {
        if (mTargetPaddingLeft != 0) {
            return mTargetPaddingLeft;
        }
        if (mTargetPaddingHorizontal != 0) {
            return mTargetPaddingHorizontal;
        }
        return mTargetPadding;
    }

    public int getTargetPaddingRight() {
        if (mTargetPaddingRight != 0) {
            return mTargetPaddingRight;
        }
        if (mTargetPaddingHorizontal != 0) {
            return mTargetPaddingHorizontal;
        }
        return mTargetPadding;
    }

    public int getTargetPaddingTop() {
        if (mTargetPaddingTop != 0) {
            return mTargetPaddingTop;
        }
        if (mTargetPaddingVertical != 0) {
            return mTargetPaddingVertical;
        }
        return mTargetPadding;
    }

    public int getTargetPaddingBottom() {
        if (mTargetPaddingBottom != 0) {
            return mTargetPaddingBottom;
        }
        if (mTargetPaddingVertical != 0) {
            return mTargetPaddingVertical;
        }
        return mTargetPadding;
    }

    /**
     * 遮罩范围
     * 如果为空默认全屏
     */
    public View mShadowView;
    /**
     * 遮罩颜色
     */
    @ColorInt
    public int mShadowColor;
    /**
     * 引导视图
     * 优先级大于布局文件
     */
    public View mGuideView;
    /**
     * 锚点方式
     */
    @AnchorType
    public int mGuideAnchorType;
    /**
     * x轴偏移量
     */
    @Px
    public int mGuideOffsetX;
    /**
     * y轴偏移量
     */
    @Px
    public int mGuideOffsetY;
    /**
     * 展示动画
     */
    @AnimatorRes
    public int mAnimatorShow = -1;
    /**
     * 隐藏动画
     */
    @AnimatorRes
    public int mAnimatorDismiss = -1;
    /**
     * 蒙层消失的监听事件
     */
    public List<GuideView.OnDismissListener> mOnDismissListeners;
    /**
     * 目标view超出屏幕监听
     */
    public GuideView.OnOutOfRangeListener mOnOutOfRangeListener;
}
