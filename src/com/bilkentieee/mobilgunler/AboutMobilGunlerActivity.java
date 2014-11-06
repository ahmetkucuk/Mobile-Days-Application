package com.bilkentieee.mobilgunler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.http.util.ByteArrayBuffer;
import com.bilkentieee.mobilgunler.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class AboutMobilGunlerActivity extends MapActivity {

	MapView mapView;
	GeoPoint myGeoPoint, kizilayGeo, bahceliGeo;
	MapController mapController;
	Itemization itemizedOverlay;
	TextView kizilay, bahceli, klavuz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mobilgunlerscreen);
		mapView = (MapView) findViewById(R.id.mapView);
		kizilay = (TextView) findViewById(R.id.kizilayText);
		bahceli = (TextView) findViewById(R.id.bahceliText);
		klavuz = (TextView) findViewById(R.id.klavuz);

		myGeoPoint = new GeoPoint((int) (1E6 * 39.865862),
				(int) (1E6 * 32.748970));
		kizilayGeo = new GeoPoint((int) (1E6 * 39.919619),
				(int) (1E6 * 32.852803));
		bahceliGeo = new GeoPoint((int) (1E6 * 39.917117),
				(int) (1E6 * 32.826605));
		mapController = mapView.getController();
		mapController.setZoom(15);
		mapController.animateTo(myGeoPoint);
		itemizedOverlay = new Itemization(getResources().getDrawable(
				R.drawable.marker));
		OverlayItem overlayitem = new OverlayItem(myGeoPoint, "Bilkent FFB",
				"Bilkent FFB");
		OverlayItem overlayK = new OverlayItem(kizilayGeo, getResources()
				.getString(R.string.thekizilay), getResources().getString(
				R.string.thekizilay));
		OverlayItem overlayB = new OverlayItem(bahceliGeo, getResources()
				.getString(R.string.thebahceli), getResources().getString(
				R.string.thebahceli));
		itemizedOverlay.addOverlay(overlayitem);
		itemizedOverlay.addOverlay(overlayB);
		itemizedOverlay.addOverlay(overlayK);
		mapView.getOverlays().add(itemizedOverlay);

		kizilay.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mapController.animateTo(kizilayGeo);
			}
		});

		bahceli.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mapController.animateTo(bahceliGeo);
			}
		});
		
		klavuz.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PDFIntent intent = new PDFIntent();
				intent.execute();
			}
		});

	}
	
	public class PDFIntent extends AsyncTask<Void, Integer, String>
	{
		ProgressDialog dialog;
		private int progress;
		private int downloadedSize;
		private int maxSize;
		
		@Override
		protected void onPreExecute()
		{
			dialog = new ProgressDialog( AboutMobilGunlerActivity.this );
			dialog.setProgressStyle( ProgressDialog.STYLE_HORIZONTAL );
			dialog.setMessage("PDF Dosyasý Ýndiriliyor...");
			dialog.setProgress( 0 );
			// dialog.setMax( 0 );
			dialog.setCancelable( true );
			dialog.show();
		}
		
		@Override
		protected void onProgressUpdate( Integer... i )
		{
			dialog.setProgress( i[0] );
		}
		
		@Override
		protected String doInBackground(Void... params)
		{
			String success = "";
			
			try
			{
				URL url = new URL( "http://ieee.bilkent.edu.tr/mobilgunler//klavuz" ); 

	            /* Open a connection to that URL. */
	            URLConnection ucon = url.openConnection();
	
	            /*
	             * Define InputStreams to read from the URLConnection.
	             */
	            InputStream is = ucon.getInputStream();
	            BufferedInputStream bis = new BufferedInputStream(is);
	
	            /*
	             * Read bytes to the Buffer until there is nothing more to read(-1).
	             */
	            
	            maxSize = ucon.getContentLength();
	            downloadedSize = 0;
	            progress = 0;
	            
	            ByteArrayBuffer baf = new ByteArrayBuffer(200);
	            
	            new Thread(new Runnable() {
	  			  	public void run() {
	  			  		while ( progress < 100) {
	   
	  			  			// process some tasks
	  			  			if( maxSize > 0 )
	  			  				progress = downloadedSize / maxSize;
	  			  			else
	  			  				progress = 50;
	  				  
	  			  			try {
	  			  				Thread.sleep(1000);
	  			  			} catch (InterruptedException e) {
	  			  				e.printStackTrace();
	  			  			}
	  				  
	  			  			publishProgress( progress );
	  			  		}
	  			  	}
	            }).start();
	            
	            int current = 0;
	            while ( (current = bis.read() ) != -1 ) 
	            {
	            	downloadedSize += current;
	            	baf.append((byte) current);	            	
	            }
	            
	            /* Convert the Bytes read to a String. */

	            FileOutputStream fos = getApplicationContext().openFileOutput("Mobil_Gunler_temp.pdf", MODE_WORLD_READABLE);
	            fos.write(baf.toByteArray());
	            fos.close();
	            
	            success = "DX";

            } catch (IOException e) {
                    Log.e( getString( R.string.pdferror ), "PDF Download Error: ", e);
            }
			
			return success;
		}
		
		@Override
		protected void onPostExecute( String result )
		{
			dialog.dismiss();
			
			if( result.isEmpty() )
			{
				// TOAST - unsuccessful
				Toast.makeText(getApplicationContext(), 
						"PDF Dosyasý indirilemedi", Toast.LENGTH_SHORT).show();
			}
			
			else
			{
				File file = getFileStreamPath("Mobil_Gunler_temp.pdf" );
				
			    Intent pdfIntent = new Intent();
			    pdfIntent.setAction(Intent.ACTION_VIEW);
			    Uri uri = Uri.fromFile(file);
			    
			    pdfIntent.setDataAndType(uri, "application/pdf");
			    startActivity(pdfIntent);
			}
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public class Itemization extends ItemizedOverlay<OverlayItem> {

		private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();

		public Itemization(Drawable defaultMarker) {

			super(boundCenterBottom(defaultMarker));
			// super(defaultMarker);

		}

		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {
			super.draw(canvas, mapView, false);
			if (shadow == false) {
				// cycle through all overlays
				for (int index = 0; index < mOverlays.size(); index++) {
					OverlayItem item = mOverlays.get(index);

					// Converts lat/lng-Point to coordinates on the screen
					GeoPoint point = item.getPoint();
					Point ptScreenCoord = new Point();
					mapView.getProjection().toPixels(point, ptScreenCoord);

					// Paint
					Paint paint = new Paint();
					paint.setTextAlign(Paint.Align.CENTER);
					paint.setTextSize(16);
					paint.setStrokeWidth(4);
					paint.setARGB(150, 0, 0, 0); // alpha, r, g, b (Black, semi
													// see-through)

					// show text to the right of the icon
					canvas.drawText(item.getTitle(), ptScreenCoord.x,
							ptScreenCoord.y + 16, paint);
				}
			}
			return true;
		}

		@Override
		protected OverlayItem createItem(int i) {
			return mOverlays.get(i);
		}

		public void addOverlay(OverlayItem overlay) {
			mOverlays.add(overlay);
			populate();
		}

		@Override
		public int size() {
			return mOverlays.size();
		}

	}

}
