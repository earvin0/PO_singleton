package db;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DbSingleton {

    private static DbSingleton instance;

    private AtomicInteger nextUserID = new AtomicInteger(1);
    private Map<Integer,User> userList = new HashMap<>();
    private AtomicInteger nextBookID = new AtomicInteger(1);
    private Map<Integer,Book> bookList = new HashMap<>();
    private Map<Integer, ArrayList<Integer>> wypozyczenia = new HashMap<>();

    private DbSingleton(){

    }

    public static DbSingleton getInstance() {

        if(instance == null) {
            instance = new DbSingleton();
        }

        return instance;
    }

    public void importFromFile(String path,String path2){

            try(
                    Reader in = new FileReader("src/main/resources/" + path);
                    CSVParser csvParser = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader()
                            .withIgnoreHeaderCase()
                            .withTrim());
                    Reader in2 = new FileReader("src/main/resources/" + path2);
                    CSVParser csvParser2 = new CSVParser(in2, CSVFormat.DEFAULT.withFirstRecordAsHeader()
                            .withIgnoreHeaderCase()
                            .withTrim())
                    )
            {
                List<CSVRecord> users = csvParser.getRecords();
                List<CSVRecord> books = csvParser2.getRecords();
                users.forEach(user -> addUser(user.get("name"),user.get("lastName"),Integer.parseInt(user.get("year")),Integer.parseInt(user.get("penalty"))));
                books.forEach(book -> addBook(book.get("title")));


            }catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void exportToFile(String path, String path2){
        try {
            BufferedWriter userWriter = new BufferedWriter(new FileWriter("src/main/resources/" + path));
            BufferedWriter bookWriter = new BufferedWriter(new FileWriter("src/main/resources/" + path2));
            userWriter.write("id,name, lastName, year, penalty\n");
            userList.forEach((key,value)-> {
                try {
                    userWriter.append(value.getId()+","+value.getImie()+","+value.getNazwisko()+","+value.getRok()+","+value.getKara()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            bookWriter.write("id,title\n");
            bookList.forEach((key,value)-> {
                try {
                    bookWriter.append(value.getId()+","+value.getNazwa()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public int addUser(String name, String lastName, int year, int penalty){
        int newID = nextUserID.getAndIncrement();
        userList.put(newID, new User(newID,name,lastName,year,penalty));
        wypozyczenia.put(newID,new ArrayList<>());
        return newID;
    }

    public void removeUser(int id){
        userList.remove(id);
    }

    public void modifyUser(int userID,String name, String lastName, int year, int penalty){
        userList.replace(userID,new User(userID,name,lastName,year,penalty));
    }

    public void getUsers(){
        System.out.println("List of users: ");
        userList.forEach((key, value) -> System.out.println("user id: "+key+" name: "+value.getImie()+" surname: "+value.getNazwisko()+" rok: "+value.getRok()+" kara: "+value.getKara()));
    }

    public int addBook(String nazwa){
        int newID = nextBookID.getAndIncrement();
        bookList.put(newID,new Book(newID,nazwa));
        return newID;
    }

    public void removeBook(int id){
        bookList.remove(id);
    }

    public void modifyBook(int id,String nazwa){
        bookList.replace(id,new Book(id,nazwa));
    }

    public void getBooks(){
        System.out.println("List of books: ");
        bookList.forEach((key, value) -> System.out.println("book id: "+key+" name: "+value.getNazwa()));
    }

    public void wypozycz(int userID, int bookID){
        wypozyczenia.get(userID).add(bookID);
    }

    public void zwroc(int userID, int bookID){
        wypozyczenia.get(userID).remove(bookID);
    }

    public void listaWypozyczen(){
        System.out.println("Wypozyczenia:");
        wypozyczenia.forEach((user,lista)-> {
            lista.forEach(ksiazka -> System.out.println(userList.get(user).getImie()+" "+userList.get(user).getNazwisko()+ " wypozyczyl "+bookList.get(ksiazka).getNazwa()));
            ;
        });
    }
}
