package valeriano.com.aplaystore.model;

/**
 * Contenedor para el manejo de las aplicaciones
 * Created by luis.valeriano on 06/07/2016.
 */
public class ModelAplication {
    public String nameAplication;
    public String nameDeveloper;
    public int resourceId;
    public int instaled;

    /*
    * Contructor de la clase con los parametros de entrada @nameAplication
    * y @nameDeveloper
    * */
    public ModelAplication(String nameAplication, String nameDeveloper){
        this.nameAplication = nameAplication;
        this.nameDeveloper = nameDeveloper;
    }

    /*
    * Contructor de la clase con los parametros de entrada @nameAplication
    * y @nameDeveloper
    * */
    public ModelAplication(String nameAplication, String nameDeveloper, int resourceId, int instaled){
        this.nameAplication = nameAplication;
        this.nameDeveloper = nameDeveloper;
        this.resourceId = resourceId;
        this.instaled = instaled;
    }
    /*
    * Constructor ModelAplication por defecto*/
    public ModelAplication(){
        this.nameAplication = "";
        this.nameDeveloper = "";
    }

    /*Método para obtener el nombre de la aplicación*/
    public String getNameAplication() {
        return nameAplication;
    }

    /*Método para establecer el nombre de la aplicación*/
    public void setNameAplication(String nameAplication) {
        this.nameAplication = nameAplication;
    }

    /*Método para obtener el nombre del desarrollador*/
    public String getNameDeveloper() {
        return nameDeveloper;
    }

    /*Método para establecer  el nombre del desarrollador */
    public void setNameDeveloper(String nameDeveloper) {
        this.nameDeveloper = nameDeveloper;
    }

    /*Método para obtener el recurso id de la imagen  */
    public int getResourceId() {
        return resourceId;
    }

    /*Método para establecer el recurso id de la imagen  */
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    /*Método para obtener el estado de instalación */
    public int getInstaled() {
        return instaled;
    }

    /*Método para establecer el estado de instalación */
    public void setInstaled(int instaled) {
        this.instaled = instaled;
    }

}
