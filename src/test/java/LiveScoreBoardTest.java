import exception.BadParameterException;
import model.Match;
import model.Score;
import model.Team;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class LiveScoreBoardTest {

    private final LiveScoreBoard liveScoreBoard = new LiveScoreBoard();

    @Test
    public void given_home_team_and_away_team_should_add_new_match_to_scoreboard() throws Exception {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("AWAY", "AWY");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);

        assertThat(liveScoreBoard.getScoreBoard().size(), is(1));
    }

    @Test
    public void given_home_team_and_away_team_should_initialize_new_match_to_scoreboard() throws BadParameterException {
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

    @Test
    public void given_repeated_home_team_should_not_create_another_match_with_it() throws BadParameterException {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("AWAY", "AWY");
        Team awayTeam2 = new Team("AWAY2", "AW2");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);
        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam2));

        assertThat(exception.getMessage(), is("One of the specified teams is already playing a match"));
    }

    @Test
    public void given_repeated_away_team_should_not_create_another_match_with_it() throws BadParameterException {
        Team homeTeam = new Team("HOME", "HME");
        Team homeTeam2 = new Team("HOME2", "HM2");
        Team awayTeam = new Team("AWAY", "AWY");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);
        var exception = assertThrows(BadParameterException.class, () -> liveScoreBoard.startNewMatch(homeTeam2, awayTeam));

        assertThat(exception.getMessage(), is("One of the specified teams is already playing a match"));
    }

    @Test
    public void given_repeated_away_and_home_team_should_not_create_another_match_with_it() throws BadParameterException {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("AWAY", "AWY");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);
        var exception = assertThrows(BadParameterException.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("One of the specified teams is already playing a match"));
    }

    @Test
    public void given_home_score_and_away_score_should_update_match() {
        Match match = new Match(new Team("HOME", "HME"), new Team("AWAY", "AWY"), new Score(0, 0), LocalDateTime.now());
        liveScoreBoard.getScoreBoard().add(match);
        var scoreBoard = liveScoreBoard.getScoreBoard().stream().iterator().next();

        liveScoreBoard.updateScore(match, 1, 2);

        assertThat(scoreBoard.getScore().getHomeTeamScoredGoals(), is(1));
        assertThat(scoreBoard.getScore().getAwayTeamScoredGoals(), is(2));
    }
}
