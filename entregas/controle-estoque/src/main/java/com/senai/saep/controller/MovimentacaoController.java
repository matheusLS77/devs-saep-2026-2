package com.senai.saep.controller;

import com.senai.saep.dto.MovimentacaoDto;
import com.senai.saep.dto.UsuarioDto;
import com.senai.saep.enums.TipoMovimentacao;
import com.senai.saep.service.MaterialService;
import com.senai.saep.service.MovimentacaoService;
import com.senai.saep.service.UsuarioService;
import com.senai.saep.sessao.SessaoDto;
import com.senai.saep.sessao.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MovimentacaoController {
    private final MovimentacaoService movimentacaoService;
    private final MaterialService materialService;
    private final UsuarioService usuarioService;

    public MovimentacaoController(MovimentacaoService movimentacaoService, MaterialService materialService, UsuarioService usuarioService) {
        this.movimentacaoService = movimentacaoService;
        this.materialService = materialService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/movimentacao")
    public String cadastrarMovimentacao(@Valid @ModelAttribute("movimentacao") MovimentacaoDto movimentacaoDto, BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes, Authentication authentication, Model model) {

        UsuarioDto usuario = usuarioService.buscarPorLogin(authentication.getName());

        movimentacaoDto.setIdUsuario(usuario.getId());

        model.addAttribute("usuarioLogin", usuario.getLogin());

        if (bindingResult.hasErrors()) {
            model.addAttribute("movimentacoes", movimentacaoService.listar());
            model.addAttribute("tipos", TipoMovimentacao.values());
            model.addAttribute("materiais", materialService.listar());
            return "movimentacaocadastrar";
        }

        try {
            movimentacaoService.cadastrar(movimentacaoDto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("movimentacoes", movimentacaoService.listar());
            model.addAttribute("materiais", materialService.listar());
            model.addAttribute("tipos", TipoMovimentacao.values());

            return "movimentacaocadastrar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Movimentação realizada com sucesso ");

        return "redirect:/movimentacaolista";
    }

}
