/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moveAcceptance;

import java.util.Random;

/**
 *
 * @author asferreira
 */
public class LateAcceptance extends AcceptanceCriterion{

    public LateAcceptance(Random r) {
        super(r);
    }

    @Override
    public boolean accept(double newFitness, double currentFitness, double bestFitness) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
