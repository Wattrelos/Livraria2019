/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecmogidascruzes.test.autor;

import br.com.fatecmogidascruzes.dao.AutorDAO;
import br.com.fatecmogidascruzes.domain.Autor;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Josias Wattrelos
 */
public class TestFindListAutorByName {
    
    public static void main(String[] args){
        
        // Teste de busca por autor, um autores.
        AutorDAO autorfacade = new AutorDAO();              
        List<Autor> autores = new ArrayList<>();                
        autores = autorfacade.findByName("B%");
        if(autores == null){
            System.out.println("Nenhum autor encontrado"); 
        }else{
            for(Autor autor : autores){
                System.out.println(autor.toString());
            } 
        }
    }
    
}
