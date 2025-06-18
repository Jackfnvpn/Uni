package com.example.send_go.Servlet;

import com.example.send_go.Dao.Corriere.SendGoDaoCorriere;
import com.example.send_go.Dao.Corriere.SendGoDaoCorriereImpl;
import com.example.send_go.Entity.Corriere.ListCorriere;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class CorriereServlet extends HttpServlet {
    private SendGoDaoCorriere dao;

    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String dbUser = getInitParameter("userName");
        String dbPass = getInitParameter("password");

        System.out.print("SendGoServlet. Opening DB connection...");
        dao = new SendGoDaoCorriereImpl(ip,port,dbName,dbUser,dbPass);

        if (dao == null || !dao.isConnected()) {
            throw new ServletException("Errore nella connessione col db");
        }

        System.out.println("Connessione riuscita");

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (dao == null || !dao.isConnected()) {
            resp.setStatus(500);
            return;
        }

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        ListCorriere listCorriere = dao.getCorrieres();

        if (listCorriere == null) {
            resp.setStatus(500);
            return;
        }

        System.out.println(listCorriere.toJSONString());

        resp.setStatus(200);
        out.print(listCorriere.toJSONString());
        out.flush();
        out.close();
        return;

    }
}
