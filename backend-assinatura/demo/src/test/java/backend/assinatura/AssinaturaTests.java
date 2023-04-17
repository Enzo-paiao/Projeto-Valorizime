package backend.assinatura;

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

import backend.assinatura.entidades.Assinatura;
import backend.assinatura.persistencia.AssinaturaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AssinaturaTests {

    private static Logger LOGGER = LoggerFactory.getLogger(AssinaturaTests.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    
	@Autowired
	private AssinaturaRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");
		Assinatura c1 = new Assinatura(null,"Joao Antunes","joao@gmail.com","31988887777",df.parse("01/01/2000"),"joao","1234", "Ativada");
		repository.save(c1);

		Assinatura c2 = new Assinatura(null,"Maria Silva","maria@gmail.com","31988887778",df.parse("02/02/1995"),"maria","1234", "Ativada");
		repository.save(c2);

		Assinatura c3 = new Assinatura(null,"Tiago Santos","tiago@gmail.com","31988887779",df.parse("03/03/1980"),"tiago","1234", "Desativada");
		repository.save(c3);
		
		LOGGER.info("Pesquisado todos");
		Iterable<Assinatura> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Assinatura assinatura : lista) {
			LOGGER.info(assinatura.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Assinatura> result = repository.findByNome("Tiago Santos");
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getEmail(), "tiago@gmail.com");
		LOGGER.info("Encontrado: {}", result.get(0));
	}
	
	
	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		List<Assinatura> result = repository.findByEmail("joao@gmail.com");
		for (Assinatura assinatura : result) {
			LOGGER.info("Excluindo Assinatura id = "+assinatura.getId());
			repository.delete(assinatura);
		}
		result = repository.findByEmail("maria@gmail.com");
		for (Assinatura assinatura : result) {
			LOGGER.info("Excluindo Assinatura id = "+assinatura.getId());
			repository.delete(assinatura);
		}
		result = repository.findByEmail("tiago@gmail.com");
		for (Assinatura assinatura : result) {
			LOGGER.info("Excluindo Assinatura id = "+assinatura.getId());
			repository.delete(assinatura);
		}
		result = repository.findByNome("Tiago Santos");
		assertEquals(result.size(), 0);
		LOGGER.info("Exclusão feita com sucesso");
	}
	
	
}
