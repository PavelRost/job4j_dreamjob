package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

public class MainStore {
    public static void main(String[] args) {
        Store store = DbStore.instOf();
        store.savePost(new Post(0, "Java Job 444"));
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }
        store.savePost(new Post(2, "Java Job 222"));
        System.out.println();
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }
        System.out.println();
        store.saveCandidate(new Candidate(0, "Fedor"));
        store.saveCandidate(new Candidate(0, "Liza"));
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate.getId() + " " + candidate.getName());
        }
        System.out.println();
        store.saveCandidate(new Candidate(2, "Liza new"));
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate.getId() + " " + candidate.getName());
        }
    }
}
