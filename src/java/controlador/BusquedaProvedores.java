/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.Provedor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Labing
 */
public class BusquedaProvedores extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String id = request.getParameter("id");
            ProvedorDAO dao=new ProvedorDAO();
            Provedor pro = null;
            pro = dao.Buscar(Integer.parseInt(id));
            
            RequestDispatcher dispacher =request.getRequestDispatcher("BusquedaProvedor.jsp");
            
            request.setAttribute("provedor", pro);
            dispacher.forward(request, response);
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
//package Controlador;
//
//import DAO.DaoElementos;
//import Modelo.Elemento;
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class BuscarElemento extends HttpServlet {
//
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        //Buscar Por nombre
//        String parametro = request.getParameter("Buscar");
//
//        Elemento elm = new Elemento();
//        //1. Crear instancia del DAO        
//        DaoElementos daoE = new DaoElementos();
//        //Lista todos los elementos.
//        elm = daoE.buscarPorNombre(parametro);
//        //2. Envio de los datos por el request.
//        request.setAttribute("etiqueta", elm);
//        //3. RequestDispacher
//        RequestDispatcher rd = request.getRequestDispatcher("BuscarElemento.jsp");
//        rd.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        //Buscar por etiqueta
//        String parametro = request.getParameter("Buscar");
//
//        Elemento elm = new Elemento();
//
//        DaoElementos daoE = new DaoElementos();
//
//        elm = daoE.buscar(Integer.parseInt(parametro));
//
//        request.setAttribute("etiqueta", elm);
//        //3. RequestDispacher
//        RequestDispatcher rd = request.getRequestDispatcher("BuscarElemento.jsp");
//        rd.forward(request, response);
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
}
