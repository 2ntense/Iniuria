package com.intense.iniuria;

/**
 * Created by Intense on 7-11-2015.
 */
public class Champion {

    //Health and mana
    private int health;
    private int mana;

    //Physical
    private int attackDamage;
    private double attackSpeed;
    private int armorPenetrationFlat;
    private double armorPenetrationPercentage;

    //Magic
    private int abilityPower;
    private int magicPenetrationFlat;
    private double magicPenetrationPercentage;

    //Resistance
    private double armor;
    private double magicResist;

    //Other
    private double damageGiveReduction;
    private double damageReceiveReduction;

    public Champion() {

        //setHealth(1000);
        //setMana(1000);
        //setAttackDamage(125);
        //setAttackSpeed(0.90);
        setArmorPenetrationFlat(0);
        setArmorPenetrationPercentage(0.5);
        //setAbilityPower(450);
        //setMagicPenetrationFlat(10);
        //setMagicPenetrationPercentage(0);
        setArmor(150);
        setMagicResist(100);

        //setDamageGiveReduction(0);
        //setDamageReceiveReduction(0);
    }

    public Champion(int armorPenetrationFlat, double armorPenetrationPercentage, int magicPenetrationFlat, double magicPenetrationPercentage) {

        this.armorPenetrationFlat = armorPenetrationFlat;
        this.armorPenetrationPercentage = armorPenetrationPercentage;
        this.magicPenetrationFlat = magicPenetrationFlat;
        this.magicPenetrationPercentage = magicPenetrationPercentage;
    }

    public Champion(double armor, double magicResist) {
        this.armor = armor;
        this.magicResist = magicResist;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health > 0) {
            this.health = health;
        }
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana > 0) {
            this.mana = mana;
        }
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        if (attackDamage >= 0) {
            this.attackDamage = attackDamage;
        }
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        if (attackSpeed >= 0) {
            this.attackSpeed = attackSpeed;
        }
    }

    public int getArmorPenetrationFlat() {
        return armorPenetrationFlat;
    }

    public void setArmorPenetrationFlat(int armorPenetrationFlat) {
        if (armorPenetrationFlat >= 0) {
            this.armorPenetrationFlat = armorPenetrationFlat;
        }
    }

    public double getArmorPenetrationPercentage() {
        return armorPenetrationPercentage;
    }

    public void setArmorPenetrationPercentage(double armorPenetrationPercentage) {
        if (armorPenetrationPercentage >= 0 && armorPenetrationPercentage <= 1) {
            this.armorPenetrationPercentage = armorPenetrationPercentage;
        }
    }

    public int getAbilityPower() {
        return abilityPower;
    }

    public void setAbilityPower(int abilityPower) {
        if (abilityPower >= 0) {
            this.abilityPower = abilityPower;
        }
    }

    public int getMagicPenetrationFlat() {
        return magicPenetrationFlat;
    }

    public void setMagicPenetrationFlat(int magicPenetrationFlat) {
        if (magicPenetrationFlat >= 0) {
            this.magicPenetrationFlat = magicPenetrationFlat;
        }
    }

    public double getMagicPenetrationPercentage() {
        return magicPenetrationPercentage;
    }

    public void setMagicPenetrationPercentage(double magicPenetrationPercentage) {
        if (magicPenetrationPercentage >= 0 && magicPenetrationPercentage <= 1) {
            this.magicPenetrationPercentage = magicPenetrationPercentage;
        }
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getMagicResist() {
        return magicResist;
    }

    public void setMagicResist(double magicResist) {
        this.magicResist = magicResist;
    }

    public double getDamageGiveReduction() {
        return damageGiveReduction;
    }

    public void setDamageGiveReduction(double damageGiveReduction) {
        this.damageGiveReduction = damageGiveReduction;
    }

    public double getDamageReceiveReduction() {
        return damageReceiveReduction;
    }

    public void setDamageReceiveReduction(double damageReceiveReduction) {
        this.damageReceiveReduction = damageReceiveReduction;
    }
}
