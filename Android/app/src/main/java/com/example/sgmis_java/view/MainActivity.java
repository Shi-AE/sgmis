package com.example.sgmis_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sgmis_java.R;
import com.example.sgmis_java.api.Api;
import com.example.sgmis_java.api.service.LoginService;
import com.example.sgmis_java.domain.vo.UserVo;
import com.example.sgmis_java.utils.MessageUtils;
import com.example.sgmis_java.utils.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button submit = findViewById(R.id.submit);
        EditText account = findViewById(R.id.account);
        EditText password = findViewById(R.id.password);
        submit.setOnClickListener(l -> {
            String accountString = account.getText().toString().trim();
            String passwordString = password.getText().toString().trim();

            UserVo user = UserVo.builder()
                    .account(accountString)
                    .password(passwordString)
                    .build();

            Api.execute(
                    this,
                    Api.creat(LoginService.class).loginVerify(user),
                    (data, msg) -> {
                        Boolean admin = data.getAdmin();
                        // 设置用户信息存储
                        SharedPreferencesUtils.putString("account", accountString);
                        SharedPreferencesUtils.putBoolean("admin", admin);
                        MessageUtils.showToast(this, "登录成功");
                        Intent intent = new Intent(this, HomeActivity.class);
                        startActivity(intent);
                    }
            );
        });
    }
}