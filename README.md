# MarqueeView

俗名：垂直跑马灯  
学名：垂直翻页公告

### 效果图

<img src="/screenshot/MarqueeView_Gif.gif" style="width: 30%;">

### 使用

#### Gradle:

    compile 'com.sunfusheng:marqueeview:1.1.0'

#### 属性

| Attribute 属性          | Description 描述 |
|:---				     |:---|
| mvAnimDuration         | 一行文字动画执行时间           |
| mvInterval         | 两行文字翻页时间间隔           |
| mvTextSize         | 文字大小           |
| mvTextColor         | 文字颜色          |
| mvGravity         | 文字位置:left、center、right          |
| mvSingleLine         | 单行设置          |

#### XML

    <com.sunfusheng.marqueeview.MarqueeView
        android:id="@+id/marqueeView"
        android:layout_width="match_parent"
        android:layout_height="30dp"
        app:mvAnimDuration="1000"
        app:mvInterval="3000"
        app:mvTextColor="@color/white"
        app:mvTextSize="14sp"
        app:mvSingleLine="true"/>

#### 设置列表数据

    MarqueeView marqueeView = (MarqueeView) findViewById(R.id.marqueeView);

    List<String> info = new ArrayList<>();
    info.add("1. 大家好，我是孙福生。");
    info.add("2. 欢迎大家关注我哦！");
    info.add("3. GitHub帐号：sfsheng0322");
    info.add("4. 新浪微博：孙福生微博");
    info.add("5. 个人博客：sunfusheng.com");
    info.add("6. 微信公众号：孙福生");
    marqueeView.startWithList(info);

#### 设置字符串数据

    String notice = "心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！";
    marqueeView.startWithText(notice);

#### 设置事件监听

    marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
        @Override
        public void onItemClick(int position, TextView textView) {
            Toast.makeText(getApplicationContext(), String.valueOf(marqueeView1.getPosition()) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();
        }
    });

#### 重影问题可参考以下解决方案

    @Override
    public void onStart() {
        super.onStart(); 
        ...
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
        ...
    }
    
    
### [APK下载地址](http://fir.im/MarqueeView)

### 微信公众号

<img src="https://github.com/sfsheng0322/StickyHeaderListView/blob/master/screenshots/%E5%BE%AE%E4%BF%A1%E5%85%AC%E4%BC%97%E5%8F%B7.jpg" style="width: 30%;">

### 关于我

个人邮箱：sfsheng0322@126.com

[GitHub主页](https://github.com/sfsheng0322)

[简书主页](http://www.jianshu.com/users/88509e7e2ed1/latest_articles)

[个人博客](http://sunfusheng.com/)

[新浪微博](http://weibo.com/u/3852192525)

License
--
    Copyright (C) 2016 sfsheng0322@126.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
