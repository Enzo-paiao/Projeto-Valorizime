package backend.assinatura.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import backend.assinatura.entidades.Assinatura;
import backend.assinatura.persistencia.AssinaturaRepository;

@Service
public class AssinaturaService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    private final AssinaturaRepository assinaturaRepo;

    public AssinaturaService(AssinaturaRepository assinaturaRepository){
        this.assinaturaRepo=assinaturaRepository;
    }
    
    public List<Assinatura> getAssinatura(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Assinatura> lista = this.assinaturaRepo.findAll();
        if (lista == null) {
        	return new ArrayList<Assinatura>();
        }
        return IteratorUtils.toList(lista.iterator());
    }    

    public Assinatura getAssinaturaById(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Assinatura com o codigo {}",id);
        }
        Optional<Assinatura> retorno = this.assinaturaRepo.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("Assinatura com o id "+id+" nao encontrada");
        }
        return retorno.get();
    }
    
    public List<Assinatura> getAssinaturaByStatus(String status){
    	if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Assinatura> lista = this.assinaturaRepo.findByStatus(status);
        if (lista == null) {
        	return new ArrayList<Assinatura>();
        }
        return IteratorUtils.toList(lista.iterator());
    }
    
    public List<Assinatura> getAssinaturaByNome(String nome){
    	if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Assinatura> lista = this.assinaturaRepo.findByNome(nome);
        if (lista == null) {
        	return new ArrayList<Assinatura>();
        }
        return IteratorUtils.toList(lista.iterator());
    }
    
    public Assinatura saveAssinatura(Assinatura assinatura){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Assinatura com os detalhes {}",assinatura.toString());
        }
        return this.assinaturaRepo.save(assinatura);
    }
    
    public void deleteAssinatura(Long id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Assinatura com id {}",id);
        }
        this.assinaturaRepo.deleteById(id);
    }

    public boolean isAssinaturaExists(Assinatura assinatura){
    	Optional<Assinatura> retorno = this.assinaturaRepo.findById(assinatura.getId());
        return retorno.isPresent() ? true:  false;
    }

}