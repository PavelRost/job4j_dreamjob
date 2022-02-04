package ru.job4j.dream.store;

import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

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
        Candidate candidate = new Candidate(0, "Oleg");
        store.saveCandidate(candidate);
        Candidate candidateInDb = store.findCandidateById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdatePost() {
        Store store = DbStore.instOf();
        Post post = new Post(1, "Java Job New");
        store.savePost(post);
        Post postInDb = store.findPostById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(1, "Oleg New");
        store.saveCandidate(candidate);
        Candidate candidateInDb = store.findCandidateById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenFindAllPosts() {
        Store store = DbStore.instOf();
        Collection<Post> allPosts = store.findAllPosts();
        Collection<Post> expected = List.of(new Post(1, "Java Job New"));
        assertThat(allPosts, is(expected));
    }

    @Test
    public void whenFindAllCandidates() {
        Store store = DbStore.instOf();
        Collection<Candidate> allCandidates = store.findAllCandidates();
        Collection<Candidate> expected = List.of(new Candidate(1, "Oleg New"));
        assertThat(allCandidates, is(expected));
    }

}