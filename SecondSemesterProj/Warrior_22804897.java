import princeton.In;

public class Warrior_22804897 {

	private int R;
	private int C;
	private int Row;
	private int Col;
	private int ID;
	private int age;
	private double health;
	private double base_offensivePower;
	private double base_defensiveStrength;
	private double offensivePower;
	private double defensiveStrength;
	private int weaponInventorySize;
	private int weaponInventory;
	private String movements;
	private int moveCounter = 0;
	private String warriorType;
	private boolean isDead = false;
	private boolean specialAbilityActivity = false;
	private int abilityIterations = 0;
	private int abilityCounter = 0;
	private boolean specialAbilityOnStartUp = false;
	
	public Warrior_22804897(int R, int C, int Row, int Col, int ID, int age, double health, double offensivePower, double defensiveStrength, int weaponInventory, int weaponInventorySize, String movements){
		this.R = R;
		this.C = C;
		this.Row = Row;
		this.Col = Col;
		this.ID = ID;
		this.age = age;
		this.health = health;
		this.offensivePower = offensivePower;
		this.defensiveStrength = defensiveStrength;
		this.weaponInventory = weaponInventory;
		this.weaponInventorySize = weaponInventorySize;
		this.movements= movements;
	}
	
	public void set_specialAbilityOnStartUp(boolean specialAbilityOnStartUp){
		this.specialAbilityOnStartUp = specialAbilityOnStartUp;
	}
	
	public boolean specialAbilityOnStartUp(){
		return specialAbilityOnStartUp;
	}
	
	public void setSpecialAbilityActivity(boolean specialAbilityActive){
		this.specialAbilityActivity = specialAbilityActive;
	}
	
	public boolean getSpecialAbilityActivity(){
		return specialAbilityActivity;
	}
	
	public void setAbilityIterations(int abilityIterations){
		this.abilityIterations = abilityIterations;
	}
	
	public int getAbilityIterations(){
		return abilityIterations;
	}
	
	public void setAbilityCounter(int abilityCounter){
		this.abilityCounter = abilityCounter;
	}
	
	public int getAbilityCounter(){
		return abilityCounter;
	}
	
	public void addWeaponToInventory(){
		if((weaponInventory + 1) <= weaponInventorySize){
			weaponInventory = weaponInventory + 1;
		}
		
	}
	
	public int getInventorySize(){
		return weaponInventorySize;
	}
	
	public void setInventorySize(int weaponInventorySize){
		this.weaponInventorySize = weaponInventorySize;
	}
	
	public int getWeaponInventory(){
		return weaponInventory;
	}
	
	public int returnWeaponIventory(){
		if(weaponInventory == 0){
			return 0;
		}
		else{
			return weaponInventory;
		}
		
	}
	
	public void setDeath(boolean isDead){
		this.isDead = isDead;
		
	}
	
	public boolean getIsDead(){
		return isDead;
	}
	
	public void setType(String warriorType){
		this.warriorType = warriorType;
	}
	
	public String getType(){
		return warriorType;
	}
	
	public String returnType(){
		if(warriorType.equals("S")){
			return "Stone";
		}
		else if(warriorType.equals("F")){
			return "Flame";
		}
		else if(warriorType.equals("A")){
			return "Air";
		}
		else if(warriorType.equals("W")){
			return "Water";
		}
		return "none";
	}
	
	public void setRow(int Row){
		this.Row = Row;
	}
	
	public int getRow(){
		return Row;
	}
	
	public void setCol(int Col){
		this.Col = Col;
	}
	
