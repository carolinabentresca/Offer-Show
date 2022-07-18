package com.example.controller;

import com.example.dto.UsuarioDto;
import com.example.service.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroControl {

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @ModelAttribute("usuario")
    public UsuarioDto registro() {
        return new UsuarioDto();
    }

    @GetMapping
    public String registroForm() {
        return "registro";
    }

    @PostMapping
    public String registroSave(@ModelAttribute("usuario") UsuarioDto usuario) {
        usuarioDetailsService.save(usuario);
        return "redirect:/registro?exito";
    }
}
