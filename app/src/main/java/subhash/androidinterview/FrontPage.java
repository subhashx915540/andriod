package subhash.androidinterview;

;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FrontPage extends AppCompatActivity implements OnClickListener {

	Button bsimple, btough, bseeotherapps, brateapp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.front_page);

		// Codes to add Action Bar

		LinearLayout front_ll = (LinearLayout) findViewById(R.id.front_page_titlebar);
		//getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(R.layout.frontpage_title_bar);

		bsimple = (Button) findViewById(R.id.bsq);
		btough = (Button) findViewById(R.id.btq);
		bseeotherapps = (Button) findViewById(R.id.bseeotherapps);
		brateapp = (Button) findViewById(R.id.brateapp);

		bsimple.setOnClickListener(this);
		btough.setOnClickListener(this);
		bseeotherapps.setOnClickListener(this);
		brateapp.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bsq:

			Intent i = new Intent(FrontPage.this, Simple_question.class);
			startActivity(i);

			break;
		case R.id.btq:
			
			Intent j = new Intent(FrontPage.this, Tough_question.class);
			startActivity(j);
			
			
			break;
		case R.id.bseeotherapps:
			
			try{
				Uri uri1 = Uri.parse("market://search?q=Subhash");
				Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
				startActivity(goToMarket1);
			}catch(ActivityNotFoundException e){
				
				Uri uri1 = Uri.parse("http://play.google.com/store/search?q=Subhash");
				Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
				startActivity(goToMarket1);
				
			}
			
			
			break;
		case R.id.brateapp:

			try{
				Uri uri1 = Uri.parse("market://details?id="+ getPackageName());
				Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
				startActivity(goToMarket1);
			}catch(ActivityNotFoundException e){
				
				Uri uri1 = Uri.parse("http://play.google.com/store/apps/details?id="+ getPackageName());
				Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
				startActivity(goToMarket1);
				
			}
			
			
			break;

		}

	}
}
