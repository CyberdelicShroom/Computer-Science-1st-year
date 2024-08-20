import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import princeton.In;
import princeton.StdDraw;

public class SU22804897 {

	public static void main(String[] args) throws FileNotFoundException {
		boolean isGUImode = true;
		boolean key = false;
		if (args.length == 2) {
			// Text mode
			isGUImode = false;
			
			In boardFile = new In(args[0]);			
			
			String boardNAME = boardFile.readLine();
			
			int R = boardFile.readInt();
			int C = boardFile.readInt();		
			
		
			if((R<3 || R>15) || (C<3 || C>15)){
				System.out.println(boardNAME + ": is an invalid board, rows and coloumns must be between 3 and 15 (inclusive)");
				System.exit(0);
				
			}
			
			char[][] board = new char[R][C];
			
			//puts board into a 2D array
			for (int i = 0; i<R; i++){
				boardFile.readLine();
		    	for(int j = 0; j<C; j++){
		    		board[i][j] = boardFile.readChar();
		    		
		    	}
		    }
			
			
			int Rt = 0;//row of t				
			int Ct = 0;//column of t
			
			int Rs = 0;//row of s				
			int Cs = 0;//column of s			
			int[] playerPos = new int[2]; //contains Rs in playerPos[0] & Cs in playerPos[1]
			
		
		//counts the number of characters in the moves text file
		int movesLength = 0;
		FileReader readMoves = new FileReader(args[1]);				
		
		try{
			int c;
			while((c = readMoves.read()) != -1){
				movesLength = movesLength + 1;
			
			}
			
		}
		catch(IOException ex){
			System.out.println("Could not find file: " + args[1]);
		}
		
		//adds characters in moves text file to the moves-array
		In movesFile = new In(args[1]);
		
		
		char[] moves = new char[movesLength];				
			while(movesFile.hasNextChar()){
				for(int i = 0; i<movesLength; i++){
					moves[i] = movesFile.readChar();
				}
			}
		
			
			//search for player position
			searchPlayerPosition(Rs ,Cs ,R ,C ,board, playerPos);
		
			
			//searches for position of t
			for (int p = 0; p<R; p++){
				for(int q = 0; q<C; q++){
					if (board[p][q] == 't'){
						Rt = p;
						Ct = q;
						break;
					}
				}
			}
			
			//this whole for loop checks the next char in moves-file and applies the move to the board
			for (int i = 0; i<=movesLength; i++){
				//checks if player has not gotten to target by end of move cycle
				if ((board[Rt][Ct] != 's') && (i == movesLength)){
					System.out.println("You lose!");
					System.out.println("All moves used up");
					printTextBoard(R, C, board);
					System.exit(0);
				}
				//updates position of s
				searchPlayerPosition(Rs ,Cs ,R ,C ,board, playerPos);

					if (moves[i] == 'h'){
						//checks if player has moved to a wall
						if(playerPos[1]-1 > 0){
							if (board[playerPos[0]][playerPos[1]-1] == 'x' || isMoverLeft(board, playerPos) == true){
								System.out.println("You lose!");
								printTextBoard(R, C, board);
								System.exit(0);
							}
						}
						
						//changes position of s (LEFT)
						leftMove(board, R, C, playerPos, isGUImode, key);


						//checks if s has gotten to t and prints you win if it has
						if (board[Rt][Ct] == 's' || board[Rt][Ct] == 'S'){
							
							try{
								board[Rt][Ct+1] = '.';
							}
							catch (ArrayIndexOutOfBoundsException ar){
								continue;
							}
							System.out.println("You Won!");
							printTextBoard(R, C, board);
							System.exit(0);

						}

					}
					else if (moves[i] == 'l'){
						//checks if player has moved to a wall
						if (playerPos[1]+1 < C){
							if (board[playerPos[0]][playerPos[1]+1] == 'x' || isMoverRight(board, playerPos) == true){
								System.out.println("You lose!");
								printTextBoard(R, C, board);
								System.exit(0);
							}
						}
						
						//changes position of s (RIGHT)
						rightMove(board, R, C, playerPos, isGUImode, key);

						//checks if s has gotten to t and prints you win if it has
						if (board[Rt][Ct] == 's' || board[Rt][Ct] == 'S'){
							try{
								board[Rt][Ct-1] = '.';
							}
							catch (ArrayIndexOutOfBoundsException ar){
								continue;
							}
							System.out.println("You Won!");
							printTextBoard(R, C, board);
							System.exit(0);

						}

					}
					else if(moves[i] == 'j'){
						//checks if player has moved to a wall
						if((playerPos[0]+1 < R)){
							if (board[playerPos[0]+1][playerPos[1]] == 'x' || isMoverDown(board, playerPos) == true){
								System.out.println("You lose!");
								printTextBoard(R, C, board);
								System.exit(0);
							}
						}
						
						//changes position of s (DOWN)
						downMove(board, R, C, playerPos, Rs, Cs, isGUImode, key);

						//checks if s has gotten to t and prints you win if it has
						if (board[Rt][Ct] == 's' || board[Rt][Ct] == 'S'){
							try{
								board[Rt-1][Ct] = '.';
							}
							catch (ArrayIndexOutOfBoundsException ar){
								board[R-1][playerPos[1]] = '.';
							}
							System.out.println("You Won!");
							printTextBoard(R, C, board);
							System.exit(0);
						}

					}
					else if (moves[i] == 'k'){
						//checks if player has moved to a wall
						if(playerPos[0]-1 > 0){
							if (board[playerPos[0]-1][playerPos[1]] == 'x' || isMoverUp(board, playerPos) == true){
								System.out.println("You lose!");
								printTextBoard(R, C, board);
								System.exit(0);
							}
						}
						
						//changes position of s (UP)
						upMove(board, R, C, playerPos, Rs, Cs, isGUImode, key);

						//checks if s has gotten to t and prints you win if it has
						if (board[Rt][Ct] == 's' || board[Rt][Ct] == 'S'){
							
							try{
								board[Rt+1][Ct] = '.';
							}
							catch (ArrayIndexOutOfBoundsException ar){
								board[0][playerPos[1]] = '.';
							}
							System.out.println("You Won!");
							printTextBoard(R, C, board);
							System.exit(0);
						}


					}
					
					else if(moves[i] == 'x'){
						if(board[Rt][Ct] != 's' || board[Rt][Ct] != 'S'){
							System.out.println("You quit");
							printTextBoard(R, C, board);
							System.exit(0);
						}
						
					}
					else{
						System.out.println("Incorrect move");
						printTextBoard(R, C, board);
						System.exit(0);
					}
				
			}
			
	
		}
		
		else if (args.length == 1) {
			isGUImode = true;
			
			In boardFile = new In(args[0]);			
			
			String boardNAME = boardFile.readLine();
			
			int R = boardFile.readInt();
			int C = boardFile.readInt();		
			
		
			if((R<3 || R>15) || (C<3 || C>15)){
				System.out.println(boardNAME + ": is an invalid board, rows and coloumns must be between 3 and 15 (inclusive)");
				System.exit(0);
				
			}
			
			char[][] board = new char[R][C];
			
			//puts board into a 2D array
			for (int i = 0; i<R; i++){
				boardFile.readLine();
		    	for(int j = 0; j<C; j++){
		    		board[i][j] = boardFile.readChar();
		    	}
		    }
			
			
			int Rt = 0;//row of t				
			int Ct = 0;//column of t
			
			int Rs = 0;//row of s				
			int Cs = 0;//column of s			
			int[] playerPos = new int[2]; //contains Rs in playerPos[0] & Cs in playerPos[1]
		
			
			//search for player position
			searchPlayerPosition(Rs ,Cs ,R ,C ,board, playerPos);
		
			
			//searches for position of t
			for (int p = 0; p<R; p++){
				for(int q = 0; q<C; q++){
					if (board[p][q] == 't'){
						Rt = p;
						Ct = q;
						break;
					}
				}
			}
			
			StdDraw.setCanvasSize(108*C, 108*R);
			StdDraw.setXscale(0, C);
			StdDraw.setYscale(0, R);
			
			printGUIBoard(R, C, board);
			
			while(true){
				
				if (StdDraw.hasNextKeyTyped()){
					char input = StdDraw.nextKeyTyped();
					
					searchPlayerPosition( Rs, Cs, R, C, board, playerPos);
					
					if (input == 'h'){
						//move left
						//checks if player has moved to a wall, mover, closed port or closed switcher
						if(playerPos[1]-1 > 0){
							if (board[playerPos[0]][playerPos[1]-1] == 'x' || isMoverLeft(board, playerPos) == true || board[playerPos[0]][playerPos[1]-1] == 'p' 
								|| board[playerPos[0]][playerPos[1]-1] == 'h' || board[playerPos[0]][playerPos[1]-1] == 'v'){
								System.out.println("You lose!");
								printGUIBoard(R, C, board);
								System.exit(0);
							}
						}
						
						//changes position of s (LEFT)
						leftMove(board, R, C, playerPos, isGUImode, key);

						//checks if s has gotten to t and prints you win if it has
						if (board[Rt][Ct] == 's' || board[Rt][Ct] == 'S'){
							try{
								board[Rt][Ct+1] = '.';
							}
							catch (ArrayIndexOutOfBoundsException ar){
								continue;
							}
							System.out.println("You Won!");
							printGUIBoard(R, C, board);
							System.exit(0);

						}
					}
					
					else if (input == 'l'){
						//move right
						//checks if player has moved to a wall, mover, closed port or closed switcher
						if (playerPos[1]+1 < C){
							if (board[playerPos[0]][playerPos[1]+1] == 'x' || isMoverRight(board, playerPos) == true || board[playerPos[0]][playerPos[1]+1] == 'p' 
								|| board[playerPos[0]][playerPos[1]+1] == 'h' || board[playerPos[0]][playerPos[1]+1] == 'v'){
								System.out.println("You lose!");
								printGUIBoard(R, C, board);
								System.exit(0);
							}
						}
						
						//changes position of s (RIGHT)
						rightMove(board, R, C, playerPos, isGUImode, key);
						
						//checks if s has gotten to t and prints you win if it has
						if (board[Rt][Ct] == 's' || board[Rt][Ct] == 'S'){
							try{
								board[Rt][Ct-1] = '.';
							}
							catch (ArrayIndexOutOfBoundsException ar){
								continue;
							}
							System.out.println("You Won!");
							printGUIBoard(R, C, board);
							System.exit(0);

						}
						//playerPos[1]++;
					}
					
					else if (input == 'k'){
						//move up
						//checks if player has moved to a wall, mover, closed port or closed switcher
						if(playerPos[0]-1 >= 0){
							if (board[playerPos[0]-1][playerPos[1]] == 'x' || isMoverUp(board, playerPos) == true || board[playerPos[0]-1][playerPos[1]] == 'p' 
								|| board[playerPos[0]-1][playerPos[1]] == 'h' || board[playerPos[0]-1][playerPos[1]] == 'v'){
								System.out.println("You lose!");
								printGUIBoard(R, C, board);
								System.exit(0);
							}
						}
						
						//changes position of s (UP)
						upMove(board, R, C, playerPos, Rs, Cs, isGUImode, key);

						//checks if s has gotten to t and prints you win if it has
						if (board[Rt][Ct] == 's' || board[Rt][Ct] == 'S'){
							try{
								board[Rt+1][Ct] = '.';
							}
							catch (ArrayIndexOutOfBoundsException ar){
								board[0][playerPos[1]] = '.';
							}
							System.out.println("You Won!");
							printGUIBoard(R, C, board);
							System.exit(0);
						}
					}
					
					else if (input == 'j'){
						//move down
						//checks if player has moved to a wall, mover, closed port or closed switcher
						if((playerPos[0]+1 < R)){
							if (board[playerPos[0]+1][playerPos[1]] == 'x' || isMoverDown(board, playerPos) == true || board[playerPos[0]+1][playerPos[1]] == 'p' 
								|| board[playerPos[0]+1][playerPos[1]] == 'h' || board[playerPos[0]+1][playerPos[1]] == 'v'){
								System.out.println("You lose!");
								printGUIBoard(R, C, board);
								System.exit(0);
							}
						}
						
						//changes position of s (DOWN)
						downMove(board, R, C, playerPos, Rs, Cs, isGUImode, key);
						
						//checks if s has gotten to t and prints you win if it has
						if (board[Rt][Ct] == 's' || board[Rt][Ct] == 'S'){
							try{
								board[Rt-1][Ct] = '.';
							}
							catch (ArrayIndexOutOfBoundsException ar){
								board[R-1][playerPos[1]] = '.';
							}
							System.out.println("You Won!");
							printGUIBoard(R, C, board);
							System.exit(0);
						}
					}
					
					else if (input == 'q'){
						//exit
						if(board[Rt][Ct] != 's' || board[Rt][Ct] != 'S'){
							System.out.println("You quit");
							printGUIBoard(R, C, board);
							System.exit(0);
						}
					}
					
					else if  (input != 'h' || input != 'l' || input != 'k' || input != 'j' || input != 'q'){
						continue;
					}
					
				}
				
			}
			
		}
			
			
	}//end main
	
