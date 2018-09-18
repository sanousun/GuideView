# GuideView

 [ ![Download](https://api.bintray.com/packages/shenhui1876/maven/guide-view/images/download.svg) ](https://bintray.com/shenhui1876/maven/guide-view/_latestVersion)

一个引导图的框架

### 时间线

* 2018-01-16 GuideView_1.0版本


* 2018-01-17 解决被高亮目标在 scrollView 下超出屏幕范围的情况

通过设置 OnOutOfRangeListener 获取到引导目标的偏移量

* 2018-03-15 支持目标多个 view 情况下，将高亮区域连成一块

将多个 targetView 统一计算获取一块集中的高亮区域，暂不支持多个目标分别，以及自定义的形状

* 2018-04-10 支持引导图响应点击事件

因为引导图响应点击事件会拦截触摸事件，所以透出了 guideView 去手动 dismiss

### 添加依赖

```
implementation 'com.sanousun:guide-view:1.0.0'
```

### 使用

```
new Guide(this)
    // 目标展示高亮 view
    .setTargetView(target)
    // 高亮形状
    .setTargetShape(GuideConfig.SHAPE_RECTANGLE)
    // 高亮区域留白
    .setTargetPadding(SizeUtils.dp2px(this, 5f))
    // 矩形圆角
    .setTargetCorner(SizeUtils.dp2px(this, 2f))
    // 阴影颜色
    .setShadowColor(Color.parseColor("#88000000"))
    // 锚点位置
    .setGuideAnchorType(GuideConfig.ANCHOR_TOP)
    // 引导目标
    .setGuideView(R.layout.view_guide)
    // 展示动画
    .setAnimatorShow(R.animator.animator_show)
    // 消失动画
    .setAnimatorDismiss(R.animator.animator_hide)
    // 添加一些处理回调
    .setOnOutOfRangeListener(this)
    // 展示
    .show()
```

