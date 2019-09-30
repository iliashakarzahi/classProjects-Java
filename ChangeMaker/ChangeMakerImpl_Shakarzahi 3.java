package change;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ChangeMakerImpl_Shakarzahi implements ChangeMaker{
	
	private List<Integer> denominationsList;
	
	 
	//part of pre: i <= 0 ==> !denominations.contains(i)
	public ChangeMakerImpl_Shakarzahi(Set<Integer> denominations){
		
		assert denominations!= null;
		assert denominations.size() > 0;
		assert !denominations.contains(null);
		
		List<Integer> theDenominationsList = new ArrayList<Integer>(denominations);
		
		for ( int i = 0; i < theDenominationsList.size(); i++){
			assert theDenominationsList.get(i) > 0;
			
		}
		
		Collections.sort(theDenominationsList);
		Collections.reverse(theDenominationsList);
		
		
		denominationsList = theDenominationsList;
	}
	
	//part of pre: denominationsList.size> 0; 
	//part of pre: denominationsList.contains(!null);
	//part of pre: 
	//part of post: i in [0, rv.size() -1) ==> rv.get(i) . rv.get(i +1)
	public List<Integer> getDenominations() {
		assert denominationsList.size() > 0;
		assert denominationsList != null;
		assert !denominationsList.contains(null);
		
		return denominationsList;
	}

	//?
	// pre: denominationsList.size() > 0;
	// part of post: returns true if valueInCents is divisible by the last element in denominationsList
	//part of post: returns false if valueInCents is < 0
	//part of post: returns false if the last element in denominationsList is > valueInCents

	public boolean canMakeExactChange(int valueInCents) {
		boolean canMakeExactChange = true;
	
		int lastElementInList = denominationsList.get(denominationsList.size() -1);
	
		if (valueInCents < lastElementInList){
			canMakeExactChange = false;
		}
		
		if( valueInCents == 0){
			canMakeExactChange = true;
		}
		
		if (valueInCents < 0){
			canMakeExactChange = false; 
		}
		
		if ( denominationsList.size() ==1){
			int element = denominationsList.get(0);
			canMakeExactChange = element%valueInCents == 0;
		}
		
		
		
		if ( !denominationsList.contains(1)){
			canMakeExactChange = getRemainder(denominationsList,valueInCents) == 0;
		}

		else if ( oddNumInList(denominationsList)){
			canMakeExactChange = getRemainder(denominationsList,valueInCents)==0 ;
			
		}
		
		else if( isPrime(valueInCents) && !denominationsList.contains(valueInCents) ){
			canMakeExactChange = false;
		}
		return canMakeExactChange;
	}
	
	public static boolean isPrime(int num){
		boolean isPrime = true;
		if (num < 2) {
			isPrime= false;
		}
        if (num == 2) {
        	isPrime= true;
        }
        if (num % 2 == 0) {
        	isPrime= false;
        }
        for (int i = 3; i * i <= num; i += 2){
        	if (num % i == 0) {
        		isPrime= false;
        	}
        }
            
        return isPrime;
	}
	
	public static int getRemainder(List<Integer> list, int valueInCents){
		int remainder = -1;
		int element,integerDivision;
		
		for ( int i =0; i< list.size(); i++){
			element = list.get(i);
			integerDivision = valueInCents/element;
			remainder = valueInCents%element;
			valueInCents = remainder;
		}
		return remainder;
	}
	
	public static boolean oddNumInList(List<Integer> list){
		boolean oddNumInList = false;
		
		for (int i =0; i< list.size(); i++){
			if( list.get(i)%2 != 0){
				oddNumInList = true;
			}
		}
		
		return oddNumInList;
	}
	

	
	public static boolean isInteger(int k){
		boolean isInteger = (k%1 ==0) ;
		return isInteger;
	}

	//part of pre denominationsList.size > 0
	//part of pre: canMakeExactChange(valueInCents) == true
	//part of post: calculateValueOfChangeList(rv) == valueInCents
	//part of post: i in [0, rv.size() -1) ==> getDenominations.get(i) > rv.get(i+1)*getDenominations.get(i+1)
	public List<Integer> getExactChange(int valueInCents) {
		assert denominationsList.size() > 0;
		assert canMakeExactChange(valueInCents);
		
		List<Integer> changeList = new ArrayList<Integer>();
		
		int integerDivision, remainder, denomination; 
		
		for ( int i = 0; i < denominationsList.size(); i ++){
			denomination =denominationsList.get(i); 
			integerDivision = valueInCents/denomination;
			changeList.add(integerDivision);
			
			if ( integerDivision > 0){
				remainder = valueInCents%denomination;
				valueInCents = remainder;
			}
		}
		
		return changeList;
	}

	// part of pre: changeList.size() == getDenominations().size()
	//part of pre: SIZE = changeList.size() [NOTE: purely for notation]
	//part of post: int final value 
	public int calculateValueOfChangeList(List<Integer> changeList) {
		assert changeList.size() == getDenominations().size();
		
		assert !denominationsList.isEmpty();
		assert denominationsList != null;
		assert !denominationsList.contains(null);
		
		assert !changeList.isEmpty();
		assert changeList != null;
		assert !changeList.contains(null);
		
		
		int finalValue=0;
		int changeAmount, denomination;
		
		for( int i = 0; i < changeList.size(); i++){
			changeAmount = changeList.get(i);
			denomination = denominationsList.get(i);
			finalValue += changeAmount*denomination;
			
		}
		
		
		
		return finalValue;
	}
	
public String toString(){
		
		String temp= "";
		
		temp = "Denominations " +denominationsList;
		
		return temp;
	}
	
}