	public static void printGUIBoard(int R, int C, char[][] board){
		
		StdDraw.clear();
		for (int i = 0; i < R; i++){
			for (int p = 0; p < C; p++){
				
				if (board[i][p] == '.'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_e.png");
				}
				else if (board[i][p] == 's' || board[i][p] == 'S'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_s.png");
				}
				else if (board[i][p] == 't'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_t.png");
				}
				else if (board[i][p] == 'x' || board[i][p] == 'X'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_x.png");
				}
				else if (board[i][p] == 'U'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_uv.png");
				}
				else if (board[i][p] == 'D'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_dv.png");
				}
				else if (board[i][p] == 'L'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_lv.png");
				}
				else if (board[i][p] == 'R'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_rv.png");
				}
				else if (board[i][p] == 'u'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_uh.png");
				}
				else if (board[i][p] == 'd'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_dh.png");
				}
				else if (board[i][p] == 'l'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_lh.png");
				}
				else if (board[i][p] == 'r'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_rh.png");
				}
				else if (board[i][p] == 'H'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_sh0.png");
				}
				else if (board[i][p] == 'h'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_sh1.png");
				}
				else if (board[i][p] == 'V'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_sv0.png");
				}
				else if (board[i][p] == 'v'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_sv1.png");
				}
				else if (board[i][p] == 'k' || board[i][p] == 'K'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_k1.png");
				}
				//I made Z assigned to an unavailable key
				else if (board[i][p] == 'Z'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_k0.png");
				}
				else if (board[i][p] == 'P'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_p0.png");
				}
				else if (board[i][p] == 'p'){
					StdDraw.picture(p + 0.5 , (R-i) - 0.5 , "images/tvl_p1.png");
				}
				StdDraw.square(p + 0.5, (R-i) - 0.5, 0.5);
				
			}
		}
		StdDraw.show(33);
	}
	
