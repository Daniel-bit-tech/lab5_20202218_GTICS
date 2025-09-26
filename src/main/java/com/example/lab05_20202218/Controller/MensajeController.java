

package com.example.lab05_20202218.Controller;
import com.example.lab05_20202218.Entity.Mensaje;
import com.example.lab05_20202218.Entity.Ranking;
import com.example.lab05_20202218.Entity.Usuario;
import com.example.lab05_20202218.Repository.MensajeRepository;
import com.example.lab05_20202218.Repository.RankingRepository;
import com.example.lab05_20202218.Repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class MensajeController {


    // Inyectamos los repositorys que usamos para los controladores
    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // metodo para mostrar formuarios
    @GetMapping("/mensajes/nuevo")
    public String mostrarFormulario(Model model) {
        // nnueva instancia del mensaje
        Mensaje mensaje = new Mensaje();
        // lista de todos los usuarios de la BD
        List<Usuario> usuarios = usuarioRepository.findAll();
        // Agrega el objeto mensaje al modelo mensaje
        model.addAttribute("mensaje", mensaje);
        // lo mismo que arriba
        model.addAttribute("usuarios", usuarios);
        // busca el html enviar en la carpeta templates
        return "enviar";
    }

    // Ahora un metoodo post , para guardar el regalo
    @PostMapping("/mensajes/enviar")
    public String procesarFormulario(
            // usamos el @valid par validacion
            @Valid @ModelAttribute("mensaje") Mensaje mensaje,
            BindingResult bindingResult,
            Model model,

            @RequestParam("remitenteId") Integer remitenteId,
            @RequestParam("destinatarioId") Integer destinatarioId
    ) {
        // un if para verificar si hay errores de validacion
        if (bindingResult.hasErrors()) {
            // si hay errores se muestra nuevamente la vista
            // lista de usaurios de nuevo para que los menus se muestren
            List<Usuario> usuarios = usuarioRepository.findAll();
            model.addAttribute("usuarios", usuarios);
            // retorna a la vista enviar.html
            return "enviar";
        }

        // bsuca el usaurio remitente en la BD usando el id
        Usuario remitente = usuarioRepository.findById(remitenteId).orElse(null);
        // si existe lo asigna al objeto mensaje
        if (remitente != null) {
            mensaje.setRemitente(remitente);
        }
        // busca al usaurio destinatario en lBD usando el id recibido
        Usuario destinatario = usuarioRepository.findById(destinatarioId).orElse(null);
        // si existe lo asigna al objeto mensaje
        if (destinatario != null) {
            mensaje.setDestinatario(destinatario);
        }
        // guarda
        mensajeRepository.save(mensaje);
        // se redirige a index.html con url /inicio
        return "redirect:/inicio";
    }



    // implemento el controlador el mensaje controller pq me parecio innecesario crear otro controlador a parte solo par aranking
    @Autowired
    // inyecto el repository que tiene el query
    private RankingRepository rankingRepository;

    // metodo get donde me encuentra lel ranking de users
    @GetMapping("/ranking")
    public String ranking(Model model) {
        // una lista que usa ranking repository que encunetra a los users para el ranking
        List<Ranking> ranking = rankingRepository.findRankingOfUsers();
        model.addAttribute("ranking", ranking);
        // te manda a ranking.html
        return "ranking";
    }

    // CONTROLADOR PARA LA VISTA DE MENSAJES RECIBIDOS POR USUARIO

    @GetMapping("/mensajes/recibidos")
    public String listarMensajesRecibidos(@RequestParam("id") Integer usuarioId, Model model) {
        // METODO QUE ME DA UNA LISTA CON LOS DESTINARIOID QUE ESTA EN EL MENSAJE REPOSITORY
        List<Mensaje> mensajes = mensajeRepository.findByDestinatarioId(usuarioId);

        model.addAttribute("mensajes", mensajes);
        model.addAttribute("cantidadTotal", mensajes.size());

        // OBTENEMOS EL USAURIO DESDE EL USAURIO REPOSITORY O EN CASO NO HAYA SEA NULL
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        model.addAttribute("usuario", usuario);
        // AGARRA mensajes_recibidos. html
        return "Mensajes_recibidos";
    }

}
