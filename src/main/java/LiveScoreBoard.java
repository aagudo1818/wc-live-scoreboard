import exception.BadParameterException;
import model.Match;
import model.Score;
import model.Team;

import java.time.LocalDateTime;
import java.util.HashSet;

public class LiveScoreBoard {
    private final HashSet<Match> scoreBoard = new HashSet<>(); //TODO make it thread safe

    public void startNewMatch(Team homeTeam, Team awayTeam) throws BadParameterException {
        if (scoreBoardContainsMatchWithRepeatedTeams(homeTeam, awayTeam)) {
            throw new BadParameterException("One of the specified teams is already playing a match");
        }
        scoreBoard.add(new Match(homeTeam, awayTeam, new Score(0, 0), LocalDateTime.now())); //TODO make it timezone safe
    }

    private boolean scoreBoardContainsMatchWithRepeatedTeams(Team homeTeam, Team awayTeam) {
        return scoreBoard.stream().anyMatch( match -> match.getHomeTeam().equals(homeTeam) ||  match.getAwayTeam().equals(awayTeam));
    }

    public HashSet<Match> getScoreBoard() {
        return scoreBoard;
    }

    public void updateScore(Match match, int homeTeamScore, int awayTeamScore) {
        match.getScore().setHomeTeamScoredGoals(homeTeamScore);
        match.getScore().setAwayTeamScoredGoals(awayTeamScore);
    }
}
