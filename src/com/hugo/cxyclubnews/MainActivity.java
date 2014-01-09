package com.hugo.cxyclubnews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hugo.cxyclubnews.utils.News;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				ArrayList<String> titlelist = News.getTitles();
				for (int i = 0, j = titlelist.size(); i < j; i++) {
					String titleString = titlelist.get(i);
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("title", titleString);
					list.add(map);
					if (i > 5)
						break;

				}
			}
		});
		
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleAdapter adapter = new SimpleAdapter(this, list,
				R.layout.list_view, new String[] { "title" },
				new int[] { R.id.title });
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}
}
