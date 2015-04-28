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
public class Pista {
    private int id;
    private String redaccion;
    private String respuesta;
    private int puntos;
    private Categoria categoria;

    /**
     * Método constructor sin parámetros
     */
    public Pista() {
    }

    /**
     * Método constructor con parámetros
     * @param id
     * @param redaccion
     * @param respuesta
     * @param puntos
     * @param categoria
     */
    public Pista(int id, String redaccion, String respuesta, int puntos, Categoria categoria) {
        this.id = id;
        this.redaccion = redaccion;
        this.respuesta = respuesta;
        this.puntos = puntos;
        this.categoria = categoria;
    }

    /**
     * Regresa el id de la pista.
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica el id.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Regresa la redacción de la pista.
     * @return String redaccion
     */
    public String getRedaccion() {
        return redaccion;
    }

    /**
     * Modifica la redacción.
     * @param redaccion
     */
    public void setRedaccion(String redaccion) {
        this.redaccion = redaccion;
    }

    /**
     * Regresa la respuesta de la pista.
     * @return String respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Modifica la respuesta.
     * @param respuesta
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Regresa los puntos de la pista.
     * @return int puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Modifica los puntos de la pista.
     * @param puntos
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * Regresa el objeto Categoría de la pista.
     * @return Categoría categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Modifica el objeto Categoria
     * @param categoria
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