	public static void printTextBoard(int R, int C, char[][] board){
		
		char[][] boardOutput = new char[R][C]; 
		
		//puts board into a 2D array
		for (int i = 0; i<R; i++){
			
	    	for(int j = 0; j<C; j++){

	    		boardOutput[i][j] = board[i][j];

	    	}
	    }
		outputFormat(R,  C, boardOutput);
		for (int h = 0; h<R; h++){
			for(int j = 0; j<C; j++){
				if (j%C==0){
					System.out.println();
				}
				
				System.out.print(boardOutput[h][j]);
			}

		}
		System.out.println();
		
	}
	
	
	public static void outputFormat(int R, int C, char[][] boardOutput){
		for (int p = 0; p<R; p++){
			for(int q = 0; q<C; q++){
				
				if (boardOutput[p][q] == 'U' || boardOutput[p][q] == 'D' || boardOutput[p][q] == 'L' || boardOutput[p][q] == 'R' 
					|| boardOutput[p][q] == 'u' || boardOutput[p][q] == 'd' || boardOutput[p][q] == 'r' || boardOutput[p][q] == 'l'){
					boardOutput[p][q] = 'm';
				}
				if(boardOutput[p][q] == 's' || boardOutput[p][q] == 'S'){
					boardOutput[p][q] = 'Y';
				}
				if(boardOutput[p][q] == 'X'){
					boardOutput[p][q] = 'x';
				}
				if(boardOutput[p][q] == 'T'){
					boardOutput[p][q] = 't';
				}
				
			}
		}
	}
	
	
	public static int[] searchPlayerPosition(int Rs, int Cs, int R, int C, char[][] board, int[] playerPos){
		//search for player position
		for (int p = 0; p<R; p++){
			for(int q = 0; q<C; q++){
				if (board[p][q] == 's' || board[p][q] == 'S'){
					Rs = p;
					Cs = q;
					playerPos[0] = Rs;
					playerPos[1] = Cs;
					break;
				}
			}
		}
		return playerPos;//contains Rs in playerPos[0] & Cs in playerPos[1]
	}
	
	
	public static void leftMove(char[][] board, int R, int C, int[] playerPos, boolean isGUImode, boolean key){
		
		if(playerPos[1] == 0){
			
			board[playerPos[0]][playerPos[1]] = board[playerPos[0]][playerPos[1]];
			
		}
		//changes position of player (LEFT)
		else{
			if (isGUImode == true){
				ifKey_left_togglePorts(board, playerPos, key, R, C);
			}	
			Object temp4 = board[playerPos[0]][playerPos[1]];
			board[playerPos[0]][playerPos[1]] = board[playerPos[0]][playerPos[1]-1];
			board[playerPos[0]][playerPos[1]-1] = (char) temp4;
		}
		
		moveHorizontalMovers(R, C, board);
		
		if (isGUImode == true){
			toggleHorizontalSwitchers(board, R, C);
			printGUIBoard(R, C, board);	
		}	
		
		
	}
	
	
	public static void rightMove(char[][] board, int R, int C, int[] playerPos, boolean isGUImode, boolean key){
		
		if(playerPos[1] == C-1){
			
			board[playerPos[0]][playerPos[1]] = board[playerPos[0]][playerPos[1]];
			
		}
		//changes position of player (RIGHT)
		else{
			if (isGUImode == true){
				ifKey_right_togglePorts(board, playerPos, key, R, C);
			}
			Object temp4 = board[playerPos[0]][playerPos[1]];
			board[playerPos[0]][playerPos[1]] = board[playerPos[0]][playerPos[1]+1];
			board[playerPos[0]][playerPos[1]+1] = (char) temp4;
		} 
		
		moveHorizontalMovers(R, C, board);
		
		if (isGUImode == true){
			toggleHorizontalSwitchers(board, R, C);
			printGUIBoard(R, C, board);	
		}	
		
		
	}
	
