package com.pengjiqing.stockmanagerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.pengjiqing.stockmanagerapp.utils.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StorageAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String, Object>> dataList = new ArrayList<>();

    public StorageAdapter(Context context, List<Map<String, Object>> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.listview_item, viewGroup, false);

        TextView code = view.findViewById(R.id.item_code);
        TextView count = view.findViewById(R.id.item_count);
        TextView name = view.findViewById(R.id.item_name);
        TextView time = view.findViewById(R.id.item_time);
        TextView status = view.findViewById(R.id.item_status);
        Button delBtn = view.findViewById(R.id.item_delBtn);
        Button printBtn = view.findViewById(R.id.item_printBtn);

        Map<String, Object> map = dataList.get(index);
        code.setText(map.get("scode").toString());
        count.setText(map.get("scount").toString());
        name.setText(map.get("sname").toString());
        time.setText(Tools.format((long)map.get("stime")));
        status.setText(map.get("status").toString());

        delBtn.setOnClickListener(view1 -> {
            System.out.println("删除功能");
        });
        printBtn.setOnClickListener(view1 -> {
            System.out.println("打印功能");
        });
        return view;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
