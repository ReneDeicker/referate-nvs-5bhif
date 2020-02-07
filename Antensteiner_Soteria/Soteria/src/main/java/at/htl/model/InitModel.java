package at.htl.model;

import at.htl.Soteria.CustomInMemoryIdentityStore;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.HashSet;

@Startup
@Singleton
public class InitModel {
    @PostConstruct
    void init() throws NoSuchAlgorithmException,InvalidKeySpecException{
        byte[] salt = HelperClass.generateSalt();
        byte[] password = HelperClass.generateHashedPassword("admin".toCharArray(),salt);
        CustomInMemoryIdentityStore.users.add(new User("admin",password,new HashSet<>(Arrays.asList("ADMIN","USER")),salt));
        salt = HelperClass.generateSalt();
        password = HelperClass.generateHashedPassword("passme".toCharArray(),salt);
        CustomInMemoryIdentityStore.users.add(new User("user",password,new HashSet<>(Arrays.asList("USER")),salt));
        salt = HelperClass.generateSalt();
        password = HelperClass.generateHashedPassword("asdf123".toCharArray(),salt);
        CustomInMemoryIdentityStore.users.add(new User("Thomas",password,new HashSet<>(Arrays.asList("ADMIN","USER")),salt));
    }


}
