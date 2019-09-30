package bingo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BingoCardColumnListBasedImpl_Shakarzahi extends BingoCardColumnListBased_Abstract{

	public BingoCardColumnListBasedImpl_Shakarzahi(Integer[][] entries){
		columnListList = new ArrayList<List<Integer>>();
	
	    for(int i = 0; i < entries.length; i++){
	    	Integer[] array = entries[i];
	    	List<Integer> set = new ArrayList<Integer>(Arrays.asList(array));
	        columnListList.add(i,set);
	    }
		
		integersMarked = new HashSet<Integer>();
		
		integersMarked.add(FREE_SPACE);
		
	}
	
	public Integer getEntry(int row, int column) {
		assert row > 0 && row <= ROW_COUNT;
		assert column > 0 && column <= COLUMN_COUNT;
		
		Integer entry = -1;
		
		List<Integer> columnList = columnListList.get(column-1);
		
		entry = columnList.get(row-1);
		
		
		return entry;
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
	
		return checkForDuplicates;
	}

	
	public void mark(int number) {
		assert number > 0 && number <= 75;
		
		integersMarked.add(FREE_SPACE);
		if (contains(number)){
			integersMarked.add(number);
		}
		
		
	}

	
	public boolean contains(int number) {
		boolean contains = false;
		
		int column = getColumn(number);
		
		for ( int i = 0; i < ROW_COUNT; i++){
			
			if ( getEntry(i+1,column) == number){
				contains = true;
			}
		}
		
		return contains;
		
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

	
	public boolean isMarked(int row, int column) {
		assert row > 0 && row <= ROW_COUNT;
		assert column >0 && column <= COLUMN_COUNT;
		boolean isMarked = false;
		
		if (row == FREE_SPACE_ROW && column == FREE_SPACE_COLUMN){
			isMarked = true;
		}
		
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
				Integer entry = getEntry(i+1,j+1);
				if (entry == FREE_SPACE ){
					temp += "FS";
				}
				else{
				temp += " " + entry +" ";
				}
				
			}
			temp += " ] \n";
		}
		
		temp += "Integers Marked: [ ";
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

			}
		
		
		}
		temp += "]";
		
		return temp;
	}

}
