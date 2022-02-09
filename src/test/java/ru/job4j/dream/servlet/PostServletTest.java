package ru.job4j.dream.servlet;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PostServletTest {

    @Test
    public void whenCreatePost() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("name of new post");
        when(req.getParameter("description")).thenReturn("d");
        new PostServlet().doPost(req, resp);
        Post post = DbStore.instOf().findAllPosts()
                .stream()
                .filter(p -> p.getName().equals("name of new post"))
                .findFirst()
                .get();
        assertThat(post, notNullValue());
        assertThat(post.getName(), is("name of new post"));
    }

    @Ignore
    @Test
    public void whenGetResponseSuccess() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        req.setAttribute("posts", new ArrayList<>(DbStore.instOf().findAllPosts()));
        new PostServlet().doGet(req, resp);
    }

}