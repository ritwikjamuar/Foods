package ritwik.food.foods.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import android.text.Html;

import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import ritwik.food.foods.R;
import ritwik.food.foods.models.Food;
import ritwik.food.foods.utilities.Constants;

public class FoodDetailsActivity extends AppCompatActivity {
	// Views.
	@BindView ( R.id.activity_food_details_image ) ImageView mIvFoodImage;
	@BindView ( R.id.activity_food_details_name ) TextView mTvFoodName;
	@BindView ( R.id.activity_food_details_description ) TextView mTvFoodDescription;
	@BindView ( R.id.activity_food_details_type ) TextView mTvFoodType;
	@BindView ( R.id.activity_food_details_spicy ) TextView mTvFoodSpicy;
	@BindView ( R.id.activity_food_details_toolbar ) Toolbar mToolbar;

	// Strings.
	@BindString ( R.string.food_type_veg ) String sVeg;
	@BindString ( R.string.food_type_non_veg ) String sNonVeg;

	// Model.
	private Food mFood;

	@Override protected void onCreate ( Bundle savedInstanceState ) {
		super.onCreate ( savedInstanceState );
		setContentView ( R.layout.activity_food_details );
		initializeComponents ();
		getDataFromPreviousActivity ();
		initializeViews ();
		bindDataToViews ();
	}

	@Override public boolean onOptionsItemSelected ( MenuItem item ) {
		switch ( item.getItemId () ) {
			case android.R.id.home : {
				finish ();
			} break;
		}
		return super.onOptionsItemSelected ( item );
	}

	/**Initialize other components of this Activity.*/
	private void initializeComponents () {
		ButterKnife.bind ( FoodDetailsActivity.this );
	}

	/**Initialize the View Component of this Activity.*/
	private void initializeViews () {
		setSupportActionBar ( mToolbar );
		ActionBar actionBar = getSupportActionBar ();
		if ( actionBar != null ) {
			actionBar.setHomeButtonEnabled ( true );
			actionBar.setDisplayHomeAsUpEnabled ( true );
		}
	}

	/**Fetches the {@link Food} item from Previous Activity.*/
	private void getDataFromPreviousActivity () {
		Bundle bundle = getIntent ().getExtras ();
		if ( bundle != null ) {
			mFood = ( Food ) bundle.get ( Constants.FOOD );
		}
	}

	/**Bind the Data to the View Components.*/
	private void bindDataToViews () {
		if ( mFood != null ) {
			Glide.with ( FoodDetailsActivity.this ).load ( mFood.getImage () ).into ( mIvFoodImage );
			mTvFoodName.setText ( mFood.getName () );
			mTvFoodDescription.setText ( Html.fromHtml ( mFood.getDescription () ) );
			if ( mFood.isNonVeg () ) mTvFoodType.setText ( sNonVeg );
			else mTvFoodType.setText ( sVeg );
			if ( mFood.isSpicy () ) mTvFoodSpicy.setVisibility ( View.VISIBLE );
			else mTvFoodSpicy.setVisibility ( View.GONE );
		}
	}
}