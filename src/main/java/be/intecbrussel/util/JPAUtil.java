package be.intecbrussel.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// Laad de persistence.xml file en creëert een EntityManagerFactory
public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "mysql";

    // Privè-constructor is gecreëerd die maakt de utiliteit-klas klass-access-only.
    // Met deze manier kunnen de gebruikers van deze klas NOOIT een instantie aanmaken.

    private JPAUtil ( ) {

    }
    // XFactory is een utiliteit-klasse die maakt een opnieuw X klas
    // SomeFactory is een utiliteit-klasse die maakt een opnieuw Some klas
    // EntityManagerFactory is een utiliteit-klasse die maakt een opnieuw EntityManager klas

    // EntityManagerFactory is een utiliteit-klasse die maakt gebruik van de persistence.XML-file
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory ( ) {

        final String env = "DEFAULT_DATABASE_VENDOR";

        if ( factory == null && System.getenv ( env ) == null ) {
            factory = Persistence.createEntityManagerFactory ( PERSISTENCE_UNIT_NAME );
        } else if ( factory == null && System.getenv ( env ) != null ) {
            factory = Persistence.createEntityManagerFactory ( System.getenv ( "DEFAULT_DATABASE_VENDOR" ) );
        }
        return factory;
    }

    public static void shutdown ( ) {
        if ( factory != null ) {
            factory.close ( );
        }
    }
} // end class JPAUtil