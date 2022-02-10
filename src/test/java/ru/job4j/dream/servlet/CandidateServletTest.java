package ru.job4j.dream.servlet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.DbStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CandidateServletTest {

    @Test
    public void whenCreateCandidate() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("name of new candidate");
        new CandidateServlet().doPost(req, resp);
        Candidate candidate = DbStore.instOf().findAllCandidates()
                .stream()
                .filter(p -> p.getName().equals("name of new candidate"))
                .findFirst()
                .get();
        assertThat(candidate, notNullValue());
        assertThat(candidate.getName(), is("name of new candidate"));
    }

    @Test
    public void whenGetResponseSuccess() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("candidates.jsp")).thenReturn(dispatcher);
        new CandidateServlet().doGet(req, resp);
        verify(req).getRequestDispatcher("candidates.jsp");
    }

}