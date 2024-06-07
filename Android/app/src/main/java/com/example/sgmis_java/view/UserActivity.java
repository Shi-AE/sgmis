package com.example.sgmis_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sgmis_java.R;
import com.example.sgmis_java.api.Api;
import com.example.sgmis_java.api.service.LoginService;
import com.example.sgmis_java.domain.vo.UpdateUserVo;
import com.example.sgmis_java.utils.MessageUtils;
import com.example.sgmis_java.utils.SharedPreferencesUtils;

public class UserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText accountText = findViewById(R.id.account);
        EditText oldPasswordText = findViewById(R.id.oldPassword);
        EditText newPasswordText = findViewById(R.id.newPassword);
        EditText confirmPasswordText = findViewById(R.id.confirmPassword);

        accountText.setKeyListener(null);
        accountText.setText(SharedPreferencesUtils.getString("account", "用户信息错误"));

        Button update = findViewById(R.id.update);

        update.setOnClickListener(l -> Api.execute(
                this,
                Api.creat(LoginService.class).updatePassword(
                        UpdateUserVo.builder()
                                .account(accountText.getText().toString())
                                .oldPassword(oldPasswordText.getText().toString())
                                .newPassword(newPasswordText.getText().toString())
                                .confirmPassword(confirmPasswordText.getText().toString())
                                .build()
                ),
                (data, msg) -> {
                    MessageUtils.showToast(this, msg);
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
        ));
    }
}
