package com.example.send_go.Servlet;

import com.example.send_go.Dao.Customer.SendGoDaoCustomerImpl;
import com.example.send_go.Dao.Provincia.SendGoDaoProvincia;
import com.example.send_go.Dao.Provincia.SendGoDaoProvinciaImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ProvinciaServlet extends HttpServlet {
    private SendGoDaoProvincia dao;
    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");

        System.out.print("SendGoServlet. Opening DB connection...");



        dao = new SendGoDaoProvinciaImpl(ip, port, dbName, dbUser, dbPass);


        if (dao==null || !dao.isConnected()){
            throw new ServletException("Errore nella connessione al db");
        }

        System.out.println("Connessione riuscita");
    }

    @Override
    public void destroy() {
        System.out.print("MusicianServlet. Closing DB connection...");
        dao.closeConnection();
        System.out.println("DONE.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        String provMitt = request.getParameter("provMitt");
        String provDest = request.getParameter("provDest");

        if (provMitt != null && provDest != null) {
            provMitt = normalizzaProvincia(provMitt);
            provDest = normalizzaProvincia(provDest);

            boolean mittOk = dao.provExist(provMitt);
            boolean destOk = dao.provExist(provDest);

            if (mittOk && destOk) {
                response.setStatus(200);
            } else {
                response.setStatus(404);
            }
        } else {
            response.setStatus(400);
        }
    }

    private String normalizzaProvincia(String nome) {
        if (nome == null || nome.trim().isEmpty()) return "";
        nome = nome.trim().toLowerCase();

        String[] parole = nome.split("[-\\s]");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parole.length; i++) {
            if (parole[i].length() > 0) {
                sb.append(Character.toUpperCase(parole[i].charAt(0)))
                        .append(parole[i].substring(1));
                if (i < parole.length - 1) {
                    char separatore = nome.contains("-") ? '-' : ' ';
                    sb.append(separatore);
                }
            }
        }
        return sb.toString();
    }




}
