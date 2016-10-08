package fractionalknapsack;

/**
 *
 * @author Ross Cournoyer rcoupvc@yahoo.com
 */

import java.util.Scanner;

public class FractionalKnapsack {
    
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double totalValue = 0;
        double totalWeight = 0;
        //int i = 0;
        
        while (totalWeight <= capacity) {
            int maxDex = bestValueByWeight(values, weights);
            
            if (totalWeight + weights[maxDex] <= capacity) { //if there's room take it all
                totalWeight += weights[maxDex];
                totalValue += values[maxDex];
            }
            else {
                double difference = capacity - totalWeight;
                
                totalWeight += difference;
                totalValue += difference * (values[maxDex]/(double)weights[maxDex]);
            }
            values[maxDex] = 0;
            if (totalWeight == capacity) {
                break;
            }
        }
        return totalValue;
    }
    
    private static int bestValueByWeight(int[] values, int[] weights) {
        double currVal = 0;
        double maxVal = 0;
        int maxDex = 0;
        
        for (int i=0; i < values.length; ++i) {
            currVal = values[i]/(double)weights[i];
            if (currVal > maxVal) {
                maxVal = currVal;
                maxDex = i;
            }
        }
        return maxDex;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
}
