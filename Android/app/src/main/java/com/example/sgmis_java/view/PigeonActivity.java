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

public class PigeonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pigeon);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pigeon), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView pigeonText = findViewById(R.id.pigeon_text);
        pigeonText.setTextColor(Color.parseColor("#D91AD9"));


        // 导航按钮监听
        LinearLayout pigeon = findViewById(R.id.home);
        pigeon.setOnClickListener(l -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        LinearLayout sys = findViewById(R.id.sys);
        sys.setOnClickListener(l -> {
            Intent intent = new Intent(this, SysActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        // 设置功能导航
        ListView pigeonNav = findViewById(R.id.pigeon_nav);
        List<NavItemDTO> navItemDTOList = new ArrayList<>();
        navItemDTOList.add(new NavItemDTO(R.drawable.baseline_dns_36, "鸽子库", PigeonDataActivity.class));
//        navItemDTOList.add(new NavItemDTO(R.drawable.baseline_edit_24, "新增编辑", PigeonEditActivity.class));
        navItemDTOList.add(new NavItemDTO(R.drawable.baseline_drive_file_move_outline_24, "快速入库", PigeonQuickEditActivity.class));
        navItemDTOList.add(new NavItemDTO(R.drawable.baseline_account_tree_24, "操作日志", OpLogActivity.class));
        navItemDTOList.add(new NavItemDTO(R.drawable.baseline_bar_chart_24, "统计中心", StatisticActivity.class));
//        navItemDTOList.add(new NavItemDTO(R.drawable.baseline_grid_on_24, "鸽棚巢箱", NestActivity.class));
        pigeonNav.setAdapter(new ArrayAdapter<NavItemDTO>(this, R.layout.nav_item, navItemDTOList) {
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

        TextView pigeonText = findViewById(R.id.pigeon_text);
        pigeonText.setTextColor(Color.parseColor("#FFFFFF"));

        super.onDestroy();
    }
}
