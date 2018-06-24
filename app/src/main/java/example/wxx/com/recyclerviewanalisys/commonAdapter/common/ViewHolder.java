package example.wxx.com.recyclerviewanalisys.commonAdapter.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * RecyclerView的通用ViewHolder
 * 作者：wengxingxia
 * 时间：2017/6/8 0008 19:36
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "TAG";
    //    用于缓存已找到的view
    private SparseArray<View> mViews;

    public ViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * 从itemView中获取view
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
//        避免多次findViewById 对已有的View进行缓存
        if (mViews == null)
            mViews = new SparseArray<>();
        View view = mViews.get(viewId);
//        使用缓存的方式减少findViewById的次数
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

//    通用的功能进行封装，设置文本，设置条目的点击事件，设置图片

    /**
     * 给TextView设置文本
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, CharSequence text) {
        try {
            TextView tv = getView(viewId);
            tv.setText(text);
        } catch (Exception e) {
            Log.e(TAG, "setText: ", e);
            e.printStackTrace();
        }
//        链式调用
        return this;
    }

    /**
     * 设置图片资源
     *
     * @param viewId
     * @param resourceId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int resourceId) {

        return this;
    }

    /**
     * 给组件设置监听器
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setViewClickListener(int viewId, View.OnClickListener listener) {
        getView(viewId).setOnClickListener(listener);
        return this;
    }

    /**
     * 使用自定义的一套规范 HolderImageLoader 加载图片
     * 有利于解耦图片加载，比如不固定于第三方的Glide等等
     *
     * @param viewId
     * @param imageLoader
     * @return
     */
    public ViewHolder setImagePath(int viewId, HolderImageLoader imageLoader) {
        ImageView imageView = getView(viewId);
        imageLoader.loadImage(imageView, imageLoader.getPath());
//        Glide.with(context).load(path).placeholder()
        return this;
    }

    public abstract static class HolderImageLoader {

        private String mPath;

        public HolderImageLoader(String path) {
            mPath = path;
        }

        public abstract void loadImage(ImageView imageView, String path);

        public String getPath() {
            return mPath;
        }

        public void setPath(String path) {
            mPath = path;
        }
    }

    /**
     * RecyclerView的通用Adapter
     * 作者：wengxingxia
     * 时间：2017/6/8 0008 19:36
     */

//    public abstract static class RecyclerCommonAdapter<DATA> extends RecyclerView.Adapter<ViewHolder> {
//        //  条目布局不一样，只能通过参数传递
//        private int mLayoutId;
//        //    参数通用，那么只能用泛型
//        private List<DATA> mDatas;
//        //    实例化View的LayoutInflater
//        private LayoutInflater mInflater;
//
//        public Context mContext;
//
//        private MultiTypeSupport<DATA> mMultiTypeSupport;
//
//        public RecyclerCommonAdapter(Context context, int layoutId, List<DATA> datas) {
//            mContext = context;
//            mInflater = LayoutInflater.from(context);
//            this.mLayoutId = layoutId;
//            mDatas = datas;
//        }
//
//        public RecyclerCommonAdapter(Context context, List<DATA> datas, MultiTypeSupport typeSupport) {
//            this(context, -1, datas);
//            this.mMultiTypeSupport = typeSupport;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//    //      多布局问题
//            if (mMultiTypeSupport != null) {
//    //            需要多布局
//                mLayoutId = viewType;
//            }
//    //        创建View  context
//            View view = mInflater.inflate(mLayoutId, parent, false);
//
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            if (mMultiTypeSupport != null)
//                return mMultiTypeSupport.getLayoutId(mDatas.get(position));
//            return super.getItemViewType(position);
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, final int position) {
//    //        Viewholder优化
//            convert(holder, mDatas.get(position), position);
//    //        条目点击事件
//            if (mItemClickListener != null) {
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//    //                    利用回调点击事件
//                        mItemClickListener.onItemClick(position);
//                    }
//                });
//            }
//
//            if (mItemLongClickListener != null) {
//                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//                        return mItemLongClickListener.onItemLongClick(position);
//                    }
//                });
//            }
//
//        }
//
//        /**
//         * 把必要参数传出去
//         *
//         * @param holder   ViewHolder
//         * @param data     当前位置的条目数据
//         * @param position 当前位置
//         */
//        protected abstract void convert(ViewHolder holder, DATA data, int position);
//
//        @Override
//        public int getItemCount() {
//            return mDatas.size();
//        }
//
//        private ItemClickListener mItemClickListener;
//        private ItemLongClickListener mItemLongClickListener;
//
//        public void setItemClickListener(ItemClickListener itemClickListener) {
//            mItemClickListener = itemClickListener;
//        }
//
//        public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
//            mItemLongClickListener = itemLongClickListener;
//        }
//    }
}
