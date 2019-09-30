package bingo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BingoCardRowSetBasedImpl_Shakarzahi extends BingoCardRowSetBased_Abstract
{
	//STUDENT: DO *NOT* ADD ANY INSTANCE VARIABLES HERE OR IN THE ABSTRACT CLASS!!!

	public BingoCardRowSetBasedImpl_Shakarzahi(Mystery_A mystery)
	{
		super(mystery);
	}
	
	
	//pre: row1.size() == row2.size() == row3.size() == row4.size() == row5.size()
	//pre: row 3.contains(FREE_SPACE)
	//post: rowSetList.size() == 4
	//post: integersMarked.contains(FREE_SPACE); 
	public BingoCardRowSetBasedImpl_Shakarzahi(Set<Integer> row1, Set<Integer> row2,Set<Integer> row3, Set<Integer> row4, Set<Integer> row5 ){
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
		rowSetList = new ArrayList<Set<Integer>>();
		
		rowSetList.add(0,row1);
		rowSetList.add(1,row2);
		rowSetList.add(2,row3);
		rowSetList.add(3,row4);
		rowSetList.add(4,row5);
		
		
		integersMarked = new HashSet<Integer>();
		integersMarked.add(FREE_SPACE);
		
	}
	
	public BingoCardRowSetBasedImpl_Shakarzahi(Integer[][] entries){
		rowSetList = new ArrayList<Set<Integer>>();
	
	    for(int i = 0; i < entries.length; i++){
	    	Integer[] array = entries[i];
	    	Set<Integer> set = new HashSet<Integer>(Arrays.asList(array));
	        rowSetList.add(i,set);
	    }
		
		integersMarked = new HashSet<Integer>();
		
		integersMarked.add(FREE_SPACE);
		
	}
	
	//pre: column <= COLUMN_COUNT && row <= ROW_COUNT
	//post: column 1 == column "B" 1<= rv <=15
	//post: column 2 == row "I" 16<= rv <= 30
	//post: column 3 == row "N" 31<= rv <= 45
	//post: column 4 == row "G" 46 <= rv <= 60
	//post: column 5 == row "O" 61 <= rv <= 75
	public Integer getEntry(int row, int column)
	{
		//assert row > 0 && row <= ROW_COUNT;
		//assert column > 0 && column <= COLUMN_COUNT;
		//assert !checkForDuplicates(rowSetList);
		
		
		Set<Integer> rowSet = rowSetList.get(row-1);
	
		
		
		List<Integer> rowList = new ArrayList<Integer>(rowSet);
		Integer returnValue =-1;
		 Integer listElement;
		 int checkColumn;
		 
		
		 
		 
		
		for ( int i = 0; i< rowList.size(); i++){
			listElement = rowList.get(i);
			
			if ( listElement != FREE_SPACE){
			checkColumn = getColumn(listElement);
			if( checkColumn == column){
				returnValue = listElement;
			}

			
			}
		}
		
		if ( row != FREE_SPACE_ROW && column != FREE_SPACE_COLUMN){
			assert returnValue >0;
		}
		if ( row == FREE_SPACE_ROW && column == FREE_SPACE_COLUMN){
			
			returnValue = FREE_SPACE;
		}
		return returnValue; 
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
	
	public void mark(int number)
	{
		assert number > 0 && number <= 75;
		
		if ( contains(number) ){
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
	
	
	
		/*private int getRow(int num, List<Set<Integer>> setList){
		
		int row = -1;
		Set<Integer> rowInList = new HashSet<Integer>();
		List<Set<Integer>> rowSetList = setList;
		
		for ( int i = 0; i < rowSetList.size(); i++){
			rowInList = rowSetList.get(i);
			if ( rowInList.contains(num)){
				row = i+1;
			}
		}
		
		return row;
	}*/
	
	
	
	public boolean contains(int number)
	{
		/*		boolean contains = false;
		
		int row = getRow(number, rowSetList);
		
		if ( row > 0){
			contains = true;
		}
		
		
		return contains; */
		boolean contains = false;
		
		int column = getColumn(number);
		
		for ( int i = 0; i < ROW_COUNT; i++){
			
			if ( getEntry(i+1,column) == number){
				contains = true;
			}
		}
		
		return contains;
	}

	//getentry
	public boolean isMarked(int row, int column)
	{
		
		
		boolean isMarked = false;
		
		if ( row == FREE_SPACE_ROW && column == FREE_SPACE_COLUMN){
			isMarked = true;
		}
		if (row != FREE_SPACE_ROW && column != FREE_SPACE_COLUMN) {
		Integer listElement;
		
		listElement = getEntry(row,column);
		
		isMarked = integersMarked.contains(listElement);
		}
		
		
		return isMarked;
	}
	
	public boolean isWinner()
	{
		boolean isWinner = false;
		
		if( isColumnWinner() == true){
			isWinner = true;
		}
		
		if(isRowWinner() == true){
			isWinner = true;
		}
		
		if(isDiagonalWinner() == true){
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
		
		/*int columnCount = 1;
		int rowCount = 1;
		
		while ( rowCount <= ROW_COUNT){
			
			if (isMarked(rowCount,columnCount)){
				Integer entry = getEntry(rowCount,columnCount);
				columnWinnerSet.add(entry);
			}
			
			if (columnWinnerSet.size() != 5 && rowCount == ROW_COUNT){
				columnWinnerSet.clear();
				rowCount = 0;
				columnCount ++;
			}
			
			if(columnWinnerSet.size() ==5){
				isColumnWinner = true;
			}
			rowCount++;
		}*/
		
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
		
		/*int columnCount = 1;
		int rowCount = 1;
		
		while( rowCount <= ROW_COUNT){
			if(isMarked(rowCount,columnCount)){
				Integer entry = getEntry(rowCount,columnCount);
				rowWinnerSet.add(entry);
			}
			
			if( rowWinnerSet.size() != 5 && columnCount == COLUMN_COUNT ){
				rowWinnerSet.clear();
				columnCount = 0;
				rowCount++;
			}
			
			if (rowWinnerSet.size() == 5){
				isRowWinner = true;
			}
			columnCount++;
		}*/
		
		
		return isRowWinner;
	}
	
	private boolean isDiagonalWinner(){
		boolean isDiagonalWinner= false; 
		
		Set<Integer> diagonalWinnerSet1 = new HashSet<Integer>();
		Set<Integer> diagonalWinnerSet2 = new HashSet<Integer>();
		if ( isMarked(1,1) ){
			/*int rowCount = 0;
			int columnCount = 0;
			
			while( rowCount <= ROW_COUNT && columnCount <= COLUMN_COUNT){
				if ( isMarked(rowCount+1,columnCount+1) ){
					Integer entry = getEntry(rowCount+1,columnCount+1);
					diagonalWinnerSet.add(entry);
				}
				
				if ( diagonalWinnerSet.size() == 5){
					isDiagonalWinner = true;
				}
				rowCount++;
				columnCount++;
			}*/
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
		/*	int rowCount = 1;
			int columnCount = ROW_COUNT;
			
			
			
			while( rowCount <= ROW_COUNT && columnCount > 0){
				if ( isMarked(rowCount +1,columnCount +1)){
					Integer entry = getEntry(rowCount +1,columnCount+1);
					diagonalWinnerSet2.add(entry);
				}
				
				if (diagonalWinnerSet2.size() ==5){
					isDiagonalWinner = true;
				}
				
				rowCount++;
				columnCount --;
			}*/
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