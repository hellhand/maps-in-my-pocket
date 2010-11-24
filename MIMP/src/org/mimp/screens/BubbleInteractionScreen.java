package org.mimp.screens;

import org.mimp.R;
import org.mimp.adapters.BubbleInfoListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BubbleInteractionScreen extends Activity implements
		OnItemClickListener, OnClickListener {

	private ListView mListView;
	private BubbleInfoListAdapter mBubbleInfoListAdapter;
	private String[] mAddress;
	private int[] mCoords;
	private Intent emailIntent;
	private String mMessage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.bubbleinfolist);
        
        mListView = (ListView) findViewById(R.id.bubble_info_list);
        mListView.setOnItemClickListener(this);
        mBubbleInfoListAdapter = new BubbleInfoListAdapter(this, false);
        mAddress = getIntent().getStringArrayExtra("address");
        mCoords = getIntent().getIntArrayExtra("coords");
        mListView.setAdapter(mBubbleInfoListAdapter);
	}

    /*****************************************************************************
     * 
     * Key controls and menu handling
     * 
     *****************************************************************************/
	
	@Override
	public void onClick(View v) {
		System.out.println(">>>>>>>>>>>>>>> " + v);
		switch (v.getId()) {
			case R.id.bubble_interactions_header_map:
				finish();
				break;
			default:
				break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		System.out.println(">>>>>>>>>>>>>>> " + arg1);
		switch (arg2) {
		case 1:
			
			break;
		case 2:
			sendEmail();
			break;
		default:
			break;
		}
	}
	
	public void onBackPressed() {
		finish();
	}
	
	private void sendEmail() {
		mMessage = "Hey check this location : " + mAddress[0] + ", "
			+ mAddress[1] + "\n" + "http://maps.google.com/maps?q="
			+ mCoords[0]/1E6 + "," + mCoords[1]/1E6;
		emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{""});
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Location");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, mMessage);
		startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}
}