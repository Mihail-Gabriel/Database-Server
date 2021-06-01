package Models;


import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

@JsonRootName(value="branch")
public class Branch implements Serializable {
    @JsonProperty("branchId")
    private int branchId;
    @JsonProperty("theme")
    private String theme;
    @JsonProperty("branchName")
    private String branchName;
    @JsonProperty("city")
    private String city;


    @JsonCreator
    public Branch(@JsonProperty("branchId") int branchId,
                  @JsonProperty("theme") String theme,
                  @JsonProperty("branchName") String branchName,
                  @JsonProperty("city") String city) {
        this.branchId = branchId;
        this.theme = theme;
        this.branchName = branchName;
        this.city = city;

    }
    @JsonCreator
    public Branch() {
    }
    @JsonGetter
    public String getBranchName() {
        return branchName;
    }
    @JsonSetter
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    @JsonGetter
    public String getCity() {
        return city;
    }
    @JsonSetter
    public void setCity(String city) {
        this.city = city;
    }
    @JsonGetter
    public int getBranchId() {
        return branchId;
    }
    @JsonSetter
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
