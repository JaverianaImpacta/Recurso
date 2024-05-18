package edu.javeriana.ingenieria.social.recurso.servicio;

import edu.javeriana.ingenieria.social.recurso.dominio.Recurso;
import edu.javeriana.ingenieria.social.recurso.repositorio.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioRecurso {
    @Autowired
    private RepositorioRecurso repositorio;

    public List<Recurso> obtenerRecursos(){
        return repositorio.findAll();
    }

    public Recurso obtenerRecurso(Integer id){
        return repositorio.findById(id).orElse(null);
    }

    public Recurso crearRecurso(Recurso recurso){
        return repositorio.save(recurso);
    }

    public Recurso actualizarRecurso(Integer id, Recurso recurso){
        if(repositorio.findById(id).orElse(null) == null){
            return null;
        }
        recurso.setId(id);
        return repositorio.save(recurso);
    }

    public Recurso eliminarRecurso(Integer id){
        Recurso aux = repositorio.findById(id).orElse(null);
        if(aux == null){
            return null;
        }
        repositorio.delete(aux);
        return aux;
    }

    public boolean recursoExiste(Integer id){
        return repositorio.existsById(id);
    }

    public boolean recursoExiste(String descripcion){
        return repositorio.existsByDescripcion(descripcion);
    }
}
