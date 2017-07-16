package example.wxx.com.recyclerviewanalisys.list;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.wxx.com.recyclerviewanalisys.R;
import example.wxx.com.recyclerviewanalisys.baseuse.GridLayoutItemDecoration;
import example.wxx.com.recyclerviewanalisys.baseuse.LinearLayoutItemDecoration;
import example.wxx.com.recyclerviewanalisys.commonAdapter.ItemClickListener;
import example.wxx.com.recyclerviewanalisys.commonAdapter.ItemLongClickListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 内涵段子列表
 */
public class NHDZListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private LinearLayoutItemDecoration mLinearLayoutItemDecoration;
    private GridLayoutItemDecoration mGridLayoutItemDecoration;

    private OkHttpClient mOkHttpClient;
    private static Handler mHandler = new Handler();

    List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> mData;
    private CategoryListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_use);
        ButterKnife.bind(this);
        mGridLayoutItemDecoration = new GridLayoutItemDecoration(this, R.drawable.item_deliver_01);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLinearLayoutItemDecoration = new LinearLayoutItemDecoration(this, R.drawable.item_deliver_01);
        mRecyclerView.addItemDecoration(mLinearLayoutItemDecoration);

//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        mRecyclerView.addItemDecoration(new GridLayoutItemDecoration(this,R.drawable.item_deliver_01));

        mOkHttpClient = new OkHttpClient();
        requestListData();
    }

    /**
     * 请求列表数据
     */
    private void requestListData() {

        // 利用Okhttp去获取网络数据
        Request.Builder builder = new Request.Builder();
        builder.url("http://is.snssdk.com/2/essay/discovery/v3/?iid=6152551759&channel=360&aid=7" +
                "&app_name=joke_essay&version_name=5.7.0&ac=wifi&device_id=30036118478&device_brand=Xiaomi&update_version_code=5701&" +
                "manifest_version_code=570&longitude=113.000366&latitude=28.171377&device_platform=android");

        mOkHttpClient.newCall(builder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                // Gson 解析成对象
                ChannelListResult channelList = new Gson().fromJson(result, ChannelListResult.class);
                // 获取列表数据
                final List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> categoryList =
                        channelList.getData().getCategories().getCategory_list();
                // 该方法不是在主线程中
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showListData(categoryList);
                    }
                });
            }
        });
    }

    /**
     * 显示列表数据
     *
     * @param categoryList
     */
    private void showListData(List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> categoryList) {
        mData = categoryList;

        mListAdapter = new CategoryListAdapter(this,mData);
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(NHDZListActivity.this, mData.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        mListAdapter.setItemLongClickListener(new ItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                Toast.makeText(NHDZListActivity.this, "长按"+mData.get(position).getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
//        设置点击事件
//        mListAdapter.setItemClickListener(new CategoryListAdapter.ItemClickListener() {
//            @Override
//           public void onItemClick(int position) {
//                Toast.makeText(NHDZListActivity.this, mData.get(position).getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}
