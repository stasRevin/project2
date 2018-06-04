package java114.utilities;

import java.io.*;
import java.util.*;


public interface PropertiesLoader {


    default Properties loadProperties(String propertiesFilePath) {

        Properties properties = new Properties();
        InputStream inputStream = null;

        try {

            inputStream = this.getClass().getResourceAsStream(propertiesFilePath);
            properties.load(inputStream);

        } catch (IOException inputOutputException) {

            inputOutputException.printStackTrace();

        } catch (Exception exception) {

            exception.printStackTrace();
        }

        return properties;

    }


}