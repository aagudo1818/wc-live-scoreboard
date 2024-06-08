package model;

import java.util.Objects;

public class Score {
    private int homeTeamScoredGoals;
    private int awayTeamScoredGoals;

    public Score(int homeTeamScoredGoals, int awayTeamScoredGoals) {
        this.homeTeamScoredGoals = homeTeamScoredGoals;
        this.awayTeamScoredGoals = awayTeamScoredGoals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score score)) return false;
        return getHomeTeamScoredGoals() == score.getHomeTeamScoredGoals() && getAwayTeamScoredGoals() == score.getAwayTeamScoredGoals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHomeTeamScoredGoals(), getAwayTeamScoredGoals());
    }

    public int getHomeTeamScoredGoals() {
        return homeTeamScoredGoals;
    }

    public void setHomeTeamScoredGoals(int homeTeamScoredGoals) {
        this.homeTeamScoredGoals = homeTeamScoredGoals;
    }

    public int getAwayTeamScoredGoals() {
        return awayTeamScoredGoals;
    }

    public void setAwayTeamScoredGoals(int awayTeamScoredGoals) {
        this.awayTeamScoredGoals = awayTeamScoredGoals;
    }
}
