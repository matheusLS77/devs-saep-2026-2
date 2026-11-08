package com.senai.saep.controller;

import com.senai.saep.dto.UsuarioDto;
import com.senai.saep.service.UsuarioService;
import com.senai.saep.sessao.SessaoDto;
import com.senai.saep.sessao.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getIndex(){
        return "redirect:/login";
    }

//    @PostMapping("/login")
//    public String realizarLogin(
//            @Valid @ModelAttribute("usuario") UsuarioDto dto,
//            BindingResult bindingResult,
//            Model model,
//            RedirectAttributes redirectAttributes,
//            HttpSession session) {
//
//        if (bindingResult.hasErrors()) {
//            return "login";
//        }
//
//        UsuarioDto usuarioLogado = service.realizarLogin(dto);
//
//        if (usuarioLogado == null || usuarioLogado.getId() == null) {
//            model.addAttribute("erro", "Login ou senha inválidos.");
//            return "login";
//        }
//
//        SessaoDto sessaoDto = new SessaoDto();
//        sessaoDto.setUsuarioId(usuarioLogado.getId());
//        sessaoDto.setUsuarioLogin(usuarioLogado.getLogin());
//        SessaoUtil.registrarSessao(session, sessaoDto);
//
//        redirectAttributes.addFlashAttribute("mensagem", "Bem-vindo ao sistema");
//
//        return "redirect:/home";
//    }

    @PostMapping("/cadastro")
    public String cadastrar(
            @Valid @ModelAttribute("usuario") UsuarioDto dto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "cadastro";
        }

        service.cadastrar(dto);
        redirectAttributes.addFlashAttribute("mensagem", "Conta criada com sucesso!");

        return "redirect:/cadastro";
    }

}
