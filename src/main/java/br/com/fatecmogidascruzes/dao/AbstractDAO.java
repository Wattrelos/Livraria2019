
package br.com.fatecmogidascruzes.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDAO<T> {

    private Class<T> entityClass;
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("livraria2019");
    protected EntityManager em = emf.createEntityManager();   

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected EntityManager getEntityManager(){
        return em;
    }
    
    protected String getColumnName(){
    	return "nome"; // Por padrão, retorna o nome da coluna para busca por nome.        
}   
    
    protected String getQueryFindByName(){
        return "findByName"; //  Por padrão, busca pelo nome.
    }

    public void create(T entity) {
        try{
        	System.out.println("\033[46m" + entityClass.getSimpleName() + " >>>> em.getTransaction().begin()\n");
            em.getTransaction().begin();
            System.out.println("\033[46m" + entityClass.getSimpleName() + " >>>> getEntityManager().persist(entity)\n");
            getEntityManager().persist(entity);
            System.out.println("\033[46m" + entityClass.getSimpleName() + " >>>> em.getTransaction().commit()\n");
            em.getTransaction().commit();
            System.out.println("\033[46m" + entityClass.getSimpleName() + " gravado com sucesso!\n");                
        }catch(Exception ex){
            System.out.println("\033[46mErro ao tentar gravar: " + entityClass.getSimpleName() + "\n"+ ex);                
        }finally{
            em.close();
        }
    }
    
    public void create(List<T> entity) {
        try{
            em.getTransaction().begin();
            for(T t: entity){
                getEntityManager().persist(t);            
            }
            em.getTransaction().commit();
            System.out.println("\033[46m" + entityClass.getSimpleName() + " gravados com sucesso!\n"); 
       
        }catch(Exception ex){
            System.out.println("\033[46mErro ao tentar gravar lista de " + entityClass.getSimpleName() + "\n"+ ex);                
        }finally{
            em.close();
        }
    }

    public void edit(T entity) {
        try{
            em.getTransaction().begin();
            getEntityManager().merge(entity);
            em.getTransaction().commit();
            System.out.println("\033[46m" + entityClass.getSimpleName() + " atualizado com sucesso!\n"); 
        }catch(Exception ex){
            System.out.println("\033[46mErro ao tentar editar " + entityClass.getSimpleName()+ "\n"+ ex);                
        }finally{
            em.close();
        }
    }

    public void remove(T entity) {
        try{
            em.getTransaction().begin();
            getEntityManager().remove(getEntityManager().merge(entity));        
            em.getTransaction().commit();
            System.out.println("\033[46m" + entityClass.getSimpleName() + " excluído com sucesso!\n"); 
        }catch(Exception ex){
            System.out.println("\033[46mErro ao tentar apagar " + entityClass.getSimpleName() + "\n"+ ex);                
        }finally{
            em.close();
        }
    }

    public T findById(Integer id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public List<T> findByName(String name) {
        try{
            return getEntityManager().createNamedQuery(getQueryFindByName(),entityClass).setParameter(getColumnName(), name).getResultList();
        }catch(Exception ex){
            System.out.println(ex);            
            return null;
        }            
    }
    
    public T findOneByName(String name) {
        try{
            return getEntityManager().createNamedQuery(getQueryFindByName(),entityClass).setParameter(getColumnName(), name).getSingleResult();
        }catch(Exception ex){
            System.out.println(ex);            
            return null;
        }
            
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
