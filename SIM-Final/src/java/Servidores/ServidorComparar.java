/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidores;

import Gestores.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jorge
 */
@WebServlet(name = "Servidor", urlPatterns = {"/Servidor"})
public class ServidorComparar extends HttpServlet {

    private int numActual, numAlternativa, tiempoSimulacion;
    private double promActual, promAlternativa;

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
        HttpSession session = request.getSession();
        if ((Boolean) session.getAttribute("primeraVez") == true) {
            tiempoSimulacion = Integer.parseInt(request.getParameter("tiempo"));
            session.setAttribute("primeraVez", false);
            session.setAttribute("tiempoSimulacion", tiempoSimulacion);
        } else {
            tiempoSimulacion = (Integer) session.getAttribute("tiempoSimulacion");
        }
        if (request.getParameter("politicaActual") != null) {
            GestorPoliticaActual gpa = new GestorPoliticaActual(tiempoSimulacion * 60);
            gpa.simular();
            request.setAttribute("llenados", gpa.getLlenado());
            request.setAttribute("clientesMax", gpa.getClientesTotal());
            request.setAttribute("vectorMostrar", gpa.getVectorMostrar());
            request.setAttribute("promedio", gpa.getPromedio());
            request.setAttribute("clientesAtendidos", gpa.getCantidadClientesAtendidos());
            numActual = (Integer) session.getAttribute("numActual");
            numActual++;
            session.setAttribute("numActual", numActual);
            promActual = (Double) session.getAttribute("promActual");
            promActual+=gpa.getPromedio();
            session.setAttribute("promActual", promActual);
            request.getRequestDispatcher("PoliticaActual.jsp").forward(request, response);
        } else if (request.getParameter("politicaAlternativa") != null) {
            GestorPoliticaAlternativa gpal= new GestorPoliticaAlternativa(tiempoSimulacion*60);
            gpal.simular();
            request.setAttribute("clientesMax", gpal.getClientesTotal());
            request.setAttribute("vectorMostrar", gpal.getVectorMostrar());
            request.setAttribute("promedio", gpal.getPromedio());
            request.setAttribute("clientesAtendidos", gpal.getCantidadClientesAtendidos());
            numAlternativa = (Integer) session.getAttribute("numAlternativa");
            numAlternativa++;
            session.setAttribute("numAlternativa", numAlternativa);
            promAlternativa = (Double) session.getAttribute("promAlternativa");
            promAlternativa+=gpal.getPromedio();
            session.setAttribute("promAlternativa", promAlternativa);
            request.getRequestDispatcher("PoliticaAlternativa.jsp").forward(request, response);
        } else if (request.getParameter("comparar") != null) {
            request.getRequestDispatcher("Comparar.jsp").forward(request, response);
        }else if (request.getParameter("reiniciar") != null) {
            request.getRequestDispatcher("/Inicio").forward(request, response);
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
