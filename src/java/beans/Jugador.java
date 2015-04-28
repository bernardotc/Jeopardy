/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author bernardot
 */
public class Jugador {
    private int id;
    private String nombre;

    /**
     * Método constructor sin parámetros
     */
    public Jugador() {
    }

    /**
     * Método constructor con parámetros
     * @param id
     * @param nombre
     */
    public Jugador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Método que regresa el id del Jugador.
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Método que modifica el id.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que regresa el nombre del Jugador.
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que modifica el nombre del Jugador.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
