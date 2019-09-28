package com.example.moviecatalogue2.View.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.moviecatalogue2.R;
import com.example.moviecatalogue2.Service.Notification;
import com.example.moviecatalogue2.Service.Prefences;
import com.example.moviecatalogue2.Service.ReleaseReminder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.switch2) Switch switch2;
    private String TAG = "oke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ButterKnife.bind(this);

        final Notification notification = new Notification();
        final ReleaseReminder releaseReminder = new ReleaseReminder();

        switch1.setChecked(Prefences.getDaily(getApplicationContext()));
        switch2.setChecked(Prefences.getRelease(getApplicationContext()));

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    notification.setRepeatingNotification(getApplicationContext(), Notification.TYPE_7, "Testing notifikasi");
                    Prefences.setDaily(getApplicationContext(), true);
                } else {
                    Prefences.setDaily(getApplicationContext(), false);
                }
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    releaseReminder.StartReminder(getApplicationContext());
                    Prefences.setRelease(getApplicationContext(), true);
                } else {
                    Prefences.setRelease(getApplicationContext(), false);
                }
            }
        });
    }
}
