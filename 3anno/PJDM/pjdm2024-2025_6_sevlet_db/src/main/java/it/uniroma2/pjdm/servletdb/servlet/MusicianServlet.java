package it.uniroma2.pjdm.servletdb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;

import it.uniroma2.pjdm.servletdb.dao.MusicistiDAO;
import it.uniroma2.pjdm.servletdb.dao.MusicistiDAOJDBCImpl;
import it.uniroma2.pjdm.servletdb.entity.ListaMusicisti;
import it.uniroma2.pjdm.servletdb.entity.Musicista;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Musician
 */
//@WebServlet("/MusicianServlet")
public class MusicianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MusicistiDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MusicianServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		String ip = getInitParameter("ip");
		String port = getInitParameter("port");
		String dbName = getInitParameter("dbName");
		String userName = getInitParameter("userName");
		String password = getInitParameter("password");

		System.out.print("MusicianServlet. Opening DB connection...");

		dao = new MusicistiDAOJDBCImpl(ip, port, dbName, userName, password);

		System.out.println("DONE.");
		
		//MA SE LA CONNESSIONE NON E' APERTA, CHE FACCIAMO???
	}

	@Override
	public void destroy() {
		System.out.print("MusicianServlet. Closing DB connection...");
		dao.closeConnection();
		System.out.println("DONE.");
	}

	/**
	 * 
	 * This method requires the integer <code>ssn</code> used in the request to
	 * specify the serial number of the Musician to be retrieved by the DB. When
	 * <code>ssn</code> is not provided, the entire list of Musicians is retrieved.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MusicianServlet. Invoking a doGet method.");

		PrintWriter out = response.getWriter();

		if (request.getParameter("ssn") == null) {
			ListaMusicisti allMusicians = dao.loadTuttiMusicisti();
			// MANCA GESTIONE DELL'ECCEZIONE DI CONNESSIONE AL DB. 
			// ESERCIZIO: gestite opportunamente gli errori al DB, magari restituendo un codice
			// 500 o un codice della famigli 5XX da voi definito
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(allMusicians.toJSONString());
			out.flush();
			response.setStatus(200);
			return;
		}

		// che succede se ssn è impostato a "pappagallo"?
		int ssn = -1;

		try {
			ssn = Integer.valueOf(request.getParameter("ssn"));
		} catch (NumberFormatException e) {
			response.setStatus(492); // LET US DEFINE A SPECIFIC ERROR CODE
			// e.printStackTrace(); IT CAN BE USEFUL TO KEEP THE STACK TRACE
			return;
		}

		if (ssn < 0) {
			response.setStatus(491); // LET US DEFINE A SPECIFIC ERROR CODE
			return;
		}

		Musicista musicista = dao.loadMusicistaByID(ssn);
		if (musicista == null) {
			response.setStatus(404);
			// response.getWriter().append("Non esiste un musicista con ID " + ssn);
			return;
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(musicista.toJSONString());
		out.flush();
		return;

	}

	/**
	 * Il metodo POST permette di inserire un nuovo musicista, inviando un nuovo
	 * musicista specificando name e ssn, oppure direttamente in un campo json
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("MusicianServlet. Invoking a doPost method...");

		response.setContentType("text/plain");

		Musicista newMusicista = null;

		// IMPORTANTE: per poter usare un file JSON in INPUT, alcuni caratteri, come { e
		// }
		// dovrebbero essere oggetto di conversione per compatibilità per protocollo
		// HTTP1.1
		// Esempio: /MusicianServlet?json=%7Bssn:10,name:"DANILO CROCE"%7D al posto di
		// /MusicianServlet?json={ssn:10,name:"DANILO CROCE"}
		if (request.getParameter("json") != null) {
			String jsonString = request.getParameter("json");
			try {
				newMusicista = Musicista.fromJSON(jsonString);
			} catch (JSONException e) {
				response.setStatus(422); // 422 (Unprocessable Entity) ???
				// response.getWriter().append("Il file JSON è malformato");
				return;
			}
		} else if (request.getParameter("ssn") != null && request.getParameter("name") != null) {
			// che succede se arriva il parametero "Pappagallo"?
			int ssn = -1;

			try {
				ssn = Integer.valueOf(request.getParameter("ssn"));
			} catch (NumberFormatException e) {
				response.setStatus(422); // 422 (Unprocessable Entity) ???
				// response.getWriter().append("Il parametro SSN dovrebbe essere un intero");
				return;
			}
			String name = request.getParameter("name");
			newMusicista = new Musicista(name, ssn);
		} else {
			response.setStatus(422);
			// response.getWriter().append("Occorre specificare un ssn e nome da inserire
			// oppure un oggetto json.");
			return;
		}

		int res = dao.insertMusicista(newMusicista);

		if (res > 0) {
			response.setStatus(200);
			// response.getWriter().append("Tutto OK");
			return;
		} else {
			response.setStatus(409);
			// The 409 (Conflict) status code indicates that the request could not be
			// completed due to a conflict with the current state of the target resource.
			// response.getWriter().append("Problemi :-(");
			return;
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MusicianServlet. Invoking a doPut method...");
		response.setStatus(405);
		response.getWriter().append("L'implementazione del metodo PUT e' implementato come esercizio.");
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MusicianServlet. Invoking a doDelete method.");
		// Non è necessario usare anche il nome, è un check di consistenza
		if (request.getParameter("ssn") == null) {
			response.setStatus(422);
			return;
		}

		int ssn = -1;

		try {
			ssn = Integer.valueOf(request.getParameter("ssn"));
		} catch (NumberFormatException e) {
			response.setStatus(422);
			return;
		}

		Musicista oldMusicista = dao.loadMusicistaByID(ssn);
		if (oldMusicista == null) {
			response.setStatus(404);
			return;
		}

		boolean isOk = dao.deleteMusicista(ssn);

		if (isOk) {
			response.setStatus(204);
			// response.getWriter().append("Tutto OK"); // E' poco utile
		} else {
			response.setStatus(500);
			// response.getWriter().append("Problemi :-("); // E' poco utile
		}
	}

}