	public static void downMove(char[][] board, int R, int C, int[] playerPos, int Rs, int Cs, boolean isGUImode, boolean key){
		
		
		if(playerPos[0] == R-1){
			
			Object temp4 = board[playerPos[0]][playerPos[1]];
			board[playerPos[0]][playerPos[1]] = board[0][playerPos[1]];
			board[0][playerPos[1]] = (char) temp4;
		}
		//changes position of s (DOWN)
		else{
			if (isGUImode == true){
				ifKey_down_togglePorts(board, playerPos, key, R, C);	
			}
			Object temp4 = board[playerPos[0]][playerPos[1]];
			board[playerPos[0]][playerPos[1]] = board[playerPos[0]+1][playerPos[1]];
			board[playerPos[0]+1][playerPos[1]] = (char) temp4;
		}
		
		moveVerticalMovers(R, C, board);
		
		if (isGUImode == true){
			toggleVerticalSwitchers(board, R, C);
			printGUIBoard(R, C, board);	
		}	
		
	}
	
	public static void upMove(char[][] board, int R, int C, int[] playerPos, int Rs, int Cs, boolean isGUImode, boolean key){
		
		
		if(playerPos[0] == 0){
			Object temp4 = board[playerPos[0]][playerPos[1]];
			board[playerPos[0]][playerPos[1]] = board[R-1][playerPos[1]];
			board[R-1][playerPos[1]] = (char) temp4;
		}
		//changes position of s (UP)
		else{
			if (isGUImode == true){
				ifKey_up_togglePorts(board, playerPos, key, R, C);
			}
			Object temp4 = board[playerPos[0]][playerPos[1]];
			board[playerPos[0]][playerPos[1]] = board[playerPos[0]-1][playerPos[1]];
			board[playerPos[0]-1][playerPos[1]] = (char) temp4;
		}
		
		moveVerticalMovers(R, C, board);
		
		if (isGUImode == true){
			toggleVerticalSwitchers(board, R, C);
			printGUIBoard(R, C, board);	
		}	
		
		
	}
	
