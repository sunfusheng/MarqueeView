package com.sunfusheng.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MarqueeView marqueeView;
    private MarqueeView marqueeView1;
    private MarqueeView marqueeView2;
    private MarqueeView marqueeView3;
    private MarqueeView marqueeView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        marqueeView = (MarqueeView) findViewById(R.id.marqueeView);
        marqueeView1 = (MarqueeView) findViewById(R.id.marqueeView1);
        marqueeView2 = (MarqueeView) findViewById(R.id.marqueeView2);
        marqueeView3 = (MarqueeView) findViewById(R.id.marqueeView3);
        marqueeView4 = (MarqueeView) findViewById(R.id.marqueeView4);

        List<Spanned> info = new ArrayList<>();
        info.add(Html.fromHtml(String.format("ddd :<font color=\"#666666\">%s asadf", "已售")));
        info.add(Html.fromHtml("2北京市发布霾黄色预警，<font color='#ff0000'><small><small>外出携带好</small></small></font>口罩"));
        info.add(Html.fromHtml("3北京市发布霾黄色预警，<font color='#ff0000'><small><small>外出携带好</small></small></font>口罩"));

        marqueeView.startWithList(info);

        marqueeView1.startWithText(getString(R.string.marquee_texts));
        marqueeView2.startWithText(getString(R.string.marquee_texts));
        marqueeView3.startWithText(getString(R.string.marquee_texts));
        marqueeView4.startWithText(getString(R.string.marquee_text));

        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getApplicationContext(), textView.getText()+"", Toast.LENGTH_SHORT).show();
            }
        });

        marqueeView1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getApplicationContext(), String.valueOf(marqueeView1.getPosition()) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_app:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
