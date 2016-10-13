package com.example.dw.numpercentview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dw.numpercentview.widget.NumPercentView;

public class MainActivity extends AppCompatActivity {

    private NumPercentView numPercentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numPercentView = (NumPercentView) findViewById(R.id.numpercent);
        numPercentView.setCurrentValue(500);
        numPercentView.setTotalValue(1000);
    }
}
