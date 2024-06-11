package com.example.sgmis_java.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sgmis_java.R;
import com.example.sgmis_java.api.Api;
import com.example.sgmis_java.api.service.OptionService;
import com.example.sgmis_java.api.service.PigeonService;
import com.example.sgmis_java.domain.pojo.Pigeon;
import com.example.sgmis_java.domain.pojo.Xxpz;
import com.example.sgmis_java.utils.MessageUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cn.hutool.core.util.StrUtil;

public class PigeonQuickEditActivity extends AppCompatActivity {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    private ArrayAdapter<Pigeon> pigeonAdapter;

    private final Map<String, Long> fatherIdMap = new HashMap<>();
    private final Map<String, Long> matherIdMap = new HashMap<>();
    private final List<Pigeon> quickDataList = new ArrayList<>();

    private ArrayAdapter<String> yspzAdapter;
    private ArrayAdapter<String> yanpzAdapter;
    private ArrayAdapter<String> jbpzAdapter;
    private ArrayAdapter<String> lxpzAdapter;
    private ArrayAdapter<String> stateAdapter;
    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> countryAdapter;
    private ArrayAdapter<String> isGradeyAdapter;
    private ArrayAdapter<String> sexAdapter;
    private ArrayAdapter<Integer> yearAdapter;

