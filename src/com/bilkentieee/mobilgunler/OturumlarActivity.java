package com.bilkentieee.mobilgunler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.bilkentieee.mobilgunler.R;

public class OturumlarActivity extends Activity{
	
	Button program;
	Button programlist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oturumlar);
		
		program = (Button)findViewById(R.id.program);
		programlist = (Button)findViewById(R.id.programlist);
		
		programlist.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent orderHistoryItemIntent = new Intent(
						getApplicationContext(),
						ProgramListActivity.class);
				View view = TabGroupActivity.group
						.getLocalActivityManager()
						.startActivity(
								"Program List",
								orderHistoryItemIntent
										.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView();

				// Again, replace the view
				TabGroupActivity.group.replaceView(view);
			}
		});
		
		program.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent orderHistoryItemIntent = new Intent(
						getApplicationContext(),
						ProgramImageActivity.class);
				View view = TabGroupActivity.group
						.getLocalActivityManager()
						.startActivity(
								"Program",
								orderHistoryItemIntent
										.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView();

				// Again, replace the view
				TabGroupActivity.group.replaceView(view);
			}
		});
		
	}

}
