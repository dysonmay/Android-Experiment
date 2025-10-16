<h1>Android 布局实验报告</h1></br>
<h2>实验二概述</h2></br>
本实验通过四个不同的XML布局文件，展示了Android开发中四种常用布局方式的使用方法和特点，包括LinearLayout、TableLayout和ConstraintLayout。</br>


<h3>linearlayout.xml - 网格文本展示</h3></br>
在垂直方向上使用LinearLayout,每行用4个水平的LinearLayout组成一个4*4网格，在网格中添加16个TextView</br>
通过layout_weight属性控制列宽比例，使用margin控制元素间距，利用多重嵌套实现网格效果。</br>

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_weight="0.5"
        android:text="One,Two"
        android:textSize="20sp" />
</LinearLayout>
```
<h3>tablelayout.xml - 应用程序菜单</h3></br>
使用TableLayout作为根布局，添加了TextView显示菜单项和快捷键，利用View调整粗细作为菜单栏的分割线。</br>
使用layout_weight控制列宽比例,利用layout_span实现单元格合并，使用divider属性添加分隔线</br>

```xml
<TableRow>
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:layout_span="2"
        android:text="Hello TableLayout" />
</TableRow>

<TableRow>
<TextView
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_weight="1.5"
    android:text="Open..." />
<TextView
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_weight="0.25"
    android:gravity="right"
    android:text="Ctrl-o" />
</TableRow>
```
<h3>constraintlayout.xml -计算界面</h3></br>
完全使用约束关系定位，无嵌套布局,其中按钮通过按钮通过chain方式实现等间距排列，使用margin控制按钮间距</br>
约束按钮示例：

```xml
<Button
    android:id="@+id/button5"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="7"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/textView3"
    app:layout_constraintEnd_toStartOf="@+id/button6"
    app:layout_constraintBottom_toTopOf="@+id/button9" />
```

<h3>constraintlayout2.xml - 太空旅行应用界面</h3></br>
这是一个模拟太空旅行预订应用的复杂界面，采用垂直方向的LinearLayout作为根布局，内部嵌套了多个布局组件。</br>
使用了LinearLayout作为垂直方向的根布局。</br>
第一个水平的LinearLayout中包含3个垂直的LinearLayout，每个区域包含一个ImageView和一个TextView</br>
接着添加了一个水平的空白LinearLayout作为分割。</br>
中间区域利用ConstraintLayout，DCA-MARS转换区域嵌套ConstraintLayout，包含了两个绿色背景的TextView (DCA和MRAS)
以及中间的双箭头ImageView。</br>
旅行设置区域运用了Switch组件，添加了多个ImageView。</br>
底部按钮最后嵌套了一个LinearLayout，绿色背景包含DEPART文字。</br>
LinearLayout (垂直)</br>
├── LinearLayout (水平) - 顶部图标菜单</br>
├── ConstraintLayout - 主要内容区域</br>
│   ├── ConstraintLayout - DCA/MRAS 切换区域</br>
│   ├── Switch - 单程/往返选择</br>
│   ├── ImageView - 银河背景</br>
│   └── ImageView - 火箭图标</br>
└── LinearLayout - 底部出发按钮</br>

<h2>实验三概述</h2>
<h3>ListView动物列表与通知功能</h3></br>
数据初始化：

```java
int[] imageArr = {R.drawable.lion, R.drawable.tiger, ...};
String[] titleArr = {"Lion", "Tiger", "Monkey", ...};
```
适配器设置：

```java
private void setupAdapter(){
List<Map<String,Object>> datalist = new ArrayList<>();
for(int i=0;i<imageArr.length;i++){
Map<String,Object> map=new HashMap<>();
map.put("image",imageArr[i]);
map.put("name",titleArr[i]);
datalist.add(map);
}
// 使用SimpleAdapter绑定数据
}
```
列表项点击处理：

```java
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
// 重置背景色
for (int i = 0; i < parent.getChildCount(); i++) {
parent.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
}
// 高亮当前项
view.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
// 显示Toast和通知
showToast(selectedAnimal);
sendNotification(selectedAnimal);
}
});
```
通知功能实现：

```java
private void sendNotification(String animalName){
NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
.setSmallIcon(R.drawable.ic_launcher_foreground)
.setContentTitle(animalName)
.setContentText("You selected the " + animalName + " from the list")
.setAutoCancel(true);
notificationManager.notify(NOTIFICATION_ID, builder.build());
}
```
布局文件:</br>
sy3_activity_main.xml：主布局，包含 ListView</br>
sy3_list_item.xml：列表项布局，包含图片和文字

<h3>自定义对话框</h3>
核心代码对话框创建：

```java
private void showCustomAlertDialog() {
LayoutInflater inflater= LayoutInflater.from(this);
View dialogView=inflater.inflate(R.layout.sy3_alter_dialog,null);

    AlertDialog.Builder builder= new AlertDialog.Builder(this);
    builder.setView(dialogView);
    builder.setCancelable(true);
    
    AlertDialog alertDialog=builder.create();
    alertDialog.show();
}
```
布局文件 sy3_alter_dialog.xml

<h3>菜单操作</h3>
菜单创建：

```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
MenuInflater inflater = getMenuInflater();
inflater.inflate(R.menu.sy3_main_menu, menu);
return true;
}
```
菜单项处理：

```java
@Override
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
int id = item.getItemId();
if (id == R.id.ordinary_menu) {
showToast("普通菜单项");
return true;
} else if (id == R.id.font_small) {
changeFontSize(10);
item.setChecked(true);
return true;
}

// 其他菜单项处理...
return super.onOptionsItemSelected(item);
}
```
字体和颜色更改：

```java
private void changeFontSize(int size) {
currentFontSize = size;
textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
}

private void changeTextColor(int color) {
currentTextColor = color;
textView.setTextColor(color);
}
```
相关文件</br>
   sy3_menulayout.xml：包含 Toolbar 和测试文本的布局</br>
sy3_main_menu.xml：菜单定义文件，包含嵌套子菜单

<h3>列表视图与ActionMode</h3>
数据初始化：

```java
public void initData(){
dataList = new ArrayList<>();
int commonImage=R.drawable.android;
String[] englishNumbers = {"one", "two", "three", ...};
for(int i = 1; i <= 20; i++){
Map<String, Object> map = new HashMap<>();
map.put("image", commonImage);
map.put("name", englishNumbers[i-1]);
dataList.add(map);
}
}
```
多选模式监听器：

```java
listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
@Override
public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
// 更新选中状态和计数显示
int selectedCount = listView.getCheckedItemCount();
mode.setTitle(selectedCount + " 已选择");
}

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        if (item.getItemId() == R.id.am_test1) {
            Toast.makeText(sy3_4ListView_ActionMode.this, "菜单1被点击", Toast.LENGTH_SHORT).show();
            mode.finish();
            return true;
        }
        return false;
    }
});
```
相关文件</br>
   sy3_action_mode_layout.xml：动作模式自定义布局</br>

sy3_action_mode_list_item.xml：列表项布局</br>

sy3_action_mode_menu.xml：动作模式菜单</br>

