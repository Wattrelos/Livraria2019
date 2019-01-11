package br.com.fatecmogidascruzes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fatecmogidascruzes.dao.AutorDAO;
import br.com.fatecmogidascruzes.domain.Autor;

@SpringBootApplication
public class Livraria2019v006Application {

	public static void main(String[] args) {
		SpringApplication.run(Livraria2019v006Application.class, args);
		
        AutorDAO autorfacade = new AutorDAO();
        List<Autor> autores = new ArrayList<>();
        autores = autorfacade.findAll();  
        for(Autor autor : autores)
            System.out.println(autor.toString());
	}

}

