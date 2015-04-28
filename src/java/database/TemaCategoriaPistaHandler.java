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

    /**
     * Método constructor sin parámetros. Las funciones van acceder a la Base de datos.
     */
    public TemaCategoriaPistaHandler() {
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost/jeopardy", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la lista de temas.
     * @return ArrayList temas
     */
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
    
    /**
     * Obtiene la lista de categorías
     * @return ArrayList categorias
     */
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
    
    /**
     * Obtiene la lista de categorías que cumplen con el temaid pasado en el parámetro.
     * @param temid
     * @return ArrayList categorias
     */
    public ArrayList getCategorias(int temid) {
        ArrayList categorias = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Categoria INNER JOIN Tema ON Categoria.temaid = Tema.id WHERE Categoria.temaid=" + temid + " ORDER BY Categoria.id ASC");
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
    
    /**
     * Obtiene la lista de pistas.
     * @return ArrayList pistas
     */
    public ArrayList getPistas() {
        ArrayList pistas = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Pista INNER JOIN Categoria ON Pista.categoriaid = Categoria.id INNER JOIN Tema ON Categoria.temaid = Tema.id ORDER BY Pista.puntos ASC");
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
                Categoria c = new Categoria(categoriaid, nombre, descripcion, t);
                Pista p = new Pista(id, redaccion, respuesta, puntos, c);
                pistas.add(p);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pistas;
    }
    
    /**
     * Obtiene la lista de pistas que cumplen con el temaid pasado como parámetro.
     * @param temid
     * @return ArrayList pistas
     */
    public ArrayList getPistas(int temid) {
        ArrayList pistas = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Pista INNER JOIN Categoria ON Pista.categoriaid = Categoria.id INNER JOIN Tema ON Categoria.temaid = Tema.id WHERE Categoria.temaid=" + temid + " ORDER BY Pista.puntos ASC");
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
                Categoria c = new Categoria(categoriaid, nombre, descripcion, t);
                Pista p = new Pista(id, redaccion, respuesta, puntos, c);
                pistas.add(p);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pistas;
    }
    
    /**
     * Obtiene una pista en base a su id.
     * @param id
     * @return Pista pista
     */
    public Pista getPista(int id) {
        Pista pista = new Pista();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Pista INNER JOIN Categoria ON Pista.categoriaid = Categoria.id INNER JOIN Tema ON Categoria.temaid = Tema.id WHERE Pista.id=" + id + " ORDER BY Pista.puntos ASC");
            while (results.next()) {
                String redaccion=results.getString("redaccion");
                String respuesta=results.getString("respuesta");
                int puntos=results.getInt("puntos");
                int categoriaid=results.getInt("categoriaid");
                String nombre=results.getString("nombre");
                String descripcion=results.getString("descripcion");
                String tema=results.getString("tema");
                int temaid=results.getInt("temaid");
                Tema t = new Tema(temaid, tema);
                Categoria c = new Categoria(categoriaid, nombre, descripcion, t);
                Pista p = new Pista(id, redaccion, respuesta, puntos, c);
                pista = p;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pista;
    }
    
    /**
     * Obtiene la lista de pistas que cumplen con la categoriaid pasado como parámetro
     * @param categorid
     * @return ArrayList pistas
     */
    public ArrayList getPistasC(int categorid) {
        ArrayList pistas = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Pista INNER JOIN Categoria ON Pista.categoriaid = Categoria.id INNER JOIN Tema ON Categoria.temaid = Tema.id WHERE Pista.categoriaid=" + categorid + " ORDER BY Pista.puntos ASC");
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
                Categoria c = new Categoria(categoriaid, nombre, descripcion, t);
                Pista p = new Pista(id, redaccion, respuesta, puntos, c);
                pistas.add(p);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pistas;
    }
    
    /**
     * Inserta un tema nuevo
     * @param tema
     * @return boolean true o false
     */
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
    
    /**
     * Inserta una categoría nueva.
     * @param categoria
     * @param descripcion
     * @param temaid
     * @return boolean true o false
     */
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
    
    /**
     * Inserta una pista nueva.
     * @param redaccion
     * @param respuesta
     * @param puntos
     * @param categoriaid
     * @return boolean true o false
     */
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
    
    /**
     * Modifica el valor del tipo de la tabla (db) con el id que especifiques.
     * @param id
     * @param tipo
     * @param valor
     * @param db
     * @return boolean true o false
     */
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
    
    /**
     * Borra de la base de datos la fila que cumpla con el id de la tabla (db)
     * @param id
     * @param db
     * @return boolean true o false
     */
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
