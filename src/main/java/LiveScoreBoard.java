import exception.BadParameterException;
import model.Match;
import model.Score;
import model.Team;

import java.time.LocalDateTime;
import java.util.HashSet;

public class LiveScoreBoard {
    private final HashSet<Match> scoreBoard = new HashSet<>(); //TODO make it thread safe

    public void startNewMatch(Team homeTeam, Team awayteam) throws BadParameterException {
        if (scoreBoardContainsAnyMatchWithHomeMatch(homeTeam) || scoreBoardContainsAnyMatchWithAwayMatch(awayteam)) {
            throw new BadParameterException("One of the specified teams is already playing a match");
        }
        scoreBoard.add(new Match(homeTeam, awayteam, new Score(0, 0), LocalDateTime.now())); //TODO make it timezone safe
    }

    private boolean scoreBoardContainsAnyMatchWithAwayMatch(Team awayTeam) {
        for(Match match : scoreBoard){
            if (match.getAwayTeam().equals(awayTeam)){
                return true;
            }
        }
        return false;
    }

    private boolean scoreBoardContainsAnyMatchWithHomeMatch(Team homeTeam) {
        for(Match match : scoreBoard){
            if (match.getHomeTeam().equals(homeTeam)){
                return true;
            }
        }
        return false;
    }

    public HashSet<Match> getScoreBoard() {
        return scoreBoard;
    }
}
