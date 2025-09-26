package com.example.lab05_20202218.Entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.processing.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;


    private String apellido;


    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Por favor ingresa un correo válido")
    private String correo;
    @Min(value = 18, message = "La edad mínima es 18 años")
    private Integer edad;
    @Size(max = 10,min = 1,message = "El nombre puede tener solo de 1 a 10 caracteres")
    private String descripcion;
    private String contrasena;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
