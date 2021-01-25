import java.util.Arrays;

/**
 * The Optimization class contains a static routine to find the maximum in an array that changes direction at most once.
 */
public class Optimization {

    /**
     * A set of test cases.
     */
    static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, -100, -80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83}
    };

    /**
     * Returns the maximum item in the specified array of integers which changes direction at most once.
     *
     * @param dataArray an array of integers which changes direction at most once.
     * @return the maximum item in data Array
     */
    public static int searchMax(int[] dataArray) {
        //  Base Case 1:    if array entered is empty, return 0;
        //  Base Case 2:    else if array only contains 1 element, return that element;
        if (dataArray.length == 0){
            System.out.println("You have entered an empty array");
            return 0;
        }else if (dataArray.length == 1){
            return dataArray[0];
        }

        //  Base Case 3:    if array only contains 2 elements, return the larger element;
        //  Base Case 4:    if array decreases from the start, simply compare the start and end element
        //                  and return the larger element;
        if (dataArray.length == 2 || dataArray[0] > dataArray[1]){
            return Math.max(dataArray[0], dataArray[dataArray.length-1]);
        }

        //  Base Case 5:    If array increases from the start, use divide & conquer to find the local maxima;
        int start = 0;
        int end = dataArray.length - 1;
        int mid = start + (end - start) / 2;;

        //  This is a while loop set up to converge the start and end;
        //  Thus generating the local maxima
        while (start != end){
            mid = start + (end - start) / 2;
            if (dataArray[mid+1] > dataArray[mid]){
                start = mid + 1;
            }else{
                end = mid;
            }
        }
        return dataArray[mid];


    }

    /**
     * A routine to test the searchMax routine.
     */
    public static void main(String[] args) {
        for (int[] testCase : testCases) {
            System.out.println(searchMax(testCase));
        }
    }
}

/** Optional Questions
    1)  What is the best way to signal an error, e.g., if the data array is empty?
        -   To signal an error, use
            if (error){
                throw new Exception("error message.");
            }else if (otherException){
                throw new otherException("other error message.");
            }

    2)  What if all the elements in the array are not unique? How efficiently can the problem be
        solved then? What is the worst-case example?

        If all elements in the array are not unique:
        -   Case 1: {..., x(mid), x, x, ..., global peak, ...}
                    when the next two elements after the midpoint element of the array are the same, while the
                    peak is after the midpoint element, the divide and conquer operation in Base Case 5 would
                    fail. This happens as it will only analyse the front half of the array since it fails the
                    condition dataArray[mid+1] > dataArray[mid] in line 51. In the end, returning a local peak
                    at the front half of the array, while the global peak actually exists at the back half.

                    Solution:
                    This can be solved if we change line 51 to dataArray[mid+1] >= dataArray[mid]. However, a reverse
                    outcome will happen where now the back half of the array will be analysed while the front half
                    won't, missing out on the global maxima which actually exists at the front half of the array.

        -   Case 2: {..., x(mid), y, y, ..., global peak, ...}
                    As long as the two elements after midpoint element of the array are different, the algorithm
                    will proceed as expected.
                    This is even so if the global peak is at the front half of the array.

    3)  This array changes direction once. What if it changes direction twice? Is the problem much
        harder?

        If the direction of the array changes more than once, the array will have more than one local peak.
        This is much harder to analyse now and to find the global maxima.

        -   Case:   {..., local peak,..., v, w, x(mid), y, z, ..., global peak, ...}
                    By line 51, in the first looping, if x is smaller than y, this will lead to a correct
                    expectation where the algorithm will proceed to find the global peak since the back half will
                    be analysed.
                    However, if x is equal/bigger than y, this will instead lead to a finding of the local peak
                    at the front half of the array.

                    Solution:
                    [Change Algorithm of Base Case 5]
                    int start = 0;
                    int end = dataArray.length - 1;
                    int mid = ((start + end) / 2) + 1;

                    return Math.max(
                        searchMax(Arrays.copyOfRange(dataArray, start, mid)),
                        searchMax(Arrays.copyOfRange(dataArray, mid, end + 1))
                    );

                    However, for the question, this would then not be the most efficient method, as this algorithm
                    would then have O(n^2) time complexity and it is worse than a linear searching method.

    4)  What if the array contains noisy data, so some of the entries in the array had +1 or−1
        noise randomly added/subtracted from their initial value (with probability 1/2 in each direction).
        Can you solve the problem in that case?  What would the expected running time be?

        I would simply run a linear search for the largest number since there might be multiple peaks to detect,
        which I think is rather efficient as time complexity of O(n).
 */