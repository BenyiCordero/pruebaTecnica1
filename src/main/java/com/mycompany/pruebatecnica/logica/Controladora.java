package com.mycompany.pruebatecnica.logica;

import com.mycompany.pruebatecnica.persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author benyi
 */
public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    public void CrearTarea (Tareas tar)
    {
        controlPersis.crearTarea (tar);
    }
    
    public void eliminarTarea (int id)
    {
        controlPersis.eliminarTarea (id);
    }
    
    public void editarTarea (Tareas tar)
    {
        controlPersis.editarTarea (tar);
    }
    
    public Tareas traerTarea (int id)
    {
        return controlPersis.traerTarea (id);
    }
    
    public ArrayList<Tareas> traerListaTareas ()
    {
        return controlPersis.traerListaTareas ();
    }
    
    public boolean comprobarTareas ()
    {
        return controlPersis.findTareasEntities();
    }
    
}
