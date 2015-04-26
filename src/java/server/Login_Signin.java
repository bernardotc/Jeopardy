/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import beans.User;
import database.UserHandler;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
        request.setCharacterEncoding("UTF-8");
        String operation = (String) request.getParameter("do");
        User usuario = null;
        String url = "/login.jsp";
        int intentosLogin = 0;
        UserHandler uHandler = new UserHandler();

        if (operation.equals("signin")) {
            String pass = Long.toHexString(Double.doubleToLongBits(Math.random()));
            String user = (String) request.getParameter("newUser");
            String nom = (String) request.getParameter("newFName");
            String ape = (String) request.getParameter("newLName");
            String correo = (String) request.getParameter("newMail");
            pass += "$#";
              
            uHandler.newUser(nom, user, ape, correo, pass);
            
            usuario = uHandler.getUser(user, pass);
            
            HttpSession session = request.getSession();
            session.setAttribute("user", usuario);
            
            sendMail(nom, ape, correo, user, pass, usuario.getId());

            url = "/emailConfirmation.jsp";
        } else if (operation.equals("confirm")) {
            String i = (String) request.getParameter("id");
            int id = Integer.parseInt(i);
            usuario = uHandler.getUser(id);
            
            HttpSession session = request.getSession();
            session.setAttribute("user", usuario);
            
            if (usuario != null) {
                url = "/login.jsp";
            }

            url = "/confirmation.jsp";
        } else if (operation.equals("changePass")) {
            String user = (String) request.getParameter("user");
            String lastP = (String) request.getParameter("lPaswd");
            String nPass = (String) request.getParameter("nPaswd");
            String cNPass = (String) request.getParameter("cNPaswd");
            
            usuario = uHandler.getUser(user, lastP);
            
            boolean cambiarPass = uHandler.updatePassword(usuario.getId(), nPass);
            if(!cambiarPass) {
                request.setAttribute("errorChangePass", "Error en el sistema al intentar cambiar contraseñas.");
                url = "/confirmation.jsp";
            } else {
                usuario = uHandler.getUser(user, nPass);
                HttpSession session = request.getSession();
                session.setAttribute("user", usuario);
                url = "/menu.jsp";
            }
        } else if (operation.equals("sendMail")) {
            HttpSession session = request.getSession();
            usuario = (User) session.getAttribute("user");
            
            sendMail(usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getUsuario(), usuario.getPassword(), usuario.getId());
        } else {
            String user = (String) request.getParameter("userid");
            String password = (String) request.getParameter("userpswd");

            usuario = uHandler.getUser(user, password);

            HttpSession session = request.getSession();
            session.setAttribute("user", usuario);

            if (password.endsWith("$#")) {
                url = "/emailConfirmation.jsp";
            } else {

                if (session.getAttribute("contador") != null) {
                    intentosLogin = (int) session.getAttribute("contador");
                }
                if (usuario != null) {
                    url = "/menu.jsp";
                    intentosLogin = 0;
                    session.setAttribute("contador", intentosLogin);
                } else {
                    intentosLogin++;
                    request.setAttribute("errorLogin", "Usuario o contraseña equivocada. Tienes " + (3 - intentosLogin)
                            + " intentos. Sino se bloqueará la cuenta por 1 minuto.");
                    session.setAttribute("contador", intentosLogin);
                    if (intentosLogin == 3) {
                        intentosLogin = 0;
                    }
                }
            }
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    public void sendMail(String nom, String ape, String correo, String user, String pass, int id) {
        final String username = "etherniajeopardy";
        final String password = "etherniaJeo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("etherniajeopardy@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(correo));
            message.setSubject("Te has registrado al Sistema de Jeopardy");
            message.setText("Dear " + nom + " " + ape + ","
                + "\n\n Este mensaje es para decirle que ya está registrado en el sistema." +
                    "\n Para acceder a el tendrá que dar click al link para cambiar su contraseña usando su contraseña temporal dada a continuación:" +
                    "\n\n Usuario: " + user + "\n Contraseña: " + pass +
                    "\n\n Link: http://127.0.0.1:8080/Jeopardy/control?do=confirm&id=" + id +
                    "\n\n Gracias por su atención." + "\n Ethernia Company.");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
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
