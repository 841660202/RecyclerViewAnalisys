package example.wxx.com.recyclerviewanalisys.commonAdapter.chat;

import android.content.Context;

import java.util.List;

import example.wxx.com.recyclerviewanalisys.R;
import example.wxx.com.recyclerviewanalisys.commonAdapter.common.MultiTypeSupport;
import example.wxx.com.recyclerviewanalisys.commonAdapter.common.ViewHolder;

/**
 * Created by Darren on 2016/12/28.
 * Email: 240336124@qq.com
 * Description: 利用万能通用的Adapter改造后的列表
 */
public class ChatAdapter extends ViewHolder.RecyclerCommonAdapter<ChatData> {

    public ChatAdapter(Context context, List<ChatData> chatDatas) {
        super(context, chatDatas, new MultiTypeSupport<ChatData>() {
            @Override
            public int getLayoutId(ChatData item) {
                if (item.isMe == 1) {
                    return R.layout.item_chat_me;
                }
                return R.layout.item_chat_friend;
            }
        });
    }

    @Override
    protected void convert(ViewHolder holder, ChatData chatData, int position) {
        holder.setText(R.id.tvText, chatData.getChatContent());
    }
}
