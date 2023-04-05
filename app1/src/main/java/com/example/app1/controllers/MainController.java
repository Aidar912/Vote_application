package com.example.app1.controllers;

import com.example.app1.entities.Candidate;
import com.example.app1.entities.User;
import com.example.app1.repositories.CandidateRepository;
import com.example.app1.repositories.UserRepository;
import com.example.app1.service.CandidateService;
import com.example.app1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private CandidateService candidateService;

    @GetMapping("/")
    public String getMainPage(Model model, Authentication authentication) {
        if (userService.isAdmin(authentication)) {
            List<Candidate> candidates = candidateService.getAllCandidate();
            model.addAttribute("candidates", candidates);
            return "adminPage";
        } else {
            List<Candidate> candidates = candidateService.getAllCandidate();
            model.addAttribute("candidates", candidates);
            model.addAttribute("currentUser", userRepository.findByUsername(authentication.getName()));
            return "index";
        }
    }

    @PostMapping("/")
    public String saveVote(@RequestParam String candidateName) {

        Candidate candidate = new Candidate();
        candidate.setName(candidateName);
        candidateService.save(candidate);

        return "redirect:/";
    }

    @PostMapping("/vote")
    public String voteForCandidate(@RequestParam Long candidateId, @RequestParam Integer userId , Model model) {
        try {
            candidateService.voteForCandidate(candidateId, userId);
            return "redirect:/";
        }catch (Exception e){

            return "redirect:/";
        }
    }

    @GetMapping("/winner")
    public String finish(Model model ){
        System.out.println("winner");
        Candidate winner = candidateService.winner();
        model.addAttribute("candidates", winner);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(){
        System.out.println("delete");
        candidateService.deleteAll();
        return "redirect:/";
    }
}

