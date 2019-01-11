
package br.com.fatecmogidascruzes.test.autor;

import br.com.fatecmogidascruzes.dao.AutorDAO;
import br.com.fatecmogidascruzes.domain.Autor;



public class TestDeleteAutor {
    
    public static void main(String[] args){
    
        Autor autor = new Autor(8,"Simone Diniz Junqueira Barbosa");
        AutorDAO autorfacade = new AutorDAO();     
        autorfacade.remove(autor);
    }
    
}
