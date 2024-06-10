package com.example.sgmis_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.sgmis_java.R;
import com.example.sgmis_java.api.Api;
import com.example.sgmis_java.api.service.OplogService;
import com.example.sgmis_java.api.service.PigeonService;
import com.example.sgmis_java.domain.pojo.Oplog;
import com.example.sgmis_java.domain.pojo.Pigeon;
import com.example.sgmis_java.domain.pojo.PigeonInfo;

import java.time.format.DateTimeFormatter;

import cn.hutool.core.util.StrUtil;

public class DetailActivity extends AppCompatActivity {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Api.yyyyMMdd);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);

        ImageView picture = findViewById(R.id.picture);
        TextView gpcx = findViewById(R.id.gpcx);
        TextView createTime = findViewById(R.id.createTime);
        TextView sex = findViewById(R.id.sex);
        TextView yan = findViewById(R.id.yan);
        TextView ys = findViewById(R.id.ys);
        TextView source = findViewById(R.id.source);
        TextView bloodline = findViewById(R.id.bloodline);
        TextView lx = findViewById(R.id.lx);
        TextView jb = findViewById(R.id.jb);
        ListView oplogList = findViewById(R.id.oplogList);
        Button button = findViewById(R.id.button);

        Api.execute(this, Api.creat(PigeonService.class).getPigeon(id), (data, msg) -> {
            Pigeon pigeon = data.getPigeon();
            PigeonInfo pigeonInfo = data.getPigeonInfo();

            Glide.with(picture)
                    .load(Api.baseUrl + Api.pigeon + pigeon.getPictureUrl())
                    .error(R.drawable.baseline_hourglass_empty_24)
                    .fitCenter()
                    .into(picture);

            gpcx.setText(StrUtil.blankToDefault(pigeon.getGpcx(), "---"));
            createTime.setText(StrUtil.blankToDefault(pigeonInfo.getCreateTime().format(formatter), "---"));
            sex.setText(StrUtil.blankToDefault(pigeon.getSex(), "---"));
            yan.setText(StrUtil.blankToDefault(pigeon.getYan(), "---"));
            ys.setText(StrUtil.blankToDefault(pigeon.getYs(), "---"));
            source.setText(StrUtil.blankToDefault(pigeonInfo.getSource(), "---"));
            bloodline.setText(StrUtil.blankToDefault(pigeon.getBloodline(), "---"));
            lx.setText(StrUtil.blankToDefault(pigeon.getLx(), "---"));
            jb.setText(StrUtil.blankToDefault(pigeon.getJb(), "---"));
        });

        Api.execute(this, Api.creat(OplogService.class).getLogById(id, 10), (data, msg) -> {
            oplogList.setAdapter(new ArrayAdapter<Oplog>(getApplication(), R.layout.oplog_data_item, data) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                    if (convertView == null) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.oplog_data_item, parent, false);
                    }

                    Oplog item = getItem(position);

                    if (item == null) {
                        return LayoutInflater.from(getContext()).inflate(R.layout.oplog_data_item, parent, false);
                    }

                    TextView time = convertView.findViewById(R.id.time);
                    TextView msg = convertView.findViewById(R.id.msg);

                    time.setText(item.getTime().format(formatter));
                    msg.setText(item.getTip());

                    return convertView;
                }
            });
        });

        button.setOnClickListener(l -> {
            Intent moreIntent = new Intent(getApplication(), OpLogActivity.class);
            startActivity(moreIntent);
            overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
        });
    }
}
