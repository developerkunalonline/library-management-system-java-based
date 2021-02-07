import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import com.util.Books;
import com.util.Utility;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Programe will start soon !!");
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?autoReconnect=true&useSSL=false", "testing", "password");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from books");

        Utility utility = Utility.getInstance();
        List<Books> bookList = utility.listBooks(resultSet);
        
        System.out.println("***************** Welcome to Kunal\'s Central Library *****************");
        while (true) {
            System.out.println("++------- Menu -------++");    
            System.out.println("||                    ||");
            System.out.println("||   1. Show Books    ||");
            System.out.println("||   2. Borrow Books  ||");
            System.out.println("||   3. Return Books  ||");
            System.out.println("||   4. Exit Library  ||");
            System.out.println("||                    ||");
            System.out.println("++------- xxxx -------++");    
            System.out.print("Enter your choice (number) : ");
            int choice;
            try {
                choice = Integer.parseInt(reader.readLine());
                System.out.println();
            } catch (Exception e) {
                System.out.println("Please enter select valid option");
                continue;
            
            }
            if (choice == 4) {
                break;
            }
            else if(choice == 1){
                System.out.println("\t++------- Menu -------++");    
                for(Books b:bookList){
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        
                    }
                    System.out.println("\t   "+b.id+". "+b.name);
                }
                System.out.println("\t++------- xxxx -------++");
                System.out.print("Press any key to continue : ");                        
                reader.readLine();
            }
            else {
                
                System.out.print("Enter book name : ");
                String name = reader.readLine();
                System.out.println();
                int search = utility.searchBook(name, bookList);

                if (search!=-1 && choice==2) {

                    if (!bookList.get(search).isAvilabal) {
                        System.out.println("We are sorry but we cant issue this "+name+" book to you because it is already borrowed by someone else !");
                        System.out.print("Press any key to continue : ");                        
                        reader.readLine();
                        continue;
                    }
                    utility.updateBook(bookList.get(search), 0, statement);
                    System.out.println("Book issued sucessfully !");
                    System.out.print("Press any key to continue : ");                        
                    reader.readLine();
                }
                else if (search!=-1 && choice==3) {
                    if (bookList.get(search).isAvilabal) {
                        System.out.println("Thanks ! but we already having this book !");
                        System.out.print("Press any key to continue : ");                        
                        reader.readLine();
                        continue;
                    }
                    utility.updateBook(bookList.get(search), 1, statement);
                    System.out.println("Book returned sucessfully !");
                    System.out.print("Press any key to continue : ");                        
                    reader.readLine();
                }
                else{
                    System.out.println("Book not Found !");
                    continue;
                }

            }


        }

        System.out.println("Bye \nUse me again :)");
        
    }

}
