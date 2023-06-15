package val.backend.rest;

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

import val.backend.entidades.Funcionario;
import val.backend.negocio.FuncionarioService;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "funcionario")
public class FuncionarioController {
   
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService){
        this.funcionarioService=funcionarioService;
    }

    @GetMapping(value = "")
    public List<Funcionario> getFuncionarios(){
        return funcionarioService.getFuncionarios();
    }
    
    @GetMapping(value="{id}")
    public Funcionario getFuncionarioById(@PathVariable Long id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return funcionarioService.getFuncionarioById(id);
        }
        throw new Exception("Funcionario com codigo "+id+" nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Funcionario createFuncionario(@RequestBody @NotNull Funcionario funcionario) throws Exception {
         return funcionarioService.saveFuncionario(funcionario);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Funcionario updateFuncionario(@PathVariable String id, 
    		@RequestBody @NotNull Funcionario funcionario) throws Exception {
         return funcionarioService.saveFuncionario(funcionario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updateFuncionario(@PathVariable Long id) throws Exception {
        funcionarioService.deleteFuncionario(id);
         return true;
    }
    
}