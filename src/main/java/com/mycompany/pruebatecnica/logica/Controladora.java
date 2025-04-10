package com.mycompany.pruebatecnica.logica;

import com.mycompany.pruebatecnica.persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.List;

/*
Se agregan los metodos para que se puedan usar en el main, cada uno de los metodos o funciones
de aqui llaman a la controladora de persistencia que se encuentra en su paquete de persistencia
*/

/**
 *
 * @author benyi
 */
public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia(); //Se crea un objeto de ControladoraPersistencia para poder hacer uso de los metodos de la misma
    
    //Funcion para crear una tarea  y recibimos el objeto completo
    public void CrearTarea (Tareas tar)
    {
        controlPersis.crearTarea (tar);//Hacemos uso de la funcion crearTarea de nuestra controladora
    }
    
    //Funcion para eliminar la tarea y recbimos el id
    public void eliminarTarea (int id)
    {
        controlPersis.eliminarTarea (id); //Usamos la funcion eliminar tarea de nuestra controladora 
    }
    
    //Funcion para editar la tarea y recibimos el objeto completo
    public void editarTarea (Tareas tar)
    {
        controlPersis.editarTarea (tar); //Usamos la funcion de editarTarea
    }
    
    //Funcion para traer tarea mediante el id
    public Tareas traerTarea (int id)
    {
        return controlPersis.traerTarea (id); //Usamos la funcion de traer tarea
    }
    
    //Creamos una funcion de ArrayList para traer toda la lista de las tareas
    public ArrayList<Tareas> traerListaTareas ()
    {
        return controlPersis.traerListaTareas (); //Usamos la funcion traerListaTareas y la retornamos
    }
    
    //Metodo para comprobar las existencias de las tareas
    public boolean comprobarTareas ()
    {
        return controlPersis.findTareasEntities(); //Hacemos el uso de findTareasEntities
    }
    
}
