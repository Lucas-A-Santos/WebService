package com.example.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.data.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

}
