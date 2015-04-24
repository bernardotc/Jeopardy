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
    private ArrayList pistas;

    public PerfilJuego() {
    }

    public PerfilJuego(int id, String name, User usuario, ArrayList pistas) {
        this.id = id;
        this.name = name;
        this.usuario = usuario;
        this.pistas = pistas;
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

    public ArrayList getPistas() {
        return pistas;
    }

    public void setPistas(ArrayList pistas) {
        this.pistas = pistas;
    }
}
