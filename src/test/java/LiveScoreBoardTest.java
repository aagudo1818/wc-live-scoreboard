import model.Team;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LiveScoreBoardTest {

    private final LiveScoreBoard liveScoreBoard = new LiveScoreBoard();

    @Test
    public void given_home_team_and_away_team_should_start_add_new_match_to_scoreboard(){
        Team homeTeam = new Team();
        homeTeam.setName("HOME");
        homeTeam.setAbbreviation("HME");
        Team awayTeam = new Team();
        awayTeam.setName("AWAY");
        awayTeam.setAbbreviation("AWY");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);

        assertThat(liveScoreBoard.getScoreBoard().size(), is(1));
    }
}
