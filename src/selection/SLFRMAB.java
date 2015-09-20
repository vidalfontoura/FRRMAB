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
public class SLFRMAB extends MabSelection {

    public SLFRMAB(int numberOfHeuristics, double scalingFactor, Random r) {
        super(numberOfHeuristics, scalingFactor, r);
    }

    @Override
    public void updateHeuristicValue(double delta, int heuristic, int k) {
        addToSlidingWindow(new Operator(heuristic, delta));
        setReward(getCreditAssignment().getReward(heuristic, 1, false, getSlidingWindow(), numberOfHeuristics));
        getqH()[heuristic] = getReward() * (1.0 / (getnHeutrials()[heuristic] + 1.0));
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
