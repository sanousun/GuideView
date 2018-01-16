package sanousun.com.guideview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import sanousun.com.guide_view.Configuration;
import sanousun.com.guide_view.Guide;
import sanousun.com.guide_view.SizeUtils;

public class MainActivity extends AppCompatActivity {

    private Guide mGuide;
    private TextView hello;
    private TextView world;
    private TextView me;
    private TextView android;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        mGuide.createGuide()
                .setTargetView(hello)
                .setTargetShape(Configuration.SHAPE_OVAL)
                .setTargetRadio(0.5f)
                .setTargetPadding(SizeUtils.dp2px(this, 5f))
                .setShadowColor(Color.parseColor("#88000000"))
                .setGuideAnchorType(Configuration.ANCHOR_BOTTOM)
                .setGuideView(R.layout.view_guide)
                .setAnimatorShow(R.animator.animator_show)
                .setAnimatorDismiss(R.animator.animator_hide)
                .show();
    }

    private void showWorldGuide() {
        mGuide.createGuide()
                .setTargetView(world)
                .setTargetShape(Configuration.SHAPE_RECTANGLE)
                .setTargetPadding(SizeUtils.dp2px(this, 5f))
                .setTargetCorner(SizeUtils.dp2px(this, 2f))
                .setShadowColor(Color.parseColor("#88000000"))
                .setGuideAnchorType(Configuration.ANCHOR_BOTTOM)
                .setGuideView(R.layout.view_guide)
                .setAnimatorShow(R.animator.animator_show)
                .setAnimatorDismiss(R.animator.animator_hide)
                .show();
    }

    private void showMeGuide() {
        mGuide.createGuide()
                .setTargetView(me)
                .setTargetShape(Configuration.SHAPE_OVAL)
                .setTargetRadio(0.5f)
                .setTargetPadding(SizeUtils.dp2px(this, 5f))
                .setShadowColor(Color.parseColor("#88000000"))
                .setGuideAnchorType(Configuration.ANCHOR_BOTTOM)
                .setGuideView(R.layout.view_guide)
                .setAnimatorShow(R.animator.animator_show)
                .setAnimatorDismiss(R.animator.animator_hide)
                .show();
    }

    private void showAndroidGuide() {
        mGuide.createGuide()
                .setTargetView(android)
                .setTargetShape(Configuration.SHAPE_RECTANGLE)
                .setTargetPadding(SizeUtils.dp2px(this, 5f))
                .setTargetCorner(SizeUtils.dp2px(this, 2f))
                .setShadowColor(Color.parseColor("#88000000"))
                .setGuideAnchorType(Configuration.ANCHOR_BOTTOM)
                .setGuideView(R.layout.view_guide)
                .setAnimatorShow(R.animator.animator_show)
                .setAnimatorDismiss(R.animator.animator_hide)
                .show();
    }
}
