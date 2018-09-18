package sanousun.com.guide_view;

import android.app.Activity;
import android.support.annotation.AnimatorRes;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

/**
 * @author dashu
 * 2017/12/17
 * 引导视图的构造器
 */

@SuppressWarnings("unused")
public class GuideBuilder {

    /**
     * 引导配置项
     */
    private GuideConfig mGuideConfig;
    private Guide mGuide;

    public GuideBuilder(Guide guide) {
        mGuide = guide;
        mGuideConfig = new GuideConfig();
    }

    /**
     * 设置引导目标view
     *
     * @param targetView 引导目标view
     * @return this
     */
    public GuideBuilder setTargetView(View targetView) {
        if (mGuideConfig.mTargetViewList == null) {
            mGuideConfig.mTargetViewList = new ArrayList<>();
        }
        mGuideConfig.mTargetViewList.clear();
        mGuideConfig.mTargetViewList.add(targetView);
        return this;
    }

    /**
     * 添加引导目标view，项目会把多个目标view高亮区域整合成一块
     *
     * @param targetView 引导目标view
     * @return this
     */
    public GuideBuilder addTargetView(View targetView) {
        if (mGuideConfig.mTargetViewList == null) {
            mGuideConfig.mTargetViewList = new ArrayList<>();
        }
        mGuideConfig.mTargetViewList.add(targetView);
        return this;
    }

    /**
     * 设置引导目标展示形状
     *
     * @param targetShape 引导目标的展示形状
     * @return this
     */
    public GuideBuilder setTargetShape(@GuideConfig.ShapeType int targetShape) {
        mGuideConfig.mTargetShape = targetShape;
        return this;
    }

    /**
     * 设置引导目标展示形状的圆角
     *
     * @param targetCorner 引导目标的展示形状圆角大小
     * @return this
     */
    public GuideBuilder setTargetCorner(@Px int targetCorner) {
        mGuideConfig.mTargetCorner = targetCorner;
        return this;
    }

    /**
     * 设置引导目标展示形状的宽高比例
     * 以目标的宽高最大值为基准，默认为1
     *
     * @param targetRadio 引导目标展示形状的宽高比例
     * @return this
     */
    public GuideBuilder setTargetRadio(float targetRadio) {
        mGuideConfig.mTargetRadio = targetRadio;
        return this;
    }

    /**
     * 设置引导目标边距
     *
     * @param padding 边距
     * @return this
     */
    public GuideBuilder setTargetPadding(@Px int padding) {
        mGuideConfig.mTargetPadding = padding;
        return this;
    }

    /**
     * 设置引导目标边距
     *
     * @param paddingHorizontal 水平边距
     * @param paddingVertical   垂直边距
     * @return this
     */
    public GuideBuilder setTargetPadding(@Px int paddingHorizontal, @Px int paddingVertical) {
        mGuideConfig.mTargetPaddingHorizontal = paddingHorizontal;
        mGuideConfig.mTargetPaddingVertical = paddingVertical;
        return this;
    }

    /**
     * 设置引导目标边距
     *
     * @param paddingLeft   左边距
     * @param paddingTop    上边距
     * @param paddingRight  右边距
     * @param paddingBottom 下边距
     * @return this
     */
    public GuideBuilder setTargetPadding(@Px int paddingLeft, @Px int paddingTop,
                                         @Px int paddingRight, @Px int paddingBottom) {
        mGuideConfig.mTargetPaddingLeft = paddingLeft;
        mGuideConfig.mTargetPaddingTop = paddingTop;
        mGuideConfig.mTargetPaddingRight = paddingRight;
        mGuideConfig.mTargetPaddingBottom = paddingBottom;
        return this;
    }

    /**
     * 设置遮罩区域
     * 如果为空默认全屏
     *
     * @param shadowView 遮罩区域
     * @return this
     */
    public GuideBuilder setShadowView(View shadowView) {
        mGuideConfig.mShadowView = shadowView;
        return this;
    }

