package backend.valorizeme.rest;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import backend.valorizeme.entidades.Usuarios;
import backend.valorizeme.negocio.UsuarioService;




@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "usuarios")
public class UsuariosController {
   
    private final UsuarioService usuarioService;

    public UsuariosController(UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }

    @GetMapping
    public List<Usuarios> getUsuario(){
        return usuarioService.getUsuarios();
    }
    
    @GetMapping(value="{id}")
    public Usuarios getUsuariosId(@PathVariable String id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return usuarioService.getUsuariosByCodigo(id);
        }
        throw new Exception("Usuario com codigo "+id+" nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Usuarios createUsuarios(@RequestBody @NotNull Usuarios usuario) throws Exception {
         return usuarioService.saveUsuarios(usuario);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Usuarios updateUsuarios(@PathVariable String id, 
    		@RequestBody @NotNull Usuarios usuarios) throws Exception {
    	if (!id.equals(usuarios.getCodigo())) {
    		throw new Exception("Codigo "+id+" nao está correto");
    	}
    	if (!usuarioService.isUsuariosExists(usuarios)) {
    		throw new Exception("Usuarios com codigo "+id+" não existe");
    	}
        return usuarioService.saveUsuarios(usuarios);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updateUsuarios(@PathVariable String id) throws Exception {
    	if (!usuarioService.isUsuariosExists(id)) {
    		throw new Exception("Usuarios com codigo "+id+" não existe");
    	} 
    	usuarioService.deleteUsuarios(id);
        return true;
    }
    
}