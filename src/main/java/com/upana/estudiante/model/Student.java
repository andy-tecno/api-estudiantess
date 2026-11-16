package com.upana.estudiante.model;

import jakarta.validation.constraints.*;

public class Student {
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "Máximo 255 caracteres")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{10}", message = "Debe tener exactamente 10 dígitos numéricos")
    private String numero_telefono;

    @NotBlank(message = "El idioma es obligatorio")
    @Pattern(regexp = "inglés|español|francés", message = "Solo se acepta: inglés, español o francés")
    private String idioma;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getNumero_telefono() { return numero_telefono; }
    public void setNumero_telefono(String numero_telefono) { this.numero_telefono = numero_telefono; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }
}