	public static void ifKey_right_togglePorts(char[][] board, int[] playerPos, boolean key, int R, int C){
		
		if(board[playerPos[0]][playerPos[1]+1] == 'K' || board[playerPos[0]][playerPos[1]+1] == 'k'){
			key = true;
		}
		
		if (key == true){
			for (int i = 0; i < R; i++){
				for (int d = 0; d < C; d++){
					if (board[i][d] == 'P'){
						board[i][d] = 'p';
						board[playerPos[0]][playerPos[1]+1] = 'Z';
					}
					else if (board[i][d] == 'p'){
						board[i][d] = 'P';
						board[playerPos[0]][playerPos[1]+1] = 'Z';
					}
				}
			}
		}
		
	}
	
	public static void ifKey_left_togglePorts(char[][] board, int[] playerPos, boolean key, int R, int C){
		
		if(board[playerPos[0]][playerPos[1]-1] == 'K' || board[playerPos[0]][playerPos[1]-1] == 'k'){
			key = true;
		}
		
		if (key == true){
			for (int i = 0; i < R; i++){
				for (int d = 0; d < C; d++){
					if (board[i][d] == 'P'){
						board[i][d] = 'p';
						board[playerPos[0]][playerPos[1]-1] = 'Z';
					}
					else if (board[i][d] == 'p'){
						board[i][d] = 'P';
						board[playerPos[0]][playerPos[1]-1] = 'Z';
					}
				}
			}
		}
		
	}
	
