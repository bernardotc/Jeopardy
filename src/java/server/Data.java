/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import beans.Categoria;
import beans.Tema;
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

/**
 *
 * @author bernardot
 */
@WebServlet(name = "Data", urlPatterns = {"/Data"})
public class Data extends HttpServlet {

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
        String operation = (String) request.getParameter("do");
        TemaCategoriaPistaHandler createHandler = new TemaCategoriaPistaHandler();
        String url = "/crearTemaCategoriaPista.jsp";

        if (operation.equals("create")) {
            ArrayList temas = createHandler.getTemas();
            ArrayList categorias = createHandler.getCategorias();
            request.setAttribute("listaTemas", temas);
            request.setAttribute("listaCategorias", categorias);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("insertTema")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String tema = (String) request.getParameter("tema");
            boolean creado = createHandler.newTema(tema);
            if (!creado) {
                out.print("error");
            } else {
                ArrayList temas = createHandler.getTemas();
                for (int i = 0; i < temas.size(); i++) {
                    Tema aux = (Tema) temas.get(i);
                    out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                }
            }
        } else if (operation.equals("insertCategoria")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String categoria = (String) request.getParameter("categoria");
            String descripcion = (String) request.getParameter("descripcion");
            String temaid = (String) request.getParameter("temaid");
            int id = Integer.parseInt(temaid);
            boolean creado = createHandler.newCategoria(categoria, descripcion, id);
            if (!creado) {
                out.print("error");
            } else {
                ArrayList categorias = createHandler.getCategorias();
                for (int i = 0; i < categorias.size(); i++) {
                    Categoria aux = (Categoria) categorias.get(i);
                    out.print("<option value=\"" + aux.getId() + "\">" + aux.getNombre() + "</option>");
                }
            }
        } else if (operation.equals("insertPista")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String redaccion = (String) request.getParameter("redaccion");
            String respuesta = (String) request.getParameter("respuesta");
            String punt = (String) request.getParameter("puntos");
            int puntos = Integer.parseInt(punt);
            String id = (String) request.getParameter("categoriaid");
            int categoriaid = Integer.parseInt(id);
            boolean creado = createHandler.newPista(redaccion, respuesta, puntos, categoriaid);
            if (!creado) {
                out.print("error");
            } else {
                out.print("ok");
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
