/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bernardot
 */
public class UserHandler {
    
    private static Connection connection;

    /**
     * Método constructor sin parámetros. Las funciones van acceder a la Base de datos.
     */
    public UserHandler() {
        try {
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost/jeopardy", "root", "");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Obtiene el usuario con su user y password
     * @param user
     * @param password
     * @return User usr
     */
    public User getUser(String user, String password) {
        User usr = null;
        try {            
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Usuario WHERE usuario='"+user+"' and password='"+password+"'");
            while (results.next()) {
                int id = results.getInt("id");
                String nombre=results.getString("nombre");
                String apellido=results.getString("apellido");
                String usuario=results.getString("usuario");
                String correo=results.getString("correo");
                String passwod=results.getString("password");
                System.out.println(passwod);
                usr = new User(id, nombre, apellido, usuario, correo, passwod);
            }
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usr;
    }
    
    /**
     * Obtiene el Usuario con id (i)
     * @param i
     * @return User usr
     */
    public User getUser(int i) {
        User usr = null;
        try {            
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Usuario WHERE id=" + i);
            while (results.next()) {
                int id = results.getInt("id");
                String nombre=results.getString("nombre");
                String apellido=results.getString("apellido");
                String usuario=results.getString("usuario");
                String correo=results.getString("correo");
                String passwod=results.getString("password");
                System.out.println(passwod);
                usr = new User(id, nombre, apellido, usuario, correo, passwod);
            }
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usr;
    }
    
    /**
     * Inserta un usuario en la base de datos.
     * @param n
     * @param u
     * @param a
     * @param c
     * @param p
     * @return Boolean true o false
     */
    public boolean newUser(String n, String u, String a, String c, String p) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Usuario (usuario, nombre, apellido, correo, password) values ('" + u + "','" + n + "','" + a + "','" + c + "','" + p + "')";
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Modifica el password de un usuario
     * @param userid
     * @param newPass
     * @return boolean true o false
     */
    public boolean updatePassword(int userid, String newPass) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE Usuario SET password='" + newPass + "' WHERE id=" + userid ;
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
