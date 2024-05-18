package edu.javeriana.ingenieria.social.recurso.controlador;

import edu.javeriana.ingenieria.social.recurso.dominio.Recurso;
import edu.javeriana.ingenieria.social.recurso.servicio.ServicioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recursos")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorRecurso {
    @Autowired
    private ServicioRecurso servicio;

    @GetMapping("listar")
    public List<Recurso> obtenerRecursos(){ return servicio.obtenerRecursos();}

    @GetMapping("obtener")
    public ResponseEntity<Recurso> obtenerRecurso(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerRecurso(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerRecurso(id), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<Recurso> crearRecurso(@RequestBody Recurso recurso){
        if(recurso == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.recursoExiste(recurso.getId()) || servicio.recursoExiste(recurso.getDescripcion())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearRecurso(recurso), HttpStatus.CREATED);
    }

    @PutMapping("actualizar")
    public ResponseEntity<Recurso> actualizarRecurso(@RequestParam Integer id, @RequestBody Recurso recurso){
        if(id == null || recurso == null || !id.equals(recurso.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.actualizarRecurso(id, recurso) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recurso, HttpStatus.OK);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> eliminarRecurso(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.eliminarRecurso(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
