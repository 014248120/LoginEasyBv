/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import ohtu.domain.User;
import java.util.Scanner;

/**
 *
 * @author juhokyyh
 */
public class FileUserDao implements UserDao {
    private File file;

    public FileUserDao(String fileAddress) {
        this.file = new File(fileAddress);
    }

    @Override
    public List<User> listAll() {
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<User> list = new ArrayList<User>();
            
            while(scanner.hasNext()) {
                String userInfo = scanner.nextLine();
                String[] usernameAndPassword = userInfo.split(":");
                User user = new User(usernameAndPassword[0], usernameAndPassword[1]);
                list.add(user);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Something went wrong");
            return null;
        }
    }

    @Override
    public User findByName(String name) {
        List<User> users = listAll();
        for (User u : users) {
            if (u.getUsername().equals(name)) return u;
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            
            FileWriter filewriter = new FileWriter(file, true);
            filewriter.write(user.getUsername()+":"+user.getPassword()+"\n");
            filewriter.close();
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
    
}
