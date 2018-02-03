package ritwik.food.foods.models;

public class Food {
	private String name;
	private String description;
	private String image;
	private boolean nonVeg;
	private boolean spicy;

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
}