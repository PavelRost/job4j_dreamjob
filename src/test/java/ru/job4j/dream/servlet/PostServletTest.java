package ru.job4j.dream.servlet;

import static org.junit.Assert.*;
import org.junit.Test;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
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
        Post post = DbStore.instOf().findAllPosts().stream().filter("name of new post"::equals).collect(Collectors.toList()).get(0);
        assertThat(post, notNullValue());
    }

}