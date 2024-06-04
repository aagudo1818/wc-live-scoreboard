import model.Match;
import model.Team;

import java.util.HashSet;

public class LiveScoreBoard {
    private HashSet<Match> scoreBoard = new HashSet<>();

    public void startNewMatch(Team homeTeam, Team awayteam){
    }

    public HashSet<Match> getScoreBoard() {
        return scoreBoard;
    }
}
