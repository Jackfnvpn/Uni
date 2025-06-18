package com.example.send_go.Servlet;

import com.example.send_go.Dao.Spedizioni.SendGoDaoSped;
import com.example.send_go.Dao.Spedizioni.SendGoDaoSpedImpl;
import com.example.send_go.Entity.Spedizione.ListSpedizione;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SpedizioniServlet extends HttpServlet {
    private SendGoDaoSped daoSped;
    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");

        System.out.print("SendGoServlet. Opening DB connection...");

        daoSped = new SendGoDaoSpedImpl(ip,port,dbName,dbUser,dbPass);

        if (daoSped == null || !daoSped.isConnected()) {
            throw new ServletException("Errore nella connessione col db");
        }

        System.out.println("Connessione riuscita");

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ListSpedizione listaSpedizioni;
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        int id;

        if (request.getParameter("idCliente") != null) {
            try {
                 id = Integer.parseInt(request.getParameter("idCliente"));
            } catch (NumberFormatException e) {
                response.setStatus(400);
                out.write("L'idCliente deve essere un intero");
                out.flush();
                out.close();
                return;
            }

            listaSpedizioni=daoSped.getListSpedizioni(id);

            if (listaSpedizioni==null){
                response.setStatus(500);
                out.close();
                return;
            }

            if (listaSpedizioni.isEmpty()){
                response.setStatus(204);
                out.close();
                return;
            }

            response.setStatus(200);
            out.print(listaSpedizioni.toJSON());
            out.flush();
            return;
        }
        else{
            response.setStatus(400);
            out.write("Manca idCliente");
            out.flush();
            out.close();
            return;
        }
    }

}
