package com.example.phamhongminh_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddView extends AppCompatActivity {
    private EditText eSBD;
    private EditText eName;
    private EditText eToan;
    private EditText eLy;
    private EditText eHoa;
    private Button btAdd;
    private Button btExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);
        eSBD = findViewById(R.id.editTextSBD);
        eName = findViewById(R.id.editTextName);
        eToan = findViewById(R.id.editTextDToan);
        eLy = findViewById(R.id.editTextDLy);
        eHoa = findViewById(R.id.editTextDHoa);
        btAdd = findViewById(R.id.buttonThem);
        btExit = findViewById(R.id.buttonback);
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(151,intent);
                finish();
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String sSBD = eSBD.getText().toString();
                String sName = eName.getText().toString();
                Double sToan = Double.parseDouble(eToan.getText().toString());
                Double sLy = Double.parseDouble(eLy.getText().toString());
                Double sHoa = Double.parseDouble(eHoa.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("sbd",sSBD);
                bundle.putString("name",sName);
                bundle.putDouble("toan",sToan);
                bundle.putDouble("ly",sLy);
                bundle.putDouble("hoa",sHoa);
                intent.putExtras(bundle);
                setResult(150,intent);
                finish();

            }
        });

    }
}