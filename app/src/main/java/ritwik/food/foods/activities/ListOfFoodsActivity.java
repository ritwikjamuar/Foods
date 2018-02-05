package ritwik.food.foods.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ritwik.food.foods.R;
import ritwik.food.foods.adapters.ListOfFoodsRecyclerAdapter;
import ritwik.food.foods.api_calls.ListOfFoodsAPI;
import ritwik.food.foods.models.Food;
import ritwik.food.foods.utilities.ConstantMethods;
import ritwik.food.foods.utilities.Constants;

public class ListOfFoodsActivity
		extends AppCompatActivity
		implements ListOfFoodsAPI.ListOfFoodsListener,
		           ListOfFoodsRecyclerAdapter.ListOfFoodsRecyclerListener {
	// Views.
	@BindView ( R.id.activity_list_of_foods_recycler_view ) RecyclerView mRvListOfFoods;

	// Other Components
	private ListOfFoodsAPI mAPI;

	// Adapters.
	private ListOfFoodsRecyclerAdapter mAdapter;

	@Override protected void onCreate ( Bundle savedInstanceState ) {
		super.onCreate ( savedInstanceState );
		setContentView ( R.layout.activity_list_of_foods );
		initializeComponents ();
		initializeViews ();
		callListOfFoodAPI ();
	}

	/**Initialize the Other Components of this Activity.*/
	private void initializeComponents () {
		ButterKnife.bind ( ListOfFoodsActivity.this );
		mAPI = new ListOfFoodsAPI ( ListOfFoodsActivity.this );
		mAdapter = new ListOfFoodsRecyclerAdapter ( ListOfFoodsActivity.this, ListOfFoodsActivity.this );
	}

	/**Initialize the View components of this Activity.*/
	private void initializeViews () {
		mRvListOfFoods.setLayoutManager ( new LinearLayoutManager ( ListOfFoodsActivity.this ) );
		mRvListOfFoods.setAdapter ( mAdapter );
	}

	/**Calls the API : {@link ListOfFoodsAPI List of Food}*/
	private void callListOfFoodAPI () {
		if ( ConstantMethods.isOnline ( ListOfFoodsActivity.this ) ) {
			ConstantMethods.showProgress ( ListOfFoodsActivity.this );
			mAPI.getListOfFoods ();
		} else ConstantMethods.showToastMessage ( ListOfFoodsActivity.this, R.string.check_internet_connection );
	}

	@Override public void onListOfFoodsFetched ( List<Food> foods ) {
		ConstantMethods.removeProgress ();
		mAdapter.setData ( foods );
	}

	@Override public void onListOfFoodsError ( String error ) {
		ConstantMethods.removeProgress ();
		ConstantMethods.showToastMessage ( ListOfFoodsActivity.this, error );
	}

	@Override public void onFoodSelected ( Food food ) { startFoodDetailsActivity ( food ); }

	/**Starts {@link FoodDetailsActivity Food Details} Activity.
	 * @param food Instance of {@link Food} denoting Food info selected by user.*/
	private void startFoodDetailsActivity ( Food food ) {
		Intent intent = new Intent ( ListOfFoodsActivity.this, FoodDetailsActivity.class );
		Bundle bundle = new Bundle ();
		bundle.putParcelable ( Constants.FOOD, food );
		intent.putExtras ( bundle );
		startActivity ( intent );
	}
}