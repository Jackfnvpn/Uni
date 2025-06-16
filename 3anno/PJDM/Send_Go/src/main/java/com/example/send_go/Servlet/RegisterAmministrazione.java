package com.example.send_go.Servlet;
import com.example.send_go.Dao.Operatore.SendGoDaoOperator;
import com.example.send_go.Dao.Operatore.SendGoDaoOperatorImpl;
import com.example.send_go.Entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RegisterAmministrazione extends HttpServlet {
    private SendGoDaoOperator dao;

    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");

        System.out.print("SendGoServlet. Opening DB connection...");

        try {
            dao = new SendGoDaoOperatorImpl(ip,port,dbName,dbUser,dbPass);
            System.out.println("Connessione riuscita");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Errore durante l'inizializzazione", e);
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
