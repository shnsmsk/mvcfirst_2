package be.intecbrussel.service;

import be.intecbrussel.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AbstractRepository {

    // SLOGAN :::: Geen zin om te weten hoe ik een opnieuw EntityManager moet maken
    private final EntityManagerFactory entityManagerFactory = JPAUtil.getEntityManagerFactory ( );

    public EntityManagerFactory getEntityManagerFactory ( ) {
        return entityManagerFactory;
    }

    public void close ( ) {
        System.out.println (
                "Closing EntityManagerFactory" );

        entityManagerFactory.close ( );
    }


}
