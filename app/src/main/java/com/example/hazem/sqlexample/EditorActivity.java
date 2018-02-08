package com.example.hazem.sqlexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditorActivity extends AppCompatActivity {

    boolean editor;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_editor);
        final DbHelper dbHelper=new DbHelper (this);
        editor=false;
        if (getIntent ().getSerializableExtra ("Student")!=null)
        {
            editor=true;
            Student student= (Student) getIntent ().getSerializableExtra ("Student");
            getIDEditText ().setText (student.getID ());
            getNameEditText ().setText (student.getName ());
            getAgeEditText ().setText (student.getAge ()+"");
            getGradEditText ().setText (student.getGraduateYear ()+"");
        }
        getSaveButton ().setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {
                Student student=new Student (
                        getIDEditText ().getText ().toString (),
                        getNameEditText ().getText ().toString (),
                        Integer.parseInt (getAgeEditText ().getText ().toString ()),
                        Integer.parseInt (getGradEditText ().getText ().toString ())
                );
                if (student.getID ().equals ("") || student.getName ().equals (""))
                {
                    Toast.makeText (EditorActivity.this, "Enter ID & Name", Toast.LENGTH_SHORT).show ();
                }
                else
                {
                    if (editor)
                    {
                        dbHelper.update (student);
                        EditorActivity.this.finish ();
                    }
                    else
                    {
                        dbHelper.insert (student);
                        EditorActivity.this.finish ();
                    }
                }
            }
        });
    }
    EditText getIDEditText()
    {
        return (EditText) findViewById (R.id.et_id);
    }
    EditText getNameEditText()
    {
        return (EditText) findViewById (R.id.et_name);
    }
    EditText getAgeEditText()
    {
        return (EditText) findViewById (R.id.et_age);
    }
    EditText getGradEditText()
    {
        return (EditText) findViewById (R.id.et_grad);
    }
    Button getSaveButton()
    {
        return (Button) findViewById (R.id.bn_save);
    }
}
