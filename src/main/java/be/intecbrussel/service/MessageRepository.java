package be.intecbrussel.service;

import be.intecbrussel.model.MessageEntity;
import be.intecbrussel.model.UserEntity;
import jakarta.persistence.EntityManager;
import lombok.experimental.NonFinal;

public class MessageRepository extends AbstractRepository {

    public void create ( MessageEntity messageEntity ) throws MessageException{
        if(messageEntity==null){
            throw new MessageException("Message Entity is Null");
        }

        if(messageEntity.getSender()==null && (messageEntity.getSubject().isBlank()|| messageEntity.getSubject()==null)){
            throw new MessageException("Sender information and subject must be in valid format");
        }
        if(messageEntity.getReceivers()==null){
            throw new MessageException("Receiver missing...");
        }
        if(!messageEntity.getActive()){
            throw new MessageException("Sender is not active");
        }
        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );

        em.getTransaction ( ).begin ( );

        em.persist ( messageEntity );

        em.getTransaction ( ).commit ( );

        em.close ( );
    }

    public void update ( final Long id, final String message ) throws MessageException{
        if(id==null){
            throw new MessageException("Id is required");
        }
        if(message==null || message.isBlank()){
            throw new MessageException("Message information missing");
        }
        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );

        em.getTransaction ( ).begin ( );

        final MessageEntity messageEntity = em.find ( MessageEntity.class, id );

        messageEntity.setContent(messageEntity.getContent()+" "+message);

        em.persist ( messageEntity );

        em.getTransaction ( ).commit ( );

        em.close ( );
    }

    public void delete (final Long id) throws MessageException{
        if(id==null){
            throw new MessageException("Id is required");
        }
        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );

        em.getTransaction ( ).begin ( );

        final MessageEntity delete = em.find ( MessageEntity.class, id );

        em.remove(delete);

        em.getTransaction ( ).commit ( );

        em.close ( );
    }

    public void read ( final Long id ) throws MessageException{
        if(id==null){
            throw new MessageException("Id is required");
        }

        final EntityManager em = super.getEntityManagerFactory ( ).createEntityManager ( );

        em.getTransaction ( ).begin ( );

        final MessageEntity messageEntity = em.find ( MessageEntity.class, id );

        System.out.println(messageEntity.getContent());

        em.getTransaction ( ).commit ( );

        em.close ( );

    }

}
