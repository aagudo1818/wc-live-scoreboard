import model.Match;
import model.Score;
import model.Team;

import java.time.LocalDateTime;
import java.util.HashSet;

public class LiveScoreBoard {
    private final HashSet<Match> scoreBoard = new HashSet<>(); //TODO make it thread safe

    public void startNewMatch(Team homeTeam, Team awayteam){
        var match = new Match();
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayteam);
        var score = new Score();
        score.setHomeTeamScoredGoals(0);
        score.setAwayTeamScoredGoals(0);
        match.setScore(score);
        match.setStartTime(LocalDateTime.now()); //TODO make it timezone safe
        scoreBoard.add(match);
    }

    public HashSet<Match> getScoreBoard() {
        return scoreBoard;
    }
}
