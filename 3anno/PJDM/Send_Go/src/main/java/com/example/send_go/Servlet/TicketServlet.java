package com.example.send_go.Servlet;

import com.example.send_go.Dao.Mesaggio.SendGoDaoMessaggio;
import com.example.send_go.Dao.Mesaggio.SendGoDaoMessaggioImpl;
import com.example.send_go.Dao.Ticket.SendGoDaoTicket;
import com.example.send_go.Dao.Ticket.SendGoDaoTicketImpl;

import com.example.send_go.Entity.Messaggio.Messaggio;
import com.example.send_go.Entity.Ticket.ListTicket;
import com.example.send_go.Entity.Ticket.Ticket;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class TicketServlet extends HttpServlet {

    private SendGoDaoTicket dao;
    private SendGoDaoMessaggio daoMessaggio;

    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");
        System.out.print("SendGoServlet. Opening DB connection...");


        dao = new SendGoDaoTicketImpl(ip,port,dbName,dbUser,dbPass);
        daoMessaggio= new SendGoDaoMessaggioImpl(ip,port,dbName,dbUser,dbPass);
        System.out.println("Connessione riuscita");

        if (dao == null || !dao.isConnected() || daoMessaggio == null || !daoMessaggio.isConnected()) {
            throw new ServletException("Impossibile connettersi al database");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (dao == null || !dao.isConnected() ||  daoMessaggio == null || !daoMessaggio.isConnected()) {
            resp.setStatus(500);
            return;
        }

        resp.setContentType("text/plain");
        Ticket ticket;
        Messaggio messaggio;
        String json;

        if (req.getParameter("json")!=null && req.getParameter("idTicket")==null) {
            json = req.getParameter("json");
            System.out.println(json);

            JSONObject obj = new JSONObject(json);
            try{
                ticket=Ticket.fromJSON(obj);
            }
            catch (JSONException e){
                e.printStackTrace();
                resp.getWriter().println(e.getMessage());
                resp.setStatus(400);
                return;
            }
            int ticketId = dao.insertTicket(ticket);
            ticket.setId(ticketId);
            if (ticketId > 0) {
                try{
                    JSONArray objMessaggio = obj.getJSONArray("messaggi");
                    messaggio=Messaggio.fromJson(objMessaggio.getJSONObject(0));
                }
                catch(JSONException e){
                    e.printStackTrace();
                    resp.getWriter().println(e.getMessage());
                    resp.setStatus(400);
                    return;
                }
                int res = daoMessaggio.insertMessaggio(messaggio, ticketId);
                if(res > 0){
                    resp.setStatus(200);
                    return;
                }
                else {
                    resp.setStatus(500);
                }
            }
            else {
                resp.setStatus(500);
                return;
            }

        }
        else if (req.getParameter("idTicket")!=null && req.getParameter("json") != null) {
            int idTicket = Integer.parseInt(req.getParameter("idTicket"));
            json = req.getParameter("json");
            JSONObject obj = new JSONObject(json);
            try {
                messaggio=Messaggio.fromJson(obj);
            } catch (JSONException e) {
                resp.getWriter().println(e.getMessage());
                resp.setStatus(422);
                return;
            }
            int res = daoMessaggio.insertMessaggio(messaggio, idTicket);

            if (res > 0) {
                resp.setStatus(200);
                return;
            }
            else {
                resp.setStatus(500);
                return;
            }

        }
        else {
            resp.setStatus(400);
            return;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (dao == null || !dao.isConnected()) {
            resp.setStatus(500);
            return;
        }

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        ListTicket listTicket;
        int id,idTicket;

        if (req.getParameter("idTicket") == null && req.getParameter("id") != null) {
            try {
                id = Integer.parseInt(req.getParameter("id"));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                out.write("Id cliente deve essere un valore intero");
                out.flush();
                out.close();
                return;
            }

            listTicket = dao.getListTicket(id);

            if (listTicket == null) {
                resp.setStatus(500);
                return;
            }

            if (listTicket.isEmpty()) {
                resp.setStatus(404);
                return;
            }

            resp.setStatus(200);
            out.print(listTicket.toJSONString());
            out.flush();
            return;
        } else if (req.getParameter("idTicket") != null) {
            try {
                idTicket = Integer.parseInt(req.getParameter("idTicket"));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                out.write("Id cliente deve essere un valore intero");
                out.flush();
                out.close();
                return;
            }

            Ticket ticket = dao.getTicket(idTicket);

            if (ticket != null) {
                resp.setStatus(200);
                out.print(ticket.toJSONString());
                out.flush();
            } else {
                resp.setStatus(404);
            }
        } else if (req.getParameter("idTicket") == null && req.getParameter("id") == null) {
            listTicket = dao.getListAllTicket();
            if (listTicket == null) {
                resp.setStatus(500);
                return;
            }

            else if (listTicket.isEmpty()) {
                resp.setStatus(404);
                return;
            }

            resp.setStatus(200);
            out.print(listTicket.toJSONString());
            out.flush();
            return;

        } else {
            resp.setStatus(400);
            return;
        }
    }
}

