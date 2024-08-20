public interface WarriorTypeInterface_22804897{
	
	public void setDeath(boolean isDead);
	
	public boolean getIsDead();
	
	public void setType(String warriorType);
	
	public String getType();
	
	public void setRow(int Row);
	
	public int getRow();
	
	public void setCol(int Col);
	
	public int getCol();
	
	public void setAge(int age);
	
	public int getAge();
	
	public int incrementAge();
	
	public void setHealth(double health);
	
	public double getHealth();
	
	public void setMovements(String movements);
	
	public String getMovements();
	
	public void set_BaseOffensivePower(double base_offensivePower);
	
	public void set_BaseDefensiveStrength(double base_defensiveStrength);
	
	public double get_BaseOffensivePower();
	
	public double get_BaseDefensiveStrength();
	
	public void setOffensivePower(double offensivePower);

	public double getOffensivePower();
	
	public void setDefensiveStrength(double defensiveStrength);
	
	public double getDefensiveStrength();
	
	public void warriorToString();
	
	public void nextMove();
	
    void performSpecialAbility();
    
    public void setSpecialAbilityActivity(boolean specialAbilityActive);
	
	public boolean getSpecialAbilityActivity();
	
	public void setAbilityIterations(int abilityCounter);
	
	public int getAbilityIterations();
	
	public void setAbilityCounter(int abilityCounter);
	
	public int getAbilityCounter();
	
	public void set_specialAbilityOnStartUp(boolean specialAbilityOnStartUp);
	
	public boolean specialAbilityOnStartUp();
	
	public void addWeaponToInventory();
	
	public int getInventorySize();
	
	public void setInventorySize(int weaponInventorySize);
	
	public int getWeaponInventory();
    
    void specialAbilityCompleted();

}
