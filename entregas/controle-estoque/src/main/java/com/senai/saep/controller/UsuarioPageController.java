package com.senai.saep.controller;

import com.senai.saep.dto.UsuarioDto;
import com.senai.saep.repository.CategoriaRepository;
import com.senai.saep.repository.MaterialRepository;
import com.senai.saep.service.MaterialService;
import com.senai.saep.service.UsuarioService;
import com.senai.saep.sessao.SessaoDto;
import com.senai.saep.sessao.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UsuarioPageController {
    private final UsuarioService service;
    private final MaterialRepository materialRepository;
    private final CategoriaRepository categoriaRepository;
    private final MaterialService materialService;

    public UsuarioPageController(UsuarioService service, MaterialRepository materialRepository, CategoriaRepository categoriaRepository, MaterialService materialService) {
        this.service = service;
        this.materialRepository = materialRepository;
        this.categoriaRepository = categoriaRepository;
        this.materialService = materialService;
    }

    @GetMapping("/login")
    public String getLogin(@ModelAttribute("usuario") UsuarioDto usuario) {
        return "login";
    }

    @GetMapping("/cadastro")
    public String getCadastro(@ModelAttribute("usuario") UsuarioDto usuario) {
        return "cadastro";
    }

    @GetMapping("/home")
    public String getSucesso(
            Authentication authentication,
            Model model) {

        model.addAttribute("usuarioLogin", authentication.getName());
        model.addAttribute("totalMateriais", materialRepository.count());
        model.addAttribute("totalCategorias", categoriaRepository.count());
        model.addAttribute("totalEstoqueBaixo", materialService.contarEstoqueBaixo());

        return "home";
    }

//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        SessaoUtil.encerrarSessao(session);
//        return "redirect:/login";
//    }
}
