package com.mycompany.pruebatecnica.persistencia;

import com.mycompany.pruebatecnica.logica.Tareas;
import com.mycompany.pruebatecnica.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benyi
 */
/*
Se hace el control de nuestra persistencia, en la cual llamamos a los metodos o 
funciones de nuestra TareasJpaController 
*/

public class ControladoraPersistencia {
    
    TareasJpaController tareaJPA = new TareasJpaController(); //Creamos un objeto de TareasJpaController para estar llamando a esta misma
    
    //Se genera una funcion para generar tareas y se recibe un parametro completo de tarea
    public void crearTarea(Tareas tar) {
        tareaJPA.create(tar); //Se llama a la funcion create que esta en nuestro controller
    }
    
    //Se genera una funcion para eliminarTareas que recibe un id
    public void eliminarTarea(int id) {
        try {
            tareaJPA.destroy(id); //Se intenta destruir la tarea mediante el id
        } catch (NonexistentEntityException ex) {
            System.out.println("Tarea no existente"); //Si genera una exception se muestra este msj
        }
    }
    
    //Se genera una funcion para traer las tareas que recibe un id
    public Tareas traerTarea(int id) {
        //Como el findTareas no lanza exception y devuelve null, jamas entraria en catch
        Tareas tarea =tareaJPA.findTareas(id); //Se hace un objeto auxiliar Tareas con el metodo find  que se manda el id 
        if (tarea == null) //Si el id no existe entonces
        {
            System.out.println("Tarea no existente"); //Se muestra msj de que no existe
        }
        return tarea; //Si si existe se retorna el objeto tarea entero
    }
    
    //Funcion que retorna un arrayList para traer todos los ingresos de tareas
    public ArrayList<Tareas> traerListaTareas() {
        List <Tareas> lista = tareaJPA.findTareasEntities(); //Hacemos una lista con la funcion de findEntities
        ArrayList <Tareas> listaTareas = new ArrayList <Tareas> (lista); //Generamos nuestra ArrayList con la lista de arriba
        return listaTareas; //Retornamos la arrayList
    }

    //Funcion para editar las tareas
    public void editarTarea(Tareas tar) {
        try {
            tareaJPA.edit(tar); //Se llama al metodo edit
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex); //Si salta exception sale este msj
        }
    }
    
    //Hacemos un findTareasEntities para nuestra verificacion de existencias en el main que regresa un booleano
    public boolean findTareasEntities ()
    {
        List <Tareas> tareasExisten = tareaJPA.findTareasEntities(); //Creamos una lista de nuestras tareas
        if (tareasExisten.isEmpty()){ //Si la lista esta vacia
            System.out.println("No hay tareas existentes"); //Se muestra el mensaje
            return false; //Retornamos un valor false
        }
        return true; //Si no esta vacia se retorna un valor true
    }
    
}
