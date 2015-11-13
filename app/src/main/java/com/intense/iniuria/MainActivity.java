package com.intense.iniuria;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Champion champion0;
    private Champion champion1;
    private EditText incomingDamge;
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

        incomingDamge = (EditText) findViewById(R.id.incoming_damage);

        //DamageCalculator damageCalculator = new DamageCalculator(champion0, champion1);
        //double damage = damageCalculator.calculateDamage(350, DamageType.MAGIC);
        //System.out.println(String.valueOf(damage));
    }

    public void onClickAddChampionOne(View view) {
        System.out.println("WORKING");

        Intent intent = new Intent(this, NewChampionActivity.class);
        startActivityForResult(intent, 1);
    }

    public void onClickAddChampionTwo(View view) {
        Intent intent = new Intent(this, NewChampion2Activity.class);
        startActivityForResult(intent, 1);
    }

    public void onClickCalculate(View view) {
        DamageCalculator damageCalculator = new DamageCalculator(champion0, champion1);
        DamageType damageType = null;

        if(this.damageType.getSelectedItemPosition() == 0) {
            damageType = DamageType.PHYSICAL;
        }
        else if(this.damageType.getSelectedItemPosition() == 1) {
            damageType = DamageType.MAGIC;
        }

        double damage = damageCalculator.calculateDamage(Integer.valueOf(incomingDamge.getText().toString()), damageType);
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

            champion0 = new Champion(armorPenFlat, armorPenPerc, magicPenFlat, magicPenPerc);
        }
        else if(requestCode == 1 && resultCode == 2) {
            double armor = data.getIntExtra("armor", 0);
            double magicResist = data.getIntExtra("magicResist", 0);

            champion1 = new Champion(armor, magicResist);
        }
    }
}
