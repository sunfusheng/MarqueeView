# MarqueeView

俗名：垂直跑马灯  
学名：垂直翻页公告

### 效果图

<img src="/screenshot/MarqueeView_Gif.gif" style="width: 30%;">

### 使用

#### Gradle:

    compile 'com.sunfusheng:marqueeview:1.0.0'

#### 属性

| Attribute 属性          | Description 描述 |
|:---				     |:---|
| mvInterval         | 两行文字翻页时间间隔           |
| mvAnimDuration         | 一行文字动画执行时间           |
| mvTextSize         | 文字大小           |
| mvTextColor         | 文字颜色          |

#### XML

    <com.sunfusheng.marqueeview.MarqueeView
        android:id="@+id/marqueeView"
        android:layout_width="match_parent"
        android:layout_height="30dp"
        android:layout_centerVertical="true"
        android:layout_marginLeft="30dp"
        android:layout_marginRight="30dp"
        app:mvAnimDuration="1000"
        app:mvInterval="3000"
        app:mvTextColor="@color/white"
        app:mvTextSize="14sp"/>

#### 代码中设置列表数据

    MarqueeView marqueeView = (MarqueeView) findViewById(R.id.marqueeView);

    List<String> info = new ArrayList<>();
    info.add("1. 大家好，我是孙福生。");
    info.add("2. 欢迎大家关注我哦！");
    info.add("3. GitHub帐号：sfsheng0322");
    info.add("4. 新浪微博：孙福生微博");
    info.add("5. 个人博客：sunfusheng.com");
    info.add("6. 微信公众号：孙福生");
    marqueeView.startWithList(info);

#### 代码中设置长字符串数据

    String notice = "心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！";
    marqueeView1.startWithText(notice);


### [APK下载地址](http://fir.im/MarqueeView)

### 微信公众号

<img src="https://github.com/sfsheng0322/StickyHeaderListView/blob/master/screenshots/%E5%BE%AE%E4%BF%A1%E5%85%AC%E4%BC%97%E5%8F%B7.jpg" style="width: 30%;">

### 关于我

个人邮箱：sfsheng0322@126.com

[GitHub主页](https://github.com/sfsheng0322)

[简书主页](http://www.jianshu.com/users/88509e7e2ed1/latest_articles)

[个人博客](http://sunfusheng.com/)

[新浪微博](http://weibo.com/u/3852192525)
