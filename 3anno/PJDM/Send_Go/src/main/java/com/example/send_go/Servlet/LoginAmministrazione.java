package com.example.send_go.Servlet;

import com.example.send_go.Entity.Customer;
import com.example.send_go.Dao.Operatore.SendGoDaoOperator;
import com.example.send_go.Dao.Operatore.SendGoDaoOperatorImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginAmministrazione extends HttpServlet {
    private SendGoDaoOperator dao;
    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");

        System.out.print("SendGoServlet. Opening DB connection...");


        dao = new SendGoDaoOperatorImpl(ip,port,dbName,dbUser,dbPass);

        if (dao == null || !dao.isConnected()) {
            throw new ServletException("Errore nella connessione al db");
        }

        System.out.println("Connessione riuscita");

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (dao == null || !dao.isConnected()) {
            response.setStatus(500);
            return;
        }

        Customer customer;
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String email=request.getParameter("email");
        String password=request.getParameter("password");

        if (email == null|| password == null) {
            response.setStatus(422);
            response.getWriter().write("Email o password mancanti");
            return;
        }

        if (!email.contains("@")) {
            response.setStatus(422);
            response.getWriter().write("Formato email non valido");
            return;
        }
        customer = dao.checkCustomer(email,password);

        if (customer != null){
            response.setStatus(200);
            String customerJson = customer.toJson();
            response.getWriter().append(customerJson);
        }
        else{
            response.setStatus(401);
        }
        return;
    }
}
