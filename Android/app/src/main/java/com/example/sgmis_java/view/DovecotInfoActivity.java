package com.example.sgmis_java.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sgmis_java.R;
import com.example.sgmis_java.api.Api;
import com.example.sgmis_java.api.service.AddressService;
import com.example.sgmis_java.api.service.DovecotInfoService;
import com.example.sgmis_java.domain.pojo.Area;
import com.example.sgmis_java.domain.pojo.Gsxx;
import com.example.sgmis_java.domain.pojo.Provincial;
import com.example.sgmis_java.domain.pojo.Urban;
import com.example.sgmis_java.utils.MessageUtils;

import java.util.Objects;

public class DovecotInfoActivity extends AppCompatActivity {

    private ArrayAdapter<Provincial> provinceAdapter;
    private ArrayAdapter<Urban> urbanAdapter;
    private ArrayAdapter<Area> areaAdapter;

    private boolean init = false;

    private Long gid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dovecot_info);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText nameText = findViewById(R.id.name);
        Spinner provincialSpinner = findViewById(R.id.provincial);
        Spinner urbanSpinner = findViewById(R.id.urban);
        Spinner areaSpinner = findViewById(R.id.area);
        EditText addressText = findViewById(R.id.address);
        EditText yearsText = findViewById(R.id.years);
        EditText bloodlineText = findViewById(R.id.bloodline);
        EditText performanceText = findViewById(R.id.performance);
        Button save = findViewById(R.id.save);

        // 设置下拉框
        Api.execute(getApplication(), Api.creat(DovecotInfoService.class).getGsxx(), (data, msg) -> {
            String name = data.getName();
            String location = data.getLocation();
            String address = data.getAddress();
            Integer years = data.getYears();
            String bloodline = data.getBloodline();
            String performance = data.getPerformance();
            gid = data.getGid();

            nameText.setText(name);
            addressText.setText(address);
            yearsText.setText(String.valueOf(years));
            bloodlineText.setText(bloodline);
            performanceText.setText(performance);

            if (location == null || init) {
                return;
            }

            String[] locationSplit = location.split(",");

            Api.execute(getApplication(), Api.creat(AddressService.class).getProvincial(), (provincialList, provincialMsg) -> {
                provinceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provincialList);
                provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                provincialSpinner.setAdapter(provinceAdapter);
                provincialSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Provincial provincial = (Provincial) adapterView.getItemAtPosition(i);
                        Long provincialId = provincial.getId();

                        Api.execute(getApplication(), Api.creat(AddressService.class).getUrban(provincialId), (data, msg) -> {
                            urbanAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, data);
                            urbanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            urbanSpinner.setAdapter(urbanAdapter);

                            urbanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    Urban urban = (Urban) adapterView.getItemAtPosition(i);
                                    Long urbanId = urban.getId();

                                    Api.execute(getApplication(), Api.creat(AddressService.class).getAreas(urbanId), (data, msg) -> {
                                        areaAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, data);
                                        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        areaSpinner.setAdapter(areaAdapter);

                                        if (init) {
                                            return;
                                        }
                                        int areaAdapterCount = areaAdapter.getCount();
                                        for (int j = 0; j < areaAdapterCount; j++) {
                                            Area item = areaAdapter.getItem(j);
                                            if (item == null) {
                                                continue;
                                            }
                                            if (Objects.equals(item.getValue(), locationSplit[2])) {
                                                areaSpinner.setSelection(j);
                                                break;
                                            }
                                        }
                                        init = true;
                                    });
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                }
                            });

                            if (init) {
                                return;
                            }
                            int urbanAdapterCount = urbanAdapter.getCount();
                            for (int j = 0; j < urbanAdapterCount; j++) {
                                Urban item = urbanAdapter.getItem(j);
                                if (item == null) {
                                    continue;
                                }
                                if (Objects.equals(item.getValue(), locationSplit[1])) {
                                    urbanSpinner.setSelection(j);
                                    break;
                                }
                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

                if (init) {
                    return;
                }
                int urbanAdapterCount = provinceAdapter.getCount();
                for (int j = 0; j < urbanAdapterCount; j++) {
                    Provincial item = provinceAdapter.getItem(j);
                    if (item == null) {
                        continue;
                    }
                    if (Objects.equals(item.getValue(), locationSplit[0])) {
                        provincialSpinner.setSelection(j);
                        break;
                    }
                }
            });
        });

        // 设置保存
        save.setOnClickListener(l -> {
            String location = "";
            if (provincialSpinner != null && urbanSpinner != null && areaSpinner != null) {
                Provincial provincial = (Provincial) provincialSpinner.getSelectedItem();
                Urban urban = (Urban) urbanSpinner.getSelectedItem();
                Area area = (Area) areaSpinner.getSelectedItem();
                location = provincial + "," + urban + "," + area;
            }
            Api.execute(
                    this,
                    Api.creat(DovecotInfoService.class).postGsxx(
                            Gsxx.builder()
                                    .name(nameText.getText().toString())
                                    .years(Integer.valueOf(yearsText.getText().toString()))
                                    .address(addressText.getText().toString())
                                    .bloodline(bloodlineText.getText().toString())
                                    .performance(performanceText.getText().toString())
                                    .location(location)
                                    .build()
                    ),
                    (data, msg) -> {
                        MessageUtils.showToast(this, msg);
                        this.recreate();
                    });
        });
    }

    @Override
    protected void onDestroy() {
        init = false;
        super.onDestroy();
    }
}
