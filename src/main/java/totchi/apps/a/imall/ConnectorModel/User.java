package totchi.apps.a.imall.ConnectorModel;

/**
 * Created by 3zoOz on 29/12/2017.
 */

public class User {
    private String id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String image;
    private String number_of_followings;
    private String date_of_birth;
    private String city;
    private String message;
    private String isFollowing;
    public User (){}

    public String getIsFollowing() {
        return isFollowing;
    }

    public void setIsFollowing(String isFollowing) {
        this.isFollowing = isFollowing;
    }

    public String getNumber_of_followings() {
        return number_of_followings;
    }

    public void setNumber_of_followings(String number_of_followings) {
        this.number_of_followings = number_of_followings;
    }


    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage(){return image;}

    public void setImage(String image){this.image = image;}

}