	public static void ifKey_up_togglePorts(char[][] board, int[] playerPos, boolean key, int R, int C){
		
		if(board[playerPos[0]-1][playerPos[1]] == 'K' || board[playerPos[0]-1][playerPos[1]] == 'k'){
			key = true;
		}
		
		if (key == true){
			for (int i = 0; i < R; i++){
				for (int d = 0; d < C; d++){
					if (board[i][d] == 'P'){
						board[i][d] = 'p';
						board[playerPos[0]-1][playerPos[1]] = 'Z';
					}
					else if (board[i][d] == 'p'){
						board[i][d] = 'P';
						board[playerPos[0]-1][playerPos[1]] = 'Z';
					}
				}
			}
		}
		
	}
	
	public static void ifKey_down_togglePorts(char[][] board, int[] playerPos, boolean key, int R, int C){
		
		if(board[playerPos[0]+1][playerPos[1]] == 'K' || board[playerPos[0]+1][playerPos[1]] == 'k'){
			key = true;
		}
		
		if (key == true){
			for (int i = 0; i < R; i++){
				for (int d = 0; d < C; d++){
					if (board[i][d] == 'P'){
						board[i][d] = 'p';
						board[playerPos[0]+1][playerPos[1]] = 'Z';
					}
					else if (board[i][d] == 'p'){
						board[i][d] = 'P';
						board[playerPos[0]+1][playerPos[1]] = 'Z';
					}
				}
			}
		}
		
	}
	
	public static void toggleHorizontalSwitchers(char[][] board, int R, int C){
		
		for (int i = 0; i < R; i++){
			for (int d = 0; d < C; d++){
				
				if (board[i][d] == 'H'){
					board[i][d] = 'h';
				}
				else if (board[i][d] == 'h'){
					board[i][d] = 'H';
				}
			}
		}
	}
	
	public static void toggleVerticalSwitchers(char[][] board, int R, int C){
		
		for (int i = 0; i < R; i++){
			for (int d = 0; d < C; d++){
				
				if (board[i][d] == 'V'){
					board[i][d] = 'v';
				}
				else if (board[i][d] == 'v'){
					board[i][d] = 'V';
				}
			}
		}
	}
	
