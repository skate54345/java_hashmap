
public class HashMapMain
{
	ISet<String> map;
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
		HashMap<Integer, Integer> myMap = new HashMap(100000);

        for(int i = 1; i < 100000; i++) //adds items
        {
            myMap.put(i, i+2); //adds items to list
        }
        startTime(); //starts time
        for(int i = 1; i < 100000; i++) //iterates through items in list
        {
        	myMap.put(i,1+2); //gets item from tree
        }
        System.out.println("HashMap of size 100,000: " + endTime() + " nanoseconds"); //ends time, prints result


        HashMap<Integer, Integer> myMap2 = new HashMap(200000);

        for(int i = 1; i < 200000; i++) //adds items
        {
            myMap2.put(i, i+2); //adds items to list
        }
        startTime(); //starts time
        for(int i = 1; i < 200000; i++) //iterates through items in list
        {
        	myMap2.put(i,1+2); //gets item from tree
        }
        System.out.println("HashMap of size 200,000: " + endTime() + " nanoseconds"); //ends time, prints result


        HashMap<Integer, Integer> myMap3 = new HashMap(300000);

        for(int i = 1; i < 300000; i++) //adds items
        {
            myMap.put(i, i+2); //adds items to list
        }
        startTime(); //starts time
        for(int i = 1; i < 300000; i++) //iterates through items in list
        {
        	myMap.put(i,1+2); //gets item from tree
        }
        System.out.println("HashMap of size 300,000: " + endTime() + " nanoseconds\n"); //ends time, prints result


        HashMap<Integer, Integer> myMap4 = new HashMap(300110);

        for(int i = 1; i < 100000; i++) //adds items
        {
            myMap4.put(i, i+2); //adds items to list
        }
        startTime(); //starts time
        for(int i = 1; i < 100000; i++) //iterates through items in list
        {
        	myMap4.put(i,1+2); //gets item from tree
        }
        System.out.println("HashMap of size 100,000: " + endTime() + " nanoseconds"); //ends time, prints result


        HashMap<Integer, Integer> myMap5 = new HashMap(300110);

        for(int i = 1; i < 200000; i++) //adds items
        {
            myMap5.put(i, i+2); //adds items to list
        }
        startTime(); //starts time
        for(int i = 1; i < 200000; i++) //iterates through items in list
        {
        	myMap5.put(i,1+2); //gets item from tree
        }
        System.out.println("HashMap of size 200,000: " + endTime() + " nanoseconds"); //ends time, prints result


        HashMap<Integer, Integer> myMap6 = new HashMap(300110);

        for(int i = 1; i < 300000; i++) //adds items
        {
            myMap6.put(i, i+2); //adds items to list
        }
        startTime(); //starts time
        for(int i = 1; i < 300000; i++) //iterates through items in list
        {
        	myMap6.put(i,1+2); //gets item from tree
        }
        System.out.println("HashMap of size 300,000: " + endTime() + " nanoseconds"); //ends time, prints result
	}
}
