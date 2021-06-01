package Models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name ="Users")
public class Users implements Serializable {
    @Id
    @Column(name ="username")
    private String username;
    @Column(name ="password")
    private String password;
    @Column(name = "address")
    private String address;
    @Column(name = "telephoneno")
    private String telephoneNo;
    @Column(name = "city")
    private String city;
    @Column(name = "securitylevel")
    private String role;

    public Users() {

    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return username + " " + password + " " + city + " " + role + " " + telephoneNo;
    }
}
