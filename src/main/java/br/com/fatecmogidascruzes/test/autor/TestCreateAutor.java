
package br.com.fatecmogidascruzes.test.autor;

import br.com.fatecmogidascruzes.dao.AutorDAO;
import br.com.fatecmogidascruzes.domain.Autor;



public class TestCreateAutor {
    
    public static void main(String[] args){
    
        Autor autor01 = new Autor("Simone Diniz Junqueira Barbosa");
        AutorDAO autorfacade = new AutorDAO();     
        autorfacade.create(autor01);
    }
    
}
