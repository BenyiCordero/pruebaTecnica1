package com.mycompany.pruebatecnica;

import com.mycompany.pruebatecnica.logica.Controladora;
import com.mycompany.pruebatecnica.logica.Tareas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


/**
 *
 * @author benyi
 */
public class PruebaTecnica {
    
    public static Controladora control = new Controladora();
    public static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
    public static Scanner teclado = new Scanner (System.in);
    public static int id;
    public static int opcion;
    public static String descripcion;
    public static String fechText;
    public static String estatus;
    public static Date fecha;
    
    public static int menu ()
    {
        System.out.println("----MENU----");
        System.out.println("1---Crear Tarea");
        System.out.println("2---Ver Tarea");
        System.out.println("3---Ver Tareas");
        System.out.println("4---Actualizar Tarea");
        System.out.println("5---Eliminar Tarea");
        System.out.println("6---Salir");
        opcion = teclado.nextInt();
        return opcion;
    }
    
    public static void crear ()
    {
        teclado.nextLine(); //Limpiamos buffer de entrada
        System.out.println("Ingrese Descripcion de tarea");
        descripcion = teclado.nextLine();
        System.out.println("Ingrese fecha dd/MM/yyyy HH:mm:ss");
        fechText = teclado.nextLine();
        fecha = new Date ();
        try {
            fecha = formato.parse(fechText);
        }catch (ParseException e) {
            System.out.println("Fecha invalida, se usara la actual");
        }
        Tareas tar = new Tareas (id, descripcion, "Pendiente", fecha);
        control.CrearTarea(tar);
    }
    
    public static void eliminar ()
    {
        int desicion;
        System.out.println("Tareas existentes");
        verTareas();
        System.out.println("Ingrese id de tarea que desea eliminar");
        id = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Esta de acuerdo en eliminar la tarea con id " + id + "?");
        System.out.println("1---Si");
        System.out.println("2---No");
        desicion = teclado.nextInt();
        if (desicion == 1)
        {
            control.eliminarTarea(id);
        }
    }
    
    public static void verTarea ()
    {
        //comprobarExistencia();
        System.out.println("Ingrese id de tarea que desea ver");
        id = teclado.nextInt();
        teclado.nextLine();
        Tareas tareaBusqueda = control.traerTarea(id);
        if (tareaBusqueda != null)
        {
        System.out.println(tareaBusqueda.toString());
        }
    }
    
    public static void verTareas ()
    {
        ArrayList <Tareas> listaTareas = control.traerListaTareas();
        for (Tareas t : listaTareas)
        {
            System.out.println("Tarea: " + t.toString());
        }
    }
    
    public static void actualizarTarea ()
    {
        System.out.println("Tareas existentes");
        verTareas();
        System.out.println("Ingrese id de la tarea que desea modificar");
        id = teclado.nextInt();
        Tareas tareaBusqueda = control.traerTarea(id);
        System.out.println("La tarea que desea modificar es: " + tareaBusqueda.toString());
        System.out.println("Que desea modificar?");
        System.out.println("1---Descripcion");
        System.out.println("2---Estado");
        System.out.println("3---Fecha");
        opcion = teclado.nextInt();
        int op;
        switch (opcion)
        {
            case 1:
            System.out.println("Ingrese descripcion nueva");
            teclado.nextLine();
            descripcion = teclado.nextLine();
            tareaBusqueda.setDescripcion(descripcion);
            control.editarTarea(tareaBusqueda);
            break;
            case 2:
            System.out.println("Ingrese estado nuevo");
            if (tareaBusqueda.getEstado().equals("Pendiente"))
            {
                System.out.println("1---En progreso");
                System.out.println("2---Completada");
                op = teclado.nextInt();
                if (op == 1)
                {
                    tareaBusqueda.setEstado("En progreso");
                    control.editarTarea(tareaBusqueda);
                }else{
                    tareaBusqueda.setEstado("Completada");
                    control.editarTarea(tareaBusqueda);
                }
            }
            else if (tareaBusqueda.getEstado().equals("En progreso"))
            {
                System.out.println("1---Pendiente");
                System.out.println("2---Completada");
                op = teclado.nextInt();
                if (op == 1)
                {
                    tareaBusqueda.setEstado("Pendiente");
                    control.editarTarea(tareaBusqueda);
                }else{
                    tareaBusqueda.setEstado("Completada");
                    control.editarTarea(tareaBusqueda);
                }
            }
            else
            {
                System.out.println("1---Pendiente");
                System.out.println("2---En progreso");
                op = teclado.nextInt();
                if (op == 1)
                {
                    tareaBusqueda.setEstado("Pendiente");
                    control.editarTarea(tareaBusqueda);
                }else{
                    tareaBusqueda.setEstado("En progreso");
                    control.editarTarea(tareaBusqueda);
                }
            }
            break;
            case 3:
            System.out.println("Ingrese fecha nueva dd/MM/yyyy HH:mm:ss");
            teclado.nextLine();
            fechText = teclado.nextLine();
            fecha = new Date ();
            try {
                fecha = formato.parse(fechText);
            } catch (ParseException e){
                System.out.println("Fecha invalida, se usara la actual");
            }
            tareaBusqueda.setFechaLimite(fecha);
            control.editarTarea(tareaBusqueda);
            break;
        }
        
    }
    
    public static boolean comprobarExistencia ()
    {
        if (!control.comprobarTareas())
        {
            System.out.println("Volviendo al menu...");
            return false;
        }else{
            return true;
        }
    }
    
    public static void main(String[] args) {
        
        int opcion;
        
        do 
        {
            opcion = menu ();
            
            switch (opcion)
            {
                case 1:
                crear();
                break;
                case 2:
                if (!comprobarExistencia()){break;}
                verTarea();
                break;
                case 3:
                if (!comprobarExistencia()){break;}
                verTareas();
                break;
                case 4:
                if (!comprobarExistencia()){break;}
                actualizarTarea();
                break;
                case 5:
                if (!comprobarExistencia()){break;}
                eliminar();
                break;
                case 6:
                System.out.println("Saliendo");
                break;
                default:
                System.out.println("Opcion invalida");
                break;
            }
        }while (opcion != 6);
    }
}
