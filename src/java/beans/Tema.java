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
public class Tema {
    private int id;
    private String tema;

    /**
     * Método constructor con parámetros
     * @param id
     * @param tema
     */
    public Tema(int id, String tema) {
        this.id = id;
        this.tema = tema;
    }

    /**
     * Método constructor sin parámetros
     */
    public Tema() {
    }

    /**
     * Regresa el id del Tema.
     * @return int id.
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
     * Regresa el tema del Tema.
     * @return String tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * Modifica el tema.
     * @param tema
     */
    public void setTema(String tema) {
        this.tema = tema;
    }
}
