package com.senai.saep.controller;

import com.senai.saep.dto.CategoriaDto;
import com.senai.saep.dto.MaterialDto;
import com.senai.saep.repository.CategoriaRepository;
import com.senai.saep.repository.MaterialRepository;
import com.senai.saep.service.CategoriaService;
import com.senai.saep.service.MaterialService;
import com.senai.saep.sessao.SessaoDto;
import com.senai.saep.sessao.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MaterialPageController {
    private final MaterialService materialService;
    private final CategoriaService categoriaService;
    private final MaterialRepository materialRepository;
    private final CategoriaRepository categoriaRepository;

    public MaterialPageController(MaterialService materialService, CategoriaService categoriaService, MaterialRepository materialRepository, CategoriaRepository categoriaRepository) {
        this.materialService = materialService;
        this.categoriaService = categoriaService;
        this.materialRepository = materialRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/materialcadastrar")
    public String getCadastrar(Model model, Authentication authentication) {
        model.addAttribute("usuarioLogin", authentication.getName());

        model.addAttribute("material", new MaterialDto());
        model.addAttribute("categorias", categoriaService.listar());

        return "materialcadastrar";
    }

    @GetMapping("/materialatualizar/{id}")
    public String getAtualizar(@PathVariable Long id, Model model, Authentication authentication) {
        model.addAttribute("usuarioLogin", authentication.getName());

        MaterialDto material = materialService.obterMaterial(id);

        model.addAttribute("material", material);
        model.addAttribute("categorias", categoriaService.listar());

        return "materialatualizar";
    }

    @GetMapping("/materiaislista")
    public String getMateriais(@RequestParam(required = false) String material, @RequestParam(defaultValue = "0") int page, Model model, Authentication authentication) {
        model.addAttribute("usuarioLogin", authentication.getName());

        boolean admin = authentication
                .getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority
                                .getAuthority()
                                .equals("ROLE_ADMIN")
                );

        model.addAttribute("admin", admin);

        Page<MaterialDto> resultado = materialService.listarPaginado(page);

        model.addAttribute("materiais", resultado.getContent());
        model.addAttribute("paginaAtual", resultado.getNumber());
        model.addAttribute("totalPaginas", resultado.getTotalPages());

        if (material != null && !material.isBlank()) {
            model.addAttribute("materiais", materialService.materiaisBusca(material));
        } else {
            model.addAttribute("materiais", materialService.listarPaginado(page));
        }

        return "materiaislista";
    }

    @GetMapping("/categoriacadastrar")
    public String getCadastrarCategoria(Model model, Authentication authentication) {
        model.addAttribute("usuarioLogin", authentication.getName());

        model.addAttribute("categoria", new CategoriaDto());
        model.addAttribute("categorias", categoriaService.listar());

        return "categoriacadastrar";
    }

    @GetMapping("/categoriaatualizar/{id}")
    public String getAtualizarCategoria(@PathVariable Long id, Model model, Authentication authentication) {
        model.addAttribute("usuarioLogin", authentication.getName());


        CategoriaDto categoria = categoriaService.obterCategoria(id);

        model.addAttribute("categoria", categoria);
        model.addAttribute("categorias", categoriaService.listar());

        return "categoriaatualizar";
    }

    @GetMapping("/categorialista")
    public String getCategorias(@RequestParam(required = false) String categoria, Model model, Authentication authentication) {
        model.addAttribute("usuarioLogin", authentication.getName());

        boolean admin = authentication
                .getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority
                                .getAuthority()
                                .equals("ROLE_ADMIN")
                );

        model.addAttribute("admin", admin);

        if (categoria != null && !categoria.isBlank()) {
            model.addAttribute("categorias", categoriaService.categoriaBusca(categoria));
        } else {
            model.addAttribute("categorias", categoriaRepository.findAll());
        }

        return "categorialista";
    }


}
