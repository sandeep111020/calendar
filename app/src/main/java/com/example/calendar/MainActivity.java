package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    CalendarView calendar;
    TextView date;
    Button button;
    EditText text;
    String Date;
    String textdata;
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    modelclass model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = (CalendarView)
                findViewById(R.id.calendar);
        date = (TextView)
                findViewById(R.id.date);
        button = (Button)
                findViewById(R.id.button);
        text = findViewById(R.id.text);
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("EmployeeInfo");
        model = new modelclass();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textdata = text.getText().toString();
                addDatatoFirebase(textdata, Date);
                Toast.makeText(MainActivity.this, "hello" + textdata + Date, Toast.LENGTH_SHORT).show();
            }
        });
        calendar
                .setOnDateChangeListener(
                        new CalendarView
                                .OnDateChangeListener() {
                            @Override

                            // In this Listener have one method
                            // and in this method we will
                            // get the value of DAYS, MONTH, YEARS
                            public void onSelectedDayChange(
                                    @NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth) {

                                // Store the value of date with
                                // format in String type Variable
                                // Add 1 in month because month
                                // index is start with 0

                                Date = dayOfMonth + "-"
                                        + (month + 1) + "-" + year;

                                // set this date in TextView for Display
                                date.setText(Date);
                            }
                        });
    }

    private void addDatatoFirebase(String name, String date) {
        // below 3 lines of code is used to set
        // data in our object class.
        model.setName(name);
        model.setDate(date);


        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(model);

                // after adding this data we are showing toast message.
                Toast.makeText(MainActivity.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(MainActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
