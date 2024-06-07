package com.example.sgmis_java.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.sgmis_java.api.service.UserService;
import com.example.sgmis_java.domain.dto.AdminUserDTO;
import com.example.sgmis_java.domain.pojo.User;
import com.example.sgmis_java.utils.MessageUtils;

public class AdminActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView userList = findViewById(R.id.userList);
        String[] states = new String[]{"离线", "在线", "活跃"};
        Api.execute(
                this,
                Api.creat(UserService.class).getUserList(),
                (data, msg) -> userList.setAdapter(new ArrayAdapter<AdminUserDTO>(this, R.layout.user_list_item, data) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                        if (convertView == null) {
                            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_list_item, parent, false);
                        }

                        AdminUserDTO item = getItem(position);

                        if (item == null) {
                            return LayoutInflater.from(getContext()).inflate(R.layout.user_list_item, parent, false);
                        }

                        TextView account = convertView.findViewById(R.id.account);
                        TextView state = convertView.findViewById(R.id.state);
                        TextView admin = convertView.findViewById(R.id.admin);
                        Button reset = convertView.findViewById(R.id.reset);
                        Button delete = convertView.findViewById(R.id.delete);

                        String accountValue = item.getAccount();
                        Integer stateValue = item.getState();
                        Boolean adminValue = item.getAdmin();

                        account.setText(accountValue);
                        state.setText(states[stateValue]);
                        admin.setText(adminValue ? "管理员" : "成员");
                        Long id = item.getId();
                        Long gid = item.getGid();

                        reset.setOnClickListener(l -> {
                            if (adminValue) {
                                MessageUtils.showToast(getContext(), "无法修改管理员信息");
                                return;
                            }
                            MessageUtils.showConfirmationDialog(
                                    getContext(),
                                    "重置成员密码",
                                    "确认要重置成员" + accountValue + "的密码吗？\n" +
                                            "重置后密码初始值为“123456”\n" +
                                            "成功后请尽快更换密码！",
                                    (dialog, which) -> Api.execute(
                                            getContext(),
                                            Api.creat(UserService.class).resetPassword(
                                                    User.builder()
                                                            .id(id)
                                                            .gid(gid)
                                                            .build()
                                            ),
                                            (data, msg) -> MessageUtils.showToast(getApplication(), msg)
                                    )
                            );
                        });

                        delete.setOnClickListener(l -> {
                            if (adminValue) {
                                MessageUtils.showToast(getContext(), "无法修改管理员信息");
                                return;
                            }
                            MessageUtils.showConfirmationDialog(
                                    getContext(),
                                    "删除成员",
                                    "确认要删除成员" + accountValue + "吗？",
                                    (dialog, which) -> Api.execute(
                                            getContext(),
                                            Api.creat(UserService.class).deleteUser(id),
                                            (data, msg) -> {
                                                MessageUtils.showToast(getApplication(), msg);
                                                ((Activity) getContext()).recreate();
                                            }
                                    )
                            );
                        });

                        return convertView;
                    }
                })
        );
    }
}
