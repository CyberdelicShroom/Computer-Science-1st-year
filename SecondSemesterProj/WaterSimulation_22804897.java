
public class WaterSimulation_22804897 {
  int R;
  int C;
  int[][] waterGrid;
  String[][] line;
  
  public WaterSimulation_22804897(int R, int C) {
  		this.R = R;
  		this.C = C;
  		this.waterGrid = new int[R][C];
  }

  public void setNextGen(String[][] board) {
      for (int x = 0; x < R; x++) {
    	 line = new String[R][C];
      	
          for (int y = 0; y < C; y++) {
          	if (waterGrid[x][y] == 0) {
                      line[x][y] = ".";
                 } else {
                     line[x][y] = "w";
                 }
          	if(line[x][y] == "w"){
          		board[x][y] = "w";
          	}
          	if(board[x][y] == null){
          		board[x][y] = ".";
          	}
              
          }
      }
  }

  public void setAlive(int x, int y) {
  	  waterGrid[x][y] = 1;
  }

  public void setDead(int x, int y) {
      waterGrid[x][y] = 0;
  }

  public int countAliveNeighbours(int x, int y) {
      int count = 0;

      count += getState(x - 1, y - 1);
      count += getState(x, y - 1);
      count += getState(x + 1, y - 1);

      count += getState(x - 1, y);
      count += getState(x + 1, y);

      count += getState(x - 1, y + 1);
      count += getState(x, y + 1);
      count += getState(x + 1, y + 1);

      return count;
  }

  public int getState(int x, int y) {
      if (x < 0 || x >= C) {
          return 0;
      }

      if (y < 0 || y >= R) {
          return 0;
      }

      return waterGrid[x][y];
  }

  public void evolve() {
      int[][] newBoard = new int[R][C];

      for (int x = 0; x < R; x++) {
          for (int y = 0; y < C; y++) {
              int aliveNeighbours = countAliveNeighbours(x, y);

              if (getState(x, y) == 1) {
                  if (aliveNeighbours < 2) {
                      newBoard[x][y] = 0;
                  } else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                      newBoard[x][y] = 1;
                  } else if (aliveNeighbours > 3) {
                      newBoard[x][y] = 0;
                  }
              } else {
                  if (aliveNeighbours == 3) {
                      newBoard[x][y] = 1;
                  }
              }

          }
      }

      this.waterGrid = newBoard;
  }

//  public static void main(String[] args) {
//  
//      WaterSimulation_22804897 sim = new WaterSimulation_22804897(5, 5);
//      int I = 3;
//      
//      sim.setAlive(2, 1);
//      sim.setAlive(2, 2);
//      sim.setAlive(2, 3);
//      sim.setAlive(2, 4);
//      
//      sim.setNextGen();
//      for(int i = 0; i<I ; i++){
//      	sim.evolve();
//      	sim.setNextGen();
//      }
//      
//
//  }
}
