/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.forCruiter;

import DAO.ApplicationDAO;
import Model.Application;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "ListApplicationForRcruiter", urlPatterns = {"/jobApplicationRecruiter"})
public class ListApplicationForRcruiter extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListApplicationForRcruiter</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListApplicationForRcruiter at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String jobID = request.getParameter("jobId");
        ApplicationDAO aDAO = new ApplicationDAO();
        String status = request.getParameter("status");
        try {
            int jobId = Integer.parseInt(jobID);
            System.out.println(jobID);
            ArrayList<Application> listAll = aDAO.getByJob(jobId);
            ArrayList<Application> list = new ArrayList<>();
            if (status == null || status.equalsIgnoreCase("All")) {
                list = listAll;
            } else {
                for (Application a : listAll) {
                    if (a.getStatus().equalsIgnoreCase(status)) {
                        list.add(a);
                    }
                }
            }
            System.out.println(list.size());
            request.setAttribute("listApplication", list);
            request.setAttribute("jobId", jobId);
            request.getRequestDispatcher("ApplicationForJobRecruiter.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
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
