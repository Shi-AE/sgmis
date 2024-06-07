package com.example.sgmis_java.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sgmis_java.R;
import com.example.sgmis_java.api.Api;
import com.example.sgmis_java.api.service.LoginService;
import com.example.sgmis_java.domain.pojo.LoginMsg;

import java.time.format.DateTimeFormatter;

public class LoginInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_info);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView loginList = findViewById(R.id.loginList);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Api.yyyyMMddHHmmss);
        Api.execute(this, Api.creat(LoginService.class).getLoginMessage(), (data, msg) -> {
            loginList.setAdapter(new ArrayAdapter<LoginMsg>(getApplication(), R.layout.login_item, data) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                    if (convertView == null) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.login_item, parent, false);
                    }

                    LoginMsg item = getItem(position);

                    if (item == null) {
                        return LayoutInflater.from(getContext()).inflate(R.layout.login_item, parent, false);
                    }

                    TextView accountText = convertView.findViewById(R.id.account);
                    TextView ipText = convertView.findViewById(R.id.ip);
                    TextView timeText = convertView.findViewById(R.id.time);
                    TextView browserText = convertView.findViewById(R.id.browser);
                    TextView osText = convertView.findViewById(R.id.os);
                    TextView deviceText = convertView.findViewById(R.id.device);

                    accountText.setText(item.getAccount());
                    ipText.setText(item.getIp());
                    timeText.setText(item.getTime().format(formatter));
                    browserText.setText(item.getBrowser());
                    osText.setText(item.getOs());
                    deviceText.setText(item.getDevice());

                    return convertView;
                }
            });
        });

    }
}
