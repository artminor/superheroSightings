/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.wat4method.superherospringmvc.model.Location;
import sg.wat4method.superherospringmvc.service.DataIntegrityViolationException;
import sg.wat4method.superherospringmvc.service.DataNotFoundException;
import sg.wat4method.superherospringmvc.service.SightingService;

/**
 *
 * @author Jun
 */
@Controller
public class LocationController {

    SightingService stservice;

    @Inject
    public LocationController(SightingService stservice) {
        this.stservice = stservice;
    }

    @RequestMapping(value = {"/displayLocationsPage"}, method = RequestMethod.GET)
    public String displayLocations(HttpServletRequest request, Model model) {
        try {
            List<Location> locationList = stservice.getAllLocations();
            model.addAttribute("locationList", locationList);
            return "locations";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(HttpServletRequest request, Model model) {
        try {
            Location location = new Location();
            location.setName(request.getParameter("name"));
            BigDecimal latitude = new BigDecimal(request.getParameter("latitude"));
            location.setLatitude(latitude);
            BigDecimal longitude = new BigDecimal(request.getParameter("longitude"));
            location.setLongitude(longitude);
            location.setStreet(request.getParameter("street"));
            location.setCity(request.getParameter("city"));
            location.setState(request.getParameter("state"));
            location.setZip(request.getParameter("zip"));
            location.setDescription(request.getParameter("description"));
            Location returnLocation = stservice.addLocation(location);

            model.addAttribute("returnLocation", returnLocation);
            return "redirect:displayLocationsPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        try {
            String locationIdparameter = request.getParameter("locationId");
            int locationId = Integer.parseInt(locationIdparameter);
            Location curretnLocation = stservice.getLocationById(locationId);
            model.addAttribute("location", curretnLocation);
            return "locationDetails";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request, Model model) {
        try {
            String locationIdparameter = request.getParameter("locationId");
            int locationId = Integer.parseInt(locationIdparameter);
            stservice.deleteLocation(locationId);
            return "redirect:displayLocationsPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
        try {
            String locationIdParameter = request.getParameter("locationId");
            int locationId = Integer.parseInt(locationIdParameter);
            Location currentLocation = stservice.getLocationById(locationId);
            model.addAttribute("location", currentLocation);
            return "editLocations";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return "editLocations";
            }
            stservice.editLocation(location);
            return "redirect:displayLocationsPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }
}
