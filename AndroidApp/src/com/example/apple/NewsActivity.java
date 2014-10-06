package com.example.apple;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NewsActivity extends ActionBarActivity implements OnClickListener{

	News actu;
	TextView title, date, description, link;
	Button button_web;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		
		actu = getIntent().getExtras().getParcelable("actu");
		
		title = (TextView) findViewById(R.id.title);
		date = (TextView) findViewById(R.id.date);
		description = (TextView) findViewById(R.id.description);
		link = (TextView) findViewById(R.id.link);
		
		title.setText(actu.getTitle());
		date.setText(actu.getDate());
		description.setText(actu.getDescription());
		link.setText(actu.getLink());
		
		button_web = (Button) findViewById(R.id.button_web);
		button_web.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, WebActivity.class);
		intent.putExtra("link", actu.getLink());
		startActivity(intent);
	}
}
