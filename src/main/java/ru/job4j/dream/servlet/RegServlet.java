package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;
import ru.job4j.dream.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (email.isEmpty() || name.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Все поля должны быть заполнены");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
        DbStore.instOf().saveUser(
                new User(
                        0,
                        name,
                        email,
                        password
                )
        );
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
