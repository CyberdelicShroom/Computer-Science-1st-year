public class FlameWarrior_22804897 extends Warrior_22804897 implements WarriorTypeInterface_22804897 {

    public FlameWarrior_22804897(int R, int C, int Row, int Col, int ID, int age, double health, double offensivePower, double defensiveStrength, int weaponInventory, int weaponInventorySize, String movements) {
        super(R, C, Row, Col, ID, age, health, offensivePower, defensiveStrength, weaponInventory, weaponInventorySize, movements);
    }
    public void performSpecialAbility() {
    	setDefensiveStrength(100);
    }

    public void specialAbilityCompleted() {
    	
    }
}