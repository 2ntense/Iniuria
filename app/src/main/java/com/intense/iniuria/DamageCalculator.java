package com.intense.iniuria;

/**
 * Created by Intense on 7-11-2015.
 */
public class DamageCalculator {

    private Champion sourceChampion;
    private Champion destinationChampion;

    public DamageCalculator(Champion sourceChampion, Champion destinationChampion) {

        this.sourceChampion = sourceChampion;
        this.destinationChampion = destinationChampion;
    }

    protected double calculateDamage(int damageIncoming, DamageType damageType) {

        double resistance = 0;

        if(damageType == DamageType.PHYSICAL) resistance = destinationChampion.getArmor();
        else if(damageType == DamageType.MAGIC) resistance = destinationChampion.getMagicResist();

        double penetration = calculatePenetration(damageType);
        double newResistance = resistance - penetration;

        double damageMultiplier;

        if (newResistance >= 0) {
            damageMultiplier = 100 / (100 + newResistance);
        } else {
            damageMultiplier = 2 - (100 / (100 + newResistance));
        }

        return damageIncoming * damageMultiplier;
    }

    private double calculatePenetration(DamageType damageType) {

        double penetration = 0;
        double penetrationPercentage = 0;
        double penetrationFlat = 0;
        double resistance = 0;

        if(damageType == DamageType.PHYSICAL) {
            penetrationPercentage = sourceChampion.getArmorPenetrationPercentage();
            penetrationFlat = sourceChampion.getArmorPenetrationFlat();
            resistance = destinationChampion.getArmor();
        }
        else if(damageType == DamageType.MAGIC) {
            penetrationPercentage = sourceChampion.getMagicPenetrationPercentage();
            penetrationFlat = sourceChampion.getMagicPenetrationFlat();
            resistance = destinationChampion.getMagicResist();
        }

        //Check if percentage penetration is available
        if (penetrationPercentage > 0 && penetrationPercentage <= 1) {
            penetration = resistance * penetrationPercentage;
        }
        //Check if flat penetration is available
        if (penetrationFlat > 0) {
            //Check if resistance is going negative (resistance can't go below zero)
            if(penetration + penetrationFlat >= resistance) {
                //If resistance is going negative, set total penetration to the resistance of the damage receiving champion
                penetration = resistance;
            }
            //If resistance does not go negative
            else {
                penetration += penetrationFlat;
            }
        }

        return penetration;
    }
}
