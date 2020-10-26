//STEP 1. Import required packages
import java.sql.*;

public class Main{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "MegaSystem560";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //Create database
            stmt = conn.createStatement();

            String sqlCreateDatabase = "CREATE DATABASE STUDENTS";
            stmt.executeUpdate(sqlCreateDatabase);
            System.out.println("Database created successfully...");

            //Connect to the database
            String DB_URL_STUDENTS = "jdbc:mysql://localhost/Students";
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL_STUDENTS, USER, PASS);
            System.out.println("Connected database successfully...");

            //Create table
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            String sqlCreateTable = "CREATE TABLE REGISTRATION " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate( sqlCreateTable);
            System.out.println("Created table in given database...");

            //Insert row
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sqlInsertRow = "INSERT INTO Registration " +
                    "VALUES (100, 'Zara', 'Ali', 18)";
            stmt.executeUpdate(sqlInsertRow);
            sqlInsertRow = "INSERT INTO Registration " +
                    "VALUES (101, 'Mahnaz', 'Fatma', 25)";
            stmt.executeUpdate(sqlInsertRow);
            sqlInsertRow = "INSERT INTO Registration " +
                    "VALUES (102, 'Zaid', 'Khan', 30)";
            stmt.executeUpdate(sqlInsertRow);
            sqlInsertRow = "INSERT INTO Registration " +
                    "VALUES(103, 'Sumit', 'Mittal', 28)";
            stmt.executeUpdate(sqlInsertRow);
            System.out.println("Inserted records into the table...");

            //Select table
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sqlSelect = "SELECT id, first, last, age FROM Registration";
            ResultSet rs = stmt.executeQuery(sqlSelect);

            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }

            //Update table
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sqlUpdateTable = "UPDATE Registration " +
                    "SET age = 30 WHERE id in (100, 101)";
            stmt.executeUpdate(sqlUpdateTable);

            // Now you can extract all the records
            // to see the updated records
            sqlSelect = "SELECT id, first, last, age FROM Registration";
            rs = stmt.executeQuery(sqlSelect);

            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            rs.close();

            //Delete rows
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sqlDeleteRows = "DELETE FROM Registration " +
                    "WHERE id = 101";
            stmt.executeUpdate(sqlDeleteRows);

            // Now you can extract all the records
            // to see the remaining records
            sqlSelect = "SELECT id, first, last, age FROM Registration";
            rs = stmt.executeQuery(sqlSelect);

            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            rs.close();

            //Drop table
            System.out.println("Deleting table in given database...");
            stmt = conn.createStatement();

            String sqlDropTable = "DROP TABLE REGISTRATION ";

            stmt.executeUpdate(sqlDropTable);
            System.out.println("Table  deleted in given database...");

            //Drop database
            System.out.println("Deleting database...");
            stmt = conn.createStatement();

            String sqlDropDatabase = "DROP DATABASE STUDENTS";
            stmt.executeUpdate(sqlDropDatabase);
            System.out.println("Database deleted successfully...");




            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample