package ritwik.food.foods.utilities;

import android.app.ProgressDialog;

import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Build;

import android.widget.Toast;

import ritwik.food.foods.R;

/**Provides Commonly Used method to be accessible across the application.*/
public class ConstantMethods {
	private static ProgressDialog progressDialog;

	/**Checks the connectivity of device from Internet.
	 * @param context Context of the given invocation of method.
	 * @return Boolean Value True, if internet is active, else False.*/
	public static boolean isOnline ( Context context ) {
		ConnectivityManager cm = ( ConnectivityManager ) context.getSystemService ( Context.CONNECTIVITY_SERVICE );
		if ( cm == null ) return false;
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnected ();
	}

	/**Shows the Progress Dialog.
	 * @param context Context of the given invocation of method.*/
	public static void showProgress ( Context context ) {
		if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP ) progressDialog = new ProgressDialog ( context );
		else progressDialog = new ProgressDialog ( context, R.style.AppTheme_Dialog );
		progressDialog.setMessage ( "Loading..." );
		progressDialog.setCancelable ( false );
		try { progressDialog.show (); }
		catch ( Exception e ) { e.printStackTrace (); }
	}

	/**Checks whether Progress Dialog is showing in the screen or not.
	 * @return true when Progress Dialog is showing, else false.*/
	public static boolean isProgressShown () {
		boolean isShown = false;
		if ( progressDialog != null ) {
			if ( progressDialog.isShowing () )
				isShown = true;
		}
		return isShown;
	}

	/**Removes the Progress Dialog.*/
	public static void removeProgress () {
		if ( progressDialog != null ) {
			if ( progressDialog.isShowing () ) {
				progressDialog.dismiss ();
				progressDialog = null;
			}
		}
	}

	/**Shows the Toast Message from any screen.
	 * @param context Context of the given invocation of method.
	 * @param message Message to be displayed as Toast.*/
	public static void showToastMessage ( Context context, String message ) {
		Toast.makeText ( context, message, Toast.LENGTH_SHORT ).show ();
	}

	/**Shows the Toast Message from any screen.
	 * @param context Context of the given invocation of method.
	 * @param resourceID ID of String Message to be displayed as Toast.*/
	public static void showToastMessage ( Context context, int resourceID ) {
		Toast.makeText ( context, resourceID, Toast.LENGTH_SHORT ).show ();
	}
}