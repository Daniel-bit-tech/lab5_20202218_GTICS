package com.example.lab05_20202218.Repository;


import com.example.lab05_20202218.Entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Integer> {



    // tenemos qun query que combina tablas de mensajes y usuarios, y tambien agrupamos destinatario y lo contamos con count, y lo ordenamos descendente
    @Query(value = "SELECT " +
            "m.destinatario_id as usuarioId, " +
            "u.nombre as nombre, " +
            "u.apellido as apellido, " +
            "COUNT(m.regalo_tipo) as totalRegalos " +
            "FROM mensajes m " +
            "INNER JOIN usuarios u ON m.destinatario_id = u.id " +
            "GROUP BY m.destinatario_id " +
            "ORDER BY totalRegalos DESC", nativeQuery = true)


    List<Ranking> findRankingOfUsers();
}
