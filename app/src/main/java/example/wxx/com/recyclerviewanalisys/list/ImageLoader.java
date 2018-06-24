package example.wxx.com.recyclerviewanalisys.list;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import example.wxx.com.recyclerviewanalisys.R;
import example.wxx.com.recyclerviewanalisys.commonAdapter.common.ViewHolder;

/**
 * 作者：wengxingxia
 * 时间：2017/6/9 0009 10:02
 */

public class ImageLoader extends ViewHolder.HolderImageLoader{

    public ImageLoader(String path) {
        super(path);
    }

    @Override
    public void loadImage(ImageView imageView, String path) {
        Glide.with(imageView.getContext()).load(path)
                .placeholder(R.drawable.ic_discovery_default_channel)
                .into(imageView);
    }
}
