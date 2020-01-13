package ru.temoteam.simsinfinity.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.Date;

import ru.temoteam.simsinfinity.R;
import ru.temoteam.simsinfinity.data.FireBaseRepo;
import ru.temoteam.simsinfinity.data.models.Goal;
import ru.temoteam.simsinfinity.data.models.Task;
import ru.temoteam.simsinfinity.util.DatePickerUtil;
import ru.temoteam.simsinfinity.util.ScrollFabHider;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener {


    private Button apply, delete;
    private FloatingActionButton fab;
    private ScrollView sw;
    private EditText title, description;
    private DatePicker deadline;

    private Task task;
    private FireBaseRepo fbr = new FireBaseRepo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        apply = findViewById(R.id.btn_apply);
        sw = findViewById(R.id.scrollView);
        fab = findViewById(R.id.fab);
        delete = findViewById(R.id.btn_delete);
        deadline = findViewById(R.id.date);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        apply.setOnClickListener(this);
        sw.setOnScrollChangeListener(new ScrollFabHider(fab));
        fab.setOnClickListener(this);
        delete.setOnClickListener(this);

        Serializable s = getIntent().getSerializableExtra("task");
        if (s!=null){
            task = (Task) s;
            showTask();
        }
    }

    void showTask(){
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        delete.setVisibility(View.VISIBLE);
        DatePickerUtil.updateDP(deadline,new Date(task.getDeadline()));
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_delete){
            fbr.removeTask(task);
        }
        else {
            if (task == null) task = new Task();
            task.setTitle(title.getText().toString());
            task.setDescription(description.getText().toString());
            task.setDeadline(DatePickerUtil.datePicker2Date(deadline));
            if (task.getAdditionalProperties().containsKey("id"))
                fbr.updateTask(task);
            else
                fbr.saveTask(task);
        }
        onBackPressed();
        onBackPressed();
    }
}
