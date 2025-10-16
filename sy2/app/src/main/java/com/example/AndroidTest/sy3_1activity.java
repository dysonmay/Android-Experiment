package com.example.AndroidTest;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sy3_1activity extends AppCompatActivity {
    ListView listView;
    int[] imageArr = {R.drawable.lion,
            R.drawable.tiger,
            R.drawable.monkey,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.elephant};
    String[] titleArr = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};

    private static final String CHANNEL_ID = "animal_channel";
    private static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sy3_activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                // 请求权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        1001);
            }
        }
        //初始化视图
        listView = findViewById(R.id.listView);
        setupAdapter();
        notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel();
        setupListViewClickListener();
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Animal Channel";
            String description = "Channel for animal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setupAdapter(){
        List<Map<String,Object>> datalist =new ArrayList<>();

        for(int i=0;i<imageArr.length;i++){
            Map<String,Object> map=new HashMap<>();

            map.put("image",imageArr[i]);
            map.put("name",titleArr[i]);
            datalist.add(map);
        }

        String[] from={"image","name"};
        int[] to ={
                R.id.iv_image,
                R.id.tv_name
        };

        SimpleAdapter adapter =new SimpleAdapter(
                this,
                datalist,
                R.layout.sy3_list_item,
                from,
                to
        );

        listView.setAdapter(adapter);
    }


    private void setupListViewClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                // 先重置所有项的背景色
                for (int i = 0; i < parent.getChildCount(); i++) {
                    View child = parent.getChildAt(i);
                    if (child != null) {
                        child.setBackgroundColor(Color.TRANSPARENT);
                    }
                }

                // 高亮当前选中的项
                view.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));

                // 获取选中的动物名称
                String selectedAnimal = titleArr[position];

                // 显示Toast
                showToast(selectedAnimal);

                // 发送通知
                sendNotification(selectedAnimal);
            }
        });
    }

    private void showToast(String animalName){
        String toastMessage=animalName;
        //创建toast
        Toast.makeText(this,toastMessage,Toast.LENGTH_SHORT).show();
    }

    private void sendNotification(String animalName){
        // 创建点击通知后的Intent
        Intent intent = new Intent(this, sy3_1activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // 构建通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // 应用程序图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                .setContentTitle(animalName) // 列表项内容作为标题
                .setContentText("You selected the " + animalName + " from the list") // 通知内容
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("You selected the " + animalName + " from the animal list. This is a notification example for Android development practice."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true); // 点击后自动消失

        // 显示通知
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}