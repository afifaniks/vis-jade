package vis;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import vis.entity.InsurancePackageEntity;
import vis.entity.UserEntity;

public class WriteSampleData {

	public static void main(String[] args) {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

		InsurancePackageEntity entity = new InsurancePackageEntity("Starter",
				"This package is most suitable for young people", 20.0, 36);

		InsurancePackageEntity entity2 = new InsurancePackageEntity("Premium",
				"This package is most suitable for business.", 100.0, 36);

		InsurancePackageEntity entity3 = new InsurancePackageEntity("Classic", "This package is suitable for all.",
				30.0, 24);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(entity2);
		session.persist(entity3);
		session.getTransaction().commit();
		session.close();
	}

}
