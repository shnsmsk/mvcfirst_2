package be.intecbrussel.service;

import be.intecbrussel.model.MessageEntity;
import be.intecbrussel.model.RoleEntity;
import jakarta.persistence.EntityManager;

public class RoleRepository extends AbstractRepository {

    public void create(RoleEntity roleEntity) throws RoleException {
        if (roleEntity == null) {
            throw new RoleException("Role Entity is empty");
        }
        if (roleEntity.getTitle() == null || roleEntity.getTitle().isBlank()) {
            throw new RoleException("Please Enter valid Title");
        }
        if (!roleEntity.getActive()) {
            throw new MessageException("Role is not active");
        }
        final EntityManager em = super.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        em.persist(roleEntity);

        em.getTransaction().commit();

        em.close();
    }

    public void update(final Long id,String description) throws RoleException {
        if(id == null){
            throw new RoleException("Id is not valid");
        }
        if(description==null || description.isBlank()){
            throw new RoleException("Description can not be empty");
        }
        final EntityManager em = super.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        final RoleEntity roleEntity = em.find ( RoleEntity.class, id );

        roleEntity.setDescription(roleEntity.getDescription()+" "+description);

        em.persist ( roleEntity );

        em.getTransaction ( ).commit ( );

        em.close ( );
    }

    public void delete(final Long id) throws RoleException {
        if(id==null){
            throw new RoleException("Id is required");
        }
        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );

        em.getTransaction ( ).begin ( );

        final RoleEntity delete = em.find ( RoleEntity.class, id );

        em.remove(delete);

        em.getTransaction ( ).commit ( );

        em.close ( );
    }

    public void read(final Long id) throws RoleException {
        if(id==null){
            throw new RoleException("Id is required");
        }

        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );

        em.getTransaction ( ).begin ( );

        final RoleEntity roleEntity = em.find ( RoleEntity.class, id );

        System.out.println(roleEntity.getDescription());

        em.getTransaction ( ).commit ( );

        em.close ( );

    }

}
