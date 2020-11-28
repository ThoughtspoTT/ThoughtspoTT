package com.thoughtspott.app;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtspott.app.MainActivity.user;

public class DatabaseClassAdd extends AppCompatActivity {

    private LinearLayout mLinearLayout;
    DatabaseReference databaseReference;
    EditText prefix,cnumber;
    TextView subtitle;
    Button enter_class_button, add_class;
    ArrayList<String> classInput, prefix_array, number_array;
    String sprefix,scnumber;
    List<String> names;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_class_add);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mLinearLayout = findViewById(R.id.DCA);
        subtitle = new TextView(this);
        prefix = new EditText(this);
        cnumber = new EditText(this);
        prefix_array = new ArrayList<>();
        number_array = new ArrayList<>();
        classInput = new ArrayList<>();
        names = new ArrayList<>();



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
            }
        });

        //Button to add another course to the input
        add_class = findViewById(R.id.button1);
        add_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!((TextUtils.isEmpty(sprefix))|| (TextUtils.isEmpty(scnumber)))) {
                    //Course text
                    TextView text = new TextView(DatabaseClassAdd.this);
                    text.setText("\nCourse");
                    mLinearLayout.addView(text);

                    prefix = new EditText(DatabaseClassAdd.this);
                    cnumber = new EditText(DatabaseClassAdd.this);

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

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                names = new ArrayList<>();
                for (DataSnapshot chilSnap : snapshot.getChildren()) {
                    String spinnerName = chilSnap.child("name") != null ? chilSnap.child("name").getValue(String.class) : null;
                    if (spinnerName != null) {
                        names.add(spinnerName);
                    }
                }
                //update prefix list aka 'spinner'
                for (int i = 0; i < prefix_array.size(); i++) {
                    if (!(names.contains(prefix_array.get(i))))
                    {
                        rootNode.getReference(prefix_array.get(i)).push().child("name").setValue(number_array.get(i));
                        rootNode.getReference(prefix_array.get(i));
                        //child("student").setValue("bob");
                        reference.push().child("name").setValue(prefix_array.get(i));
//                        rootNode.getReference(prefix_array.get(i)).child("name: 1301").getParent().child("student").setValue("bob");
//                        rootNode.getReference(prefix_array.get(i)).child("student").setValue("bob");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //update course number list
        //does the prefix currently have a list?
        //is the course number currently in the prefix list?
    }

    public void enter_classes(){
        sprefix = prefix.getText().toString();
        scnumber = cnumber.getText().toString();
        prefix_array.add(sprefix);
        number_array.add(scnumber);

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
           // user.setCourses(classInput);
            Intent intent = new Intent(DatabaseClassAdd.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }
}