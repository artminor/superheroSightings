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
public class SuperPower {

    private int superId;
    private int powerId;
    List<Super> supers;
    List<Power> powers;

    public List<Super> getSupers() {
        return supers;
    }

    public void setSupers(List<Super> supers) {
        this.supers = supers;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }
    
    
    
    public int getSuperId() {
        return superId;
    }

    public void setSuperId(int superId) {
        this.superId = superId;
    }

    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.superId;
        hash = 17 * hash + this.powerId;
        hash = 17 * hash + Objects.hashCode(this.supers);
        hash = 17 * hash + Objects.hashCode(this.powers);
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
        final SuperPower other = (SuperPower) obj;
        if (this.superId != other.superId) {
            return false;
        }
        if (this.powerId != other.powerId) {
            return false;
        }
        if (!Objects.equals(this.supers, other.supers)) {
            return false;
        }
        if (!Objects.equals(this.powers, other.powers)) {
            return false;
        }
        return true;
    }

    

    
}
