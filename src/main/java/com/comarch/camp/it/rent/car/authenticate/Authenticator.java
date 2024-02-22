package com.comarch.camp.it.rent.car.authenticate;

import com.comarch.camp.it.rent.car.db.IUserRepository;
import com.comarch.camp.it.rent.car.db.UserRepository;
import com.comarch.camp.it.rent.car.db.UserRepositoryV2;
import com.comarch.camp.it.rent.car.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Authenticator implements IAuthenticator {
    private final IUserRepository userRepository;
    private final String seed = "v_#(jxXlmQ+Eh&[k[^Xtu{26=;GT_cW${;KhjVQ.";
    public static String loggedUserRole;

    public Authenticator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
}
