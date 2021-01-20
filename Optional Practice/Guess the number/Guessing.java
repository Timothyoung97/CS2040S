//  Simple Binary Search for integer from 0 to 1000
//  max_round_of_operation = 10
//  1000 / 2^10 = 0.9765 (narrow down to 1 integer)


public class Guessing {

    // Your local variables here
    private int low = 0;
    private int high = 1000;
    private int feedback = 0;
    private int mid = low + (high - low) / 2;

    /**
     * Implement how your algorithm should make a guess here
     */
    public int guess() {

        if (feedback != 0) {
            mid = low + (high - low) / 2;
        }
        return mid;
    }

    /**
     * Implement how your algorithm should update its guess here
     */
    public void update(int answer) {
        feedback = answer;
        if(answer == 1){
            high = mid;
        }else{
            low = mid;
        }
        return;
    }
}
