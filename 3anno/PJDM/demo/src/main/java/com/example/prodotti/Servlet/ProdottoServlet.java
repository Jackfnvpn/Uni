package com.example.prodotti.Servlet;

import com.example.prodotti.Dao.ProdottiDao;
import com.example.prodotti.Dao.ProdottiDaoImpl;
import com.example.prodotti.Entity.ListaProdotti;
import com.example.prodotti.Entity.Prodotto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;

import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/prodotti")
public class ProdottoServlet extends HttpServlet {

    ProdottiDao dao;

    @Override
    public void init() throws ServletException {
        String ip = getInitParameter("ip");
        String port = getInitParameter("port");
        String dbName = getInitParameter("dbName");
        String userName = getInitParameter("userName");
        String password = getInitParameter("password");

        System.out.print("MusicianServlet. Opening DB connection...");

        dao = new ProdottiDaoImpl(ip,port,dbName,userName,password);
        System.out.println("DONE.");
    }

    @Override
    public void destroy() {
        System.out.print("ProdottiServlet. Closing DB connection...");
        dao.closeConnection();
        System.out.println("DONE.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ListaProdotti allProdotti = dao.loadTuttiProdotti();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        out.print(allProdotti.toJSONString());
        out.flush();
        resp.setStatus(200);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");

        Prodotto prodotto;

        if (req.getParameter("json") != null) {
            String jsonString = req.getParameter("json");
            try {
                prodotto = Prodotto.fromJSON(jsonString);
            } catch (JSONException e) {
                resp.setStatus(422);
                resp.getWriter().append("Il file JSON è malformato");
                return;
            }
        }

        else if (req.getParameter("id") != null && req.getParameter("nome") != null
                && req.getParameter("quantita") != null && req.getParameter("prezzo") != null) {
            int id = -1;
            int quantita = -1;
            double prezzo = -1;

            try {
                id = Integer.valueOf(req.getParameter("id"));
                quantita = Integer.valueOf(req.getParameter("quantita"));
                prezzo = Double.valueOf(req.getParameter("id"));
            }
            catch (NumberFormatException e) {
                resp.setStatus(422);
                resp.getWriter().append("Il file ID <UNK> malformato");
                return;
            }

            String nome = req.getParameter("nome");

            prodotto = new Prodotto(id, nome, quantita, prezzo);
        }
        else {
            resp.setStatus(422);
            resp.getWriter().append("Occorre specificare un ssn e nome da inserire oppure un oggetto json.");
            return;
        }

        int res = dao.insertProdotto(prodotto);

        if (res > 0) {
            resp.setStatus(200);
            resp.getWriter().append("Prodotto inserito.");
        }
        else {
            resp.setStatus(400);
            resp.getWriter().append("Prodotto non inserito.");
        }
        return;
    }

    /*@Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        if (req.getParameter("id") != null) {
            try {
                id = Integer.valueOf(req.getParameter("id"));
            }
            catch (NumberFormatException e) {
                resp.setStatus(422);
                resp.getWriter().append("il campo ID <UNK> malformato");
                return;
            }

            int res = dao.editProdotto(id);

            if (res > 0) {
                response.setStatus(200);
                // response.getWriter().append("Tutto OK");
                return;
            } else {
                resp.setStatus(400);
                resp.getWriter().append("Prodotto non editato.");
                return;

            }
        }
    }*/

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            resp.setStatus(422);
            resp.getWriter().append("il campo ID <UNK> non presente");
            return;
        }
        int id;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.setStatus(422);
            resp.getWriter().append("il campo ID <UNK> malformato");
            return;
        }

        boolean res = dao.deleteProdotto(id);

        if (res) {
            resp.setStatus(204);
            resp.getWriter().append("Tutto OK");
        } else {
            resp.setStatus(500);
            resp.getWriter().append("Problemi :-(");
        }

    }
}