package ritwik.food.foods.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ritwik.food.foods.models.Food;

public interface APIService {
	@GET ( "data.php" ) Call< List < Food > > listOfFoods ();
}