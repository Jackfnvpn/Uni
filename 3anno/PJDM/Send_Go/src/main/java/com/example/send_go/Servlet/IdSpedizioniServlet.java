package com.example.send_go.Servlet;

import com.example.send_go.Dao.Spedizioni.SendGoDaoSped;
import com.example.send_go.Dao.Spedizioni.SendGoDaoSpedImpl;
import com.example.send_go.Entity.Spedizione.listCoppieId;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class IdSpedizioniServlet extends HttpServlet {
    private SendGoDaoSped daoSped;
    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");

        System.out.print("SendGoServlet. Opening DB connection...");

        try {
            daoSped = new SendGoDaoSpedImpl(ip,port,dbName,dbUser,dbPass);
            System.out.println("Connessione riuscita");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Errore durante l'inizializzazione", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listCoppieId coppieId;
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        int id;

        if (req.getParameter("idCliente")!=null) {
            try {
                id = Integer.parseInt(req.getParameter("idCliente"));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                out.write("L'idCliente deve essere un intero");
                out.flush();
                out.close();
                return;
            }
            coppieId = daoSped.selectIdOrdineSpedizione(id);

            if (coppieId==null) {
                resp.setStatus(500);
                out.close();
                return;
            }

            if (coppieId.isEmpty()){
                resp.setStatus(204);
                out.close();
                return;
            }

            resp.setStatus(200);
            out.print(coppieId.toJson());
            out.flush();
            out.close();
        }
        else{
            resp.setStatus(400);
            out.write("Mancano dei campi");
            out.flush();
            out.close();
        }
        return;

    }

}
