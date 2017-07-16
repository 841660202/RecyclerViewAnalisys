package example.wxx.com.recyclerviewanalisys.baseuse;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.wxx.com.recyclerviewanalisys.R;

public class BaseUseActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<String> mDatas;
    private LinearLayoutItemDecoration mLinearLayoutItemDecoration;
    private GridLayoutItemDecoration mGridLayoutItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_use);
        ButterKnife.bind(this);
        initData();
        mGridLayoutItemDecoration = new GridLayoutItemDecoration(this, R.drawable.item_deliver_01);
        mRecyclerView.setAdapter(new RecyclerAdapter());
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mLinearLayoutItemDecoration = new LinearLayoutItemDecoration(this, R.drawable.item_deliver_01);
//        mRecyclerView.addItemDecoration(mLinearLayoutItemDecoration);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.addItemDecoration(new GridLayoutItemDecoration(this,R.drawable.item_deliver_01));

    }

    /**
     * 初始化数据
     */
    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

                break;
            case R.id.id_action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

                break;
        }
        return true;
    }

    /**
     * RecyclerView适配器
     */
    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            创建ViewHolder
            View itemView = LayoutInflater.from(BaseUseActivity.this).inflate(R.layout.item_home, parent, false);
            ViewHolder viewHolder = new ViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
//          绑定数据
            holder.mIdNum.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
//            显示条数
            return mDatas.size();
        }

       public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
            @BindView(R.id.id_num)
            TextView mIdNum;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    /**
     * 添加分割线，10px的红色
     */
    private class RecyclerItemDecoration extends RecyclerView.ItemDecoration{

        private Paint mPaint;
        public RecyclerItemDecoration() {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.RED);
        }

        /***
         * 基本操作，留出分割线位置
         * @param outRect
         * @param view
         * @param parent
         * @param state
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            代表每个底部的位置留出10px来绘制分割线,最后一个不需要分割线
            int position = parent.getChildLayoutPosition(view);
//              parent.getChildCount()是不断变化的，现在没办法保证最后一条
//            保证第一条，所以除了第一天，下面的所有子view的顶部中都加10px
            if(position != 0){
                outRect.top = 10;
            }
        }

        /**
         * 绘制分割线
         * @param canvas
         * @param parent
         * @param state
         */
        @Override
        public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
//            利用canvas想绘制什么就绘制什么
//            在每一个Item的头部绘制
            int childCount = parent.getChildCount();
//            指定绘制区域
            Rect rect = new Rect();
            rect.left = parent.getPaddingLeft();
            rect.right = parent.getWidth()-parent.getPaddingRight();
            for (int i = 1; i < childCount; i++) {//从第二个开始加头部
                rect.bottom = parent.getChildAt(i).getTop();
                rect.top = rect.bottom-10;
                canvas.drawRect(rect,mPaint);
            }

        }
    }
}
