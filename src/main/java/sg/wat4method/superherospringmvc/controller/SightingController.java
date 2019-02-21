/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.wat4method.superherospringmvc.model.Location;
import sg.wat4method.superherospringmvc.model.Sighting;
import sg.wat4method.superherospringmvc.model.Super;
import sg.wat4method.superherospringmvc.service.DataIntegrityViolationException;
import sg.wat4method.superherospringmvc.service.DataNotFoundException;
import sg.wat4method.superherospringmvc.service.SightingService;
import sg.wat4method.superherospringmvc.service.SuperService;

/**
 *
 * @author Jun
 */
@Controller
public class SightingController {

    SuperService service;
    SightingService stservice;

    @Inject
    public SightingController(SuperService service, SightingService stservice) {
        this.service = service;
        this.stservice = stservice;
    }

    @RequestMapping(value = {"/displaySightingsPage"}, method = RequestMethod.GET)
    public String displaySightingsPage(HttpServletRequest request, Model model) throws DataNotFoundException {

        List<Sighting> sightingList = stservice.getAllSightings();
        List<Super> superList = service.getAllSupers();
        List<Location> locationList = stservice.getAllLocations();
        List<Location> locationListBySighting = new ArrayList<>();
        List<Sighting> newSightingList = new ArrayList<>();
        try {
            for (Sighting currentSighting : sightingList) {

                Sighting sighting = currentSighting;
                int superId = currentSighting.getSuperId();
                int locationId = currentSighting.getLocationId();
                Super hero = service.getSuperById(superId);
                Location location = stservice.getLocationById(locationId);
                sighting.setHero(hero);
                sighting.setLocation(location);
                locationListBySighting.add(location);
                newSightingList.add(sighting);
            }
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
        model.addAttribute("newSightingList", newSightingList);

        model.addAttribute("locationList", locationList);
        model.addAttribute("superList", superList);
        model.addAttribute("sightingList", sightingList);
        return "sightings";

    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request, Model model) {
        try {
            Sighting sighting = new Sighting();
            sighting.setSightingDate(LocalDate.parse(request.getParameter("sightingDate")));
            sighting.setLocationId(Integer.parseInt(request.getParameter("locationId")));
            sighting.setSuperId(Integer.parseInt(request.getParameter("superId")));
            Sighting returnSighting = stservice.addSighting(sighting);
            model.addAttribute("returnSighting", returnSighting);
            return "redirect:displaySightingsPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        try {
            String sightingIdParameter = request.getParameter("sightingId");
            int sightingId = Integer.parseInt(sightingIdParameter);
            Sighting sighting = stservice.getSightingById(sightingId);
            Super hero = service.getSuperById(sighting.getSuperId());
            Location location = stservice.getLocationById(sighting.getLocationId());
            model.addAttribute("hero", hero);
            model.addAttribute("location", location);

            model.addAttribute("sighting", sighting);

            return "sightingDetails";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        try {
            String sightingIdParameter = request.getParameter("sightingId");
            int sightingId = Integer.parseInt(sightingIdParameter);
            stservice.deleteSighting(sightingId);
            return "redirect:displaySightingsPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        try {
            String sightingIdParameter = request.getParameter("sightingId");
            int sightingId = Integer.parseInt(sightingIdParameter);

            Sighting sighting = stservice.getSightingById(sightingId);

            List<Location> locationList = stservice.getAllLocations();
            model.addAttribute("locationList", locationList);

            List<Super> superList = service.getAllHeroes();
            model.addAttribute("superList", superList);

            model.addAttribute("sighting", sighting);
            return "editSightings";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@Valid HttpServletRequest request, Model model) {

        try {
            Sighting sighting = new Sighting();
            sighting.setSightingId(Integer.parseInt(request.getParameter("sightingId")));
            sighting.setSightingDate(LocalDate.parse(request.getParameter("sightingDate")));
            sighting.setLocationId(Integer.parseInt(request.getParameter("locationId")));
            sighting.setSuperId(Integer.parseInt(request.getParameter("superId")));
            stservice.updateSighting(sighting);

            return "redirect:displaySightingsPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }
}
