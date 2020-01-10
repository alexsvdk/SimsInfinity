package ru.temoteam.simsinfinity.data;

import ru.temoteam.simsinfinity.data.models.Goal;

public interface Repo {
    void saveGoal(Goal goal);
    void removeGoal(Goal goal);
    void getGoals();


}
