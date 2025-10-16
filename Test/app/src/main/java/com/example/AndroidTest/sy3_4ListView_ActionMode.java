package com.example.AndroidTest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sy3_4ListView_ActionMode extends AppCompatActivity {

    private ListView listView;
    private SimpleAdapter adapter; // 改为 SimpleAdapter
    private List<Map<String, Object>> dataList;
    private ActionMode actionMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sy3_activity_main);
        initData();
        initListView();
        setupAdapter();
    }

    private void setupAdapter(){
        // 创建 SimpleAdapter
        adapter = new SimpleAdapter(
                this,
                dataList,
                R.layout.sy3_action_mode_list_item, // 使用自定义的列表项布局
                new String[]{"image", "name"},     // 数据中的键名
                new int[]{R.id.imageview,R.id.textView} // 布局中对应的视图ID
        );
        listView.setAdapter(adapter);
    }

    public void initData(){
        dataList = new ArrayList<>();
        int commonImage=R.drawable.android;
        String[] englishNumbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
                "eighteen", "nineteen", "twenty"};
        for(int i = 1; i <= 20; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("image", commonImage); // 绑定到图片
            map.put("name", englishNumbers[i-1]);    // 绑定到文本
            dataList.add(map);
        }
    }

    private void initListView(){
        listView = findViewById(R.id.listView);

        // ActionMode 设置
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            // 用户选择其他项 触发计数
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                int selectedCount = listView.getCheckedItemCount();

                View listItem = listView.getChildAt(position - listView.getFirstVisiblePosition());
                if(listItem != null){
                    if(checked){
                        listItem.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    }
                    else{
                        listItem.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
                View customView = mode.getCustomView();
                if(customView != null){
                    android.widget.TextView tv = customView.findViewById(R.id.tvSelectedCount);
                    if(tv != null){
                        tv.setText("已选择 " + selectedCount + " 项");
                    }
                }
                mode.setTitle(selectedCount + " 已选择");
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.am_test1) {
                    Toast.makeText(sy3_4ListView_ActionMode.this, "菜单1被点击", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                }
                return false;
            }

            // 用户长按列表项触发 显示菜单
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                View customView = getLayoutInflater().inflate(R.layout.sy3_action_mode_layout, null);
                mode.setCustomView(customView);

                mode.getMenuInflater().inflate(R.menu.sy3_action_mode_menu, menu);
                actionMode = mode;
                return true;
            }

            // 清理资源
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                actionMode = null;
                // 恢复所有项的背景色
                for (int i = 0; i < listView.getChildCount(); i++) {
                    View child = listView.getChildAt(i);
                    if (child != null) {
                        child.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode == null) {
                    // 显示项目名称而不是整个Map
                    String itemName = (String) dataList.get(position).get("name");
                    Toast.makeText(sy3_4ListView_ActionMode.this,
                            "点击: " + itemName, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}