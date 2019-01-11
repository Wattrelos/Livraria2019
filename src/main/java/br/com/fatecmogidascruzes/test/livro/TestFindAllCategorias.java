/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecmogidascruzes.test.livro;

import java.util.ArrayList;
import java.util.List;

import br.com.fatecmogidascruzes.dao.CategoriaDAO;
import br.com.fatecmogidascruzes.domain.Categoria;

/**
 *
 * @author Josias Wattrelos
 */
public class TestFindAllCategorias {
   
    public static void main(String[] args){
            
    	CategoriaDAO categoriafacade = new CategoriaDAO();
        List<Categoria> categorias = new ArrayList<>();
        categorias = categoriafacade.findAll();  
        for(Categoria categoria : categorias)
            System.out.println(categoria.toString());
    }
    
}
