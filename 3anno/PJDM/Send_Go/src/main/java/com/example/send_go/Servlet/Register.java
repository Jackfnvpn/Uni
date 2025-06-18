package com.example.send_go.Servlet;

import java.io.*;

import com.example.send_go.Dao.Customer.SendGoDaoCustomer;
import com.example.send_go.Dao.Customer.SendGoDaoCustomerImpl;
import com.example.send_go.Entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.json.JSONException;
import org.json.JSONObject;


public class Register extends HttpServlet {
    private SendGoDaoCustomer dao;

    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");

        System.out.print("SendGoServlet. Opening DB connection...");

        try {
            dao = new SendGoDaoCustomerImpl(ip, port, dbName, dbUser, dbPass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Errore nella connessione al db", e);
        }
        if (dao==null || !dao.isConnected()) {
            throw new ServletException("Errore nella connessione col db");
        }

        System.out.println("Connessione riuscita");

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (dao==null || !dao.isConnected()) {
            response.setStatus(500);
            return;
        }

        response.setContentType("text/plain");
        Customer customer;
        String jsonReq=request.getParameter("json");

        if (jsonReq == null) {
            response.setStatus(400);
            return;
        }

        JSONObject jsonCustomer= new JSONObject(jsonReq);

        try {
            customer = Customer.fromJson(jsonCustomer);
        } catch (JSONException e) {
            response.setStatus(422);
            response.getWriter().println(e.getMessage());
            return;
        }

        int res = dao.insertCustomer(customer);

        if (res > 0) {
            response.setStatus(200);
        }
        else {
            response.setStatus(409);
        }

        return;
    }
}
