import db.DbSingleton;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class testDB {

    @Test
    public void testSingleton(){
        DbSingleton db = DbSingleton.getInstance();
        DbSingleton db2 = DbSingleton.getInstance();
        assertSame(db,db2);
    }

    @Test
    public void testUsers(){
        DbSingleton db = DbSingleton.getInstance();
        db.getUsers();
        db.addUser("Jan","Kowalski",1980,200);
        int removeID = db.addUser("Jan2","Kowalski2",1982,0);
        db.getUsers();
        db.removeUser(removeID);
        db.getUsers();
        db.modifyUser(1,"Jan","Kowaki",1910,200);
        db.getUsers();
    }

    @Test
    public void testBooks(){
        DbSingleton db = DbSingleton.getInstance();
        db.getBooks();
        db.addBook("Stary czlowiek i morze");
        int removeID = db.addBook("Ten nie może");
        db.getBooks();
        db.removeBook(removeID);
        db.getBooks();
        db.modifyBook(1,"Ten tez nie moze");
        db.getBooks();
    }

    @Test
    public void wypozyczenia(){
        DbSingleton db = DbSingleton.getInstance();
        db.addUser("Jan","Kowalski",1980,200);
        db.addUser("Jan2","Kowalski2",1982,0);
        db.addBook("Stary czlowiek i morze");
        db.addBook("Ten nie może");
        db.addBook("Ten tez nie asd może");
        db.wypozycz(1,1);
        db.wypozycz(1,2);
        db.wypozycz(2,3);
        db.listaWypozyczen();
    }

    @Test
    public void testImport(){
        DbSingleton db = DbSingleton.getInstance();
        db.getUsers();
        db.getBooks();
        db.importFromFile("users.csv","books.csv");
        db.getUsers();
        db.getBooks();
    }

    @Test
    public void testExport(){
        DbSingleton db = DbSingleton.getInstance();
        db.importFromFile("users.csv","books.csv");
        db.addUser("Jan3","Kowalski3",1982,0);
        db.addBook("costamcostamblabla");
        db.exportToFile("users2.csv","books2.csv");
    }
}
