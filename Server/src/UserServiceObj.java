import interfaces.User;
import interfaces.UserServicePOA;
import org.omg.CORBA.ORB;

import java.util.ArrayList;

/**
 * Created by impresyjna on 25.11.2016.
 */
public class UserServiceObj extends UserServicePOA{
    private ORB orb;
    ArrayList<User>users = new ArrayList<>();

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    @Override
    public User createUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public String deleteUser(int userId) {
        for(User user:users) {
            if(user.id == userId) {
                users.remove(user);
                return "Success";
            }
        }
        return "Failure";
    }

    @Override
    public User getUser(int userId) {
        for(User user:users) {
            if(user.id == userId) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        for(int i=0; i<users.size(); i++)
        {
            User userTmp = users.get(i);
            if(userTmp.id == user.id) {
                users.set(i, user);
                return user;
            }
        }
        return null;
    }

    @Override
    public User[] getUsers() {
        return (User[]) users.toArray();
    }
}
