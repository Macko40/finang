package com.maykelmarrero.springboot.finangportal.controller;

import com.maykelmarrero.springboot.finangportal.entity.Kreditangeboten;
import com.maykelmarrero.springboot.finangportal.entity.KreditangebotenStatus;
import com.maykelmarrero.springboot.finangportal.service.KreditangeboteService;
import com.maykelmarrero.springboot.finangportal.service.KreditangebotenStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/kreditangeboten")
public class KreditangebotController {

    // @Autowired
    private KreditangeboteService kreditangeboteService;
    //@Autowired
    private KreditangebotenStatusService kreditangebotenStatusService;

    // Constructor injection
    @Autowired
    private KreditangebotController(KreditangeboteService pKreditangeboteService,
                                    KreditangebotenStatusService kreditangebotenStatusService) {
        this.kreditangeboteService = pKreditangeboteService;
        this.kreditangebotenStatusService = kreditangebotenStatusService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listeKreditangeboten(Model theModel) {

        // get the kreditangeboten from db
        List<Kreditangeboten> kreditangeboten = kreditangeboteService.findAll();

        // add to the spring model
        theModel.addAttribute("kreditangeboten", kreditangeboten);

        return "kreditangeboten/liste-kreditangeboten";
    }

    @GetMapping("/formularNeuesKreditangebotHinzufuegen")
    public String formularNeuKreditangebotHinzufuegen(Model model) {

        // Create model attribute to bind form data
        Kreditangeboten kreditangeboten = new Kreditangeboten();


        List<KreditangebotenStatus> getStatusNameList = kreditangebotenStatusService.getKreditangebotenStatusName();

        System.out.println("Lista: " + getStatusNameList);

        model.addAttribute("kreditangeboten", kreditangeboten);
        model.addAttribute("getStatusNameList", getStatusNameList);

        return "kreditangeboten/formular-neuesKreditangebot";
    }


    @GetMapping("/formularUpdateKreditangeboten")
    public String formularUpdateKreditangeboten(@RequestParam("kreditangebotId") int kreditangebotId, Model model) {

        // get the kreditangeboten from the service
        Kreditangeboten kreditangeboten = kreditangeboteService.findById(kreditangebotId);
        // Liste kreditangebotenStatus
        List<KreditangebotenStatus> getStatusNameList = kreditangebotenStatusService.getKreditangebotenStatusName();

        // set kreditangeboten in the model to PREPOPULATE the form
        model.addAttribute("kreditangeboten", kreditangeboten);
        model.addAttribute("getStatusNameList", getStatusNameList);
        // send  over to our form
        return "kreditangeboten/formular-neuesKreditangebot";
    }

    @GetMapping("/veroeffentlichtenKreditangebote")

    public String getVeroeffentlichtenKreditangebote(Model theModel) {


        // get the veroeffentlichten Kreditangebote from db
        List<Kreditangeboten> veroeffentlichtenKreditangebote = kreditangeboteService.getVeroeffentlichtenKreditangebote();

        // add to the spring model
        theModel.addAttribute("veroeffentlichtenKreditangebote", veroeffentlichtenKreditangebote);

        return "kreditangeboten/liste-veroeffentlichtenKreditangebote";
    }

    @GetMapping("formularNeueKundeHinzufuegen")
    public String formularNeueKundeHinzufuegen(Model model) {

        // Create model attribute to bind form data
        Kreditangeboten kreditangeboten = new Kreditangeboten();
        model.addAttribute("kreditangeboten", kreditangeboten);
        // model.addAttribute("kreditangebotStatus", kreditangebotStatus);

        return "kreditangeboten/formular-kundeKontakt";
    }


    @PostMapping("/save")
    public String speichernKreditangeboten(@ModelAttribute("kreditangeboten") Kreditangeboten kreditangeboten) {
        // save kreditangeboten
        kreditangeboteService.save(kreditangeboten);

        // use a redirect to prevent duplicate submissions
        return "redirect:/kreditangeboten/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("kreditangebotId") int kreditangebotenId) {

        // delete the Kreditangebot
        kreditangeboteService.deleteById(kreditangebotenId);
        // redirect to the /kreditangeboten/list
        return "redirect:/kreditangeboten/list";
    }


}
