package Models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonRootName(value="food")
public class Food implements Serializable {
    @JsonProperty("foodName")
    private String foodName;
    @JsonProperty("foodPrice")
    private double foodPrice;
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
