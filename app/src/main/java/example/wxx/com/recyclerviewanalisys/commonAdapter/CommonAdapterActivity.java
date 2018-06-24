package example.wxx.com.recyclerviewanalisys.commonAdapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.wxx.com.recyclerviewanalisys.R;
import example.wxx.com.recyclerviewanalisys.commonAdapter.chat.ChatAdapter;
import example.wxx.com.recyclerviewanalisys.commonAdapter.chat.ChatData;

public class CommonAdapterActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<ChatData> mChatDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_use);
        ButterKnife.bind(this);

        initData();
//        设置数据
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChatAdapter chatAdapter = new ChatAdapter(this, mChatDatas);
        chatAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.i("click", String.valueOf(position));
                Toast.makeText(CommonAdapterActivity.this, "click:" + String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(chatAdapter);

    }

    private void initData() {
        mChatDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                mChatDatas.add(new ChatData("自己内容" + i, 1));
            } else
                mChatDatas.add(new ChatData("朋友内容" + i, 0));
        }
    }
}
