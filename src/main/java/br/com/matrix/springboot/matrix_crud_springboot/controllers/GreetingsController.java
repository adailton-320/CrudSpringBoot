package br.com.matrix.springboot.matrix_crud_springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.matrix.springboot.matrix_crud_springboot.model.Usuario;
import br.com.matrix.springboot.matrix_crud_springboot.repository.UsuarioRepositoryInt;

/**
 *
 * A sample greetings controller to return greeting text
 */

@RestController
public class GreetingsController {
	
	private static final int ResponseEntity = 0;
	private static final int List = 0;
	
	@Autowired
	public UsuarioRepositoryInt usuarioRepositoryInt;

	@RequestMapping(value = "/index")
	@ResponseBody
	public ModelAndView paginaModel() {
		ModelAndView mv= new ModelAndView();
		mv.setViewName("/index.html");
		
		return mv;
	}
	
	@PostMapping(value = "salvarUser")
	@ResponseBody
	public ResponseEntity<Usuario> salvarUser(@RequestBody Usuario usuario){
		
		Usuario user= usuarioRepositoryInt.save(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
		
	}
    
    @GetMapping(value = "listarTodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listarTodos(){
    	 
    	List<Usuario> usuarios= usuarioRepositoryInt.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK );
    	
    	
    }
    
    @DeleteMapping(value = "deletarUser")
    @ResponseBody
    public ResponseEntity<String> deletarUser(@RequestParam Long idUser){
    	
    	usuarioRepositoryInt.deleteById(idUser);
    	
    	return new ResponseEntity<String>("Deletado com Sucesso",HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarUserId")
    @ResponseBody
    public ResponseEntity<Usuario> buscarUserId(@RequestParam(value = "idUser")Long idUser){
    	
    	Usuario usuario= usuarioRepositoryInt.findById(idUser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
    
    @PutMapping(value = "atualizarUser")
    @ResponseBody
    public ResponseEntity<?> atualizarUser(@RequestBody Usuario usuario){
    	
    	if(usuario.getId()== null) {
    		return new ResponseEntity<String>("Identificação não informada", HttpStatus.OK);
    		
    	}
    	
    	Usuario user= usuarioRepositoryInt.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name")String name){
    	
    	List<Usuario> usuarios= usuarioRepositoryInt.buscarUserNome(name.trim().toUpperCase());
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    	
    }
   
    
}
