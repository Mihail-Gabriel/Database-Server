package Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name="BRANCH")
public class Branch implements Serializable {
    @Id
    @Column(name ="branch_id")
    private int branchId;
    @Column(name ="branch_theme")
    private String theme;
    @Column (name = "branch_name")
    private String branchName;
    @Column(name ="city_name")
    private String city;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}
