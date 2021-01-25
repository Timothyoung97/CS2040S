import java.util.Arrays;
import java.lang.Math;

class WiFi {

    /**
     * Implement your solution here
     */
    public static double computeDistance(int[] houses, int numOfAccessPoints) {

        //  Ensure the houses[] array is sorted in an ascending order
        Arrays.sort(houses);

        //  Binary search for minimum radius required
        int maxDistRequired = (int) Math.ceil(houses[houses.length - 1] - houses[0]);
        int minDistRequired = 0;
        int midDistRequired;


        while(minDistRequired < maxDistRequired){
            midDistRequired = minDistRequired + (maxDistRequired - minDistRequired)/2;
            //  remember to midDistRequired / 2, this is because midDistRequired is the diameter
            if(coverable(houses, numOfAccessPoints, midDistRequired/2)){
                maxDistRequired = midDistRequired;
            }else{
                minDistRequired = midDistRequired + 1;
            }
        }

        //  return the radius only
        return minDistRequired * 0.5;
    }

    /** A simple for loop that will maximise the usage of access points.
     */
    public static boolean coverable(int[] houses, int numOfAccessPoints, double distance) {

        //  sort the houses into the ascending order of position
        Arrays.sort(houses);

        int numberOfRouterUsed = 0;

        //  putting the router at the furthest possible position from the house
        double positionOfRouter = houses[0] - distance - 1;

        for (int positionOfHouse : houses) {
            //  if distance between new house is out of the range of the router from its position,
            //  we place a new router at the furthest position away form the new house,
            //  and record a new router being used.
            if (Math.abs(positionOfHouse - positionOfRouter) > distance) {
                positionOfRouter = positionOfHouse + distance;
                numberOfRouterUsed++;
            }
        }

        //  if router used is less or equal to number of access points available, then it is coverable,
        //  else it is not coverable.
        return numberOfRouterUsed <= numOfAccessPoints;
    }
}