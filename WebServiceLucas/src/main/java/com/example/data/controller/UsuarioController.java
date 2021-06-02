package com.example.data.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.data.model.Usuario;
import com.example.data.repository.UsuarioRepository;
	
@RestController
@RequestMapping("")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(path="/data")
	public String date(HttpServletRequest request)
	{
		String tz= request.getParameter("tz");
		Calendar GMTZone = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		long gmtTime = GMTZone.getTime().getTime();
		long TZAlteredTime = gmtTime + TimeZone.getTimeZone(tz).getRawOffset();
		Calendar TMZone = Calendar.getInstance(TimeZone.getTimeZone(tz));
		TMZone.setTimeInMillis(TZAlteredTime+10800000);
		Date alteredDate =TMZone.getTime();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		
		return dateFormat.format(alteredDate);
		
	}
	@GetMapping("/usuario/consulta")	
	public List<Usuario> consulta() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping(path="/usuario/inclusao")
	@ResponseStatus
	public Usuario adicionar(HttpServletRequest request)
	{
		Long id = Long.parseLong(request.getParameter("id"));
		String nome= request.getParameter("nome");
		String nascimento= request.getParameter("nascimento");
		double salario = Double.parseDouble(request.getParameter("salario"));
		Usuario usuario= new Usuario();
		usuario.setId(id);
		usuario.setNome(nome);
		usuario.setNascimento(nascimento);
		usuario.setSalario(salario);
		return usuarioRepository.save(usuario);
	}
}
