package com.hugo.cxyclubnews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

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

		try {

			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			HashMap<String, String> map;
			List<String> titleList = News.requestNewsList();

			for (int i = 0, j = titleList.size(); i < j; i++) {
				map = new HashMap<String, String>();
				map.put("title", titleList.get(i));
				list.add(map);
				if (i > 5)
					break;
			}

			SimpleAdapter adapter = new SimpleAdapter(this, list,
					R.layout.list_view, new String[] { "title" },
					new int[] { R.id.title });
			setListAdapter(adapter);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
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
