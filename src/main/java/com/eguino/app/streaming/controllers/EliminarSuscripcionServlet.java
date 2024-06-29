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
import java.util.Optional;

@WebServlet ("/suscripciones/eliminar")
public class EliminarSuscripcionServlet extends HttpServlet {
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
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/suscripciones/listar");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "No existe la suscripcion en la base de datos.");
            }
        }
        else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Error el id es null, se debe enviar como parámetro");
        }
    }
}
