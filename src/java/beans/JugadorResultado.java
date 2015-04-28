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
public class JugadorResultado {
    private String nombre;
    private int puntos;

    /**
     * Método constructor sin parámetros
     */
    public JugadorResultado() {
    }

    /**
     * Método constructor con parámetros
     * @param nombre
     * @param puntos
     */
    public JugadorResultado(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }

    /**
     * Método que regresa el nombre del jugador.
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que modifica el nombre del jugador.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que regresa los puntos.
     * @return int puntos.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Método que modifica los puntos.
     * @param puntos
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
