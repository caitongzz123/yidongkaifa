package com.hwhhhh.wordbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hwhhhh.wordbook.R;
import com.hwhhhh.wordbook.entity.WordInfo;

import java.util.List;

public class WordListAdapter extends BaseAdapter {
    private List<WordInfo> wordInfoList;
    private Context mContext;
    private String title;

    public WordListAdapter(Context context, List<WordInfo> wordInfoList) {
        this.mContext = context;
        this.wordInfoList = wordInfoList;
    }

    @Override
    public int getCount() {
        return wordInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return wordInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        WordInfo wordInfo = (WordInfo) getItem(i);
        String name = wordInfo.getKey();
        String first;
        boolean isTitle;
        if (title == null) {
            title = name.substring(0,1);
            isTitle = true;
        } else {
            first = name.substring(0,1);
            if (first.equals(title)) {
                isTitle = false;
            } else {
                isTitle = true;
                title = first;
            }
        }
        ViewHolder viewHolder;
        View contentView;
        if (view == null) {
            contentView = LayoutInflater.from(mContext).inflate(R.layout.notebook_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.title = contentView.findViewById(R.id.item_notebook_wordTitle);
            viewHolder.word = contentView.findViewById(R.id.item_notebook_word);
            contentView.setTag(viewHolder);
        } else {
            contentView = view;
            viewHolder = (ViewHolder) contentView.getTag();
        }
        if (isTitle) {
            viewHolder.title.setText(title);
        } else {
            viewHolder.title.setVisibility(View.INVISIBLE);
        }
        viewHolder.word.setText(name);
        return contentView;
    }

    private class ViewHolder {
        TextView title;
        TextView word;
    }
}
