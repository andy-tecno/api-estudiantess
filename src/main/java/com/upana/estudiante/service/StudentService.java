package com.upana.estudiante.service;

import com.upana.estudiante.model.Student;
import com.upana.estudiante.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> listar() {
        return repo.findAll();
    }

    public Student obtener(int id) {
        Student s = repo.findById(id);
        if (s == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado");
        return s;
    }

    public Student crear(Student nuevo) {
        for (Student s : repo.findAll()) {
            if (s.getCorreo().equalsIgnoreCase(nuevo.getCorreo())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya está registrado");
            }
        }
        nuevo.setId(null);
        return repo.save(nuevo);
    }

    public Student editarCompleto(int id, Student datos) {
        Student actual = obtener(id);
        for (Student s : repo.findAll()) {
            if (s.getCorreo().equalsIgnoreCase(datos.getCorreo()) && s.getId() != id) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya pertenece a otro estudiante");
            }
        }
        actual.setNombre(datos.getNombre());
        actual.setCorreo(datos.getCorreo());
        actual.setNumero_telefono(datos.getNumero_telefono());
        actual.setIdioma(datos.getIdioma());
        return actual;
    }

    public Student editarParcial(int id, Map<String, String> campos) {
        Student actual = obtener(id);
        if (campos.containsKey("nombre")) actual.setNombre(campos.get("nombre"));
        if (campos.containsKey("correo")) actual.setCorreo(campos.get("correo"));
        if (campos.containsKey("numero_telefono")) actual.setNumero_telefono(campos.get("numero_telefono"));
        if (campos.containsKey("idioma")) actual.setIdioma(campos.get("idioma"));
        return actual;
    }

    public void eliminar(int id) {
        if (!repo.delete(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante no encontrado");
    }
}