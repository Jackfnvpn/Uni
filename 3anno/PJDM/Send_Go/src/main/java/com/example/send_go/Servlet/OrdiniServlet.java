package com.example.send_go.Servlet;

import com.example.send_go.Dao.Ordini.SendGoDaoOrder;
import com.example.send_go.Dao.Ordini.SendGoDaoOrderImpl;
import com.example.send_go.Dao.Spedizioni.SendGoDaoSped;
import com.example.send_go.Dao.Spedizioni.SendGoDaoSpedImpl;
import com.example.send_go.Dao.VociCarrello.SendGoDaoVociCart;
import com.example.send_go.Dao.VociCarrello.SendGoDaoVociCartImpl;
import com.example.send_go.Entity.Carrello.VociCarrello;
import com.example.send_go.Entity.Ordine.ListOrder;
import com.example.send_go.Entity.Ordine.Ordine;
import com.example.send_go.Entity.Spedizione.ListSpedizione;
import com.example.send_go.Entity.Spedizione.Spedizione;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class OrdiniServlet extends HttpServlet {

    private SendGoDaoVociCart daoCart;
    private SendGoDaoOrder daoOrder;
    private SendGoDaoSped daoSped;

    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");

        System.out.print("SendGoServlet. Opening DB connection...");

        daoOrder = new SendGoDaoOrderImpl(ip,port,dbName,dbUser,dbPass);
        daoSped  = new SendGoDaoSpedImpl(ip,port,dbName,dbUser,dbPass);
        daoCart  = new SendGoDaoVociCartImpl(ip,port,dbName,dbUser,dbPass);

        if (daoCart==null || daoOrder==null || daoSped==null || !daoCart.isConnected()||!daoOrder.isConnected()||!daoSped.isConnected()) {
            throw new ServletException("Errore nella connessione col db");
        }

        System.out.println("Connessione riuscita");


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (daoCart==null || daoOrder==null || daoSped==null || !daoCart.isConnected()||!daoOrder.isConnected()||!daoSped.isConnected()) {
            response.setStatus(500);
            return;
        }

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        ListOrder order;
        int id;

        if (request.getParameter("idCliente") != null) {

            try {
                id = Integer.parseInt(request.getParameter("idCliente"));
            } catch (NumberFormatException e) {
                response.setStatus(400);
                out.write("L'id del cliente deve essere un intero");
                out.flush();
                out.close();
                return;
            }
            order = daoOrder.getListOrder(id);

            if (order == null) {
                response.setStatus(500);
                return;
            } else if (order.isEmpty()) {
                response.setStatus(404);
                return;
            }
            else {
                response.setStatus(200);
                out.write(order.toJSONString());
                out.flush();
                out.close();
                return;
            }
        }
        else {
            response.setStatus(400);
            out.write("Mancano dei campi");
            out.flush();
            out.close();
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (daoCart==null || daoOrder==null || daoSped==null || !daoCart.isConnected()||!daoOrder.isConnected()||!daoSped.isConnected()) {
            resp.setStatus(500);
            return;
        }

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        ListSpedizione spedizioni;
        int idCliente;
        String jsonString = req.getParameter("json");
        try {
            if (jsonString != null && req.getParameter("idCliente") != null) {

                System.out.println(jsonString);

                JSONObject jsonObj = new JSONObject(jsonString);
                try {
                    idCliente = Integer.parseInt(req.getParameter("idCliente"));
                } catch (NumberFormatException e) {
                    resp.setStatus(400);
                    out.write("L'id del cliente deve essere un intero");
                    out.flush();
                    out.close();
                    return;
                }

                double totale = jsonObj.getInt("totale");
                JSONArray array = jsonObj.getJSONArray("spedizioni");

                try {
                    spedizioni = ListSpedizione.fromJSONArray(array);
                } catch (JSONException e) {
                    resp.setStatus(400);
                    out.write(e.getMessage());
                    out.flush();
                    out.close();
                    return;
                }

                Ordine ordine = new Ordine(totale, idCliente);
                int idOrdineGenerato = daoOrder.insertOrdine(ordine, idCliente);

                if (idOrdineGenerato <= 0) {
                    resp.setStatus(500);
                    return;
                }

                for (Spedizione s : spedizioni) {
                    s.setIdOrdine(idOrdineGenerato);
                    int resSped = daoSped.insertSpedizioni(s);
                    if (resSped <= 0) {
                        resp.setStatus(500);
                        return;
                    }
                }

                int resEl = daoCart.clearCart(idCliente);

                if (resEl < 0) {
                    resp.setStatus(500);
                    return;
                }
                resp.setStatus(200);
                out.flush();
            } else {
                resp.setStatus(400);
                out.write("Campi mancanti");
                out.flush();
                out.close();
                return;
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
            resp.setStatus(400);
            out.write(e.getMessage());
            out.flush();
            out.close();
            return;
        }
    }
}
