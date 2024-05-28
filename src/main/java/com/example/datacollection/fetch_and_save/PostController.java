package com.example.datacollection.fetch_and_save;

//import org.hibernate.mapping.List;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String home() {
        return "Application is running!";
    }

    @GetMapping("/fetch-posts")
    public String fetchPosts() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://jsonplaceholder.typicode.com/posts";
        Post[] posts = restTemplate.getForObject(apiUrl, Post[].class);
        if (posts != null) {
            for (Post post : posts) {
                postRepository.save(post);
            }
        }
        return "Posts fetched and saved successfully!";
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return (List<Post>) postRepository.findAll();
    }
}
