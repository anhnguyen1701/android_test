package com.example.b18dccn022_nguyenlamanh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
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

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner sp;
    private EditText eTen, eNoidung, eDate;
    private RadioGroup r_group;
    RadioButton selectedRadioButton;
    private Button btUpdate, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        eDate.setOnClickListener(this);
    }

    private void initView() {
        sp = findViewById(R.id.spCategory);
        eTen = findViewById(R.id.tvTen);
        eNoidung = findViewById(R.id.tvNoidung);
        eDate = findViewById(R.id.tvDate);
        r_group = findViewById(R.id.r_group);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);

        //init values for spinner
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View view) {
        if (view == eDate) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        if (view == btCancel) {
            finish();
        }

        if (view == btUpdate) {
            String ten = eTen.getText().toString();
            String noidung = eNoidung.getText().toString();
            String tinhtrang = sp.getSelectedItem().toString();
            String date = eDate.getText().toString();

            //get radio button value
            int congtac = 0;
            selectedRadioButton = findViewById(r_group.getCheckedRadioButtonId());
            if (selectedRadioButton.equals("Làm chung")) {
                congtac = 1;
            }

            if (!ten.isEmpty()) {
                Item i = new Item(ten, noidung, date, tinhtrang, congtac);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addItem(i);
                finish();
            }
        }
    }
}