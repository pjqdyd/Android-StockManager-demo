package com.pengjiqing.stockmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pengjiqing.stockmanagerapp.db.UserDao;


public class RegisterActivity extends AppCompatActivity {

    // 1. 声明控件对象
    private EditText reg_name, reg_pwd, reg_rppwd;
    private Button reg_btn, back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 2. 找到控件对象
        reg_name = findViewById(R.id.reg_name);
        reg_pwd = findViewById(R.id.reg_pwd);
        reg_rppwd = findViewById(R.id.reg_rppwd);
        reg_btn = findViewById(R.id.reg_btn);
        back_btn = findViewById(R.id.back_btn);

        // 3. 给按钮设置事件
        reg_btn.setOnClickListener(view -> register());
        back_btn.setOnClickListener(view -> finish());
    }

    // 注册方法
    protected void register() {
        String pwd = reg_pwd.getText().toString();
        String rppwd = reg_rppwd.getText().toString();
        if(TextUtils.isEmpty(reg_name.getText().toString())){
            showToast("用户名必须填写！");
            return;
        }
        if(TextUtils.isEmpty(reg_pwd.getText().toString())){
            showToast("密码必须填写！");
            return;
        }
        if(!pwd.equals(rppwd)){
            showToast("两次密码必须一致！");
            return;
        }
        // 完成注册逻辑
        UserDao userDao = new UserDao();
        String username = reg_name.getText().toString().trim();
        // 1.验证用户名重复
        if (userDao.isUserName(username)){
            showToast("用户名已存在！");
            return;
        }
        long num = userDao.addUser(username, rppwd); // 添加用户
        if (num > 0){
            Log.i("REG", "用户注册成功！");
            showToast("用户注册成功！");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        showToast("用户注册失败！");
    }

    // 轻提示
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
