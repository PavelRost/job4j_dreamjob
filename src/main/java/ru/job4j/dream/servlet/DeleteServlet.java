package ru.job4j.dream.servlet;

import ru.job4j.dream.store.DbStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        for (File name : new File("e:\\images\\").listFiles()) {
            String nameFile = name.getName().split("\\.")[0];
            if (id.equals(nameFile)) {
                name.delete();
                break;
            }
        }
        DbStore.instOf().deleteCandidate(Integer.parseInt(id));
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
