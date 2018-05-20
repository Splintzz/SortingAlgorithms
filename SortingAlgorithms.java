public class SortingAlgorithms
{	
	private Integer[] unsortedArray;
	private int rangeOfValues;
	
	public SortingAlgorithms(Integer[] unsortedArray, int rangeOfValues)
	{
		this.unsortedArray = unsortedArray;
		this.rangeOfValues = rangeOfValues;
	}
	
	/**
	 * Bubble sort - goes though each values of array and swaps to make sure the end value is 
	 * the biggest value each time.
	 * Time complexity : O(n^2)
	 */
	public void bubbleSort()
	{
		for(int i=1;i<=unsortedArray.length;i++)
		{
			for(int j=0;j<(unsortedArray.length - i);j++)
			{
				if(unsortedArray[j] > unsortedArray[j+1])
				{
					swap(unsortedArray, j, j+1);
				}
			}
		}
	}
	
	/**
	 * The quick sort the main class will call.
	 */
	public void quickSort()
	{
		quickSort(0, unsortedArray.length-1, unsortedArray);
	}
	
	/**
	 * Quick sort - pick a pivot position and partition the array around that pivot.
	 * Then dump all the values less than the pivot on the left side of the pivot, and the 
	 * rest of the values on the right. Keep doing this recursively till the array is sorted.
	 * Time complexity : O(nlgn)
	 * @param left - the left most index of the array.
	 * @param right - the right most index of the array.
	 * @param array - the array being sorted.
	 */
	private void quickSort(int left, int right, Integer[] array)
	{
		if(left < right)
		{
			int partitionIndex = partition(array, left, right);
			quickSort(left, partitionIndex - 1, array);
			quickSort(partitionIndex + 1, right, array);
		}
	}
	
	/**
	 * Partitions an array around a pivot, and dumps values on the correct
	 * side of the partitioned array.
	 * @param array - the array being partitioned.
	 * @param left - the left most index of the array.
	 * @param right - the right most index of the array.
	 * @return the index at which the array was partitioned. 
	 */
	private int partition(Integer[] array, int left, int right)
	{
		int pivot = array[right];	//pivot position
		int smaller = left - 1;		//smaller value index
		
		for(int larger = left; larger < right; larger++)
		{//loop through and compare values on each side of partition
			if(array[larger] <= pivot)
			{
				smaller++;
				swap(array, larger, smaller);
			}
		}
		
		swap(array, smaller+1, right);
		
		return smaller + 1;
	}

	/**
	 * Map sort - takes in an array OF INTEGERS WITH NO DUPLICATE VALUES, and maps values to indices.
	 * Horrible space complexity.
	 * Time complexity : O(n+n) = O(2n) = O(n)
	 * @return the sorted array.
	 */
	public Integer[] mapSort()
	{
		assert unsortedArray.length > 0;
		Integer[] mapArray = new Integer[rangeOfValues+1];		
		int index;
		Integer sortedValue;

		//mapping elements
		for(index = 0; index< unsortedArray.length; index++)
		{
			mapArray[unsortedArray[index]] = unsortedArray[index];	//Put values in their index
																		//Ex: 4 -> sortedArray[4]
		}
		
		//mapArray is considered sorted, but has lots of empty space
		
		//cutting nulls
		for(int sortedIndex = 0, unsortedIndex = 0; sortedIndex < mapArray.length; sortedIndex++)
		{
			sortedValue = mapArray[sortedIndex];	//value of sorted value
			
			if(sortedValue != null)
			{//if the value is null, its unwanted
				unsortedArray[unsortedIndex] = sortedValue;//put values back in array that aren't null
			}
		}
		return unsortedArray;	//which is now sorted
	}
	
	/**
	 * The merge sort method that the main class will call.
	 */
	public void mergeSort()
	{
		unsortedArray = mergeSort(unsortedArray);
	}
	
	/**
	 * Merge sort - takes an array of integers, and cuts the array down to smaller
	 * pieces. Then works back up combining the small pieces into a sorted whole.
	 * Time complexity : O(nlgn)
	 * @param unsortedArray - the array being sorted.
	 * @return the sorted array.
	 */
	private Integer[] mergeSort(Integer[] unsortedArray)
	{
		if(unsortedArray.length < 2)
		{
			return unsortedArray;
		}//base case : the array has 1 or no elements.
		
		Integer[] firstHalf = new Integer[unsortedArray.length/2];					//first section of array
		Integer[] secondHalf = new Integer[unsortedArray.length-firstHalf.length];	//second section of array
		Integer[] combined = new Integer[unsortedArray.length];						//combines the two halves
		int unsortedIndex = 0;
		
		for(int firstIndex = 0; firstIndex < firstHalf.length; firstIndex++, unsortedIndex++)
		{//put the first half of values into the first half array
			firstHalf[firstIndex] = unsortedArray[unsortedIndex];
		}
		
		for(int secondIndex = 0; secondIndex < secondHalf.length; secondIndex++, unsortedIndex++)
		{//put the second half od values into the second half array
			secondHalf[secondIndex] = unsortedArray[unsortedIndex];
		}
		
		firstHalf = mergeSort(firstHalf);			//sort the first half
		secondHalf = mergeSort(secondHalf);			//sort the second half
		combined = merge(firstHalf, secondHalf);	//merge the two sorted halves
		
		return combined;		//return the sorted combine
		
	}
	
	/**
	 * Merges and sorts the two halves given.
	 * @param firstHalf - the first half of the soon to be whole array.
	 * @param secondHalf - the second half of the soon to be whole array.
	 * @return the merged sorted array.
	 */
	private Integer[] merge(Integer[] firstHalf, Integer[] secondHalf)
	{
		Integer[] combined = new Integer[firstHalf.length + secondHalf.length];	//combined version of the 2 halves
			
		int firstIndex = 0;			//index pointer of first half array 
		int secondIndex = 0;		//index pointer of second half array
		int combineIndex = 0;		//index pointer of combined array
		
		while((firstIndex < firstHalf.length) || (secondIndex < secondHalf.length))
		{//while there are still elements to compare
			if((firstIndex < firstHalf.length) && (secondIndex < secondHalf.length))
			{//if BOTH halves still have elements to compare
				if(firstHalf[firstIndex] <= secondHalf[secondIndex])
				{//if first half element is less than or equal to right element 
					combined[combineIndex] = firstHalf[firstIndex];		//put that element in the combine array
					firstIndex++;	//point the first half pointer to the next index in first half array
				}
				
				else
				{//if second half element is less than or equal to right element 
					combined[combineIndex] = secondHalf[secondIndex];	//put that element in the combine array
					secondIndex++;	//point the second half pointer to the next index in first half array
				}
				combineIndex++;	//point the combine pointer to the next index so the next element can be added
			}
			
			else if(firstIndex < firstHalf.length)
			{//the second half has no more elements but the first half does
				while(firstIndex < firstHalf.length)
				{//loop through the rest of the first half 
					combined[combineIndex] = firstHalf[firstIndex];		//add the elements into the combine array
					firstIndex++;	//point the first half pointer to the next index in first half array
					combineIndex++;	//point the combine pointer to the next index so the next element can be added
				}
			}
			
			else
			{//the first half has no more elements but the second half does
				while(secondIndex < secondHalf.length)
				{//loop through the rest of the second half 
					combined[combineIndex] = secondHalf[secondIndex];	//add the elements into the combine array
					secondIndex++;	//point the second half pointer to the next index in first half array
					combineIndex++;	//point the combine pointer to the next index so the next element can be added
				}
			}
		}
		return combined;	//return the combined array
	}
	
	/**
	 * Selection sort - find the smallest element and swap it with the front element of the array.
	 * Time complexity : O(n^2)
	 */
	public void selectionSort()
	{
		int smallest;
		
		for(int i=0;i<unsortedArray.length;i++)
		{
			smallest = i;//reset the smallest 
			for(int j=i;j<unsortedArray.length;j++)
			{
				smallest = unsortedArray[j] < unsortedArray[smallest] ? j : smallest;	
				//if the current element is smaller than the smallest, it is the new smallest.
			}
			
			swap(unsortedArray,i, smallest);
		}
	}
	
	
	/**
	 * Swaps two values in an array.
	 * @param a - array with the two values to be swapped
	 * @param index1 - the index of the first element to be swapped.
	 * @param index2 - the index of the second element to be swapped.
	 */
	public void swap(Integer[] array, int index1, int index2)
	{
		Integer temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	/**
	 * Prints the array.
	 */
	public void printArray()
	{
		for(Integer e : unsortedArray)
		{
			System.out.print(e+"|");
		}
	}

}