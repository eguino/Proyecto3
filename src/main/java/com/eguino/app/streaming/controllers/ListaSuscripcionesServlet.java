package com.eguino.app.streaming.controllers;

import com.eguino.app.streaming.models.Suscripcion;
import com.eguino.app.streaming.services.IService;
import com.eguino.app.streaming.services.SuscripcionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/suscripciones/listar")
public class ListaSuscripcionesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperamos la conexion que provee el filtro
        Connection conn = (Connection) req.getAttribute("conn");

        //resp.getWriter().println("<h1>Suscripciones</h1>");
        //resp.getWriter().println("<p>Lista con todos los suscripciones registrados en la empresa</p>");
        //Declaramos un objeto de tipo servicio
        IService<Suscripcion> service = new SuscripcionService(conn);
        List<Suscripcion> suscripciones = service.listar();
        /*for(Chofer c: choferes){
            resp.getWriter().println("<h2>" + c.getId() + " -> "
                + c.getNombre() + " -> " + c.getApPaterno() + "</h2>");
        }*/
        req.setAttribute("suscripciones",suscripciones);
        getServletContext().getRequestDispatcher("/listaSuscripciones.jsp").forward(req,resp);
    }
}
