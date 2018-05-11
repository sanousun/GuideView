package sanousun.com.guideview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import sanousun.com.guide_view.GuideConfig;
import sanousun.com.guide_view.Guide;
import sanousun.com.guide_view.GuideView;
import sanousun.com.guide_view.SizeUtils;

public class MainActivity extends AppCompatActivity implements GuideView.OnOutOfRangeListener {

    private ScrollView scrollView;
    private Guide mGuide;
    private TextView hello;
    private TextView world;
    private TextView me;
    private TextView android;

    private GuideView mHelloView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = findViewById(R.id.scrollView);
        hello = findViewById(R.id.hello);
        world = findViewById(R.id.world);
        me = findViewById(R.id.me);
        android = findViewById(R.id.android);
        mGuide = new Guide(this);
        findViewById(R.id.btn_guide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGuide();
            }
        });
        showGuide();
    }

    private void showGuide() {
        showHelloGuide();
        showWorldGuide();
        showMeGuide();
        showAndroidGuide();
    }

    private void showHelloGuide() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_guide_3, null);
        View title = view.findViewById(R.id.tv_title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                if (mHelloView != null) {
                    mHelloView.dismiss();
                }
            }
        });
        mHelloView = mGuide.createGuide()
                .setShadowColor(Color.parseColor("#88000000"))
                .setGuideAnchorType(GuideConfig.ANCHOR_CENTER)
                .setGuideView(view)
                .setAnimatorShow(R.animator.animator_show)
                .setAnimatorDismiss(R.animator.animator_hide)
                .setOnOutOfRangeListener(this)
                .showReturnGuide();
    }

    private void showWorldGuide() {
        mGuide.createGuide()
                .setTargetView(world)
                .addTargetView(me)
                .setTargetShape(GuideConfig.SHAPE_RECTANGLE)
                .setTargetPadding(SizeUtils.dp2px(this, 5f))
                .setTargetCorner(SizeUtils.dp2px(this, 2f))
                .setShadowColor(Color.parseColor("#88000000"))
                .setGuideAnchorType(GuideConfig.ANCHOR_TOP)
                .setGuideView(R.layout.view_guide_2)
                .setAnimatorShow(R.animator.animator_show)
                .setAnimatorDismiss(R.animator.animator_hide)
                .setOnOutOfRangeListener(this)
                .show();
    }

    private void showMeGuide() {
        mGuide.createGuide()
                .setTargetView(me)
                .setTargetShape(GuideConfig.SHAPE_OVAL)
                .setTargetRadio(0.5f)
                .setTargetPadding(SizeUtils.dp2px(this, 5f))
                .setShadowColor(Color.parseColor("#88000000"))
                .setGuideAnchorType(GuideConfig.ANCHOR_TOP)
                .setGuideView(R.layout.view_guide_2)
                .setAnimatorShow(R.animator.animator_show)
                .setAnimatorDismiss(R.animator.animator_hide)
                .setOnOutOfRangeListener(this)
                .show();
    }

    private void showAndroidGuide() {
        mGuide.createGuide()
                .setTargetView(android)
                .setTargetShape(GuideConfig.SHAPE_RECTANGLE)
                .setTargetPadding(SizeUtils.dp2px(this, 5f))
                .setTargetCorner(SizeUtils.dp2px(this, 2f))
                .setShadowColor(Color.parseColor("#88000000"))
                .setGuideAnchorType(GuideConfig.ANCHOR_TOP)
                .setGuideView(R.layout.view_guide_2)
                .setAnimatorShow(R.animator.animator_show)
                .setAnimatorDismiss(R.animator.animator_hide)
                .setOnOutOfRangeListener(this)
                .show();
    }

    @Override
    public void onOutOfRange(int offsetX, int offsetY) {
        scrollView.scrollBy(offsetX, offsetY);
    }
}
