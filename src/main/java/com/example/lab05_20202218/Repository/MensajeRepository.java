package com.example.lab05_20202218.Repository;

import com.example.lab05_20202218.Entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    // lista para encontrar el destinatarioid
    List<Mensaje> findByDestinatarioId(Integer destinatarioId);

}
