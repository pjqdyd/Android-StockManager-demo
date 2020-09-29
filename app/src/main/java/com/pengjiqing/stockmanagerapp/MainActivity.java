package com.pengjiqing.stockmanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // 1.声明控件
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2.找到控件
        gridView = findViewById(R.id.grid_view);

        // 对网格视图初始化
        initData();

        // 3.设置事件
        gridView.setOnItemClickListener(this);
    }

    // 对网格视图初始化
    private void initData() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("title", "入库");
        map1.put("img", R.drawable.ruku);
        dataList.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("title", "出库");
        map2.put("img", R.drawable.chuku);
        dataList.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("title", "盘点");
        map3.put("img", R.drawable.pandian);
        dataList.add(map3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("title", "物料信息");
        map4.put("img", R.drawable.wuliao);
        dataList.add(map4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("title", "库存查询");
        map5.put("img", R.drawable.kucun);
        dataList.add(map5);

        gridView.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int index, View view, ViewGroup viewGroup) {
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                ImageView imageView = view.findViewById(R.id.gv_item);
                TextView textView = view.findViewById(R.id.gv_item_tip);

                Map<String, Object> map = dataList.get(index);
                imageView.setImageResource((Integer) map.get("img"));
                textView.setText(map.get("title").toString());
                return view;
            }

            @Override
            public int getCount() {
                return dataList.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
        switch (index) {
            case 1:
                System.out.println("入库功能");
                break;
            case 2:
                System.out.println("出库功能");
                break;
            case 3:
                System.out.println("盘点功能");
                break;
            case 4:
                System.out.println("物料信息功能");
                break;
            case 5:
                System.out.println("库存查询功能");
                break;
            default:
                break;
        }
    }
}
