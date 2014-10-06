package com.example.apple;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class RefreshRSS extends AsyncTask<Void, Void, Void> {

	private ListRSSActivity activity;
	private ProgressDialog progress = null;
	
	@Override
	protected Void doInBackground(Void... params) {
		activity.refreshRSS("https://www.apple.com/main/rss/hotnews/hotnews.rss",activity.getNews());
		NewsAdapter news_adapter = new NewsAdapter(activity, R.layout.row,activity.getNews());
		activity.setNewsAdapter(news_adapter);
		System.out.println(activity.getNewsAdapter());
        return null;
	}

	public RefreshRSS(ListRSSActivity activity) {
		this.activity = activity;
	}
	
	@Override
    protected void onCancelled() {
            super.onCancelled();
    }
    
    @Override
    protected void onPreExecute() {
            progress = ProgressDialog.show(
                            activity, null, "Chargement du flux RSS");
            
            super.onPreExecute();
    }
    
    @Override
    protected void onPostExecute(Void result) {
        activity.refreshList();
        progress.dismiss();  
        super.onPostExecute(result);
    }
    
    @Override
    protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
    }
	
	

}
