package com.example.sgmis_java.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
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

import com.bumptech.glide.Glide;
import com.example.sgmis_java.R;
import com.example.sgmis_java.api.Api;
import com.example.sgmis_java.api.service.DataService;
import com.example.sgmis_java.domain.pojo.Oplog;
import com.example.sgmis_java.domain.pojo.Pigeon;
import com.example.sgmis_java.echarts.EchartView;
import com.example.sgmis_java.utils.EchartOptionUtil;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView pigeonText = findViewById(R.id.home_text);
        pigeonText.setTextColor(Color.parseColor("#D91AD9"));

        // 设置按钮跳转
        LinearLayout pigeon = findViewById(R.id.pigeon);
        pigeon.setOnClickListener(l -> {
            Intent intent = new Intent(this, PigeonActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });

        LinearLayout sys = findViewById(R.id.sys);
        sys.setOnClickListener(l -> {
            Intent intent = new Intent(this, SysActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });


        // 设置banner
        Banner<Pigeon, BannerAdapter<Pigeon, BannerImageHolder>> banner = findViewById(R.id.banner);
        Api.execute(this,
                Api.creat(DataService.class).getPigeonPicture(),
                (data, msg) -> banner.setAdapter(new BannerImageAdapter<Pigeon>(data) {
                            @Override
                            public void onBindView(BannerImageHolder holder, Pigeon data, int position, int size) {
                                // 如何实现加载网络服务器远程图片
                                Glide.with(holder.itemView)
                                        .load(Api.baseUrl + Api.pigeon + data.getPictureUrl())
                                        .error(R.drawable.baseline_hourglass_empty_24)
                                        .into(holder.imageView);
                            }
                        })
                        .addBannerLifecycleObserver(this)
                        .setLoopTime(5000)
                        .setIndicator(new CircleIndicator(this))
        );

        // 设置基本数据
        TextView online = findViewById(R.id.online);
        TextView onlineGroup = findViewById(R.id.onlineGroup);
        TextView totalPigeon = findViewById(R.id.totalPigeon);
        TextView totalOplog = findViewById(R.id.totalOplog);

        Api.execute(this,
                Api.creat(DataService.class).getOnline(),
                (data, msg) -> online.setText(String.valueOf(data))
        );
        Api.execute(this,
                Api.creat(DataService.class).getOnlineGroup(),
                (data, msg) -> onlineGroup.setText(String.valueOf(data))
        );
        Api.execute(this,
                Api.creat(DataService.class).getPigeonCount(),
                (data, msg) -> totalPigeon.setText(String.valueOf(data.getTotal()))
        );
        Api.execute(this,
                Api.creat(DataService.class).getOplogCount(),
                (data, msg) -> totalOplog.setText(String.valueOf(data.getTotal()))
        );

        // 图表
        List<Object[]> createData = new ArrayList<>();
        List<Object[]> deleteData = new ArrayList<>();
        Api.execute(this, Api.creat(DataService.class).getCreate(), (data, msg) -> {
            LocalDate localDate = LocalDate.now().minusDays(29);
            for (int i = 0; i < 30; i++) {
                String format = localDate.format(DateTimeFormatter.ofPattern(Api.yyyyMMdd));
                createData.add(new Object[]{format, data.getOrDefault(localDate, 0)});
                localDate = localDate.plusDays(1);
            }
        });
        Api.execute(this, Api.creat(DataService.class).getDelete(), (data, msg) -> {
            Map<String, Integer> deleteMap = data
                    .stream()
                    .collect(Collectors.toMap(
                            item -> (String) item.get("time"),
                            item -> ((Double) Objects.requireNonNull(item.get("count"))).intValue()

                    ));
            LocalDate localDate = LocalDate.now().minusDays(29);
            for (int i = 0; i < 30; i++) {
                String format = localDate.format(DateTimeFormatter.ofPattern(Api.yyyyMMdd));
                deleteData.add(new Object[]{format, deleteMap.getOrDefault(format, 0)});
                localDate = localDate.plusDays(1);
            }
        });

        EchartView pigeonLineChart = findViewById(R.id.pigeonLine);
        pigeonLineChart.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pigeonLineChart.refreshEchartsWithOption(EchartOptionUtil.getPigeonLineOptions(createData, deleteData));
            }
        });

        // 操作数据
        List<List<Object[]>> contentMap = new ArrayList<>();
        Api.execute(this, Api.creat(DataService.class).getOplogLine(), (data, msg) -> {
            final int opNum = 9;
            Map<Integer, Map<String, Integer>> dataMap = new HashMap<>();
            for (int i = 0; i < opNum; i++) {
                dataMap.put(i, new HashMap<>());
                contentMap.add(new ArrayList<>());
            }
            data.forEach(item -> Objects.requireNonNull(dataMap.get(item.getContent()))
                    .put(
                            item.getTime().format(DateTimeFormatter.ofPattern(Api.yyyyMMdd)),
                            item.getCount()
                    ));
            LocalDate localDate = LocalDate.now().minusDays(29);
            for (int i = 0; i < 30; i++) {
                String format = localDate.format(DateTimeFormatter.ofPattern(Api.yyyyMMdd));
                for (int j = 0; j < opNum; j++) {
                    contentMap.get(j).add(
                            new Object[]{format,
                                    Objects.requireNonNull(dataMap.get(j))
                                            .getOrDefault(format, 0)}
                    );
                }
                localDate = localDate.plusDays(1);
            }
        });

        EchartView oplogLine = findViewById(R.id.oplogLine);
        oplogLine.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                oplogLine.refreshEchartsWithOption(EchartOptionUtil.getOplogUtilOptions(contentMap));
            }
        });

        ListView oplogData = findViewById(R.id.oplogData);
        DateTimeFormatter form = DateTimeFormatter.ofPattern(Api.yyyyMMddHHmmss);
        Api.execute(this, Api.creat(DataService.class).getOplogData(5), (data, msg) -> {
            ArrayAdapter<Oplog> adapter = new ArrayAdapter<Oplog>(this, R.layout.oplog_data_item, data) {
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

                    time.setText(item.getTime().format(form));
                    msg.setText(item.getTip());

                    return convertView;
                }
            };
            oplogData.setAdapter(adapter);
        });

        EchartView optionPie = findViewById(R.id.optionPie);
        Api.execute(this, Api.creat(DataService.class).getOptionPie(), (data, msg) -> {
            List<Map<String, Object>> countDataPie = new ArrayList<>();
            Map<String, Object> map;
            map = new HashMap<>();
            map.put("name", "新增");
            map.put("value", 0);
            countDataPie.add(map);
            map = new HashMap<>();
            map.put("name", "修改");
            map.put("value", 0);
            countDataPie.add(map);
            map = new HashMap<>();
            map.put("name", "删除");
            map.put("value", 0);
            countDataPie.add(map);
            map = new HashMap<>();
            map.put("name", "共享血统");
            map.put("value", 0);
            countDataPie.add(map);
            map = new HashMap<>();
            map.put("name", "接收血统");
            map.put("value", 0);
            countDataPie.add(map);
            map = new HashMap<>();
            map.put("name", "关联血亲");
            map.put("value", 0);
            countDataPie.add(map);
            map = new HashMap<>();
            map.put("name", "解除血亲");
            map.put("value", 0);
            countDataPie.add(map);
            map = new HashMap<>();
            map.put("name", "转移鸽棚巢");
            map.put("value", 0);
            countDataPie.add(map);
            map = new HashMap<>();
            map.put("name", "其他");
            map.put("value", 0);
            countDataPie.add(map);
            data.forEach(item -> countDataPie.get(item.getContent()).put("value", item.getCount()));
            optionPie.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    optionPie.refreshEchartsWithOption(EchartOptionUtil.getOptionPieUtilOptions(countDataPie));
                }
            });
        });
    }

    @Override
    protected void onDestroy() {

        TextView pigeonText = findViewById(R.id.home_text);
        pigeonText.setTextColor(Color.parseColor("#FFFFFF"));

        super.onDestroy();
    }
}
