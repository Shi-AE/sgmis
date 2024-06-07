package com.example.sgmis_java.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.sgmis_java.domain.dto.NavItemDTO;

import java.util.ArrayList;
import java.util.List;

public class SysActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sys), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView pigeonText = findViewById(R.id.sys_text);
        pigeonText.setTextColor(Color.parseColor("#D91AD9"));

        LinearLayout pigeon = findViewById(R.id.home);
        pigeon.setOnClickListener(l -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        LinearLayout sys = findViewById(R.id.pigeon);
        sys.setOnClickListener(l -> {
            Intent intent = new Intent(this, PigeonActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        ListView sysNav = findViewById(R.id.sys_nav);
        List<NavItemDTO> navItemDTOList = new ArrayList<>();
        navItemDTOList.add(new NavItemDTO(R.drawable.baseline_line_weight_36, "选项设置", OptionActivity.class));
        navItemDTOList.add(new NavItemDTO(R.drawable.baseline_assignment_36, "鸽舍信息", DovecotInfoActivity.class));
        navItemDTOList.add(new NavItemDTO(R.drawable.baseline_menu_book_36, "血统书配置", HerdbookActivity.class));
        navItemDTOList.add(new NavItemDTO(R.drawable.baseline_people_36, "用户设置", UserActivity.class));
        navItemDTOList.add(new NavItemDTO(R.drawable.baseline_groups_36, "管理员设置", AdminActivity.class));
        navItemDTOList.add(new NavItemDTO(R.drawable.baseline_manage_search_36, "登录信息", LoginInfoActivity.class));
        sysNav.setAdapter(new ArrayAdapter<NavItemDTO>(this, R.layout.nav_item, navItemDTOList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.nav_item, parent, false);
                }

                NavItemDTO item = getItem(position);

                if (item == null) {
                    return LayoutInflater.from(getContext()).inflate(R.layout.nav_item, parent, false);
                }

                ImageView iconView = convertView.findViewById(R.id.nav_icon);
                TextView nameView = convertView.findViewById(R.id.nav_name);

                iconView.setImageResource(item.getIcon());
                nameView.setText(item.getName());

                convertView.setOnClickListener(l -> {
                    Intent intent = new Intent(getApplication(), item.getNavTo());
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                });

                return convertView;
            }
        });
    }

    @Override
    protected void onDestroy() {

        TextView pigeonText = findViewById(R.id.sys_text);
        pigeonText.setTextColor(Color.parseColor("#FFFFFF"));

        super.onDestroy();
    }
}
