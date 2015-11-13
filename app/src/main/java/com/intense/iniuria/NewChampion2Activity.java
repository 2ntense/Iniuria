package com.intense.iniuria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewChampion2Activity extends AppCompatActivity {

    private EditText armor;
    private EditText magicResist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_champion2);

        armor = (EditText) findViewById(R.id.armor);
        magicResist = (EditText) findViewById(R.id.magicResist);
    }

    public void onClickSave(View view) {

        Intent intent = new Intent();
        intent.putExtra("armor", Integer.valueOf(armor.getText().toString()));
        intent.putExtra("magicResist", Integer.valueOf(magicResist.getText().toString()));
        setResult(2, intent);
        finish();
    }
}