    private List<String> yspzList;
    private List<String> yanpzList;
    private List<String> jbpzList;
    private List<String> lxpzList;
    private List<String> stateList;
    private List<String> provinceList;
    private List<String> countryList;
    private List<Integer> yearList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pigeon_quick_edit);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AutoCompleteTextView fatherItem = findViewById(R.id.fatherItem);
        AutoCompleteTextView matherItem = findViewById(R.id.matherItem);
        ListView quickList = findViewById(R.id.quickList);

        // 设置搜索下拉框
        fatherItem.addTextChangedListener(new TextWatcher() {
            @Override // 改变后
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // 改变时
            public void onTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // 改变后
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                if (StrUtil.isBlank(s)) {
                    return;
                }
                Api.execute(
                        getApplication(),
                        Api.creat(PigeonService.class)
                                .searchPigeon("father", StrUtil.replace(s, "/", "")),
                        (data, msg) -> {
                            fatherIdMap.putAll(data
                                    .stream()
                                    .collect(Collectors.toMap(
                                            Pigeon::getRingNumber,
                                            Pigeon::getId
                                    )));
                            fatherItem.setAdapter(new ArrayAdapter<>(
                                    getApplication(),
                                    android.R.layout.simple_dropdown_item_1line,
                                    data
                            ));
                        }
                );
            }
        });

        matherItem.addTextChangedListener(new TextWatcher() {
            @Override // 改变后
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // 改变时
            public void onTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // 改变后
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                if (StrUtil.isBlank(s)) {
                    return;
                }
                Api.execute(
                        getApplication(),
                        Api.creat(PigeonService.class)
                                .searchPigeon("mather", StrUtil.replace(s, "/", "")),
                        (data, msg) -> {
                            matherIdMap.putAll(data
                                    .stream()
                                    .collect(Collectors.toMap(
                                            Pigeon::getRingNumber,
                                            Pigeon::getId
                                    )));
                            matherItem.setAdapter(new ArrayAdapter<>(
                                    getApplication(),
                                    android.R.layout.simple_dropdown_item_1line,
                                    data
                            ));
                        }
                );
            }
        });

        // 创建线程计数器
        final int numberOfTasks = 7;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfTasks);

        // 获取选项数据
        yspzAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        yanpzAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        jbpzAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        lxpzAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        provinceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);
        yspzList = new ArrayList<>();
        yanpzList = new ArrayList<>();
        jbpzList = new ArrayList<>();
        lxpzList = new ArrayList<>();
        stateList = new ArrayList<>();
        provinceList = new ArrayList<>();
        countryList = new ArrayList<>();
        yearList = new ArrayList<>();
        sexAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"雌", "雄"}
        );
        isGradeyAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"是", "否"}
        );
        yearAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line
        );
        executor.submit(() ->
                Api.execute(this, Api.creat(OptionService.class).getAllByType("yspz"), (data, msg) -> {
                    yspzList = data.stream().map(Xxpz::getName).collect(Collectors.toList());
                    yspzAdapter = new ArrayAdapter<>(
                            getApplication(),
                            android.R.layout.simple_dropdown_item_1line,
                            yspzList
                    );
                    countDownLatch.countDown();
                    Log.d("Api", "yspz 已执行,剩余线程: " + countDownLatch.getCount());
                })
        );
        executor.submit(() ->
                Api.execute(this, Api.creat(OptionService.class).getAllByType("yanpz"), (data, msg) -> {
                    yanpzList = data.stream().map(Xxpz::getName).collect(Collectors.toList());
                    yanpzAdapter = new ArrayAdapter<>(
                            getApplication(),
                            android.R.layout.simple_dropdown_item_1line,
                            yanpzList
                    );
                    countDownLatch.countDown();
                    Log.d("Api", "yanpz 已执行,剩余线程: " + countDownLatch.getCount());
                })
        );
        executor.submit(() ->
                Api.execute(this, Api.creat(OptionService.class).getAllByType("jbpz"), (data, msg) -> {
                    jbpzList = data.stream().map(Xxpz::getName).collect(Collectors.toList());
                    jbpzAdapter = new ArrayAdapter<>(
                            getApplication(),
                            android.R.layout.simple_dropdown_item_1line,
                            jbpzList
                    );
                    countDownLatch.countDown();
                    Log.d("Api", "jbpz 已执行,剩余线程: " + countDownLatch.getCount());
                })
        );
        executor.submit(() ->
                Api.execute(this, Api.creat(OptionService.class).getAllByType("lxpz"), (data, msg) -> {
                    lxpzList = data.stream().map(Xxpz::getName).collect(Collectors.toList());
                    lxpzAdapter = new ArrayAdapter<>(
                            getApplication(),
                            android.R.layout.simple_dropdown_item_1line,
                            lxpzList
                    );
                    countDownLatch.countDown();
                    Log.d("Api", "lxpz 已执行,剩余线程: " + countDownLatch.getCount());
                })
        );
        executor.submit(() ->
                Api.execute(this, Api.creat(OptionService.class).getAllByType("state"), (data, msg) -> {
                    stateList = data.stream().map(Xxpz::getName).collect(Collectors.toList());
                    stateAdapter = new ArrayAdapter<>(
                            getApplication(),
                            android.R.layout.simple_dropdown_item_1line,
                            stateList
                    );
                    countDownLatch.countDown();
                    Log.d("Api", "state 已执行,剩余线程: " + countDownLatch.getCount());
                })
        );
        executor.submit(() ->
                Api.execute(this, Api.creat(OptionService.class).getAllByType("province"), (data, msg) -> {
                    provinceList = data.stream().map(Xxpz::getName).collect(Collectors.toList());
                    provinceAdapter = new ArrayAdapter<>(
                            getApplication(),
                            android.R.layout.simple_dropdown_item_1line,
                            provinceList
                    );
                    countDownLatch.countDown();
                    Log.d("Api", "province 已执行,剩余线程: " + countDownLatch.getCount());
                })
        );
        executor.submit(() ->
                Api.execute(this, Api.creat(OptionService.class).getAllByType("country"), (data, msg) -> {
                    countryList = data.stream().map(Xxpz::getName).collect(Collectors.toList());
                    countryAdapter = new ArrayAdapter<>(
                            getApplication(),
                            android.R.layout.simple_dropdown_item_1line,
                            countryList
                    );
                    countDownLatch.countDown();
                    Log.d("Api", "country已执行,剩余线程: " + countDownLatch.getCount());
                })
        );
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        yearList = IntStream.rangeClosed(0, 99)
                .mapToObj(i -> currentYear - i)
                .collect(Collectors.toList());
        yearAdapter = new ArrayAdapter<>(
                getApplication(),
                android.R.layout.simple_dropdown_item_1line,
                yearList
        );


        executor.submit(() -> {
            // 等待线程计数器执行完毕
            try {
                boolean await = countDownLatch.await(10, TimeUnit.SECONDS);
                Log.d("await", "线程等待结束");
                if (!await) {
                    MessageUtils.showToast(this, "选项获取失败请重试");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            new Handler(Looper.getMainLooper()).post(() -> {
                // 设置上传数据表
                quickDataList.add(
                        new Pigeon()
                );

                pigeonAdapter = new ArrayAdapter<Pigeon>(this, R.layout.quick_data_item, quickDataList) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                        if (convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.quick_data_item, parent, false);
                        }

                        Spinner countrySpinner = convertView.findViewById(R.id.country);
                        Spinner yearSpinner = convertView.findViewById(R.id.year);
                        Spinner provinceSpinner = convertView.findViewById(R.id.province);
                        EditText codeEditText = convertView.findViewById(R.id.code);
                        Spinner sexSpinner = convertView.findViewById(R.id.sex);
                        Spinner yanSpinner = convertView.findViewById(R.id.yan);
                        Spinner ysSpinner = convertView.findViewById(R.id.ys);
                        Spinner lxSpinner = convertView.findViewById(R.id.lx);
                        Spinner stateSpinner = convertView.findViewById(R.id.state);
                        Spinner jbSpinner = convertView.findViewById(R.id.jb);
                        Spinner isGradeSpinner = convertView.findViewById(R.id.isGrade);

                        countrySpinner.setAdapter(countryAdapter);
                        provinceSpinner.setAdapter(provinceAdapter);
                        yanSpinner.setAdapter(yanpzAdapter);
                        ysSpinner.setAdapter(yspzAdapter);
                        lxSpinner.setAdapter(lxpzAdapter);
                        stateSpinner.setAdapter(stateAdapter);
                        jbSpinner.setAdapter(jbpzAdapter);
                        isGradeSpinner.setAdapter(isGradeyAdapter);
                        sexSpinner.setAdapter(sexAdapter);
                        yearSpinner.setAdapter(yearAdapter);

                        Pigeon item = getItem(position);

                        assert item != null;
                        String country = item.getCountry();
                        String province = item.getProvince();
                        String yan = item.getYan();
                        String ys = item.getYs();
                        String lx = item.getLx();
                        String state = item.getState();
                        String jb = item.getJb();
                        String isGrade = item.getIsGrade();
                        String sex = item.getSex();
                        String year = item.getYear();
                        String code = item.getCode();

                        if (StrUtil.isNotBlank(country)) {
                            for (int i = 0; i < countryList.size(); i++) {
                                if (Objects.equals(countryList.get(i), country)) {
                                    countrySpinner.setSelection(i);
                                }
                            }
                        }

                        if (StrUtil.isNotBlank(province)) {
                            for (int i = 0; i < provinceList.size(); i++) {
                                if (Objects.equals(provinceList.get(i), province)) {
                                    provinceSpinner.setSelection(i);
                                }
                            }
                        }

                        if (StrUtil.isNotBlank(yan)) {
                            for (int i = 0; i < yanpzList.size(); i++) {
                                if (Objects.equals(yanpzList.get(i), yan)) {
                                    yanSpinner.setSelection(i);
                                }
                            }
                        }

                        if (StrUtil.isNotBlank(ys)) {
                            for (int i = 0; i < yspzList.size(); i++) {
                                if (Objects.equals(yspzList.get(i), ys)) {
                                    ysSpinner.setSelection(i);
                                }
                            }
                        }

                        if (StrUtil.isNotBlank(lx)) {
                            for (int i = 0; i < lxpzList.size(); i++) {
                                if (Objects.equals(lxpzList.get(i), lx)) {
                                    lxSpinner.setSelection(i);
                                }
                            }
                        }

                        if (StrUtil.isNotBlank(state)) {
                            for (int i = 0; i < stateList.size(); i++) {
                                if (Objects.equals(stateList.get(i), state)) {
                                    stateSpinner.setSelection(i);
                                }
                            }
                        }

                        if (StrUtil.isNotBlank(jb)) {
                            for (int i = 0; i < jbpzList.size(); i++) {
                                if (Objects.equals(jbpzList.get(i), jb)) {
                                    jbSpinner.setSelection(i);
                                }
                            }
                        }

                        if (StrUtil.isNotBlank(year)) {
                            for (int i = 0; i < yearList.size(); i++) {
                                if (Objects.equals(String.valueOf(yearList.get(i)), year)) {
                                    yearSpinner.setSelection(i);
                                }
                            }
                        }

                        if (StrUtil.isNotBlank(isGrade)) {
                            if (isGrade.equals("雌")) {
                                sexSpinner.setSelection(0);
                            } else {
                                sexSpinner.setSelection(1);
                            }
                        }

                        if (StrUtil.isNotBlank(sex)) {
                            if (sex.equals("是")) {
                                isGradeSpinner.setSelection(0);
                            } else {
                                isGradeSpinner.setSelection(1);
                            }
                        }

                        if (StrUtil.isNotBlank(code)) {
                            codeEditText.setText(code);
                        }

                        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String s = (String) adapterView.getItemAtPosition(i);
                                quickDataList.get(position).setCountry(s);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                quickDataList.get(position).setCountry(null);
                            }
                        });

                        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                Integer s = (Integer) adapterView.getItemAtPosition(i);
                                quickDataList.get(position).setYear(String.valueOf(s));
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                quickDataList.get(position).setYear(null);
                            }
                        });

                        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String s = (String) adapterView.getItemAtPosition(i);
                                quickDataList.get(position).setProvince(s);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                quickDataList.get(position).setProvince(null);
                            }
                        });

                        sexSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String s = (String) adapterView.getItemAtPosition(i);
                                quickDataList.get(position).setSex(s);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                quickDataList.get(position).setSex(null);
                            }
                        });

                        yanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String s = (String) adapterView.getItemAtPosition(i);
                                quickDataList.get(position).setYan(s);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                quickDataList.get(position).setYan(null);
                            }
                        });

                        ysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String s = (String) adapterView.getItemAtPosition(i);
                                quickDataList.get(position).setYs(s);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                quickDataList.get(position).setYs(null);
                            }
                        });

                        lxSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String s = (String) adapterView.getItemAtPosition(i);
                                quickDataList.get(position).setLx(s);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                quickDataList.get(position).setLx(null);
                            }
                        });

                        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String s = (String) adapterView.getItemAtPosition(i);
                                quickDataList.get(position).setState(s);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                quickDataList.get(position).setState(null);
                            }
                        });

                        jbSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String s = (String) adapterView.getItemAtPosition(i);
                                quickDataList.get(position).setJb(s);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                quickDataList.get(position).setJb(null);
                            }
                        });

                        isGradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String s = (String) adapterView.getItemAtPosition(i);
                                quickDataList.get(position).setIsGrade(s);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                quickDataList.get(position).setIsGrade(null);
                            }
                        });

                        codeEditText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                String s = editable.toString();
                                quickDataList.get(position).setCode(s);
                            }
                        });

                        Button copy = convertView.findViewById(R.id.copy);
                        copy.setOnClickListener(l -> {
                            Pigeon pigeonNew = new Pigeon();
                            Pigeon pigeonOld = quickDataList.get(position);
                            pigeonNew.setCountry(pigeonOld.getCountry());
                            pigeonNew.setProvince(pigeonOld.getProvince());
                            pigeonNew.setYan(pigeonOld.getYan());
                            pigeonNew.setYs(pigeonOld.getYs());
                            pigeonNew.setLx(pigeonOld.getLx());
                            pigeonNew.setState(pigeonOld.getState());
                            pigeonNew.setJb(pigeonOld.getJb());
                            pigeonNew.setIsGrade(pigeonOld.getIsGrade());
                            pigeonNew.setSex(pigeonOld.getSex());
                            pigeonNew.setYear(pigeonOld.getYear());
                            pigeonNew.setCode(pigeonOld.getCode());
                            quickDataList.add(position, pigeonNew);
                            pigeonAdapter.notifyDataSetChanged();
                        });

                        Button delete = convertView.findViewById(R.id.delete);
                        delete.setOnClickListener(l -> {
                            quickDataList.remove(position);
                            pigeonAdapter.notifyDataSetChanged();
                        });

                        return convertView;
                    }
                };

                quickList.setAdapter(pigeonAdapter);

                Button add = findViewById(R.id.add);

                add.setOnClickListener(l -> {
                    quickDataList.add(0, new Pigeon());
                    pigeonAdapter.notifyDataSetChanged();
                });
            });
        });

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(l -> {
            Long fid = fatherIdMap.get(fatherItem.getText().toString());
            Long mid = matherIdMap.get(matherItem.getText().toString());
            if (fid == null && mid == null) {
                MessageUtils.showToast(this, "必须选择一个父代");
                return;
            }

            for (int i = 0; i < quickDataList.size(); i++) {
                Pigeon pigeon = quickDataList.get(i);
                if (!StrUtil.isAllNotBlank(
                        pigeon.getCountry(),
                        pigeon.getCode(),
                        pigeon.getProvince(),
                        pigeon.getYear()
                )) {
                    MessageUtils.showToast(this, "必需填脚环的组成信息");
                    return;
                }
            }

            MessageUtils.showConfirmationDialog(
                    PigeonQuickEditActivity.this,
                    "上传鸽子信息",
                    "确认鸽子信息",
                    (dialog, which) -> {
                        Map<String, Object> map = new HashMap<>();
                        if (fid != null) {
                            map.put("fid", String.valueOf(fid));
                        }
                        if (mid != null) {
                            map.put("mid", String.valueOf(mid));
                        }
                        quickDataList.forEach(pigeon -> {
                            pigeon.setRingNumber(
                                    pigeon.getCountry().split("/")[1] + "-" +
                                            pigeon.getYear() + "-" +
                                            pigeon.getProvince().split("/")[1] + "-" +
                                            pigeon.getCode()
                            );
                        });
                        map.put("pigeons", quickDataList);

                        Api.execute(
                                PigeonQuickEditActivity.this,
                                Api.creat(PigeonService.class).rapidAddPigeon(map),
                                (data, msg) -> MessageUtils.showToast(PigeonQuickEditActivity.this, msg)
                        );
                    }
            );

        });
    }
}
