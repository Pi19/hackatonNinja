
public class Solution {

	static int  getNextNumber(int s) {
		int  sumDigitsInNumber = 0;
		String toStringS = s + "";
		for(int i=0; i<toStringS.length(); i++) {
			sumDigitsInNumber += Integer.parseInt(String.valueOf(toStringS.charAt(i)));
		}
		return s + sumDigitsInNumber;
	}


	static int  computeJoinPoint(int nb1,int nb2) {

		if(nb1 <= 0 || nb2 <= 0 || nb1 >= 20000000 || nb2 >= 20000000){
			return 0;
		}

		while(nb1 != nb2) {
			if(nb1 < nb2){
				nb1 = getNextNumber(nb1);
			}else{
				nb2 = getNextNumber(nb2);
			}
		}

		return nb1;
	}


}
