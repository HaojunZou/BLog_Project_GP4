package se.molk.blog.web.controller;

import se.molk.blog.utils.Mail;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;



@WebServlet("/ContactControl")
public class ContactControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contactName = request.getParameter("contactName");
        String contactEmail = request.getParameter("contactEmail");
        String contactMessage = request.getParameter("contactMessage");

        Mail mail = new Mail();
        mail.BlogMail(contactName, contactEmail, contactMessage);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/contact.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
