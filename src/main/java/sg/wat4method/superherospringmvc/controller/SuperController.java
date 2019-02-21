/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.controller;

import java.util.ArrayList;
import java.util.List;
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
import sg.wat4method.superherospringmvc.model.Organization;
import sg.wat4method.superherospringmvc.model.Power;
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
@RequestMapping({"/"})
public class SuperController {

    SuperService service;
    SightingService stservice;
//    public static final String pictureFolder = "images/";
//    private AlbumDao picturedao;

    @Inject
    public SuperController(SuperService service, SightingService stservice) {
        this.service = service;
        this.stservice = stservice;
    }

    @RequestMapping(value = {"/index", ""}, method = RequestMethod.GET)
    public String index(Model model) throws DataNotFoundException {
        List<Sighting> sightingList = stservice.getLatestSightings();
        List<Super> superList = service.getAllSupers();
        List<Location> locationList = stservice.getAllLocations();

//        List<Location> locationListBySighting = new ArrayList<>();
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
//            locationListBySighting.add(location);
                newSightingList.add(sighting);

            }
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }

        model.addAttribute("newSightingList", newSightingList);

        model.addAttribute("locationList", locationList);
        model.addAttribute("superList", superList);
        model.addAttribute("sightingList", sightingList);
        return "index";
    }

    //Request Mappings for create/display/edit/delete superheroes
    @RequestMapping(value = {"/displaySupersPage"}, method = RequestMethod.GET)
    public String displaySupersPage(Model model) {
        try {
            // Get all the Supers from the DAO
            List<Super> superList = service.getAllSupers();
            List<Power> powerList = service.getAllPowers();
            // Put the List of Supers on the Model
            List<Organization> organizationList = service.getAllOrgs();
            model.addAttribute("organizationList", organizationList);
            model.addAttribute("superList", superList);
            model.addAttribute("powerList", powerList);
            // Return the logical name of our View component
            return "supers";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/createSuper", method = RequestMethod.POST)
    public String createSuper(@Valid HttpServletRequest request, Model model) {
        Super hero = new Super();
        hero.setName(request.getParameter("name"));
        hero.setDescription(request.getParameter("description"));
        hero.setIsHero(Boolean.parseBoolean(request.getParameter("isHero")));

        List<Power> powerList = new ArrayList<>();
        String[] pList = request.getParameterValues("power");
        for (String currentStr : pList) {
            try {
                int powerId = Integer.parseInt(currentStr);
                Power currentpw = service.getPowerById(powerId);
                powerList.add(currentpw);
            } catch (DataNotFoundException ex) {
                return "redirect:error";
            }
        }

        List<Organization> orgs = new ArrayList<>();
        String[] orgList = request.getParameterValues("organization");
        for (String currentOrgStr : orgList) {
            try {
                int orgId = Integer.parseInt(currentOrgStr);
                Organization currentOrg = service.getOrgById(orgId);
                orgs.add(currentOrg);
            } catch (DataNotFoundException ex) {
                return "redirect:error";
            }
        }

        hero.setPowers(powerList);
        hero.setOrgs(orgs);

        Super returnHero;
        try {
            returnHero = service.createSuper(hero);
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }

        model.addAttribute("returnHero", returnHero);

        return "redirect:displaySupersPage";
    }

    @RequestMapping(value = "/displaySuperDetails", method = RequestMethod.GET)
    public String displaySuperDetails(@Valid HttpServletRequest request, Model model) {
        try {
            String superIdParameter = request.getParameter("superId");
            int superId = Integer.parseInt(superIdParameter);
            Super hero = service.getSuperById(superId);

            String isHero;
            if (hero.getIsHero() == true) {
                isHero = "Hero";
            } else {
                isHero = "Villain";
            }
            model.addAttribute("isHero", isHero);

            model.addAttribute("hero", hero);

            List<Power> powerList = service.getAllPowersBySuper(superId);
            List<Organization> organizationList = service.getAllOrgsBySuper(superId);
            model.addAttribute("organizationList", organizationList);
            model.addAttribute("powerList", powerList);

//        picture attempt
//        List<Picture> pictures = picturedao.getAllPictures();
//        model.addAttribute("pictureList", pictures);
            return "superDetails";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/displayEditSuperForm", method = RequestMethod.GET)
    public String displayEditSuperForm(HttpServletRequest request, Model model) {
        try {
            String superIdParameter = request.getParameter("superId");
            int superId = Integer.parseInt(superIdParameter);

            Super hero = service.getSuperById(superId);
            List<Power> powerList = service.getAllPowers();
            List<Organization> organizationList = service.getAllOrgs();

            model.addAttribute("hero", hero);
            model.addAttribute("organizationList", organizationList);
            model.addAttribute("powerList", powerList);

            return "editSupers";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/editSuper", method = RequestMethod.POST)
    public String editSuper(@Valid HttpServletRequest request, @ModelAttribute("hero") Super hero, BindingResult result) {

        List<Power> powerList = new ArrayList<>();
        String[] pList = request.getParameterValues("powers");
        for (String currentStr : pList) {
            try {
                int powerId = Integer.parseInt(currentStr);
                Power currentpw = service.getPowerById(powerId);
                powerList.add(currentpw);
            } catch (DataNotFoundException ex) {
                return "redirect:error";
            }
        }

        List<Organization> orgs = new ArrayList<>();
        String[] orgList = request.getParameterValues("orgs");
        for (String currentOrgStr : orgList) {
            try {
                int orgId = Integer.parseInt(currentOrgStr);
                Organization currentOrg = service.getOrgById(orgId);
                orgs.add(currentOrg);
            } catch (DataNotFoundException ex) {
                return "redirect:error";
            }
        }

        hero.setPowers(powerList);
        hero.setOrgs(orgs);

        try {
            service.editSuper(hero);
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
        return "redirect:displaySupersPage";

    }

    @RequestMapping(value = "/deleteSuper", method = RequestMethod.GET)
    public String deleteSuper(HttpServletRequest request) {
        try {
            String superIdParameter = request.getParameter("superId");
            int superId = Integer.parseInt(superIdParameter);
            service.deleteSuper(superId);
            return "redirect:displaySupersPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }

}
