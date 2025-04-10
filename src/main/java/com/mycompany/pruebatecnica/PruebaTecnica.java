package com.mycompany.pruebatecnica;

import com.mycompany.pruebatecnica.logica.Controladora; //Importamos la controladora
import com.mycompany.pruebatecnica.logica.Tareas; //Importamos la clase
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
    
    public static Controladora control = new Controladora(); //Creamos una controladora para llevar a cabo las acciones
    public static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //Agregamos formato para fechas
        
    public static Scanner teclado = new Scanner (System.in); //Entrada de usuario
    public static int id; //Id que estara recibiendo las tareas
    public static int opcion; //Variable global para tener control en switches a lo largo del programa 
    public static String descripcion; //Descripcion de la tarea
    public static String fechText; //Fecha de la tarea con formato String para que pueda ser ingresada
    public static Date fecha; //Formato de la fecha DATE que sera implementada a la base de datos
    
    //Un metodo para mostrar el menu y que ingrese una opcion
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
    
    //Metodo para crear una tarea
    public static void crear ()
    {
        teclado.nextLine(); //Limpiamos buffer de entrada
        System.out.println("Ingrese Descripcion de tarea");
        descripcion = teclado.nextLine();
        System.out.println("Ingrese fecha dd/MM/yyyy HH:mm:ss");
        fechText = teclado.nextLine();
        fecha = new Date (); //Si no es valido el ingreso de la fecha, se le asiganra la de hoy
        try {
            fecha = formato.parse(fechText); //Convertimos el ingreso del usuario a DATE
        }catch (ParseException e) {
            System.out.println("Fecha invalida, se usara la actual"); //Si la ingreso mal, se usa la de hoy
        }
        Tareas tar = new Tareas (id, descripcion, "Pendiente", fecha); //Se crea una nueva tarea
        control.CrearTarea(tar); //Se llama a la funcion crearTarea y se le asigna la tarea que creamos mediante nuestra controladora
    }
    
    //Metodo para eliminar un registro de alguna tarea existente
    public static void eliminar ()
    {
        System.out.println("Tareas existentes"); //Mensaje para mostrar las tareas que existen
        verTareas(); //Llamamos al metodo que hace la muestra de las tareas existentes
        System.out.println("Ingrese id de tarea que desea eliminar");
        id = teclado.nextInt(); //Ingresamos id de la tarea que deseamos eliminar
        teclado.nextLine(); //Limpiamos el buffer
        System.out.println("Esta de acuerdo en eliminar la tarea con id " + id + "?");
        System.out.println("1---Si"); //Preguntamos si esta seguro de querer eliminar esa tarea
        System.out.println("2---No"); //Si no,  regresa al menu
        opcion = teclado.nextInt();
        if (opcion == 1)
        {
            control.eliminarTarea(id); //Si fue 1 se elimina la tarea con ese id
        }
    }
    
    //Metodo para ver una tarea
    public static void verTarea ()
    {
        System.out.println("Ingrese id de tarea que desea ver");
        id = teclado.nextInt(); //Ingresamos el id de la tarea que queremos ver
        teclado.nextLine(); //Limpiamos buffer
        Tareas tareaBusqueda = control.traerTarea(id); //Creamos una nueva tarea de ayuda y llamamos a nuestra controladora y el metodo en ella de traer tarea
        if (tareaBusqueda != null)
        {
        System.out.println(tareaBusqueda.toString()); //Si existe la mostramos con el metodo toString
        }
    }
    
    //Metodo para ver todas las tareas de la DB
    public static void verTareas ()
    {
        ArrayList <Tareas> listaTareas = control.traerListaTareas(); //Creamos un ArrayList del tipo Tareas y llamamos a nuestra controladora para que traiga la lista de tareas completas
        for (Tareas t : listaTareas) //FOREACH para recorrer todo nuestro ArrayList
        {
            System.out.println("Tarea: " + t.toString()); //Mostramos cada una con el metodo toString
        }
    }
    
    //Metodo para actualizar los campos de las tareas
    public static void actualizarTarea ()
    {
        System.out.println("Tareas existentes");//Mensaje para indicar las tareas existentes
        verTareas(); //Llamamos a nuestro metodo verTareas que nos muestra todas las tareas existentes para que se puedan identificar los id mas facil
        System.out.println("Ingrese id de la tarea que desea modificar");
        id = teclado.nextInt(); //Ingresamos el id que queremos 
        Tareas tareaBusqueda = control.traerTarea(id); //Creamos un objeto de tipo Tareas para ayudarnos y con nuestra controladora traer la info de esa tarea con el id
        System.out.println("La tarea que desea modificar es: " + tareaBusqueda.toString()); //Mostramos la tarea que se quiere modificar
        System.out.println("Que desea modificar?"); //Pequenio menu para interactuar en que se quiere modificar
        System.out.println("1---Descripcion");
        System.out.println("2---Estado");
        System.out.println("3---Fecha");
        opcion = teclado.nextInt(); //Ingresamos la opcion que se necesita modificar 
        int op; //Creamos una variable interna para los if dentro del switch
        switch (opcion)
        {
            case 1: //En caso que se quiera modificar la descripcion
            System.out.println("Ingrese descripcion nueva");
            teclado.nextLine(); //Limpiamos el buffer
            descripcion = teclado.nextLine(); //Ingresamos la descripcion a nuestra variable global descripcion mediante nuestro teclado
            tareaBusqueda.setDescripcion(descripcion);//Seteamos la Descripcion a nuestra tarea de ayuda
            control.editarTarea(tareaBusqueda); //Llamamos a nuestro metodo editar tarea y mandamos la tarea de ayuda para que haga el update de la tarea original
            break;
            case 2: //En caso que se quiera modificar la descripcion
            System.out.println("Ingrese estado nuevo");
            if (tareaBusqueda.getEstado().equals("Pendiente")) //Si la descripcion actual es pendiente
            {
                //Menu que muestra las opciones validas 
                System.out.println("1---En progreso"); //Se podra cambiar a en progreso
                System.out.println("2---Completada");  //Se podra cambiar a completada
                op = teclado.nextInt(); //Ingresamos la opcion que queremos
                if (op == 1)
                {
                    tareaBusqueda.setEstado("En progreso"); //Seteamos el nuevo estado
                    control.editarTarea(tareaBusqueda); //Llamamos a nuestra controladora para que haga el update
                }else{
                    tareaBusqueda.setEstado("Completada"); //Seteamos el nuevo estado
                    control.editarTarea(tareaBusqueda); //llamamos  ala controladora para que haga el update
                }
            }
            else if (tareaBusqueda.getEstado().equals("En progreso")) //Si el estado actual es en progreso
            {
                //Menu de opciones validas
                System.out.println("1---Pendiente"); //Se podra cambiar a pendiente
                System.out.println("2---Completada"); //Se podra cambiar a completada
                op = teclado.nextInt(); //Ingresamos opcion 
                if (op == 1)
                {
                    tareaBusqueda.setEstado("Pendiente"); //Seteamos
                    control.editarTarea(tareaBusqueda); //Hacemos update
                }else{
                    tareaBusqueda.setEstado("Completada"); //Seteamos
                    control.editarTarea(tareaBusqueda);//Hacemos update
                }
            }
            else //Si esta en estado completada 
            {
                //Menu de opciones validas
                System.out.println("1---Pendiente"); //Se podra cambiar a pendiente
                System.out.println("2---En progreso"); //Se podra cambiar a en progreso
                op = teclado.nextInt(); //Ingresamos opcion
                if (op == 1)
                {
                    tareaBusqueda.setEstado("Pendiente"); //Seteamos
                    control.editarTarea(tareaBusqueda); //Llamamos a la controladora
                }else{
                    tareaBusqueda.setEstado("En progreso"); //Seteamos
                    control.editarTarea(tareaBusqueda); //Llamamos a controladora
                }
            }
            break;
            case 3: //En caso que se quiera cambiar la fecha
            System.out.println("Ingrese fecha nueva dd/MM/yyyy HH:mm"); //Formato valido
            teclado.nextLine(); //Limpiamos buffer
            fechText = teclado.nextLine(); //Ingresamos la fecha deseada
            fecha = new Date (); //Si no es correcto el ingreso se pondra la fecha actual
            try {
                fecha = formato.parse(fechText); //Hacemos la conversion de String a DATE
            } catch (ParseException e){
                System.out.println("Fecha invalida, se usara la actual"); //Se pone la actual y vuelve al menu
            }
            tareaBusqueda.setFechaLimite(fecha); //Seteamos
            control.editarTarea(tareaBusqueda); //Hacemos update
            break;
        }
        
    }
    
    //Funcion para comprobar que existan tareas para mostrar, actualizar o eliminar
    public static boolean comprobarExistencia () //Funcion que devuelve un valor boleano 
    {
        if (!control.comprobarTareas()) //Llamamos a nuestra controladora con la funcion de comprobar tareas y si es nula la lista
        {
            System.out.println("Volviendo al menu..."); //Se ejecuta el mensaje 
            return false; //Se devuelve false para el uso del switch en el main y que no se ejecute la siguiente instruccion y regrese a el menu
        }else{
            return true; //En cambio si hay elementos en la lista se devuelve un true
        }
    }
    
    public static void main(String[] args) {
                
        do  //Se hace un do while para repetir las opciones
        {
            menu (); //Se llama al metodo menu
            
            switch (opcion) //Se hace un switch evaluando nuestra variable opcion que se selecciono en el menu
            {
                case 1: 
                crear(); //Se llama al metodo para crear una tarea
                break;
                case 2:
                if (!comprobarExistencia()){break;} //Se verifica la existencia de tareas mediante el metodo comprobarExistencia y si no hay nada se va hacia menu
                verTarea(); //Se llama al metodo verTarea si hay existencias
                break;
                case 3:
                if (!comprobarExistencia()){break;} //Volvemos a verificar las existencias
                verTareas(); //Llamamos el metodo verTareas que nos muestra todas las tareas
                break;
                case 4:
                if (!comprobarExistencia()){break;} //Comprobamos existencias
                actualizarTarea(); //Llamamos al metodo actualizarTarea 
                break;
                case 5:
                if (!comprobarExistencia()){break;} //Volvemos a comprobar existencias
                eliminar(); //Llamamos al metodo eliminar para eliminar alguna tarea
                break;
                case 6:
                System.out.println("Saliendo"); //Salimos de nuestro programa
                break;
                default:
                System.out.println("Opcion invalida"); //Opcion invalida y volvemos a validar
                break;
            }
        }while (opcion != 6); //Se repite hasta que la opcion == 6
    }
}
