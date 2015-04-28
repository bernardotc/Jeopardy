/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Timestamp;

/**
 *
 * @author bernardot
 */
public class Juego {
    private int id;
    private Timestamp fecha;
    private int perfiljuegoid;

    /**
     * Método constructor sin parámetros
     */
    public Juego() {
    }

    /**
     * Método constructor con parámetros
     * @param id
     * @param fecha
     * @param perfiljuegoid
     */
    public Juego(int id, Timestamp fecha, int perfiljuegoid) {
        this.id = id;
        this.fecha = fecha;
        this.perfiljuegoid = perfiljuegoid;
    }

    /**
     * Método que regresa id.
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Método que modifica el valor de id.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que regresa el id de Perfil Juego.
     * @return int perfiljuegoid.
     */
    public int getPerfiljuegoid() {
        return perfiljuegoid;
    }

    /**
     * Método que modifica el valor de perfiljuegoid.
     * @param perfiljuegoid
     */
    public void setPerfiljuegoid(int perfiljuegoid) {
        this.perfiljuegoid = perfiljuegoid;
    }

    /**
     * Método que regresa una Timestamp de fecha.
     * @return Timestamp fecha 
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * Método que modifica el valor de la fecha.
     * @param fecha
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
