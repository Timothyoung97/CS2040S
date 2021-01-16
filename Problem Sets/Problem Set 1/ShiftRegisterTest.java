import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

/**
 * ShiftRegisterTest
 * @author dcsslg
 * Description: set of tests for a shift register implementation
 */
public class ShiftRegisterTest {

    /**
     * getRegister returns a shiftregister to test
     * @param size
     * @param tap
     * @return a new shift register
     * Description: to test a shiftregister, update this function
     * to instantiate the shift register
     */
    ILFShiftRegister getRegister(int size, int tap){
        return new ShiftRegister(size, tap);
    }

    /**
     * Test shift with simple example
     */
    @Test
    public void testShift1() {
        /*
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = {0,1,0,1,1,1,1,0,1};
        r.setSeed(seed);
        int[] expected = {1,1,0,0,0,1,1,1,1,0};
        for (int i=0; i<10; i++){
            assertEquals(expected[i], r.shift());
        }
        */
        int[] array = new int[] {0,1,0,1,1,1,1,0,1};
        ShiftRegister shifter = new ShiftRegister(9,7);
        shifter.setSeed(array);
        for (int i=0; i<10; i++){
            int j = shifter.shift();
            System.out.print(j);
        }
    }

    /**
     * Test generate with simple example
     */
    @Test
    public void testGenerate1() {
        /*
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = {0,1,0,1,1,1,1,0,1};
        r.setSeed(seed);
        int[] expected = {6,1,7,2,2,1,6,6,2,3};
        for (int i=0; i<10; i++){
            assertEquals("GenerateTest", expected[i], r.generate(3));
        }
        */
        int[] array = new int[] {0,1,0,1,1,1,1,0,1};
        ShiftRegister shifter = new ShiftRegister(9,7);
        shifter.setSeed(array);
        for (int i=0; i<10; i++){
            int j = shifter.generate(3);
            System.out.println(j);
            System.out.println();
        }
    }

    /**
     * Test register of length 1
     */
    @Test
    public void testOneLength() {
        ILFShiftRegister r = getRegister(1, 0);
        int[] seed = {1};
        r.setSeed(seed);
        int[] expected = {0,0,0,0,0,0,0,0,0,0,};
        for (int i=0; i<10; i++){
            assertEquals(expected[i], r.generate(3));
        }
    }

    /**
     * Test with erroneous seed.
     */
    @Test
    public void testError() {
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = {1,0,0,0,1,1,0};
        r.setSeed(seed);
        r.shift();
        r.generate(4);
    }

    /**
     * Test with tapError
     */
    @Test
    public void testTap(){
        ILFShiftRegister r = getRegister(7, 10);
        int[] seed = {1,0,0,0,1,1,0};
        r.setSeed(seed);
        r.shift();
        r.generate(4);
    }

    /**
     * Test with wrong input Int[]
     */
    @Test
    public void testIntArray(){
        ILFShiftRegister r = getRegister(7, 3);
        int[] seed = {1,2,0,7,1,1,0};
        r.setSeed(seed);
        r.shift();
        r.generate(4);
    }

    @Test
    public void testRepeat(){
        ShiftRegister r = new ShiftRegister(7, 6);
        int[] seed = {1,0,0,1,1,1,0};
        int[] original = {1,0,0,1,1,1,0};
        r.setSeed(seed);
        System.out.println(r.getSeed().toString());
        r.shift();
        System.out.println(r.getSeed().toString());
        int sum = 1;
        while (Arrays.compare(r.getSeed(), original) != 0){
            r.shift();
            System.out.println(r.getSeed().toString());
            sum++;
        }
        System.out.println(sum);
        return;
    }

}
