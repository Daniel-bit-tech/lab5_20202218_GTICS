package com.example.lab05_20202218.Repository;

import com.example.lab05_20202218.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
