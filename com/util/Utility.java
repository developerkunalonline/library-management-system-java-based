package com.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    private static List<Books> books = null;
    private static Utility util = null;

    public List<Books> getBooks() {
        return this.books;
    }

    public static Utility getInstance() {
        if (util == null) {
            return new Utility();
        }
        return util;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    private Utility() {

    }

    public List<Books> listBooks(ResultSet set) {
        List<Books> bList = new ArrayList<>();
        try {
            set.next();

            while (true) {
                bList.add(new Books(set.getInt(1), set.getString(2),set.getInt(3)));
                if(set.isLast()){
                    break;
                }
                set.next();
            }

            set.first();
            set.isFirst();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        

        
        return bList;
    }
    public void updateBook(Books book,int mode,Statement statement){

        if(mode == 1){
            book.isAvilabal = true;
            try {
                statement.executeUpdate("update books set avilable = 1 where id="+book.id);
            } catch (Exception e) {
                System.out.println("Something Went Wrong !");
                e.printStackTrace();
            }

        }
        else if(mode == 0){
            book.isAvilabal = false;
            try {
                statement.executeUpdate("update books set avilable = 0 where id=" + book.id);
            } catch (SQLException e) {
                System.out.println("Something Went Wrong !");
                e.printStackTrace();
            }
        }

    }
    public int searchBook(String name , List<Books> myBooks){
        
        for (int i = 0; i < myBooks.size(); i++) {
            Books b = myBooks.get(i);
            if (b.name.toLowerCase().equals(name.toLowerCase())) {
                
                return i;   
            }
        }
        return -1;
    }

}