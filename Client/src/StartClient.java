import interfaces.*;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;
import java.util.*;

public class StartClient {


    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef =   orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            UserService userService = (UserService) UserServiceHelper.narrow(ncRef.resolve_str("ABC"));

            System.out.println("Welcome to the calculator system:");

            User user = new User("Text", "Grazyna", "Janusz", 50, false, 1);
            User user1 = new User("Text3", "Janusz", "Grazyna", 50, false, 2);
            User responseUser = userService.createUser(user);
            User response2User = userService.createUser(user1);
            System.out.println(String.format("User: %s %s %s", responseUser.login, responseUser.name, responseUser.surname));

            user.age = 70;
            user = userService.updateUser(user);
            System.out.println(String.format("Updated user %s %s %2d", user.login, user.name, user.age));

        }
        catch (Exception e) {
            System.out.println("Hello Client exception: " + e);
            e.printStackTrace();
        }

    }
}