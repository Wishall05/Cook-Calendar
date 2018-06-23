package com.wishall.cookcalendar;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wishall.cookcalendar.database.DatabaseOperations;
import com.wishall.cookcalendar.enums.VauleEnums;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button questionButton;
    private Button showAllButton;
    private TextView showAllTextView;
    private PopupWindow popup;

    private RelativeLayout detailsActivityLayout;

    private DatabaseOperations databaseOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        questionButton = (Button)findViewById(R.id.button_add);
        showAllButton = (Button)findViewById(R.id.button_show_all);
        showAllTextView = (TextView)findViewById(R.id.tv_show_all);
        detailsActivityLayout = (RelativeLayout)findViewById(R.id.activity_details_layout);
        questionButton.setOnClickListener(this);
        showAllButton.setOnClickListener(this);


        databaseOperation = DatabaseOperations.getInstance(this);
    }

    private void showPopup(){
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.activity_question, null);
        Button yesButton = popupView.findViewById(R.id.button_yes);
        Button noButton = popupView.findViewById(R.id.button_no);
        Button naButton = popupView.findViewById(R.id.button_na);
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
        naButton.setOnClickListener(this);

        popup = new PopupWindow(popupView, 800, 300, true);
        popup.setOutsideTouchable(true);
        popup.showAtLocation(detailsActivityLayout, Gravity.CENTER, 0, 0);;
    }

    private void showAllValues(){
        Cursor cursor = databaseOperation.getAllValues();
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            StringBuilder data = new StringBuilder();
            while (!cursor.isAfterLast()) {
                data.append(cursor.getString(0)).append(" ").append(cursor.getString(1)).append("\n");
                cursor.moveToNext();
            }
            showAllTextView.setText(data.toString());
        } else {
            showAllTextView.setText("No entries in db");
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add :
                showPopup();
                break;
            case R.id.button_show_all :
                showAllValues();
                break;
            case R.id.button_yes :
                popup.dismiss();
                addTableEntry(VauleEnums.YES.name());
                break;
            case R.id.button_no :
                popup.dismiss();
                addTableEntry(VauleEnums.NO.name());
                break;
            case R.id.button_na :
                popup.dismiss();
                addTableEntry(VauleEnums.NA.name());
                break;
        }
    }

    private void addTableEntry(String value){
        databaseOperation.insertValue(value);
    }
}
