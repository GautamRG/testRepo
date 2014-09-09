package com.example.testsample;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.neuron.Parser.UserFunctions;

public class MainActivity extends Activity {
	private ListView catList;
	private ArrayList<String> categoryArrayList, IdArrayList;

	private UserFunctions uf;
	private ArrayList<CategoryRowItems> items;
	private CatAdapterClass adapter;
	private CheckBox cb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.catagory_popup);

		catList = (ListView) findViewById(R.id.catList);
		uf = new UserFunctions();
		categoryArrayList = new ArrayList<String>();
		IdArrayList = new ArrayList<String>();

		new JobCatagoryAsyncTask().execute();
	}

	private class JobCatagoryAsyncTask extends AsyncTask<String, String, JSONObject> {

		ProgressDialog pd1 = new ProgressDialog(MainActivity.this);

		@Override
		protected JSONObject doInBackground(String... params) {

			JSONObject j = get_job_catagory();

			return j;
		}

		@Override
		protected void onPostExecute(JSONObject json) {
			pd1.dismiss();

			try {

				JSONArray data = json.getJSONArray("data");
				for (int i = 0; i < data.length(); i++) {

					JSONObject dataObj = data.getJSONObject(i);

					categoryArrayList.add("<b><big><font color='#9D4895'>" + dataObj.getString("sc_name") + "</font><big></b>");
					IdArrayList.add(dataObj.getString("sc_id"));
					JSONArray subcat = dataObj.getJSONArray("subcat");
					for (int j = 0; j < subcat.length(); j++) {

						JSONObject subObj = subcat.getJSONObject(j);
						categoryArrayList.add(subObj.getString("ss_name"));
						IdArrayList.add(subObj.getString("ss_id"));

					}

				}

			} catch (JSONException e) {

				e.printStackTrace();
			}
			items = new ArrayList<CategoryRowItems>();
			for (int i = 0; i < categoryArrayList.size(); i++) {
				CategoryRowItems row = new CategoryRowItems(IdArrayList.get(i), categoryArrayList.get(i), false);
				items.add(row);
			}

			adapter = new CatAdapterClass(MainActivity.this, R.layout.category_row, items);
			catList.setAdapter(adapter);

		}

		@Override
		protected void onPreExecute() {
			
			pd1.setMessage(Html.fromHtml("<b><font color=#9D4895>Loading...Please Wait!!!</font></b>"));
			pd1.show();
			pd1.setCancelable(false);
		}

	}

	public JSONObject get_job_catagory() {

		return uf.getJson(com.neuron.Classes.Constants.JobCatagoryURL);
	}

	private class CatAdapterClass extends ArrayAdapter<CategoryRowItems> {

		private ArrayList<CategoryRowItems> rowItemList;

		public CatAdapterClass(Context context, int textViewResourceId, ArrayList<CategoryRowItems> rowItemList) {
			super(context, textViewResourceId, rowItemList);
			this.rowItemList = new ArrayList<CategoryRowItems>();
			this.rowItemList.addAll(rowItemList);
		}

		private class ViewHolder {
			TextView tv;
			CheckBox tb;

		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;

			Log.v("ConvertView", String.valueOf(position));

			if (convertView == null) {

				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				convertView = vi.inflate(R.layout.category_row, null);

				holder = new ViewHolder();

				holder.tv = (TextView) convertView.findViewById(R.id.tv);

				holder.tb = (CheckBox) convertView.findViewById(R.id.tb);

				convertView.setTag(holder);

				holder.tb.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						cb = (CheckBox) v;

						if (cb.isChecked()) {
							CategoryRowItems RI = (CategoryRowItems) cb.getTag();
							RI.setSelected(true);
							Toast.makeText(getApplicationContext(), RI.getjob_id(), Toast.LENGTH_SHORT).show();
							adapter.notifyDataSetChanged();

						} else {
							CategoryRowItems RI = (CategoryRowItems) cb.getTag();
							RI.setSelected(false);
							Toast.makeText(getApplicationContext(), RI.getjob_id()+" removed!", Toast.LENGTH_SHORT).show();
							adapter.notifyDataSetChanged();

						}

					}
				});

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			CategoryRowItems rowItem = rowItemList.get(position);

			holder.tv.setText(Html.fromHtml(rowItem.getjob_title()));
			if (rowItem.getjob_title().contains("<b>")) {

				holder.tb.setVisibility(View.GONE);

			} else {
				holder.tb.setVisibility(View.VISIBLE);
			}
			holder.tb.setChecked(rowItem.isSelected());

			holder.tb.setTag(rowItem);

			return convertView;
		}
	}

}
