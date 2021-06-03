package Models;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "orderfood")
public class OrderFood implements Serializable {
    @Id
    @Column(name ="food_name")
    private String foodName;
    @Column(name ="food_price")
    private double foodPrice;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_name", nullable = false)
    private Orders orders;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}



