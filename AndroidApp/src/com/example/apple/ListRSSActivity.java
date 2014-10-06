package com.example.apple;

import java.io.Console;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


public class ListRSSActivity extends ListActivity {

	private NewsAdapter news_adapter = null;
	private ArrayList<News> news = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        news = new ArrayList<News>();
        new RefreshRSS(this).execute();
    }

    public ArrayList<News> getNews() {
		return news;
	}

	public void setNews(ArrayList<News> news) {
		this.news = news;
	}

	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);
        News actu = news.get(position);
        Intent intent = new Intent(this, NewsActivity.class);
		intent.putExtra("actu", actu);
        //Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(actu.getLink()));
        startActivity(intent);
    }
    
    public void refreshRSS(String urlToRssFeed,ArrayList<News> news)
    {
        try
        {
           URL url = new URL(urlToRssFeed);
           SAXParserFactory factory = SAXParserFactory.newInstance();
           SAXParser parser = factory.newSAXParser();
           XMLReader xmlreader = parser.getXMLReader();
           RSSParser theRssHandler = new RSSParser(news);

           xmlreader.setContentHandler(theRssHandler);

           InputSource is = new InputSource(url.openStream());

           xmlreader.parse(is);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
        	case R.id.action_settings :
        		return true;
        	case R.id.action_refresh :
        		new RefreshRSS(this).execute();
        		break;
        }
        return super.onOptionsItemSelected(item);
    }

	public NewsAdapter getNewsAdapter() {
		return news_adapter;
	}

	public void setNewsAdapter(NewsAdapter news_adapter) {
		this.news_adapter = news_adapter;
	}

	public void refreshList() {
		setListAdapter(news_adapter);
		
	}
}
