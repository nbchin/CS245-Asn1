
public class Sudoku {
	
	private static int[][] board;

	public Sudoku(){
		Sudoku.board = new int[9][9];
	}
	
	//method to print board
	public void printBoard(){
		for(int i = 0; i < board.length; i++){
			System.out.print("\n");
			rowSeperator(board.length);
			System.out.print("\n");
			for(int n = 0; n < board.length; n++){
				System.out.print("|");
				System.out.format("%3d", board[i][n]);
			}
			System.out.print("|");
		}
		System.out.print("\n");
		rowSeperator(board.length);
	}
	
	//static method for Sudoku board to be filled.
	//9x9 board starts off filled with 0's. 
	//0 means not set, 1 means set, 2 means fixed.
	public static int[][] fillBoard(){
		
		int[][] status = new int[9][9];
				
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				status[i][j] = 0;
			}
		}
		
		//starts at (0, 0)
		if(fillBoard(board, status, 0, 0)){
			System.out.println("Board filled.");
			return status;
		} else {
			System.out.println("Board not filled.");
			return status;
		}	
				
	}
	
	//recursive board solving method
	protected static boolean fillBoard(int[][] board, int[][] status, int x, int y){
		if(x == 9){ //checks to see if we are at end
			int count = 0; //when count is 81 board is filled, all 0's have been replaced
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					count += status[i][j] > 0 ? 1:0;
				}
			}
			if(count == 81){
				return true;
			} else {
				return false;
			}
		}
		//if vertex is set, continue.
		if(status[x][y] >= 1){
			int nextX = x;
			int nextY = y + 1;
			//will continue row unless at 9(end)
			if(nextY == 9 ){
				nextX = x + 1; 
				nextY = 0;
			}
			//recursively calls next point
			return fillBoard(board, status, nextX, nextY);
		} else {
			//the checks. the boolean array is of positions near point that have been used
			boolean[] used = new boolean[9];
			//checks col
			for(int i = 0; i <9; i++){
				if(status[i][y] >= 1){
					used[board[i][y] - 1] = true;
				}
			}
			//checks row
			for(int i = 0; i <9; i++){
				if(status[x][i] >= 1){
					used[board[x][i] - 1] = true;
				}
			}
			//checks 3x3 box. Rows and cols start at 0, 3, &6
			//Ex, first check is at (0, 0) and checks through (2, 2)
			for(int i = x - (x%3); i < x - (x%3) + 3; i++){
				for(int j = y - (y % 3); j < y - (y%3) + 3; j++){
					if(status[i][j] >= 1){
						used[board[i][j] - 1] = true;
					}
				}
			}
			
			//this check uses bool array 
			for(int i = 0; i < used.length; i++){
				//if the index's have not been used
				if(used[i] == false){
					status[x][y] = 1;
					board[x][y] = i + 1; //value
					//procede
					int nextX = x;
					int nextY = y + 1;
					if(nextY == 9 ){
						nextX = x + 1;
						nextY = 0;
					}
					//will return true if board is filled
					if(fillBoard(board, status, nextX, nextY)){
						return true;
					}
					//try new "setting"
					for(int m = 0; m < 9; m++){
						for(int n = 0; n < 9; n++){
							//will not change/check numbers past current x and y
							if(m > x || m == x && n >= y){
								if(status[m][n] == 1){
									status[m][n] = 0;
									board[m][n] = 0;
								}
							}
						}
					}
				
				}
			}
			return false;
		}
		
	}
	
	//helper method to printBoard
	private void rowSeperator(int boardLength){
		for(int i = 0; i < boardLength; i++){
			
			System.out.print(" _ _");
		}
	}
	

}
