import java.util.Arrays;
import java.util.Collections;

public class Solution {


	public static int find (int[] array ) {

		//trier par ordre decroissant
		Integer[] integerArray = new Integer[array.length];
		for (int i=0; i < array.length; i++) {
			integerArray[i] = new Integer(array[i]);
		}
		Arrays.sort(integerArray, Collections.reverseOrder());


		int interv = integerArray [0]- integerArray [1];
		for (int i = 0 ; i< integerArray.length-1 ; i++) {

			int diff = integerArray [i]- integerArray [i+1];

			if (diff < interv && interv>0) {
				interv = diff ;
			}

		}


		return interv;
	}

	public static void main(String[] args) {
		int [] array = {1,6,4,8,-3};
		System.out.println(find(array));

	}

}
