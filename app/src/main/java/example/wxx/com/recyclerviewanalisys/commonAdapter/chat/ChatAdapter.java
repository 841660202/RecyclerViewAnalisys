package example.wxx.com.recyclerviewanalisys.commonAdapter.chat;

import android.content.Context;

import java.util.List;

import example.wxx.com.recyclerviewanalisys.R;
import example.wxx.com.recyclerviewanalisys.commonAdapter.common.MultiTypeSupport;
import example.wxx.com.recyclerviewanalisys.commonAdapter.common.RecyclerCommonAdapter;
import example.wxx.com.recyclerviewanalisys.commonAdapter.common.ViewHolder;

public class ChatAdapter extends RecyclerCommonAdapter<ChatDataEntity> {

    public ChatAdapter(Context context, List<ChatDataEntity> chatDatas) {
        super(context, chatDatas, new MultiTypeSupport<ChatDataEntity>() {
            // 使用哪种布局
            @Override
            public int getLayoutId(ChatDataEntity item) {
                if (item.isMe == 1) {
                    return R.layout.item_chat_me;
                }
                return R.layout.item_chat_friend;
            }
        });
    }

    @Override
    protected void convert(ViewHolder holder, ChatDataEntity chatData, int position) {
        holder
                .setText(R.id.tvText, chatData.getChatContent())
                .setText(R.id.tpText, Integer.toString(chatData.getIsMe()));
    }
}
