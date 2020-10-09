package com.example.duihuakuang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity<mEditTextUserWordMessage> extends AppCompatActivity {
    private List<UserMessage> msgList=new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private UserMessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        inputText=(EditText) findViewById(R.id.input_text);
        send=(Button) findViewById(R.id.send);
        msgRecyclerView=(RecyclerView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //LayoutManager用来指定RecyclerView的布局方式，layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL)设置横向滚动，StaggeredLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL) 可以设置3列的瀑布排列
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter=new UserMessageAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                String content=inputText.getText().toString();
                if(!"".equals(content))
                {
                    UserMessage msg=new UserMessage(content,UserMessage.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
            }
        });
    }
    public void initMsg()//初始化信息
    {
        UserMessage msg1=new UserMessage("hello guy",UserMessage.TYPE_RECEIVED);
        msgList.add(msg1);
        UserMessage msg2=new UserMessage("hello guy",UserMessage.TYPE_SENT);
        msgList.add(msg2);
        UserMessage msg3=new UserMessage("hello guy",UserMessage.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}