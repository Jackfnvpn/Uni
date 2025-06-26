package com.example.send_go.Servlet;

import com.example.send_go.Dao.Mesaggio.SendGoDaoMessaggioImpl;
import com.example.send_go.Dao.Tariffa.SendGoDaoTariffa;
import com.example.send_go.Dao.Tariffa.SendGoDaoTariffaInt;
import com.example.send_go.Dao.Ticket.SendGoDaoTicketImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class TariffaServlet extends HttpServlet {
    private SendGoDaoTariffaInt dao;
    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");
        System.out.print("SendGoServlet. Opening DB connection...");


        dao = new SendGoDaoTariffa(ip,port,dbName,dbUser,dbPass);
        System.out.println("Connessione riuscita");

        if (dao == null || !dao.isConnected() ) {
            throw new ServletException("Impossibile connettersi al database");
        }

    }

    @Override
    public void destroy() {
        System.out.print("MusicianServlet. Closing DB connection...");
        dao.closeConnection();
        System.out.println("DONE.");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (dao == null || !dao.isConnected()) {
            response.setStatus(500);
            return;
        }

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        System.out.println(request.getParameter("peso"));
        System.out.println(request.getParameter("idCorriere"));
        System.out.println(request.getParameter("tariffa"));


        if ( request.getParameter("idCorriere") != null && request.getParameter("peso") != null && request.getParameter("tariffa") != null) {
            double peso;
            double tariffa;
            int idCorriere;

            try {
                idCorriere = Integer.parseInt(request.getParameter("idCorriere"));
            }
            catch (NumberFormatException e) {
                response.setStatus(400);
                out.write("idcorriere deve essere un inter");
                out.flush();
                out.close();
                return;
            }

            try {
                peso = Double.parseDouble(request.getParameter("peso"));
                tariffa = Double.parseDouble(request.getParameter("tariffa"));
            } catch (NumberFormatException e) {
                response.setStatus(400);
                out.write("peso e tariffa devono essere un intero");
                out.flush();
                out.close();
                return;
            }

            if  (peso<0 || tariffa<0) {
                response.setStatus(400);
                out.write("peso e tariffa devono essere maggiori di 0");
                out.flush();
                out.close();
                return;
            }

            int res = dao.updateTariffa(idCorriere,tariffa,peso);

            System.out.println(res);


            if (res > 0) {
                response.setStatus(200);
            }
            else {
                response.setStatus(404);
            }
            return;
        }
        else {
            response.setStatus(400);
            out.write("Campi mancanti");
            out.flush();
            out.close();
            return;        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (dao == null || !dao.isConnected()) {
            response.setStatus(500);
            return;
        }

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        if (request.getParameter("idCorriere") != null && request.getParameter("peso") != null && request.getParameter("tariffa") != null) {
            int idCorriere;
            double peso;
            double tariffa;
            try {
                idCorriere = Integer.parseInt(request.getParameter("idCorriere"));
            } catch (NumberFormatException e) {
                response.setStatus(400);
                out.write("idCorriere deve essere un intero");
                out.flush();
                out.close();
                return;
            }

            try {
                peso = Double.parseDouble(request.getParameter("peso"));
                tariffa = Double.parseDouble(request.getParameter("tariffa"));
            } catch (NumberFormatException e) {
                response.setStatus(400);
                out.write("peso e tariffa devono essere un intero");
                out.flush();
                out.close();
                return;
            }

            if  (peso<0 || tariffa<0) {
                response.setStatus(400);
                out.write("peso e tariffa devono essere maggiori di 0");
                out.flush();
                out.close();
                return;
            }

            int res =dao.insertTariffa(idCorriere,peso,tariffa);

            if (res > 0) {
                response.setStatus(200);
                return;
            }
            else {
                response.setStatus(409);
                return;
            }
        }
    }
}
