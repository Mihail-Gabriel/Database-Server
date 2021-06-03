package Models;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

@Entity
@Table(name = "food")
@JsonRootName(value="food")
public class Food implements Serializable {
    @Id
    @Column(name = "food_name")
    @JsonProperty("foodName")
    private String foodName;
    @Column(name = "foodprice")
    @JsonProperty("foodPrice")
    private double foodPrice;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "branch_id", nullable = false)
    @JsonProperty("branch")
    private Branch branch;
    @JsonCreator
    public Food(@JsonProperty("foodName") String foodName,
                @JsonProperty("foodPrice") double foodPrice,
                @JsonProperty("branch") Branch branch) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.branch = branch;
    }
    @JsonCreator
    public Food() {
    }
    @JsonGetter
    public String getFoodName() {
        return foodName;
    }
    @JsonSetter
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    @JsonGetter
    public double getFoodPrice() {
        return foodPrice;
    }
    @JsonSetter
    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
    @JsonGetter
    public Branch getBranch() {
        return branch;
    }
    @JsonSetter
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
