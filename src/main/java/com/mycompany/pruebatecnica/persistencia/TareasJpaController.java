/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pruebatecnica.persistencia;

import com.mycompany.pruebatecnica.logica.Tareas;
import com.mycompany.pruebatecnica.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author benyi
 */
/*
Controller que tiene todos los metodos como edit, destroy, create, find etc.
es de donde esta agarrando los metodos la controladora de persistencia

Mapeo: 
Metodos del main llaman a los metodos que estan en la logica en la controladora
La controladora de la logica manda llamar a los metodos que estan en la controladora de persistencia
La controladora de persistencia manda llamar a los metodos que estan aqui en el controller

En cada una de estas se crea un objeto de dicha controladora que esta debajo de la misma
*/
public class TareasJpaController implements Serializable {

    public TareasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public TareasJpaController ()
    {
        emf = Persistence.createEntityManagerFactory("pruebaTecnicaPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tareas tareas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tareas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tareas tareas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tareas = em.merge(tareas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tareas.getId();
                if (findTareas(id) == null) {
                    throw new NonexistentEntityException("The tareas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tareas tareas;
            try {
                tareas = em.getReference(Tareas.class, id);
                tareas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tareas with id " + id + " no longer exists.", enfe);
            }
            em.remove(tareas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tareas> findTareasEntities() {
        return findTareasEntities(true, -1, -1);
    }

    public List<Tareas> findTareasEntities(int maxResults, int firstResult) {
        return findTareasEntities(false, maxResults, firstResult);
    }

    private List<Tareas> findTareasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tareas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tareas findTareas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tareas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTareasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tareas> rt = cq.from(Tareas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
