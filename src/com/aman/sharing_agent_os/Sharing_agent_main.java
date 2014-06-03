package com.aman.sharing_agent_os;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Sharing_agent_main extends Activity {

	private Type_Action ta;
	private EditText edit;
	private LinearLayout imageLinearLayout;
	private ImageView imgView;
	private Uri imageUri;
	private Button fbButton;
	private Button twButton;
	private Button lnButton;
	private Button gpButton;
	private Button insButton;
	private Button hoButton;
	private Button waButton;
	
	private static final String GOOGLE_PLUS_PACKAGE= "com.google.android.apps.plus";
	private static final String INSTAGRAM_PACKAGE = "com.instagram.android";
	private static final String HANGOUTS_PACKAGE = "com.google.android.talk";
	private static final String WHATSAPP_PACKAGE = "com.whatsapp";
	private static final String TWITTER_PACKAGE = "com.twitter.android";
	private static final String FACEBOOK_PACKAGE = "com.facebook.katana";
	private static final String LINKEDIN_PACKAGE = "com.linkedin.android";
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharing_agent_main);

		fbButton = (Button)findViewById(R.id.fbButton);
		twButton = (Button)findViewById(R.id.txButton);
		lnButton = (Button)findViewById(R.id.lnButton);
		gpButton = (Button)findViewById(R.id.gpButton);
		insButton = (Button)findViewById(R.id.insButton);
		insButton.setVisibility(View.GONE);
		hoButton = (Button)findViewById(R.id.hoButton);
		waButton = (Button)findViewById(R.id.waButton);

		edit = (EditText) findViewById(R.id.editTxt);
		imageLinearLayout = (LinearLayout)findViewById(R.id.imgLL);
		imageLinearLayout.setVisibility(View.GONE);
		imgView = (ImageView)findViewById(R.id.imgView);

		// Get intent, action and MIME type
		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();

		ta = Type_Action.TEXT;
		imageLinearLayout.setVisibility(View.GONE);
		lnButton.setVisibility(View.VISIBLE);
		insButton.setVisibility(View.GONE);
		
		if (Intent.ACTION_SEND.equals(action) && type != null) 
		{
			if ("text/plain".equals(type)) 
			{
				handleSendText(intent); // Handle text being sent
			} 
			else if (type.startsWith("image/")) 
			{
				handleSendImage(intent); // Handle single image being sent
			}
		} 
		
		fbButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				if(check_if_app_installed(FACEBOOK_PACKAGE) == true)
				{
					if(ta == Type_Action.IMAGE)
					{
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
						shareIntent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
						shareIntent.setType("image/*");
						shareIntent.setPackage(FACEBOOK_PACKAGE);
						startActivity(shareIntent);
					}
					else if(ta == Type_Action.TEXT)
					{
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
						shareIntent.setType("text/plain");
						shareIntent.setPackage(FACEBOOK_PACKAGE);
						startActivity(shareIntent);
					}
				}
				else 
				{
					Toast.makeText(Sharing_agent_main.this, "Please install the Facebook app from play store!", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		twButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				if(check_if_app_installed(TWITTER_PACKAGE) == true)
				{
					if(ta == Type_Action.IMAGE)
					{
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
						shareIntent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
						shareIntent.setType("image/*");
						shareIntent.setPackage(TWITTER_PACKAGE);
						startActivity(shareIntent);
					}
					else if(ta == Type_Action.TEXT)
					{
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
						shareIntent.setType("text/plain");
						shareIntent.setPackage(TWITTER_PACKAGE);
						startActivity(shareIntent);
					}
				}
				else 
				{
					Toast.makeText(Sharing_agent_main.this, "Please install the Twitter app from play store!", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		lnButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				if(ta == Type_Action.TEXT)
				{
					if(check_if_app_installed(LINKEDIN_PACKAGE) == true)
					{
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
						shareIntent.setType("text/plain");
						shareIntent.setPackage(LINKEDIN_PACKAGE);
						startActivity(shareIntent);
					}
					else 
					{
						Toast.makeText(Sharing_agent_main.this, "Please install the LinkedIn app from play store!", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		
		gpButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				if(check_if_app_installed(GOOGLE_PLUS_PACKAGE) == true)
				{
					if(ta == Type_Action.IMAGE)
					{
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
						shareIntent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
						shareIntent.setType("image/*");
						shareIntent.setPackage(GOOGLE_PLUS_PACKAGE);
						startActivity(shareIntent);
					}
					else if(ta == Type_Action.TEXT)
					{
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
						shareIntent.setType("text/plain");
						shareIntent.setPackage(GOOGLE_PLUS_PACKAGE);
						startActivity(shareIntent);
					}
				}
				else 
				{
					Toast.makeText(Sharing_agent_main.this, "Please install the Google+ app from play store!", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		insButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				if(check_if_app_installed(INSTAGRAM_PACKAGE) == true)
				{
					if(ta == Type_Action.IMAGE)
					{
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
						shareIntent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
						shareIntent.setType("image/*");
						shareIntent.setPackage(INSTAGRAM_PACKAGE);
						startActivity(shareIntent);
					}
				}
				else 
				{
					Toast.makeText(Sharing_agent_main.this, "Please install the Instagram app from play store!", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		hoButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				if(check_if_app_installed(HANGOUTS_PACKAGE) == true)
				{
					if(ta == Type_Action.IMAGE)
					{
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
						shareIntent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
						shareIntent.setType("image/*");
						shareIntent.setPackage(HANGOUTS_PACKAGE);
						startActivity(shareIntent);
					}
					else if(ta == Type_Action.TEXT)
					{
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
						shareIntent.setType("text/plain");
						shareIntent.setPackage(HANGOUTS_PACKAGE);
						startActivity(shareIntent);
					}
				}
				else 
				{
					Toast.makeText(Sharing_agent_main.this, "Please install the Hangouts app from play store!", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		waButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				if(check_if_app_installed(WHATSAPP_PACKAGE) == true)
				{
					if(ta == Type_Action.IMAGE)
					{
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
						shareIntent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
						shareIntent.setType("image/*");
						shareIntent.setPackage(WHATSAPP_PACKAGE);
						startActivity(shareIntent);
					}
					else if(ta == Type_Action.TEXT)
					{
						Intent shareIntent = new Intent();
						shareIntent.setAction(Intent.ACTION_SEND);
						shareIntent.putExtra(Intent.EXTRA_TEXT, edit.getText().toString());
						shareIntent.setType("text/plain");
						shareIntent.setPackage(WHATSAPP_PACKAGE);
						startActivity(shareIntent);
					}
				}
				else 
				{
					Toast.makeText(Sharing_agent_main.this, "Please install the WhatsApp app from play store!", Toast.LENGTH_LONG).show();
				}
			}
		});
		

	}
	
	@Override
	public void onBackPressed() 
	{
		super.onBackPressed();			
		finish();
	}

	void handleSendText(Intent intent)
	{
		String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
		if (sharedText != null) 
		{
			imageLinearLayout.setVisibility(View.GONE);
			edit.setText(sharedText);
			ta = Type_Action.TEXT;
			lnButton.setVisibility(View.VISIBLE);
			insButton.setVisibility(View.GONE);
		}
	}

	void handleSendImage(Intent intent) 
	{
		imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
		if (imageUri != null) 
		{
			imageLinearLayout.setVisibility(View.VISIBLE);
			imgView.setImageBitmap(getBitmap(imageUri));
			ta = Type_Action.IMAGE;
			lnButton.setVisibility(View.GONE);
			insButton.setVisibility(View.VISIBLE);
		}
	}
	
	void updateNotification(String message, int mid)
	{
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("Sharing-Agent")
		        .setContentText(message);
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, Sharing_agent_main.class);

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(Sharing_agent_main.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(mid, mBuilder.build());
	}
	
	public Bitmap getBitmap(Uri imageUri)
	{
		Bitmap bitmap = null;
		try 
		{
			  bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		if(bitmap != null)
		{
			bitmap = getResizedBitmap(bitmap, 640);
		}
		return bitmap;
	}
	
	public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
	}
	
	public boolean check_if_app_installed(String s)
	{
		ApplicationInfo info = null;
		boolean result = false;
		try
		{
			info = getPackageManager().getApplicationInfo(s, 0 );
			if(info.packageName.equals(s))
			{
				result = true;
			}
		}
		catch(PackageManager.NameNotFoundException e )
		{
		}
		return result;
	}
	
	public enum Type_Action
	{
		TEXT, IMAGE
	}
}
