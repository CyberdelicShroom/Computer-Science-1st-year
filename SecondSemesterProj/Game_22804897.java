import princeton.In;

public class Game_22804897{
	public static void main(String[] args) {
    	String path = args[0];
    	int outputVersion = Integer.parseInt(args[1]);
    	
        In in = new In(path);
        
        //Reading in first line of text file - Row, Column and Iterations
        int R = in.readInt();
        int C = in.readInt();
        int I = in.readInt();
        
        String[][] board = new String[R][C];
    	WarriorTypeInterface_22804897 warrior = null;
    	WarriorTypeInterface_22804897[] warriors = null;
    	int numberOfWarriors = 0;
    	
    	int crystalRow = 0;
    	int crystalCol = 0;
    	boolean gameContainsCrystal = false;
    	boolean magicCrystalActivated = false;
    	
    	double[][] weapons_OPandLocation = new double[R][C];
    	int numberOfWeapons = 0;
    	String[] weaponData;
    	boolean gameContainsWeapons = false;
    	
        if (outputVersion == 0) {
            // warrior statistics mode
        	WaterSimulation_22804897 sim = new WaterSimulation_22804897(R,C);
        	boolean isWaterPieces = false;
        	//EXTRACTING DATA FROM TEXT FILE
        	while(in.hasNextLine()){
            	String line = in.readLine();
            	String[] parts = line.split(" ");
            	
            	if (line.contains("Warrior")){
            		String[] warriorData = null;
            		numberOfWarriors = Integer.parseInt(parts[1]);
            		warriors = new WarriorTypeInterface_22804897[numberOfWarriors];
            		
            		if(numberOfWarriors == 0){
            			System.out.println("No warriors are left!");
            			System.exit(0);
            		}
            		
            		for(int x = 0; x < numberOfWarriors; x++){
            			String warriorLine = in.readLine();
            			warriorData = warriorLine.split(" ");
            			
            			int warriorRow = Integer.parseInt(warriorData[0]);
            			int warriorCol = Integer.parseInt(warriorData[1]);
            			int ID = Integer.parseInt(warriorData[2]);
            			int AGE = Integer.parseInt(warriorData[4]);
            			double HP = Double.parseDouble(warriorData[5]);
            			double OP = Double.parseDouble(warriorData[6]);
            			double DS = Double.parseDouble(warriorData[7]);
            			int ISize = Integer.parseInt(warriorData[8]);
            			String moves = warriorData[9];
            			
            			if(warriorData[3].equals("Stone")){
            				warrior = new StoneWarrior_22804897(R, C, warriorRow, warriorCol, ID, AGE, HP, OP, DS, 0, ISize, moves);
            				
            				warrior.setType("S");
            				warrior.setMovements(moves);
            				warrior.set_BaseDefensiveStrength(DS);
            				warrior.setInventorySize(ISize);
            				warriors[x] = warrior;
            				if(warriors[x].getHealth() <= 10){
            					warrior.set_specialAbilityOnStartUp(true);
            				}

                		}
            			
            			else if(warriorData[3].equals("Flame")){
            				warrior = new FlameWarrior_22804897(R, C, warriorRow, warriorCol, ID, AGE, HP, OP, DS, 0, ISize, moves);
            				
            				warrior.setType("F");
            				warrior.setMovements(moves);
            				warrior.set_BaseDefensiveStrength(DS);
            				warrior.setInventorySize(ISize);
            				warriors[x] = warrior;
            				if(warriors[x].getHealth() <= 10){
            					warrior.set_specialAbilityOnStartUp(true);
            				}
                			
                		}
            			else if(warriorData[3].equals("Air")){
            				warrior = new AirWarrior_22804897(R, C, warriorRow, warriorCol, ID, AGE, HP, OP, DS, 0, ISize, moves);
            				
            				warrior.setType("A");
            				warrior.setMovements(moves);
            				warrior.set_BaseDefensiveStrength(DS);
            				warrior.setInventorySize(ISize);
            				warriors[x] = warrior;
            				if(warriors[x].getHealth() <= 10){
            					warrior.set_specialAbilityOnStartUp(true);
            				}
                			
                		}
            			else if(warriorData[3].equals("Water")){
            				warrior = new WaterWarrior_22804897(R, C, warriorRow, warriorCol, ID, AGE, HP, OP, DS, 0, ISize, moves);
            				
            				warrior.setType("W");
            				warrior.setMovements(moves);
            				warrior.set_BaseDefensiveStrength(DS);
            				warrior.setInventorySize(ISize);
            				warriors[x] = warrior;
            				if(warriors[x].getHealth() <= 10){
            					warrior.set_specialAbilityOnStartUp(true);
            				}
                			
                		}
            			

            		}
            		//Checking whether 10 or more warriors have been configured on the same cell anywhere on the board
            		if(numberOfWarriors >= 10){
            			int y = 0;
                		int yPlusOne = y+1;
                		int count = 0;
                		for(y = 0; y < numberOfWarriors; y++){
                			if(yPlusOne == numberOfWarriors-1){
                				break;
                			}
                			else if(yPlusOne > numberOfWarriors-1){
                				break;
                			}
                			else if((warriors[y].getRow() == warriors[yPlusOne].getRow()) && (warriors[y].getCol() == warriors[yPlusOne].getCol())){
                				count += 1;
                			}
                				
                			if(count>=10){
                				System.out.println("Error: more than 10 warrior pieces were configured at the same position on the game grid");
                				System.exit(0);
                			}
                		}
            		}
            	}
            	
            	else if (line.contains("Water")){
            		int waterPieces = Integer.parseInt(parts[1]);
            		isWaterPieces = true;
            		//Adding water pieces to the appropriate 2d arrays for board processing
            		for(int i = 0; i < waterPieces; i++){
            			String waterLine = in.readLine();
            			String[] waterLocation = waterLine.split(" ");
            			//Sets position of water piece to a 1's and 0's grid/array to prepare for the water simulation
            			sim.setAlive(Integer.parseInt(waterLocation[0]), Integer.parseInt(waterLocation[1]));
            			//Checking whether more than one water piece is configured on one position
            			if(board[Integer.parseInt(waterLocation[0])][Integer.parseInt(waterLocation[1])] == "w"){
            				System.out.println("Error: multiple water pieces were configured at the same position on the game grid");
							System.exit(0);
            			}
            			board[Integer.parseInt(waterLocation[0])][Integer.parseInt(waterLocation[1])] = "w";
            		}
            	}
            	
            	else if(line.contains("Magic Crystal")){
            		int numberOfCrystals = Integer.parseInt(parts[2]);
            		if(numberOfCrystals > 1){
            			System.out.println("Error: multiple magic crystal pieces configured on the grid.");
            			System.exit(0);
            		}
            		else{
            			String locationLine = in.readLine();
            			String[] crystalLocation = locationLine.split(" ");
            			crystalRow = Integer.parseInt(crystalLocation[0]);
            			crystalCol = Integer.parseInt(crystalLocation[1]);
            			board[crystalRow][crystalCol] = "c";
            			gameContainsCrystal = true;
            		}
            	}
            	
            	else if(line.contains("Weapon")){
            		gameContainsWeapons = true;
            		numberOfWeapons = Integer.parseInt(parts[1]);
            		for(int w = 0; w < R; w++){
        				for(int x = 0; x < C; x++){
        					
        					weapons_OPandLocation[w][x] = 0;
        					
        				}
        			}
            		for(int i = 0; i < numberOfWeapons; i++){
            			String locationLine = in.readLine();
            			weaponData = locationLine.split(" ");
            			
            			weapons_OPandLocation[Integer.parseInt(weaponData[0])][Integer.parseInt(weaponData[1])] = Integer.parseInt(weaponData[2]);
            			
            			board[Integer.parseInt(weaponData[0])][Integer.parseInt(weaponData[1])] = "x";
            		}
            	}
            	
            }//end while loop
        	
      
        	
        	//Place initial states of the warriors onto the board
        	updateWarriorsOnBoard(numberOfWarriors, warriors, board, R, C);
        	
        	//PRINT INITIAL STATS
        	for(int y = 0; y < numberOfWarriors; y++){
        		warriors[y].warriorToString();
        	}
        	System.out.println();
        	
        	checkWhetherGameStartsWithSingleWarrior(board, R, C);
        	
        	//Check to see if any warriors started with health <= 10, and if so performing its special ability
        	checkIfWarriorStartsWithLowHealth(warriors, numberOfWarriors);
        	
        	//------------------------------------------------------------------------------------------------------------------------------------------------------
        	//INITIATING GAME SEQUENCE ITERATIONS
        	for (int i = 1; i <= I; i++){
        		//Magic Crystal check
        		if(gameContainsCrystal = true && magicCrystalActivated == false){
        			int airTally = 0;
        			int stoneTally = 0;
        			int flameTally = 0;
        			int waterTally = 0;
        			
        			int down = crystalRow + 1;//Row + 1
        			int up = crystalRow - 1;//Row - 1
        			int left = crystalCol - 1; //Col - 1
        			int right = crystalCol + 1;//Col + 1
        			
        			if(down>R-1){
        				down = 0;
        			}
        			if(up<0){
        				up = R-1;
        			}
        			if(left<0){
        				left = C-1;
        			}
        			if(right > C-1){
        				right = 0;
        			}
        			if(left<0 && down<=R-1){//z
        				left = C-1;
        			}
        			if(left<0 && up>=0){//q
        				left = C-1;
        			}
        			if(right>R-1 && up>=0){//e
        				right = 0;
        				
        			}
        			if(right > R-1 && down<=R-1){//c
        				right = 0;
        			}
        			
        			if(board[down][left].equals("A") || board[down][left].equals("S") || board[down][left].equals("F") || board[down][left].equals("W")){
        				if(board[down][left].equals("A")){
        					airTally++;
        				}
        				else if(board[down][left].equals("S")){
        					stoneTally++;
        				}
        				else if(board[down][left].equals("F")){
        					flameTally++;
        				}
        				else if(board[down][left].equals("W")){
        					waterTally++;
        				}
        				
        			}
        			if(board[down][right].equals("A") || board[down][right].equals("S") || board[down][right].equals("F") || board[down][right].equals("W")){
        				if(board[down][right].equals("A")){
        					airTally++;
        				}
        				else if(board[down][right].equals("S")){
        					stoneTally++;
        				}
        				else if(board[down][right].equals("F")){
        					flameTally++;
        				}
        				else if(board[down][right].equals("W")){
        					waterTally++;
        				}
        			}
        			if(board[up][right].equals("A") || board[up][right].equals("S") || board[up][right].equals("F") || board[up][right].equals("W")){
        				if(board[up][right].equals("A")){
        					airTally++;
        				}
        				else if(board[up][right].equals("S")){
        					stoneTally++;
        				}
        				else if(board[up][right].equals("F")){
        					flameTally++;
        				}
        				else if(board[up][right].equals("W")){
        					waterTally++;
        				}
        			}
        			if(board[up][left].equals("A") || board[up][left].equals("S") || board[up][left].equals("F") || board[up][left].equals("W")){
        				if(board[up][left].equals("A")){
        					airTally++;
        				}
        				else if(board[up][left].equals("S")){
        					stoneTally++;
        				}
        				else if(board[up][left].equals("F")){
        					flameTally++;
        				}
        				else if(board[up][left].equals("W")){
        					waterTally++;
        				}
        			}
        			if(airTally == 1 && stoneTally == 1 && flameTally == 1 && waterTally == 1){
        				magicCrystalActivated = true;
        				board[crystalRow][crystalCol] = "";
        				System.out.println("The Magic Crystal has been activated! Four warriors remain...");
        				for(int c = 0; c < numberOfWarriors; c++){
        					if((warriors[c].getRow() != down && warriors[c].getCol() != left) && (warriors[c].getRow() != down && warriors[c].getCol() != right) 
        						&& (warriors[c].getRow() != up && warriors[c].getCol() != right) && (warriors[c].getRow() != up && warriors[c].getCol() != left))
        					{
        						warriors[c].setDeath(true);
        					}
        				}
        				
        			}
        		}
        		
        		//CHECKING NEIHBORHOOD OF WARRIORS FOR WATER PIECES
        		checkWarriorsNeighborhoodForWaterPieces(warriors, numberOfWarriors, R, C, board);
        		
        		//BATTLE CODE
        		warriorBattle(board, warriors, numberOfWarriors, R, C);
        		
        		//Checking if multiple of the same type of warriors are on the same position and increments their defensiveStrength if there are
        		for(int x = 0; x < numberOfWarriors; x++){
        			for(int y = 0; y < numberOfWarriors; y++){
        				if(x != y){
        					if(warriors[x].getType().equals(warriors[y].getType()) && warriors[x].getRow() == warriors[y].getRow() && warriors[x].getCol() == warriors[y].getCol()){
        						warriors[x].setDefensiveStrength(warriors[x].getDefensiveStrength() + 2.00);
        					}
        				}
        			}
        		}
        		
        		//RESET BOARD
        		for(int a = 0; a < R; a++){
            		for(int b = 0; b < C; b++){
            			if(board[a][b] != "."){
            				if(board[a][b] == "c" || board[a][b] == "x"){
            					continue;
            				}
            				else{
            					board[a][b] = null;
            				}
            			}
            					
            		}
            	}
            	
            	if(isWaterPieces == true){
            		//simulating next sequence of water pieces
            		sim.evolve();
            		//adding next generation of water pieces onto the board
                	sim.setNextGen(board);
            	}
        		
            	
        		//EXECUTE MOVES & increase age by 1
        		for(int p = 0; p < numberOfWarriors; p++){
        			warriors[p].nextMove();
        			warriors[p].setAge(warriors[p].incrementAge());
        		}
        		
        		int initial = numberOfWarriors;
        		//Death check
        		for(int y = 0; y < numberOfWarriors; y++){
        			if(warriors[y].getHealth() <= 0 && warriors[y].getIsDead() == false){
    					warriors[y].setDeath(true);
    					initial -= 1;
    					System.out.println("A warrior has left the game!");
    	        		
    				}

                }
        		
        		//Place warriors onto board
        		updateWarriorsOnBoard(numberOfWarriors, warriors, board, R, C);
        		
            	//PRINT STATS (of alive warriors)
        		for(int y = 0; y < numberOfWarriors; y++){
        			if(warriors[y].getIsDead() == false){
        				warriors[y].warriorToString();
        			}

                }
        		
        		if(gameContainsWeapons == true){
        			//Weapon pick up
            		for(int w = 0; w < numberOfWarriors; w++){
            			for(int a = 0; a < R; a++){
                			for(int b = 0; b < C; b++){
                				if(weapons_OPandLocation[a][b] != 0 && warriors[w].getRow() == a && warriors[w].getCol() == b){
                					if((warriors[w].getWeaponInventory() + 1) <= warriors[w].getInventorySize()){
                						warriors[w].setOffensivePower(warriors[w].getOffensivePower() + weapons_OPandLocation[a][b]);
                					}
                					
                                	warriors[w].addWeaponToInventory();
                                	
                            	}	
                			}
                		}
            		}
        		}
        		
    			if(initial==1 || numberOfWarriors == 1){
        			System.out.println("A warrior has been proven victor!");
        			System.exit(0);
        		}
        		
            	System.out.println();
        	}
        	//------------------------------------------------------------------------------------------------------------------------------------------------------
        	
        }//end of statistics mode
        
        else if (outputVersion == 1) {
            // warrior statistics with visualisation mode
        	WaterSimulation_22804897 sim = new WaterSimulation_22804897(R,C);
        	boolean isWaterPieces = false;
        	//EXTRACTING DATA FROM TEXT FILE
        	while(in.hasNextLine()){
            	String line = in.readLine();
            	String[] parts = line.split(" ");
            	
            	if (line.contains("Warrior")){
            		String[] warriorData = null;
            		numberOfWarriors = Integer.parseInt(parts[1]);
            		warriors = new WarriorTypeInterface_22804897[numberOfWarriors];
            		
            		if(numberOfWarriors == 0){
            			displayBoard(numberOfWarriors, warriors, board, R, C);
            			System.out.println("No warriors are left!");
            			System.exit(0);
            		}
            		
            		for(int x = 0; x < numberOfWarriors; x++){
            			String warriorLine = in.readLine();
            			warriorData = warriorLine.split(" ");
            			
            			int warriorRow = Integer.parseInt(warriorData[0]);
            			int warriorCol = Integer.parseInt(warriorData[1]);
            			int ID = Integer.parseInt(warriorData[2]);
            			int AGE = Integer.parseInt(warriorData[4]);
            			double HP = Double.parseDouble(warriorData[5]);
            			double OP = Double.parseDouble(warriorData[6]);
            			double DS = Double.parseDouble(warriorData[7]);
            			int ISize = Integer.parseInt(warriorData[8]);
            			String moves = warriorData[9];
            			
            			if(warriorData[3].equals("Stone")){
            				warrior = new StoneWarrior_22804897(R, C, warriorRow, warriorCol, ID, AGE, HP, OP, DS, 0, ISize, moves);
            				warrior.setType("S");
            				warrior.setMovements(moves);
            				warrior.set_BaseOffensivePower(OP);
            				warrior.set_BaseDefensiveStrength(DS);
            				warrior.setInventorySize(ISize);
            				warriors[x] = warrior;
            				if(warriors[x].getHealth() <= 10){
            					warrior.set_specialAbilityOnStartUp(true);
            				}

                		}
            			
            			else if(warriorData[3].equals("Flame")){
            				warrior = new FlameWarrior_22804897(R, C, warriorRow, warriorCol, ID, AGE, HP, OP, DS, 0, ISize, moves);
            				
            				warrior.setType("F");
            				warrior.setMovements(moves);
            				warrior.set_BaseOffensivePower(OP);
            				warrior.set_BaseDefensiveStrength(DS);
            				warrior.setInventorySize(ISize);
            				warriors[x] = warrior;
            				if(warriors[x].getHealth() <= 10){
            					warrior.set_specialAbilityOnStartUp(true);
            				}
                			
                		}
            			else if(warriorData[3].equals("Air")){
            				warrior = new AirWarrior_22804897(R, C, warriorRow, warriorCol, ID, AGE, HP, OP, DS, 0, ISize, moves);
            				
            				warrior.setType("A");
            				warrior.setMovements(moves);
            				warrior.set_BaseOffensivePower(OP);
            				warrior.set_BaseDefensiveStrength(DS);
            				warrior.setInventorySize(ISize);
            				warriors[x] = warrior;
            				if(warriors[x].getHealth() <= 10){
            					warrior.set_specialAbilityOnStartUp(true);
            				}
                			
                		}
            			else if(warriorData[3].equals("Water")){
            				warrior = new WaterWarrior_22804897(R, C, warriorRow, warriorCol, ID, AGE, HP, OP, DS, 0, ISize, moves);
            				
            				warrior.setType("W");
            				warrior.setMovements(moves);
            				warrior.set_BaseOffensivePower(OP);
            				warrior.set_BaseDefensiveStrength(DS);
            				warrior.setInventorySize(ISize);
            				warriors[x] = warrior;
            				if(warriors[x].getHealth() <= 10){
            					warrior.set_specialAbilityOnStartUp(true);
            				}
                			
                		}
            			

            		}
            		//Checking whether 10 or more warriors have been configured on the same cell anywhere on the board
            		if(numberOfWarriors >= 10){
            			int y = 0;
                		int yPlusOne = y+1;
                		int count = 0;
                		for(y = 0; y < numberOfWarriors; y++){
                			if(yPlusOne == numberOfWarriors-1){
                				break;
                			}
                			else if(yPlusOne > numberOfWarriors-1){
                				break;
                			}
                			else if((warriors[y].getRow() == warriors[yPlusOne].getRow()) && (warriors[y].getCol() == warriors[yPlusOne].getCol())){
                				count += 1;
                			}
                				
                			if(count>=10){
                				System.out.println("Error: more than 10 warrior pieces were configured at the same position on the game grid");
                				System.exit(0);
                			}
                		}
            		}
            	}
            	
            	else if (line.contains("Water")){
            		isWaterPieces = true;
            		int waterPieces = Integer.parseInt(parts[1]);
            		
            		//Adding water pieces to the appropriate 2d arrays for board processing
            		for(int i = 0; i < waterPieces; i++){
            			String waterLine = in.readLine();
            			String[] waterLocation = waterLine.split(" ");
            			//Sets position of water piece to a 1's and 0's grid/array to prepare for the water simulation
            			sim.setAlive(Integer.parseInt(waterLocation[0]), Integer.parseInt(waterLocation[1]));
            			//Checking whether more than one water piece is configured on one position
            			if(board[Integer.parseInt(waterLocation[0])][Integer.parseInt(waterLocation[1])] == "w"){
            				System.out.println("Error: multiple water pieces were configured at the same position on the game grid");
							System.exit(0);
            			}
            			board[Integer.parseInt(waterLocation[0])][Integer.parseInt(waterLocation[1])] = "w";
            		}
            	}
            	
            	else if(line.contains("Magic Crystal")){
            		int numberOfCrystals = Integer.parseInt(parts[2]);
            		if(numberOfCrystals > 1){
            			System.out.println("Error: multiple magic crystal pieces configured on the grid.");
            			System.exit(0);
            		}
            		else{
            			String locationLine = in.readLine();
            			String[] crystalLocation = locationLine.split(" ");
            			crystalRow = Integer.parseInt(crystalLocation[0]);
            			crystalCol = Integer.parseInt(crystalLocation[1]);
            			board[crystalRow][crystalCol] = "c";
            			gameContainsCrystal = true;
            		}
            	}
            	
            	else if(line.contains("Weapon")){
            		gameContainsWeapons = true;
            		numberOfWeapons = Integer.parseInt(parts[1]);
            		for(int w = 0; w < R; w++){
        				for(int x = 0; x < C; x++){
        					
        					weapons_OPandLocation[w][x] = 0;
        					
        				}
        			}
            		for(int i = 0; i < numberOfWeapons; i++){
            			String locationLine = in.readLine();
            			weaponData = locationLine.split(" ");
            			
            			weapons_OPandLocation[Integer.parseInt(weaponData[0])][Integer.parseInt(weaponData[1])] = Integer.parseInt(weaponData[2]);
            			
            			board[Integer.parseInt(weaponData[0])][Integer.parseInt(weaponData[1])] = "x";
            		}
            	}
            	
            	
            }//end while loop
        	
        	//PRINT INITIAL BOARD
        	displayBoard(numberOfWarriors, warriors, board, R, C);
        	
        	//PRINT INITIAL STATS
        	for(int y = 0; y < numberOfWarriors; y++){
        		warriors[y].warriorToString();
        	}
        	System.out.println();
        	
        	checkWhetherGameStartsWithSingleWarrior(board, R, C);
        	
        	//Check to see if any warriors started with health <= 10, and if so performing its special ability
        	checkIfWarriorStartsWithLowHealth(warriors, numberOfWarriors);
        	//------------------------------------------------------------------------------------------------------------------------------------------------------
        	//INITIATING GAME SEQUENCE ITERATIONS
        	for (int i = 1; i <= I; i++){
        		//Magic Crystal check
        		if(gameContainsCrystal = true && magicCrystalActivated == false){
        			int airTally = 0;
        			int stoneTally = 0;
        			int flameTally = 0;
        			int waterTally = 0;
        			
        			int down = crystalRow + 1;//Row + 1
        			int up = crystalRow - 1;//Row - 1
        			int left = crystalCol - 1; //Col - 1
        			int right = crystalCol + 1;//Col + 1
        			
        			if(down>R-1){
        				down = 0;
        			}
        			if(up<0){
        				up = R-1;
        			}
        			if(left<0){
        				left = C-1;
        			}
        			if(right > C-1){
        				right = 0;
        			}
        			if(left<0 && down<=R-1){//z
        				left = C-1;
        			}
        			if(left<0 && up>=0){//q
        				left = C-1;
        			}
        			if(right>R-1 && up>=0){//e
        				right = 0;
        				
        			}
        			if(right > R-1 && down<=R-1){//c
        				right = 0;
        			}
        			
        			if(board[down][left].equals("A") || board[down][left].equals("S") || board[down][left].equals("F") || board[down][left].equals("W")){
        				if(board[down][left].equals("A")){
        					airTally++;
        				}
        				else if(board[down][left].equals("S")){
        					stoneTally++;
        				}
        				else if(board[down][left].equals("F")){
        					flameTally++;
        				}
        				else if(board[down][left].equals("W")){
        					waterTally++;
        				}
        				
        			}
        			if(board[down][right].equals("A") || board[down][right].equals("S") || board[down][right].equals("F") || board[down][right].equals("W")){
        				if(board[down][right].equals("A")){
        					airTally++;
        				}
        				else if(board[down][right].equals("S")){
        					stoneTally++;
        				}
        				else if(board[down][right].equals("F")){
        					flameTally++;
        				}
        				else if(board[down][right].equals("W")){
        					waterTally++;
        				}
        			}
        			if(board[up][right].equals("A") || board[up][right].equals("S") || board[up][right].equals("F") || board[up][right].equals("W")){
        				if(board[up][right].equals("A")){
        					airTally++;
        				}
        				else if(board[up][right].equals("S")){
        					stoneTally++;
        				}
        				else if(board[up][right].equals("F")){
        					flameTally++;
        				}
        				else if(board[up][right].equals("W")){
        					waterTally++;
        				}
        			}
        			if(board[up][left].equals("A") || board[up][left].equals("S") || board[up][left].equals("F") || board[up][left].equals("W")){
        				if(board[up][left].equals("A")){
        					airTally++;
        				}
        				else if(board[up][left].equals("S")){
        					stoneTally++;
        				}
        				else if(board[up][left].equals("F")){
        					flameTally++;
        				}
        				else if(board[up][left].equals("W")){
        					waterTally++;
        				}
        			}
        			if(airTally == 1 && stoneTally == 1 && flameTally == 1 && waterTally == 1){
        				magicCrystalActivated = true;
        				board[crystalRow][crystalCol] = "";
        				System.out.println("The Magic Crystal has been activated! Four warriors remain...");
        				for(int c = 0; c < numberOfWarriors; c++){
        					if((warriors[c].getRow() != down && warriors[c].getCol() != left) && (warriors[c].getRow() != down && warriors[c].getCol() != right) 
        						&& (warriors[c].getRow() != up && warriors[c].getCol() != right) && (warriors[c].getRow() != up && warriors[c].getCol() != left))
        					{
        						warriors[c].setDeath(true);
        					}
        				}
        				
        			}
        		}
        		
        		//CHECKING NEIHBORHOOD OF WARRIORS FOR WATER PIECES
        		checkWarriorsNeighborhoodForWaterPieces(warriors, numberOfWarriors, R, C, board);
        		
        		//BATTLE CODE
        		warriorBattle(board, warriors, numberOfWarriors, R, C);
        		
        		//Checking if multiple of the same type of warriors are on the same position and increments their defensiveStrength if there are
        		for(int x = 0; x < numberOfWarriors; x++){
        			for(int y = 0; y < numberOfWarriors; y++){
        				if(x != y){
        					if(warriors[x].getType().equals(warriors[y].getType()) && warriors[x].getRow() == warriors[y].getRow() && warriors[x].getCol() == warriors[y].getCol()){
        						warriors[x].setDefensiveStrength(warriors[x].getDefensiveStrength() + 2.00);
        					}
        				}
        			}
        		}
        		
        		//RESET BOARD
            	for(int a = 0; a < R; a++){
            		for(int b = 0; b < C; b++){
            			if(board[a][b] != "."){
            				if(board[a][b] == "c" || board[a][b] == "x"){
            					continue;
            				}
            				else{
            					board[a][b] = null;
            				}
            			}
            					
            		}
            	}
            	
            	if(isWaterPieces == true){
            		//simulating next sequence of water pieces
            		sim.evolve();
            		//adding next generation of water pieces onto the board
                	sim.setNextGen(board);
            	}
            	
        		//EXECUTE MOVES & increase age by 1
        		for(int p = 0; p < numberOfWarriors; p++){
        			warriors[p].nextMove();
        			warriors[p].setAge(warriors[p].incrementAge());
        		}
        		
        		int initial = numberOfWarriors;
        		//Death check
        		for(int y = 0; y < numberOfWarriors; y++){
        			if(warriors[y].getHealth() <= 0 && warriors[y].getIsDead() == false){
    					warriors[y].setDeath(true);
    					initial -= 1;
    					System.out.println("A warrior has left the game!");
    	        		
    				}

                }
        		
        		if(initial==0){
    				System.out.println("No warriors are left!");
        			System.exit(0);
    			}
        		
        		
        		//PRINT BOARD
        		displayBoard(numberOfWarriors, warriors, board, R, C);
        		
            	//PRINT STATS (of alive warriors)
        		for(int y = 0; y < numberOfWarriors; y++){
        			if(warriors[y].getIsDead() == false){
        				warriors[y].warriorToString();
        			}

                }
        		
        		if(gameContainsWeapons == true){
        			//Weapon pick up
            		for(int w = 0; w < numberOfWarriors; w++){
            			for(int a = 0; a < R; a++){
                			for(int b = 0; b < C; b++){
                				if(weapons_OPandLocation[a][b] != 0 && warriors[w].getRow() == a && warriors[w].getCol() == b){
                					if((warriors[w].getWeaponInventory() + 1) <= warriors[w].getInventorySize()){
                						warriors[w].setOffensivePower(warriors[w].getOffensivePower() + weapons_OPandLocation[a][b]);
                					}
                                	warriors[w].addWeaponToInventory();
                            	}	
                			}
                		}
            		}
        		}
        		
    			if(initial==1 || numberOfWarriors == 1){
        			System.out.println("A warrior has been proven victor!");
        			System.exit(0);
        		}
    			
            	System.out.println();
        	}
        	//------------------------------------------------------------------------------------------------------------------------------------------------------
        	
        }//end of visualisation mode
        
    }
	
