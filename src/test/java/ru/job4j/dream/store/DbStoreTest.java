package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DbStoreTest {

    @Test
    public void whenCreatePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.savePost(post);
        Post postInDb = store.findPostById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenCreateCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Oleg", 1);
        store.saveCandidate(candidate);
        Candidate candidateInDb = store.findCandidateById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdatePost() {
        Store store = DbStore.instOf();
        clearPostAndResetId((DbStore) store);
        Post post1 = new Post(0, "Java Job");
        store.savePost(post1);
        Post post2 = new Post(1, "Java Job New");
        store.savePost(post2);
        Post postInDb = store.findPostById(post2.getId());
        assertThat(postInDb.getName(), is(post2.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
        Store store = DbStore.instOf();
        clearCandidateAndResetId((DbStore) store);
        Candidate candidate1 = new Candidate(0, "Oleg", 1);
        store.saveCandidate(candidate1);
        Candidate candidate2 = new Candidate(1, "Oleg New", 1);
        store.saveCandidate(candidate2);
        Candidate candidateInDb = store.findCandidateById(candidate2.getId());
        assertThat(candidateInDb.getName(), is(candidate2.getName()));
    }

    @Test
    public void whenFindAllPosts() {
        Store store = DbStore.instOf();
        clearPostAndResetId((DbStore) store);
        Post post = new Post(0, "Java Job");
        store.savePost(post);
        Collection<Post> allPosts = store.findAllPosts();
        Collection<Post> expected = List.of(new Post(1, "Java Job"));
        assertThat(allPosts, is(expected));
    }

    @Test
    public void whenFindAllCandidates() {
        Store store = DbStore.instOf();
        clearCandidateAndResetId((DbStore) store);
        Candidate candidate = new Candidate(0, "Oleg", 1);
        store.saveCandidate(candidate);
        Collection<Candidate> allCandidates = store.findAllCandidates();
        Collection<Candidate> expected = List.of(new Candidate(1, "Oleg", 1));
        assertThat(allCandidates, is(expected));
    }

    private void clearPostAndResetId(DbStore store) {
        BasicDataSource pool = store.getPool();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM post");
             PreparedStatement ps2 = cn.prepareStatement("ALTER TABLE post ALTER COLUMN id RESTART WITH 1;")) {
            ps.execute();
            ps2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearCandidateAndResetId(DbStore store) {
        BasicDataSource pool = store.getPool();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM candidate");
             PreparedStatement ps2 = cn.prepareStatement("ALTER TABLE candidate ALTER COLUMN id RESTART WITH 1;")) {
            ps.execute();
            ps2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}