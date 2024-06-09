package model;

import java.util.Objects;

public class Score {
    private int homeTeamScoredGoals = 0;
    private int awayTeamScoredGoals = 0;

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

    public boolean isMinorHomeScoreThanRecorded(int homeTeamScoredGoals) {
        return homeTeamScoredGoals < this.homeTeamScoredGoals;
    }

    public boolean isMinorAwayScoreThanRecorded(int awayTeamScoredGoals) {
        return awayTeamScoredGoals < this.awayTeamScoredGoals;
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
