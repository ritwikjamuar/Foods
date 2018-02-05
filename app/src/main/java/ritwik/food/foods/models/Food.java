package ritwik.food.foods.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
	private String name;
	private String description;
	private String image;
	private boolean nonVeg;
	private boolean spicy;

	private Food ( Parcel in ) {
		name = in.readString ();
		description = in.readString ();
		image = in.readString ();
		nonVeg = in.readByte () != 0;
		spicy = in.readByte () != 0;
	}

	public static final Creator<Food> CREATOR = new Creator<Food> () {
		@Override public Food createFromParcel ( Parcel in ) { return new Food ( in ); }
		@Override public Food[] newArray ( int size ) { return new Food[size]; }
	};

	public String getName () { return name; }
	public void setName ( String name ) { this.name = name; }

	public String getDescription () { return description; }
	public void setDescription ( String description ) { this.description = description; }

	public String getImage () { return image; }
	public void setImage ( String image ) { this.image = image; }

	public boolean isNonVeg () { return nonVeg; }
	public void setNonVeg ( boolean nonVeg ) { this.nonVeg = nonVeg; }

	public boolean isSpicy () { return spicy; }
	public void setSpicy ( boolean spicy ) { this.spicy = spicy; }

	@Override public String toString () {
		return "Food { " +
				"name = " + name +
				", description = " + description +
				", image = " + image +
				", nonVeg = " + nonVeg +
				", spicy = " + spicy;
	}

	@Override public int describeContents () { return 0; }
	@Override public void writeToParcel ( Parcel parcel, int i ) {
		parcel.writeString ( name );
		parcel.writeString ( description );
		parcel.writeString ( image );
		parcel.writeByte ( ( byte ) ( nonVeg ? 1 : 0 ) );
		parcel.writeByte ( ( byte ) ( spicy ? 1 : 0 ) );
	}
}