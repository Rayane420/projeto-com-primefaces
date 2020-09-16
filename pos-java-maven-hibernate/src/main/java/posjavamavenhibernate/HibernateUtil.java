package posjavamavenhibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	
	public static EntityManagerFactory factory = null; //fábrica de conexão do hibernate
	
	static {  //invocando a classe sempre que é iniciado
		init();
	}
	
	
	private static void init() {
		
		try {
			
			if(factory == null) {
				factory = Persistence.createEntityManagerFactory("pos-java-maven-hibernate");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static EntityManagerFactory getFactory() {
		return factory;
	}
	
	public static EntityManager getEntityManager () {
		return factory.createEntityManager(); //provê a parte de persistência
	}
	
	public static Object getPrimaryKey(Object entity) { //Método para retornar a Primary Key
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}
	
	
	
}