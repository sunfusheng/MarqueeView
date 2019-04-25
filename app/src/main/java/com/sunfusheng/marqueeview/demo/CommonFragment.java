package com.sunfusheng.marqueeview.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sunfusheng.marqueeview.MarqueeView;
import com.sunfusheng.marqueeview.demo.R;
import com.sunfusheng.marqueeview.demo.model.MarqueeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by sunfusheng on 2017/8/8.
 */
public class CommonFragment extends Fragment {

    private MarqueeView marqueeView;
    private MarqueeView marqueeView1;
    private MarqueeView marqueeView2;
    private MarqueeView marqueeView3;
    private MarqueeView marqueeView4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        marqueeView = view.findViewById(R.id.marqueeView);
        marqueeView1 = view.findViewById(R.id.marqueeView1);
        marqueeView2 = view.findViewById(R.id.marqueeView2);
        marqueeView3 = view.findViewById(R.id.marqueeView3);
        marqueeView4 = view.findViewById(R.id.marqueeView4);

        List<CharSequence> list = new ArrayList<>();
        SpannableString ss1 = new SpannableString("1、MarqueeView开源项目");
        ss1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red)), 2, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss1);
        SpannableString ss2 = new SpannableString("2、GitHub：sunfusheng");
        ss2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue)), 9, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss2);
        SpannableString ss3 = new SpannableString("3、个人博客：sunfusheng.com");
        ss3.setSpan(new URLSpan("http://sunfusheng.com/"), 7, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss3);
        list.add("4、新浪微博：@孙福生微博");
        //set Custom font
        marqueeView.setTypeface(ResourcesCompat.getFont(getContext(), R.font.huawenxinwei));

        marqueeView.startWithList(list);
        marqueeView.setOnItemClickListener((position, textView) -> Toast.makeText(getContext(), textView.getText() + "", Toast.LENGTH_SHORT).show());

        marqueeView1.startWithText(getString(R.string.marquee_texts), R.anim.anim_top_in, R.anim.anim_bottom_out);
        marqueeView1.setOnItemClickListener((position, textView) -> Toast.makeText(getContext(), String.valueOf(position) + ". " + textView.getText(), Toast.LENGTH_SHORT).show());

        marqueeView2.startWithText(getString(R.string.marquee_text));

        marqueeView3.startWithText(getString(R.string.marquee_texts));

        List<MarqueeModel> models = new ArrayList<>();
        models.add(new MarqueeModel(10000, "增加了新功能：", "显示自己的Model数据"));
        models.add(new MarqueeModel(10001, "GitHub：sunfusheng", "新浪微博：@孙福生微博"));
        models.add(new MarqueeModel(10002, "MarqueeView开源项目", "个人博客：sunfusheng.com"));
        marqueeView4.startWithList(models);

        marqueeView4.setOnItemClickListener((position, textView) -> {
            MarqueeModel model = (MarqueeModel) marqueeView4.getMessages().get(position);
            Toast.makeText(getContext(), "ID:" + model.id, Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
