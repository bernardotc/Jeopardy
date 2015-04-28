/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import beans.Juego;
import beans.Jugador;
import beans.PerfilJuego;
import beans.Pista;
import beans.User;
import database.GameHandler;
import database.TemaCategoriaPistaHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bernardot
 */
@WebServlet(name = "Game", urlPatterns = {"/Game"})
public class Game extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String operation = (String) request.getParameter("do");
        TemaCategoriaPistaHandler createHandler = new TemaCategoriaPistaHandler();
        GameHandler gameHandler = new GameHandler();
        String url = "/gameProfile.jsp";

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        }
        
        if (operation.equals("prepareG")) {
            ArrayList temas = createHandler.getTemas();
            request.setAttribute("listaTemas", temas);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("saveProf")) {
            String nombreP = (String) request.getParameter("nombrePerfil");
            String[] seccion1 = request.getParameterValues("seccion-1");
            String[] seccion2 = request.getParameterValues("seccion-2");
            String[] seccion3 = request.getParameterValues("seccion-3");
            String[] seccion4 = request.getParameterValues("seccion-4");
            String[] seccion5 = request.getParameterValues("seccion-5");
            String[] seccion6 = request.getParameterValues("seccion-6");
            HttpSession session = request.getSession();
            User usuario = (User) session.getAttribute("user");
            if (usuario == null) {
                url = "/login.jsp";
            } else {
                boolean perfilCreado = gameHandler.newPerfilJuego(nombreP, usuario.getId());
                ArrayList perfiles = gameHandler.getPerfilJuegos(usuario);
                PerfilJuego pJuego = null;
                for (int x = 0; x < perfiles.size(); x++) {
                    PerfilJuego aux = (PerfilJuego) perfiles.get(x);
                    if (aux.getName().equals(nombreP)) {
                        pJuego = aux;
                        break;
                    }
                }
                for (int y = 0; y < 5; y++) {
                    gameHandler.insertPistaInPerfilJuego(seccion1[y], Integer.toString(pJuego.getId()), "1", y);
                }
                for (int y = 0; y < 5; y++) {
                    gameHandler.insertPistaInPerfilJuego(seccion2[y], Integer.toString(pJuego.getId()), "2", y);
                }
                for (int y = 0; y < 5; y++) {
                    gameHandler.insertPistaInPerfilJuego(seccion3[y], Integer.toString(pJuego.getId()), "3", y);
                }
                for (int y = 0; y < 5; y++) {
                    gameHandler.insertPistaInPerfilJuego(seccion4[y], Integer.toString(pJuego.getId()), "4", y);
                }
                for (int y = 0; y < 5; y++) {
                    gameHandler.insertPistaInPerfilJuego(seccion5[y], Integer.toString(pJuego.getId()), "5", y);
                }
                for (int y = 0; y < 5; y++) {
                    gameHandler.insertPistaInPerfilJuego(seccion6[y], Integer.toString(pJuego.getId()), "6", y);
                }

                url = "/menu.jsp";
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("showProf")) {
            HttpSession session = request.getSession();
            User usuario = (User) session.getAttribute("user");
            if (usuario == null) {
                url = "/login.jsp";
            } else {
                ArrayList perfiles = gameHandler.getPerfilJuegos(usuario);
                request.setAttribute("perfiles", perfiles);
                url = "/usersProfiles.jsp";
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("showGame")) {
            HttpSession session = request.getSession();
            User usuario = (User) session.getAttribute("user");
            if (usuario == null) {
                url = "/login.jsp";
            } else {
                ArrayList perfiles = gameHandler.getPerfilJuegos(usuario);
                request.setAttribute("perfiles", perfiles);
                url = "/games.jsp";
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("startGame")) {
            HttpSession session = request.getSession();
            User usuario = (User) session.getAttribute("user");
            if (usuario == null) {
                url = "/login.jsp";
            } else {
                String i = request.getParameter("id");
                int id = Integer.parseInt(i);

                PerfilJuego perfil = gameHandler.getPerfilJuegos(usuario, id);
                perfil.setSeccion1(gameHandler.getPistasPerfilJuegos(perfil.getId(), 1));
                perfil.setSeccion2(gameHandler.getPistasPerfilJuegos(perfil.getId(), 2));
                perfil.setSeccion3(gameHandler.getPistasPerfilJuegos(perfil.getId(), 3));
                perfil.setSeccion4(gameHandler.getPistasPerfilJuegos(perfil.getId(), 4));
                perfil.setSeccion5(gameHandler.getPistasPerfilJuegos(perfil.getId(), 5));
                perfil.setSeccion6(gameHandler.getPistasPerfilJuegos(perfil.getId(), 6));

                session.setAttribute("perfil", perfil);
                url = "/players.jsp";
            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("putPlayers")) {
            HttpSession session = request.getSession();
            String num = request.getParameter("number");
            int number = Integer.parseInt(num);
            ArrayList jugadores = new ArrayList();
            ArrayList jug = new ArrayList();

            for (int x = 1; x <= number; x++) {
                jugadores.add(request.getParameter("jugador" + x));
            }

            for (int y = 0; y < jugadores.size(); y++) {
                String nom = (String) jugadores.get(y);
                Jugador aux = gameHandler.getJugador(nom);
                if (aux.getNombre() != null) {
                    jug.add(aux);
                } else {
                    jug.add(gameHandler.insertJugador(nom));
                }
            }

            session.setAttribute("jugadores", jug);
            url = "/jeopardyGame.jsp";

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("changeProf")) {
            HttpSession session = request.getSession();
            User usuario = (User) session.getAttribute("user");
            if (usuario == null) {
                url = "login.jsp";
            } else {
                String i = request.getParameter("id");
                int id = Integer.parseInt(i);

                PerfilJuego perfil = gameHandler.getPerfilJuegos(usuario, id);
                perfil.setSeccion1(gameHandler.getPistasPerfilJuegos(perfil.getId(), 1));
                perfil.setSeccion2(gameHandler.getPistasPerfilJuegos(perfil.getId(), 2));
                perfil.setSeccion3(gameHandler.getPistasPerfilJuegos(perfil.getId(), 3));
                perfil.setSeccion4(gameHandler.getPistasPerfilJuegos(perfil.getId(), 4));
                perfil.setSeccion5(gameHandler.getPistasPerfilJuegos(perfil.getId(), 5));
                perfil.setSeccion6(gameHandler.getPistasPerfilJuegos(perfil.getId(), 6));

                ArrayList pistas1 = perfil.getSeccion1();
                ArrayList pistas2 = perfil.getSeccion2();
                ArrayList pistas3 = perfil.getSeccion3();
                ArrayList pistas4 = perfil.getSeccion4();
                ArrayList pistas5 = perfil.getSeccion5();
                ArrayList pistas6 = perfil.getSeccion6();
                if (pistas1.size() > 0) {
                    Pista pista = (Pista) pistas1.get(0);
                    pistas1 = createHandler.getPistasC(pista.getCategoria().getId());
                }
                if (pistas2.size() > 0) {
                    Pista pista = (Pista) pistas2.get(0);
                    pistas2 = createHandler.getPistasC(pista.getCategoria().getId());
                }
                if (pistas3.size() > 0) {
                    Pista pista = (Pista) pistas3.get(0);
                    pistas3 = createHandler.getPistasC(pista.getCategoria().getId());
                }
                if (pistas4.size() > 0) {
                    Pista pista = (Pista) pistas4.get(0);
                    pistas4 = createHandler.getPistasC(pista.getCategoria().getId());
                }
                if (pistas5.size() > 0) {
                    Pista pista = (Pista) pistas5.get(0);
                    pistas5 = createHandler.getPistasC(pista.getCategoria().getId());
                }
                if (pistas6.size() > 0) {
                    Pista pista = (Pista) pistas6.get(0);
                    pistas6 = createHandler.getPistasC(pista.getCategoria().getId());
                }

                request.setAttribute("perfil", perfil);
                request.setAttribute("pistas1", pistas1);
                request.setAttribute("pistas2", pistas2);
                request.setAttribute("pistas3", pistas3);
                request.setAttribute("pistas4", pistas4);
                request.setAttribute("pistas5", pistas5);
                request.setAttribute("pistas6", pistas6);
                ArrayList temas = createHandler.getTemas();
                request.setAttribute("listaTemas", temas);
                ArrayList categorias = createHandler.getCategorias();
                request.setAttribute("listaCategorias", categorias);
                url = "/modifyGameProfile.jsp";
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("modifyProf")) {
            String idP = (String) request.getParameter("idPerfil");
            int idPerfil = Integer.parseInt(idP);
            String nombreP = (String) request.getParameter("nombrePerfil");
            String[] seccion1 = request.getParameterValues("seccion-1");
            String[] seccion2 = request.getParameterValues("seccion-2");
            String[] seccion3 = request.getParameterValues("seccion-3");
            String[] seccion4 = request.getParameterValues("seccion-4");
            String[] seccion5 = request.getParameterValues("seccion-5");
            String[] seccion6 = request.getParameterValues("seccion-6");
            HttpSession session = request.getSession();
            User usuario = (User) session.getAttribute("user");
            if (usuario == null) {
                url = "/login.jsp";
            } else {
                boolean perfilModificado = gameHandler.updatePerfilJuego(idPerfil, nombreP);
                PerfilJuego pJuego = gameHandler.getPerfilJuegos(usuario, idPerfil);

                pJuego.setSeccion1(gameHandler.getPistasPerfilJuegos(pJuego.getId(), 1));
                pJuego.setSeccion2(gameHandler.getPistasPerfilJuegos(pJuego.getId(), 2));
                pJuego.setSeccion3(gameHandler.getPistasPerfilJuegos(pJuego.getId(), 3));
                pJuego.setSeccion4(gameHandler.getPistasPerfilJuegos(pJuego.getId(), 4));
                pJuego.setSeccion5(gameHandler.getPistasPerfilJuegos(pJuego.getId(), 5));
                pJuego.setSeccion6(gameHandler.getPistasPerfilJuegos(pJuego.getId(), 6));

                // Modificar las pistas.
                ArrayList pistas = (ArrayList) pJuego.getSeccion1();
                for (int y = 0; y < pistas.size(); y++) {
                    gameHandler.updatePistaPerfilJuego(Integer.parseInt(seccion1[y]), pJuego.getId(), 1, y);
                }
                for (int y = pistas.size(); y < 5; y++) {
                    gameHandler.insertPistaInPerfilJuego(seccion1[y], Integer.toString(pJuego.getId()), "1", y);
                }

                pistas = (ArrayList) pJuego.getSeccion2();
                for (int y = 0; y < pistas.size(); y++) {
                    gameHandler.updatePistaPerfilJuego(Integer.parseInt(seccion2[y]), pJuego.getId(), 2, y);
                }
                for (int y = pistas.size(); y < 5; y++) {
                    gameHandler.insertPistaInPerfilJuego(seccion2[y], Integer.toString(pJuego.getId()), "2", y);
                }

                pistas = (ArrayList) pJuego.getSeccion3();
                for (int y = 0; y < pistas.size(); y++) {
                    gameHandler.updatePistaPerfilJuego(Integer.parseInt(seccion3[y]), pJuego.getId(), 3, y);
                }
                for (int y = pistas.size(); y < 5; y++) {
                    gameHandler.insertPistaInPerfilJuego(seccion3[y], Integer.toString(pJuego.getId()), "3", y);
                }

                pistas = (ArrayList) pJuego.getSeccion4();
                for (int y = 0; y < pistas.size(); y++) {
                    gameHandler.updatePistaPerfilJuego(Integer.parseInt(seccion4[y]), pJuego.getId(), 4, y);
                }
                for (int y = pistas.size(); y < 5; y++) {
                    gameHandler.insertPistaInPerfilJuego(seccion4[y], Integer.toString(pJuego.getId()), "4", y);
                }

                pistas = (ArrayList) pJuego.getSeccion5();
                for (int y = 0; y < pistas.size(); y++) {
                    gameHandler.updatePistaPerfilJuego(Integer.parseInt(seccion5[y]), pJuego.getId(), 5, y);
                }
                for (int y = pistas.size(); y < 5; y++) {
                    gameHandler.insertPistaInPerfilJuego(seccion5[y], Integer.toString(pJuego.getId()), "5", y);
                }

                pistas = (ArrayList) pJuego.getSeccion6();
                for (int y = 0; y < pistas.size(); y++) {
                    gameHandler.updatePistaPerfilJuego(Integer.parseInt(seccion6[y]), pJuego.getId(), 6, y);
                }
                for (int y = pistas.size(); y < 5; y++) {
                    gameHandler.insertPistaInPerfilJuego(seccion6[y], Integer.toString(pJuego.getId()), "6", y);
                }

                url = "/control?do=showProf";
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("saveGame")) {
            HttpSession session = request.getSession();
            PerfilJuego perfil = (PerfilJuego) session.getAttribute("perfil");
            if (perfil == null) {
                url = "/login.jsp";
            } else {
                Juego juego = gameHandler.insertJuego(perfil.getId());

                for (int x = 1; x <= 30; x++) {
                    String xa = Integer.toString(x);
                    String valor = request.getParameter(xa);
                    String[] aux = valor.split("-");
                    String jugadorid = aux[0];
                    int jugid = Integer.parseInt(jugadorid);
                    String pistaid = aux[1];
                    int pid = Integer.parseInt(pistaid);
                    gameHandler.insertResultado(juego.getId(), jugid, pid);
                }

                ArrayList resultados = gameHandler.getResultados(juego.getId());

                request.setAttribute("resultados", resultados);

                url = "/gameResults.jsp";
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("finalList")) {
            HttpSession session = request.getSession();
            User usuario = (User) session.getAttribute("user");
            if (usuario == null) {
                url = "/login.jsp";
            } else {

                ArrayList resultados = gameHandler.getConcentradoFinal(usuario.getId());

                request.setAttribute("resultados", resultados);

                url = "/concentrado.jsp";
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
