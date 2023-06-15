package val.backend.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import val.backend.entidades.Funcionario;
import val.backend.persistencia.FuncionarioRepository;


@Service
public class FuncionarioService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    private final FuncionarioRepository funcionarioRepo;

    public FuncionarioService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepo=funcionarioRepository;
    }
    
    public List<Funcionario> getFuncionarios(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Funcionario> lista = this.funcionarioRepo.findAll();
        if (lista == null) {
        	return new ArrayList<Funcionario>();
        }
        return IteratorUtils.toList(lista.iterator());
    }    

    public Funcionario getFuncionarioById(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Funcionario com o codigo {}",id);
        }
        Optional<Funcionario> retorno = this.funcionarioRepo.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("Funcionario com o id "+id+" nao encontrada");
        }
        return retorno.get();
    }
    
    public List<Funcionario> getFuncionarioByCpf(String cpf){
    	if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Funcionario> lista = this.funcionarioRepo.findByCpf(cpf);
        if (lista == null) {
        	return new ArrayList<Funcionario>();
        }
        return IteratorUtils.toList(lista.iterator());
    }
    
    public List<Funcionario> getFuncionarioByNome(String nome){
    	if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Funcionario> lista = this.funcionarioRepo.findByCpf(nome);
        if (lista == null) {
        	return new ArrayList<Funcionario>();
        }
        return IteratorUtils.toList(lista.iterator());
    }
    
    public Funcionario saveFuncionario(Funcionario funcionario){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Funcionario com os detalhes {}",funcionario.toString());
        }
        return this.funcionarioRepo.save(funcionario);
    }
    
    public void deleteFuncionario(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Funcionario com id {}",id);
        }
        this.funcionarioRepo.deleteById(id);
    }

    public boolean isFuncionarioExists(Funcionario funcionario){
    	Optional<Funcionario> retorno = this.funcionarioRepo.findById(funcionario.getId());
        return retorno.isPresent() ? true:  false;
    }

}