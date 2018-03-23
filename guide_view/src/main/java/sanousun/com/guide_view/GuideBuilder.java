package sanousun.com.guide_view;

import android.app.Activity;
import android.support.annotation.AnimatorRes;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.Px;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

/**
 * Created with Android Studio.
 * <p>
 * author: dashu
 * date: 2017/12/17
 * time: 下午9:37
 * desc: 引导视图的构造器
 */

public class GuideBuilder {

    /**
     * 引导配置项
     */
    private Configuration mConfiguration;
    private Guide mGuide;

    public GuideBuilder(Guide guide) {
        mGuide = guide;
        mConfiguration = new Configuration();
    }

    /**
     * 设置引导目标view
     *
     * @param targetView 引导目标view
     * @return this
     */
    public GuideBuilder setTargetView(View targetView) {
        if (mConfiguration.mTargetViewList == null) {
            mConfiguration.mTargetViewList = new ArrayList<>();
        }
        mConfiguration.mTargetViewList.clear();
        mConfiguration.mTargetViewList.add(targetView);
        return this;
    }

    /**
     * 添加引导目标view，项目会把多个目标view高亮区域整合成一块
     *
     * @param targetView 引导目标view
     * @return this
     */
    public GuideBuilder addTargetView(View targetView) {
        if (mConfiguration.mTargetViewList == null) {
            mConfiguration.mTargetViewList = new ArrayList<>();
        }
        mConfiguration.mTargetViewList.add(targetView);
        return this;
    }

    /**
     * 设置引导目标展示形状
     *
     * @param targetShape 引导目标的展示形状
     * @return this
     */
    public GuideBuilder setTargetShape(@Configuration.ShapeType int targetShape) {
        mConfiguration.mTargetShape = targetShape;
        return this;
    }

    /**
     * 设置引导目标展示形状的圆角
     *
     * @param targetCorner 引导目标的展示形状圆角大小
     * @return this
     */
    public GuideBuilder setTargetCorner(@Px int targetCorner) {
        mConfiguration.mTargetCorner = targetCorner;
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
        mConfiguration.mTargetRadio = targetRadio;
        return this;
    }

    /**
     * 设置引导目标边距
     *
     * @param padding 边距
     * @return this
     */
    public GuideBuilder setTargetPadding(@Px int padding) {
        mConfiguration.mTargetPadding = padding;
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
        mConfiguration.mTargetPaddingHorizontal = paddingHorizontal;
        mConfiguration.mTargetPaddingVertical = paddingVertical;
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
        mConfiguration.mTargetPaddingLeft = paddingLeft;
        mConfiguration.mTargetPaddingTop = paddingTop;
        mConfiguration.mTargetPaddingRight = paddingRight;
        mConfiguration.mTargetPaddingBottom = paddingBottom;
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
        mConfiguration.mShadowView = shadowView;
        return this;
    }

    /**
     * 设置遮罩颜色
     *
     * @param shadowColor 遮罩颜色
     * @return this
     */
    public GuideBuilder setShadowColor(@ColorInt int shadowColor) {
        mConfiguration.mShadowColor = shadowColor;
        return this;
    }

    /**
     * 设置引导视图
     *
     * @param guideView 引导视图的布局文件
     * @return this
     */
    public GuideBuilder setGuideView(@LayoutRes int guideView) {
        mConfiguration.mGuideView = LayoutInflater.from(mGuide.getActivity())
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
        mConfiguration.mGuideView = guideView;
        return this;
    }

    /**
     * 设置引导视图的锚点类型
     *
     * @param guideAnchorType 锚点类型
     * @return this
     */
    public GuideBuilder setGuideAnchorType(@Configuration.AnchorType int guideAnchorType) {
        mConfiguration.mGuideAnchorType = guideAnchorType;
        return this;
    }

