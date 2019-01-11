/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecmogidascruzes.test.livro;

import br.com.fatecmogidascruzes.dao.LivroDAO;
import br.com.fatecmogidascruzes.domain.Autor;
import br.com.fatecmogidascruzes.domain.Categoria;
import br.com.fatecmogidascruzes.domain.Livro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josias Wattrelos
 */
public class TestFindAllLivro {
        public static void main(String[] args){
            
        LivroDAO livrofacade = new LivroDAO();
        List<Livro> livros = new ArrayList<>();
        livros = livrofacade.findAll();  
        for(Livro livro : livros){
            System.out.println("\033[46m" + livro.toString());
            for(Autor autor : livro.getAutor()){
                System.out.println("  Autores: " + autor.toString());
            }
            for(Categoria categoria : livro.getCategorias()){
                System.out.println("  Categorias: " + categoria.toString());
            }
        }
    }
}
