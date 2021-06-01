package Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "foods")
public class Food implements Serializable {
    @Id
    @Column(name = "food_name")
    private String foodName;
    @Column(name = "foodprice")
    private double foodPrice;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