	public static void moveVerticalMovers(int R, int C, char[][] board){
		//finds the vertical movers and moves them up/down/left/right
		for (int p = 0; p<R; p++){
			for(int q = 0; q<C; q++){
				
				if (board[p][q] == 'D'){
					
					int Rd = p;
					int Cd = q;
					
					//System.out.println("ROW: " + Rd + "COL: " + Cd);
					
					if(Rd<R-1){
						if(board[Rd+1][Cd] == 't'){
							Object temp4 = board[Rd][Cd];
							board[Rd][Cd] = board[Rd+2][Cd];
							board[Rd+2][Cd] = (char) temp4;
						}
						else{
							Object temp4 = board[Rd][Cd];
							board[Rd][Cd] = board[Rd+1][Cd];
							board[Rd+1][Cd] = (char) temp4;
						}
						
					}
					
					else if(Rd == R-1){
						Object temp4 = board[Rd][Cd];
						board[Rd][Cd] = board[0][Cd];
						board[0][Cd] = (char) temp4;
					}
					break;

				}
				
				else if (board[p][q] == 'U'){
					int Ru = p;
					int Cu = q;
					
					//System.out.println("ROW: " + Ru + "COL: " + Cu);
					
					if(Ru>0){
						if(board[Ru-1][Cu] == 't'){
							Object temp4 = board[Ru][Cu];
							board[Ru][Cu] = board[Ru-2][Cu];
							board[Ru-2][Cu] = (char) temp4;
						}
						else{
							Object temp4 = board[Ru][Cu];
							board[Ru][Cu] = board[Ru-1][Cu];
							board[Ru-1][Cu] = (char) temp4;
						}
						
						
					}
					else if(Ru == 0){
						Object temp4 = board[Ru][Cu];
						board[Ru][Cu] = board[R-1][Cu];
						board[R-1][Cu] = (char) temp4;
					}
					break;
				}
				
				else if (board[p][q] == 'L'){
					
					int Rl = p;
					int Cl = q;
					
					
					if(Cl>0 || Cl == 0){
						if(Cl == 0){
							Object temp4 = board[Rl][Cl];
							board[Rl][Cl] = board[Rl][C-1];
							board[Rl][C-1] = (char) temp4;
						}
						
						else{
							if(board[Rl][Cl-1] == 't'){
								Object temp4 = board[Rl][Cl];
								board[Rl][Cl] = board[Rl][Cl-2];
								board[Rl][Cl-2] = (char) temp4;
							}
							else{
								Object temp4 = board[Rl][Cl];
								board[Rl][Cl] = board[Rl][Cl-1];
								board[Rl][Cl-1] = (char) temp4;
							}
							
						}
					}
					
					break;
					
				}
				
				else if (board[p][q] == 'R'){
					int Rr = p;
					int Cr = q;
					
					
					if(Cr <= C-1){
						if(Cr == C-1){
							Object temp4 = board[Rr][Cr];
							board[Rr][Cr] = board[Rr][0];
							board[Rr][0] = (char) temp4;
						}
						
						else{
							if(board[Rr][Cr+1] == 't'){
								Object temp4 = board[Rr][Cr];
								board[Rr][Cr] = board[Rr][Cr+2];
								board[Rr][Cr+2] = (char) temp4;
							}
							else{
								Object temp4 = board[Rr][Cr];
								board[Rr][Cr] = board[Rr][Cr+1];
								board[Rr][Cr+1] = (char) temp4;
							}
							
						}
					}
					break;
					
				}
				
			}
				
					
		}
				
				
	}
		
	
	
	public static void moveHorizontalMovers(int R, int C, char[][] board){
		//finds the horizontal movers and moves them up/down/left/right
		for (int p = 0; p<R; p++){
			for(int q = 0; q<C; q++){
				
				if (board[p][q] == 'd'){
					//moves down when Player moves left/right
					int Rd = p;
					int Cd = q;
					
					
					if(Rd<R-1){
						if(Rd == R-1){
						Object temp4 = board[Rd][Cd];
						board[Rd][Cd] = board[0][Cd];
						board[0][Cd] = (char) temp4;
						}
					
						else{
							Object temp4 = board[Rd][Cd];
							board[Rd][Cd] = board[Rd+1][Cd];
							board[Rd+1][Cd] = (char) temp4;
						}
					}
					break;
				}
				
				else if (board[p][q] == 'u'){
					//moves up when Player moves left/right
					int Ru = p;
					int Cu = q;
					
					if(Ru>0){
						
						Object temp4 = board[Ru][Cu];
						board[Ru][Cu] = board[Ru-1][Cu];
						board[Ru-1][Cu] = (char) temp4;
						
					}
					
					else if(Ru == 0){
						Object temp4 = board[Ru][Cu];
						board[Ru][Cu] = board[R-1][Cu];
						board[R-1][Cu] = (char) temp4;
						
					}
					break;
				}
				
				else if (board[p][q] == 'l'){
					//moves left when Player moves left/right
					int Rl = p;
					int Cl = q;
					
					if(Cl > 0){
						
						Object temp4 = board[Rl][Cl];
						board[Rl][Cl] = board[Rl][Cl-1];
						board[Rl][Cl-1] = (char) temp4;
						
					}
					
					else if(Cl == 0){
						Object temp4 = board[Rl][Cl];
						board[Rl][Cl] = board[Rl][C-1];
						board[Rl][C-1] = (char) temp4;
						
					}
					break;
					
				}
				
				else if (board[p][q] == 'r'){
					//moves right when Player moves left/right
					int Rr = p;
					int Cr = q;
					
					if(Cr < C-1){
						
						Object temp4 = board[Rr][Cr];
						board[Rr][Cr] = board[Rr][Cr+1];
						board[Rr][Cr+1] = (char) temp4;
						
					}
					
					else if(Cr == C-1){
						Object temp4 = board[Rr][Cr];
						board[Rr][Cr] = board[Rr][0];
						board[Rr][0] = (char) temp4;
						
					}
					break;
					
				}
			}
		}
	}
	
