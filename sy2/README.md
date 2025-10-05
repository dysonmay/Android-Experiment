<h1>Android 布局实验报告</h1></br>
<h2>实验概述</h2></br>
本实验通过四个不同的XML布局文件，展示了Android开发中四种常用布局方式的使用方法和特点，包括LinearLayout、TableLayout和ConstraintLayout。</br>
<h2>linearlayout.xml - 网格文本展示</h2></br>
在垂直方向上使用LinearLayout,每行用4个水平的LinearLayout组成一个4*4网格，在网格中添加16个TextView</br>
通过layout_weight属性控制列宽比例，使用margin控制元素间距，利用多重嵌套实现网格效果。</br>
<h2>tablelayout.xml - 应用程序菜单</h2></br>
使用TableLayout作为根布局，添加了TextView显示菜单项和快捷键，利用View调整粗细作为菜单栏的分割线。</br>
使用layout_weight控制列宽比例,利用layout_span实现单元格合并，使用divider属性添加分隔线</br>
<h2>constraintlayout.xml -计算界面</h2></br>
完全使用约束关系定位，无嵌套布局,其中按钮通过按钮通过chain方式实现等间距排列，使用margin控制按钮间距</br>
<h2>constraintlayout2.xml - 太空旅行应用界面</h2></br>
这是一个模拟太空旅行预订应用的复杂界面，采用垂直方向的LinearLayout作为根布局，内部嵌套了多个布局组件。</br>
使用了LinearLayout作为垂直方向的根布局。</br>
第一个水平的LinearLayout中包含3个垂直的LinearLayout，每个区域包含一个ImageView和一个TextView</br>
接着添加了一个水平的空白LinearLayout作为分割。</br>
中间区域利用ConstraintLayout，DCA-MARS转换区域嵌套ConstraintLayout，包含了两个绿色背景的TextView (DCA和MRAS)
以及中间的双箭头ImageView。</br>
旅行设置区域运用了Switch组件，添加了多个ImageView。</br>
底部按钮最后嵌套了一个LinearLayout，绿色背景包含DEPART文字。
