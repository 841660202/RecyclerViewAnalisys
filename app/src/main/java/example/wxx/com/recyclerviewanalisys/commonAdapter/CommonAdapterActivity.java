package example.wxx.com.recyclerviewanalisys.commonAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.wxx.com.recyclerviewanalisys.R;

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
        mRecyclerView.setAdapter(new RecyclerAdapter(this,mChatDatas));
    }

    private void initData() {
        mChatDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if(i%3==0){
                mChatDatas.add(new ChatData("自己内容"+i,1));
            }else
                mChatDatas.add(new ChatData("朋友内容"+i,0));
        }
    }

    private class RecyclerAdapter extends RecyclerCommonAdapter<ChatData>{

        public RecyclerAdapter(Context context, List<ChatData> chatDatas) {
            super(context, chatDatas, new MultiTypeSupport<ChatData>() {
                @Override
                public int getLayoutId(ChatData item) {
                    if(item.isMe==1){
                        return R.layout.item_chat_me;
                    }
                    return R.layout.item_chat_friend;
                }
            });
        }

        @Override
        protected void convert(ViewHolder holder, ChatData chatData, int position) {
            holder.setText(R.id.tvText,chatData.getChatContent());
        }
    }

    public class ChatData{
        public String chatContent;
        public int isMe;

        public ChatData(String chatContent, int isMe) {
            this.chatContent = chatContent;
            this.isMe = isMe;
        }

        public String getChatContent() {
            return chatContent;
        }

        public void setChatContent(String chatContent) {
            this.chatContent = chatContent;
        }

        public int getIsMe() {
            return isMe;
        }

        public void setIsMe(int isMe) {
            this.isMe = isMe;
        }
    }
}
