package example.wxx.com.recyclerviewanalisys.baseuse;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ListView样式的分割线
 * 作者：wengxingxia
 * 时间：2017/6/2 0002 08:41
 */

public class LinearLayoutItemDecoration extends RecyclerView.ItemDecoration{

    private Context mContext;
    private int mDrawableResourceId;

    private Drawable mDeliver;

//    用的是系统的一个属性 android.attrs.listDriver
    public LinearLayoutItemDecoration(Context context, int drawableResourceId) {
        mContext = context;
        mDrawableResourceId = drawableResourceId;

//        获取Drawable
        mDeliver = ContextCompat.getDrawable(mContext,mDrawableResourceId);
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
            outRect.top = mDeliver.getIntrinsicHeight();
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
            View childView = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();

            rect.bottom = parent.getChildAt(i).getTop()-params.topMargin;
            rect.top = rect.bottom-mDeliver.getIntrinsicHeight();
            mDeliver.setBounds(rect);
            mDeliver.draw(canvas);
        }

    }
}

