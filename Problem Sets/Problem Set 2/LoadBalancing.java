import java.rmi.dgc.VMID;
import java.util.Arrays;

/**
 * Contains static routines for solving the problem of balancing m jobs on p processors
 * with the constraint that each processor can only perform consecutive jobs.
 */
public class LoadBalancing {

    /**
     * Checks if it is possible to assign the specified jobs to the specified number of processors such that no
     * processor's load is higher than the specified query load.
     *
     * @param jobSize the sizes of the jobs to be performed
     * @param queryLoad the maximum load allowed for any processor
     * @param p the number of processors
     * @return true iff it is possible to assign the jobs to p processors so that no processor has more than queryLoad load.
     */
    public static boolean feasibleLoad(int[] jobSize, int queryLoad, int p) {
        /**  This is a for loop, where each loader will be loaded continuously until is is overloaded
         * ie. jobLoad > queryLoad. If a loader is overloaded after accepting i-th job, we will remove the i-th
         * job and pass to the next loader. This will decrease the loader number by 1.
         *
         * Special Case: If i-th job's jobSize is larger than the queryLoad of a loader, return false.
         *
         * return boolean (number of loaders left more than zero)
        */
        int jobLoad = 0;
        for(int job = 0; job < jobSize.length; job++){
            if (jobSize[job] > queryLoad){
                return false;
            }
            jobLoad = jobLoad + jobSize[job];
            if (jobLoad > queryLoad){
                jobLoad = jobSize[job];
                p -= 1;
            }

        }
        return p > 0;
    }

    /**
     * Returns the minimum achievable load given the specified jobs and number of processors.
     *
     * @param jobSize the sizes of the jobs to be performed
     * @param p the number of processors
     * @return the maximum load for a job assignment that minimizes the maximum load
     */
    public static int findLoad(int[] jobSize, int p) {
        /** maxWorkLoad =   Sum of all job sizes. This is because if there is only 1 processor, it will need to
         *                  handle all the jobs.
         *  minWorkLoad =   maxWorkLoad / no. of processors. This is because by splitting the total work size
         *                  equally to all processor, the average workload will be the minimum workload taken for
         *                  each processor (imagine situation where each job disk can split further)
         *

         */
        int maxWorkLoad = Arrays.stream(jobSize).reduce(0, (x,y) -> x+y );
        int minWorkLoad = maxWorkLoad / p;
        int midWorkLoad;

        /** Binary Search for the minimum feasible query load for each processor.
         *  If minWorkLoad = maxWorkLoad, the while loop will break, which signals the while loop has already
         *  converge onto the least feasible query load.
         */
        while (minWorkLoad < maxWorkLoad){
            midWorkLoad = minWorkLoad + (maxWorkLoad - minWorkLoad) / 2;
            /**
             * If feasibleLoad is true for the specific queryLoad, look further to the left half for the minimum
             * feasible queryLoad.
             * Else, look into the right half for the minimum feasible queryLoad.
             */
            if (feasibleLoad(jobSize, midWorkLoad, p)){
                maxWorkLoad = midWorkLoad;
            }else{
                minWorkLoad = midWorkLoad + 1;
            }
        }

        return minWorkLoad;
    }

    // These are some arbitrary testcases.
    public static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, 100, 80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83},
            {7}
    };

    /**
     * Some simple tests for the findLoad routine.
     */
    public static void main(String[] args) {
        for (int p = 1; p < 30; p++) {
            System.out.println("Processors: " + p);
            for (int[] testCase : testCases) {
                System.out.println(findLoad(testCase, p));
            }
        }
    }
}
