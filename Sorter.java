
public class Sorter {
	private int count;
	
	public int[] bubbleSort(int[] array)
	{
		count = 0;
		boolean swapped = true;
		int temp;
		while (swapped == true) 
		{//runs through until nothing in the array has been changed
			swapped = false;
			for(int i = 1; i < array.length; i++)
			{
				if (array[i-1] > array[i])
				{//performs swap and sets swapped to true
					temp = array[i];
					array[i] = array[i-1];
					array[i-1] = temp;
					swapped = true;
				}
				count++;
			}
		}
		//returns the sorted array
		return array;
	}
	
	public int[] selectionSort(int[] array)
	{
		int current = 0;
		while(current < array.length)//iterates for each position
		{
			int pos = current;
			int lowest = array[current];
			for(int i = current; i < array.length; i++)//starts at the 'current' position
			{
				if (array[i] < lowest)//checks what number is the lowest
				{
					lowest = array[i];
					pos = i;//where the lowest number is
				}
			}
			//does the swap
			int temp = array[current];
			array[current] = lowest;
			array[pos] = temp;
			current++;
		}
		return array;
	}
	
	public int[] insertionSort(int[] array)
	{
		int swap;
		for(int i = 0; i < array.length-1; i++)//first loop
		{
			if(array[i] > array[i+1])
			{//performs swap
				swap = array[i];
				array[i] = array[i+1];
				array[i+1] = swap;
				for(int j = i; j > 0; j--)//second loop
				{//has to check everything prior to the swap that just happened
					if(array[j-1] > array[j])
					{//swaps if necessary
						swap = array[j-1];
						array[j-1] = array[j];
						array[j] = swap;
					}
				}
			}
		}
		return array;
	}
	
	public int[] mergeSort(int[] array)
	{
		if (array.length == 1)//array length is 1
		{
			return array;
		}
		int[]left;
		int[]right;
		if(array.length%2 == 0)//if array length is even
		{
			left = new int[array.length/2]; //first half
			right = new int[array.length/2]; //second half
		}
		else //array length is odd
		{
			left = new int[array.length/2]; //first half
			right = new int[(array.length/2) + 1]; //second half
		}
		
		for (int i = 0; i < array.length/2; i++) //populate new arrays with previous values
		{
			left[i] = array[i];
			right[i] = array[i + (array.length/2)];
			if(array.length%2 == 1 && i <= right.length)
			{
				right[i+1] = array[i+1 +(array.length/2)];
			}
		}
		left = mergeSort(left); //put the left through, sorts them
		right = mergeSort(right); //put the right through, sorts them
		
		array = actuallyMerge(array, left, right);
		
		return array;
	}
	private int[] actuallyMerge(int[]array, int[]left, int[]right)
	{
		int leftPos = 0; //left pos is starting at 1
		int rightPos = 0; //right pos is starting at 1
		for(int i = 0; i < array.length; i++) //the sorting part
		{
			if(rightPos < right.length && leftPos == left.length)
				//inserts the rest of the right values if the left is done
			{
				array[i] = right[rightPos];
				rightPos++;
			}
			if(leftPos < left.length && rightPos == right.length)
				//inserts the rest of the left values if the right is done
			{
				array[i] = left[leftPos];
				leftPos++;
			}
			if(rightPos < right.length&& leftPos < left.length) //makes sure there won't be an out of bounds
			{
				if (right[rightPos] < left[leftPos])//right is smaller
				{
					array[i] = right[rightPos];
					rightPos++;
				}
				else if(right[rightPos] > left[leftPos])//left is smaller
				{
					array[i] = left[leftPos];
					leftPos++;
				}
				else if(right[rightPos] == left[leftPos])//they're equal
				{
					array[i] = right[rightPos];
					i++;
					rightPos++;
					array[i] = left[leftPos];
					leftPos++;
				}
			}
		}
		return array;
	}
	
