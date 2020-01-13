package ru.temoteam.simsinfinity.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ru.temoteam.simsinfinity.R;
import ru.temoteam.simsinfinity.data.FireBaseRepo;
import ru.temoteam.simsinfinity.data.models.Goal;
import ru.temoteam.simsinfinity.data.models.Task;
import ru.temoteam.simsinfinity.ui.GoalActivity;
import ru.temoteam.simsinfinity.ui.TaskActivity;

public class TasksAdapter extends  RecyclerView.Adapter<TasksAdapter.GoalViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<Task> tasks = new ArrayList<>();
    private FireBaseRepo fbr = new FireBaseRepo();
    private Date date = new Date();
    private Context context;
    private SimpleDateFormat format = new SimpleDateFormat("DD.MM.YY");
    //private ItemClickListener mClickListener;

    public TasksAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void update(){
        fbr.getDB().collection("tasks").get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            tasks = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Task t = Task.fromMap(document.getData());
                                t.getAdditionalProperties().put("id",document.getId());
                                tasks.add(t);
                            }
                            notifyDataSetChanged();
                    }}
                });
        date = new Date();
    }


    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_goal, parent, false);
        return new GoalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {
        Task t = tasks.get(position);
        holder.title.setText(t.getTitle());
        holder.description.setText(t.getDescription());
        holder.setPercent(1);
        holder.deadline.setText(format.format(new Date(t.getDeadline())));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TaskActivity.class);
            intent.putExtra("task", t);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        update();
    }

    class GoalViewHolder extends RecyclerView.ViewHolder{

        TextView title, description, percent, deadline;
        FrameLayout f1, f2,f3,f4;

        void setPercent(double perc,FrameLayout f1, FrameLayout f2, boolean b){
            //TODO fix long percent
            if (b)
            percent.setText(perc*100+"%");
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = (float) (10.0f*perc);
            f1.setLayoutParams(params);
            params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = (float) (10.0f*(1.0-perc));
            f2.setLayoutParams(params);
        }

        public void setPercent(double perc) {
            setPercent(perc,f1,f2,true);
        }

        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            percent = itemView.findViewById(R.id.percent);
            description = itemView.findViewById(R.id.description);
            deadline = itemView.findViewById(R.id.deadline);
            f1 = itemView.findViewById(R.id.complete);
            f2 = itemView.findViewById(R.id.incomplete);
            f3 = itemView.findViewById(R.id.f3);
            f4 = itemView.findViewById(R.id.f4);
        }
    }
}
