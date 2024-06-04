package model;

public class Score {
    private int homeTeamScoredGoals;
    private int awayTeamScoredGoals;

    public Score(int homeTeamScoredGoals, int awayTeamScoredGoals) {
        this.homeTeamScoredGoals = homeTeamScoredGoals;
        this.awayTeamScoredGoals = awayTeamScoredGoals;
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
