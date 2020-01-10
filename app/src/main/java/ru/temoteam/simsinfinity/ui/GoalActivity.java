package ru.temoteam.simsinfinity.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import ru.temoteam.simsinfinity.data.FireBaseRepo;
import ru.temoteam.simsinfinity.R;
import ru.temoteam.simsinfinity.data.models.Goal;

public class GoalActivity extends AppCompatActivity implements View.OnClickListener {

    EditText title, description, total, of, reason, reward;
    DatePicker deadline;
    Button apply;
    Goal goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        apply = findViewById(R.id.btn_apply);
        deadline = findViewById(R.id.date);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        total = findViewById(R.id.qiantity);
        of = findViewById(R.id.of);
        reason = findViewById(R.id.reason);
        reward = findViewById(R.id.reward);

        apply.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (goal==null) goal = new Goal();

        goal.setTitle(title.getText().toString());
        goal.setDescription(description.getText().toString());
        goal.setValue(of.getText().toString());
        goal.setTotal(Double.parseDouble(total.getText().toString()));
        goal.setReason(reason.getText().toString());
        goal.setReward(reward.getText().toString());
        goal.setDeadline(deadline.getMaxDate());

        FireBaseRepo fbr = new FireBaseRepo();
        fbr.saveGoal(goal);

        onBackPressed();
        onBackPressed();
    }
}
