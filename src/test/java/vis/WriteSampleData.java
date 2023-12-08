package vis;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import vis.entity.InsurancePackageEntity;

public class WriteSampleData {

	public static void main(String[] args) {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

		InsurancePackageEntity entity = new InsurancePackageEntity("Starter",
				"This package is suitable for most old cars. As your car has mileage more than 100,000 miles, we recommend you to take this package.",
				20.0, 36);

		InsurancePackageEntity entity2 = new InsurancePackageEntity("Premium",
				"This package is to cover your new vehicle. As your car has mileage less than 10,000 miles, we recommend you to take this package for extra care.",
				50.0, 36);

		InsurancePackageEntity entity3 = new InsurancePackageEntity("Classic",
				"This is our customer favorite! No matter whether your car is new or old, this package covers most of the things you could ask for.",
				30.0, 24);

		InsurancePackageEntity entity4 = new InsurancePackageEntity("Basic",
				"We recommend customers with vehicle mileage from 10,000 miles up to 100,000 miles to take this package.",
				25.0, 48);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(entity);
		session.persist(entity2);
		session.persist(entity3);
		session.persist(entity4);
		session.getTransaction().commit();
		session.close();
	}

}
