package com.example.send_go.Servlet;

import java.io.*;
import java.sql.SQLException;

import com.example.send_go.Dao.Customer.SendGoDaoCustomer;
import com.example.send_go.Dao.Customer.SendGoDaoCustomerImpl;
import com.example.send_go.Entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;


public class Login extends  HttpServlet{
    private SendGoDaoCustomer dao;
    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");

        System.out.print("SendGoServlet. Opening DB connection...");

        try {
            dao = new SendGoDaoCustomerImpl(ip,port,dbName,dbUser,dbPass);
            System.out.println("Connessione riuscita");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Errore durante l'inizializzazione", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Customer customer;
       PrintWriter out = response.getWriter();
       response.setContentType("text/plain");
       response.setCharacterEncoding("UTF-8");
       String email=request.getParameter("email");
       String password=request.getParameter("password");

        if (email == null|| password == null) {
            response.setStatus(400);
            out.write("Email o password mancanti");
            out.flush();
            out.close();
            return;
        }

        if (!email.contains("@")) {
            response.setStatus(422);
            response.getWriter().write("Formato email non valido");
            return;
        }

        try {
            customer = dao.checkCustomer(email,password);
            if (customer != null){
                response.setStatus(200);
                String customerJson = customer.toJson();
                response.getWriter().append(customerJson);
            }
            else{
                response.setStatus(401);
            }
        } catch (Exception e){
            response.setStatus(500);
            return;
        }
    }

}
