/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import beans.User;
import database.UserHandler;
import java.io.IOException;
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
@WebServlet(name = "Login_Signin", urlPatterns = {"/Login_Signin"})
public class Login_Signin extends HttpServlet {

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
        User usuario = null;
        String url = "/footer.html";
        UserHandler uHandler = new UserHandler();

        if (operation.equals("signin")) {
            String pass = Long.toHexString(Double.doubleToLongBits(Math.random()));
            String user = (String) request.getParameter("newUser");
            String nom = (String) request.getParameter("newFName");
            String ape = (String) request.getParameter("newLName");
            String correo = (String) request.getParameter("newMail");

            usuario = new User(nom, ape, user, correo, pass);
            uHandler.newUser(usuario);

            url = "/emailConfirmation.jsp";
        } else {
            String user = (String) request.getParameter("userid");
            String password = (String) request.getParameter("userpswd");

            usuario = uHandler.getUser(user, password);

            if (usuario != null) {
                url = "/confirmation.jsp"; // Cambiar despues por el correcto
            }
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
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
