package com.bilkentieee.mobilgunler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bilkentieee.mobilgunler.R;

public class OturumDetailActivity extends Activity {
	
	ImageButton back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oturumdetail);
		back = (ImageButton)findViewById(R.id.oturumhomebutton);
		Bundle bundle = getIntent().getExtras();
		
		Oturum oturum = (Oturum) bundle.getSerializable("OTURUM_OBJECT");
		
		TextView time = (TextView)findViewById(R.id.oturumtarihi);
		TextView speaker = (TextView)findViewById(R.id.speaker);
		TextView subject = (TextView)findViewById(R.id.subject);
		
		time.setText(oturum.getDay() + " / " + oturum.getTime());
		speaker.setText(oturum.getSpeaker());
		subject.setText(oturum.getSubject());
		
		back.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		
	}

}
