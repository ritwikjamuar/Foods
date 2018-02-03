package ritwik.food.foods;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

public class AppController extends MultiDexApplication {
	public AppController () {
		super ();
	}

	@Override public void onCreate () {
		super.onCreate ();
		MultiDex.install ( AppController.this );
	}

	@Override protected void attachBaseContext ( Context base ) {
		super.attachBaseContext ( base );
	}
}