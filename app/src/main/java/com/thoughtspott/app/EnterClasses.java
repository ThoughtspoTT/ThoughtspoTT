package com.thoughtspott.app;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.Distribution;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class EnterClasses extends MainActivity {

    private LinearLayout mLinearLayout;
    private ArrayList<Spinner> mSpinners;
    DatabaseReference databaseReference;
    Button enter_class_button,add_course_button,database_button;
    List<String> names;
    ArrayList<String> classInput;
    Spinner spinner, spinner2;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_classes);


        View sample;
        names = new ArrayList<>();
        classInput = new ArrayList<>();
        mSpinners = new ArrayList<>();
        mLinearLayout = findViewById(R.id.my_linearLayout);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        spinner = new Spinner(this, Spinner.MODE_DROPDOWN);
        spinner2 = new Spinner(this, Spinner.MODE_DROPDOWN);
        TextView text = new TextView(this);




            databaseReference = FirebaseDatabase.getInstance().getReference();

            //INITIAL SPINNERS
            text.setText("\nCourse");
            mLinearLayout.addView(text);
            //1st initial spinner
            spinner = makeSpinnerData("spinner");
            mSpinners.add(spinner);
            mLinearLayout.addView(spinner);
            //2nd spinner
            spinner2 = makeSpinnerData("Choose a Prefix");
            mSpinners.add(spinner2);
            mLinearLayout.addView(spinner2);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    spinner2.setVisibility(View.GONE);
                    String course_chosen = spinner.getSelectedItem().toString();
                    spinner2 = makeSpinnerData(course_chosen);
                    mSpinners.set(mSpinners.size()-1,spinner2);
                    mLinearLayout.addView(spinner2);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        //END OF INITIAL SPINNERS



        //Button to add another course input
        add_course_button = findViewById(R.id.button_submit2);
        add_course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prefix = spinner.getSelectedItem().toString();
                String number = spinner2.getSelectedItem().toString();
                if (prefix.equals("Choose a Prefix")) {
                    TextView errorText = (TextView) spinner.getSelectedView();
                    errorText.setError("");
                    TextView errorText2 = (TextView) spinner2.getSelectedView();
                    errorText2.setError("");
                } else if (number.equals("Choose a Course")) {
                    TextView errorText2 = (TextView) spinner2.getSelectedView();
                    errorText2.setError("");
                } else {

                    //Course text
                    TextView text = new TextView(EnterClasses.this);
                    text.setText("\nCourse");
                    mLinearLayout.addView(text);

                    //1st spinner
                    spinner = makeSpinnerData("spinner");
                    mSpinners.add(spinner);
                    mLinearLayout.addView(spinner);

                    //2nd spinner
                    spinner2 = makeSpinnerData("Choose a Prefix");
                    mSpinners.add(spinner2);
                    mLinearLayout.addView(spinner2);

                    //2nd spinner: Uses the 1st spinner to choose the 2nd spinner's choices
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String course_chosen = spinner.getSelectedItem().toString();
                            spinner2.setVisibility(View.GONE);
                            spinner2 = makeSpinnerData(course_chosen);
                            mSpinners.set(mSpinners.size() - 1, spinner2);
                            mLinearLayout.addView(spinner2);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        });

        //Button for adding classes to database
        enter_class_button = findViewById(R.id.button_submit);
        enter_class_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                enter_classes();
            }
        });


        //BUTTON TO ADD NEW CLASSES TO DATABASE
        database_button = findViewById(R.id.button_submit3);
        database_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for (int i = 0; i < mSpinners.size(); i=i+2) {      // Read all spinners
                  //  Spinner spinnerprefix = mSpinners.get(i);
                    //Spinner spinnernumber = mSpinners.get(i+1);
                   // String prefix1 = spinnerprefix.getSelectedItem().toString();
                    //String number1 = spinnernumber.getSelectedItem().toString();
                    //String course = prefix1 + " " + number1;
                    //if(!((prefix1.equals("Choose a Prefix"))&&(number1.equals("Choose a Course"))))
                  //  {
                    //    classInput.add(course);
                    //}
                //}
//                user.setCourses(classInput);
                Intent intent = new Intent(EnterClasses.this, DatabaseClassAdd.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void enter_classes(){
        String prefix = spinner.getSelectedItem().toString();
        String number = spinner2.getSelectedItem().toString();
        if (prefix.equals("Choose a Prefix"))
        {
            TextView errorText = (TextView)spinner.getSelectedView();
            errorText.setError("");
            TextView errorText2 = (TextView)spinner2.getSelectedView();
            errorText2.setError("");
        }else if(number.equals("Choose a Course")){
            TextView errorText2 = (TextView)spinner2.getSelectedView();
            errorText2.setError("");
        }else {
            for (int i = 0; i < mSpinners.size(); i=i+2) {      // Read all spinners
                Spinner spinnerprefix = mSpinners.get(i);
                Spinner spinnernumber = mSpinners.get(i+1);
                String prefix1 = spinnerprefix.getSelectedItem().toString();
                String number1 = spinnernumber.getSelectedItem().toString();
                String course = prefix1 + " " + number1;
                rootNode.getReference(course).push().child("student").setValue(user.getNameFirst() + " " + user.getNameLast());
                classInput.add(course);
            }


            Intent i = getIntent();
            Bundle SUbundle = i.getExtras();
            String uid = (String) SUbundle.get("userID");
            String email = (String) SUbundle.get("userEmail");
            Log.d("EnterClasses","UID = "+uid);
            Intent intent = new Intent(EnterClasses.this, Enter_Info.class);
            intent.putExtra("userID", uid);
            intent.putExtra("userEmail", email);
            intent.putExtra("courses", classInput);
            startActivity(intent);
            finish();
        }

    }





    private Spinner makeSpinnerData(String s) {
        //while (TRUE) {
            Spinner spin = new Spinner(this, Spinner.MODE_DROPDOWN);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            spin.setLayoutParams(layoutParams);

            databaseReference = FirebaseDatabase.getInstance().getReference();
            //spinner choices
            databaseReference.child(s).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    names = new ArrayList<>();
                    for (DataSnapshot chilSnap : snapshot.getChildren()) {
                        String spinnerName = chilSnap.child("name") != null ? chilSnap.child("name").getValue(String.class) : null;
                        if (spinnerName != null) {
                            names.add(spinnerName);
                        }
                    }          
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(EnterClasses.this, android.R.layout.simple_spinner_item, names);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    arrayAdapter.setNotifyOnChange(TRUE);
                    spin.setAdapter(arrayAdapter);
  //                  mSpinners.add(spin);
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            return spin;
        //}
        //return null;
    }
}