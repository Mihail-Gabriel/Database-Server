package Models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Orders implements Serializable {

    private String username;
    private double price;
    private Set<OrderFood> foodSet = new HashSet<OrderFood>();

    public Set<OrderFood> getFoodSet() {
        return foodSet;
    }

    public void setFoodSet(Set<OrderFood> foodSet) {
        this.foodSet = foodSet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
