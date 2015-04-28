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
public class User {
    private int id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String correo;
    private String password;

    /**
     * Método constructor sin parámetros
     */
    public User() {
    }

    /**
     * Método constructor con parámetros
     * @param id
     * @param nombre
     * @param apellido
     * @param usuario
     * @param correo
     * @param password
     */
    public User(int id, String nombre, String apellido, String usuario, String correo, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.correo = correo;
        this.password = password;
    }

    /**
     * Regresa el id del User.
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
     * Regresa el nombre del User
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa el apellido del User.
     * @return String apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Modifica el apellido.
     * @param apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Regresa el usuario del User.
     * @return String usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Modifica el usuario del User.
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Regresa el correo del User.
     * @return String correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Modifica el correo.
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Regresa la password del User.
     * @return String password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifica la password.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
