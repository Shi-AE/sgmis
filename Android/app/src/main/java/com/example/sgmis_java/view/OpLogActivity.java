package com.example.sgmis_java.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
import com.example.sgmis_java.api.service.OplogService;
import com.example.sgmis_java.domain.pojo.Oplog;
import com.example.sgmis_java.domain.vo.PagingConditionVo;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OpLogActivity extends AppCompatActivity {

    private int current = 0;
    private int pages = Integer.MAX_VALUE;
    private final List<Oplog> oplogList = new ArrayList<>();

    private ListView opList;
    private boolean isLoading = false;

    private final String[] contentOptions = new String[]{"新增", "修改", "删除", "共享血统", "接收血统", "关联血亲", "解除血亲", "转移鸽棚巢箱", "其他"};

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Api.yyyyMMddHHmmss);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op_log);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        opList = findViewById(R.id.opList);

        opList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }
            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (!isLoading && current < pages && (firstVisibleItem + visibleItemCount >= totalItemCount)) {
                    isLoading = true;
                    Api.execute(
                            getApplication(),
                            Api.creat(OplogService.class).getConditionPage(
                                    PagingConditionVo
                                            .builder()
                                            .current(current + 1)
                                            .pageSize(10)
                                            .build()
                            ),
                            (data, msg) -> {
                                current = data.getCurrent();
                                pages = data.getPages();

                                oplogList.addAll(data.getRecords());

                                opList.setAdapter(new ArrayAdapter<Oplog>(getApplication(), R.layout.oplog_item, oplogList) {
                                    @NonNull
                                    @Override
                                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                                        if (convertView == null) {
                                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.oplog_item, parent, false);
                                        }

                                        Oplog item = getItem(position);

                                        if (item == null) {
                                            return LayoutInflater.from(getContext()).inflate(R.layout.oplog_item, parent, false);
                                        }

                                        TextView content = convertView.findViewById(R.id.content);
                                        TextView tip = convertView.findViewById(R.id.tip);
                                        TextView ringNumber = convertView.findViewById(R.id.ringNumber);
                                        TextView time = convertView.findViewById(R.id.time);
                                        TextView author = convertView.findViewById(R.id.author);

                                        content.setText(contentOptions[item.getContent()]);
                                        tip.setText(item.getTip());
                                        ringNumber.setText(item.getRingNumber());
                                        time.setText(item.getTime().format(formatter));
                                        author.setText(item.getAuthor());

                                        return convertView;
                                    }
                                });

                                opList.setSelection(firstVisibleItem);

                                isLoading = false;
                            }
                    );
                }
            }
        });
    }
}
