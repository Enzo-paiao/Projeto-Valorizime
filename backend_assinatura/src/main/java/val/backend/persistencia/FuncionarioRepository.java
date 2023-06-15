package val.backend.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import val.backend.entidades.Funcionario;



public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

	List<Funcionario> findByNome(String nome);
	
	List<Funcionario> findByCpf(String cpf);

	Funcionario findById(long id);

}
