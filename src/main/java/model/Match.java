package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private Score score;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Match(Team homeTeam, Team awayTeam, Score score, LocalDateTime startTime) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match match)) return false;
        return Objects.equals(getHomeTeam(), match.getHomeTeam()) && Objects.equals(getAwayTeam(), match.getAwayTeam()) && Objects.equals(getScore(), match.getScore()) && Objects.equals(getStartTime(), match.getStartTime()) && Objects.equals(getEndTime(), match.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHomeTeam(), getAwayTeam(), getScore(), getStartTime(), getEndTime());
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
