package com.senai.saep.controller;

import com.senai.saep.dto.CategoriaDto;
import com.senai.saep.dto.MaterialDto;
import com.senai.saep.service.CategoriaService;
import com.senai.saep.service.MaterialService;
import com.senai.saep.sessao.SessaoDto;
import com.senai.saep.sessao.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MaterialController {
    private final MaterialService materialService;
    private final CategoriaService categoriaService;

    public MaterialController(MaterialService materialService, CategoriaService categoriaService) {
        this.materialService = materialService;
        this.categoriaService = categoriaService;
    }

    @PostMapping("/material")
    private String cadastrarMaterial(@Valid @ModelAttribute("material") MaterialDto dto, BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes, Authentication authentication, Model model) {

        model.addAttribute("usuarioLogin", authentication.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listar());
            return "materialcadastrar";
        }

        try {
            materialService.cadastrar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("categorias", categoriaService.listar());

            return "materialcadastrar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Material cadastrado com sucesso ");

        return "redirect:/materiaislista";
    }

    @PostMapping("/materialatualizar")
    private String atualizarMaterial(@Valid @ModelAttribute("material") MaterialDto dto, BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes, Authentication authentication, Model model) {

        model.addAttribute("usuarioLogin", authentication.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listar());
            return "materialatualizar";
        }

        try {
            materialService.atualizar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("categorias", categoriaService.listar());

            return "materialatualizar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Material atualizado com sucesso ");

        return "redirect:/materiaislista";
    }

    @DeleteMapping("/materialexcluir/{id}")
    public ResponseEntity<String> removerMaterial(@PathVariable Long id) {
        try {
            materialService.excluir(id);

            return ResponseEntity.ok("Material excluído com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/categoria")
    private String cadastrarCategoria(@Valid @ModelAttribute("categoria") CategoriaDto dto, BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes, Authentication authentication, Model model) {

        model.addAttribute("usuarioLogin", authentication.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listar());
            return "categoriacadastrar";
        }

        try {
            categoriaService.cadastrar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("categorias", categoriaService.listar());

            return "categoriacadastrar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Categoria cadastrada com sucesso ");

        return "redirect:/categorialista";
    }

    @PostMapping("/categoriaatualizar")
    private String atualizarCategoria(@Valid @ModelAttribute("categoria") CategoriaDto dto, BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes, Authentication authentication, Model model) {

        model.addAttribute("usuarioLogin", authentication.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listar());
            return "categoriaatualizar";
        }

        try {
            categoriaService.atualizar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("categorias", categoriaService.listar());

            return "categoriaatualizar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Categoria atualizada com sucesso ");

        return "redirect:/categorialista";
    }

    @DeleteMapping("/categoriaexcluir/{id}")
    public ResponseEntity<String> removerCategoria(@PathVariable Long id) {
        try {
            categoriaService.excluir(id);
            return ResponseEntity.ok("Categoria excluída com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
