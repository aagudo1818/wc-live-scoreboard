import model.Match;
import model.Score;
import model.Team;

import java.time.LocalDateTime;
import java.util.HashSet;

public class LiveScoreBoard {
    private final HashSet<Match> scoreBoard = new HashSet<>(); //TODO make it thread safe

    public void startNewMatch(Team homeTeam, Team awayteam) {
        scoreBoard.add(new Match(homeTeam, awayteam, new Score(0, 0), LocalDateTime.now())); //TODO make it timezone safe
    }

    public HashSet<Match> getScoreBoard() {
        return scoreBoard;
    }
}