	public static void displayBoard(int numberOfWarriors, WarriorTypeInterface_22804897[] warriors, String[][] board, int R, int C){
		
		//PLACE WARRIORS INTO BOARD
    	for(int x = 0; x < numberOfWarriors; x++){
    		
    		if(warriors[x].getType().equals("S")){
    			
    			int r = warriors[x].getRow();
    			int c = warriors[x].getCol();
    			if (board[r][c] == null || board[r][c] == "."){
    				board[r][c] = "";
    			}
    			
    			board[r][c] += warriors[x].getType();
    			
    			if(warriors[x].getIsDead() == true){
    				board[r][c] = ".";
    			}
    		}
    		else if(warriors[x].getType().equals("F")){
    			int r = warriors[x].getRow();
    			int c = warriors[x].getCol();
    			if (board[r][c] == null || board[r][c] == "."){
    				board[r][c] = "";
    			}

    			board[r][c] += warriors[x].getType();
    			if(warriors[x].getIsDead() == true){
    				board[r][c] = ".";
    			}
    		}
    		else if(warriors[x].getType().equals("W")){
    			int r = warriors[x].getRow();
    			int c = warriors[x].getCol();
    			if (board[r][c] == null || board[r][c] == "."){
    				board[r][c] = "";
    			}

    			board[r][c] += warriors[x].getType();
    			if(warriors[x].getIsDead() == true){
    				board[r][c] = ".";
    			}
    			
    		}
    		else if(warriors[x].getType().equals("A")){
    			int r = warriors[x].getRow();
    			int c = warriors[x].getCol();
    			if (board[r][c] == null || board[r][c] == "."){
    				board[r][c] = "";
    			}

    			board[r][c] += warriors[x].getType();
    			if(warriors[x].getIsDead() == true){
    				board[r][c] = ".";
    			}
    		}
    		
    	}
    	
    	//Checking whether 10 or more warriors have moved onto the same cell on the board
    	for (int l = 0; l<R; l++){
			for(int m = 0; m<C; m++){
				if(board[l][m] == null){
					continue;
				}
				if(board[l][m].length() >= 10){
					String cell = board[l][m];
					int warriorCount = 0;
					for(int p = 0; p < board[l][m].length(); p++){
						if(Character.toString(cell.charAt(p)).equals("S") || Character.toString(cell.charAt(p)).equals("F") || Character.toString(cell.charAt(p)).equals("W") || Character.toString(cell.charAt(p)).equals("A")){
							warriorCount += 1;
						}
					}
					if(warriorCount >= 10){
						System.out.println("Error: warrior limit exceeded in cell " + l+","+m);
						System.exit(0);
					}
				}
			}
    	}

		boolean isWeapon = false;
    	//PRINT BOARD
    	for (int h = 0; h<R; h++){
			for(int j = 0; j<C; j++){
				if(board[h][j] == null){
					board[h][j] = ".";
					System.out.print(board[h][j] + " ");
				}
				
				else{
					//checking whether cell has multiple warriors and if so, printing number of warriors on cell
					if(board[h][j].length() > 1){
						isWeapon = false;
						for(int i = 0; i < board[h][j].length(); i++){
							String cell = board[h][j];
							if(Character.toString(cell.charAt(i)).equals("x")){
								isWeapon = true;
								cell = cell.substring(0, i) + cell.substring(i+1, cell.length());
								board[h][j] = cell;
							}
						}
						if(isWeapon == false){
							int numWarriors = board[h][j].length();
		    				System.out.print(Integer.toString(numWarriors));
		    				board[h][j] = "";
						}
						
    				}
					System.out.print(board[h][j] + " ");
				}
			}
			System.out.println();
		}

    	
    	
	}
	//This method is only for the statistics mode (i.e. outputVersion == 0),
	//and it is basically the displayBoard method but edited so that it doesn't print the board
	public static void updateWarriorsOnBoard(int numberOfWarriors, WarriorTypeInterface_22804897[] warriors, String[][] board, int R, int C){
		//PLACE WARRIORS INTO BOARD
    	for(int x = 0; x < numberOfWarriors; x++){
    		
    		if(warriors[x].getType().equals("S")){
    			
    			int r = warriors[x].getRow();
    			int c = warriors[x].getCol();
    			if (board[r][c] == null || board[r][c] == "."){
    				board[r][c] = "";
    			}
    			
    			board[r][c] += warriors[x].getType();
    			
    			if(warriors[x].getIsDead() == true){
    				board[r][c] = ".";
    			}
    		}
    		else if(warriors[x].getType().equals("F")){
    			int r = warriors[x].getRow();
    			int c = warriors[x].getCol();
    			if (board[r][c] == null || board[r][c] == "."){
    				board[r][c] = "";
    			}

    			board[r][c] += warriors[x].getType();
    			if(warriors[x].getIsDead() == true){
    				board[r][c] = ".";
    			}
    		}
    		else if(warriors[x].getType().equals("W")){
    			int r = warriors[x].getRow();
    			int c = warriors[x].getCol();
    			if (board[r][c] == null || board[r][c] == "."){
    				board[r][c] = "";
    			}

    			board[r][c] += warriors[x].getType();
    			if(warriors[x].getIsDead() == true){
    				board[r][c] = ".";
    			}
    			
    		}
    		else if(warriors[x].getType().equals("A")){
    			int r = warriors[x].getRow();
    			int c = warriors[x].getCol();
    			if (board[r][c] == null || board[r][c] == "."){
    				board[r][c] = "";
    			}

    			board[r][c] += warriors[x].getType();
    			if(warriors[x].getIsDead() == true){
    				board[r][c] = ".";
    			}
    		}
    		
    	}
    	//Checking whether 10 or more warriors have moved onto the same cell on the board
    	for (int l = 0; l<R; l++){
			for(int m = 0; m<C; m++){
				if(board[l][m] == null){
					continue;
				}
				if(board[l][m].length() >= 10){
					String cell = board[l][m];
					int warriorCount = 0;
					for(int p = 0; p < board[l][m].length(); p++){
						if(Character.toString(cell.charAt(p)).equals("S") || Character.toString(cell.charAt(p)).equals("F") || Character.toString(cell.charAt(p)).equals("W") || Character.toString(cell.charAt(p)).equals("A")){
							warriorCount += 1;
						}
					}
					if(warriorCount >= 10){
						System.out.println("Error: warrior limit exceeded in cell " + l+","+m);
						System.exit(0);
					}
				}
			}
    	}
    	//Replacing null positions with dots and checking if multiple warriors are on a position,
    	//and changing the board position to an empty string if so
    	for (int h = 0; h<R; h++){
			for(int j = 0; j<C; j++){
				if(board[h][j] == null){
					board[h][j] = ".";
				}
				
				else{
					//checking whether cell has multiple warriors and if so, printing number of warriors on cell
					if(board[h][j].length() > 1){
						//int numWarriors = board[h][j].length();
    					board[h][j] = "";
    				}
				}
			}
		}
	}
	
