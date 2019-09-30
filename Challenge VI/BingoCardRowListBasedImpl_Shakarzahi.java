package bingo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BingoCardRowListBasedImpl_Shakarzahi extends BingoCardRowListBased_Abstract{
	
	public BingoCardRowListBasedImpl_Shakarzahi(Integer[][] entries){
		rowListList = new ArrayList<List<Integer>>();
	
	    for(int i = 0; i < entries.length; i++){
	    	Integer[] array = entries[i];
	    	List<Integer> set = new ArrayList<Integer>(Arrays.asList(array));
	        rowListList.add(i,set);
	    }
		
		integersMarked = new HashSet<Integer>();
		
		integersMarked.add(FREE_SPACE);
		
	}
	
	public BingoCardRowListBasedImpl_Shakarzahi(Set<Integer> row1, Set<Integer> row2,Set<Integer> row3, Set<Integer> row4, Set<Integer> row5 ){
		assert !checkForDuplicates(row1,row2);
		assert !checkForDuplicates(row1,row3);
		assert !checkForDuplicates(row1,row4);
		assert !checkForDuplicates(row1,row5);
		assert !checkForDuplicates(row2,row3);
		assert !checkForDuplicates(row2,row4);
		assert !checkForDuplicates(row2,row5);
		assert !checkForDuplicates(row3,row4);
		assert !checkForDuplicates(row3,row5);
		assert !checkForDuplicates(row4,row5);
		rowListList = new ArrayList<List<Integer>>();
		
		List<Integer> row11 = new ArrayList<Integer>(row1);
		List<Integer> row22 = new ArrayList<Integer>(row2);
		List<Integer> row33 = new ArrayList<Integer>(row3);
		List<Integer> row44 = new ArrayList<Integer>(row4);
		List<Integer> row55 = new ArrayList<Integer>(row5);
		
		rowListList.add(0,row11);
		rowListList.add(1,row22);
		rowListList.add(2,row33);
		rowListList.add(3,row44);
		rowListList.add(4,row55);
		
		
		integersMarked = new HashSet<Integer>();
		integersMarked.add(FREE_SPACE);
	}
	
	private boolean checkForDuplicates(Set<Integer> set1, Set<Integer> set2 ){
		boolean checkForDuplicates = false;
		Set<Integer> checkSet = new HashSet<Integer>();
		
		checkSet.addAll(set1);
		checkSet.addAll(set2);
		
		int expectedSize = set1.size() + set2.size();
		
		if( checkSet.size() != expectedSize){
			checkForDuplicates= true;
		}
		/*for (int i = 0; i < setList.size(); i ++){
			Set<Integer> set = new HashSet<Integer>(setList.get(i));
			checkSet.addAll(set);
		}
		
		if (checkSet.size() != setList.size()){
			checkForDuplicates = true;
		}*/
		
		return checkForDuplicates;
	}

	//5
	public Integer getEntry(int row, int column) {
		assert row > 0 && row <= ROW_COUNT;
		assert column > 0 && column <= COLUMN_COUNT;
		
		Integer returnValue =-1;
		Integer listElement;
		Integer listElement2 = -1;
		int checkColumn;
		
		//if ( row != FREE_SPACE_ROW){
			
	
			List<Integer> rowList1 = rowListList.get(row-1);
			
			for ( int i = 0; i< rowList1.size(); i++){
				listElement = rowList1.get(i);
			
				checkColumn = getColumn(listElement);
				if( checkColumn == column){
					returnValue = listElement;
				
				}
			
		}
			if (column == FREE_SPACE_COLUMN){
				returnValue = FREE_SPACE;
			}
		//}	
		/*
		if ( row == FREE_SPACE_ROW){
		List<Integer> rowList2 = rowListList.get(FREE_SPACE_ROW -1);
		
		for ( int i = 0; i < rowList2.size(); i++)
			listElement2 = rowList2.get(i);
			checkColumn = getColumn(listElement2);
			if( checkColumn == column){
				returnValue = listElement2;
			}
			
			if (column == FREE_SPACE_COLUMN){
				returnValue = FREE_SPACE;
			}
		}*/
		assert returnValue != -1;
		return returnValue;
	}
	


	
	public void mark(int number) {
	assert number > 0 && number <= 75;
		
		if (contains(number)){
			integersMarked.add(number);
		}
	}

	public static int getColumn (int num){
		
		int column = -1;
		
		if ( num%15 == 0){
			column = num/15;
		}
		
		if ( num%15 != 0){
			column = (num/15) +1;
		}
		
		return column;
	}

	public boolean contains(int number) {
		assert number > 0 && number <= 75;
		boolean contains = false;
		
		int column = getColumn(number);
		
		for ( int i = 0; i < ROW_COUNT; i++){
			
			if ( getEntry(i+1,column) == number){
				contains = true;
			}
		}
		
		if ( number == FREE_SPACE){
			contains = true; 
		}
		
		return contains;
	}


	public boolean isMarked(int row, int column) {
		assert row > 0 && row <= ROW_COUNT;
		assert column >0 && column <= COLUMN_COUNT;
		boolean isMarked = false;
		
		int entry = getEntry(row,column);
		
		if(integersMarked.contains(entry)){
			isMarked = true;
		}
		
		
		return isMarked;
	}

	
	public boolean isWinner() {
		boolean isWinner = false;
		
		if( isColumnWinner()){
			isWinner = true;
		}
		
		if(isRowWinner()){
			isWinner = true;
		}
		
		if(isDiagonalWinner()){
			isWinner = true;
		}
		
		return isWinner;
	}

	private boolean isColumnWinner(){
		boolean isColumnWinner = false;
		Set<Integer> columnWinnerSet = new HashSet<Integer>();
		
		for ( int i = 0; i < ROW_COUNT; i++){
			
			for (int j= 0; j < COLUMN_COUNT; j++){
				if ( isMarked(i+1,j+1)){
					Integer entry = getEntry(i+1,j+1);
					columnWinnerSet.add(entry);
				}
			}
			if (columnWinnerSet.size() ==5){
				isColumnWinner = true;
			}
			columnWinnerSet.clear();
		}
		
		
		return isColumnWinner;
	}
	
	private boolean isRowWinner(){
		boolean isRowWinner = false;
		Set<Integer> rowWinnerSet = new HashSet<Integer>();
		
		for ( int i = 0; i < COLUMN_COUNT; i++){
			
			for (int j= 0; j < ROW_COUNT; j++){
				if ( isMarked(i+1,j+1)){
					Integer entry = getEntry(i+1,j+1);
					rowWinnerSet.add(entry);
				}
			}
			if (rowWinnerSet.size() ==5){
				isRowWinner = true;
			}
			rowWinnerSet.clear();
		}	
		
		
		return isRowWinner;
	}
	
	private boolean isDiagonalWinner(){
		boolean isDiagonalWinner = false;
		
		Set<Integer> diagonalWinnerSet1 = new HashSet<Integer>();
		Set<Integer> diagonalWinnerSet2 = new HashSet<Integer>();
		if ( isMarked(1,1) ){
		for ( int i = 0; i < COLUMN_COUNT; i++){
			if ( isMarked(i+1,i+1)){
				Integer entry = getEntry(i+1,i+1);
				diagonalWinnerSet1.add(entry);
			}
		}
		
		if (diagonalWinnerSet1.size() == 5){
			isDiagonalWinner = true;
		}
		
	}
	
	if (isMarked(1,ROW_COUNT) ){
		for ( int i = 0; i < ROW_COUNT; i++){
			
			for (int j= COLUMN_COUNT; j > 0; j--){
				if ( isMarked(i+1,j)){
					Integer entry = getEntry(i+1,j+1);
					diagonalWinnerSet2.add(entry);
				}
			}
			if (diagonalWinnerSet2.size() ==5){
				isDiagonalWinner = true;
			}
		}	
		}
		return isDiagonalWinner;
	}
	
	public String toString()
	{
		
		String temp = "\n" ;
		
		
		
		for ( int i = 0; i < ROW_COUNT; i++){
			temp += "[ ";
			for (int j= 0; j < COLUMN_COUNT; j++){
				
				if (i != FREE_SPACE_ROW -1 && j != FREE_SPACE_COLUMN -1 ){
				Integer entry = getEntry(i+1,j+1);
				
				temp += " " + entry +" ";
				}
				
			}
			temp += " ] \n";
		}
		
		/*temp += "Integers Marked: [ ";
		for ( int i = 0; i < ROW_COUNT; i++){
			
			for (int j= 0; j < COLUMN_COUNT; j++){
				if (isMarked(i+1,j+1)){
				Integer markedEntry = getEntry(i+1,j+1);
				if ( markedEntry == FREE_SPACE){
					temp+= "FS ";
				}
				else{
				temp += " " + markedEntry +" ";
				} 
				}

			}*/
		
		
		//}
		temp += "]";
		
		return temp;
	}
}

