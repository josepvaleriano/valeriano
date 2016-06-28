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
    public Boolean rememberId;

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

    /* Constructor de la clase con parámetros de entrada userName y password*/
    public void setRememberId(Boolean rememberId) {
        this.rememberId = rememberId;
    }

    /*Obtiene la propieda del @nombre*/
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /*Obtiene la propieda del @password*/
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*Obtiene la propieda del @Fecha de conexión*/
    public Date getLast_Conection() {
        return last_Conection;
    }

    public void setLast_Conection(Date last_Conection) {
        this.last_Conection = last_Conection;
    }

    /*Obtiene la propieda de la @fecha de creación*/
    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    /*Obtiene la propieda del @recordar credenciales*/
    public Boolean getRememberId() {
        return rememberId;
    }
}
