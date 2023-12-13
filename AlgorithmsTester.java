import java.util.Random;
public class AlgorithmsTester {
	public static void main(String[]args)
	{
		Random rand = new Random();//random for generating values
		Sorter sort = new Sorter();//the sorter, which has all the sorting algorithms needed
		int minimum = 10000;//the minimum number of elements for the array to have
		int maximum = 200000;//the maximum number of elements for the array to have
		int increment = 10000;//the increment amount
		int randomMaximum = 500000;//maximum value for the random numbers to be
		//whichever algorithm is being used is typed in
		for(int i = minimum; i <= maximum; i += increment)
		{
			int[] array = new int[i];
			for(int j = 0; j < i; j++)
			{//populates array with random integers
				array[j] = rand.nextInt(randomMaximum);
			}
			//calculates time taken
			long time = System.currentTimeMillis();
			array = sort.shellSort(array);//this line is changed to determine which algorithm to use
			long timeTaken = System.currentTimeMillis() - time;
			double timeSeconds = timeTaken;//to get time in seconds, which I prefer for readability
			timeSeconds/=1000;
			System.out.println("Entries: " + i + " Time: " + timeSeconds + "seconds");//prints out the amount of time taken for each sorting algorithm
		}
	}
}