    /**
     * 设置遮罩颜色
     *
     * @param shadowColor 遮罩颜色
     * @return this
     */
    public GuideBuilder setShadowColor(@ColorInt int shadowColor) {
        mGuideConfig.mShadowColor = shadowColor;
        return this;
    }

    /**
     * 设置引导视图
     *
     * @param guideView 引导视图的布局文件
     * @return this
     */
    public GuideBuilder setGuideView(@LayoutRes int guideView) {
        mGuideConfig.mGuideView = LayoutInflater.from(mGuide.getActivity())
                .inflate(guideView, null, false);
        return this;
    }

    /**
     * 设置引导视图
     *
     * @param guideView 引导视图的view
     * @return this
     */
    public GuideBuilder setGuideView(View guideView) {
        mGuideConfig.mGuideView = guideView;
        return this;
    }

    /**
     * 设置引导视图的锚点类型
     *
     * @param guideAnchorType 锚点类型
     * @return this
     */
    public GuideBuilder setGuideAnchorType(@GuideConfig.AnchorType int guideAnchorType) {
        mGuideConfig.mGuideAnchorType = guideAnchorType;
        return this;
    }

    /**
     * 设置引导视图的水平偏移量
     *
     * @param guideOffsetX 引导视图的水平偏移量
     * @return this
     */
    public GuideBuilder setGuideOffsetX(@Px int guideOffsetX) {
        mGuideConfig.mGuideOffsetX = guideOffsetX;
        return this;
    }

    /**
     * 设置引导视图的垂直偏移量
     *
     * @param guideOffsetY 引导视图的垂直偏移量
     * @return this
     */
    public GuideBuilder setGuideOffsetY(@Px int guideOffsetY) {
        mGuideConfig.mGuideOffsetY = guideOffsetY;
        return this;
    }

    /**
     * 引导蒙层展示的动画
     *
     * @param animatorShow 动画资源文件
     * @return this
     */
    public GuideBuilder setAnimatorShow(@AnimatorRes int animatorShow) {
        mGuideConfig.mAnimatorShow = animatorShow;
        return this;
    }

    /**
     * 引导蒙层消失的动画
     *
     * @param animatorDismiss 动画资源文件
     * @return this
     */
    public GuideBuilder setAnimatorDismiss(@AnimatorRes int animatorDismiss) {
        mGuideConfig.mAnimatorDismiss = animatorDismiss;
        return this;
    }

    /**
     * 蒙层消失的回调监听
     *
     * @param onDismissListener 蒙层消失的回调监听
     * @return this
     */
    public GuideBuilder addOnDismissListener(GuideView.OnDismissListener onDismissListener) {
        if (mGuideConfig.mOnDismissListeners == null) {
            mGuideConfig.mOnDismissListeners = new ArrayList<>();
        }
        mGuideConfig.mOnDismissListeners.add(onDismissListener);
        return this;
    }

    /**
     * 目标超出屏幕的回调监听
     *
     * @param onOutOfRangeListener 目标超出屏幕的回调监听
     * @return this
     */
    public GuideBuilder setOnOutOfRangeListener(GuideView.OnOutOfRangeListener onOutOfRangeListener) {
        mGuideConfig.mOnOutOfRangeListener = onOutOfRangeListener;
        return this;
    }

    /**
     * 创建引导视图
     *
     * @return 引导视图
     */
    private GuideView create() {
        Activity activity = mGuide.getActivity();
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        GuideView guideView = new GuideView(activity);
        guideView.setGuideConfig(mGuideConfig);
        guideView.addView(mGuideConfig.mGuideView);
        return guideView;
    }

    /**
     * 直接展示引导视图
     */
    public void show() {
        showReturnGuide();
    }

    /**
     * 展示引导视图，并返回view
     *
     * @return 引导视图
     */
    @Nullable
    public GuideView showReturnGuide() {
        GuideView guideView = create();
        if (guideView != null) {
            mGuide.addGuide(guideView);
        }
        return guideView;
    }
}
