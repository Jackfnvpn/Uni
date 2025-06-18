package com.example.send_go.Servlet;

import com.example.send_go.Dao.VociCarrello.SendGoDaoVociCart;
import com.example.send_go.Dao.Customer.SendGoDaoCustomerImpl;
import com.example.send_go.Dao.Ordini.SendGoDaoOrder;
import com.example.send_go.Dao.Spedizioni.SendGoDaoSped;
import com.example.send_go.Dao.VociCarrello.SendGoDaoVociCartImpl;
import com.example.send_go.Entity.Carrello.ListCarello;
import com.example.send_go.Entity.Carrello.VociCarrello;
import com.example.send_go.Entity.Ticket.ListTicket;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class CarrelloServlet extends HttpServlet {
    private SendGoDaoVociCart daoCarrello;


    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");

        System.out.print("SendGoServlet. Opening DB connection...");


        daoCarrello = new SendGoDaoVociCartImpl(ip,port,dbName,dbUser,dbPass);

        if (daoCarrello == null || !daoCarrello.isConnected()) {
            throw new ServletException("Impossibile connettersi al database");
        }

        System.out.println("Connessione riuscita");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if (daoCarrello == null || !daoCarrello.isConnected()) {
            resp.setStatus(500);
            return;
        }

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        VociCarrello carrello;
        int idCliente;
        String jsonString = req.getParameter("json");

        System.out.println(jsonString);

        if (req.getParameter("idCliente") == null) {
            resp.setStatus(401);
            out.write("Accedi o registrati per aggiunegere al carrello la spedizione");
            out.flush();
            out.close();
            return;
        }

        if (jsonString != null) {
            JSONObject jsonCarrello = new JSONObject(jsonString);
            try {
                idCliente = Integer.parseInt(req.getParameter("idCliente"));
            }
            catch (NumberFormatException ex) {
                resp.setStatus(400);
                out.write("Id deve essere un valore intero");
                out.flush();
                out.close();
                return;
            }

            try {

                carrello = VociCarrello.fromJSON(jsonCarrello);
            } catch (JSONException e){
                e.printStackTrace();
                resp.setStatus(400);
                out.write(e.getMessage());
                out.flush();
                out.close();
                return;
            }

            int res = daoCarrello.insertVociCart(carrello, idCliente);

            if (res > 0) {
                resp.setStatus(200);
                out.close();
            } else {
                resp.setStatus(500);
                out.close();
            }

        } else {
            resp.setStatus(400);
            out.write("Campi mancanti");
            out.flush();
            out.close();
            return;
        }

    return;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (daoCarrello == null || !daoCarrello.isConnected()) {
            resp.setStatus(500);
            return;
        }

        ListCarello listCarello;
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        int id;

        if (req.getParameter("id")!=null) {
            try {
                id = Integer.parseInt(req.getParameter("id"));
            }
            catch (NumberFormatException ex) {
                resp.setStatus(400);
                out.write("Id deve essere un valore intero");
                out.flush();
                out.close();
                return;
            }

            listCarello = daoCarrello.getListCarrello(id);
        }
        else {
            resp.setStatus(401);
            out.write("Accedi o registrati per potere effettuare un ordine");
            out.flush();
            out.close();
            return;
        }

        if (listCarello.isEmpty()) {
            resp.setStatus(404);
            out.close();
        } else if (listCarello==null) {
            resp.setStatus(500);
            out.close();

        } else {
            resp.setStatus(200);
            System.out.println(listCarello.toJSONString());
            out.print(listCarello.toJSONString());
            out.flush();
            out.close();
        }
        return;
    }
}
