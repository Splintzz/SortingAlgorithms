public class SortingTester
{
	public static void main(String[] args)
	{
		SortingAlgorithms sorter = new SortingAlgorithms(generateRandomArray(10, 20), 20);
		sorter.printArray();
		long startTime = System.nanoTime();
		//insert sort here
		long endTime = System.nanoTime();
		System.out.println();
		sorter.printArray();
		System.out.println("\n\nThe sort took "+(endTime - startTime)/1_000_000_000.0+" seconds");
	}
	
	/**
	 * Produces a random array of an size amount of values ranging from
	 * 0 - range.
	 * @param size - amount of values that are to be generated.
	 * @param range - range of the values that are being generated from 0 - range.
	 * @return the produced array.
	 */
	private static Integer[] generateRandomArray(int size, int range)
	{
		Integer[] a = new Integer[size];	//array to be returned
		
		for(int i=0;i<size;i++)
		{//from 0 to the size specified
			int rand = (int)(Math.random()*range);	//produce random variable from 0 - range
			a[i] = rand; 							//put the randomly generated value into the array
		}
		
		return a;	//return the array
	}
}