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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet ("/suscripciones/editar")
public class EdicionSuscripcionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Suscripcion> service = new SuscripcionService(conn);
        String nombre = req.getParameter("nombre");
        String cantDispositivos = req.getParameter("cantDispositivos");
        String costo = req.getParameter("costo");
        String calidadMax = req.getParameter("calidadMax");
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "¡El nombre es requerido!");
        }
        if (cantDispositivos == null || cantDispositivos.isBlank()){
            errores.put("cantDispositivos","¡La cantidad de dispositivos es requerida!");
        }
        if (costo == null || costo.isBlank()){
            errores.put("costo","¡El costo es requerido!");
        }
        if (calidadMax == null || calidadMax.isBlank()){
            errores.put("calidadMax","¡La calidadMax es requerida!");
        }

        long id;
        id = Long.parseLong(req.getParameter("id"));
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setId(id);
        suscripcion.setNombre(nombre);
        suscripcion.setCantDispositivos(Integer.parseInt(cantDispositivos));
        suscripcion.setCosto(Double.parseDouble(costo));
        suscripcion.setCalidadMax(calidadMax);

        if (errores.isEmpty()){

            service.guardar(suscripcion);
            resp.sendRedirect("listar");
        } else {
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/edicionSuscripcion.jsp")
                    .forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IService<Suscripcion> service = new SuscripcionService(conn);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            id = 0L;
        }
        Suscripcion suscripcion = new Suscripcion();
        if (id > 0){
            Optional<Suscripcion> o = service.getById(id);
            if(o.isPresent()) {
                suscripcion = o.get();
                req.setAttribute("suscripcion",suscripcion);
                getServletContext().getRequestDispatcher("/edicionSuscripcion.jsp")
                        .forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe la suscripción en la base de datos.");
            }
        }
        else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parámetro");
        }
    }
}
