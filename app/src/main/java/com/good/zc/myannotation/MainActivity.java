package com.good.zc.myannotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.good.zc.myannotation.buildanno.ViewInjector;
import com.good.zc.processor.Bind;

//import com.good.zc.myannotation.anno.ContentView;
//import com.good.zc.myannotation.anno.FindView;
//import com.good.zc.myannotation.anno.OnClick;
//import com.good.zc.myannotation.anno.ViewInjectUtils;

//@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
//    @FindView(R.id.text)
//    TextView textView;
    @Bind(R.id.text)
    TextView textView;
    @Bind(R.id.btn)
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ViewInjectUtils.inject(this);
        setContentView(R.layout.activity_main);
        ViewInjector.injectView(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("我有变化哦········");
            }
        });
    }

//    @OnClick({R.id.btn})
//    public void onMyClick(View view){
//        textView.setText("我有变化哦········");
//    }
}
