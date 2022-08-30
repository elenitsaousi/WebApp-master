package hua.WebApp.SpringBoot;


import antlr.BaseAST;
import hua.WebApp.SpringBoot.entities.RecommendationLetter.RecommendationLetter;
import hua.WebApp.SpringBoot.entities.RecommendationLetter.RecommendationLetterRepository;
import hua.WebApp.SpringBoot.entities.Request.Request;
import hua.WebApp.SpringBoot.entities.Request.RequestRepository;
import hua.WebApp.SpringBoot.entities.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final RecommendationLetterRepository recommendationLetterRepository;

    public RestController(UserDetailsService userDetailsService, UserRepository userRepository, RequestRepository requestRepository, RecommendationLetterRepository recommendationLetterRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
        this.recommendationLetterRepository = recommendationLetterRepository;
    }


    //Student Endpoints

    @GetMapping("/requests")
    public List<Request> getAllRequests(){

        List<Request> requests = requestRepository.findAll();

        requests.removeIf(r -> !r.getUid().equals(GetLoggedInUsername()));
        if (requests.isEmpty()){
            throw  new IllegalStateException(
                    " No requests found."
            );
        }
        return requests;

    }


    private String GetLoggedInUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();

        } else {
            username = principal.toString();

        }
        return username;

    }
    @PostMapping("/addRequest")
    public String addRequest (@RequestBody Request r){
        r.setUid(GetLoggedInUsername());
        r.setStatus("Pending");
        requestRepository.save(r);
        r.setUid(GetLoggedInUsername());
        requestRepository.save(r);
        return "Request added";
    }

    @PutMapping("/editRequest/{requestId}")
    public String editRequest(@PathVariable("requestId") Long requestId,
                              @Valid @RequestBody Request requestDetails){
        boolean exists = requestRepository.existsById(requestId);
        if (!exists) {
            throw new IllegalStateException(
                    "request with id : " + requestId + " does not exists"
            );
        }
        Optional<Request> r = requestRepository.findById(requestId);
        if (!r.get().getUid().equals(GetLoggedInUsername())) {
            throw new IllegalStateException(
                    "You dont have access to change the status of this request with id: " + requestId
            );
        }
        if (r.get().getStatus().equals("Accepted") ){
            throw  new IllegalStateException(
                    "You can't edit Accepted Requests! "
            );
        }
        Request request =  r.get();
        request.setEmail(requestDetails.getEmail());
        request.setText(requestDetails.getText());
        request.setDest(requestDetails.getDest());
        request.setMail(requestDetails.getEmail());
        request.setMark(requestDetails.getMark());
        request.setName(requestDetails.getName());
        request.setText(requestDetails.getText());
        requestRepository.save(request);
        return "Request Updated";
    }
    @DeleteMapping( "deleteRequest/{requestId}")
    public String deleteRequest(@PathVariable("requestId") Long requestId){
        Optional<Request> r = requestRepository.findById(requestId);

        boolean exists =  requestRepository.existsById(requestId);

        if (!exists ){
            throw  new IllegalStateException(
                    "request with id : "+ requestId + " does not exists"
            );
        }
        if (!r.get().getUid().equals(GetLoggedInUsername()) ){
            throw  new IllegalStateException(
                    "You dont have access to delete the request with id: "+ requestId
            );
        }
        if (r.get().getStatus().equals("Accepted") ){
            throw  new IllegalStateException(
                    "You can't delete Accepted Requests! "
            );
        }
        requestRepository.deleteById(requestId);
        return "Request Deleted";
    }

    @GetMapping("/pendingRequests")
    public List<Request> getAllPendingRequests(){

        List<Request> pendingRequests = requestRepository.findAll();

        pendingRequests.removeIf(r -> !r.getDest().equals(GetLoggedInUsername()) || !r.getStatus().equals("Pending") );

        return pendingRequests;
    }


    @GetMapping("/acceptedRequests")
    public List<Request> getAllAcceptedRequests(){

        List<Request> acceptedRequests = requestRepository.findAll();

        acceptedRequests.removeIf(r -> !r.getDest().equals(GetLoggedInUsername()) || !r.getStatus().equals("Accepted") );

        return acceptedRequests;
    }


    @PutMapping("/setRequestStatus/{requestId}")
    public String setRequestStatus(@PathVariable("requestId") Long requestId,
                                   @Valid @RequestBody Request requestDetails){
        boolean exists =  requestRepository.existsById(requestId);
        if (!exists ){
            throw  new IllegalStateException(
                    "request with id : "+ requestId + " does not exists"
            );
        }
        Optional<Request> r =  requestRepository.findById(requestId);
        if (!r.get().getDest().equals(GetLoggedInUsername()) ){
            throw  new IllegalStateException(
                    "You dont have access to change the status of this request with id: "+ requestId
            );
        }

        Request request =  r.get();
        request.setStatus(requestDetails.getStatus()) ;
        requestRepository.save(request);

        return  "Request Updated";
    }


    @PostMapping("/addLetter")
    public String addRecommendationLetter (@RequestBody RecommendationLetter rl){
        rl.setUid(GetLoggedInUsername());
        recommendationLetterRepository.save(rl);
        return "Recommendation Letter Saved";
    }


    @GetMapping("/viewLetters")
    public List<RecommendationLetter> getAllLetterRequests(){

        List<RecommendationLetter> recommendationLetters = recommendationLetterRepository.findAll();

        recommendationLetters.removeIf(r -> !r.getUid().equals(GetLoggedInUsername()));
        if (recommendationLetters.isEmpty()){
            throw  new IllegalStateException(
                    "No Letters found."
            );
        }
        return recommendationLetters;
    }


    @PutMapping("/editLetter/{letterId}")
    public String editLetter(@PathVariable("letterId") Long letterId,
                             @Valid @RequestBody RecommendationLetter letterDetails) {
        boolean exists = recommendationLetterRepository.existsById(letterId);
        if (!exists) {
            throw new IllegalStateException(
                    "letter with id : " + letterId + " does not exists"
            );
        }
        Optional<RecommendationLetter> l = recommendationLetterRepository.findById(letterId);
        if (!l.get().getUid().equals(GetLoggedInUsername())) {
            throw new IllegalStateException(
                    "You dont have access to change the status of this letter with id: " + letterId
            );
        }
        RecommendationLetter letter =  l.get();
        letter.setEmail(letterDetails.getEmail()); ;
        letter.setText(letterDetails.getText());
        letter.setProf_email(letterDetails.getProf_email());
        recommendationLetterRepository.save(letter);
        return "Recommendation Letter Updated";
    }


    @DeleteMapping( "deleteLetter/{letterId}")
    public void deleteLetter(@PathVariable("letterId") Long letterId){
        recommendationLetterRepository.findById(letterId);

        boolean exists =  recommendationLetterRepository.existsById(letterId);
        if (!exists){
            throw  new IllegalStateException(
                    "student with id : "+ letterId + " does not exists"
            );
        }
        Optional<RecommendationLetter> recommendationLetter = recommendationLetterRepository.findById(letterId);
        if (!recommendationLetter.get().getUid().equals(GetLoggedInUsername()) ){
            throw  new IllegalStateException(
                    "You dont have access to change the status of this request with id: "+ letterId
            );
        }
        recommendationLetterRepository.deleteById(letterId);
    }

    //    secretary's endpoints
    @PostMapping("/register")
    public String register(@RequestBody User u) {

        System.out.println(u);

        userRepository.save(u);

        u.setAttribute("message", "User Register Sucessfully!");
        return "User Register Sucessfully!";

    }



    @GetMapping("/users")
    public List<User> getAllUsers(){

        List<hua.WebApp.SpringBoot.entities.User.User> users = userRepository.findAll();

//            users.removeIf(r -> !r.getUid().equals(GetLoggedInUsername()));
        if (users.isEmpty()){
            throw  new IllegalStateException(
                    "No users found."
            );
        }
        return users;
    }


    @PutMapping("/editUser/{userId}")
    public String editUser(@PathVariable("userId") Long userId,
                             @Valid @RequestBody User userDetails) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException(
                    "user with id : " + userId + " does not exists"
            );
        }
        Optional<User> u = userRepository.findById(userId);
        if (!u.get().getID().equals(GetLoggedInUsername())) {
            throw new IllegalStateException(
                    "You don't have access to change the user with id: " + userId
            );
        }
        User user =  u.get();
        user.setRoles(userDetails.getRoles()); ;
        user.setUserName(userDetails.getUserName());
        userRepository.save(user);
        return "User Updated";
    }

    @DeleteMapping( "deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userRepository.findById(userId);

        boolean exists =  userRepository.existsById(userId);
        if (!exists){
            throw  new IllegalStateException(
                    "student with id : "+ userId + " does not exists"
            );
        }
        Optional<User> user = userRepository.findById(userId);

        userRepository.deleteById(userId);
    }


}