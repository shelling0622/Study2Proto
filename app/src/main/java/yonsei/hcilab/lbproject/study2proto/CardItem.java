package yonsei.hcilab.lbproject.study2proto;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

public class CardItem {


    private String mTitleResource;
    private int mTextResource;

    public CardItem(String title, int text){
        mTitleResource = title;
        mTextResource = text;

    }

    public int getText() {
        return mTextResource;
    }

    public String getTitle() {
        return mTitleResource;
    }
}