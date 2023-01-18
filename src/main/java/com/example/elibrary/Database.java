package com.example.elibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    public static Database instance = new Database();
    Connection con;
    private Database(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library",
                    "root", "root");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public ResultSet checkCredentials(String username) throws Exception{
        Statement stmt = con.createStatement();

        String strSelect = "select * from users where username='" + username + "'";

        return stmt.executeQuery(strSelect);
    }
    public void addUser(String username, String password, String type) throws Exception{
        Statement stmt = con.createStatement();

        String strAdd = "insert into users(username, password, type) " +
                "values('" + username +"','" + password + "','" + type +"')";

        stmt.executeUpdate(strAdd);
    }
    public void addBook(String title, String description, String author, int pages, String filename, String genre) throws Exception{
        Statement stmt = con.createStatement();

        String strAdd = "insert into books(title, description, author, pages, filename, genre) " +
        "values('" + title + "','" + description + "','" + author + "'," + pages + ",'" + filename + "','" + genre + "')";

        stmt.executeUpdate(strAdd);
    }
    public ResultSet getBook(int id) throws Exception{
        Statement stmt = con.createStatement();

        String strSelect = "select * from books where id=" + id;

        return stmt.executeQuery(strSelect);
    }
    public void borrowBook(int user_id, int book_id) throws Exception{
        Statement stmt = con.createStatement();

        String strSelect = "select count(*) as count from books_borrowed where user_id=" + user_id + " and book_id=" + book_id;
        ResultSet countBorrows = stmt.executeQuery(strSelect);
        countBorrows.next();
        if (countBorrows.getInt("count") > 0)
            return;

        String strAdd = "insert into books_borrowed(user_id, book_id) " +
                "values(" + user_id + "," + book_id + ")";

        stmt.executeUpdate(strAdd);
    }
}
