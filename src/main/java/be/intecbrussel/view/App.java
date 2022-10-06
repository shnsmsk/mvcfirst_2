package be.intecbrussel.view;

import be.intecbrussel.model.MessageEntity;
import be.intecbrussel.model.RoleEntity;
import be.intecbrussel.model.UserEntity;
import be.intecbrussel.service.MessageRepository;
import be.intecbrussel.service.RoleRepository;
import be.intecbrussel.service.UserRepository;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main ( String[] args ) {
        UserRepository userRepository = new UserRepository ( );

        UserEntity u01 = new UserEntity ( );
        u01.setFname ( "John" );
        u01.setLname ( "Doe" );
        u01.setEmail ( "john@doe.be" );
        u01.setHashedPassword ( "123456" );
        u01.setValidation ( "654321" );
        u01.setActive ( true );

        UserEntity u02 = new UserEntity ( );
        u02.setFname ( "Jane" );
        u02.setLname ( "Doe" );
        u02.setEmail ( "jane@doe.be" );
        u02.setHashedPassword ( "123456" );
        u02.setValidation ( "654321" );
        u02.setActive ( true );

        UserEntity u03 = new UserEntity ( );
        u02.setFname ( "Sahin" );
        u02.setLname ( "Simsek" );
        u02.setEmail ( "sahin@doe.be" );
        u02.setHashedPassword ( "1234567" );
        u02.setValidation ( "654322" );
        u02.setActive ( true );

        userRepository.create ( u01 );
        userRepository.create ( u02 );

        MessageEntity message1=new MessageEntity();
        message1.setActive(true);
        message1.setSender(u01);
        message1.setSubject("Greetings");
        message1.setContent("Hello how are you guys?");
        message1.setReceivers(u03);
        message1.setReceivers(u02);
        MessageRepository messageRepository=new MessageRepository();
        messageRepository.create(message1);
        messageRepository.update(1L,"update : I need help!!!");
        messageRepository.read(1L);
        messageRepository.delete(1L);
        RoleEntity roleEntity=new RoleEntity();
        Lorem lorem=new LoremIpsum();
        roleEntity.setDescription(lorem.getWords(10));
        roleEntity.setActive(true);
        roleEntity.setTitle(lorem.getTitle(1));
        RoleRepository roleRepository=new RoleRepository();
        roleRepository.create(roleEntity);
        roleRepository.update(1L,lorem.getWords(3));
        roleRepository.read(1L);
        roleRepository.delete(1L);





    }
}
