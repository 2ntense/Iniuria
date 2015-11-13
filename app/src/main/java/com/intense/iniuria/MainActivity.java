package com.intense.iniuria;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.intense.iniuria.Enum.DamageType;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private DamageCalculator damageCalculator;

    private Champion attackingChampion;
    private Champion defendingChampion;
    private EditText incomingDamage;
    private Spinner damageType;

    private TextView tvArmorPenFlat;
    private TextView tvArmorPenPerc;
    private TextView tvMagicPenFlat;
    private TextView tvMagicPenPerc;

    private TextView tvArmor;
    private TextView tvMagicResist;

    private TextView tvCalculatedDamage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvArmorPenFlat = (TextView) findViewById(R.id.textView_armor_penetration_flat);
        tvArmorPenPerc = (TextView) findViewById(R.id.textView_armorPenetration_percentage);
        tvMagicPenFlat = (TextView) findViewById(R.id.textView_magic_penetration_flat);
        tvMagicPenPerc = (TextView) findViewById(R.id.textView_magic_penetration_percentage);

        tvArmor = (TextView) findViewById(R.id.textView_armor);
        tvMagicResist = (TextView) findViewById(R.id.textView_magic_resist);

        tvCalculatedDamage = (TextView) findViewById(R.id.textView_calculated_damage);

        damageType = (Spinner) findViewById(R.id.spinner_damage_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.damage_types, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        damageType.setAdapter(adapter);

        incomingDamage = (EditText) findViewById(R.id.incoming_damage);
        incomingDamage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")) {
                    calculateDamage();
                }

            }
        });
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

                        tvArmorPenFlat.setText(String.valueOf(armorPenFlat));
                        tvArmorPenPerc.setText(String.valueOf(armorPenPerc));
                        tvMagicPenFlat.setText(String.valueOf(magicPenFlat));
                        tvMagicPenPerc.setText(String.valueOf(magicPenPerc));

                        armorPenPerc = armorPenPerc / 100;
                        magicPenPerc = magicPenPerc / 100;

                        attackingChampion = new Champion(armorPenFlat, armorPenPerc, magicPenFlat, magicPenPerc);

                        if (defendingChampion != null) {
                            damageCalculator = new DamageCalculator(attackingChampion, defendingChampion);
                        }
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

                        tvArmor.setText(String.valueOf(armor));
                        tvMagicResist.setText(String.valueOf(magicResist));

                        defendingChampion = new Champion(armor, magicResist);

                        if(attackingChampion != null) {
                            damageCalculator = new DamageCalculator(attackingChampion, defendingChampion);
                        }
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

    private void calculateDamage() {

        DamageType damageType = null;

        if(this.damageType.getSelectedItemPosition() == 0) {
            damageType = DamageType.PHYSICAL;
        }
        else if(this.damageType.getSelectedItemPosition() == 1) {
            damageType = DamageType.MAGIC;
        }

        double damage = damageCalculator.calculateDamage(Integer.valueOf(incomingDamage.getText().toString()), damageType);

        tvCalculatedDamage.setText(String.valueOf(damage));

    }

    private boolean isInteger(String s) {

        for(int a=0;a<s.length();a++)
        {
            if(a==0 && s.charAt(a) == '-') continue;
            if( !Character.isDigit(s.charAt(a)) ) return false;
        }
        return true;
    }

}
