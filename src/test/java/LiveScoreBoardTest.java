import model.Team;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LiveScoreBoardTest {

    private final LiveScoreBoard liveScoreBoard = new LiveScoreBoard();

    @Test
    public void given_home_team_and_away_team_should_add_new_match_to_scoreboard(){
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("AWAY", "AWY");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);

        assertThat(liveScoreBoard.getScoreBoard().size(), is(1));
    }

    @Test
    public void given_home_team_and_away_team_should_initialize_new_match_to_scoreboard(){
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("AWAY", "AWY");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);
        var scoreBoard = liveScoreBoard.getScoreBoard().stream().iterator().next();

        assertThat(scoreBoard.getHomeTeam(), is(homeTeam));
        assertThat(scoreBoard.getAwayTeam(), is(awayTeam));
        assertThat(scoreBoard.getScore().getHomeTeamScoredGoals(), is(0));
        assertThat(scoreBoard.getScore().getAwayTeamScoredGoals(), is(0));
        assertNotNull(scoreBoard.getStartTime());
        assertNull(scoreBoard.getEndTime());
    }
}
