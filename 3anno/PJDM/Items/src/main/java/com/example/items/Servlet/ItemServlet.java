package com.example.items.Servlet;
import com.example.items.Entity.Items;
import com.example.items.Entity.ItemMap;

import org.json.JSONException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;



@WebServlet("/Items")
public class ItemServlet extends HttpServlet {
    private ItemMap items;

    public ItemServlet() {
        this.items = new ItemMap();
    }
    public void init() throws ServletException {
        this.items.clear();

        try{
            this.items.add(new Items(1, "Latte", "1 Litro"));
            this.items.add(new Items(2, "Pane", "1 Kilogrammo"));
            this.items.add(new Items(3, "Prosciutto", "1 Ettogrammo"));
            this.items.add(new Items(4, "Birra", "3 Lattine"));
        }
        catch (Exception e){
            e.printStackTrace();
            this.items.clear();
            throw new ServletException("Problems in inizializing the list of items. " + e.getMessage());
        }
    }

    public void destroy() {
        this.items.clear();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(200);

            response.getWriter().append(this.items.toJson());
            return;
        }

        int id;

        try {
            id = Integer.valueOf(request.getParameter("id"));
        }
        catch (Exception e) {
            response.setStatus(422);
            return;
        }
        if (!this.items.containsById(id)) {
            response.setStatus(404);
            return;
        }

        Items item = this.items.get(id);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);

        out.print(item.toJson());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Items item = null;

        // Verifica che esista il parametro corretto
        if (request.getParameter("json") == null) {
            response.setStatus(422);
            return;
        }

        // IMPORTANTE: Ricordarsi di convertire { e }
        // Esempio:
        // - key: json
        // -value: %7Bid:-1,desc:"uova",quant:"2"%7D

        String jsonString = request.getParameter("json");
        try {
            item = Items.fromJSON(jsonString);
        } catch (JSONException e) {
            response.setStatus(422);
            return;
        }

        // L'ID non deve essere inizializzato e verrà assegnato dalla lista in forma
        // autoincrementale
        if (item.getId() != -1) {
            response.setStatus(422);
            return;
        }

        // Prelevo l'ultimo ID inserito e lo incremento di 1
        item.setId(this.items.getLastID() + 1);

        // Aggiunta del nuovo itam
        try {
            this.items.add(item);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            return;
        }

        // Restituzione del nuovo item in formato JSON
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        out.print(item.toJson());
        return;
    }

    /**
     * Gestisce le richieste HTTP PUT per aggiornare un elemento esistente.
     * L'oggetto JSON aggiornato deve contenere un ID valido già esistente.
     *
     * Esempio:
     * PUT http://localhost:8080/NOME_APP/Items
     * Body (x-www-form-urlencoded):
     *   key: json
     *   value: %7B"id":2,"desc":"Pane","quant":"500g"%7D
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Items item = null;

        // Verifica che esista il parametro JSON
        if (request.getParameter("json") == null) {
            response.setStatus(422);
            return;
        }

        // IMPORTANTE: Ricordarsi di convertire { e }
        // Esempio:
        // - key: json
        // -value: %7Bid:-1,desc:"uova",quant:"2"%7D
        String jsonString = request.getParameter("json");
        try {
            item = Items.fromJSON(jsonString);
        } catch (JSONException e) {
            response.setStatus(422);
            return;
        }

        // Verifica che esista un elemento con l'id specificato
        // Se esiste, lo rimuove e lo inserisce di nuovo
        if (this.items.containsById(item.getId())) {
            this.items.remove(item.getId());
            try {
                this.items.add(item);
            } catch (Exception e) {
                response.setStatus(500);
            }
            response.setStatus(200);
            return;
        } else {
            response.setStatus(404);
            return;
        }
    }

    /**
     * Gestisce le richieste HTTP DELETE per rimuovere un elemento specifico.
     * Richiede un parametro "id" con l'identificativo dell'item da cancellare.
     *
     * Esempio:
     * DELETE http://localhost:8080/NOME_APP/Items?id=3
     */

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verifica che il parametro sia impostato
        if (request.getParameter("id") == null) {
            response.setStatus(422);
            return;
        }
        // Verifica che il parametro id sia un numero intero
        int id;
        try {
            id = Integer.valueOf(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.setStatus(422);
            return;
        }
        // Verifica che esista un elemento con l'ID speciicato e lo elimina
        if (this.items.containsById(id)) {
            this.items.remove(id);
            response.setStatus(204);
            return;
        } else {
            response.setStatus(404);
            return;
        }
    }
}