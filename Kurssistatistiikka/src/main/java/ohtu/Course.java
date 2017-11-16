/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.List;

/**
 *
 * @author squid
 */
public class Course {
    
    private String name;
    private String term;
    private List exercises;
    
    public List max_ex() {
        return this.exercises;
    }
    
    @Override
    public String toString() {
        return this.name + ", " + this.term;
    }
}
