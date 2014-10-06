package com.example.apple;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<News> {

	private List<News> news = null;
	private NewsAdapter news_adapter = null;
	
	public NewsAdapter(Context context,int textviewid, List<News> news) {
		super(context, textviewid, news);
		this.news = news;
	}
	
	@Override
	public int getCount() {
		return ((null != news) ? news.size() : 0);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public News getItem(int position) {
		return ((null != news) ? news.get(position) : null);
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
        if(view == null)
        {
        	LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        	view = (ViewGroup) layoutInflater.inflate(R.layout.row, null);
        }
        
        News actu = news.get(position);
        
        if(null != actu)
        {
            TextView title = (TextView)view.findViewById(R.id.title);
            TextView date = (TextView)view.findViewById(R.id.date);
                
            title.setText(actu.getTitle());
            date.setText("on " + actu.getDate());
        }
        
        return view;
	}

	public NewsAdapter getNews_adapter() {
		return news_adapter;
	}

	public void setNews_adapter(NewsAdapter news_adapter) {
		this.news_adapter = news_adapter;
	}

}
