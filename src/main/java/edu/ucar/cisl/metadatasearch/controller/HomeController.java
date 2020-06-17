package edu.ucar.cisl.metadatasearch.controller;

import edu.ucar.cisl.metadatasearch.model.SearchResults;
import edu.ucar.cisl.metadatasearch.model.SearchResultsImpl;
import edu.ucar.cisl.metadatasearch.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController
{
    private SearchRepository searchRepository;

    @Autowired
    public HomeController(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @RequestMapping(value = "/index")
    public String index(Model model) {

        model.addAttribute("message", "You rock!");

        return "index";
    }

    @ModelAttribute("command")
    public SearchCommand setupSearchCommand() {
        return new SearchCommand();
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {

        return "search-form";
    }

    @RequestMapping(value = "/searchOutput", method = RequestMethod.GET)
    public ModelAndView getResults(@ModelAttribute("command") SearchCommand searchCommand,
                             BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("search-form");

        SearchResults searchResults = this.searchRepository.getQueryResults(searchCommand.getQueryText());
        modelAndView.addObject("searchResults", searchResults);

        return modelAndView;
    }

}