	public int[] shellSort (int[] array)
	{//gap values sourced from Wikipedia article on Shellsort, uses Ciura's gap sequence
		int currentGap;//for readability
		int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};
		for(int i = 0; i < gaps.length; i++)
		{//goes through each gap value
			currentGap = gaps[i];
			for(int j = 0; j + currentGap < array.length; j++)
			{
				if(array[j] > array[currentGap+j])
				{//knows that a swap needs to happen
					int temp = array[j];
					array[j] = array[j + currentGap];
					array[j + currentGap] = temp;
					int backGap = j-currentGap;
					while(backGap >= 0)
					{//goes backwards through the gaps
						if(array[backGap] > array[backGap + currentGap])
						{//swaps under the condition stated
							temp = array[backGap];
							array[backGap] = array[backGap + currentGap];
							array[backGap + currentGap] = temp;
						}
						//goes backwards further
						backGap -= currentGap;
						}
					}
				}
			}
		return array;
	}
	
	public int[] quickSort(int[] array)
	{//for this sorting algorithm, I chose the last element as the pivot
		//info found on www.geeksforgeeks.org/quick-sort/
		int pivot = array.length - 1;
		if(pivot < 1)
		{//if the array is too small to sort
			return array;
		}
		int nextIndex = 0;
		for(int i = 0; i < array.length - 1; i++)
		{//goes through array up until the pivot
			if(array[i] < array[pivot])
			{//swap occurs if the value is smaller than the pivot
				int temp = array[i];
				array[i] = array[nextIndex];
				array[nextIndex] = temp;
				nextIndex++;
			}
		}
		if(array[nextIndex] > array[pivot])
		{//if the last number changed is larger than the pivot
			int temp = array[pivot];
			array[pivot] = array[nextIndex];
			array[nextIndex] = temp;
			pivot = nextIndex;
		}
		int[] smaller = new int[pivot];
		int[] bigger = new int[array.length - pivot -1];
		//split into two arrays
		for(int i = 0; i < pivot; i++)
		{//array filled with values that are smaller than pivot
			smaller[i] = array[i];
		}
		for(int i = 0; i < array.length - pivot -1; i++)
		{//array filled with values that are larger than pivot
			bigger[i] = array[i + pivot +1];
		}
		//recursion
		smaller = quickSort(smaller);
		bigger = quickSort(bigger);
		//a combine method to put everything together
		array = combineQuickSort(array[pivot], smaller, bigger);
		return array;
	}
	private int[] combineQuickSort (int pivotValue, int[] smaller, int[] bigger)
	{//the combine method for quick sort
		int fullSize = smaller.length + bigger.length + 1;
		int[] fullArray = new int[fullSize];
		for(int i = 0; i < smaller.length; i++)
		{//fills the first part of the array with values smaller than pivot
			fullArray[i] = smaller[i];
		}
		//pivot goes next
		fullArray[smaller.length] = pivotValue;
		for(int i = 0; i < bigger.length; i++)
		{//fills the rest of the array with values larger than pivot
			fullArray[i+smaller.length+1] = bigger[i];
		}
		//returns the whole array
		return fullArray;
	}
	
	public int[] heapSort(int[] array)
	{//array gets treated as a heap
		//info found on https://www.programiz.com/dsa/heap-data-structure and https://www.geeksforgeeks.org/heap-sort/
		int ordered = 1;//how many elements are correctly ordered
		//initial heapify
		int start = array.length/2 -1;//starting point for heapify
		for(int i = start; i >= 0; i--)
		{//increments down until it hits the beginning of the array, after which everything is in order
			array = heapify(array, array.length, i);
		}
		while(ordered < array.length)
		{//goes through ordering the rest of the array, swapping the first and last 'unordered' elements
			array = heapify(array, array.length-ordered, 0);
			int temp = array[0];
			array[0] = array[array.length-ordered];
			array[array.length-ordered] = temp;
			ordered++;//increases the number of ordered elements
		}
		return array;
	}
	private int[] heapify(int[] array, int maxIndex, int root)
	{//rearranges array in heap order
		int largest = root;//assumes the root to be the largest
		int left = 2*root + 1;//left child's index has to be at least[1]
		int right = 2*root +2;//right child's index has to be at least[2]
		
		if(left < maxIndex && array[left] > array[largest])
		{//changes the largest to the left
			largest = left;
		}
		if(right < maxIndex && array[right] > array[largest])
		{//changes the largest to the right
			largest = right;
		}
		if(largest != root)
		{//if the two need to be swapped
			int temp = array[root];
			array[root] = array[largest];
			array[largest] = temp;
			root = largest; //largest is now the new root
			//recursion to reorder the rest
			array = heapify(array, maxIndex, root);
		}
		return array;
	}
}
