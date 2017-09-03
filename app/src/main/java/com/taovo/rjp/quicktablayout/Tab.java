package com.taovo.rjp.quicktablayout;

import android.view.View;
import android.widget.TextView;

/**
 * @author Gimpo create on 2017/9/1 14:58
 * @email : jimbo922@163.com
 */

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

    public Tab(String title){
        this.title = title;
    }

    public Tab(String title, String num){
        this.title = title;
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTabLeft() {
        return tabLeft;
    }

    public void setTabLeft(int tabLeft) {
        this.tabLeft = tabLeft;
    }

    public int getIndicatorLeft() {
        return indicatorLeft;
    }

    public void setIndicatorLeft(int indicatorLeft) {
        this.indicatorLeft = indicatorLeft;
    }

    public int getIndicatorWidth() {
        return indicatorWidth;
    }

    public void setIndicatorWidth(int indicatorWidth) {
        this.indicatorWidth = indicatorWidth;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int tabWidth) {
        this.tabWidth = tabWidth;
        textView.setWidth(tabWidth);
    }

    public TextView getTvNotice() {
        return tvNotice;
    }

    public void setTvNotice(TextView tvNotice) {
        this.tvNotice = tvNotice;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public View getTabView() {
        return tabView;
    }

    public void setTabView(View tabView) {
        this.tabView = tabView;
    }
}
