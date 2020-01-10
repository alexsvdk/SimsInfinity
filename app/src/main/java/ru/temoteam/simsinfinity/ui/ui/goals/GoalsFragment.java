package ru.temoteam.simsinfinity.ui.ui.goals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.temoteam.simsinfinity.R;
import ru.temoteam.simsinfinity.ui.GoalActivity;
import ru.temoteam.simsinfinity.ui.adapters.GoalsAdapter;

public class GoalsFragment extends Fragment {

    private GoalsViewModel goalsViewModel;
    private FloatingActionButton fab;
    private RecyclerView rw;
    private GoalsAdapter goalsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        goalsViewModel = ViewModelProviders.of(this).get(GoalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_goals, container, false);

        fab = root.findViewById(R.id.fab);
        rw = root.findViewById(R.id.recycler);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GoalActivity.class));
            }
        });

        rw.setLayoutManager(new LinearLayoutManager(getActivity()));
        goalsAdapter =  new GoalsAdapter(getActivity());
        rw.setAdapter(goalsAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}