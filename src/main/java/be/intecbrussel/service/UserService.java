package be.intecbrussel.service;

public class UserService {

    private final UserRepository userRepository = new UserRepository ( );

    public boolean login ( final String email, final String password ) {

        // TODO: controleer of de gebruiker bestaat (email)

        if ( userRepository.existsByEmail ( email ) ) {
            throw new RuntimeException ( "User already exists" );
        }

        // TODO: controleer of de gebruiker actief is
        if ( ! userRepository.isActiveByEmail ( email ) ) {
            throw new RuntimeException ( "The user with given email is deleted." );
        }

        // TODO: controleer of de gebruikersnaam en het wachtwoord klopt zijn

        // TODO: als alles klopt, geef true terug

        return true;
    }

    public boolean register ( final String firstName,
                              final String middleName,
                              final String lastName,
                              final String email,
                              final String password ) {

        // TODO: controleer of de gebruiker bestaat, return false als dat zo (true) is

        // TODO: controleer of de gebruiker actief is, return false als dat zo (true) is

        // TODO: als de wachtwoord minder dan 6 tekens is, return false

        // TODO: Secure wachtwoord: als de wachtwoord niet minstens 1 hoofdletter, 1 kleine letter en 1 cijfer bevat, return false

        // TODO: als de gebruiker niet bestaat, maak een nieuwe gebruiker aan

        return true;
    }

}
