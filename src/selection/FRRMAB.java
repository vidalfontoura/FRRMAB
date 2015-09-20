/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selection;

import java.util.Random;

/**
 *
 * @author Alexandre
 */
public class FRRMAB extends MabSelection{

    public FRRMAB(int numberOfHeuristics, double scalingFactor, Random r) {
        super(numberOfHeuristics, scalingFactor, r);
    }

    @Override
    public void updateHeuristicValue(double delta, int heuristic, int k) {
        addToSlidingWindow(new Operator(heuristic, delta));
        double rewards[] = new double [k];
        rewards = getCreditAssignment().getRankedRewards(heuristic, 1, false, getSlidingWindow(), numberOfHeuristics);
        for(int i = 0; i < k; i++){
            getqH()[i] = rewards[i];
        }
    }
    
    @Override
    public void addToSlidingWindow(Operator op) {
        if (getSlidingWindow().size() == getW()) {
            int hIndx = getSlidingWindow().removeFirst().getOperatorId();
            getnHeutrials()[hIndx]--;
            getSlidingWindow().addLast(op);
        } else {

            getSlidingWindow().addLast(op);
        }
        getnHeutrials()[op.getOperatorId()]++;
    }

    
}
