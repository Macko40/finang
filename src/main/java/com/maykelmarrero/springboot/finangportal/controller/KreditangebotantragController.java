package com.maykelmarrero.springboot.finangportal.controller;

import com.maykelmarrero.springboot.finangportal.entity.Kreditangebotantrag;
import com.maykelmarrero.springboot.finangportal.entity.KreditangebotantragStatus;
import com.maykelmarrero.springboot.finangportal.entity.Kreditangeboten;
import com.maykelmarrero.springboot.finangportal.service.KreditangebotantragService;
import com.maykelmarrero.springboot.finangportal.service.KreditangebotantragStatusService;
import com.maykelmarrero.springboot.finangportal.service.KreditangeboteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/kreditangebotantrag")
public class KreditangebotantragController {


    private KreditangeboteService kreditangeboteService;
    private KreditangebotantragService kreditangebotantragService;
    private KreditangebotantragStatusService kreditangebotantragStatusService;

    private static final Logger LOGGER = Logger.getLogger(KreditangebotantragController.class.getName());

    @Autowired
    private KreditangebotantragController(KreditangeboteService kreditangeboteService,
                                          KreditangebotantragService kreditangebotantragService,
                                          KreditangebotantragStatusService kreditangebotantragStatusService) {
        this.kreditangeboteService = kreditangeboteService;
        this.kreditangebotantragService = kreditangebotantragService;
        this.kreditangebotantragStatusService = kreditangebotantragStatusService;
    }


    // add mapping for "/list"
    @GetMapping("/list")
    public String listeKreditangebotantrag(Model theModel) {

        // get the kreditangeboten from db
        List<Kreditangebotantrag> kreditangebotantrag = kreditangebotantragService.getKreditangebotantragList();

        // Liste kreditangebotantragStatus
        List<KreditangebotantragStatus> getAntragstatusNameList = kreditangebotantragStatusService.getKreditangebotantragStatusName();
        System.out.println("hier sind die Liste von Antrag-Satus:" + getAntragstatusNameList);
        // add to the spring model
        
        theModel.addAttribute("kreditangebotantrag", kreditangebotantrag);
        theModel.addAttribute("getAntragstatusNameList", getAntragstatusNameList);


        return "kreditangebotantrag/liste-kreditangebotantrag";
    }


    @GetMapping("/formularNeuesKreditangebotantrag")
    public String formularNeuesKreditangebotantrag(@RequestParam("kreditangebotenId") int kreditangebotenId, Model model) {

        // Get the Kreditangeboten object based on the ID provided.
        Kreditangeboten kreditangeboten = kreditangeboteService.findById(kreditangebotenId);

        //Get Kreditangebotantrag
        Kreditangebotantrag kreditangebotantrag = new Kreditangebotantrag();

        // The previously obtained Kreditangeboten object is set to the kreditangebotantrag object.
        kreditangebotantrag.setKreditangeboten(kreditangeboten);

        System.out.println("KreditangebotId comprobar si es null:" + kreditangebotenId);
        model.addAttribute("kreditangeboten", kreditangeboten);
        model.addAttribute("kreditangebotenId", kreditangebotenId);
        model.addAttribute("kreditangebotantrag", kreditangebotantrag);


        return "kreditangebotantrag/formular-neuesKreditangebotantrag";

    }


    @PostMapping("/save")
    public String speichernKreditangebotantrag(@Valid @ModelAttribute("kreditangebotantrag") Kreditangebotantrag kreditangebotantrag,
                                               Model model, BindingResult bindingResult) {
        System.out.println(kreditangebotantrag.toString());

        // save kreditangebotantrag
        kreditangebotantragService.save(kreditangebotantrag);


        // Add kreditangebotantragId to the Model
        model.addAttribute("kreditangebotantragId", kreditangebotantrag.getAntragId());
        model.addAttribute("kreditangebotantrag", kreditangebotantrag);

        if (bindingResult.hasErrors()) {
            return "kreditangebotantrag/formular-neuesKreditangebotantrag";
        } else {
            // use a redirect to prevent duplicate submissions
            // redirect to confirmation page
            return "/kreditangebotantrag/confirm-neuesKreditangebotantrag";
        }


    }


}
