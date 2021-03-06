package java114.userData;

import java.util.*;
import java.io.*;
import java.sql.*;

import java114.utilities.*;

public class UserDirectory {

    private Properties properties;


    public UserDirectory() {

    }

    public UserDirectory(Properties properties) {

        this();
        this.properties = properties;
    }

    private Connection establishDatabaseConnection() {

        Connection connection = null;

        try {

            System.out.println("PROPERTY DRIVER: " + properties.getProperty("driver"));
            Class.forName(properties.getProperty("driver"));

            connection = DriverManager.getConnection( properties.getProperty("url"),
                                                      properties.getProperty("username"),
                                                      properties.getProperty("password"));

        } catch (ClassNotFoundException classNotFoundException) {

            classNotFoundException.printStackTrace();

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        } catch (Exception exception) {

            exception.printStackTrace();
        }

        return connection;


    }

    public String addUser(String firstName, String lastName, String gender,
                          String birthdateInput, String weightInput, String username,
                          String password) {

        Connection connection = null;
        PreparedStatement insertUpdate = null;
        int executionResult = 0;
        String message = "An error occurred. No users were added.";
        DataConverter dataConverter = new DataConverter();
        java.sql.Date birthdate = dataConverter.convertToDate(birthdateInput);
        int weight = dataConverter.convertToInt(weightInput);

        try {

            connection = establishDatabaseConnection();

            insertUpdate = connection.prepareStatement(
                  "INSERT INTO exercise_user (first_name, last_name, gender, "
                + "birthdate, weight, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)"
                );

            System.out.println("INSERT STATEMENT: " + insertUpdate);

            insertUpdate.setString(1, firstName);
            insertUpdate.setString(2, lastName);
            insertUpdate.setString(3, gender);
            insertUpdate.setDate(4, birthdate);
            insertUpdate.setInt(5, weight);
            insertUpdate.setString(6, username);
            insertUpdate.setString(7, password);

            executionResult = insertUpdate.executeUpdate();

            if (executionResult > 0) {

                message = firstName + " was added successfully.";

            } else if (executionResult == 0) {

                message = "No users were added, please provide correct entries.";
            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        } catch (Exception exception) {

            exception.printStackTrace();

        } finally {

            try {

                if (insertUpdate != null) {

                    insertUpdate.close();
                }

                if (connection != null) {

                    System.out.println("Closing db conn in addUser");
                    connection.close();
                }
            } catch (SQLException sqlException) {

                sqlException.printStackTrace();
            } catch (Exception exception) {

                exception.printStackTrace();

            }

        }


        return message;

    }


    public User login(String username, String password) {

        Connection connection = null;
        PreparedStatement query = null;
        ResultSet resultSet = null;
        User user = null;

        try {

            connection = establishDatabaseConnection();
            query = connection.prepareStatement(
                    "SELECT id, username, first_name, last_name, gender, birthdate,"
                +   " weight, photo_location FROM exercise_user WHERE username = ? AND password = ?");

            query.setString(1, username);
            query.setString(2, password);

            System.out.println("QUERY: " + query);

            resultSet = query.executeQuery();

            while (resultSet.next()) {

                user = new User();

                user.setUserId(resultSet.getString("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setGender(resultSet.getString("gender"));
                user.setBirthdate(resultSet.getString("birthdate"));
                user.setWeight(resultSet.getString("weight"));
                user.setUsername(resultSet.getString("username"));
                user.setPhotoLocation(resultSet.getString("photo_location"));

            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        } catch (Exception exception) {

            exception.printStackTrace();

        } finally {

            try {

                if (resultSet != null) {

                    resultSet.close();

                }

                if (query != null) {

                    query.close();
                }

                if (connection != null) {
                    System.out.println("Closing db conn in login");
                    connection.close();

                }
            } catch (SQLException sqlException) {

                sqlException.printStackTrace();

            } catch (Exception exception) {

                exception.printStackTrace();
            }
        }

        return user;
    }

    public int getUserId(String username) {

        Connection connection = null;
        PreparedStatement query = null;
        ResultSet resultSet = null;
        int id = -1;

        try {

            connection = establishDatabaseConnection();
            query = connection.prepareStatement("SELECT id FROM exercise_user "
                    + "WHERE username = ?");
            System.out.println("QUERY ID: " + query);
            query.setString(1, username);

            resultSet = query.executeQuery();

            while (resultSet.next()) {

                id = resultSet.getInt("id");

            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        } catch (Exception exception) {

            exception.printStackTrace();

        } finally {

            try {

                if (resultSet != null) {

                    resultSet.close();

                }

                if (query != null) {

                    query.close();
                }

                if (connection != null) {

                    System.out.println("Closing db conn in getUserId");
                    connection.close();

                }

            } catch (SQLException sqlException) {

                sqlException.printStackTrace();

            } catch (Exception exception) {

                exception.printStackTrace();

            }
        }

        return id;

    }

    public String addUserPhotoLocation(int id, String fileLocation) {

        Connection connection = null;
        PreparedStatement insertUpdate = null;
        String message = "An error occurred. The photo was not saved.";
        int executionResult = 0;

        try {

            connection = establishDatabaseConnection();

            insertUpdate = connection.prepareStatement("UPDATE exercise_user "
                        + " SET photo_location = ? WHERE id = ?");

            insertUpdate.setString(1, fileLocation);
            insertUpdate.setInt(2, id);

            executionResult = insertUpdate.executeUpdate();

            if (executionResult > 0) {

                message = "The photo was saved successfully at " + fileLocation;

            } else if (executionResult == 0) {

                message = "The photo was not saved.";
            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        } catch (Exception exception) {

            exception.printStackTrace();

        } finally {

            try {

                if (insertUpdate != null) {

                    insertUpdate.close();

                }

                if (connection != null) {
                    System.out.println("Closing db conn in addUserPhoto");
                    connection.close();
                }

            } catch (SQLException sqlException) {

                sqlException.printStackTrace();

            } catch (Exception exception) {

                exception.printStackTrace();

            }

        }

        return message;
    }

    public String logWorkout(String idInput, String dateInput, String typeInput,
                             String timeInput, String heartrateInput, int calories) {

        Connection connection = null;
        PreparedStatement insertWorkout = null;
        String message = "An error occurred. The workout was not logged.";
        int executionResult = 0;

        DataConverter dataConverter = new DataConverter();
        System.out.println("ID INPUT: " + idInput);
        int id = dataConverter.convertToInt(idInput);
        java.sql.Date date = dataConverter.convertToDate(dateInput);
        int type = dataConverter.convertToInt(typeInput);
        int time = dataConverter.convertToInt(timeInput);
        int heartrate = dataConverter.convertToInt(heartrateInput);


        try {

            String query = "INSERT INTO "
                    + " exercise_log (user_id, date, type, time_in_minutes, "
                    + "heartrate, calories) VALUES (?, ?, ?, ?, ?, ?)";

            System.out.println("WORKOUT QUERY: " + query);
            connection = establishDatabaseConnection();
            System.out.println("ALIVE AFTER CONNECTION: " + connection.toString());
            insertWorkout = connection.prepareStatement(query);



            insertWorkout.setInt(1, id);
            insertWorkout.setDate(2, date);
            insertWorkout.setInt(3, type);
            insertWorkout.setInt(4, time);
            insertWorkout.setInt(5, heartrate);
            insertWorkout.setInt(6, calories);

            executionResult = insertWorkout.executeUpdate();

            if (executionResult > 0) {

                message = "The workout was added successfully.";

            } else if (executionResult == 0) {

                message = "The workout was not added. ";
            }


        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        } catch (Exception exception) {

            exception.printStackTrace();

        } finally {

            try {

                if (insertWorkout != null) {

                    insertWorkout.close();
                }

                if (connection != null) {

                    System.out.println("Closing db conn in logWorkout");
                    connection.close();

                }

            } catch (SQLException sqlException) {

                sqlException.printStackTrace();

            } catch (Exception exception) {

                exception.printStackTrace();
            }

        }
        return message;
    }

    public List<Workout> getWorkout(int userId) {

        Connection connection = null;
        PreparedStatement query = null;
        ResultSet resultSet = null;
        List<Workout> workoutList = new ArrayList<>();

        try {

            connection = establishDatabaseConnection();
            String queryText = "SELECT date, name, time_in_minutes, heartrate, "
                    + "calories "
                    + "FROM exercise_view WHERE user_id = ?";

            System.out.println("getWorkout query text: " + queryText);
            query = connection.prepareStatement(queryText);

            query.setInt(1, userId);
            resultSet = query.executeQuery();

            while (resultSet.next()) {

                Workout workout = new Workout();
                workout.setDate(resultSet.getString("date"));
                workout.setType(resultSet.getString("name"));
                workout.setTime(resultSet.getString("time_in_minutes"));
                workout.setHeartRate(resultSet.getString("heartrate"));
                workout.setCaloriesBurned(resultSet.getString("calories"));

                workoutList.add(workout);

            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        } catch (Exception exception) {

            exception.printStackTrace();

        } finally {


            try {

                if (resultSet != null) {

                    resultSet.close();

                }

                if (query != null) {

                    query.close();

                }

                if (connection != null) {

                    connection.close();

                }

            } catch (SQLException sqlException) {

                sqlException.printStackTrace();

            } catch (Exception exception) {

                exception.printStackTrace();

            }

        }


       return workoutList;
    }

    public User updateProfile(String idInput, String firstName, String lastName,
                              String gender, String birthdateInput,
                              String weightInput, String photoLocation) {

        User user = null;
        Connection connection = null;
        PreparedStatement update = null;
        PreparedStatement query = null;
        ResultSet resultSet = null;
        int executionResult = 0;
        DataConverter dataConverter = new DataConverter();
        java.sql.Date birthdate = dataConverter.convertToDate(birthdateInput);
        int weight = dataConverter.convertToInt(weightInput);
        int id = dataConverter.convertToInt(idInput);

        try {

            connection = establishDatabaseConnection();

            String sqlText = "UPDATE exercise_user "
                    + "SET first_name = ?, last_name = ?, gender = ?, "
                    + "birthdate = ?, weight = ?, photo_location "
                    + "WHERE id = ?";

            System.out.println("UPDATE PROFILE QUERY: " + sqlText);
            update = connection.prepareStatement(sqlText);

            update.setString(1, firstName);
            update.setString(2, lastName);
            update.setString(3, gender);
            update.setDate(4, birthdate);
            update.setInt(5, weight);
            update.setString(6, photoLocation);
            update.setInt(7, id);

            executionResult = update.executeUpdate();

            System.out.println((executionResult > 0) ? "UPDATE success" : "UPDATE fail");

            sqlText = "SELECT id, username, first_name, last_name, gender, birthdate,"
                    + " weight, photo_location FROM exercise_user WHERE id = ?";


            query.setInt(1, id);
            query = connection.prepareStatement(sqlText);
            resultSet = query.executeQuery();

            while (resultSet.next()) {

                user = new User();

                user.setUserId(resultSet.getString("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setGender(resultSet.getString("gender"));
                user.setBirthdate(resultSet.getString("birthdate"));
                user.setWeight(resultSet.getString("weight"));
                user.setUsername(resultSet.getString("username"));
                user.setPhotoLocation(resultSet.getString("photo_location"));

            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        } catch (Exception exception) {

            exception.printStackTrace();

        } finally {

            try {

                if (update != null) {

                    update.close();
                }

                if (query != null) {

                    query.close();

                }

                if (resultSet != null) {

                    resultSet.close();
                }

                if (connection != null) {

                    connection.close();
                }

            } catch (SQLException sqlException) {

                sqlException.printStackTrace();

            } catch (Exception exception) {

                exception.printStackTrace();
            }

        }

        return user;
    }

}



