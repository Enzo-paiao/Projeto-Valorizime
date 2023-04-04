package backend.valorizeme;

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
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import backend.valorizeme.entidades.Usuarios;
import backend.valorizeme.persistencia.UsuariosRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, UsuarioTest.DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioTest {

    private static Logger LOGGER = LoggerFactory.getLogger(UsuarioTest.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	    
    @Configuration
	@EnableDynamoDBRepositories(basePackageClasses = UsuariosRepository.class)
	public static class DynamoDBConfig {

		@Value("")
		private String amazonAWSAccessKey;

		@Value("")
		private String amazonAWSSecretKey;

		public AWSCredentialsProvider amazonAWSCredentialsProvider() {
			return new AWSStaticCredentialsProvider(amazonAWSCredentials());
		}

		@Bean
		public AWSCredentials amazonAWSCredentials() {
			return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
		}

		@Bean
		public AmazonDynamoDB amazonDynamoDB() {
			return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
					.withRegion(Regions.US_EAST_1).build();
		}
	}
    
	@Autowired
	private UsuariosRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");
		Usuarios c1 = new Usuarios("1", "Enzo", "Backend", 
				df.parse("30/06/2015"));
		repository.save(c1);

		Usuarios c2 = new Usuarios("2", "Marcelo", "Cloud", 
				df.parse("02/10/2017"));
		repository.save(c2);

		Usuarios c3 = new Usuarios("3", "Thigas", "Suporte", 
				df.parse("21/09/2017"));
		repository.save(c3);
		LOGGER.info("Pesquisado todos");
		Iterable<Usuarios> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Usuarios usuarios : lista) {
			LOGGER.info(usuarios.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Usuarios> result = repository.findByNome("Enzo");
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getCodigo(), "1");
		LOGGER.info("Encontrado: {}", result.get(0));
	}
	
	
	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		Usuarios c1 = new Usuarios("1", "Enzo", "Backend", 
				df.parse("30/06/2015"));
		repository.delete(c1);

		Usuarios c2 = new Usuarios("2", "Marcelo", "Cloud", 
				df.parse("02/10/2017"));
		repository.delete(c2);

		Usuarios c3 = new Usuarios("3", "Thigas", "Suporte", 
				df.parse("21/09/2017"));
		repository.delete(c3);

		List<Usuarios> result = repository.findByNome("Thigas");
		assertEquals(result.size(), 0);
		LOGGER.info("Exclusão feita com sucesso");
	}
	
	
}