package com.upana.estudiante.controller;

import com.upana.estudiante.model.Student;
import com.upana.estudiante.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> verTodos() { return service.listar(); }

    @GetMapping("/{id}")
    public Student verUno(@PathVariable int id) { return service.obtener(id); }

    @PostMapping
    public Student agregar(@Valid @RequestBody Student s) { return service.crear(s); }

    @PutMapping("/{id}")
    public Student actualizar(@PathVariable int id, @Valid @RequestBody Student s) { return service.editarCompleto(id, s); }

    @PatchMapping("/{id}")
    public Student actualizarParcial(@PathVariable int id, @RequestBody Map<String, String> campos) { return service.editarParcial(id, campos); }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        service.eliminar(id);
        return "Estudiante eliminado";
    }
}