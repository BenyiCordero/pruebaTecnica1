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
public class ControladoraPersistencia {
    
    TareasJpaController tareaJPA = new TareasJpaController();

    public void crearTarea(Tareas tar) {
        tareaJPA.create(tar);
    }

    public void eliminarTarea(int id) {
        try {
            tareaJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            System.out.println("Tarea no existente");
        }
    }

    public Tareas traerTarea(int id) {
        //Como el findTareas no lanza exception y devuelve null, jamas entraria en catch
        Tareas tarea =tareaJPA.findTareas(id);
        if (tarea == null)
        {
            System.out.println("Tarea no existente");
        }
        return tarea;
    }

    public ArrayList<Tareas> traerListaTareas() {
        List <Tareas> lista = tareaJPA.findTareasEntities();
        ArrayList <Tareas> listaTareas = new ArrayList <Tareas> (lista);
        return listaTareas;
    }

    public void editarTarea(Tareas tar) {
        try {
            tareaJPA.edit(tar);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean findTareasEntities ()
    {
        List <Tareas> tareasExisten = tareaJPA.findTareasEntities();
        if (tareasExisten.isEmpty()){
            System.out.println("No hay tareas existentes");
            return false;
        }
        return true;
    }
    
}
