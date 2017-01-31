package io.tailorweb.scoreboard.model;

public class Data {
    private int mCount;
    private String mTitle;

    public Data (String title,int score) {
        mTitle = title;
        mCount = score;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int score) {
        mCount = score;
    }
}
