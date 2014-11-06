package com.bilkentieee.mobilgunler;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.bilkentieee.mobilgunler.R;

public class IletisimActivity extends Activity {

	ListView listMobilgunler;
	ListView bilkentIEEE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iletisim);

		listMobilgunler = (ListView) findViewById(R.id.mobillist);
		bilkentIEEE = (ListView) findViewById(R.id.ieeelist);

		ArrayList<String> list = new ArrayList<String>();
		list.add("Web Sitesi \n \t\t\t mobilgunler.org");
		list.add("E-mail \n \t\t\t ieeebilkentmobilgunler@gmail.com");
		list.add("Telefon-1 \n \t\t\t +905468073355");
		list.add("Telefon-2 \n \t\t\t +905073302013");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_list_item_1,
				list);

		ArrayList<String> listIEEE = new ArrayList<String>();

		listIEEE.add("Web Sitesi \n \t\t\t ieee.bilkent.edu.tr");
		listIEEE.add("E-mail \n \t\t\t bilkentieee@gmail.com");
		listIEEE.add("Telefon \n \t\t\t +90-312-2902093");
		listIEEE.add("Fax \n \t\t\t +90-312-2664909");
		listIEEE.add("Adres \n \t\t\t " + getResources().getString(R.string.ieeeadres));

		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_list_item_1,
				listIEEE);
		
		listMobilgunler.setAdapter(adapter);
		bilkentIEEE.setAdapter(adapter2);
		
		listMobilgunler.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				if(position == 0) {
					String url = "http://www.mobilgunler.org";
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				} else if(position == 1) {
					Intent i = new Intent(Intent.ACTION_SEND);  
					//i.setType("text/plain"); //use this line for testing in the emulator  
					i.setType("message/rfc822") ; // use from live device
					i.putExtra(Intent.EXTRA_EMAIL, new String[]{"ieeebilkentmobilgunler@gmail.com"});  
					i.putExtra(Intent.EXTRA_SUBJECT,getResources().getString(R.string.aboutmg));  
					i.putExtra(Intent.EXTRA_TEXT,"");  
					startActivity(Intent.createChooser(i, "Select email application."));
				} else if(position == 2) {
					Intent intent = new Intent(Intent.ACTION_DIAL);
					intent.setData(Uri.parse("tel:" + "+905468073355"));
					startActivity(intent);
				} else if(position == 3) {
					Intent intent = new Intent(Intent.ACTION_DIAL);
					intent.setData(Uri.parse("tel:" + "+905073302013"));
					startActivity(intent);
				}
				
			}
		});
		
		bilkentIEEE.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				if(position == 0) {
					String url = "http://www.ieee.bilkent.edu.tr";
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				} else if(position == 1) {
					Intent i = new Intent(Intent.ACTION_SEND);  
					//i.setType("text/plain"); //use this line for testing in the emulator  
					i.setType("message/rfc822") ; // use from live device
					i.putExtra(Intent.EXTRA_EMAIL, new String[]{"bilkentieee@gmail.com"});  
					i.putExtra(Intent.EXTRA_SUBJECT,getResources().getString(R.string.aboutmobil));  
					i.putExtra(Intent.EXTRA_TEXT,"");  
					startActivity(Intent.createChooser(i, "Select email application."));
				} else if(position == 2) {
					Intent intent = new Intent(Intent.ACTION_DIAL);
					intent.setData(Uri.parse("tel:" + "+903122902093"));
					startActivity(intent);
				}
				
			}
		});

	}

}
