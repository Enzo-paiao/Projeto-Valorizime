package backend.valorizeme.persistencia;

import java.util.List;


import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import backend.valorizeme.entidades.Usuarios;


@EnableScan()
public interface UsuariosRepository extends CrudRepository<Usuarios, String> {
	
	List<Usuarios> findByNome(String nome);
	
}
