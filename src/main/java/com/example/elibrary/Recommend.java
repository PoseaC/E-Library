package com.example.elibrary;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/recommend")
public class Recommend extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            List<Book> recommendations = new ArrayList<>();

            ResultSet borrowedBooks = null;
            int userID = 0;

            for (Cookie cookie: request.getCookies()){
                if (cookie.getName().equals("UserID")){
                    userID = Integer.parseInt(cookie.getValue());
                    break;
                }
            }

            borrowedBooks = Database.instance.getBorrowedBooks(userID);
            List<String> genres = new ArrayList<>();
            while (borrowedBooks.next()){
                genres.add(borrowedBooks.getString("genre"));
            }

            Random r = new Random();
            List<Integer> idAlreadyRecommended = new ArrayList<>();
            for (int i = 0; i <= 10; i++){
                int choice = r.nextInt(genres.size());
                ResultSet book = Database.instance.getBookByGenre(genres.get(choice), userID);

                if (!book.next() || idAlreadyRecommended.contains(book.getInt("id")))
                    continue;

                Book b = new Book();
                idAlreadyRecommended.add(book.getInt("id"));
                b.setId(book.getInt("id"));
                b.setTitle(book.getString("title"));
                b.setDescription(book.getString("description"));
                b.setAuthor(book.getString("author"));
                b.setPages(book.getInt("pages"));
                b.setGenre(book.getString("genre"));

                recommendations.add(b);
            }

            request.setAttribute("books", recommendations);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/userRecommendations.jsp");
            dispatcher.forward(request, response);
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