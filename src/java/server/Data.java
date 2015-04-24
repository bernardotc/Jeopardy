/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import beans.Categoria;
import beans.Pista;
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
        request.setCharacterEncoding("UTF-8");
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
        } else if (operation.equals("showT")) {
            ArrayList temas = createHandler.getTemas();
            request.setAttribute("listaTemas", temas);
            url = "/tema.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("showC")) {
            ArrayList categorias = createHandler.getCategorias();
            request.setAttribute("listaCategorias", categorias);
            ArrayList temas = createHandler.getTemas();
            request.setAttribute("listaTemas", temas);
            url = "/categoria.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("showP")) {
            ArrayList temas = createHandler.getTemas();
            request.setAttribute("listaTemas", temas);
            url = "/pista.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else if (operation.equals("change")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String i = (String) request.getParameter("id");
            int id = Integer.parseInt(i);
            String tipo = (String) request.getParameter("tipo");
            String valor = (String) request.getParameter("valor");
            String db = (String) request.getParameter("db");
            boolean creado = createHandler.modify(id, tipo, valor, db);
            if (!creado) {
                out.print("error");
            } else {
                out.print("ok");
            }
        } else if (operation.equals("getT")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            ArrayList temas = createHandler.getTemas();
            for (int i = 0; i < temas.size(); i++) {
                Tema aux = (Tema) temas.get(i);
                out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
            }
        } else if (operation.equals("getP")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String t = request.getParameter("temaid");
            int temaid = Integer.parseInt(t);
            ArrayList pistas = createHandler.getPistas(temaid);
            for (int i = 0; i < pistas.size(); i++) {
                Pista aux = (Pista) pistas.get(i);
                out.print("\t\t\t\t<tr>\n");
                out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\" class=\"special\" ondblclick=\"modificar(this)\">" + aux.getRedaccion() + "</a></td>\n");
                out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\" class=\"special\" ondblclick=\"modificar(this)\">" + aux.getRespuesta() + "</a></td>\n");
                out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\" class=\"special\" ondblclick=\"modificar(this)\">" + aux.getPuntos() + "</a></td>\n");
                out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\" class=\"special\" ondblclick=\"modificarCategoria(this)\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                out.print("\t\t\t\t\t<td class=\"button\"><input id=\"Pista-" + aux.getId() + "\" class=\"specialButton\" type =\"button\" value=\"Borrar fila\"</td>\n");
                out.print("\t\t\t\t</tr>\n");
            }
        } else if (operation.equals("getC")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            ArrayList categorias = createHandler.getCategorias();
            for (int i = 0; i < categorias.size(); i++) {
                Categoria aux = (Categoria) categorias.get(i);
                out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
            }
        } else if (operation.equals("erase")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String i = (String) request.getParameter("id");
            int id = Integer.parseInt(i);
            String db = (String) request.getParameter("db");
            boolean borrado = createHandler.delete(id, db);
            if (!borrado) {
                out.print("error");
            } else {
                out.print("ok");
            }
        } else if (operation.equals("getPAg")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String t = request.getParameter("categoriaid");
            String id = request.getParameter("id");
            int categoriaid = Integer.parseInt(t);
            ArrayList pistas = createHandler.getPistasC(categoriaid);
            for (int i = 0; i < pistas.size(); i++) {
                Pista aux = (Pista) pistas.get(i);
                out.print("\t\t\t\t<tr>\n");
                out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                out.print("\t\t\t\t\t<td><input name=\"seccion-"+ id + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + id + "(this)\"></td>\n");
                out.print("\t\t\t\t</tr>\n");
            }
        } else if (operation.equals("getCAg")) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String t = request.getParameter("temaid");
            int temaid = Integer.parseInt(t);
            ArrayList categorias = createHandler.getCategorias(temaid);
            for (int i = 0; i < categorias.size(); i++) {
                Categoria aux = (Categoria) categorias.get(i);
                out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
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
