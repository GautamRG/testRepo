package com.neuron.Classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import com.example.testsample.R;

public class Constants {

	// public static String BaseURL =
	// "http://1-800spatogo.whatall.com/index.php/api/";

	public static String BaseURL = "http://192.168.0.187/spatogo/index.php/api/";

	// CustomerDashboard

	public static String FacebookLoginURL = BaseURL + "auth/signup";
	public static String LoginURL = BaseURL + "auth/login";
	public static String JobCatagoryURL = BaseURL + "site/getcategories";
	public static String PostJobURL = BaseURL + "job/createjob";
	public static String StatesURL = BaseURL + "site/statelist";
	public static String TestURL = BaseURL + "tester";

	public static String BeauticianList = BaseURL + "beautician/list";
	public static String BeauticianInviteURL = BaseURL + "beautician/invite";
	public static String SendInvitation = BaseURL + "beautician/SaveInvitation";

	public static String CustomerOpenjob = BaseURL + "job/customeropenjob";
	public static String CustomerOpensClosejob = BaseURL + "job/CustomerCloseJob";
	public static String CustomerOpensEditjob = BaseURL + "job/CustomerEditJob";
	public static String CustomerOpensEditGetJob = BaseURL + "job/GetCustomerJobById";

	// BeauticianDashboard
	public static String BeauticianMyJob = BaseURL + "beautician/job/myjob";
	public static String BeauticianJobDetail = BaseURL + "beautician/job/jobdetail";

	public static String SHARED_KEY = "KEY";
	public static String SHARED_User_KEY = "user_id";
	public static String FB_APP_ID = "253294971529479";

	public static String SHARED_Beautician_KEY = "BeauticianSearchList";
	public static String Beautician_cat_ID_Array = "beauticianArray";
	public static String Min_Cost_ID = "Mincost";
	public static String Max_Cost_ID = "Maxcost";
	public static String Min_Rating_ID = "Minrating";
	public static String Max_Rating_ID = "Maxrating";
	public static String Zip_code_ID = "zipcode";

	public static final int DATE_DIALOG_ID = 0;
	public static final int DATE_End_DIALOG_ID = 3;
	public static final int DATE_Award_DIALOG_ID = 4;
	public static final int TIME_FIRST_DIALOG_ID = 1;
	public static final int TIME_SECOND_DIALOG_ID = 2;

	public static int RESULT_LOAD_IMAGE = 1;

	static {

	}

	public Constants() {

	}

	public static AlertDialog showAlert(final String message, final String title, final Activity context) {
		final AlertDialog alert;
		try {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage(Html.fromHtml("<font color='#9D4895'>" + message + "</font>"))
					.setTitle(Html.fromHtml("<font color='#9D4895'>" + title + "</font>")).setCancelable(false)
					.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

						}
					});
			alert = builder.create();
			alert.show();

			Resources resources = alert.getContext().getResources();
			int titleDividerId = resources.getIdentifier("titleDivider", "id", "android");
			View titleDivider = alert.getWindow().getDecorView().findViewById(titleDividerId);
			titleDivider.setBackgroundColor(Color.parseColor("#9D4895"));

			Button b = alert.getButton(DialogInterface.BUTTON_POSITIVE);
			if (b != null)
//				b.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.greenbutton));
			b.setTextColor(Color.WHITE);

			return alert;
		} catch (Exception e) {

		}
		return null;

	}

	public static AlertDialog showAlertTrueFalse(final String message, final String title, final Activity context) {
		final AlertDialog alert;
		try {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);

			builder.setMessage(Html.fromHtml("<font color='#9D4895'>" + message + "</font>"));

			builder.setTitle(Html.fromHtml("<font color='#9D4895'>" + title + "</font>"));
			builder.setCancelable(false);
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					dialog.dismiss();

				}
			});

			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					dialog.dismiss();

				}
			});

			alert = builder.create();
			alert.show();

			Resources resources = alert.getContext().getResources();
			int titleDividerId = resources.getIdentifier("titleDivider", "id", "android");
			View titleDivider = alert.getWindow().getDecorView().findViewById(titleDividerId);
			titleDivider.setBackgroundColor(Color.parseColor("#9D4895"));

			Button b = alert.getButton(DialogInterface.BUTTON_POSITIVE);
			if (b != null)
//				b.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.greenbutton));
			b.setTextColor(Color.WHITE);

			Button c = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
			if (c != null)
//				c.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.greenbutton));
			c.setTextColor(Color.WHITE);

			return alert;
		} catch (Exception e) {

		}
		return null;

	}

	public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
		Bitmap sbmp;
		if (bmp.getWidth() != radius || bmp.getHeight() != radius)
			sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
		else
			sbmp = bmp;
		Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		// final int color = 0xffa19774;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);
		// paint.setStyle(Paint.Style.STROKE);
		// paint.setColor(Color.GRAY);
		canvas.drawCircle(sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f, sbmp.getWidth() / 2 + 0.1f, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(sbmp, rect, rect, paint);

		return output;
	}

	public static Bitmap getBorderedCroppedBitmap(Bitmap bmp, int radius) {
		Bitmap sbmp = null;

		if (bmp.getWidth() != radius || bmp.getHeight() != radius)
			sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
		else
			sbmp = bmp;
		Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		// final int color = 0xffa19774;

		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);

		canvas.drawARGB(0, 0, 0, 0);

		canvas.drawCircle(sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f, sbmp.getWidth() / 2 + 0.1f, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(sbmp, rect, rect, paint);

		paint.setXfermode(null);
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
		paint.setColor(0X50000000);
		paint.setStrokeWidth(5f);

		canvas.drawCircle(sbmp.getWidth() / 2 + 0.5f, sbmp.getHeight() / 2 + 0.5f, sbmp.getWidth() / 2.03f, paint);

		return output;
	}

}
