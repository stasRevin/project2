package java114.userData;

import java.sql.*;
import java.util.*;

public class User {

    private String userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String birthdate;
    private String weight;
    private String username;
    private String photoLocation;



    public User() {
    }


    /**

     * Returns the value of userId.

     */

    public String getUserId() {

        return userId;

    }



    /**

     * Sets the value of userId.

     * @param userId The value to assign userId.

     */

    public void setUserId(String userId) {

        this.userId = userId;

    }





    /**

     * Returns the value of firstName.

     */

    public String getFirstName() {

        return firstName;

    }





    /**

     * Sets the value of firstName.

     * @param firstName The value to assign firstName.

     */

    public void setFirstName(String firstName) {

        this.firstName = firstName;

    }





    /**

     * Returns the value of lastName.

     */

    public String getLastName() {

        return lastName;

    }





    /**

     * Sets the value of lastName.

     * @param lastName The value to assign lastName.

     */

    public void setLastName(String lastName) {

        this.lastName = lastName;

    }





    /**

     * Returns the value of gender.

     */

    public String getGender() {

        return gender;

    }





    /**

     * Sets the value of gender.

     * @param gender The value to assign gender.

     */

    public void setGender(String gender) {

        this.gender = gender;

    }





    /**

     * Returns the value of birthdate.

     */

    public String getBirthdate() {

        return birthdate;

    }





    /**

     * Sets the value of birthdate.

     * @param birthdate The value to assign birthdate.

     */

    public void setBirthdate(String birthdate) {

        this.birthdate = birthdate;

    }





    /**

     * Returns the value of weight.

     */

    public String getWeight() {

        return weight;

    }





    /**

     * Sets the value of weight.

     * @param weight The value to assign weight.

     */

    public void setWeight(String weight) {

        this.weight = weight;

    }


    /**

     * Returns the value of username.

     */

    public String getUsername() {

        return username;

    }


    /**

     * Sets the value of username.

     * @param username The value to assign username.

     */

    public void setUsername(String username) {

        this.username = username;

    }


    public String getPhotoLocation() {

        return photoLocation;

    }

    public void setPhotoLocation(String photoLocation) {

        this.photoLocation = photoLocation;
    }




}