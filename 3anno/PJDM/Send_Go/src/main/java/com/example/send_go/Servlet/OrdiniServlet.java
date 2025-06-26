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

    private SendGoDaoOrder daoOrder;

    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");

        System.out.print("SendGoServlet. Opening DB connection...");

        daoOrder = new SendGoDaoOrderImpl(ip,port,dbName,dbUser,dbPass);

        if (daoOrder == null || !daoOrder.isConnected()) {

            throw new ServletException("connessione al db fallita") ;
        }

        System.out.println("Connessione riuscita");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (daoOrder == null || !daoOrder.isConnected()) {
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
                response.setStatus(404);
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
    public void destroy() {
        System.out.print("MusicianServlet. Closing DB connection...");
        daoOrder.closeConnection();


        System.out.println("DONE.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (daoOrder == null || !daoOrder.isConnected()) {
            resp.setStatus(500);
            return;
        }

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        try {
            String jsonString = req.getParameter("json");
            String idClienteStr = req.getParameter("idCliente");

            if (jsonString == null || idClienteStr == null) {
                resp.setStatus(400);
                out.write("Campi mancanti");
                return;
            }

            int idCliente;
            try {
                idCliente = Integer.parseInt(idClienteStr);
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                out.write("L'id del cliente deve essere un intero");
                return;
            }

            JSONObject jsonObj = new JSONObject(jsonString);

            System.out.println(jsonObj);

            double totale = jsonObj.getDouble("totale");
            JSONArray array = jsonObj.getJSONArray("spedizioni");

            ArrayList<Spedizione> spedizioni;
            try {
                spedizioni = ListSpedizione.fromJSONArray(array);
            } catch (JSONException e) {
                resp.setStatus(400);
                out.write("Errore nel parsing delle spedizioni: " + e.getMessage());
                return;
            }

            Ordine ordine = new Ordine(totale, idCliente);

            boolean success = daoOrder.creaOrdineCompleto(ordine, spedizioni, idCliente);

            if (success) {
                resp.setStatus(200);
            } else {
                resp.setStatus(500);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            resp.setStatus(400);
            out.write("Errore JSON: " + e.getMessage());
        } finally {
            out.flush();
            out.close();
        }
    }

}
