package com.comarch.camp.it.rent.car.authenticate;

import com.comarch.camp.it.rent.car.db.IUserRepository;
import com.comarch.camp.it.rent.car.db.UserRepository;
import com.comarch.camp.it.rent.car.db.UserRepositoryV2;
import com.comarch.camp.it.rent.car.model.User;
import org.apache.commons.codec.digest.DigestUtils;

public class Authenticator implements IAuthenticator {
    private final IUserRepository userRepository = UserRepository.getInstance();
    private final String seed = "v_#(jxXlmQ+Eh&[k[^Xtu{26=;GT_cW${;KhjVQ.";
    public static String loggedUserRole;
    private static final Authenticator instance = new Authenticator();

    private Authenticator() {}

    @Override
    public boolean authenticate(String login, String password) {
        User user = this.userRepository.findByLogin(login);
        if(user != null &&
                DigestUtils.md5Hex(password+seed).equals(user.getPassword())) {
            loggedUserRole = user.getRole();
            return true;
        }
        return false;
    }

    public static Authenticator getInstance() {
        return instance;
    }
}
