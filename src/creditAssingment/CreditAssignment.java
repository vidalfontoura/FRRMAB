package creditAssingment;

import java.util.LinkedList;

import selection.Operator;

public class CreditAssignment {

    public double getReward(int op, int type, boolean norm,
            LinkedList<Operator> slidingWindow, int K) {
        double reward = 0;
        // last
        if (type == 0) {
            for (int i = slidingWindow.size() - 1; i >= 0; i--) {
                if (op == slidingWindow.get(i).getOperatorId()) {
                    reward = slidingWindow.get(i).getFitnessImprovement();
                    break;
                }
            }
        } // average
        else if (type == 1) {
            double sum = 0;
            int count = 0;
            for (Operator operator : slidingWindow) {
                if (op == operator.getOperatorId()) {
                    sum += operator.getFitnessImprovement();
                    count++;
                }
            }
            reward = sum / count;
        } // extreme
        else if (type == 2) {
            double max = Double.MIN_VALUE;
            for (Operator operator : slidingWindow) {
                if (op == operator.getOperatorId()) {
                    if (operator.getFitnessImprovement() > max) {
                        max = operator.getFitnessImprovement();
                    }
                }
            }
            reward = max;
        }
        // normalized
        if (norm) {
            double max = Double.MIN_VALUE;
            double normfactor = 0;
            for (int i = 0; i < K; i++) {
                double value = this.getReward(i, type, false, slidingWindow, K);
                if (value >= max) {
                    max = value;
                }
            }
            normfactor = max;
            reward = this.getReward(op, type, false, slidingWindow, K)
                    / normfactor;

        }
        return reward;
    }
    
    public double[] getRankedRewards(int op, int type, boolean norm,
            LinkedList<Operator> slidingWindow, int K) {
        double D = 1;
        double rewards[] = new double[K];
        double valueOps[] = new double[K];
        int ranks[] = new int[K];
        double decayValues[] = new double[K];
        double decaySum = 0;
        
        for (Operator operator : slidingWindow) {
            valueOps[operator.getOperatorId()] += operator.getFitnessImprovement();
        }
        
        Rank_rewards(valueOps, K, ranks);
        
        for (int i = 0; i < K; i++) {
            decayValues[ranks[i]] = Math.pow(D, (double) i) * valueOps[ranks[i]];
            decaySum += decayValues[i];
        }
        for (int i = 0; i < K; i++) {
            rewards[i]  = decayValues[i]/decaySum;   
        }
        return rewards;
    }
    
    	static void Rank_rewards(double[] reward_, int numStrategies_, int[] rank) {
		int i, j;
		double[][] temp;
		double temp_index;
		double temp_value;

		temp = new double[2][numStrategies_];
		for (i = 0; i < numStrategies_; i++) {
			temp[0][i] = reward_[i];
			temp[1][i] = i;
		}

		for (i = 0; i < numStrategies_ - 1; i++) {
			for (j = i + 1; j < numStrategies_; j++) {
				if (temp[0][i] < temp[0][j]) {
					temp_value = temp[0][j];
					temp[0][j] = temp[0][i];
					temp[0][i] = temp_value;

					temp_index = temp[1][j];
					temp[1][j] = temp[1][i];
					temp[1][i] = temp_index;
				}
			}
		}

		for (i = 0; i < numStrategies_; i++) {
			rank[i] = (int) temp[1][i];
		}
	}

}
