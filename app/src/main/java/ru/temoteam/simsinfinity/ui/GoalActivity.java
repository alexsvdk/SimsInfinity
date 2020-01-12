package ru.temoteam.simsinfinity.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ru.temoteam.simsinfinity.data.FireBaseRepo;
import ru.temoteam.simsinfinity.R;
import ru.temoteam.simsinfinity.data.models.Goal;

public class GoalActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, TextWatcher, View.OnScrollChangeListener {

    static String TAG = "GoalActivity";

    EditText title, description, total, of, reason, reward;
    TextView tw2;
    DatePicker deadline;
    Button apply, delete;
    FloatingActionButton fab;
    Goal goal;
    ScrollView sw;
    SeekBar reached;
    FireBaseRepo fbr = new FireBaseRepo();
    SimpleDateFormat d = new SimpleDateFormat("DD"),m = new SimpleDateFormat("MM"),
            y = new SimpleDateFormat("YYYY");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        apply = findViewById(R.id.btn_apply);
        sw = findViewById(R.id.scrollView);
        fab = findViewById(R.id.fab);
        delete = findViewById(R.id.btn_delete);
        deadline = findViewById(R.id.date);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        total = findViewById(R.id.qiantity);
        of = findViewById(R.id.of);
        reason = findViewById(R.id.reason);
        reward = findViewById(R.id.reward);
        reached = findViewById(R.id.reached);
        tw2 = findViewById(R.id.textview2);

        reached.setOnSeekBarChangeListener(this);
        apply.setOnClickListener(this);
        total.addTextChangedListener(this);
        sw.setOnScrollChangeListener(this);
        fab.setOnClickListener(this);
        delete.setOnClickListener(this);

        Serializable s = getIntent().getSerializableExtra("goal");
        if (s!=null){
            Log.d(TAG,s.toString());
            goal = (Goal)s;
            showGoal();
        }

    }

    void showGoal(){
        title.setText(goal.getTitle());
        description.setText(goal.getDescription());
        of.setText(goal.getValue());
        total.setText(goal.getTotal()+"");
        delete.setVisibility(View.VISIBLE);
        reached.setMax((int)(goal.getTotal()*100));
        reached.setProgress((int)(100*goal.getCompleted()));
        tw2.setText(goal.getCompleted()/100.0+"/"+total.getText().toString());
        reason.setText(goal.getReason());
        reward.setText(goal.getReward());
        deadline.updateDate(Integer.parseInt(y.format(new Date(goal.getDeadline()))),
                Integer.parseInt(m.format(new Date(goal.getDeadline()))),
                Integer.parseInt(d.format(new Date(goal.getDeadline()))));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_delete){
            fbr.removeGoal(goal);
        }
        else {
            if (goal == null) goal = new Goal();
            goal.setTitle(title.getText().toString());
            goal.setDescription(description.getText().toString());
            goal.setValue(of.getText().toString());
            goal.setTotal(Double.parseDouble(total.getText().toString()));
            goal.setReason(reason.getText().toString());
            goal.setReward(reward.getText().toString());
            goal.setDeadline(getTime());
            goal.setCompleted(reached.getProgress() / 100.0);
            if (goal.getAdditionalProperties().containsKey("id"))
                fbr.updateGoal(goal);
            else
                fbr.saveGoal(goal);
        }
        onBackPressed();
        onBackPressed();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tw2.setText(progress/100.0+"/"+total.getText().toString());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try{
            reached.setMax((int)(Double.parseDouble(total.getText().toString())*100));
        }catch (Exception e){

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY>oldScrollY)
            fab.hide();
        if (scrollY<oldScrollY)
            fab.show();
    }

    public static long getTime(DatePicker dp) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, dp.getYear());
        c.set(Calendar.MONTH, dp.getMonth());
        c.set(Calendar.DAY_OF_MONTH, dp.getDayOfMonth());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTimeInMillis();
    }

    long getTime(){
        return getTime(deadline);
    }
}
