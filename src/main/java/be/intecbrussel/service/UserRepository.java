package be.intecbrussel.service;

import be.intecbrussel.model.UserEntity;
import be.intecbrussel.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository extends AbstractRepository {

    // CFUD :::: Create, Find, Update, Delete.
    // SLOGAN :::: Geen zin om te weten hoe ik een opnieuw EntityManager moet maken

    public void create ( UserEntity userEntity ) throws UserException {

        // WE FOCUSSEREN EERST UITZONDERINGEN (EXCEPTION)


        if ( userEntity == null ) {
            throw new UserException ( "UserEntity is null" );
        }

        if ( userEntity.getEmail ( ) == null && userEntity.getEmail ( ).isBlank ( )
                && userEntity.getEmail ( ).contains ( "@" ) ) {
            throw new UserException ( "User email is required and must be in valid format..!" );
        }

        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );

        em.getTransaction ( ).begin ( );
        em.persist ( userEntity );
        em.getTransaction ( ).commit ( );

        em.close ();
    }

    public void createAll ( List < UserEntity > userEntities ) {
        if ( userEntities == null ) {
            throw new UserException ( "UserEntity is null" );
        }
        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );
        em.getTransaction ( ).begin ( );
        for ( UserEntity userEntity : userEntities ) {
            em.persist ( userEntity );
        }
        em.getTransaction ( ).commit ( );

        em.close ();
    }

    public void updateFullNameById ( final Long id,
                                     final String firstName,
                                     final String middleName,
                                     final String lastName ) {

        if ( id == null ) {
            throw new UserException ( "Id is required!" );
        }

        if ( firstName == null || firstName.isBlank ( )
                || lastName == null || lastName.isBlank ( ) ) {
            throw new UserException ( "Firstname and lastname are required!" );
        }

        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );
        em.getTransaction ( ).begin ( );

        final UserEntity foundEntity = em.find ( UserEntity.class, id );

        // total no of records = 3
        // vind een gebruiker met een bepaalde id (5)
        if ( foundEntity == null ) {
            throw new UserException ( "UserEntity not found!" );
        }

        foundEntity.setFname ( firstName );
        foundEntity.setMname ( middleName );
        foundEntity.setLname ( lastName );

        em.getTransaction ( ).commit ( );

        em.close ();

    }

    public void updateEmailById ( final Long id, final String email ) {
        if ( id == null ) {
            throw new UserException ( "Id is required!" );
        }

        if ( email == null || email.isBlank ( ) || ! email.contains ( "@" ) ) {
            throw new UserException ( "Email is required and must be in valid format (john@doe.com)!" );
        }

        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );
        em.getTransaction ( ).begin ( );

        final UserEntity foundEntity = em.find ( UserEntity.class, id );

        if ( foundEntity == null ) {
            throw new UserException ( "UserEntity not found!" );
        }

        foundEntity.setEmail ( email );

        em.getTransaction ( ).commit ( );
        em.close ( );
    }

    public void delete ( final Long id ) {
        if ( id == null ) {
            throw new UserException ( "Id is required!" );
        }

        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );
        em.getTransaction ( ).begin ( );

        final UserEntity foundEntity = em.find ( UserEntity.class, id );

        if ( foundEntity == null ) {
            throw new UserException ( "UserEntity not found!" );
        }

        em.remove ( foundEntity );

        em.getTransaction ( ).commit ( );
        em.close ( );
    }

    // CRUD -> Create, Read, Update, Delete voor SQL
    // CFUD -> Create, Find, Update, Delete voor JPA
    public Optional < UserEntity > findById ( final Long id ) {

        if ( id == null ) {
            throw new UserException ( "Id is required!" );
        }

        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );

        em.getTransaction ( ).begin ( );

        final UserEntity foundEntity = em.find ( UserEntity.class, id );

        em.getTransaction ( ).commit ( );
        em.close ( );

        return Optional.ofNullable ( foundEntity );
    }

    public List < UserEntity > findAll ( ) {
        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );
        em.getTransaction ( ).begin ( );

        final List<UserEntity> entities = em.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();

        // Alternatief: Criteria API -> JPA

        em.getTransaction ( ).commit ( );
        em.close ( );

        return entities;
    }

    public Boolean existsByEmail ( String email ) {
        // TODO
        return false;
    }

    public Boolean isActiveByEmail ( String email ) {
        // TODO
        return false;
    }

}
