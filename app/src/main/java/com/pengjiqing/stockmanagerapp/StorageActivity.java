package com.pengjiqing.stockmanagerapp;

import android.app.Activity;
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
import com.pengjiqing.stockmanagerapp.po.Stock;

import java.util.List;
import java.util.Map;


public class StorageActivity extends Activity {

    private EditText stora_code, stora_count, stora_name;
    private Button stora_btn;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        stora_code = findViewById(R.id.stora_code);
        stora_count = findViewById(R.id.stora_count);
        stora_name = findViewById(R.id.stora_name);
        listView = findViewById(R.id.listView);
        stora_btn = findViewById(R.id.stora_btn);

        stora_btn.setOnClickListener(view -> {
            if (TextUtils.isEmpty(stora_code.getText().toString())) {
                showToast("请输入代码！");
                return;
            }
            if (TextUtils.isEmpty(stora_count.getText().toString())) {
                showToast("请输入数量！");
                return;
            }
            if (TextUtils.isEmpty(stora_name.getText().toString())) {
                showToast("请输入品名！");
                return;
            }
            storage();
        });
    }

    protected void storage() {
        String code = stora_code.getText().toString();
        String count = stora_count.getText().toString();
        String name = stora_name.getText().toString();
        StockDao stockDao = new StockDao();
        if (stockDao.isCheckedCode(code)){
            stockDao.updateStock(Integer.parseInt(count), code);
        }else {
            stockDao.addStock(code, Integer.parseInt(count), name);
        }

        StoreDao storeDao = new StoreDao();
        long result = storeDao.addStore(code, count, name, "入库");
        if (result > 0) {
            Log.i("addStore", "入库成功");
            showToast("入库成功！");
            stora_code.setText("");
            stora_count.setText("");
            stora_name.setText("");
            getDataList();
        }else {
            Log.i("addStore", "入库失败");
            showToast("入库失败！");
        }

    }

    private void getDataList() {
        List<Map<String, Object>> dataList = new StoreDao().queryStore("入库");
        StorageAdapter adapter = new StorageAdapter(this, dataList);
        listView.setAdapter(adapter);
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
