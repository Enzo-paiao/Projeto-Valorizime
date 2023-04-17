package backend.assinatura.rest;

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

import backend.assinatura.entidades.Assinatura;
import backend.assinatura.negocio.AssinaturaService;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "assinatura")
public class AssinaturaRest {
   
    private final AssinaturaService assinaturaService;

    public AssinaturaRest(AssinaturaService assinaturaService){
        this.assinaturaService=assinaturaService;
    }

    @GetMapping(value = "")
    public List<Assinatura> getAssinatura(){
        return assinaturaService.getAssinatura();
    }
    
    @GetMapping(value="{id}")
    public Assinatura getAssinaturaById(@PathVariable Long id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return assinaturaService.getAssinaturaById(id);
        }
        throw new Exception("Usuario que possui assinatura com codigo "+id+" nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Assinatura createAssinatura(@RequestBody @NotNull Assinatura assinatura) throws Exception {
         return assinaturaService.saveAssinatura(assinatura);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Assinatura updateAssinatura(@PathVariable String id, 
    		@RequestBody @NotNull Assinatura assinatura) throws Exception {
         return assinaturaService.saveAssinatura(assinatura);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updateAssinatura(@PathVariable Long id) throws Exception {
        assinaturaService.deleteAssinatura(id);
         return true;
    }
    
}