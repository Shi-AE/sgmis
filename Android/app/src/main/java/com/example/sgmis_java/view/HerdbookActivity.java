package com.example.sgmis_java.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.sgmis_java.R;
import com.example.sgmis_java.api.Api;
import com.example.sgmis_java.api.service.HerdbookService;
import com.example.sgmis_java.domain.pojo.Xtspz;
import com.example.sgmis_java.utils.MessageUtils;

public class HerdbookActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herdbook);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText nameET = findViewById(R.id.name);
        EditText shortNameET = findViewById(R.id.shortName);
        EditText phoneET = findViewById(R.id.phone);
        EditText mailET = findViewById(R.id.mail);
        EditText urlET = findViewById(R.id.url);
        EditText addressET = findViewById(R.id.address);
        ImageView logo = findViewById(R.id.logo);

        Api.execute(this, Api.creat(HerdbookService.class).getInfo(), (data, msg) -> {
            String name = data.getName();
            String shortName = data.getShortName();
            String phone = data.getPhone();
            String mail = data.getMail();
            String url = data.getUrl();
            String address = data.getAddress();
            String logoUrl = data.getLogoUrl();

            nameET.setText(name);
            shortNameET.setText(shortName);
            phoneET.setText(phone);
            mailET.setText(mail);
            urlET.setText(url);
            addressET.setText(address);
            Glide.with(logo)
                    .load(Api.baseUrl + Api.logo + logoUrl)
                    .error(R.drawable.baseline_hourglass_empty_24)
                    .fitCenter()
                    .into(logo);
        });

        Button save = findViewById(R.id.save);
        save.setOnClickListener(
                l -> Api.execute(
                        this,
                        Api.creat(HerdbookService.class).postInfo(
                                Xtspz.builder()
                                        .name(nameET.getText().toString())
                                        .shortName(shortNameET.getText().toString())
                                        .phone(phoneET.getText().toString())
                                        .mail(mailET.getText().toString())
                                        .url(urlET.getText().toString())
                                        .address(addressET.getText().toString())
                                        .build()
                        ),
                        (data, msg) -> {
                            MessageUtils.showToast(this, msg);
                            this.recreate();
                        }
                )
        );
    }
}
