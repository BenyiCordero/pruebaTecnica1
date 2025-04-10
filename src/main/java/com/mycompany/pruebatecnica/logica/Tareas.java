package com.mycompany.pruebatecnica.logica;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author benyi
 */
/*
Clase principal donde hacemos el ORM mapeo de nuestra entidad e implementamos 
los metodos para crear los objetos y asignamos atributos a la clase, junto con 
los getters y setters
*/

//Hacemos el mapeo que esta sera una entidad
@Entity
public class Tareas implements Serializable {
    //Marcamos el parametro que sea nuestra primary Key
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY) //Hacemos una generatedValue que sea incremental i+1
    private int id;
    //Definimos los parametros basicos de nuestra entidad
    @Basic
    private String descripcion;
    private String estado;
    //Mapeamos y definimos que sera una fecha
    @Temporal (TemporalType.TIMESTAMP) //Acepta dia, mes, anio, hora, minuto
    private Date fechaLimite;
    
    //Metodo para crear tareas vacias
    public Tareas() {
    }
    
    //Metodo para crear tareas con todos los atributos
    public Tareas(int id, String descripcion, String estado, Date fechaLimite) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaLimite = fechaLimite;
    }
    
    //Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    //Metodo to string para poder mostrar los formatos 
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //Muestra la fecha mas ordenada
        return "Tareas{" + "id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", fechaLimite=" + sdf.format(fechaLimite) + '}';
    }
    
}
