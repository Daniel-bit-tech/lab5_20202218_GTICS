package com.example.lab05_20202218.Controller;

import com.example.lab05_20202218.Entity.Usuario;
import com.example.lab05_20202218.Repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String procesarFormulario(
            @Valid @ModelAttribute("usuario") Usuario usuario,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "registro";
        }
        usuarioRepository.save(usuario);
        return "redirect:/inicio";

    }

}
