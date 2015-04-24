/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.PerfilJuego;
import beans.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bernardot
 */
public class GameHandler {
    
    private static Connection connection;

    public GameHandler() {
        try {
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost/jeopardy", "root", "");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean newPerfilJuego(String nombre, int userid) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO PerfilJuego (name, usuarioid) values ('"+nombre+"', '"+userid+"')";
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean insertPistaInPerfilJuego(String pid, String pjid, String seccion) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO PistaPerfilJuego (pid, pjid, seccion) values ('"+pid+"', '"+pjid+"', '"+seccion+"')";
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public ArrayList getPerfilJuegos(User usuario) {
        ArrayList perfiljuegos = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM PerfilJuego WHERE usuarioid=" + usuario.getId() + " ORDER BY id ASC");
            while (results.next()) {
                int id=results.getInt("id");
                String name=results.getString("nombre");
                int usuarioid=results.getInt("usuarioid");
                PerfilJuego p = new PerfilJuego(id, name, usuario, null);
                perfiljuegos.add(p);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return perfiljuegos;
    }
}
