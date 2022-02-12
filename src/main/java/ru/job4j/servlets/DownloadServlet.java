package ru.job4j.servlets;

import ru.job4j.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", req.getSession().getAttribute("user"));
        String name = req.getParameter("name");
        File downloadFile = null;
        for (File file : new File(Service.instOf().getPath()).listFiles()) {
            String nameFile = file.getName().split("\\.")[0];
            if (name.equals(nameFile)) {
                downloadFile = file;
                break;
            }
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(downloadFile)){
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}
