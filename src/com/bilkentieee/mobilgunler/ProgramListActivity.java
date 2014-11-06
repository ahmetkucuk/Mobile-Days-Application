package com.bilkentieee.mobilgunler;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.bilkentieee.mobilgunler.R;

public class ProgramListActivity extends Activity {

	ArrayList<String> arrayList;
	ListView listView;
	ArrayList<Oturum> oturum21 = new ArrayList<Oturum>();
	ArrayList<Oturum> oturum22 = new ArrayList<Oturum>();
	ArrayList<Oturum> oturum23 = new ArrayList<Oturum>();
	boolean isSuccessful = true;
	ProgressBar progress;
	boolean inDay = false;
	ImageButton back;
	TextView topText;
	
	
	OturumAsyncTask oturumAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.programlist);
		arrayList = new ArrayList<String>();

		listView = (ListView) findViewById(R.id.listview);
		progress = (ProgressBar) findViewById(R.id.progressID);
		progress.setVisibility(View.GONE);
		back = (ImageButton)findViewById(R.id.eczanehomebutton);
		topText = (TextView)findViewById(R.id.dayText);
		
		arrayList.add("Birinci Gün");
		arrayList.add("Ýkinci Gün");
		arrayList.add("Üçüncü Gün");
		final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrayList);
		listView.setAdapter(arrayAdapter2);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				if (position == 0 && isSuccessful) {
					RoadSituationAdapter adapter1 = new RoadSituationAdapter(getApplicationContext(), oturum21);
					listView.setAdapter(adapter1);
					topText.setText(getResources().getString(R.string.aralik21));
					inDay = true;

				} else if (position == 1  && isSuccessful) {
					RoadSituationAdapter adapter2 = new RoadSituationAdapter(getApplicationContext(), oturum22);
					listView.setAdapter(adapter2);
					topText.setText(getResources().getString(R.string.aralik22));
					inDay = true;

				} else if (position == 2  && isSuccessful) {
					RoadSituationAdapter adapter3 = new RoadSituationAdapter(getApplicationContext(), oturum23);
					listView.setAdapter(adapter3);
					topText.setText(getResources().getString(R.string.aralik23));
					inDay = true;
				}

			}
		});
		
		
		back.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(inDay){
					listView.setAdapter(arrayAdapter2);
					topText.setText(getResources().getString(R.string.gunler));
					inDay = false;
				} else {
					onBackPressed();
				}
			}
		});
		oturumAsyncTask = new OturumAsyncTask();
		oturumAsyncTask.execute();

	}

	public class OturumAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			try {
				SAXParserFactory saxPF = SAXParserFactory.newInstance();
				SAXParser saxP = saxPF.newSAXParser();
				XMLReader xmlR = saxP.getXMLReader();

				URL url = new URL(
						"http://ieee.bilkent.edu.tr/mobilgunler/oturumlar.xml"); // URL
				// of
				// the
				// XML

				/**
				 * Create the Handler to handle each of the XML tags.
				 **/
				InputSource is = new InputSource(url.openStream());
				//is.setEncoding("ISO-8859-1");
				final XMLHandlerForSession myXMLHandler = new XMLHandlerForSession();
				xmlR.setContentHandler(myXMLHandler);
				xmlR.parse(is);
				oturum21 = myXMLHandler.getOturumList21();
				oturum22 = myXMLHandler.getOturumList22();
				oturum23 = myXMLHandler.getOturumList23();
				if (oturum21 == null || oturum22 == null || oturum23 == null) {
					isSuccessful = false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				isSuccessful = false;
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			if (!isSuccessful) {
				Toast.makeText(getApplicationContext(), getResources().getString(R.string.cannotbring),
						Toast.LENGTH_SHORT).show();
			}
			progress.setVisibility(View.GONE);
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progress.setVisibility(View.VISIBLE);
			super.onPreExecute();
		}

	}
	
	public class RoadSituationAdapter extends ArrayAdapter<Oturum> {

		Context context;
		ArrayList<Oturum> roadList;

		public RoadSituationAdapter(Context context, ArrayList<Oturum> roadList) {
			super(context,
					com.bilkentieee.mobilgunler.R.layout.inflatedoturum,
					roadList);
			// TODO Auto-generated constructor stub
			this.context = context;
			this.roadList = roadList;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			ViewHolder holder;
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				convertView = inflater.inflate(
						R.layout.inflatedoturum, parent, false);
				holder = new ViewHolder();
				holder.roadCadde = (TextView) convertView
						.findViewById(R.id.upperText);
				holder.roadIlce = (TextView) convertView
						.findViewById(R.id.lowerText);
				holder.detailButton = (ImageButton) convertView
						.findViewById(R.id.inflatedButton);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.roadCadde.setText(roadList.get(position).getSubject());
			holder.roadIlce.setText(roadList.get(position).getSpeaker());
			holder.detailButton.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent roadDetailIntent = new Intent(
							getApplicationContext(),
							com.bilkentieee.mobilgunler.OturumDetailActivity.class);
					roadDetailIntent.putExtra("OTURUM_OBJECT",
							roadList.get(position));
					startActivity(roadDetailIntent);
				}
			});

			return convertView;
		}

	}

	static class ViewHolder {

		TextView roadCadde;
		TextView roadIlce;
		ImageButton detailButton;

	}

}
