/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.controller;

import java.util.ArrayList;
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
import sg.wat4method.superherospringmvc.dao.OrganizationDao;
import sg.wat4method.superherospringmvc.model.Location;
import sg.wat4method.superherospringmvc.model.Organization;
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
public class OrganizationController {

    OrganizationDao dao;
    SuperService service;
    SightingService stservice;

    @Inject
    public OrganizationController(SuperService service, SightingService stservice) {
        this.service = service;
        this.stservice = stservice;
    }

    @RequestMapping(value = {"/displayOrgsPage", ""}, method = RequestMethod.GET)
    public String displayOrgsPage(Model model) {
        try {
            List<Organization> organizationList = service.getAllOrgs();
            List<Location> locationList = stservice.getAllLocations();

            model.addAttribute("organizationList", organizationList);
            model.addAttribute("locationList", locationList);
            return "organizations";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(HttpServletRequest request, Model model) {
        try {
            Organization org = new Organization();
            org.setName(request.getParameter("name"));
            org.setDescription(request.getParameter("description"));
            org.setLocationId(Integer.parseInt(request.getParameter("locationId")));
            Organization returnOrg = service.createOrg(org);
            model.addAttribute("returnOrg", returnOrg);
            return "redirect:displayOrgsPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/displayOrgDetails", method = RequestMethod.GET)
    public String displayOrgDetails(HttpServletRequest request, Model model) {
        try {
            String organizationIdParameter = request.getParameter("organizationId");
            int organizationId = Integer.parseInt(organizationIdParameter);
            Organization org = service.getOrgById(organizationId);

            Location location = stservice.getLocationById(org.getLocationId());
            model.addAttribute("location", location);

            List<Super> superList = service.getSupersByOrgId(organizationId);
            model.addAttribute("superList", superList);

            model.addAttribute("organization", org);
            return "organizationDetails";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/displayEditOrgForm", method = RequestMethod.GET)
    public String displayEditOrgForm(HttpServletRequest request, Model model) {
        try {
            List<Location> locationList = stservice.getAllLocations();
            model.addAttribute("locationList", locationList);
            String organizationIdParameter = request.getParameter("organizationId");
            int organizationId = Integer.parseInt(organizationIdParameter);
            Organization organization = service.getOrgById(organizationId);
            model.addAttribute("organization", organization);
            return "editOrganizations";
        } catch (DataNotFoundException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/editOrg", method = RequestMethod.POST)
    public String editOrg(@Valid @ModelAttribute("organization") Organization org, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return "editOrganizations";
            }
            service.editOrg(org);
            return "redirect:displayOrgsPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }

    @RequestMapping(value = "/deleteOrg", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        try {
            String organizationIdParameter = request.getParameter("organizationId");
            int organizationId = Integer.parseInt(organizationIdParameter);
            service.deleteOrg(organizationId);
            return "redirect:displayOrgsPage";
        } catch (DataIntegrityViolationException ex) {
            return "redirect:error";
        }
    }

}
