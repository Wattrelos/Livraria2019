
package br.com.fatecmogidascruzes.test.autor;

import br.com.fatecmogidascruzes.dao.AutorDAO;
import br.com.fatecmogidascruzes.domain.Autor;



public class TestDeleteAutor {
    
    public static void main(String[] args){
    
        Autor autor = new Autor("aaaaaaccccc");
        AutorDAO autordao = new AutorDAO();
        autordao.remove(autor);
    }
    
}
