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
public class SLMAB extends MabSelection{

    public SLMAB(int numberOfHeuristics, double scalingFactor, Random r) {
        super(numberOfHeuristics, scalingFactor, r);
    }

    @Override
    public void updateHeuristicValue(double delta, int heuristic, int k) {
        addToSlidingWindow(new Operator(heuristic, delta));
        setReward(getCreditAssignment().getReward(heuristic, 1, false, getSlidingWindow(), numberOfHeuristics));
        getqH()[heuristic] = getReward() * (1.0 / (getnHeutrials()[heuristic] + 1.0));
        getnHeutrials()[heuristic] = getnHeutrials()[heuristic] * (double) getSlidingWindow().size() / (double) (getSlidingWindow().size() + ((double) k - getLast()[heuristic])) + (1.0 / (getnHeutrials()[heuristic] + 1.0));
        getLast()[heuristic] = k;
    }

    @Override
    public void addToSlidingWindow(Operator op) {
         if (getSlidingWindow().size() == getW()) {
            getSlidingWindow().removeFirst();
            getSlidingWindow().addLast(op);
        } else {
            getSlidingWindow().addLast(op);
        }
    }
    
}
