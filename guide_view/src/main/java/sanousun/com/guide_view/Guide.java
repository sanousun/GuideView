package sanousun.com.guide_view;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * @author dashu
 * @date 2017/12/20
 * 入口
 */

public class Guide {

    private final WeakReference<Activity> mActivity;
    private LinkedList<GuideView> mGuideViews;

    /**
     * 引导页展示采用一个guide实例调用{@link #createGuide()}，
     * guide内部实现了蒙层的顺序调用
     *
     * @param activity 宿主activity
     */
    public Guide(@NonNull Activity activity) {
        mActivity = new WeakReference<>(activity);
    }

    public GuideBuilder createGuide() {
        return new GuideBuilder(this);
    }

    /**
     * 返回宿主的activity
     *
     * @return 宿主页面
     */
    Activity getActivity() {
        return mActivity.get();
    }

    private boolean isShowGuide = false;

    /**
     * 添加目标引导页到队列中，可以实现顺序展示
     *
     * @param guideView 生成的引导页
     */
    void addGuide(@NonNull GuideView guideView) {
        if (mGuideViews == null) {
            mGuideViews = new LinkedList<>();
        }
        mGuideViews.add(guideView);
        showGuide();
    }

    /**
     * 展示引导页
     * 同时给引导页添加消失回调，使按链表中的顺序展示
     */
    private void showGuide() {
        if (mGuideViews == null || mGuideViews.size() == 0) {
            return;
        }
        if (isShowGuide) {
            return;
        }
        GuideView guideView = mGuideViews.getFirst();
        guideView.setSystemDismissListener(new GuideView.OnDismissListener() {
            @Override
            public void onDismiss() {
                mGuideViews.removeFirst();
                isShowGuide = false;
                showGuide();
            }
        });
        guideView.show();
        isShowGuide = true;
    }
}
