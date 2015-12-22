/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sector.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jorodriguez
 */
@WebServlet(name = "AbrirImage", urlPatterns = {"/AbrirImage"})
public class AbrirImage extends HttpServlet {

//    @EJB
//    private SiArchivoFacadeLocal archivoService;

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
        
        String url = request.getParameter("URL");
        
        String type = request.getParameter("TYPE");
        System.out.println("En el servlet  " + url);
        System.out.println("En el servlet  " + type);
        
        if (!url.equals("")) {
            InputStream inFile = null;

//            if (archivo != null) {
            inFile = Files.newInputStream(Paths.get(url));
                byte[] img = new byte[inFile.available()];
                inFile.read(img);
                if (img != null) {
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    try {
                        output.write(img, 0, img.length);
                        response.setContentType(type);
                        response.setHeader("content-disposition", "inline;");
                        response.setContentLength(output.size());
                        OutputStream out = response.getOutputStream();
                        output.writeTo(out);
                        out.flush();
                        out.close();
                    } finally {
                        output.close();
                    }
                }
//            }
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
