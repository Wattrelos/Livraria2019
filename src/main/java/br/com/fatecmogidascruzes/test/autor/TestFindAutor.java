/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecmogidascruzes.test.autor;

import br.com.fatecmogidascruzes.dao.AutorDAO;
import br.com.fatecmogidascruzes.domain.Autor;


/**
 *
 * @author Josias Wattrelos
 */
public class TestFindAutor {
    
    public static void main(String[] args){
        
        // Teste de busca por autor, um autores.
        AutorDAO autorfacade = new AutorDAO();
        Autor autor = new Autor(18,"");        
        Autor autor2 = autorfacade.findById(autor.getId());
        if(autor2 == null)
            System.out.println("\033[46mAutor não encontrado"); 
        else
            System.out.println("\033[46m" + autor2.toString()); 
        
       
    }
    
}
