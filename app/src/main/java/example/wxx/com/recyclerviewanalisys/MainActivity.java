package example.wxx.com.recyclerviewanalisys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import example.wxx.com.recyclerviewanalisys.baseuse.BaseUseActivity;
import example.wxx.com.recyclerviewanalisys.commonAdapter.CommonAdapterActivity;
import example.wxx.com.recyclerviewanalisys.list.NHDZListActivity;
import example.wxx.com.recyclerviewanalisys.wrap.HeaderFooterListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 基本使用
     * @param view
     */
    public void baseUseOnClick(View view) {

        Intent intent = new Intent(this, BaseUseActivity.class);
        startActivity(intent);
    }

    /**
     * 仿内涵段子列表
     * @param view
     */
    public void nhdzUseOnClick(View view) {
        Intent intent = new Intent(this, NHDZListActivity.class);
        startActivity(intent);
    }

    /**
     * 万能Adapter和多布局显示
     * @param view
     */
    public void commonAdapter(View view) {
        Intent intent = new Intent(this, CommonAdapterActivity.class);
        startActivity(intent);
    }

    /**
     * 添加头部和底部
     * @param view
     */
    public void headerFooter(View view) {
        Intent intent = new Intent(this, HeaderFooterListActivity.class);
        startActivity(intent);
    }
}
