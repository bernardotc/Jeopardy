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

    public PerfilJuego() {
    }

    public PerfilJuego(int id, String name, User usuario) {
        this.id = id;
        this.name = name;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public ArrayList getSeccion1() {
        return seccion1;
    }

    public void setSeccion1(ArrayList seccion1) {
        this.seccion1 = seccion1;
    }

    public ArrayList getSeccion2() {
        return seccion2;
    }

    public void setSeccion2(ArrayList seccion2) {
        this.seccion2 = seccion2;
    }

    public ArrayList getSeccion3() {
        return seccion3;
    }

    public void setSeccion3(ArrayList seccion3) {
        this.seccion3 = seccion3;
    }

    public ArrayList getSeccion4() {
        return seccion4;
    }

    public void setSeccion4(ArrayList seccion4) {
        this.seccion4 = seccion4;
    }

    public ArrayList getSeccion5() {
        return seccion5;
    }

    public void setSeccion5(ArrayList seccion5) {
        this.seccion5 = seccion5;
    }

    public ArrayList getSeccion6() {
        return seccion6;
    }

    public void setSeccion6(ArrayList seccion6) {
        this.seccion6 = seccion6;
    }
}