	public int getCol(){
		return Col;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public int getAge(){
		return age;
	}
	
	public int incrementAge(){
		int newAge = age + 1;
		if(newAge>15 && newAge<=25){
			if(defensiveStrength > 70){
				defensiveStrength = 70;
			}
			
			return newAge;
		}
		else if(newAge>25 && newAge<=50){
			if(defensiveStrength > 50){
				defensiveStrength = 50;
			}
			return newAge;
		}
		else if (newAge>50){
			if(defensiveStrength > 30){
				defensiveStrength = 30;
			}
			return newAge;
		}
		return newAge;
	}
	
	public void setHealth(double health){
		this.health = health;
	}
	
	public double getHealth(){
		return health;
	}
	
	public void setMovements(String movements){
		this.movements = movements;
	}
	
	public String getMovements(){
		return movements;
	}
	
	
//		for (int i = 0; i < weaponInventory.length; i++){
//			offensivePower = offensivePower + weaponInventory[i];
//		}
	public void set_BaseOffensivePower(double base_offensivePower){
		this.base_offensivePower = base_offensivePower;
		
	}
	
	public void set_BaseDefensiveStrength(double base_defensiveStrength){
		this.base_defensiveStrength = base_defensiveStrength;
		
	}
	
	public double get_BaseOffensivePower(){
		return base_offensivePower;
		
	}
	
	public double get_BaseDefensiveStrength(){
		return base_defensiveStrength;
		
	}

	public void setOffensivePower(double offensivePower){
		this.offensivePower = offensivePower;
	}
	
	public double getOffensivePower(){
		return offensivePower;
	}
	
	public void setDefensiveStrength(double defensiveStrength){
		this.defensiveStrength = defensiveStrength;
	}
	
	public double getDefensiveStrength(){
		return defensiveStrength;
	}
	
	public void warriorToString(){
		System.out.println(ID + ", " + age + ", " + health + ", " + offensivePower + ", " + defensiveStrength + ", " + returnWeaponIventory() + ", " +  returnType() + ", " + Row + ", " + Col);
	}
	
	public void nextMove(){
		
			String move = Character.toString(movements.charAt(moveCounter)); 

    		if (move.equals("w")){
    			if (Row - 1 < 0){
    				setRow(R-1);
    			}
    			else{
    				setRow(Row - 1);
    			}
    			
    		}
    		else if (move.equals("x")){
    			if (Row + 1 > R-1){
    				setRow(0);
    			}
    			else{
    				setRow(Row + 1);
    			}
    			
    		}
    		else if (move.equals("d")){
    			if(Col + 1 > C-1){
    				setCol(0);
    			}
    			else{
    				setCol(Col + 1);
    			}
    			
    		}
    		else if (move.equals("e")){
    			if(Col + 1 > C-1 && Row - 1 < 0){
    				setCol(0);
        			setRow(R-1);
    			}
    			else if(Col + 1 > C-1 && Row - 1 >= 0){
    				setCol(0);
        			setRow(Row - 1);
    			}
    			else if(Col + 1 <= C-1 && Row - 1 < 0){
    				setCol(Col + 1);
    				setRow(R-1);
    			}
    			else{
    				setCol(Col + 1);
    				setRow(Row - 1);
    			}
    			
    		}
    		else if (move.equals("c")){
    			if(Col + 1 > C-1 && Row + 1 > R-1){
    				setCol(0);
        			setRow(0);
    			}
    			else if(Col + 1 > C-1 && Row + 1 <= R-1){
    				setCol(0);
    				setRow(Row + 1);
    			}
    			else if(Col + 1 <= C-1 && Row + 1 > R-1){
    				setCol(Col + 1);
    				setRow(0);
    			}
    			else{
    				setCol(Col + 1);
    				setRow(Row + 1);
    			}
    			
    		}
    		else if (move.equals("a")){
    			if(Col - 1 < 0){
    				setCol(C-1);
    			}
    			else{
    				setCol(Col - 1);
    			}
    		}
    		else if (move.equals("q")){
    			if(Col - 1 < 0 && Row - 1 < 0){
    				setCol(C-1);
        			setRow(R-1);
    			}
    			else if(Col - 1 < 0 && Row - 1 >= 0){
    				setCol(C-1);
    				setRow(Row - 1);
    			}
    			else if(Col - 1 >= 0 && Row - 1 < 0){
    				setCol(Col - 1);
    				setRow(R-1);
    			}
    			else{
    				setCol(Col - 1);
    				setRow(Row - 1);
    			}
    			
    		}
    		else if (move.equals("z")){
    			if(Col - 1 < 0 && Row + 1 > R-1){
    				setCol(C-1);
        			setRow(0);
    			}
    			else if(Col - 1 < 0 && Row + 1 <= R-1){
    				setCol(C-1);
    				setRow(Row + 1);
    			}
    			else if(Col - 1 >= 0 && Row + 1 > R-1){
    				setCol(Col - 1);
    				setRow(0);
    			}
    			else{
    				setCol(Col - 1);
    				setRow(Row + 1);
    			}
    			
    		}
    		else if (move.equals("n")){
    			setCol(Col);
				setRow(Row);
    		}

    		moveCounter = (moveCounter+1) % movements.length();

	}
}