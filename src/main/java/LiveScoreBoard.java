import model.Match;
import model.Team;

import java.util.HashSet;

public class LiveScoreBoard {
    private final HashSet<Match> scoreBoard = new HashSet<>();

    public void startNewMatch(Team homeTeam, Team awayteam){
        scoreBoard.add(new Match());
    }

    public HashSet<Match> getScoreBoard() {
        return scoreBoard;
    }
}
