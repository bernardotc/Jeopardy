/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bernardot
 */
public class userHandler {
    
    private static Connection connection;

    public userHandler() {
        try {
          
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mensajeria", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
