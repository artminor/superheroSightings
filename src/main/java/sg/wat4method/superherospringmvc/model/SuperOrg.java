/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jun
 */
public class SuperOrg {

    private Super hero;
//    private Organization org;
    private int superId;
    private int orgId;
    private List<Organization> organizations;

    public int getSuperId() {
        return superId;
    }

    public void setSuperId(int superId) {
        this.superId = superId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    
    
    
    public Super getHero() {
        return hero;
    }

    public void setHero(Super hero) {
        this.hero = hero;
    }

//    public Organization getOrg() {
//        return org;
//    }
//
//    public void setOrg(Organization org) {
//        this.org = org;
//    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.hero);
        hash = 67 * hash + this.superId;
        hash = 67 * hash + this.orgId;
        hash = 67 * hash + Objects.hashCode(this.organizations);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SuperOrg other = (SuperOrg) obj;
        if (this.superId != other.superId) {
            return false;
        }
        if (this.orgId != other.orgId) {
            return false;
        }
        if (!Objects.equals(this.hero, other.hero)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        return true;
    }





}
