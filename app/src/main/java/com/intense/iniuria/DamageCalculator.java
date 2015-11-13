package com.intense.iniuria;

import com.intense.iniuria.Enum.DamageType;

public class DamageCalculator {

    private Champion attackingChampion;
    private Champion defendingChampion;

    public DamageCalculator(Champion attackingChampion, Champion defendingChampion) {

        this.attackingChampion = attackingChampion;
        this.defendingChampion = defendingChampion;
    }

    protected double calculateDamage(int damageIncoming, DamageType damageType) {

        double resistance = 0;

        if(damageType == DamageType.PHYSICAL) resistance = defendingChampion.getArmor();
        else if(damageType == DamageType.MAGIC) resistance = defendingChampion.getMagicResist();

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
            penetrationPercentage = attackingChampion.getArmorPenetrationPercentage();
            penetrationFlat = attackingChampion.getArmorPenetrationFlat();
            resistance = defendingChampion.getArmor();
        }
        else if(damageType == DamageType.MAGIC) {
            penetrationPercentage = attackingChampion.getMagicPenetrationPercentage();
            penetrationFlat = attackingChampion.getMagicPenetrationFlat();
            resistance = defendingChampion.getMagicResist();
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
