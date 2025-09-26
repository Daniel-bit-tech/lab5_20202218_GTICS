package com.example.lab05_20202218.Entity;

// profe era import jakarta, no hibernate, porque con jakarta no me sale problemas con el regex :((
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mensajes")
@Getter
@Setter


// se crea la entidad tipica con sus respectibos parametros y validaciones
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "remitente_id")
    private Usuario remitente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Usuario destinatario;

    @NotBlank(message = "Debe seleccionar un regalo")
    @Column(name = "regalo_tipo")
    private String regaloTipo;

    @Column(name = "regalo_color")
    private String regaloColor;


    // como sabemos la primera es para validar que no sea nulo, el segundo que sea menor de 20 letras, el ulitmo, es una sentencia personalizada para que no acepete las palabras odio y feo
    @NotBlank(message = "Es obligotrios")
    @Size(min = 20, message = "Al menos 20 caractered")
    @Pattern(regexp = "^(?!.*(odio|feo)).*$", message = "No enviar palabras ofensivas")
    private String contenido;
}

