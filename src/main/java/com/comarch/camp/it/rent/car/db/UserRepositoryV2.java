package com.comarch.camp.it.rent.car.db;

import com.comarch.camp.it.rent.car.core.Constants;
import com.comarch.camp.it.rent.car.model.User;

import java.io.*;
import java.util.HashMap;

public class UserRepositoryV2 implements IUserRepository {
    private final HashMap<String, User> users = new HashMap<>();
    private static final UserRepositoryV2 instance = new UserRepositoryV2();

    private UserRepositoryV2() {
        try(BufferedReader reader =
                    new BufferedReader(new FileReader(Constants.USERS_FILE))) {
            String lineFromFile;
            while((lineFromFile = reader.readLine()) != null) {
                String[] userParts = lineFromFile.split(";");
                User user = new User(userParts[0], userParts[1], userParts[2]);
                this.users.put(user.getLogin(), user);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Pliku nie ma, zepsulo sie !!");
        } catch (IOException e) {
            System.out.println("Nie da sie pliku odczytać !!");
        }
    }

    @Override
    public User findByLogin(String login) {
        return this.users.get(login);
    }

    @Override
    public void save() {
        try(BufferedWriter writer =
                    new BufferedWriter(new FileWriter(Constants.USERS_FILE))) {
            boolean first = true;
            for(User user : this.users.values()) {
                if(!first) {
                    writer.newLine();
                }
                first = false;
                writer.write(user.convertToCSVString());
            }
        } catch (IOException e) {
            System.out.println("Users file writing error !");
        }
    }

    public static UserRepositoryV2 getInstance() {
        return instance;
    }
}
