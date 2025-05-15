package es.progcipfpbatoi.todolistspring.model.repositories;

import es.progcipfpbatoi.todolistspring.exceptions.NotFoundException;
import es.progcipfpbatoi.todolistspring.exceptions.TareaYaExistenteException;
import es.progcipfpbatoi.todolistspring.model.entities.Tarea;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TareaRepository {

    private ArrayList<Tarea> tareas;

    public TareaRepository() {
        this.tareas = new ArrayList<>();
    }

    /**
     * Añade la Tarea recibida como argumento a la base de datos en memoria
     * @param tarea
     */
    public void add(Tarea tarea) throws TareaYaExistenteException {
    	if (tareas.contains(tarea)) {
    		throw new TareaYaExistenteException("La tarea con codigo "+tarea.getCodigo()+" ya esta añadida");
    	}else {
    		this.tareas.add(tarea);
    	} 
    }
    
    public void delete(Tarea tarea) throws NotFoundException {
    	if (!tareas.contains(tarea)) {
    		throw new NotFoundException("La tarea con codigo "+tarea.getCodigo()+" no existe");
    	}else {
    		tareas.remove(tarea);
    	}
    }

    /**
     * Obtiene la Tarea con codigo @codTarea. En caso de que no la encuentre devolverá una excepción
     * @NotFoundException
     *
     * @param codTarea
     */
    public Tarea get(int codTarea) throws NotFoundException {
    	for (Tarea tarea : tareas) {
            if (tarea.getCodigo() == codTarea) {
                return tarea;
            }
        }
        throw new NotFoundException("La tarea con codigo "+codTarea+" no existe");
    }

    /**
     *  Devuelve el listado de todas las tareas.
     */
    public ArrayList<Tarea> findAll() {
        return tareas;
    }

    /**
     *  Devuelve el listado de todas las tareas cuyo atributo nombre coincide con @user
     */
    public ArrayList<Tarea> findAll(String user) {
    	ArrayList<Tarea> tareasFiltradas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getNombre().equals(user)) {
                tareasFiltradas.add(tarea);
            }
        }
        return tareasFiltradas;
    }

}
