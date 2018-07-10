package yonsei.hcilab.lbproject.study2proto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {

    int cellNum;
    int personNum;

    EditText editCellNum;
    EditText editPersonNum;
    Button btnGo;
    Button btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        cellNum = Integer.getInteger(editCellNum.getText().toString());
        personNum = Integer.getInteger(editPersonNum.getText().toString());

        btnGo.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, Mainpage.class);
                intent.putExtra("cellNum", cellNum);
                intent.putExtra("personNum", personNum);
                startActivity(intent);

            }
        });

        btnLog.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LogActivity.class);
                startActivity(intent);
            }
        });



    }
}
