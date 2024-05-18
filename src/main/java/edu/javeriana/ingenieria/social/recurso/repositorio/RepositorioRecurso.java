package edu.javeriana.ingenieria.social.recurso.repositorio;

import edu.javeriana.ingenieria.social.recurso.dominio.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioRecurso extends JpaRepository<Recurso, Integer> {
    boolean existsByDescripcion(String descripcion);
}
