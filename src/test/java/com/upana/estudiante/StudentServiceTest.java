package com.upana.estudiante;

import com.upana.estudiante.model.Student;
import com.upana.estudiante.repository.StudentRepository;
import com.upana.estudiante.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentRepository repoMock;
    private StudentService studentService;
    private Student estudianteEjemplo;

    @BeforeEach
    public void setUp() {
        repoMock = Mockito.mock(StudentRepository.class);
        studentService = new StudentService(repoMock);

        estudianteEjemplo = new Student();
        estudianteEjemplo.setId(1);
        estudianteEjemplo.setNombre("Andy Sis");
        estudianteEjemplo.setCorreo("andy.sis@example.com");
        estudianteEjemplo.setNumero_telefono("1234567890");
        estudianteEjemplo.setIdioma("español");
    }

    @Test
    public void testListarEstudiantes() {
        Mockito.when(repoMock.findAll()).thenReturn(Arrays.asList(estudianteEjemplo));

        List<Student> lista = studentService.listar();

        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("Andy Sis", lista.get(0).getNombre());
        Mockito.verify(repoMock).findAll();
    }

    @Test
    public void testObtenerEstudiantePorId() {
        Mockito.when(repoMock.findById(1)).thenReturn(estudianteEjemplo);

        Student encontrado = studentService.obtener(1);

        assertNotNull(encontrado);
        assertEquals("andy.sis@example.com", encontrado.getCorreo());
        Mockito.verify(repoMock).findById(1);
    }

    @Test
    public void testObtenerEstudianteNoExistente() {
        Mockito.when(repoMock.findById(99)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> {
            studentService.obtener(99);
        });
    }

    @Test
    public void testCrearEstudianteExitoso() {
        Mockito.when(repoMock.findAll()).thenReturn(List.of());
        Mockito.when(repoMock.save(Mockito.any(Student.class))).thenReturn(estudianteEjemplo);

        Student nuevo = new Student();
        nuevo.setNombre("Andy Sis");
        nuevo.setCorreo("andy.sis@example.com");
        nuevo.setNumero_telefono("1234567890");
        nuevo.setIdioma("español");

        Student guardado = studentService.crear(nuevo);

        assertNotNull(guardado);
        assertEquals("Andy Sis", guardado.getNombre());
        Mockito.verify(repoMock).save(Mockito.any(Student.class));
    }

    @Test
    public void testCrearEstudianteCorreoDuplicado() {
        Mockito.when(repoMock.findAll()).thenReturn(Arrays.asList(estudianteEjemplo));

        Student duplicado = new Student();
        duplicado.setNombre("Otro Nombre");
        duplicado.setCorreo("andy.sis@example.com");
        duplicado.setNumero_telefono("0987654321");
        duplicado.setIdioma("inglés");

        assertThrows(ResponseStatusException.class, () -> {
            studentService.crear(duplicado);
        });
    }

    @Test
    public void testEditarParcialEstudiante() {
        Mockito.when(repoMock.findById(1)).thenReturn(estudianteEjemplo);

        Map<String, String> cambios = new HashMap<>();
        cambios.put("idioma", "francés");

        Student actualizado = studentService.editarParcial(1, cambios);

        assertEquals("francés", actualizado.getIdioma());
    }

    @Test
    public void testEliminarEstudiante() {
        Mockito.when(repoMock.delete(1)).thenReturn(true);

        studentService.eliminar(1);

        Mockito.verify(repoMock).delete(1);
    }
}