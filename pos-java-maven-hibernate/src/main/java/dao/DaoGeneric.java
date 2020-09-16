package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import posjavamavenhibernate.HibernateUtil;

public class DaoGeneric<E> { //E de entidade
	
	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	/*-------------Método para salvar-------------*/
	public void salvar(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction(); // Iniciando transação/processo
		transaction.begin(); // iniciando
		entityManager.persist(entidade); // persistindo
		transaction.commit(); // salvando no banco de dados
	}
	
	
	/*-------------Método para salvar ou atualizar-------------*/
	public E updateMerge(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction(); // Iniciando transação/processo
		transaction.begin(); // iniciando
		E entidadeSalva = entityManager.merge(entidade); // persistindo
		transaction.commit(); // salvando no banco de dados
		
		return entidadeSalva;
	}
	
	
	/*-------------1º Método para consultar-------------*/	
	public E pesquisar1 (E entidade) { //pesquisando a Primary Key
		Object id = HibernateUtil.getPrimaryKey(entidade);
		E e = (E) entityManager.find(entidade.getClass(), id);
		return e; //retornando a entidade que pesquisou
	}
	
	/*-------------2º Método para consultar-------------*/	
	public E pesquisar (Long id, Class<E> entidade) { //pesquisando a Primary Key
	E e = (E) entityManager.find(entidade, id);
	return e; //retornando a entidade que pesquisou
	}
	
	/*-------------Método para deletar-------------*/
	public void deletarPorId (E entidade) { //pesquisando a Primary Key
		Object id = HibernateUtil.getPrimaryKey(entidade);
		EntityTransaction transaction = entityManager.getTransaction(); // Iniciando transação/processo
		transaction.begin(); //Dando Start na transação
		
		entityManager.createNativeQuery(
				"delete from " + entidade.getClass().getSimpleName().toLowerCase() +
				" where id = " + id).executeUpdate();

		transaction.commit(); //Grava alteração no banco
	}
	
	/*-------------Método para Listar-------------*/
	public List<E> listar (Class<E> entidade) { //pesquisando a Primary Key
		EntityTransaction transaction = entityManager.getTransaction(); // Iniciando transação/processo
		transaction.begin(); //Dando Start na transação
		
		List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
		
		transaction.commit(); //Grava alteração no banco
		
		return lista;
		
	}
	
	/*-------------Método para que seja possível usar o entityManager fora dessa classe-------------*/
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	
}