    /**
     * 设置引导视图的水平偏移量
     *
     * @param guideOffsetX 引导视图的水平偏移量
     * @return this
     */
    public GuideBuilder setGuideOffsetX(@Px int guideOffsetX) {
        mConfiguration.mGuideOffsetX = guideOffsetX;
        return this;
    }

    /**
     * 设置引导视图的垂直偏移量
     *
     * @param guideOffsetY 引导视图的垂直偏移量
     * @return this
     */
    public GuideBuilder setGuideOffsetY(@Px int guideOffsetY) {
        mConfiguration.mGuideOffsetY = guideOffsetY;
        return this;
    }

    /**
     * 引导蒙层展示的动画
     *
     * @param animatorShow 动画资源文件
     * @return this
     */
    public GuideBuilder setAnimatorShow(@AnimatorRes int animatorShow) {
        mConfiguration.mAnimatorShow = animatorShow;
        return this;
    }

    /**
     * 引导蒙层消失的动画
     *
     * @param animatorDismiss 动画资源文件
     * @return this
     */
    public GuideBuilder setAnimatorDismiss(@AnimatorRes int animatorDismiss) {
        mConfiguration.mAnimatorDismiss = animatorDismiss;
        return this;
    }

    /**
     * 蒙层消失的回调监听
     *
     * @param onDismissListener 蒙层消失的回调监听
     * @return this
     */
    public GuideBuilder addOnDismissListener(GuideView.OnDismissListener onDismissListener) {
        if (mConfiguration.mOnDismissListeners == null) {
            mConfiguration.mOnDismissListeners = new ArrayList<>();
        }
        mConfiguration.mOnDismissListeners.add(onDismissListener);
        return this;
    }

    /**
     * 目标超出屏幕的回调监听
     *
     * @param onOutOfRangeListener 目标超出屏幕的回调监听
     * @return this
     */
    public GuideBuilder setOnOutOfRangeListener(GuideView.OnOutOfRangeListener onOutOfRangeListener) {
        mConfiguration.mOnOutOfRangeListener = onOutOfRangeListener;
        return this;
    }

    /**
     * 创建引导视图
     */
    private GuideView create() {
        Activity activity = mGuide.getActivity();
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        GuideView guideView = new GuideView(activity);
        guideView.setTargetViewList(mConfiguration.mTargetViewList);
        guideView.setTargetShape(mConfiguration.mTargetShape);
        guideView.setTargetCorner(mConfiguration.mTargetCorner);
        guideView.setTargetRadio(mConfiguration.mTargetRadio);
        guideView.setTargetPaddingLeft(mConfiguration.getTargetPaddingLeft());
        guideView.setTargetPaddingTop(mConfiguration.getTargetPaddingTop());
        guideView.setTargetPaddingRight(mConfiguration.getTargetPaddingRight());
        guideView.setTargetPaddingBottom(mConfiguration.getTargetPaddingBottom());
        guideView.setShadowColor(mConfiguration.mShadowColor);
        guideView.setGuideAnchor(mConfiguration.mGuideAnchorType);
        guideView.setGuideOffsetX(mConfiguration.mGuideOffsetX);
        guideView.setGuideOffsetY(mConfiguration.mGuideOffsetY);
        guideView.setAnimatorShow(mConfiguration.mAnimatorShow);
        guideView.setAnimatorDismiss(mConfiguration.mAnimatorDismiss);
        guideView.addView(mConfiguration.mGuideView);
        if (mConfiguration.mOnDismissListeners != null) {
            for (GuideView.OnDismissListener onDismissListener : mConfiguration.mOnDismissListeners) {
                guideView.addOnDismissListener(onDismissListener);
            }
        }
        guideView.setOnOutOfRangeListener(mConfiguration.mOnOutOfRangeListener);
        return guideView;
    }

    /**
     * 直接展示引导视图
     */
    public void show() {
        GuideView guideView = create();
        if (guideView == null) {
            return;
        }
        mGuide.addGuide(guideView);
    }
}
