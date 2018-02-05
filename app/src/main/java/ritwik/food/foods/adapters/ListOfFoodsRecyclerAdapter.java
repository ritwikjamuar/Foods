package ritwik.food.foods.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import ritwik.food.foods.R;
import ritwik.food.foods.models.Food;

public class ListOfFoodsRecyclerAdapter extends RecyclerView.Adapter<ListOfFoodsRecyclerAdapter.ListOfFoodsViewHolder> {
	private Context mContext;
	private ListOfFoodsRecyclerListener mListener;
	private List<Food> mFoods;

	public ListOfFoodsRecyclerAdapter ( Context mContext, ListOfFoodsRecyclerListener mListener ) {
		this.mContext = mContext;
		this.mListener = mListener;
	}

	public void setData ( List<Food> mFoods ) {
		this.mFoods = mFoods;
		notifyDataSetChanged ();
	}

	@Override public ListOfFoodsViewHolder onCreateViewHolder ( ViewGroup parent, int viewType ) {
		View view = LayoutInflater.from ( mContext ).inflate ( R.layout.recycler_item_food, parent, false );
		return new ListOfFoodsViewHolder ( view );
	}

	@Override public void onBindViewHolder ( ListOfFoodsViewHolder holder, int position ) {
		Food food = mFoods.get ( position );
		if ( food != null ) {
			Glide.with ( mContext ).load ( food.getImage () ).into ( holder.mIvFoodImage );
			holder.mTvFoodName.setText ( food.getName () );

			String foodType;
			if ( food.isNonVeg () ) foodType = "Non-Veg";
			else foodType = "Veg";
			holder.mTvFoodType.setText ( foodType );

			if ( food.isSpicy () ) holder.mTvFoodSpicy.setVisibility ( View.VISIBLE );
			else holder.mTvFoodSpicy.setVisibility ( View.GONE );
		}
	}

	@Override public int getItemCount () { return mFoods == null ? 0 : mFoods.size (); }

	class ListOfFoodsViewHolder extends RecyclerView.ViewHolder {
		@BindView ( R.id.recycler_item_food_image ) ImageView mIvFoodImage;
		@BindView ( R.id.recycler_item_food_name ) TextView mTvFoodName;
		@BindView ( R.id.recycler_item_food_type ) TextView mTvFoodType;
		@BindView ( R.id.recycler_item_food_spicy ) TextView mTvFoodSpicy;

		ListOfFoodsViewHolder ( View itemView ) {
			super ( itemView );
			ButterKnife.bind ( ListOfFoodsViewHolder.this, itemView );
		}

		@OnClick ( R.id.recycler_item_food_view_details ) void onViewDetailsClicked () {
			mListener.onFoodSelected ( mFoods.get ( getAdapterPosition () ) );
		}
	}

	public interface ListOfFoodsRecyclerListener {
		void onFoodSelected ( Food food );
	}
}