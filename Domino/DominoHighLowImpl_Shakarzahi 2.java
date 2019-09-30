package dominoes;


import java.util.Iterator;
import java.util.Set;

public class DominoHighLowImpl_Shakarzahi implements Domino{

	private int highPipCount;
	private int lowPipCount;
	
	
	//pre: highpipCount > lowPipCount
	public DominoHighLowImpl_Shakarzahi (int highPipCount, int lowPipCount){
		assert highPipCount >= lowPipCount;
		
		assert lowPipCount >= MINIMUM_PIP_COUNT && lowPipCount <= MAXIMUM_PIP_COUNT;
		
		assert highPipCount <= MAXIMUM_PIP_COUNT && highPipCount >= MINIMUM_PIP_COUNT;
		
		
		this.highPipCount = highPipCount;
		this.lowPipCount = lowPipCount;
	    
		
	}
	
	public static final char HIGH_LOW_STRING_SEPARATOR = ':';
	
	
	public static boolean isHighLowString (String str){
		boolean isHighLowString = false; 
		int high, low;
		int theSeparator = str.indexOf(HIGH_LOW_STRING_SEPARATOR);
		if ( theSeparator <0){
			isHighLowString = false;
		}
		
		if( str == null || str.isEmpty()){
			isHighLowString = false; 
		}
		else{
		
			
			
			String beforeSeparator = str.substring(0, theSeparator);
			String afterSeparator = str.substring(theSeparator +1, str.length());
			
			char[] beforeCharArray = beforeSeparator.toCharArray();
			boolean isNotIntBefore = false; 
			for (int i = 0; i < beforeCharArray.length; i ++){
				if (!Character.isDigit(beforeCharArray[i])){
					isNotIntBefore = true;
				}
			}
			
			char[] afterCharArray = afterSeparator.toCharArray();
			boolean isNotIntAfter = false; 
			for (int i = 0; i < afterCharArray.length; i ++){
				if (!Character.isDigit(afterCharArray[i])){
					isNotIntAfter = true;
				}
			}
			
			if (isNotIntBefore || isNotIntAfter){
				isHighLowString = false;
			}
			
			else{
			high = Integer.parseInt(beforeSeparator);
			low = Integer.parseInt(afterSeparator);
	
			isHighLowString = high >= low && high <= MAXIMUM_PIP_COUNT && high >= MINIMUM_PIP_COUNT && low <= MAXIMUM_PIP_COUNT && low >= MINIMUM_PIP_COUNT ;
			}
		}
		return isHighLowString;
	}
	
	//pre: must be a highLowString
	public DominoHighLowImpl_Shakarzahi(String highLowString){
		assert isHighLowString(highLowString)==true : "Not a high low string"; 
		
		int high,low;
		int theSeparator = highLowString.indexOf(HIGH_LOW_STRING_SEPARATOR);
		
		String beforeSeparator = highLowString.substring(0, theSeparator);
		String afterSeparator = highLowString.substring(theSeparator +1, highLowString.length());
		
		
		high = Integer.parseInt(beforeSeparator);
		low = Integer.parseInt(afterSeparator);
		
		highPipCount = high;
		lowPipCount = low;
		
		
		
	}
	
	public static final int INDEX_OF_SUM = 0;
	public static final int INDEX_OF_DIFFERENCE = 1; 
	
	
	//part of pre: sumDifference[INDEX_OF_SUM] >= sumDifference[INDEX_OFDIFFERENCE]
	//[sum,difference]
	public DominoHighLowImpl_Shakarzahi(int[] sumDifference){
		assert sumDifference[INDEX_OF_SUM] >= sumDifference[INDEX_OF_DIFFERENCE] : "Invalid sum difference";
		assert sumDifference[INDEX_OF_SUM] <= MAXIMUM_PIP_COUNT *2 &&  sumDifference[INDEX_OF_SUM] >= MINIMUM_PIP_COUNT : "Invalid sum!";
		assert sumDifference[INDEX_OF_DIFFERENCE] <= MAXIMUM_PIP_COUNT*2 &&sumDifference[INDEX_OF_DIFFERENCE] >= MINIMUM_PIP_COUNT : "Invalid difference";
	
		
		int sum = sumDifference[INDEX_OF_SUM];
		int difference = Math.abs(sumDifference[INDEX_OF_DIFFERENCE]);
		
		
		int theHighAnswer = -1;
		int theLowAnswer = -1;
		
		theHighAnswer = (sum + difference)/2;
		theLowAnswer = sum - theHighAnswer; 
		
		
		assert theHighAnswer< MAXIMUM_PIP_COUNT || theHighAnswer >MINIMUM_PIP_COUNT;
		assert theLowAnswer> MINIMUM_PIP_COUNT || theLowAnswer <MAXIMUM_PIP_COUNT || sumDifference.length <2;
		assert theHighAnswer >= theLowAnswer; 
		
		highPipCount = theHighAnswer;
		lowPipCount = theLowAnswer;
		
	}
	
	//part of pre: 1<= highLowSet.size() <= 2
	//part of pre: !highLowSet.contains(null)
	public DominoHighLowImpl_Shakarzahi(Set<Integer> highLowSet){
		assert highLowSet.isEmpty() != true : "Set is empty";
		assert highLowSet != null : "Set is null!";
		assert highLowSet .size() >0 : "Set is incorrecct size";
		assert !highLowSet.contains(null) : "Set contains null";
		assert highLowSet.size() <= 2 : "Set greater than size 2";
		assert highLowSet.size() >=1 : "Set less than size 1";
	
		
		int highLowSetElement1 = -1;
		int highLowSetElement2 = -1;
		
		Iterator<Integer> highLowSetIterator = highLowSet.iterator();
		
		if (highLowSet.size() ==1){
			while( highLowSetIterator.hasNext()){
				highLowSetElement1 = highLowSetIterator.next();
				highLowSetIterator.remove();
			}
			
			assert highLowSetElement1 >= MINIMUM_PIP_COUNT && highLowSetElement1 <= MAXIMUM_PIP_COUNT : "Set element not valid!";
			
			highPipCount = highLowSetElement1;
			lowPipCount = highLowSetElement1;
		}
		
		if (highLowSet.size() ==2){
			while(highLowSetIterator.hasNext()){
				highLowSetElement1 = highLowSetIterator.next();
				highLowSetIterator.remove();
				highLowSetElement2 = highLowSetIterator.next();
				highLowSetIterator.remove();
			}
			assert highLowSetElement1 >= MINIMUM_PIP_COUNT && highLowSetElement1 <= MAXIMUM_PIP_COUNT : "Set element" + highLowSetElement1+ " not valid!";
			assert highLowSetElement2 >= MINIMUM_PIP_COUNT && highLowSetElement2 <= MAXIMUM_PIP_COUNT : "Set element" + highLowSetElement2+ " not valid!";
			
			if (highLowSetElement1 > highLowSetElement2){
				highPipCount = highLowSetElement1;
			    lowPipCount = highLowSetElement2;
			}
			
			if (highLowSetElement1 < highLowSetElement2){
				highPipCount = highLowSetElement2;
				lowPipCount = highLowSetElement1;
			}
		}
		
	}

	
	public int getHighPipCount() {
		
		return highPipCount;
	}


	public int getLowPipCount() {
	
		return lowPipCount;
	}
	
	public String toString(){
	
		String temp= "";
		
		temp = "High Pip Count:" + highPipCount + "Low Pip Count:" + lowPipCount;
		
		return temp;
	}
	
}
