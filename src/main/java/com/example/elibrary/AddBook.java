package com.example.elibrary;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.sql.ResultSet;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
@WebServlet("/addBook")
public class AddBook extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String author = request.getParameter("author");
            int pages = Integer.parseInt(request.getParameter("pages"));
            Part filePart = request.getPart("file");
            String filename = filePart.getSubmittedFileName();
            String genre = request.getParameter("genre");

            filePart.write("C:\\Users\\mincu\\OneDrive\\Documents\\Teme_si_documente\\An4Sem1\\SDT\\project2\\E-Library\\src\\main\\webapp\\Books"
                            + title + author + filename);
            Database.instance.addBook(title, description, author, pages, filename, genre);
            response.sendRedirect(request.getContextPath() + "/adminMenu.jsp");
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