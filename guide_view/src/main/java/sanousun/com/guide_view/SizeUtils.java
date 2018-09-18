package sanousun.com.guide_view;

import android.content.Context;

/**
 * @author dashu
 * 2017/12/22
 * 尺寸工具类
 */

public class SizeUtils {

    /**
     * 将dp转为px值
     *
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
     * @param context 上下文
     * @return 状态栏高度
     */
    @SuppressWarnings("unused")
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
