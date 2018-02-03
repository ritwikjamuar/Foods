package ritwik.food.foods.api_calls;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import ritwik.food.foods.models.Food;
import ritwik.food.foods.rest.APIClient;
import ritwik.food.foods.rest.APIService;

public class ListOfFoodsAPI implements Callback<List<Food>> {
	private ListOfFoodsListener mListener;

	public ListOfFoodsAPI ( ListOfFoodsListener mListener ) { this.mListener = mListener; }

	public void getListOfFoods () {
		APIService apiClient = APIClient.getRetrofitClient ().create ( APIService.class );
		Call < List < Food > > call = apiClient.listOfFoods ();
		call.enqueue ( ListOfFoodsAPI.this );
	}

	@Override public void onResponse ( @NonNull Call < List < Food > > call, @NonNull Response < List < Food > > response ) {
		List < Food > foods = response.body ();
		if ( foods == null ) mListener.onListOfFoodsError ( "List of Foods is corrupted" );
		else {
			if ( foods.size () == 0 ) mListener.onListOfFoodsError ( "No Foods Found" );
			else mListener.onListOfFoodsFetched ( foods );
		} cleanUp ();
	}

	@Override public void onFailure ( @NonNull Call < List < Food > > call, @NonNull Throwable t ) {
		mListener.onListOfFoodsError ( t.toString () );
		cleanUp ();
	}

	private void cleanUp () { mListener = null; }

	public interface ListOfFoodsListener {
		void onListOfFoodsFetched ( List < Food > foods );
		void onListOfFoodsError ( String error );
	}
}