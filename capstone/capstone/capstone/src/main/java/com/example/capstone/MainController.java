package com.example.capstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(path="/")
public class MainController {

    @Autowired
    private AutoRepository autoRepository;
    @Autowired
    private HomeRepository homeRepository;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/submit")
    public String submit(@RequestParam("button") String button) {
        if ("button1".equals(button)) {
            return "redirect:/autoquotesform";
        } else if ("button2".equals(button)) {
            return "redirect:/autoquotes";
        } else if ("button3".equals(button)) {
            return "redirect:/homequotesform";
        } else if ("button4".equals(button)) {
            return "redirect:/homequotes";
        }
        return "redirect:/";
    }

    //=======================================================================
    // AUTO POLICY QUOTES

    // get all auto quotes ------------------------------------------
    @GetMapping(path="/autoquotes")
    public String getAllAutoQuotes(Model model) {
        model.addAttribute("autoQuotes", autoRepository.findAll());
        return "autoquotes"; // html file
    }

    // auto quotes form------------------------------------------
    @GetMapping(path="/autoquotesform")
    public String getAutoQuotesForm() {
        return "autoquotesform"; // html file
    }

    // create new auto quote-----------------------------------------
    @PostMapping(path="/autoquotes")
    public @ResponseBody RedirectView addNewAutoQuote(
            @RequestParam int quoteId,
            @RequestParam double carValue,
            @RequestParam int totalAccidents,
            @RequestParam int driverAge,
            @RequestParam int location
    ){
        AutoPolicyQuote quote = new AutoPolicyQuote();
        quote.setQuoteId(quoteId);
        quote.setCarValue(carValue);
        quote.setTotalAccidents(totalAccidents);
        quote.setDriverAge(driverAge);
        quote.setLocation(location);

        // Calculate premium
        int basePremium;
        if (carValue <= 55000) {
            basePremium = 750;
        } else if (carValue > 55000 && carValue <= 75000) {
            basePremium = 900;
        } else if (carValue > 75000 && carValue <= 95000) {
            basePremium = 1200;
        } else {
            basePremium = 1500;
        }

        double ageFactor = 1.0;
        double accidentFactor = 1.0;
        double locationFactor = 1.0;

        // Apply age factor
        if (driverAge < 25) {
            ageFactor = 2.0;
        } else if (driverAge >= 25 && driverAge < 50) {
            ageFactor = 1.0;
        } else {
            ageFactor = 1.0;
        }

        // Apply accident factor
        if (totalAccidents == 0) {
            accidentFactor = 1.00;
        }
        if (totalAccidents == 1) {
            accidentFactor = 1.25;
        }
        if (totalAccidents == 2) {
            accidentFactor = 2.50;
        }

        // Apply location factor
        if (location == 1) {
            locationFactor = 1.5;
        } else if (location == 2) {
            locationFactor = 1.25;
        } else {
            locationFactor = 1.0;
        }

        // Calculate final premium
        double premium = basePremium * ageFactor * accidentFactor * locationFactor;
        quote.setQuoteTotal(premium);

        autoRepository.save(quote);
        return new RedirectView("autoquotes");
    }

    @DeleteMapping("/autoquotes/{quoteId}")
    public @ResponseBody String deleteAutoQuote(@PathVariable int quoteId) {
        AutoPolicyQuote quote = autoRepository.findById(quoteId).orElse(null);
        if (quote != null) {
            autoRepository.delete(quote);
            return "Auto quote with ID: " + quoteId + " has been deleted.";
        } else {
            return "Auto quote with ID: " + quoteId + " not found.";
        }
    }

    //=======================================================================
    // HOME POLICY QUOTES

    // get all home quotes ------------------------------------------
    @GetMapping(path="/homequotes")
    public String getAllHomeQuotes(Model model) {
        model.addAttribute("homeQuotes", homeRepository.findAll());
        return "homequotes"; // html file
    }

    // home quotes form------------------------------------------
    @GetMapping(path="/homequotesform")
    public String getHomeQuotesForm() {
        return "homequotesform"; // html file
    }

    // create new home quote-----------------------------------------
    @PostMapping(path="homequotes")
    public @ResponseBody RedirectView addNewHomeQuote(
            @RequestParam int quoteId,
            @RequestParam double homeValue,
            @RequestParam int homeAge,
            @RequestParam int heatingType
    ){
        HomePolicyQuote quote = new HomePolicyQuote();
        quote.setQuoteId(quoteId);
        quote.setHomeValue(homeValue);
        quote.setHomeAge(homeAge);
        quote.setHeatingType(heatingType);

        double basePremium;
        if (homeValue <= 150000) {
            basePremium = 500;
        } else if (homeValue > 150000 && homeValue <= 200000) {
            basePremium = 600;
        } else if (homeValue > 200000 && homeValue <= 250000) {
            basePremium = 700;
        } else {
            basePremium = 800;
        }

        double ageFactor = getAgeFactor(homeAge);
        double heatingFactor = getHeatingTypeFactor(heatingType);
        double dwellingFactor = getDwellingTypeFactor(1); // assuming a default dwelling type of 1

        double quoteTotal = basePremium * ageFactor * heatingFactor * dwellingFactor;
        quote.setQuoteTotal(quoteTotal);

        homeRepository.save(quote);
        return new RedirectView("/");
    }

    private static double getAgeFactor(int homeAge) {
        if (homeAge < 25) {
            return 1.0;
        } else if (homeAge < 50) {
            return 1.25;
        } else {
            return 1.5;
        }
    }

    private static double getHeatingTypeFactor(int heatingType) {
        switch (heatingType) {
            case 1:
                return 1.0;
            case 2:
                return 2.0;
            case 3:
                return 1.25;
            case 4:
                return 1.0;
            case 5:
                return 1.0;
            default:
                throw new IllegalArgumentException("Invalid heating type");
        }
    }

    private static double getDwellingTypeFactor(int dwellingType) {
        switch (dwellingType) {
            case 1:
                return 1.0;
            case 2:
                return 0.75;
            case 3:
                return 1.0;
            case 4:
                return 1.15;
            default:
                throw new IllegalArgumentException("Invalid dwelling type");
        }
    }
    @DeleteMapping("/homequotes/{quoteId}")
    public @ResponseBody String deleteHomeQuote(@PathVariable int quoteId) {
        HomePolicyQuote quote = homeRepository.findById(quoteId).orElse(null);
        if (quote != null) {
            homeRepository.delete(quote);
            return "Home quote with ID: " + quoteId + " has been deleted.";
        } else {
            return "Home quote with ID: " + quoteId + " not found.";
        }
    }
}
