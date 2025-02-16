import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {

        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals( restaurantName )) {
                return restaurant;
            }
        }
        throw new restaurantNotFoundException( restaurantName +" is not found" );
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant( name, location, openingTime, closingTime );
        restaurants.add( newRestaurant );
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName( restaurantName );
        if (restaurantToBeRemoved == null)
            throw new restaurantNotFoundException( restaurantName );
        restaurants.remove( restaurantToBeRemoved );
        return restaurantToBeRemoved;
    }


    public int getTotalCostOfSelectedItems(String restaurantName, List<String> itemNames) throws restaurantNotFoundException {
        //restaurantName passed is found previously, no need to handle restaurnat not found case
        Restaurant restaurant = findRestaurantByName( restaurantName );
        int totalCost = 0;
        for(String itemName : itemNames){
            //Item always exists in the Menu, no need to handle Item not found case
            Item item = restaurant.getItemFromMenu( itemName );
            totalCost += item.getPrice();
        }
        return totalCost;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
