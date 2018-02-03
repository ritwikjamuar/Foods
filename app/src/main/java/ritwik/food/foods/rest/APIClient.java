package ritwik.food.foods.rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ritwik.food.foods.utilities.Constants;

public class APIClient {
	/**
	 * Provides {@link Retrofit} to the calling services so that they can be communicated.
	 * @return Instance of {@link Retrofit} to make Server Calls.
	 */
	public static Retrofit getRetrofitClient () {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor (); // Instantiate HTTP Logging Interceptor.
		interceptor.setLevel ( HttpLoggingInterceptor.Level.BODY ); // Set level of Logging as BODY level.
		OkHttpClient.Builder builder = new OkHttpClient.Builder (); // Instantiate HTTP Client Builder.
		builder.connectTimeout ( 60, TimeUnit.SECONDS ); // Set Connection Timeout between HTTP Client and Server.
		builder.readTimeout ( 60, TimeUnit.SECONDS ); // Set Read Timeout between HTTP Client and Server.
		/*builder.addInterceptor ( interceptor ); // Set the HTTP Logging Interceptor to this builder. Comment this when app goes on Production.*/
		String BASE_URL = Constants.BASE_URL + "user/driver/"; // Set the Base URL for Client.
		return new Retrofit
				.Builder ()
				.baseUrl ( BASE_URL )
				.addConverterFactory ( GsonConverterFactory.create () )
				.client ( builder.build () )
				.build (); // Return the instance of Retrofit by using HTTP Client Builder.
	}
}