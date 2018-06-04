package java114.userData;

import java.sql.*;
import java.util.*;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private char gender;
    private String birthdate;
    private int weight;
    private String username;
    private String password;

    public User() {
    }


    /**
     * Returns the value of userId.
     */
    public int getUserId() {
        return userId;
    }
    
    
    /**
     * Sets the value of userId.
     * @param userId The value to assign userId.
     */
    public void setUserId(int userId) {
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
    public char getGender() {
        return gender;
    }
    
    
    /**
     * Sets the value of gender.
     * @param gender The value to assign gender.
     */
    public void setGender(char gender) {
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
    public int getWeight() {
        return weight;
    }
    
    
    /**
     * Sets the value of weight.
     * @param weight The value to assign weight.
     */
    public void setWeight(int weight) {
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
    
    
    /**
     * Returns the value of password.
     */
    public String getPassword() {
        return password;
    }
    
    
    /**
     * Sets the value of password.
     * @param password The value to assign password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}