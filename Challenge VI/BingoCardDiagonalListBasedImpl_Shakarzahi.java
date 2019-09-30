package bingo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BingoCardDiagonalListBasedImpl_Shakarzahi extends BingoCardDiagonalListBased_Abstract{


	public Integer getEntry(int row, int column) {
		Integer entry = -1;
		List<Integer> rowList = new ArrayList<Integer>();
		if (row ==5 && column ==1){
			rowList = diagonalListList.get(0);
			entry = rowList.get(0);
		}
		
		if ( row ==5 && column ==2){
			rowList = diagonalListList.get(1);
			entry = rowList.get(0);
		}
		
		if ( row ==4 && column ==1){
			rowList = diagonalListList.get(1);
			entry = rowList.get(1);
		}
		
		if ( row ==5 && column ==3){
			rowList = diagonalListList.get(2);
			entry = rowList.get(0);
		}
		
		if ( row ==4 && column ==2){
			rowList = diagonalListList.get(2);
			entry = rowList.get(1);
		}
		if ( row ==3 && column ==1){
			rowList = diagonalListList.get(2);
			entry = rowList.get(2);
		}
		
		if ( row ==5 && column ==4){
			rowList = diagonalListList.get(3);
			entry = rowList.get(0);
		}
		if ( row ==4 && column ==3){
			rowList = diagonalListList.get(3);
			entry = rowList.get(1);
		}
		
		if ( row ==3 && column ==2){
			rowList = diagonalListList.get(3);
			entry = rowList.get(2);
		}
		
		if ( row ==2 && column ==1){
			rowList = diagonalListList.get(3);
			entry = rowList.get(3);
		}
		
		if ( row ==5 && column ==5){
			rowList = diagonalListList.get(4);
			entry = rowList.get(1);
		}
		
		if ( row ==4 && column ==4){
			rowList = diagonalListList.get(4);
			entry = rowList.get(2);
		}
		
		if ( row ==3 && column ==3){
			
			entry = FREE_SPACE;
		}
		if ( row ==2 && column ==2){
			rowList = diagonalListList.get(4);
			entry = rowList.get(3);
		}
		
		if ( row ==1 && column ==1){
			rowList = diagonalListList.get(4);
			entry = rowList.get(4);
		}
		
		if ( row ==4 && column ==5){
			rowList = diagonalListList.get(5);
			entry = rowList.get(0);
		}
		
		if ( row ==3 && column ==4){
			rowList = diagonalListList.get(5);
			entry = rowList.get(1);
		}
		if ( row ==2 && column ==3){
			rowList = diagonalListList.get(5);
			entry = rowList.get(2);
		}
		if ( row ==1 && column ==2){
			rowList = diagonalListList.get(5);
			entry = rowList.get(3);
		}
		if ( row ==3 && column ==5){
			rowList = diagonalListList.get(6);
			entry = rowList.get(0);
		}
		if ( row ==2 && column ==4){
			rowList = diagonalListList.get(6);
			entry = rowList.get(1);
		}
		if ( row ==1 && column ==3){
			rowList = diagonalListList.get(6);
			entry = rowList.get(2);
		}
		if ( row == 2 && column == 5){
			rowList = diagonalListList.get(7);
			entry = rowList.get(0);
		}
		if ( row ==1 && column ==4){
			rowList = diagonalListList.get(7);
			entry = rowList.get(1);
		}
		if ( row ==1 && column ==5){
			rowList = diagonalListList.get(8);
			entry = rowList.get(0);
		}
		
		assert entry != -1;
		return entry;
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
}
