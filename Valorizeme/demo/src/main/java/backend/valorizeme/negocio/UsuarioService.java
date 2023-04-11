package backend.valorizeme.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import backend.valorizeme.entidades.Usuarios;
import backend.valorizeme.persistencia.UsuariosRepository;


@Service
public class UsuarioService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final UsuariosRepository usuariosRepo;

    public UsuarioService(UsuariosRepository UsuariosRepository){
        this.usuariosRepo=UsuariosRepository;
    }

    public List<Usuarios> getUsuarios(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Usuarios> lista = this.usuariosRepo.findAll();
        if (lista == null) {
        	return new ArrayList<Usuarios>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public Usuarios getUsuariosByCodigo(String codigo){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Usuarios com o codigo {}",codigo);
        }
        Optional<Usuarios> retorno = this.usuariosRepo.findById(codigo);
        if(!retorno.isPresent()){
            throw new RuntimeException("Usuario com o codigo "+codigo+" nao encontrado");
        }
        return retorno.get();
    }

    public Usuarios getUsuariosByNome(String nome){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Usuarios com o nome {}",nome);
        }
        List<Usuarios> lista = this.usuariosRepo.findByNome(nome);
        if(lista == null || lista.isEmpty()){
            throw new RuntimeException("Usuarios com o nome "+nome+" nao encontrado");
        }
        return lista.get(0);
    }

    public Usuarios saveUsuarios(Usuarios usuarios){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Usuario com os detalhes {}",usuarios.toString());
        }
        return this.usuariosRepo.save(usuarios);
    }

    public void deleteUsuarios(String codigo){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Usuario com id {}",codigo);
        }
        this.usuariosRepo.deleteById(codigo);
    }

    public boolean isUsuariosExists(@NotNull Usuarios usuarios){
    	Optional<Usuarios> retorno = this.usuariosRepo.findById(usuarios.getCodigo());
        return retorno.isPresent() ? true:  false;
    }

    public boolean isUsuariosExists(String codigo){
    	Optional<Usuarios> retorno = this.usuariosRepo.findById(codigo);
        return retorno.isPresent() ? true:  false;
    }
}
