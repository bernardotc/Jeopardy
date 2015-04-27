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

    public Juego() {
    }

    public Juego(int id, Timestamp fecha, int perfiljuegoid) {
        this.id = id;
        this.fecha = fecha;
        this.perfiljuegoid = perfiljuegoid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerfiljuegoid() {
        return perfiljuegoid;
    }

    public void setPerfiljuegoid(int perfiljuegoid) {
        this.perfiljuegoid = perfiljuegoid;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
