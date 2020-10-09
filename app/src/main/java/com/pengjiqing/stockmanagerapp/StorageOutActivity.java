package com.pengjiqing.stockmanagerapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pengjiqing.stockmanagerapp.db.StockDao;
import com.pengjiqing.stockmanagerapp.db.StoreDao;

import java.util.List;
import java.util.Map;


public class StorageOutActivity extends AppCompatActivity {

    private EditText stora_code, stora_count;
    private Button stora_btn;
    private ListView listView;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_out);

        stora_code = findViewById(R.id.stora_code1);
        stora_count = findViewById(R.id.stora_count1);
        listView = findViewById(R.id.listView);
        stora_btn = findViewById(R.id.stora_btn1);

        stora_btn.setOnClickListener(view -> {
            if (TextUtils.isEmpty(stora_code.getText().toString())) {
                showToast("请输入代码！");
                return;
            }
            if (TextUtils.isEmpty(stora_count.getText().toString())) {
                showToast("请输入数量！");
                return;
            }
            storageOut();
        });
    }

    protected void storageOut() {
        String code = stora_code.getText().toString();
        String count = stora_count.getText().toString();
        StockDao stockDao = new StockDao();
        if (stockDao.isCheckedCode(code)){
            int countNum = -Integer.parseInt(count);
            stockDao.updateStock(countNum, code);
            name = stockDao.getNameByCode(code);
            StoreDao storeDao = new StoreDao();
            long result = storeDao.addStore(code, count, name, "出库");
            if (result > 0) {
                Log.i("reduceStore", "出库成功");
                showToast("出库成功！");
                stora_code.setText("");
                stora_count.setText("");
                getDataList();
            }else {
                Log.i("reduceStore", "出库失败");
                showToast("出库失败！");
            }
        }else {
            Log.i("reduceStore", "出库失败");
            showToast("库存中无此代码的物品！");
        }
    }


    private void getDataList() {
        List<Map<String, Object>> dataList = new StoreDao().queryStore("出库");
        StorageAdapter adapter = new StorageAdapter(this, dataList);
        listView.setAdapter(adapter);
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
