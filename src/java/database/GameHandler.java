/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Categoria;
import beans.Juego;
import beans.Jugador;
import beans.PerfilJuego;
import beans.Pista;
import beans.JugadorResultado;
import beans.Tema;
import beans.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bernardot
 */
public class GameHandler {

    private static Connection connection;

    /**
     * Método constructor sin parámetros. Las funciones van acceder a la Base de datos.
     */
    public GameHandler() {
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost/jeopardy", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserta un PerfilJuego en la base de datos
     * @param nombre
     * @param userid
     * @return boolean true o false
     */
    public boolean newPerfilJuego(String nombre, int userid) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO PerfilJuego (name, usuarioid) values ('" + nombre + "', '" + userid + "')";
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Inserta contenido en la tabla PistaPerfilJuego de la base de datos.
     * @param pid
     * @param pjid
     * @param seccion
     * @param lugar
     * @return boolean true o false
     */
    public boolean insertPistaInPerfilJuego(String pid, String pjid, String seccion, int lugar) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO PistaPerfilJuego (pid, pjid, seccion, lugar) values ('" + pid + "', '" + pjid + "', '" + seccion + "', '" + lugar + "')";
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Obtiene los Perfiles de juegos en base a un usuario.
     * @param usuario
     * @return ArrayList perfiljuegos
     */
    public ArrayList getPerfilJuegos(User usuario) {
        ArrayList perfiljuegos = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM PerfilJuego WHERE usuarioid=" + usuario.getId() + " ORDER BY id ASC");
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                PerfilJuego p = new PerfilJuego(id, name, usuario);
                perfiljuegos.add(p);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return perfiljuegos;
    }

    /**
     * Obtiene el Perfil de Juego en base al usuario y el id del Perfil.
     * @param usuario
     * @param i
     * @return PerfilJuego pj
     */
    public PerfilJuego getPerfilJuegos(User usuario, int i) {
        PerfilJuego pj = new PerfilJuego();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM PerfilJuego WHERE usuarioid=" + usuario.getId() + " AND id=" + i + " ORDER BY id ASC");
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                pj = new PerfilJuego(id, name, usuario);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pj;
    }

    /**
     * Obtiene las Pistas en base a un id de un Perfil de Juego y una seccion
     * @param pjid
     * @param seccion
     * @return ArrayList pistas
     */
    public ArrayList getPistasPerfilJuegos(int pjid, int seccion) {
        ArrayList pistas = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM PistaPerfilJuego INNER JOIN Pista ON pid=Pista.id INNER JOIN Categoria ON Pista.categoriaid = Categoria.id INNER JOIN Tema ON Categoria.temaid = Tema.id WHERE pjid=" + pjid + " AND seccion=" + seccion);
            while (results.next()) {
                int id = results.getInt("pid");
                String redaccion = results.getString("redaccion");
                String respuesta = results.getString("respuesta");
                int puntos = results.getInt("puntos");
                int categoriaid = results.getInt("categoriaid");
                String nombre = results.getString("nombre");
                String descripcion = results.getString("descripcion");
                String tema = results.getString("tema");
                int temaid = results.getInt("temaid");
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
     * Modifica el Perfil de juego
     * @param id
     * @param name
     * @return boolean true or false
     */
    public boolean updatePerfilJuego(int id, String name) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE PerfilJuego SET name='" + name + "' WHERE id=" + id;
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Modifica las Pistas de un perfil de Juego
     * @param pid
     * @param pjid
     * @param seccion
     * @param lugar
     * @return boolean true or false.
     */
    public boolean updatePistaPerfilJuego(int pid, int pjid, int seccion, int lugar) {
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM PistaPerfilJuego WHERE lugar=" + lugar + " AND pjid=" + pjid + " AND seccion=" + seccion);
            while (results.next()) {
                int id = results.getInt("id");
                String query = "UPDATE PistaPerfilJuego SET pid='" + pid + "' WHERE id=" + id;
                statement.executeUpdate(query);
            }
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Obteien un jugador en base a su nombre.
     * @param nom
     * @return Jugador j
     */
    public Jugador getJugador(String nom) {
        Jugador j = new Jugador();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Jugador WHERE nombre='" + nom + "'");
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("nombre");
                j = new Jugador(id, name);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j;
    }
    
    /**
     * Obtiene un jugador de la base de datos en base a su id.
     * @param jid
     * @return Jugador j.
     */
    public Jugador getJugador(int jid) {
        Jugador j = new Jugador();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Jugador WHERE id=" + jid);
            while (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("nombre");
                j = new Jugador(id, name);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j;
    }
    
    /**
     * Inserta un jugador en la base de datos  y luego lo regresa.
     * @param nombre
     * @return Jugador
     */
    public Jugador insertJugador(String nombre) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Jugador (nombre) values ('" + nombre + "')";
            statement.executeUpdate(query);
            statement.close();
            return getJugador(nombre);
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Obtiene un juego en base a su id
     * @param Id
     * @return Juego j
     */
    public Juego getJuego(int Id) {
        Juego j = new Juego();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Juego WHERE id='" + Id + "'");
            while (results.next()) {
                int id = results.getInt("id");
                Timestamp date = results.getTimestamp("fecha");
                int perfilJuegoId = results.getInt("perfiljuegoid");
                j = new Juego(id, date, perfilJuegoId);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j;
    }
    
    /**
     * Inserta un Juego con el id de un PerfilJuego
     * @param id
     * @return Juego creado
     */
    public Juego insertJuego(int id) {
        try {
            int juegoId = 0;
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Juego (fecha, perfiljuegoid) values (NOW(), '" + id + "')";
            statement.executeUpdate(query);
            query = "SELECT last_insert_id() as lastId FROM Juego";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                juegoId = results.getInt("lastId");
            }
            statement.close();
            return getJuego(juegoId);
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Inserta el Resultado de un juego en la base de datos.
     * @param juegoId
     * @param jugadorId
     * @param pistaId
     * @return boolean true o false
     */
    public boolean insertResultado(int juegoId, int jugadorId, int pistaId) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Resultado (juegoid, jugadorid, pistaid) values ('" + juegoId + "', '" + jugadorId + "', '" + pistaId + "')";
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Obtiene el resultado de un juego.
     * @param juegoId
     * @return ArrayList resultados
     */
    public ArrayList getResultados(int juegoId) {
        ArrayList resultados = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT Jugador.nombre, SUM(Pista.puntos) AS points FROM Juego INNER JOIN Resultado ON Juego.id=Resultado.juegoid INNER JOIN Jugador ON Resultado.jugadorid=Jugador.id INNER JOIN Pista ON Pista.id=Resultado.pistaid WHERE Juego.id=" 
                    + juegoId + " GROUP BY Jugador.nombre ORDER BY points DESC");
            while (results.next()) {
                String nombre = results.getString("nombre");
                int points = results.getInt("points");
                JugadorResultado aux = new JugadorResultado(nombre, points);
                resultados.add(aux);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultados;
    }
    
    /**
     * Obtiene el concentrado de todos los juegos del usuario.
     * @param usuarioId
     * @return ArrayList resultados
     */
    public ArrayList getConcentradoFinal(int usuarioId) {
        ArrayList resultados = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT Jugador.nombre, SUM(Pista.puntos) AS points FROM Usuario INNER JOIN PerfilJuego ON Usuario.id=PerfilJuego.usuarioid INNER JOIN Juego ON Juego.perfiljuegoid=PerfilJuego.id INNER JOIN Resultado ON Juego.id=Resultado.juegoid INNER JOIN Jugador ON Resultado.jugadorid=Jugador.id INNER JOIN Pista ON Pista.id=Resultado.pistaid WHERE Usuario.id=" +
                    usuarioId + " GROUP BY Jugador.nombre ORDER BY points DESC");
            while (results.next()) {
                String nombre = results.getString("nombre");
                int points = results.getInt("points");
                JugadorResultado aux = new JugadorResultado(nombre, points);
                resultados.add(aux);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultados;
    } 
}
