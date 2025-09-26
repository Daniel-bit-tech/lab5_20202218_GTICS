package com.example.lab05_20202218.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
// DECLARAMOS LA ENTIDAD RANKING
public class Ranking {
    @Id
    private Integer usuarioId;
    private String nombre;
    private String apellido;
    private Long totalRegalos;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
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

    public Long getTotalRegalos() {
        return totalRegalos;
    }

    public void setTotalRegalos(Long totalRegalos) {
        this.totalRegalos = totalRegalos;
    }
}
