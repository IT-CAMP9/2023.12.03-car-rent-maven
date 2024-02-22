package com.comarch.camp.it.rent.car.configuration;

import com.comarch.camp.it.rent.car.authenticate.Authenticator;
import com.comarch.camp.it.rent.car.authenticate.IAuthenticator;
import com.comarch.camp.it.rent.car.db.IUserRepository;
import com.comarch.camp.it.rent.car.model.Bus;
import com.comarch.camp.it.rent.car.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(value = "com.comarch.camp.it.rent.car")
/*@ComponentScan(basePackages = {
        "com.comarch.camp.it.rent.car.authenticate",
        "com.comarch.camp.it.rent.car.core",
        "com.comarch.camp.it.rent.car.db",
        "com.comarch.camp.it.rent.car.gui"
})*/
public class AppConfiguration {
    /*@Bean
    public IAuthenticator authenticator(IUserRepository userRepository) {
        return new Authenticator(userRepository);
    }

    @Bean
    public User bus() {
        return new User();
    }*/
}
