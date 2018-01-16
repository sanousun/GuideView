package sanousun.com.guide_view;

import android.content.Context;

/**
 * Created with Android Studio.
 * <p>
 * author: dashu
 * date: 2017/12/22
 * time: 上午10:03
 * desc: 尺寸工具类
 */

public class SizeUtils {

    /**
     * 将dp转为px值
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        float reSize = context.getResources().getDisplayMetrics().density;
        return (int) ((dpValue * reSize) + 0.5);
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        } else {
            result = dp2px(context, 25);
        }
        return result;
    }
}
