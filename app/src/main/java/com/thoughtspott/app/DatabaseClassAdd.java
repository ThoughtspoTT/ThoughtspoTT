package com.thoughtspott.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.thoughtspott.app.MainActivity.user;

public class DatabaseClassAdd extends AppCompatActivity {

    private LinearLayout mLinearLayout;
    DatabaseReference databaseReference;
    EditText prefix,cnumber;
    TextView subtitle;
    Button enter_class_button, add_class;
    ArrayList<String> classInput, prefix_array, number_array;
    String sprefix,scnumber;
    ArrayList<String> names,names2;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String email, uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_class_add);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mLinearLayout = findViewById(R.id.DCA);
        subtitle = new TextView(this);
        prefix = new EditText(this);
        prefix.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        cnumber = new EditText(this);
        cnumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        prefix_array = new ArrayList<>();
        number_array = new ArrayList<>();
        classInput = new ArrayList<>();
        names = new ArrayList<>();


        Intent i = getIntent();
        Bundle b = i.getExtras();
        email = (String) b.get("email");
        uid = (String) b.get("userID");
        //First course
        //Course subtitle
        subtitle.setText("\nCourse");
        mLinearLayout.addView(subtitle);
        //prefix
        prefix.setHint("Course Prefix?");
        mLinearLayout.addView(prefix);
        //Course number
        cnumber.setHint("Course Number?");
        mLinearLayout.addView(cnumber);


        //Button for adding classes to database
        enter_class_button = findViewById(R.id.button2);
        enter_class_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                enter_classes();
                add_to_database();
                prefix_array.add(sprefix);
                number_array.add(scnumber);
            }
        });

        //Button to add another course to the input
        add_class = findViewById(R.id.button1);
        add_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprefix = prefix.getText().toString();
                scnumber = cnumber.getText().toString();
                if (!((TextUtils.isEmpty(sprefix)) && (TextUtils.isEmpty(scnumber)))) {
                    prefix_array.add(sprefix);
                    number_array.add(scnumber);
                    //next Course text
                    TextView text = new TextView(DatabaseClassAdd.this);
                    text.setText("\nCourse");
                    mLinearLayout.addView(text);

                    prefix = new EditText(DatabaseClassAdd.this);
                    prefix.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                    cnumber = new EditText(DatabaseClassAdd.this);
                    cnumber.setInputType(InputType.TYPE_CLASS_NUMBER);

                    //prefix
                    prefix.setHint("Course Prefix?");
                    mLinearLayout.addView(prefix);

                    //Course number
                    cnumber.setHint("Course Number?");
                    mLinearLayout.addView(cnumber);

                }else{
                    if(TextUtils.isEmpty(sprefix)){
                        prefix.setError("Enter a Course Prefix");
                    }
                    if(TextUtils.isEmpty(scnumber)){
                        cnumber.setError("Enter a Course Number");
                    }
                    return;
                }
            }
        });

    }

    public void add_to_database(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("spinner");
        sprefix = prefix.getText().toString();
        scnumber = cnumber.getText().toString();

        if(TextUtils.isEmpty(sprefix)){
            prefix.setError("Enter a Course Prefix");
        }
        else if(TextUtils.isEmpty(scnumber)){
            cnumber.setError("Enter a Course Number");
        }else {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    names = new ArrayList<>();
                    for (DataSnapshot chilSnap : snapshot.getChildren()) {
                        String prefixName = chilSnap.child("name") != null ? chilSnap.child("name").getValue(String.class) : null;
                        if (prefixName != null) {
                            names.add(prefixName);
                        }
                    }
                    int i;
                    for ( i = 0; i<prefix_array.size(); i++) {
                        int finalI = i;
                        (rootNode.getReference(prefix_array.get(i))).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                names2 = new ArrayList<>();
                                for (DataSnapshot chilSnap : snapshot1.getChildren()) {
                                    String spinnerName = chilSnap.child("name") != null ? chilSnap.child("name").getValue(String.class) : null;
                                    if (spinnerName != null) {
                                        names2.add(spinnerName);
                                    }
                                }
                                    if ((names.contains(prefix_array.get(finalI))) && (names2.contains(number_array.get(finalI)))) {
                                        rootNode.getReference(prefix_array.get(finalI) + " " + number_array.get(finalI)).push().child("student").setValue(email);
                                    } else if (((names.contains(prefix_array.get(finalI)))) && (!(names2.contains(number_array.get(finalI))))) {
                                        rootNode.getReference(prefix_array.get(finalI)).push().child("name").setValue(number_array.get(finalI));
                                        rootNode.getReference(prefix_array.get(finalI) + " " + number_array.get(finalI)).push().child("student").setValue(email);
                                    } else {
                                        rootNode.getReference(prefix_array.get(finalI)).push().child("name").setValue(number_array.get(finalI));
                                        rootNode.getReference(prefix_array.get(finalI) + " " + number_array.get(finalI)).push().child("student").setValue(email);
                                        reference.push().child("name").setValue(prefix_array.get(finalI));
                                    }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        }
    }

    public void enter_classes(){
        sprefix = prefix.getText().toString();
        scnumber = cnumber.getText().toString();


        if(TextUtils.isEmpty(sprefix)){
            prefix.setError("Enter a Course Prefix");
            return;
        }
        else if(TextUtils.isEmpty(scnumber)){
            cnumber.setError("Enter a Course Number");
            return;
        }else {
            for (int i = 0; i < prefix_array.size(); i++) {
                String sprefix = prefix_array.get(i);
                String snumber = number_array.get(i);
                String course = sprefix + " " + snumber;
                classInput.add(course);
            }
            //user.setCourses(classInput);

            Intent intent = new Intent(DatabaseClassAdd.this, Enter_Info.class);
            intent.putExtra("userID", uid);
            intent.putExtra("userEmail", email);
            intent.putExtra("courses", classInput);
            startActivity(intent);
            finish();
        }
    }
}