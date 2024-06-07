package com.example.sgmis_java.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.sgmis_java.api.service.OptionService;
import com.example.sgmis_java.domain.pojo.Xxpz;

public class OptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView optionList = findViewById(R.id.optionList);
        Api.execute(
                this,
                Api.creat(OptionService.class).getAllByType("yspz"),
                (data, msg) -> optionList.setAdapter(new ArrayAdapter<Xxpz>(getApplication(), R.layout.option_item, data) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                        if (convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.option_item, parent, false);
                        }

                        Xxpz item = getItem(position);

                        if (item == null) {
                            return LayoutInflater.from(getContext()).inflate(R.layout.option_item, parent, false);
                        }

                        TextView operation = convertView.findViewById(R.id.operation);
                        TextView author = convertView.findViewById(R.id.author);

                        operation.setText(item.getName());
                        author.setText(item.getAuthor());

                        return convertView;
                    }
                })
        );
    }
}
