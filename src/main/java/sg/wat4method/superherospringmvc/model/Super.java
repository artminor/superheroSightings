/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.model;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Jun
 */
public class Super {

    private int superId;

    @NotEmpty(message = "You must supply a value for Name.")
    @Length(max = 30, message = "Name must be no more than 30 characters in length.")
    private String name;

    @NotEmpty(message = "You must supply a value for Description.")
    @Length(max = 100, message = "Description must be no more than 100 characters in length.")
    private String description;

    @NotEmpty(message = "You must supply a value for hero status.")
    private Boolean isHero;

    List<Power> powers;
    List<Organization> orgs;

    List<Integer> listOfPowersInt;
    List<Integer> listofOrgsInt;

    public int getSuperId() {
        return superId;
    }

    public void setSuperId(int superId) {
        this.superId = superId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsHero() {
        return isHero;
    }

    public void setIsHero(Boolean isHero) {
        this.isHero = isHero;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public List<Organization> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<Organization> orgs) {
        this.orgs = orgs;
    }

    public List<Integer> getListOfPowersInt() {
        return listOfPowersInt;
    }

    public void setListOfPowersInt(List<Integer> listOfPowersInt) {
        this.listOfPowersInt = listOfPowersInt;
    }

    public List<Integer> getListofOrgsInt() {
        return listofOrgsInt;
    }

    public void setListofOrgsInt(List<Integer> listofOrgsInt) {
        this.listofOrgsInt = listofOrgsInt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.superId;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.description);
        hash = 71 * hash + Objects.hashCode(this.isHero);
        hash = 71 * hash + Objects.hashCode(this.powers);
        hash = 71 * hash + Objects.hashCode(this.orgs);
        hash = 71 * hash + Objects.hashCode(this.listOfPowersInt);
        hash = 71 * hash + Objects.hashCode(this.listofOrgsInt);
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
        final Super other = (Super) obj;
        if (this.superId != other.superId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.isHero, other.isHero)) {
            return false;
        }
        if (!Objects.equals(this.powers, other.powers)) {
            return false;
        }
        if (!Objects.equals(this.orgs, other.orgs)) {
            return false;
        }
        if (!Objects.equals(this.listOfPowersInt, other.listOfPowersInt)) {
            return false;
        }
        if (!Objects.equals(this.listofOrgsInt, other.listofOrgsInt)) {
            return false;
        }
        return true;
    }

}
