package example.wxx.com.recyclerviewanalisys.wrap;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * 头部和底部包裹Adapter
 * 作者：wengxingxia
 * 时间：2017/6/18 0018 12:06
 */

public class WrapRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //    数据列表的Adapter，不包含头部的
    private RecyclerView.Adapter mAdapter;

    //    头部和底部的集合 不用ArrayList 用map集合进行标识 key是int value是object 用SparseArray回更加高效
    private SparseArray<View> mHeaders, mFooters;

    private static int BASE_HEADER_KEY = 100000;
    private static int BASE_Footer_KEY = 100000;


    public WrapRecyclerAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        mHeaders = new SparseArray<>();
        mFooters = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        怎么区分头部还是底部  只能根据ViewType

        if (mHeaders.indexOfKey(viewType) >= 0) {
//            是头部
            return createHeaderFooterViewHolder(mHeaders.get(viewType));

        } else if (mFooters.indexOfKey(viewType) >= 0) {
//            底部
            return createHeaderFooterViewHolder(mFooters.get(viewType));
        }
//        列表

        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    /**
     * 创建头部和底部的ViewHolder
     *
     * @param view
     * @return
     */
    private RecyclerView.ViewHolder createHeaderFooterViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        头部和底部都不用去绑定数据
        int numHeaders = mHeaders.size();
        if(position<numHeaders){
            return;
        }

//      只需要绑定Adapter的数据
        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, position);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
//        position --> viewType  头部1 底部-1 列表0

        int numHeaders = mHeaders.size();
        if (position < numHeaders) {
            return mHeaders.keyAt(position);
        }

        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }

        int footerPosition = adjPosition - adapterCount;
        return mFooters.keyAt(footerPosition);

    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + mFooters.size() + mHeaders.size();
    }

    /**
     * 添加头部
     */
    public void addHeaderView(View view) {
        if (mHeaders.indexOfValue(view) == -1) {
//            集合里面没有就添加，不要重复添加
            mHeaders.put(BASE_HEADER_KEY++, view);
            //              更新列表
            notifyDataSetChanged();
        }
    }

    /**
     * 添加底部
     */
    public void addFooterView(View view) {
        if (mFooters.indexOfValue(view) == -1) {
//            集合里面没有就添加，不要重复添加
            mFooters.put(BASE_Footer_KEY++, view);
            //              更新列表
            notifyDataSetChanged();
        }
    }

    /**
     * 移除头部
     */
    public void removeHeaderView(View view) {
        if (mHeaders.indexOfValue(view) >= 0) {
            mHeaders.removeAt(mHeaders.indexOfValue(view));
            //              更新列表
            notifyDataSetChanged();
        }
    }

    /**
     * 移除底部
     */
    public void removeFooterView(View view) {
        if (mFooters.indexOfValue(view) >= 0) {
            mFooters.removeAt(mFooters.indexOfValue(view));
            //              更新列表
            notifyDataSetChanged();
        }
    }


}
