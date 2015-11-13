package com.intense.iniuria;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.intense.iniuria.Enum.DamageType;

public class MainActivity extends AppCompatActivity {

    private Champion attackingChampion;
    private Champion defendingChampion;
    private EditText incomingDamage;
    private Spinner damageType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        damageType = (Spinner) findViewById(R.id.damage_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.damage_types, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        damageType.setAdapter(adapter);

        incomingDamage = (EditText) findViewById(R.id.incoming_damage);
    }

    public void onClickAddAttackingChampion(View view) {

        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_attacking_champion, null);
        final EditText etArmorPenFlat = (EditText) dialogView.findViewById(R.id.armorPenFlat);
        final EditText etArmorPenPerc = (EditText) dialogView.findViewById(R.id.armorPenPerc);
        final EditText etMagicPenFlat = (EditText) dialogView.findViewById(R.id.magicPenFlat);
        final EditText etMagicPenPerc = (EditText) dialogView.findViewById(R.id.magicPenPerc);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add attacking champion");
        builder.setView(dialogView)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        int armorPenFlat = Integer.valueOf(etArmorPenFlat.getText().toString());
                        double armorPenPerc = Double.valueOf(etArmorPenPerc.getText().toString());
                        int magicPenFlat = Integer.valueOf(etMagicPenFlat.getText().toString());
                        double magicPenPerc = Double.valueOf(etMagicPenPerc.getText().toString());

                        armorPenPerc = armorPenPerc / 100;
                        magicPenPerc = magicPenPerc / 100;

                        attackingChampion = new Champion(armorPenFlat, armorPenPerc, magicPenFlat, magicPenPerc);
                    }
                });
        builder.show();
    }

    public void onClickAddDefendingChampion(View view) {

        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_defending_champion, null);
        final EditText etArmor = (EditText) dialogView.findViewById(R.id.armor);
        final EditText etMagicResist = (EditText) dialogView.findViewById(R.id.magicResist);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add defending champion");
        builder.setView(dialogView)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        double armor = Double.valueOf(etArmor.getText().toString());
                        double magicResist = Double.valueOf(etMagicResist.getText().toString());

                        defendingChampion = new Champion(armor, magicResist);
                    }
                });
        builder.show();
    }

    public void onClickCalculate(View view) {
        DamageCalculator damageCalculator = new DamageCalculator(attackingChampion, defendingChampion);
        DamageType damageType = null;

        if(this.damageType.getSelectedItemPosition() == 0) {
            damageType = DamageType.PHYSICAL;
        }
        else if(this.damageType.getSelectedItemPosition() == 1) {
            damageType = DamageType.MAGIC;
        }

        double damage = damageCalculator.calculateDamage(Integer.valueOf(incomingDamage.getText().toString()), damageType);
        System.out.println(String.valueOf(damage));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 1){
            int armorPenFlat = data.getIntExtra("armorPenFlat", 0);
            double armorPenPerc = data.getDoubleExtra("armorPenPerc", 0);
            int magicPenFlat = data.getIntExtra("magicPenFlat", 0);
            double magicPenPerc = data.getDoubleExtra("magicPenPerc", 0);

            attackingChampion = new Champion(armorPenFlat, armorPenPerc, magicPenFlat, magicPenPerc);
        }
        else if(requestCode == 1 && resultCode == 2) {
            double armor = data.getIntExtra("armor", 0);
            double magicResist = data.getIntExtra("magicResist", 0);

            defendingChampion = new Champion(armor, magicResist);
        }
    }
}
