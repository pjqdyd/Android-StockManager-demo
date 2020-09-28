package com.pengjiqing.stockmanagerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pengjiqing.stockmanagerapp.db.UserDao;

public class LoginActivity extends AppCompatActivity {

    private EditText login_name, login_pwd;
    private Button login_btn, reg2_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_name = findViewById(R.id.login_name);
        login_pwd = findViewById(R.id.login_pwd);
        login_btn = findViewById(R.id.login_btn);
        reg2_btn = findViewById(R.id.reg2_btn);

        login_btn.setOnClickListener(view -> login());
        reg2_btn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

    }

    protected void login() {
        String name = login_name.getText().toString().trim();
        String pwd = login_pwd.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            showToast("请输入登录的用户！");
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            showToast("请输入登录的密码！");
            return;
        }
        UserDao userDao = new UserDao();
        if (userDao.login(name, pwd)){
            showToast("登录成功！");
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        showToast("登录失败");
    }


    // 轻提示
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
