package com.example.phamhongminh_lab3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionBarPolicy;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.phamhongminh_lab3.db.Database;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Database db = new Database(this,"Data",null,1);
    List<ThiSinh> list;
    ViewAdaptor viewAdaptor;
    ListView listViewTS;
    Button btnadd;
    EditText search;
    private Broadcast Broadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewTS = findViewById(R.id.listView);
        btnadd = findViewById(R.id.btnAdd);
        search = findViewById(R.id.search);
        db.addData();
        list = db.getData();

        viewAdaptor = new ViewAdaptor(list);
        listViewTS.setAdapter(viewAdaptor);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,AddView.class);
                startActivityForResult(intent,100);
            }
        });
        Broadcast = new Broadcast();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(Broadcast, filter);
        listViewTS.setOnItemLongClickListener(new ItemLongClickRemove());
    }
    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Bán có muốn xóa sản phẩm này!");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String sbdToDelete = list.get(position).getSoBaoDanh();
                    db.deleteTS(sbdToDelete);
                    list.remove(position);
                    viewAdaptor.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Đã xóa thí sinh!", Toast.LENGTH_SHORT).show();

                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //không làm gì
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==150){
            Bundle b =data.getExtras();
            String sSBD = b.getString("sbd");
            String sName = b.getString("name");
            Double sToan = b.getDouble("toan");
            Double sLy = b.getDouble("ly");
            Double sHoa = b.getDouble("hoa");
            ThiSinh thiSinh = new ThiSinh(sName,sSBD,sToan,sLy,sHoa);
            db.addTS(thiSinh);
            list = db.getData();
            viewAdaptor = new ViewAdaptor(list);
            listViewTS.setAdapter(viewAdaptor);
        }

    }
}