	public static void warriorBattle(String[][] board, WarriorTypeInterface_22804897[] warriors, int numberOfWarriors, int R, int C){
		
		int x = 0;
		
		for(x = 0; x < numberOfWarriors; x++){
			for(int m = 0; m < R; m++){
        		for(int n = 0; n < C; n++){
        			
        			if(board[m][n].equals( warriors[x].getType() ) ){
            			int down = warriors[x].getRow() + 1;//Row + 1
                		int up = warriors[x].getRow() - 1;//Row - 1
                		int left = warriors[x].getCol() - 1; //Col - 1
                		int right = warriors[x].getCol() + 1;//Col + 1
                			
                		if(down>R-1){
                			down = 0;
                		}
                		if(up<0){
                			up = R-1;
               			}
               			if(left<0){
               				left = C-1;
               			}
               			if(right > C-1){
               				right = 0;
               			}
               			if(left<0 && down<=R-1){//z
               				left = C-1;
               			}
               			if(left<0 && up>=0){//q
               				left = C-1;
               			}
               			if(right>R-1 && up>=0){//e
               				right = 0;
               				
                		}
                		if(right > R-1 && down<=R-1){//c
                			right = 0;
               			}
               			
                		int xPlusOne = x + 1;
                		
                		if(xPlusOne > numberOfWarriors-1){
            				break;
            			}
                		if(board[down][warriors[x].getCol()].equals(warriors[xPlusOne].getType()) || board[up][warriors[x].getCol()].equals(warriors[xPlusOne].getType()) 
               				|| board[warriors[x].getRow()][left].equals(warriors[xPlusOne].getType()) || board[warriors[x].getRow()][right].equals(warriors[xPlusOne].getType()) 
                			|| board[down][left].equals(warriors[xPlusOne].getType()) || board[up][left].equals(warriors[xPlusOne].getType()) 
               				|| board[up][right].equals(warriors[xPlusOne].getType()) || board[down][right].equals(warriors[xPlusOne].getType()))
               			{
               				
               				if(warriors[x].getDefensiveStrength() < warriors[xPlusOne].getDefensiveStrength()){
                				
                				warriors[x].setHealth(warriors[x].getHealth() - warriors[xPlusOne].getOffensivePower());
                				
                				
                			}
               				
               				else if(warriors[x].getDefensiveStrength() > warriors[xPlusOne].getDefensiveStrength()){
                				
                				warriors[xPlusOne].setHealth(warriors[xPlusOne].getHealth() - warriors[x].getOffensivePower());
                				
                				
                			}
               				
               				specialAbilityChecks(warriors, x, numberOfWarriors);
                			
               			}//End of warrior-battle if statement
                		//This is if there is no warriors in the current warriors neighbourhood, then special ability checks are still performed:
                		else{
                			specialAbilityChecks(warriors, x, numberOfWarriors);
                		}
            		}

        		}
        		
    		}
		}
	}
	
