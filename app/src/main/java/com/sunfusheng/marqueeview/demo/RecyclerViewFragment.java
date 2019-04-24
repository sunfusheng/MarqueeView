package com.sunfusheng.marqueeview.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunfusheng.marqueeview.MarqueeView;
import com.sunfusheng.marqueeview.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunfusheng on 2018/2/6.
 */
public class RecyclerViewFragment extends Fragment {

    public static class Item {
        public String title;
        public List<String> list;
        public boolean showList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            Item item = new Item();
            item.title = "item" + i;
            if (i == 3 || i == 27) {
                List<String> list = new ArrayList<>();
                for (int j = 1; j <= 10; j++) {
                    list.add("MarqueeView Item " + i + "-" + j);
                }
                item.list = list;
                item.showList = true;
            }
            items.add(item);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        private List<Item> list;

        RecyclerViewAdapter(List<Item> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            Item item = list.get(position);

            viewHolder.tvTitle.setText(item.title);
            viewHolder.tvTitle.setVisibility(item.showList ? View.GONE : View.VISIBLE);
            viewHolder.marqueeView.setVisibility(item.showList ? View.VISIBLE : View.GONE);
            if (item.showList) {
                viewHolder.marqueeView.startWithList(item.list);
            }
        }

        @Override
        public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
            if (holder.marqueeView != null) {
                holder.marqueeView.stopFlipping();
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;
            MarqueeView marqueeView;

            ViewHolder(View view) {
                super(view);
                tvTitle = view.findViewById(R.id.tv_title);
                marqueeView = view.findViewById(R.id.marqueeView);
            }
        }
    }
}
