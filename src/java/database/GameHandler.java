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
            String query = "INSERT INTO PerfilJuego (name, usuarioid) values ('" + nombre + "', '" + userid + "')";
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

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
    
    public Juego getJuego(int perfilId) {
        Juego j = new Juego();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Juego WHERE id='" + perfilId + "'");
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
    
    public ArrayList getResultados(int juegoId) {
        ArrayList resultados = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT Jugador.nombre, SUM(Pista.puntos) AS points FROM Juego INNER JOIN Resultado ON Juego.id=Resultado.juegoid INNER JOIN Jugador ON Resultado.jugadorid=Jugador.id INNER JOIN Pista ON Pista.id=Resultado.pistaid WHERE Juego.id=" 
                    + juegoId + " GROUP BY Jugador.nombre");
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
    
    public ArrayList getConcentradoFinal(int usuarioId) {
        ArrayList resultados = new ArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT Jugador.nombre, SUM(Pista.puntos) AS points FROM Usuario INNER JOIN PerfilJuego ON Usuario.id=PerfilJuego.usuarioid INNER JOIN Juego ON Juego.perfiljuegoid=PerfilJuego.id INNER JOIN Resultado ON Juego.id=Resultado.juegoid INNER JOIN Jugador ON Resultado.jugadorid=Jugador.id INNER JOIN Pista ON Pista.id=Resultado.pistaid WHERE Usuario.id=" +
                    usuarioId + " GROUP BY Jugador.nombre");
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
