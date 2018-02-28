package com.ufcg.si1;

import com.ufcg.si1.model.Produto;
import com.ufcg.si1.repositories.ProdutosRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages={"com.ufcg.si1"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class EstoqueFacilRestApi {

	public static void main(String[] args) {
		SpringApplication.run(EstoqueFacilRestApi.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			}
		};
	}

	@Bean
	CommandLineRunner init(ProdutosRepository produtosRepository) {
		return (evt) -> {
							List<Produto> produtos = new ArrayList<>();
							Produto produto1 = new Produto("produto1", "123", "fabricante1", "categoria1");
							produtos.add(produto1);
							Produto produto2 = new Produto("produto2", "234", "fabricante2", "categoria2");
							produtos.add(produto2);
							Produto produto3 = new Produto("produto3", "345", "fabricante3", "categoria3");
							produtos.add(produto3);

							for (Produto produto: produtos) {
								produtosRepository.save(produto);
						}
		};
	}

}
