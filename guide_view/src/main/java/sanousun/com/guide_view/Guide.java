package sanousun.com.guide_view;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * Created with Android Studio.
 * <p>
 * author: dashu
 * date: 2017/12/20
 * time: 上午10:14
 * desc: 入口
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
    public Guide(Activity activity) {
        mActivity = new WeakReference<>(activity);
    }

    public GuideBuilder createGuide() {
        return new GuideBuilder(this);
    }

    public Activity getActivity() {
        return mActivity.get();
    }

    private boolean isShowGuide = false;

    public void addGuide(GuideView guideView) {
        if (mGuideViews == null) {
            mGuideViews = new LinkedList<>();
        }
        mGuideViews.add(guideView);
        showGuide();
    }

    private void showGuide() {
        if (mGuideViews == null || mGuideViews.size() == 0) {
            return;
        }
        if (isShowGuide) {
            return;
        }
        GuideView guideView = mGuideViews.getFirst();
        guideView.addOnDismissListener(new GuideView.OnDismissListener() {
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
