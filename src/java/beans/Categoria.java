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
public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;
    private Tema tema;

    /**
     * Método constructor sin parámetros
     */
    public Categoria() {
    }

    /**
     * Método constructor con parámetros
     * @param id
     * @param nombre
     * @param descripcion
     * @param tema
     */
    public Categoria(int id, String nombre, String descripcion, Tema tema) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tema = tema;
    }

    /**
     * Método que regresa el id.
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
     * Método que regresa el nombre.
     * @return String nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que modifica el valor de nombre.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que regresa la descripción.
     * @return String descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método que modifica la descripción.
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Método que regresa el objeto Tema.
     * @return Tema tema
     */
    public Tema getTema() {
        return tema;
    }

    /**
     * Método que modifica el objeto Tema.
     * @param tema
     */
    public void setTema(Tema tema) {
        this.tema = tema;
    }
}
