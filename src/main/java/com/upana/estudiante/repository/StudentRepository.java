package com.upana.estudiante.repository;

import com.upana.estudiante.model.Student;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private List<Student> lista = new ArrayList<>();
    private int contadorId = 1;

    public List<Student> findAll() {
        return lista;
    }

    public Student findById(int id) {
        for (Student s : lista) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            student.setId(contadorId++);
            lista.add(student);
        }
        return student;
    }

    public boolean delete(int id) {
        return lista.removeIf(s -> s.getId() == id);
    }
}