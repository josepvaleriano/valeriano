package test.valeriano.mx.tarea.model;

import java.util.Date;

/**
 * Clase para manejar el objeto usuario recibe userName y password
 * Created by luis.valeriano on 23/06/2016.
 *
 */
public class ModelUser {
    public String userName;
    public String password;
    public Date last_Conection;
    public Date creation;

    /* Constructor de la clase con parámetros de entrada userName y password*/
    public ModelUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /*Constructor por defecto*/
    public ModelUser(){
        this.userName = "";
        this.password = "";
    }


}
