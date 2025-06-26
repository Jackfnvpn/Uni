package com.example.send_go.Servlet;

import com.example.send_go.Dao.Corriere.SendGoDaoCorriere;
import com.example.send_go.Dao.Corriere.SendGoDaoCorriereImpl;
import com.example.send_go.Entity.Corriere.Corriere;
import com.example.send_go.Entity.Corriere.ListCorriere;
import com.example.send_go.Entity.Pacco.Pacco;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (dao == null || !dao.isConnected()) {
            resp.setStatus(500);
            return;
        }

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        ListCorriere list;
        list=dao.getCorrieres();

        if (list == null) {
            resp.setStatus(404);
            return;
        }


        if (list.isEmpty()) {
            resp.setStatus(404);
            return;
        }

        resp.setStatus(200);

        out.write(list.toJSONString());
        out.flush();
        out.close();
        return;


    }

    @Override
    public void destroy() {
        System.out.print("MusicianServlet. Closing DB connection...");
        dao.closeConnection();
        System.out.println("DONE.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (dao == null || !dao.isConnected()) {
            resp.setStatus(500);
            return;
        }

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        ArrayList<String> provinceIsolate = new ArrayList<>(Arrays.asList(
                // Sicilia
                "AGRIGENTO", "CALTANISSETTA", "CATANIA", "ENNA", "MESSINA",
                "PALERMO", "RAGUSA", "SIRACUSA", "TRAPANI",

                // Sardegna
                "CAGLIARI", "SASSARI", "NUORO", "ORISTANO", "SUD SARDEGNA",

                // Calabria
                "COSENZA", "CROTONE", "REGGIO CALABRIA", "CATANZARO", "VIBO VALENTIA"
        ));

        String provinciaMittenza = req.getParameter("provinciaMittenza");
        String provinciaDestinazione = req.getParameter("provinciaDest");
        String json = req.getParameter("json");
        String nomeCorr = req.getParameter("nome");

        System.out.println(json);
        System.out.println(provinciaDestinazione);
        System.out.println(provinciaMittenza);


        if (json!=null && provinciaMittenza !=null && provinciaDestinazione !=null && nomeCorr == null){

            double surplus;
            Pacco pacco;

            JSONObject obj = new JSONObject(json);

            try {
                pacco = Pacco.fromJSON(obj.getJSONObject("pacco"));
            }catch (JSONException e){
                resp.setStatus(400);
                out.write(e.getMessage());
                out.flush();
                out.close();
                return;
            }

            double pesoTassabile = pacco.getPesoTassabile();

            System.out.println(pesoTassabile);

            if (pesoTassabile>70){
                resp.setStatus(400);
                out.write("Peso o dimensioni eccessive");
                out.flush();
                out.close();
                return;
            }

            ListCorriere listCorriere = dao.getCorrieres(pesoTassabile);

            if (listCorriere == null || listCorriere.isEmpty()) {
                resp.setStatus(404);
                return;
            }

            if (provinceIsolate.contains(provinciaMittenza.toUpperCase()) ||
                    provinceIsolate.contains(provinciaDestinazione.toUpperCase())) {
                for (Corriere corriere : listCorriere) {
                    surplus = corriere.getCorriere_tariffa()*(1.10);
                    corriere.setTariffa(surplus);
                }
            }

            resp.setStatus(200);
            out.print(listCorriere.toJSONString());
            out.flush();
            out.close();
        } else if (nomeCorr!=null && json==null && provinciaMittenza ==null && provinciaDestinazione ==null) {
            int res = dao.insertCorriere(nomeCorr);

            if (res > 0) {
                resp.setStatus(200);
            }
            else {
                resp.setStatus(409);
            }
            out.close();
            return;


        } else {
            resp.setStatus(400);
            out.write("Mancano dei campi");
            out.flush();
            out.close();
        } return;


    }




}
