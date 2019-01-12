package br.com.fatecmogidascruzes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fatecmogidascruzes.dao.LivroDAO;
import br.com.fatecmogidascruzes.domain.Autor;
import br.com.fatecmogidascruzes.domain.Categoria;
import br.com.fatecmogidascruzes.domain.Livro;
import br.com.fatecmogidascruzes.domain.Subcategoria;

@SpringBootApplication
public class Livraria2019v006SearchAll {

	public static void main(String[] args) {
		SpringApplication.run(Livraria2019v006SearchAll.class, args);
		
        LivroDAO livrodao = new LivroDAO();
        List<Livro> livros = new ArrayList<>();
        livros = livrodao.findAll();  
        for(Livro livro : livros) {
        	System.out.println("Livro: " + livro.toString());
        	System.out.println("Editora: " + livro.getEditora().toString());
        	for(Autor autor : livro.getAutor()) {
        		System.out.println("Autor: " + autor.toString());
        	}
        	for(Categoria categoria : livro.getCategorias()) {
        		System.out.println("Categoria: " + categoria.toString());
        	}
        	for(Subcategoria subcategoria : livro.getSubcategorias()) {
        		System.out.println("Subcategoria: " + subcategoria.toString());
        	}
        }        
	}

}

