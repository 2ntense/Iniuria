package com.intense.iniuria;

public class Champion {

    //Physical
    private int armorPenetrationFlat;
    private double armorPenetrationPercentage;

    //Magic
    private int magicPenetrationFlat;
    private double magicPenetrationPercentage;

    //Resistance
    private double armor;
    private double magicResist;

    public Champion() {

        setArmorPenetrationFlat(0);
        setArmorPenetrationPercentage(0.5);
        setArmor(150);
        setMagicResist(100);
    }

    /**
     * Construct attacking champion
     * @param armorPenetrationFlat Flat armor penetration
     * @param armorPenetrationPercentage Percentage armor penetration
     * @param magicPenetrationFlat Flat magic penetration
     * @param magicPenetrationPercentage Percentage magic penetration
     */
    public Champion(int armorPenetrationFlat, double armorPenetrationPercentage, int magicPenetrationFlat, double magicPenetrationPercentage) {

        setArmorPenetrationFlat(armorPenetrationFlat);
        setArmorPenetrationPercentage(armorPenetrationPercentage);
        setMagicPenetrationFlat(magicPenetrationFlat);
        setMagicPenetrationPercentage(magicPenetrationPercentage);
    }

    /**
     * Construct defending champion
     * @param armor Armor
     * @param magicResist Magic Resist
     */
    public Champion(double armor, double magicResist) {
        setArmor(armor);
        setMagicResist(magicResist);
    }

    public int getArmorPenetrationFlat() {
        return armorPenetrationFlat;
    }

    public void setArmorPenetrationFlat(int armorPenetrationFlat) {
        this.armorPenetrationFlat = armorPenetrationFlat;
    }

    public double getArmorPenetrationPercentage() {
        return armorPenetrationPercentage;
    }

    public void setArmorPenetrationPercentage(double armorPenetrationPercentage) {
        this.armorPenetrationPercentage = armorPenetrationPercentage;
    }

    public int getMagicPenetrationFlat() {
        return magicPenetrationFlat;
    }

    public void setMagicPenetrationFlat(int magicPenetrationFlat) {
        this.magicPenetrationFlat = magicPenetrationFlat;
    }

    public double getMagicPenetrationPercentage() {
        return magicPenetrationPercentage;
    }

    public void setMagicPenetrationPercentage(double magicPenetrationPercentage) {
        this.magicPenetrationPercentage = magicPenetrationPercentage;
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
}
