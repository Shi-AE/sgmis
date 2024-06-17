package com.example.sgmis_java.view;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sgmis_java.R;
import com.example.sgmis_java.api.Api;
import com.example.sgmis_java.api.service.StatisticService;
import com.example.sgmis_java.echarts.EchartView;
import com.github.abel533.echarts.Label;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.RoseType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.itemstyle.Emphasis;

import java.util.HashMap;
import java.util.Map;

public class StatisticActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EchartView ysPie = findViewById(R.id.ysPie);
        EchartView yanPie = findViewById(R.id.yanPie);

        Api.execute(this, Api.creat(StatisticService.class).getYsData(), (data, msg) -> {

            GsonOption option = new GsonOption();
            option.title("眼色玫瑰图")
                    .legend(new Legend()
                            .top(Y.bottom)
                    )
                    .toolbox(new Toolbox()
                            .show(true)
                    )
                    .tooltip(new Tooltip()
                            .trigger(Trigger.item)
                    )
                    .series(new Pie()
                            .radius(50, 200)
                            .center("50%", "50%")
                            .roseType(RoseType.area)
                            .label(new ItemStyle()
                                    .emphasis(new Emphasis()
                                            .label(new Label()
                                                    .position(Position.outer)
                                                    .margin(0)
                                            )
                                    )
                            )
                            .data(data.stream()
                                    .map(item -> {
                                        Map<String, Object> map = new HashMap<>();
                                        map.put("value", item.getCount());
                                        map.put("name", item.getYs());
                                        return map;
                                    }).toArray())
                    );

            ysPie.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    ysPie.refreshEchartsWithOption(option);
                }
            });
        });

        Api.execute(this, Api.creat(StatisticService.class).getYanData(), (data, msg) -> {

            GsonOption option = new GsonOption();
            option.title("眼色玫瑰图")
                    .legend(new Legend()
                            .top(Y.bottom)
                    )
                    .toolbox(new Toolbox()
                            .show(true)
                    )
                    .tooltip(new Tooltip()
                            .trigger(Trigger.item)
                    )
                    .series(new Pie()
                            .radius(50, 200)
                            .center("50%", "50%")
                            .roseType(RoseType.area)
                            .label(new ItemStyle()
                                    .emphasis(new Emphasis()
                                            .label(new Label()
                                                    .position(Position.outer)
                                                    .margin(0)
                                            )
                                    )
                            )
                            .data(data.stream()
                                    .map(item -> {
                                        Map<String, Object> map = new HashMap<>();
                                        map.put("value", item.getCount());
                                        map.put("name", item.getYan());
                                        return map;
                                    }).toArray())
                    );

            yanPie.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    yanPie.refreshEchartsWithOption(option);
                }
            });
        });
    }
}