	public static boolean isMoverRight(char[][] board, int[] playerPos){
		
		try{
			//checks if there is a mover to the right of s
			if(board[playerPos[0]][playerPos[1]+1] == 'U' || board[playerPos[0]][playerPos[1]+1] == 'D' || 
			   board[playerPos[0]][playerPos[1]+1] == 'R' || board[playerPos[0]][playerPos[1]+1] == 'L' || 
			   board[playerPos[0]][playerPos[1]+1] == 'u' || board[playerPos[0]][playerPos[1]+1] == 'd' || 
			   board[playerPos[0]][playerPos[1]+1] == 'r' || board[playerPos[0]][playerPos[1]+1] == 'l')
			{
				return true;
			}
			
		}
		catch (ArrayIndexOutOfBoundsException ar){
			
		}
		
		return false;
	}
	
	public static boolean isMoverLeft(char[][] board, int[] playerPos){
		
		try{
			//checks if there is a mover to the left of s
			if(board[playerPos[0]][playerPos[1]-1] == 'U' || board[playerPos[0]][playerPos[1]-1] == 'D' || 
				board[playerPos[0]][playerPos[1]-1] == 'R' || board[playerPos[0]][playerPos[1]-1] == 'L' || 
				board[playerPos[0]][playerPos[1]-1] == 'u' || board[playerPos[0]][playerPos[1]-1] == 'd' || 
				board[playerPos[0]][playerPos[1]-1] == 'r' || board[playerPos[0]][playerPos[1]-1] == 'l')
			{
				return true;
			}
		}
		catch (ArrayIndexOutOfBoundsException ar){
			
		}
		
		return false;
	}
	
	public static boolean isMoverUp(char[][] board, int[] playerPos){
		
		try{
			//checks if there is a mover to the left of s
			if(board[playerPos[0]-1][playerPos[1]] == 'U' || board[playerPos[0]-1][playerPos[1]] == 'D' || 
				board[playerPos[0]-1][playerPos[1]] == 'R' || board[playerPos[0]-1][playerPos[1]] == 'L' || 
				board[playerPos[0]-1][playerPos[1]] == 'u' || board[playerPos[0]-1][playerPos[1]] == 'd' || 
				board[playerPos[0]-1][playerPos[1]] == 'r' || board[playerPos[0]-1][playerPos[1]] == 'l')
			{
				return true;
			}
		}
		catch (ArrayIndexOutOfBoundsException ar){
			
		}
		
		return false;
	}
	
	public static boolean isMoverDown(char[][] board, int[] playerPos){
		
		try{
			//checks if there is a mover to the left of s
			if(board[playerPos[0]+1][playerPos[1]] == 'U' || board[playerPos[0]+1][playerPos[1]] == 'D' || 
				board[playerPos[0]+1][playerPos[1]] == 'R' || board[playerPos[0]+1][playerPos[1]] == 'L' || 
				board[playerPos[0]+1][playerPos[1]] == 'u' || board[playerPos[0]+1][playerPos[1]] == 'd' || 
				board[playerPos[0]+1][playerPos[1]] == 'r' || board[playerPos[0]+1][playerPos[1]] == 'l')
			{
				return true;
			}
		}
		catch (ArrayIndexOutOfBoundsException ar){
			
		}
		
		return false;
	}
		
}



