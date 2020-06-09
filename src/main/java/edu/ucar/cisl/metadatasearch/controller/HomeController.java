package edu.ucar.cisl.metadatasearch.controller;

import edu.ucar.cisl.metadatasearch.model.SearchResults;
import edu.ucar.cisl.metadatasearch.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "/search")
    public String read(Model model) {

        SearchResults searchResults = this.searchRepository.getAll();
        model.addAttribute("searchResults", searchResults);

        return "display-results";
    }

}
