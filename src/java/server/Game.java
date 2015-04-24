/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import beans.PerfilJuego;
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
                gameHandler.insertPistaInPerfilJuego(seccion1[y], Integer.toString(pJuego.getId()), "1");
            }
            for (int y = 0; y < 5; y++) {
                gameHandler.insertPistaInPerfilJuego(seccion2[y], Integer.toString(pJuego.getId()), "2");
            }
            for (int y = 0; y < 5; y++) {
                gameHandler.insertPistaInPerfilJuego(seccion3[y], Integer.toString(pJuego.getId()), "3");
            }
            for (int y = 0; y < 5; y++) {
                gameHandler.insertPistaInPerfilJuego(seccion4[y], Integer.toString(pJuego.getId()), "4");
            }
            for (int y = 0; y < 5; y++) {
                gameHandler.insertPistaInPerfilJuego(seccion5[y], Integer.toString(pJuego.getId()), "5");
            }
            for (int y = 0; y < 5; y++) {
                gameHandler.insertPistaInPerfilJuego(seccion6[y], Integer.toString(pJuego.getId()), "6");
            }
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