	public static void specialAbilityChecks(WarriorTypeInterface_22804897[] warriors, int x, int numberOfWarriors){
		//Checking quantity of each type of warrior (besides water)
		int numStone = 0;
		int numAir = 0;
		int numFlame = 0;
		for(int u = 0; u < numberOfWarriors; u++){
			if(warriors[u].getType().equals("S")){
				numStone++;
			}
			else if (warriors[u].getType().equals("A")){
				numAir++;
			}
			else if(warriors[u].getType().equals("F")){
				numFlame++;
			}
					
		}
		//Special ability checks:
		if(warriors[x].getType().equals("W")){
			if(warriors[x].getHealth() <= 10 && warriors[x].getSpecialAbilityActivity() == false){
				warriors[x].performSpecialAbility();
				warriors[x].setSpecialAbilityActivity(true);
			}
		}
	
		else if(warriors[x].getType().equals("A")){
			
			if(warriors[x].getSpecialAbilityActivity() == false){
				warriors[x].setAbilityIterations(3*numAir);
				
				warriors[x].setAbilityCounter(warriors[x].getAbilityIterations());
			}
	
			if(warriors[x].getHealth() <= 10 && warriors[x].getAbilityCounter() > 0){
				if(warriors[x].getSpecialAbilityActivity() == false){
					System.out.println("Special ability performed by air warrior!");
					warriors[x].performSpecialAbility();
				}
				warriors[x].setSpecialAbilityActivity(true);
				
				warriors[x].setAbilityCounter(warriors[x].getAbilityCounter() - 1);
		
			}
			else if(warriors[x].getSpecialAbilityActivity() == true && warriors[x].getAbilityCounter() > 0 && warriors[x].getHealth() > 10){
				warriors[x].performSpecialAbility();
				
				warriors[x].setAbilityCounter(warriors[x].getAbilityCounter() - 1);
			}
			else if(warriors[x].getAbilityCounter() == 0 && warriors[x].getSpecialAbilityActivity() == true){
				
				warriors[x].setOffensivePower(warriors[x].getOffensivePower() - 30);
			}
	
		}
	
		else if(warriors[x].getType().equals("S")){
			
			
			if(warriors[x].getSpecialAbilityActivity() == false){
				warriors[x].setAbilityIterations(4*numStone);
				warriors[x].setAbilityCounter(warriors[x].getAbilityIterations());
				warriors[x].set_BaseDefensiveStrength(warriors[x].getDefensiveStrength());
			}
	
			if(warriors[x].getHealth() <= 10 && warriors[x].getAbilityCounter() > 0){
				if(warriors[x].getSpecialAbilityActivity() == false){
					warriors[x].setHealth(warriors[x].getHealth() - 3);
					System.out.println("Special ability performed by stone warrior!");
				}
				warriors[x].setSpecialAbilityActivity(true);
				
				warriors[x].performSpecialAbility();
				warriors[x].setAbilityCounter(warriors[x].getAbilityCounter() - 1);
		
			}
			else if(warriors[x].getSpecialAbilityActivity() == true && warriors[x].getAbilityCounter() > 0 && warriors[x].getHealth() > 10){
				warriors[x].performSpecialAbility();
				warriors[x].setAbilityCounter(warriors[x].getAbilityCounter() - 1);
			}
			else if(warriors[x].getAbilityCounter() == 0 && warriors[x].getSpecialAbilityActivity() == true){
				warriors[x].setDefensiveStrength(warriors[x].get_BaseDefensiveStrength());
			}
		}
	
		else if(warriors[x].getType().equals("F")){
			
			if(warriors[x].getSpecialAbilityActivity() == false){
				warriors[x].setAbilityIterations(2*numFlame);
				warriors[x].setAbilityCounter(warriors[x].getAbilityIterations());
			}
	
			if(warriors[x].getHealth() <= 10 && warriors[x].getAbilityCounter() > 0){
				if(warriors[x].getSpecialAbilityActivity() == false){
					System.out.println("Special ability performed by flame warrior!");
				}
				warriors[x].setSpecialAbilityActivity(true);
				
				warriors[x].performSpecialAbility();
				
				warriors[x].setAbilityCounter(warriors[x].getAbilityCounter() - 1);
		
			}
			else if(warriors[x].getSpecialAbilityActivity() == true && warriors[x].getAbilityCounter() > 0 && warriors[x].getHealth() > 10){
				warriors[x].performSpecialAbility();
				
				warriors[x].setAbilityCounter(warriors[x].getAbilityCounter() - 1);
			}
			else if(warriors[x].getAbilityCounter() == 0 && warriors[x].getSpecialAbilityActivity() == true){
				
				if(warriors[x].get_BaseDefensiveStrength() > 70){
					warriors[x].setDefensiveStrength(warriors[x].get_BaseDefensiveStrength());
				}
				else{
					warriors[x].setDefensiveStrength(70);
				}
				
			}
		}
		//End of special ability checks
	}
	
