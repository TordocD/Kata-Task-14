package Kata.tasks.RestTemplate;

import Kata.tasks.RestTemplate.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Rest {

    public static void main(String[] args) {

        String users = "http://94.198.50.185:7081/api/users";
        String delete = "http://94.198.50.185:7081/api/users/3";
        User postedUser = new User(3L, "James", "Brown", (byte)22);

        RestTemplate restTemplate = new RestTemplate();

//        GET
        ResponseEntity<String> getUsers = restTemplate.getForEntity(users, String.class);
        HttpHeaders header = new HttpHeaders();
        header.add("Cookie", getUsers.getHeaders().get("Set-Cookie").get(0));
        System.out.println("Cookie: " + header);

//        POST
        HttpEntity<User> postEntity = new HttpEntity<>(postedUser, header);
        ResponseEntity<String> postUser = restTemplate.postForEntity(users, postEntity, String.class);
        System.out.println("Post body: " + postUser.getBody());

//        PUT
        postedUser.setName("Thomas");
        postedUser.setLastName("Shelby");
        HttpEntity<User> putEntity = new HttpEntity<>(postedUser, header);
        ResponseEntity<String> putUser = restTemplate.exchange(users, HttpMethod.PUT, putEntity, String.class);
        System.out.println("Put body: " + putUser.getBody());

//        DELETE
        HttpEntity<User> deleteEntity = new HttpEntity<>(header);
        ResponseEntity<String> deleteUser = restTemplate.exchange(delete, HttpMethod.DELETE, deleteEntity, String.class);
        System.out.println("Delete body: " + deleteUser.getBody());

//        SOLUTION
        System.out.println("SOLUTION: " + postUser.getBody() + putUser.getBody() + deleteUser.getBody());
    }
}
