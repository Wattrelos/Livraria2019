
package br.com.fatecmogidascruzes.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDAO<T> {

    private Class<T> entityClass;
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("livraria2019");
    protected EntityManager entityManager = emf.createEntityManager();

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;        
    }  
    
    protected String getColumnName(){
    	return "nome"; // Por padrão, retorna o nome da coluna para busca por nome.        
}   
    
    protected String getQueryFindByName(){
        return entityClass.getSimpleName() + ".findByName"; //  Por padrão, busca pelo nome. Se a nome da coluna for diferente de "nome" este método deve ser implementado.
    }

    public void create(T entity) {    	
        try{
        	entityManager.getTransaction().begin();            
        	entityManager.persist(entity);            
        	entityManager.getTransaction().commit();
            System.out.println(entityClass.getSimpleName() + " gravado com sucesso!\n");                
        }catch(Exception ex){
            System.out.println("Erro ao tentar gravar: " + entityClass.getSimpleName() + "\n"+ ex);                
        }finally{
        	entityManager.close();
        }
    }
    
    public void create(List<T> entity) {
        try{
        	this.entityManager.getTransaction().begin();
            for(T t: entity){
            	this.entityManager.persist(t);            
            }
            this.entityManager.getTransaction().commit();
            System.out.println(entityClass.getSimpleName() + " gravados com sucesso!\n"); 
       
        }catch(Exception ex){
            System.out.println(entityClass.getSimpleName() + "\n"+ ex);                
        }finally{
        	this.entityManager.close();
        }
    }

    public void edit(T entity) {
        try{
        	this.entityManager.getTransaction().begin();
        	this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();
            System.out.println("\033[46m" + entityClass.getSimpleName() + " atualizado com sucesso!\n"); 
        }catch(Exception ex){
            System.out.println("\033[46mErro ao tentar editar " + entityClass.getSimpleName()+ "\n"+ ex);                
        }finally{
        	this.entityManager.close();
        }
    }

    public void remove(T entity) {
        try{
        	this.entityManager.getTransaction().begin();
            this.entityManager.remove(this.entityManager.merge(entity));        
            this.entityManager.getTransaction().commit();
            System.out.println(entityClass.getSimpleName() + " excluído com sucesso!\n"); 
        }catch(Exception ex){
            System.out.println(entityClass.getSimpleName() + "\n"+ ex);                
        }finally{
        	this.entityManager.close();
        }
    }

    public T findByOneById(Integer id) {
        return this.entityManager.find(entityClass, id);
    }
    
    public List<T> findByName(String name) {
        try{
            return this.entityManager.createNamedQuery(getQueryFindByName(),entityClass).setParameter(getColumnName(), name).getResultList();
        }catch(Exception ex){
            System.out.println("Nenhum autor encontrado" + ex);            
            return null;
        }            
    }
    
    public T findOneByName(String name) {
        try{
            return this.entityManager.createNamedQuery(getQueryFindByName(),entityClass).setParameter(getColumnName(), name).getSingleResult();
        }catch(Exception ex){
            System.out.println(ex);            
            return null;
        }
            
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = this.entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return this.entityManager.createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = this.entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = this.entityManager.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = this.entityManager.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(this.entityManager.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = this.entityManager.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
