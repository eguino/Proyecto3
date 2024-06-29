package com.eguino.app.streaming.controllers;

import com.eguino.app.streaming.models.Suscripcion;
import com.eguino.app.streaming.services.IService;
import com.eguino.app.streaming.services.SuscripcionService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.net.HttpURLConnection;

import java.net.URL;

@WebServlet ("/comics")
public class ListaComicsServlet extends HttpServlet {
    private static final String PUBLIC_KEY = "394ecf111d138bd3e47aa577a311ccc0";
    private static final String HASH_VAL = "6db8990a8bd81476a34e7e192e096637";
    private static final String BASE_URL = "https://gateway.marvel.com/v1/public/comics";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + "?ts=1&apikey=" + PUBLIC_KEY + "&hash=" + HASH_VAL;
        Request req = new Request.Builder().url(url).build();
        Response res = client.newCall(req).execute();
        String jsonData = res.body().string();

        JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
        JsonArray results = jsonObject.getAsJsonObject("data").getAsJsonArray("results");

        List<Map<String, String>> comics = new ArrayList<>();
        for (JsonElement result : results) {
            JsonObject comic = result.getAsJsonObject();
            String title = comic.get("title").getAsString();
            JsonObject thumbnail = comic.getAsJsonObject("thumbnail");
            String imageUrl = thumbnail.get("path").getAsString() + "." + thumbnail.get("extension").getAsString();

            if(!imageUrl.contains("image_not_available")){
                Map<String, String> comicData = new HashMap<>();
                comicData.put("title", title);
                comicData.put("imageUrl", imageUrl);

                comics.add(comicData);
            }

        }

        request.setAttribute("comics", comics);
        request.getRequestDispatcher("/listaComics.jsp").forward(request, response);
    }
}
