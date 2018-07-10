package yonsei.hcilab.lbproject.study2proto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class Mainpage extends AppCompatActivity {


    private ViewPager mViewPager;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    TextView txtHistory;
    TextView txtSwipeLeft;
    TextView txtSwipeRight;
    ImageView imgCard1;

    int cellNum;
    int personNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        // Custom App Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.custom_bar);
        actionBar.setElevation(0);

        // get cellNum, personNum
        Intent intent = new Intent();
        cellNum = intent.getIntExtra("cellNum", 0);
        personNum = intent.getIntExtra("personNum", 0);
        if((cellNum == 0)||(personNum == 0)){
            Toast.makeText(this, "cellNum or personNum is wrong", Toast.LENGTH_LONG).show();
        }

        // get Views
        txtHistory = (TextView)findViewById(R.id.txt_main_history);
        txtSwipeLeft = (TextView)findViewById(R.id.txt_main_swipe_left);
        txtSwipeRight = (TextView)findViewById(R.id.txt_main_swipe_right);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_adapter, null);
        imgCard1 = (ImageView)view.findViewById(R.id.img_main_card);

        // history text moving effect
        txtHistory.setSelected(true);

        // NO Explanation Group -> NO History

        // Image Card Viewpager
        mViewPager = (ViewPager) findViewById(R.id.pager_main_list);
        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),
                dpToPixels(2, this));
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);
        mFragmentCardShadowTransformer.enableScaling(false);
        mViewPager.setAdapter(mFragmentCardAdapter);
        mViewPager.setCurrentItem(4);
        mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);

        // if first or last, swipe messages disappear
        int itemNum = mViewPager.getCurrentItem();
        if(itemNum == 4){
            txtSwipeRight.setVisibility(View.INVISIBLE);
        }else if(itemNum == 0){
            txtSwipeLeft.setVisibility(View.INVISIBLE);
        }

        String fileName = null;

        File[] listFiles = (new File(Environment.getDataDirectory()+"/DCIM/Study2Proto/").listFiles());

        if(listFiles[0].getName().endsWith(".png") || listFiles[0].getName().endsWith(".bmp"))
            fileName = listFiles[0].getName();

        File file = new File(Environment.getDataDirectory()+"/DCIM/Study2Proto/"+fileName);

        if(file.exists()){
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();

        }


    }

    // Image Card Viewpager
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }


}
