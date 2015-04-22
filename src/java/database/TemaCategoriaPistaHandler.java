/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Categoria;
import beans.Pista;
import beans.Tema;
import beans.User;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bernardot
 */
public class TemaCategoriaPistaHandler {

    private static Connection connection;

    public TemaCategoriaPistaHandler() {
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost/jeopardy", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList getTemas() {
        ArrayList temas = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Tema ORDER BY id ASC");
            while (results.next()) {
                int id=results.getInt("id");
                String tema=results.getString("tema");
                Tema t = new Tema(id, tema);
                temas.add(t);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temas;
    }
    
    public ArrayList getCategorias() {
        ArrayList categorias = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Categoria INNER JOIN Tema ON Categoria.temaid = Tema.id ORDER BY Categoria.id ASC");
            while (results.next()) {
                int id=results.getInt("id");
                String nombre=results.getString("nombre");
                String descripcion=results.getString("descripcion");
                int temaid=results.getInt("temaid");
                String tema=results.getString("tema");
                Tema t = new Tema(temaid, tema);
                Categoria c = new Categoria(id, nombre, descripcion, t);
                categorias.add(c);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;
    }
    
    public ArrayList getPistas() {
        ArrayList pistas = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Pista INNER JOIN Categoria ON Pista.categoriaid = Categoria.id INNER JOIN Tema ON Categoria.temaid = Tema.id");
            while (results.next()) {
                int id=results.getInt("id");
                String redaccion=results.getString("redaccion");
                String respuesta=results.getString("respuesta");
                int puntos=results.getInt("puntos");
                int categoriaid=results.getInt("categoriaid");
                String nombre=results.getString("nombre");
                String descripcion=results.getString("descripcion");
                String tema=results.getString("tema");
                int temaid=results.getInt("temaid");
                Tema t = new Tema(temaid, tema);
                Categoria c = new Categoria(id, nombre, descripcion, t);
                Pista p = new Pista(id, redaccion, respuesta, puntos, c);
                pistas.add(p);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pistas;
    }
    
    public ArrayList getPistas(int temid) {
        ArrayList pistas = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Pista INNER JOIN Categoria ON Pista.categoriaid = Categoria.id INNER JOIN Tema ON Categoria.temaid = Tema.id WHERE Categoria.temaid=" + temid);
            while (results.next()) {
                int id=results.getInt("id");
                String redaccion=results.getString("redaccion");
                String respuesta=results.getString("respuesta");
                int puntos=results.getInt("puntos");
                int categoriaid=results.getInt("categoriaid");
                String nombre=results.getString("nombre");
                String descripcion=results.getString("descripcion");
                String tema=results.getString("tema");
                int temaid=results.getInt("temaid");
                Tema t = new Tema(temaid, tema);
                Categoria c = new Categoria(id, nombre, descripcion, t);
                Pista p = new Pista(id, redaccion, respuesta, puntos, c);
                pistas.add(p);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pistas;
    }
    
    public boolean newTema(String tema) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Tema (tema) values ('"+tema+"')";
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean newCategoria(String categoria, String descripcion, int temaid) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Categoria (nombre, descripcion, temaid) values ('"+categoria+"','"+descripcion+"','"+temaid+"')";
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean newPista(String redaccion, String respuesta, int puntos, int categoriaid) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Pista (redaccion, respuesta, puntos, categoriaid) values ('"+redaccion+"','"+respuesta+"','"+puntos+"','"+categoriaid+"')";
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean modify(int id, String tipo, String valor, String db) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE " + db + " SET " + tipo + "='" + valor + "' WHERE id=" + id;
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
    
    public boolean delete(int id, String db) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM " + db + " WHERE id=" + id;
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
}
