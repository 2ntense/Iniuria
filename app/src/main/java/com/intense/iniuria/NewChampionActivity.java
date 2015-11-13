package com.intense.iniuria;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewChampionActivity extends AppCompatActivity {

    private EditText armorPenFlat;
    private EditText armorPenPerc;
    private EditText magicPenFlat;
    private EditText magicPenPerc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_champion);

        armorPenFlat = (EditText) findViewById(R.id.armorPenFlat);
        armorPenPerc = (EditText) findViewById(R.id.armorPenPerc);
        magicPenFlat = (EditText) findViewById(R.id.magicPenFlat);
        magicPenPerc = (EditText) findViewById(R.id.magicPenPerc);
    }

    public void onClickSave(View view) {

        Intent intent = new Intent();
        intent.putExtra("armorPenFlat", Integer.valueOf(armorPenFlat.getText().toString()));
        intent.putExtra("armorPenPerc", Double.valueOf(armorPenPerc.getText().toString()));
        intent.putExtra("magicPenFlat", Integer.valueOf(magicPenFlat.getText().toString()));
        intent.putExtra("magicPenPerc", Double.valueOf(magicPenPerc.getText().toString()));
        setResult(1, intent);
        finish();
    }

}
