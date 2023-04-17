package backend.assinatura.persistencia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import backend.assinatura.entidades.Assinatura;



public interface AssinaturaRepository extends CrudRepository<Assinatura, Long> {

	List<Assinatura> findByNome(String nome);
	
	List<Assinatura> findByStatus(String status);

	Assinatura findById(long id);

	List<Assinatura> findByEmail(String email);

}
