package com.example.b18dccn022_nguyenlamanh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.b18dccn022_nguyenlamanh.dal.SQLiteHelper;
import com.example.b18dccn022_nguyenlamanh.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private EditText eTen, eNoidung, eDate;
    RadioButton r_motminh, r_lamchung;
    RadioGroup r_group;
    private Button btUpdate, btBack, btRemove;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        initView();
        btBack.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        eDate.setOnClickListener(this);

        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");
        eTen.setText(item.getTen());
        eNoidung.setText(item.getNoidung());
        eDate.setText(item.getDate());

        // bind data for spinner
        int p = 0;
        for (int i = 0; i < sp.getCount(); i++) {
            if (sp.getItemAtPosition(i).toString().equalsIgnoreCase(item.getTinhtrang())) {
                p = i;
                break;
            }
        }
        sp.setSelection(p);

        //bind data for radio button
        if (item.getCongtac() == 0) {
            ((RadioButton) r_group.getChildAt(0)).setChecked(true);
        } else {
            ((RadioButton) r_group.getChildAt(1)).setChecked(true);
        }
    }

    private void initView() {
        sp = findViewById(R.id.spCategory);
        eTen = findViewById(R.id.tvTen);
        eNoidung = findViewById(R.id.tvNoidung);
        eDate = findViewById(R.id.tvDate);
        r_motminh = findViewById(R.id.radio_motminh);
        r_lamchung = findViewById(R.id.radio_lamchung);
        r_group = findViewById(R.id.r_group);
        btUpdate = findViewById(R.id.btUpdate);
        btBack = findViewById(R.id.btBack);
        btRemove = findViewById(R.id.btRemove);

        //init values for spinner
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db = new SQLiteHelper(this);

        if (view == eDate) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if (m > 8) {
                        date = d + "/" + (m + 1) + "/" + y;
                    } else {
                        date = d + "/0" + (m + 1) + "/" + y;
                    }

                    eDate.setText(date);
                }
            }, year, month, day);
            dialog.show();
        }

        if (view == btBack) {
            finish();
        }

        if (view == btUpdate) {
            String ten = eTen.getText().toString();
            String noidung = eNoidung.getText().toString();
            String tinhtrang = sp.getSelectedItem().toString();
            String date = eDate.getText().toString();

            //get radio button value
            int congtac = 0;
            if (((RadioButton) r_group.getChildAt(1)).isChecked()) {
                congtac = 1;
            }

            if (!ten.isEmpty()) {
                int id = item.getId();
                Item i = new Item(id, ten, noidung, date, tinhtrang, congtac);
                db.update(i);
                finish();
            }
        }

        if (view == btRemove) {
            int id = item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("Ban co chac chan muon xoa " + item.getTen() + " khong?");
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db.delete(id);
                    finish();
                }
            });

            builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}