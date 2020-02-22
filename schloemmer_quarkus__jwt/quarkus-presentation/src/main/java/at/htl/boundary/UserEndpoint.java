package at.htl.boundary;

import at.htl.control.PasswordSecurityUtils;
import at.htl.control.TokenUtils;
import at.htl.control.dao.UserDAO;
import at.htl.entity.HashedPassword;
import at.htl.entity.MyUser;
import at.htl.restInterface.UserInterface;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Path("users")
@Transactional
public class UserEndpoint {

    @Inject
    UserDAO userRepo;

    @POST
    public Response createUser(UserInterface ui) {
//        return Response.ok().cookie(new NewCookie())
        HashedPassword hashedPassword = PasswordSecurityUtils.hashPassword(ui.getPassword());

        MyUser myUser = new MyUser(ui.getUsername(), hashedPassword);
        userRepo.persistUser(myUser);


//        Cookie cookie = new Cookie("me", "sessid", );
//        cookie.
//        NewCookie cookie = new NewCookie()

        // return jwt to permit access to secure information
        try {
            String token = TokenUtils.generateTokenString(myUser.getId().toString());
            JsonObjectBuilder builder = Json.createObjectBuilder();
            builder.add("accessToken", token);
            return Response.ok().entity(builder.build()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}
