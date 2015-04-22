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

    public Pista() {
    }

    public Pista(int id, String redaccion, String respuesta, int puntos, Categoria categoria) {
        this.id = id;
        this.redaccion = redaccion;
        this.respuesta = respuesta;
        this.puntos = puntos;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRedaccion() {
        return redaccion;
    }

    public void setRedaccion(String redaccion) {
        this.redaccion = redaccion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
