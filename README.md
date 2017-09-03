### 一、先看效果

![QuickTabLayout2.gif](http://upload-images.jianshu.io/upload_images/5994029-8bd4bb8ec7f2e346.gif?imageMogr2/auto-orient/strip)
### 二、需求
[上一篇](http://www.jianshu.com/p/5791f1f5391b)我们讲到了简易的导航条设计，有部分同学反映公司有需求需要在Tab的右上角有新消息提示，就是一个红点或者数字显示。那我们接下来就讲讲思路。
上一篇我们的Tab就是一个个的TextView，开始我想自定义一个TextView在右上角canvas画一个点或者TextView，但是实际操作起来很复杂。如果把TextView换成ViewGroup，右上角只要控制显示就好了，最合适的莫过于RelativeLayout了。
### 三、代码实现
#### 3.1 model修改
这里的代码都是基于上一篇修改的，阅读困难可以先去熟悉[第一篇](http://www.jianshu.com/p/5791f1f5391b)。首先修改Tab：
```
public class Tab {
    private String num;
    private String title;
    private View tabView;
    private TextView textView;
    private TextView tvNotice;

    private int tabLeft;
    private int tabWidth;

    private int indicatorLeft;
    private int indicatorWidth;
}
```
这里的TabView换成View了，然后里面再记录两个TextView，一个控制title，一个控制右上角的点或消息数字。
#### 3.2 Tab布局修改
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    >
    <TextView
        android:id="@+id/tv_title"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:text="新闻"
        android:layout_centerInParent="true"
        android:gravity="center"
        />

    <TextView
        android:id="@+id/tv_notice"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="1"
        android:layout_alignParentRight="true"
        android:background="@drawable/bg_notice"
        android:textColor="#fff"
        android:gravity="center"
        android:layout_margin="2dp"
        android:visibility="gone"
        />
</RelativeLayout>
```
很简单，title居中，但是注意一点，高度一定要match_parent。
#### 3.3 代码修改
```
public enum NoticeMode{
        /**
         * 圆点提示
         */
        NOTICE_DOT,
        /**
         * 数字提示
         */
        NOTICE_NUM
    }
```
增加右上角模式的枚举，一个圆点，一个数字。
```
/**
     * 首先初始化所有的tab
     *
     * @param tabs
     * @return
     */
    private void initTabs(List<Tab> tabs) {
        int size = tabs.size();
        Paint paint = new Paint();
        for (int i = 0; i < size; i++) {
            Tab tab = tabs.get(i);
            RelativeLayout rlTabLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_tab_view, null);
            LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, tabHeight);
            rlTabLayout.setLayoutParams(params);
            TextView textView = (TextView) rlTabLayout.findViewById(R.id.tv_title);
            TextView tvNotice = (TextView) rlTabLayout.findViewById(R.id.tv_notice);
            textView.setTextSize(txtSize);
            textView.setTextColor(txtUnselectedColor);
            textView.setText(tab.getTitle());
            textView.setTag(i);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tab preTab = mTabs.get(selectedIndex);
                    TextView preTextView = preTab.getTextView();
                    preTextView.setTextColor(txtUnselectedColor);
                    selectedIndex = (Integer) v.getTag();
                    setSelectState();
                    indicatorAnim(mTabs.get(selectedIndex), preTab);
                }
            });
            switch (indicatorMode) {
                case EQUAL_TAB:
                case EQUAL_CONTENT:
                    paint.setTextSize(textView.getTextSize());
                    float titleWidth = paint.measureText(textView.getText().toString());
                    tab.setIndicatorWidth((int) titleWidth);
                    break;
                case EQUAL_VALUE:
                    tab.setIndicatorWidth(indicatorWidth);
                    break;
            }
            String num = tab.getNum();
            if(!TextUtils.isEmpty(num)) {
                tvNotice.setVisibility(VISIBLE);
                switch (noticeMode) {
                    case NOTICE_NUM:
                        tvNotice.setText(num);
                        tvNotice.setTextSize(8);
                        tvNotice.setWidth(dp2px(mContext, 14));
                        tvNotice.setHeight(dp2px(mContext, 14));
                        break;
                    case NOTICE_DOT:
                        tvNotice.setText("");
                        tvNotice.setWidth(dp2px(mContext, 6));
                        tvNotice.setHeight(dp2px(mContext, 6));
                        break;
                }
            }

            tab.setTabView(rlTabLayout);
            tab.setTextView(textView);
            tab.setTvNotice(tvNotice);
        }
    }
```
前面的代码不解释了，从**String num = tab.getNum();**开始看，这里面记录一个num，如果num是空的，我们就不管了，如果有值，说明这个Tab下有新消息，然后根据noticeMode，如果是NOTICE_NUM，数字显示的区域比较大一点，14dp，可以按照需求定制出去，如果是NOTICE_DOT，显示点，那么不管数字多少，6dp显示一个圆点。
接下来是点击的时候，修改的代码比较少，直接用图片展示吧：

![JJE(97VJY$_R_D_0{%WX`CG.png](http://upload-images.jianshu.io/upload_images/5994029-b45004a8f64b78bf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
点击的时候界面上隐藏右上角，数据上置空。

附上[简书](http://www.jianshu.com/p/ec73b1b9ef61)地址，有兴趣下载下来
定制吧。