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


}



