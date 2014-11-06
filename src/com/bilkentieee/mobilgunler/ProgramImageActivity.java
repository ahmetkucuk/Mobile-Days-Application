package com.bilkentieee.mobilgunler;

import android.app.Activity;
import android.os.Bundle;
import android.text.style.ImageSpan;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.bilkentieee.mobilgunler.R;

public class ProgramImageActivity extends Activity {
	
	WebView webview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.program);
		
		 webview = (WebView)findViewById(R.id.webview);
		 webview.getSettings().setSupportZoom(true);
		 webview.getSettings().setBuiltInZoomControls(true);
		 webview.loadUrl("http://ieee.bilkent.edu.tr/mobilgunler//program");
	}

}
