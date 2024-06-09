package com.example.sgmis_java.view;

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

import com.example.sgmis_java.R;
import com.example.sgmis_java.api.Api;
import com.example.sgmis_java.api.service.PigeonService;
import com.example.sgmis_java.domain.pojo.Pigeon;
import com.example.sgmis_java.utils.MessageUtils;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;

public class PigeonDataActivity extends AppCompatActivity {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Api.yyyyMMdd);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pigeon_data);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView pigeonList = findViewById(R.id.pigeonList);
        Api.execute(this, Api.creat(PigeonService.class).listPigeon(), (data, msg) -> {

            Map<Long, Pigeon> pigeonMap = data.stream()
                    .collect(Collectors.toMap(
                            Pigeon::getId,
                            x -> x
                    ));

            pigeonList.setAdapter(new ArrayAdapter<Pigeon>(getApplication(), R.layout.pigeon_item, data) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                    if (convertView == null) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.pigeon_item, parent, false);
                    }

                    Pigeon item = getItem(position);

                    if (item == null) {
                        return LayoutInflater.from(getContext()).inflate(R.layout.pigeon_item, parent, false);
                    }

                    TextView updateData = convertView.findViewById(R.id.updateData);
                    TextView gpcx = convertView.findViewById(R.id.gpcx);
                    ImageView picture = convertView.findViewById(R.id.picture);
                    TextView ringNumber = convertView.findViewById(R.id.ringNumber);
                    TextView sex = convertView.findViewById(R.id.sex);
                    TextView fatherRingNumber = convertView.findViewById(R.id.fatherRingNumber);
                    TextView motherRingNumber = convertView.findViewById(R.id.motherRingNumber);
                    TextView yan = convertView.findViewById(R.id.yan);
                    TextView ys = convertView.findViewById(R.id.ys);
                    TextView lx = convertView.findViewById(R.id.lx);
                    TextView state = convertView.findViewById(R.id.state);
                    TextView jb = convertView.findViewById(R.id.jb);
                    TextView isGrade = convertView.findViewById(R.id.isGrade);
                    TextView remark = convertView.findViewById(R.id.remark);
                    Button delete = convertView.findViewById(R.id.delete);

                    Pigeon fp = pigeonMap.get(item.getFid());
                    Pigeon mp = pigeonMap.get(item.getMid());

                    updateData.setText(item.getUpdateData().format(formatter));
                    gpcx.setText(StrUtil.blankToDefault(item.getGpcx(), "---"));
                    ringNumber.setText(item.getRingNumber() + " || " + item.getName() + " || " + item.getBloodline());
                    sex.setText(StrUtil.blankToDefault(item.getSex(), "---"));
                    fatherRingNumber.setText(fp == null ? "---" : fp.getRingNumber() + " || " + fp.getName() + " || " + fp.getBloodline());
                    motherRingNumber.setText(mp == null ? "---" : mp.getRingNumber() + " || " + mp.getName() + " || " + mp.getBloodline());
                    yan.setText(StrUtil.blankToDefault(item.getYan(), "---"));
                    ys.setText(StrUtil.blankToDefault(item.getYs(), "---"));
                    lx.setText(StrUtil.blankToDefault(item.getLx(), "---"));
                    state.setText(StrUtil.blankToDefault(item.getState(), "---"));
                    jb.setText(StrUtil.blankToDefault(item.getJb(), "---"));
                    isGrade.setText(StrUtil.blankToDefault(item.getIsGrade(), "---"));
                    remark.setText(StrUtil.blankToDefault(item.getRemark(), "---"));

                    delete.setOnClickListener(
                            l -> MessageUtils.showConfirmationDialog(
                                    PigeonDataActivity.this,
                                    "删除鸽子",
                                    "确认要删除鸽子 " + item.getRingNumber() + " 吗？\n" +
                                            "这可能会影响到子代关系",
                                    (dialog, which) -> Api.execute(
                                            PigeonDataActivity.this,
                                            Api.creat(PigeonService.class).removePigeonById(
                                                    item.getId(),
                                                    item.getSex()
                                            ),
                                            (data, msg) -> {
                                                MessageUtils.showToast(PigeonDataActivity.this, msg);
                                                PigeonDataActivity.this.recreate();
                                            }
                                    )
                            )
                    );

                    ringNumber.setOnClickListener(l -> {
                        MessageUtils.showToast(getApplication(), item.getRingNumber());
                    });

                    if (fp != null) {
                        fatherRingNumber.setOnClickListener(l -> {
                            MessageUtils.showToast(getApplication(), fp.getRingNumber());
                        });
                    }

                    if (mp != null) {
                        motherRingNumber.setOnClickListener(l -> {
                            MessageUtils.showToast(getApplication(), mp.getRingNumber());
                        });
                    }

                    return convertView;
                }
            });
        });
    }
}
