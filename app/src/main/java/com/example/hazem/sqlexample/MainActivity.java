package com.example.hazem.sqlexample;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    StudentCursorAdapter adapter;
    DbHelper dbHelper;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        getListView ().setEmptyView (getTextView ());
        dbHelper=new DbHelper (this);
        //dbHelper.insert (new Student ("2015170127","Hazem ahmed haroun",18,2019));
        adapter=new StudentCursorAdapter (this,dbHelper.getQuery ());
        getListView ().setAdapter (adapter);
        getListView ().setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int i, long l) {
                //Student student= (Student) adapter.getItem (i);
                Cursor cursor= (Cursor) adapterView.getItemAtPosition (i);
                Student student=new Student ();
                student.setID (cursor.getString (cursor.getColumnIndex (DbContract.StudentEntry._ID)));
                student.setName (cursor.getString (cursor.getColumnIndex (DbContract.StudentEntry.STUDENT_NAME)));
                student.setAge (cursor.getInt (cursor.getColumnIndex (DbContract.StudentEntry.STUDENT_AGE)));
                student.setGraduateYear (cursor.getInt (cursor.getColumnIndex (DbContract.StudentEntry.STUDENT_GRADUATE_YEAR)));
                //Toast.makeText (MainActivity.this, student.getName ()+"", Toast.LENGTH_SHORT).show ();
                Intent intent=new Intent (MainActivity.this,EditorActivity.class);
                intent.putExtra ("Student",student);
                MainActivity.this.startActivity (intent);
            }
        });
    }

    @Override
    protected void onResume () {
        super.onResume ();
        adapter.changeCursor (dbHelper.getQuery ());
    }

    ListView getListView()
    {
        return (ListView) findViewById (R.id.lv_student);
    }
    TextView getTextView()
    {
        return (TextView) findViewById (R.id.tv_indicator);
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater ().inflate (R.menu.main_menu,menu);
        return super.onCreateOptionsMenu (menu);
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId ())
        {
            case R.id.menu_add_student:
            {
                Intent intent=new Intent (this,EditorActivity.class);
                this.startActivity (intent);
                break;
            }
        }
        return super.onOptionsItemSelected (item);
    }

    public class StudentArrayAdapter extends BaseAdapter
    {
        ArrayList<Student> students;

        public StudentArrayAdapter (ArrayList<Student> students) {
            this.students = students;
        }

        @Override
        public int getCount () {
            return students.size ();
        }

        @Override
        public Object getItem (int i) {
            return students.get (i);
        }

        @Override
        public long getItemId (int i) {
            return 0;
        }

        @Override
        public View getView (int i, View view, ViewGroup viewGroup) {
            StudentHolder holder=null;
            if (view==null)
            {
                view= LayoutInflater.from (viewGroup.getContext ()).inflate (R.layout.custom_student_view,viewGroup,false);
                holder=new StudentHolder ();
                holder.ID=view.findViewById (R.id.tv_student_id);
                holder.Name=view.findViewById (R.id.tv_student_name);
                holder.Age=view.findViewById (R.id.tv_student_age);
                holder.GradYear=view.findViewById (R.id.tv_student_grad);
                view.setTag (holder);
            }
            else
            {
                holder= (StudentHolder) view.getTag ();
            }
            holder.ID.setText (students.get (i).getID ());
            holder.Name.setText (students.get (i).getName ());
            holder.Age.setText (students.get (i).getAge ());
            holder.GradYear.setText (students.get (i).getGraduateYear ());
            return view;
        }
        private class StudentHolder
        {
            public TextView ID,Name,Age,GradYear;
        }
    }
    public class StudentCursorAdapter extends CursorAdapter
    {
        Cursor cursor;
        public StudentCursorAdapter (Context context, Cursor c) {
            super (context, c);
            this.cursor=c;

        }


        @Override
        public View newView (Context context, Cursor cursor, ViewGroup viewGroup) {
            View view=LayoutInflater.from (context).inflate (R.layout.custom_student_view,viewGroup,false);
            StudentHolder holder=new StudentHolder ();
            holder=new StudentHolder ();
            holder.ID=view.findViewById (R.id.tv_student_id);
            holder.Name=view.findViewById (R.id.tv_student_name);
            holder.Age=view.findViewById (R.id.tv_student_age);
            holder.GradYear=view.findViewById (R.id.tv_student_grad);
            view.setTag (holder);
            return view;
        }

        @Override
        public void bindView (View view, Context context, Cursor cursor) {
            StudentHolder holder= (StudentHolder) view.getTag ();
            holder.ID.setText ( cursor.getString (cursor.getColumnIndex (DbContract.StudentEntry._ID)));
            holder.Name.setText ( cursor.getString (cursor.getColumnIndex (DbContract.StudentEntry.STUDENT_NAME)));
            holder.Age.setText ( cursor.getString (cursor.getColumnIndex (DbContract.StudentEntry.STUDENT_AGE)));
            holder.GradYear.setText ( cursor.getString (cursor.getColumnIndex (DbContract.StudentEntry.STUDENT_GRADUATE_YEAR)));
        }
        private class StudentHolder
        {
            public TextView ID,Name,Age,GradYear;
        }
    }


}
