package val.backend.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import val.backend.entidades.Funcionario;
import val.backend.persistencia.FuncionarioRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioTests {

    private static Logger LOGGER = LoggerFactory.getLogger(FuncionarioTests.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    
	@Autowired
	private FuncionarioRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");
		Funcionario c1 = new Funcionario(null,"Joao Antunes","00000000000","joao@gmail.com","31988887777",df.parse("01/01/2000"),"joao","1234");
		repository.save(c1);

		Funcionario c2 = new Funcionario(null,"Maria Silva","00000000001","maria@gmail.com","31988887778",df.parse("02/02/1995"),"maria","1234");
		repository.save(c2);

		Funcionario c3 = new Funcionario(null,"Tiago Santos","00000000002","tiago@gmail.com","31988887779",df.parse("03/03/1980"),"tiago","1234");
		repository.save(c3);
		
		LOGGER.info("Pesquisado todos");
		Iterable<Funcionario> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Funcionario cliente : lista) {
			LOGGER.info(cliente.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Funcionario> result = repository.findByNome("Tiago Santos");
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getEmail(), "tiago@gmail.com");
		LOGGER.info("Encontrado: {}", result.get(0));
	}
	
	
	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		List<Funcionario> result = repository.findByCpf("00000000000");
		for (Funcionario cliente : result) {
			LOGGER.info("Excluindo Cliente id = "+cliente.getId());
			repository.delete(cliente);
		}
		result = repository.findByCpf("00000000001");
		for (Funcionario cliente : result) {
			LOGGER.info("Excluindo Cliente id = "+cliente.getId());
			repository.delete(cliente);
		}
		result = repository.findByCpf("00000000002");
		for (Funcionario cliente : result) {
			LOGGER.info("Excluindo Cliente id = "+cliente.getId());
			repository.delete(cliente);
		}
		result = repository.findByNome("Tiago Santos");
		assertEquals(result.size(), 0);
		LOGGER.info("Exclus�o feita com sucesso");
	}
	
	
}
