package edu.ucar.cisl.metadatasearch.controller;

import edu.ucar.cisl.metadatasearch.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController
{
    @Autowired
    SearchRepository searchRepository;

    @RequestMapping(value = "/index")
    public String index(Model model) {

        model.addAttribute("message", "You rock!");

        return "index";
    }

    @RequestMapping(value = "/read")
    public String read(Model model) {

        List results = this.searchRepository.getAll();
        model.addAttribute("results", results);
        return "results";

    }


}
