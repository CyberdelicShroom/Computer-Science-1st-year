public class WaterWarrior_22804897 extends Warrior_22804897 implements WarriorTypeInterface_22804897 {

    public WaterWarrior_22804897(int R, int C, int Row, int Col, int ID, int age, double health, double offensivePower, double defensiveStrength, int weaponInventory, int weaponInventorySize, String movements) {
        super(R, C, Row, Col, ID, age, health, offensivePower, defensiveStrength, weaponInventory, weaponInventorySize, movements);
    }

    public void performSpecialAbility() {
    	
    	setHealth(getHealth() + 20);
    	System.out.println("Special ability performed by water warrior!");
    }

    public void specialAbilityCompleted() {
    	
    }

}