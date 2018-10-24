package db;

import java.util.HashMap;

public class DbSingleton {

    private static DbSingleton instance;

    HashMap<Integer,User> userList;
    HashMap<Integer,Book> bookList;
    HashMap<Integer,Integer> wypozyczone;

    private DbSingleton(){
    }

    public static DbSingleton getInstance() {

        if(instance == null) {
            instance = new DbSingleton();
        }

        return instance;
    }

}
