/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;

/**
 *
 * @author bernardot
 */
public class PerfilJuego {
    private int id;
    private String name;
    private User usuario;
    private ArrayList seccion1 = new ArrayList();
    private ArrayList seccion2 = new ArrayList();
    private ArrayList seccion3 = new ArrayList();
    private ArrayList seccion4 = new ArrayList();
    private ArrayList seccion5 = new ArrayList();
    private ArrayList seccion6 = new ArrayList();

    /**
     * Método constructor sin parámetros
     */
    public PerfilJuego() {
    }

    /**
     * Método constructor con parámetros
     * @param id
     * @param name
     * @param usuario
     */
    public PerfilJuego(int id, String name, User usuario) {
        this.id = id;
        this.name = name;
        this.usuario = usuario;
    }

    /**
     * Método que regresa el id del perfil
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
     * Método que regresa el nombre del perfil.
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Método que modifica el nombre.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método que regresa el User del perfil.
     * @return User usuario
     */
    public User getUsuario() {
        return usuario;
    }

    /**
     * Método que modifica el User.
     * @param usuario
     */
    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    /**
     * Método que regresa la sección 1 de pistas.
     * @return ArrayList seccion1
     */
    public ArrayList getSeccion1() {
        return seccion1;
    }

    /**
     * Méto que modifica la sección 1.
     * @param seccion1
     */
    public void setSeccion1(ArrayList seccion1) {
        this.seccion1 = seccion1;
    }

    /**
     * Método que regresa la sección 2 de pistas.
     * @return ArrayList seccion2
     */
    public ArrayList getSeccion2() {
        return seccion2;
    }

    /**
     * Método que modifica la sección 2.
     * @param seccion2
     */
    public void setSeccion2(ArrayList seccion2) {
        this.seccion2 = seccion2;
    }

    /**
     * Método que regresa la sección 3 de pistas.
     * @return ArrayList seccion3
     */
    public ArrayList getSeccion3() {
        return seccion3;
    }

    /**
     * Método que modifica la sección 3.
     * @param seccion3
     */
    public void setSeccion3(ArrayList seccion3) {
        this.seccion3 = seccion3;
    }

    /**
     * Método que regresa la sección 4 de pistas.
     * @return ArrayList seccion4
     */
    public ArrayList getSeccion4() {
        return seccion4;
    }

    /**
     * Método que modifica la seccion 4
     * @param seccion4
     */
    public void setSeccion4(ArrayList seccion4) {
        this.seccion4 = seccion4;
    }

    /**
     * Método que regresa la sección 5 de pistas.
     * @return ArrayList seccion5
     */
    public ArrayList getSeccion5() {
        return seccion5;
    }

    /**
     * Método que modifica la sección 5.
     * @param seccion5
     */
    public void setSeccion5(ArrayList seccion5) {
        this.seccion5 = seccion5;
    }

    /**
     * Método que regresa la sección 6 de pistas.
     * @return ArrayList seccion6
     */
    public ArrayList getSeccion6() {
        return seccion6;
    }

    /**
     * Método que modifica la sección 6.
     * @param seccion6
     */
    public void setSeccion6(ArrayList seccion6) {
        this.seccion6 = seccion6;
    }
}
