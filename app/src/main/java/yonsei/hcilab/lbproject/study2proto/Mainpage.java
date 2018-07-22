package yonsei.hcilab.lbproject.study2proto;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;


public class Mainpage extends AppCompatActivity {




    int[] resIds = {R.drawable.coffee01, R.drawable.coffee02, R.drawable.coffee03, R.drawable.coffee04, R.drawable.coffee05};

    TextView txtHistory;
    TextView txtSwipeLeft;
    TextView txtSwipeRight;

    Button btnNew;

    int cellNum;
    int personNum;

    private ViewPager mViewPager;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    String fileName01;
    String fileName02;
    String fileName03;
    String fileLocation01;
    String fileLocation02;
    String fileLocation03;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        // Custom App Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.custom_bar);
        actionBar.setElevation(0);

        // intent
        Intent intent = new Intent();
        cellNum = intent.getIntExtra("cellNum", 0);
        personNum = intent.getIntExtra("personNum", 0);

        // get Views
        txtHistory = (TextView)findViewById(R.id.txt_main_history);
        txtSwipeLeft = (TextView)findViewById(R.id.txt_main_swipe_left);
        txtSwipeRight = (TextView)findViewById(R.id.txt_main_swipe_right);
        btnNew = (Button)findViewById(R.id.btn_main_new);




        // history text moving effect
        txtHistory.setSelected(true);

        // ViewPager Setting
        mViewPager = (ViewPager) findViewById(R.id.pager_main_list);
        mCardAdapter = new CardPagerAdapter();


        File[] listFiles = (new File(Environment.getExternalStorageDirectory()+"/Pictures/Study2/").listFiles());

        fileLocation01 = Environment.getExternalStorageDirectory() + "/Pictures/Study2/" + listFiles[0].getName();
        fileLocation02 = Environment.getExternalStorageDirectory() + "/Pictures/Study2/" + listFiles[1].getName();
        fileLocation03 = Environment.getExternalStorageDirectory() + "/Pictures/Study2/" + listFiles[2].getName();



        mCardAdapter.addCardItem(new CardItem(fileLocation01, 11));
        mCardAdapter.addCardItem(new CardItem(fileLocation02, 22));
        mCardAdapter.addCardItem(new CardItem(fileLocation03, 33));


        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(true, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(2);






        // NEW RECORD Button Click Event
        btnNew.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainpage.this, OpenCamera.class);
                intent.putExtra("cellNum", cellNum);
                intent.putExtra("personNum", personNum);
                startActivity(intent);
            }
        });


    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }




}
