package com.example.elibrary;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet("/download")
public class Download extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            int bookId = Integer.parseInt(request.getParameter("book_id"));
            ResultSet book = Database.instance.getBook(bookId);
            book.next();
            String title = book.getString("title");
            String author = book.getString("author");
            String filename = book.getString("filename");

            for (Cookie cookie: request.getCookies()){
                if (cookie.getName().equals("UserID")){
                    Database.instance.borrowBook(Integer.parseInt(cookie.getValue()), bookId);
                    break;
                }
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=\"" + title + "-" + author + ".pdf\"");

            PrintWriter out = response.getWriter();
            FileInputStream inputStream = new FileInputStream(
                    "C:\\Users\\mincu\\OneDrive\\Documents\\Teme_si_documente\\An4Sem1\\SDT\\project2\\E-Library\\src\\main\\webapp\\Books"
                            + title + author + filename);

            int in;
            while ((in = inputStream.read()) != -1) {
                out.write(in);
            }

            inputStream.close();
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        doGet(request,response);
    }

    public void destroy() {
    }
}