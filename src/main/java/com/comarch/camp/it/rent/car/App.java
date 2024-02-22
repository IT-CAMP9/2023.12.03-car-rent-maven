package com.comarch.camp.it.rent.car;

import com.comarch.camp.it.rent.car.configuration.AppConfiguration;
import com.comarch.camp.it.rent.car.core.Core;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);
        Core core = context.getBean(Core.class);
        core.start();
    }
}