	public static void checkWhetherGameStartsWithSingleWarrior(String[][] board, int R, int C){
		int warriorCounter = 0;
    	for (int l = 0; l<R; l++){
			for(int m = 0; m<C; m++){
				if(board[l][m].equals("S")){
					warriorCounter++;
				}
				if(board[l][m].equals("F")){
					warriorCounter++;
				}
				if(board[l][m].equals("A")){
					warriorCounter++;
				}
				if(board[l][m].equals("W")){
					warriorCounter++;
				}
			}
    	}
    	
    	if(warriorCounter == 1){
    		System.out.println("A warrior has been proven victor!");
    		System.exit(0);
    	}
	}
	
	public static void checkWarriorsNeighborhoodForWaterPieces(WarriorTypeInterface_22804897[] warriors, int numberOfWarriors, int R, int C, String[][] board){
		for(int p = 0; p < numberOfWarriors; p++){
			int down = warriors[p].getRow() + 1;//Row + 1
			int up = warriors[p].getRow() - 1;//Row - 1
			int left = warriors[p].getCol() - 1; //Col - 1
			int right = warriors[p].getCol() + 1;//Col + 1
			
			if(down>R-1){
				down = 0;
			}
			if(up<0){
				up = R-1;
			}
			if(left<0){
				left = C-1;
			}
			if(right > C-1){
				right = 0;
			}
			if(left<0 && down<=R-1){//z
				left = C-1;
			}
			if(left<0 && up>=0){//q
				left = C-1;
			}
			if(right>R-1 && up>=0){//e
				right = 0;
				
			}
			if(right > R-1 && down<=R-1){//c
				right = 0;
			}
			
			if(board[down][warriors[p].getCol()].equals("w") || board[up][warriors[p].getCol()].equals("w") || board[warriors[p].getRow()][left].equals("w") || board[warriors[p].getRow()][right].equals("w") || 
			   board[down][left].equals("w") || board[up][left].equals("w") || board[up][right].equals("w") || board[down][right].equals("w"))
			{
				
				warriors[p].setHealth(warriors[p].getHealth() + 3.00);
				//Check to see if current warrior is a Water Warrior, then evaluates the number of water pieces in its neighborhood 
				//and finally adds the necessary offensivePower to the Water Warrior
				if(warriors[p].getType().equals("W")){
					int numWaterPieces = 0;
						if(board[down][warriors[p].getCol()].equals("w")){
							numWaterPieces++;
						}
						if(board[up][warriors[p].getCol()].equals("w")){
							numWaterPieces++;
						}
						if(board[warriors[p].getRow()][left].equals("w")){
							numWaterPieces++;
						}
						if(board[warriors[p].getRow()][right].equals("w")){
							numWaterPieces++;
						}
						if(board[down][left].equals("w")){
							numWaterPieces++;
						}
						if(board[up][left].equals("w")){
							numWaterPieces++;
						}
						if(board[up][right].equals("w")){
							numWaterPieces++;
						}
						if(board[down][right].equals("w")){
							numWaterPieces++;
						}
     					
					if(numWaterPieces == 1){
						double newOffensivePower = warriors[p].getOffensivePower() + 1.00;
						if(newOffensivePower > 100){
							warriors[p].setOffensivePower(100.00);
						}
						else{
							warriors[p].setOffensivePower(newOffensivePower);
						}
						
					}
					else if(numWaterPieces > 1){
						double newOffensivePower1 = warriors[p].getOffensivePower() + (numWaterPieces);
						if(newOffensivePower1 > 100){
							warriors[p].setOffensivePower(100.00);
						}
						else{
							warriors[p].setOffensivePower(newOffensivePower1);
						}
						
					}
					
				}
			}
			else{
				warriors[p].setHealth(warriors[p].getHealth() - 0.50);
			}
		}
	}
	
	public static void checkIfWarriorStartsWithLowHealth(WarriorTypeInterface_22804897[] warriors, int numberOfWarriors){
		//Check to see if any warriors started with health <= 10, and if so performing its special ability
    	for(int s = 0; s < numberOfWarriors; s++){
			if(warriors[s].specialAbilityOnStartUp() == true && warriors[s].getType().equals("W") ){
    			warriors[s].performSpecialAbility();
    			warriors[s].set_specialAbilityOnStartUp(false);
    		}
			if(warriors[s].specialAbilityOnStartUp() == true && warriors[s].getType().equals("S")){
				warriors[s].performSpecialAbility();
				warriors[s].set_specialAbilityOnStartUp(false);
			}
			if(warriors[s].specialAbilityOnStartUp() == true && warriors[s].getType().equals("F")){
				warriors[s].performSpecialAbility();
				warriors[s].set_specialAbilityOnStartUp(false);
			}
			if(warriors[s].specialAbilityOnStartUp() == true && warriors[s].getType().equals("A")){
				warriors[s].performSpecialAbility();
				warriors[s].set_specialAbilityOnStartUp(false);
			}
		}
	}

}