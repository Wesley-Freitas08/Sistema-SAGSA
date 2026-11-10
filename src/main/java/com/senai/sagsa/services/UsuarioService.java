package com.senai.sagsa.services;

import com.senai.sagsa.exceptions.ObjectNotFoundException;
import com.senai.sagsa.models.Perfil;
import com.senai.sagsa.models.Usuario;
import com.senai.sagsa.models.dtos.UsuarioCreateDTO;
import com.senai.sagsa.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    
    @Autowired 
    private UsuarioRepository usuarioRepository;
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado com o ID: " + id));
    }
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    @Transactional
    public Usuario create(Usuario usuario) {
        usuario.setId(null);
        return usuarioRepository.save(usuario);
    }

    public Usuario fromDTO(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha()); // Em produção, aplicar BCryptEncoder aqui
        
        Perfil perfil = new Perfil();
        perfil.setId(dto.getPerfilId());
        usuario.setPerfil(perfil);
        return usuario;
    }
}