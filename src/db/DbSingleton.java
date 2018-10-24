package db;

public class DbSingleton {

    private static DbSingleton instance;

    private DbSingleton(){

    }

    public static DbSingleton getInstance() {

        if(instance == null) {
            instance = new DbSingleton();
        }

        return instance;
    }

    public void importFromFile(String filePath){

    }

    public void exportToFile(String filePath){

    }
}
