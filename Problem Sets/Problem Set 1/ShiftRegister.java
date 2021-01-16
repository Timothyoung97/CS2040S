///////////////////////////////////
// This is the main shift register class.
// Notice that it implements the ILFShiftRegister interface.
// You will need to fill in the functionality.
///////////////////////////////////
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

/**
 * class ShiftRegister
 * @author: Yang Shiyuan
 * Description: implements the ILFShiftRegister interface.
 */
public class ShiftRegister implements ILFShiftRegister {
    ///////////////////////////////////
    // Create your class variables here
    ///////////////////////////////////
    // TODO:
    //  Set all variable to private for encapsulation;
    //  Size and tap are set to <final> since they are to be remained that same throughout;
    //  Seed is modifiable through internal methods;
    private final int size;
    private final int tap;
    private int[] seed;

    ///////////////////////////////////
    // Create your constructor here:
    ///////////////////////////////////
    ShiftRegister(int size, int tap) {
        // TODO:
        //  This is a constructor for ShiftRegister;
        //  Declare the respective value for variables size and tap;

        this.size = size;
        this.tap = tap;
    }

    ///////////////////////////////////
    // Create your class methods here:
    ///////////////////////////////////
    /**
     * setSeed
     * @param seed
     * Description:
     * setSeed() is used to declare the variable seed in its desired format;
     */
    @Override
    public void setSeed(int[] seed) {
        // TODO:
        //  First, create a new int[] arrangeSeed, to store the seed in the arranged format;
        //  Second, using a for-loop to register the bit of the seed in the reverse order into arrangeSeed;
        //  Lastly, assign ShiftRegister.seed to arrangeSeed;

        int[] arrangeSeed = new int[this.size];
        for (int i = 0 ; i < this.size ; i++){
            arrangeSeed[i] = seed[this.size - 1 - i];
        }
        System.out.println("---------------------------------");
        this.seed = arrangeSeed;

        return;
    }

    /**
     * shift
     * @return least significant bit of the resulting register
     * Description:
     * shift() method will perform the one step shift and return the XOR result;
     */
    @Override
    public int shift() {
        // TODO:
        //  previousSeed is the seed of ShiftRegister;
        //  newSeed is new int[] used to store the seed after shifting one step;
        //  for-loop to register the required bits from the previousSeed into the newSeed;
        //  compare is to store the result after the XOR;
        //  newSeed's least significant bit is then registered to the compare;
        //  Lastly, ShiftRegister's seed is reassigned to newSeed;

        int[] previousSeed = this.seed;
        int[] newSeed = new int[this.size];

        for(int i = 0 ; i < this.size - 1; i++){
            newSeed[i] = previousSeed[i+1];
        }

        int compare = previousSeed[0]^previousSeed[this.size - 1 - this.tap];
        newSeed[this.size-1] = compare;
        this.seed = newSeed;
        return compare;
    }

    /**
     * generate
     * @param k
     * @return integer value of the binary number formed through shift() method
     * Description:
     * generate() method perform shift() method k times and take in k number of least significant bit
     * It then transforms the binary code into integer value
     */

    @Override
    public int generate(int k) {
        // TODO:
        //  perform shift() method k times and take in k number of least significant bit,
        //  then transforms the binary code into integer value using toBinary() method;

        int[] gen = new int[k];
        for (int i = 0 ; i < k ; i++){
            gen[i] = this.shift();
        }
        int answer = this.toBinary(gen);
        return answer;
    }

    /**
     * Returns the integer representation for a binary int array.
     * @param array
     * @return
     */
    private int toBinary(int[] array) {
        // TODO:
        //  simple binary transformation to Integer;
        //  CAUTION: typecast required for Math.pow();
        int sum = 0;
        for (int i = 0 ; i < array.length ; i++){
            int power = array.length - 1 - i;
            sum = sum + array[i] * (int) Math.pow(2, power);
        }
        return sum;
    }

    public int[] getSeed(){
        return this.seed;
    }

    @Override
    public String toString(){
        return Arrays.toString(this.seed);
    }
}
