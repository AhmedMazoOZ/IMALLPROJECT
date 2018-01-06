package totchi.apps.a.imall.ConnectorModel;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 3zoOz on 29/12/2017.
 */

public class Parser {
    boolean f = false;
    String data;
    ArrayList<User> followers;
    Context context;


    public String ParseRegisterResponse(String jsonFile) {
        try {
            f = true;
            JSONObject jsonObject = new JSONObject(jsonFile);
            if (!jsonObject.isNull("token")) {
                data = jsonObject.getString("token");
            } else if (!jsonObject.isNull("error")) {
                data = jsonObject.getString("error");
                f = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }
    public boolean isRegisterResponseValid() {
        return f;
    }

    public String ParseForgetResponse(String jsonFile) {

        try {
            f = true;
            JSONObject jsonObject = new JSONObject(jsonFile);
            if (!jsonObject.isNull("status")) {
                data = jsonObject.getString("status");
            } else if (!jsonObject.isNull("error")) {
                data = jsonObject.getString("error");
                f = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean isCodeValid(String jsonFile) {
        try {
            f = true;
            JSONObject jsonObject = new JSONObject(jsonFile);
            if (!jsonObject.isNull("status")) {
                data = jsonObject.getString("status");

            } else if (!jsonObject.isNull("error")) {
                data = jsonObject.getString("error");
                f = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return f;
    }
    public ArrayList<User> ParseFollowingsResponse(String jsonFile) {
        followers = new ArrayList<>();
        User user;
        try {
            JSONArray ja = new JSONArray(jsonFile);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                String ID = String.valueOf(jo.getInt("id"));
                String name = jo.getString("name");
                String isFollowing = String.valueOf(jo.getInt("isFollowing"));
                String image = jo.getString("img_path");

                user = new User();
                user.setName(name);
                user.setId(ID);
                user.setIsFollowing(isFollowing);
                user.setImage(image);
                followers.add(user);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return followers;
    }

    //profile
    public User FillProfile(String json) {
        User u1 = new User();
        JSONObject jo = null;

        try {

            jo = new JSONObject(json);
            String name = jo.getString("name");
            String email = jo.getString("email");
            String phone = jo.getString("phone");
            String image = jo.getString("img_path");
            String number_of_followers = jo.getString("followers");
            String number_of_followings = jo.getString("followings");
            String city = jo.getString("city");
            String date_of_birth = jo.getString("date_of_birth");

            if (name != "null")
                u1.setName(name);
            else u1.setName("No Name");
            if (email != "null")
                u1.setEmail(email);
            else u1.setEmail("No Email");
            if (phone != "null")
                u1.setPhone(phone);
            else u1.setPhone("No Phone");
            if (image != "null")
                u1.setImage(image);
            else
                u1.setImage("http://www.planetcreation.co.uk/createpic/my-avatar.JPG");


            if (number_of_followings != "null")
                u1.setNumber_of_followings(number_of_followings);
            else
                u1.setNumber_of_followings(" 0 ");

            if (city != "null")
                u1.setCity(city);
            else
                u1.setCity("No City");

            if (date_of_birth != "null")
                u1.setDate_of_birth(date_of_birth);
            else
                u1.setDate_of_birth("No Date of birth");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u1;
    }

    public User FillUserProfile(String json) {
        User u1 = new User();
        JSONObject jo = null;
        JSONObject joUser = null;

        try {
            joUser = new JSONObject(json);
            jo = joUser.getJSONObject("user");
            String name = jo.getString("name");
            String email = jo.getString("email");
            String phone = jo.getString("phone");
            String image = jo.getString("img_path");
            String msg = jo.getString("message");
            String battery = jo.getString("battery");
            String city = jo.getString("city");
            String date_of_birth = jo.getString("date_of_birth");

            if (name != "null")
                u1.setName(name);
            else u1.setName("No Name");
            if (email != "null")
                u1.setEmail(email);
            else u1.setEmail("No Email");
            if (phone != "null")
                u1.setPhone(phone);
            else u1.setPhone("No Phone");
            if (image != "null")
                u1.setImage(image);
            else
                u1.setImage("http://www.planetcreation.co.uk/createpic/my-avatar.JPG");
            if (msg != "null" && msg != null)
                u1.setMessage(msg);
            else u1.setMessage("No Messages");


            if (city != "null")
                u1.setCity(city);
            else
                u1.setCity("No City");

            if (date_of_birth != "null")
                u1.setDate_of_birth(date_of_birth);
            else
                u1.setDate_of_birth("No Date of birth");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u1;
    }

    public ArrayList<User> ParseSearchResponse(String jsonFile) {

        followers = new ArrayList<>();

        User user;

        try {
            JSONArray ja = new JSONArray(jsonFile);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);

                //Wanted Data
                String ID = String.valueOf(jo.getInt("id"));
///                String username=jo.getString("username");
                String name = jo.getString("name");
                String image = jo.getString("img_path");

                String isFollowing = String.valueOf(jo.getInt("isFollowing"));

                user = new User();
                ////  user.setUsername(username);
                user.setName(name);
                user.setId(ID);
                user.setIsFollowing(isFollowing);
                user.setImage(image);

                followers.add(user);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return followers;
    }

    public ArrayList<User> ParseNotificationResponse(String jsonFile) {
        followers = new ArrayList<>();
        User user;
        JSONArray all = null;
        JSONObject jo = null;

        try {
            all = new JSONArray(jsonFile);
            for (int x = 0; x < all.length(); x++) {
                jo = all.getJSONObject(x);
                String not_type = jo.getString("not_type");
                String message = jo.getString("message");
                //String image = jo.getString("img_path");
                user = new User();
                user.setMessage(message);
                //user.setImage(image);
                followers.add(user);
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return followers;
    }

    public ArrayList<User> ParseSentRequestResponse(String response) {
        followers = new ArrayList<>();
        User user;
        try {
            JSONArray ja = new JSONArray(response);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                //Wanted Data
                int ID = jo.getInt("id");
                String username = jo.getString("username");
                String name = jo.getString("name");
                user = new User();
                user.setUsername(username);
                user.setName(name);
                user.setId(String.valueOf(ID));
                followers.add(user);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return followers;
    }

}
