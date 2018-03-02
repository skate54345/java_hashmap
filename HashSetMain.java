import java.math.BigInteger; //imports necessary packages
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Random;

public class HashSetMain
{
	ISet<String> set;
	static long StartTime; //initializes startTime variable


	public static void startTime() //start time method
	{
		StartTime = System.nanoTime(); //gets current time down to  milliseconds
	}


	public static long endTime() //end time method
	{
		long EndTime = System.nanoTime(); //gets current time and stores in variable
		long difference = EndTime - StartTime; //gets difference between 2 times
		return difference; //returns difference
	}

	public static void main(String[] args)
	{
		HashSet<Integer> mySet = new HashSet(300110);

        for(int i = 1; i < 10000; i++) //adds items to array
        {
            mySet.add(i); //adds items to list
        }
        startTime(); //starts time
        for(int i = 1; i < 10000; i++) //iterates through items in list
        {
        	mySet.has(i); //gets item from tree
        }
        System.out.println("HashSet of size 100,000: " + endTime() + " nanoseconds"); //ends time, prints result


        HashSet<Integer> mySet2 = new HashSet(300110);

        for(int i = 1; i < 20000; i++) //adds items to array
        {
            mySet2.add(i); //adds items to list
        }
        startTime(); //starts time
        for(int i = 1; i < 20000; i++) //iterates through items in list
        {
        	mySet2.has(i); //gets item from tree
        }
        System.out.println("HashSet of size 200,000: " + endTime() + " nanoseconds"); //ends time, prints result


        HashSet<Integer> mySet3 = new HashSet(300110);

        for(int i = 1; i < 30000; i++) //adds items to array
        {
            mySet3.add(i); //adds items to list
        }
        startTime(); //starts time
        for(int i = 1; i < 30000; i++) //iterates through items in list
        {
        	mySet3.has(i); //gets item from tree
        }
        System.out.println("HashSet of size 300,000: " + endTime() + " nanoseconds"); //ends time, prints result
	}
}
