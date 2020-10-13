package com.example.twoactivy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity{
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//调用其父类Activity的onCreate方法来实现对界面的图画绘制工作
        setContentView(R.layout.activity_another);//setContentView(R.layout.activity_the_aty)的作用是加载第二个activity的界面
        Intent i =getIntent();

        tv=(TextView)findViewById(R.id.tv);//用TextView显示值
        tv.setText(i.getStringExtra("data"));

    }
}
