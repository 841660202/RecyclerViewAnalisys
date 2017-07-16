package example.wxx.com.recyclerviewanalisys.list;

import android.content.Context;
import android.text.Html;

import java.util.List;

import example.wxx.com.recyclerviewanalisys.R;
import example.wxx.com.recyclerviewanalisys.commonAdapter.RecyclerCommonAdapter;
import example.wxx.com.recyclerviewanalisys.commonAdapter.ViewHolder;

/**
 * Created by Darren on 2016/12/28.
 * Email: 240336124@qq.com
 * Description: 利用万能通用的Adapter改造后的列表
 */
public class CategoryListAdapter extends RecyclerCommonAdapter<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> {

    public CategoryListAdapter(Context context, List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> categoryListBeen) {
        super(context, R.layout.channel_list_item, categoryListBeen);
    }

    @Override
    protected void convert(ViewHolder holder, ChannelListResult.DataBean.CategoriesBean.CategoryListBean item, int position) {
        // 显示数据
        String str = item.getSubscribe_count() + " 订阅 | " +
                "总帖数 <font color='#FF678D'>" + item.getTotal_updates() + "</font>";
//        链式调用
        holder.setText(R.id.channel_text, item.getName())
                .setText(R.id.channel_topic, item.getIntro())
                .setText(R.id.channel_update_info, Html.fromHtml(str));
        holder.setImagePath(R.id.channel_icon,new ImageLoader(item.getIcon_url()));
    }

}










/*
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    private List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> mDatas;
    private Context mContext;

    public CategoryListAdapter(List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> datas, Context context) {
        mDatas = datas;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.channel_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ChannelListResult.DataBean.CategoriesBean.CategoryListBean item = mDatas.get(position);
        Glide.with(mContext).load(item.getIcon_url()).placeholder(R.drawable.ic_discovery_default_channel).into(holder.mChannelIcon);
        holder.mChannelText.setText(item.getName());
        holder.mChannelTopic.setText(item.getIntro());
        // 显示数据
        String str = item.getSubscribe_count() + " 订阅 | " +
                "总帖数 <font color='#FF678D'>" + item.getTotal_updates() + "</font>";
        holder.mChannelUpdateInfo.setText(Html.fromHtml(str));

//        绑定点击事件
        if(mItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    利用回调点击事件
                    mItemClickListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.channel_icon)
        ImageView mChannelIcon;
        @BindView(R.id.recommend_label)
        ImageView mRecommendLabel;
        @BindView(R.id.action_btn)
        TextView mActionBtn;
        @BindView(R.id.channel_text)
        TextView mChannelText;
        @BindView(R.id.channel_topic)
        TextView mChannelTopic;
        @BindView(R.id.channel_update_info)
        TextView mChannelUpdateInfo;
        @BindView(R.id.channel_text_layout)
        LinearLayout mChannelTextLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private ItemClickListener mItemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface  ItemClickListener{
        void onItemClick(int position);
    }
}
*/
