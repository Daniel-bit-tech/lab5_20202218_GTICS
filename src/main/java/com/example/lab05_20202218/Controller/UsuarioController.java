package com.example.lab05_20202218.Controller;

import com.example.lab05_20202218.Repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/inicio")
    public String home(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "index";
    }
}
