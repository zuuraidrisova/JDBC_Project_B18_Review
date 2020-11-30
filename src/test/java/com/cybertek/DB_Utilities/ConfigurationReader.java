package com.cybertek.DB_Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    //our private static variable
    private static Properties properties;

    //static block is used because it runs once and before anything else
    //we need our configuration data before anything else
    static {

        //try/catch block is handling checked exception which FileInputStream is throwing
        try {

            //we open the file from our file using the file path
            FileInputStream file = new FileInputStream("configuration.properties");

            //create an object of Properties class
            properties = new Properties();

            //load the file into memory
            properties.load(file);

            //close the file
            file.close();

        }catch (IOException e){

            e.printStackTrace();
            throw new RuntimeException("Unable to find configuration.properties file!");

        }

    }


    //we create getter method for our private variable
    public static String getProperty(String keyWord){

        //getProperty() comes from java to read properties
       return properties.getProperty(keyWord);

    }


}
