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
    public void given_home_team_and_away_team_with_properties_with_white_spaces_should_initialize_new_match_to_scoreboard_with_trimmed_teams() throws BadParameterException {
        Team homeTeam = new Team("HOME ", "HME   ");
        Team awayTeam = new Team(" AWAY ", "  AWY ");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);
        var scoreBoard = liveScoreBoard.getScoreBoard().stream().iterator().next();

        assertThat(liveScoreBoard.getScoreBoard().size(), is(1));
        assertThat(scoreBoard.getHomeTeam(), is(homeTeam));
        assertThat(scoreBoard.getHomeTeam().getName(), is("HOME"));
        assertThat(scoreBoard.getHomeTeam().getAbbreviation(), is("HME"));
        assertThat(scoreBoard.getAwayTeam(), is(awayTeam));
        assertThat(scoreBoard.getAwayTeam().getName(), is("AWAY"));
        assertThat(scoreBoard.getAwayTeam().getAbbreviation(), is("AWY"));
        assertThat(scoreBoard.getScore().getHomeTeamScoredGoals(), is(0));
        assertThat(scoreBoard.getScore().getAwayTeamScoredGoals(), is(0));
        assertNotNull(scoreBoard.getStartTime());
        assertNull(scoreBoard.getEndTime());
    }

    @Test
    public void given_null_name_home_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team(null, "HME");
        Team awayTeam = new Team("AWAY", "AWY");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team name not blank value is required"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_null_name_away_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team(null, "AWY");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team name not blank value is required"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_empty_name_home_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("", "HME");
        Team awayTeam = new Team("AWAY", "AWY");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team name not blank value is required"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_empty_name_away_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("", "AWY");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team name not blank value is required"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_blank_name_home_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("  ", "HME");
        Team awayTeam = new Team("AWAY", "AWY");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team name not blank value is required"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_blank_name_away_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team(" ", "AWY");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team name not blank value is required"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_null_abbreviation_home_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", null);
        Team awayTeam = new Team("AWAY", "AWY");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team abbreviation not blank value is required"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_null_abbreviation_away_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("AWAY", null);

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team abbreviation not blank value is required"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_empty_abbreviation_home_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", "");
        Team awayTeam = new Team("AWAY", "AWY");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team abbreviation not blank value is required"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_empty_abbreviation_away_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("AWAY", "");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team abbreviation not blank value is required"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_blank_abbreviation_home_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", "  ");
        Team awayTeam = new Team("AWAY", "AWY");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team abbreviation not blank value is required"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_blank_abbreviation_away_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("AWAY", " ");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team abbreviation not blank value is required"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_more_than_three_characters_abbreviation_home_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", "HMEE");
        Team awayTeam = new Team("AWAY", "AWY");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team abbreviations must be three characters long"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_less_than_three_characters_abbreviation_home_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", "HM");
        Team awayTeam = new Team("AWAY", "AWY");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team abbreviations must be three characters long"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_more_than_three_characters_abbreviation_away_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("AWAY", "AWYY");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team abbreviations must be three characters long"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_less_than_three_characters_abbreviation_away_team_should_not_create_another_match_with_it() {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("AWAY", "AW");

        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("Team abbreviations must be three characters long"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(0));
    }

    @Test
    public void given_repeated_home_team_should_not_create_another_match_with_it() throws BadParameterException {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("AWAY", "AWY");
        Team awayTeam2 = new Team("AWAY2", "AW2");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);
        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam2));

        assertThat(exception.getMessage(), is("One of the specified teams is already playing a match"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(1));
    }

    @Test
    public void given_repeated_home_team_with_white_spaces_should_not_create_another_match_with_it() throws BadParameterException {
        Team homeTeam = new Team("HOME", "HME");
        Team homeTeam2 = new Team("HOME  ", " HME ");
        Team awayTeam = new Team("AWAY", "AWY");
        Team awayTeam2 = new Team("AWAY2", "AW2");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);
        var exception = assertThrows(Exception.class, () -> liveScoreBoard.startNewMatch(homeTeam2, awayTeam2));

        assertThat(exception.getMessage(), is("One of the specified teams is already playing a match"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(1));
    }

    @Test
    public void given_repeated_away_team_should_not_create_another_match_with_it() throws BadParameterException {
        Team homeTeam = new Team("HOME", "HME");
        Team homeTeam2 = new Team("HOME2", "HM2");
        Team awayTeam = new Team("AWAY", "AWY");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);
        var exception = assertThrows(BadParameterException.class, () -> liveScoreBoard.startNewMatch(homeTeam2, awayTeam));

        assertThat(exception.getMessage(), is("One of the specified teams is already playing a match"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(1));
    }

    @Test
    public void given_repeated_away_team_with_white_spaces_should_not_create_another_match_with_it() throws BadParameterException {
        Team homeTeam = new Team("HOME", "HME");
        Team homeTeam2 = new Team("HOME2", "HM2");
        Team awayTeam = new Team("AWAY", "AWY");
        Team awayTeam2 = new Team(" AWAY  ", " AWY ");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);
        var exception = assertThrows(BadParameterException.class, () -> liveScoreBoard.startNewMatch(homeTeam2, awayTeam2));

        assertThat(exception.getMessage(), is("One of the specified teams is already playing a match"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(1));
    }

    @Test
    public void given_repeated_away_and_home_team_should_not_create_another_match_with_it() throws BadParameterException {
        Team homeTeam = new Team("HOME", "HME");
        Team awayTeam = new Team("AWAY", "AWY");

        liveScoreBoard.startNewMatch(homeTeam, awayTeam);
        var exception = assertThrows(BadParameterException.class, () -> liveScoreBoard.startNewMatch(homeTeam, awayTeam));

        assertThat(exception.getMessage(), is("One of the specified teams is already playing a match"));
        assertThat(liveScoreBoard.getScoreBoard().size(), is(1));
    }

    @Test
    public void given_home_score_and_away_score_should_update_match() throws BadParameterException {
        Match match = new Match(new Team("HOME", "HME"), new Team("AWAY", "AWY"), new Score(), LocalDateTime.now());
        liveScoreBoard.getScoreBoard().add(match);
        var scoreBoard = liveScoreBoard.getScoreBoard().stream().iterator().next();

        liveScoreBoard.updateScore(match, 1, 2);

        assertThat(scoreBoard.getScore().getHomeTeamScoredGoals(), is(1));
        assertThat(scoreBoard.getScore().getAwayTeamScoredGoals(), is(2));
    }

    @Test
    public void given_not_positive_home_score_and_valid_away_score_should_throw_exception_and_not_update_score() {
        Match match = new Match(new Team("HOME", "HME"), new Team("AWAY", "AWY"), new Score(), LocalDateTime.now());
        liveScoreBoard.getScoreBoard().add(match);
        var scoreBoard = liveScoreBoard.getScoreBoard().stream().iterator().next();

        var exception = assertThrows(BadParameterException.class, () -> liveScoreBoard.updateScore(match, -1, 2));

        assertThat(exception.getMessage(), is("Home team score and away team score must be positive numbers"));

        assertThat(scoreBoard.getScore().getHomeTeamScoredGoals(), is(0));
        assertThat(scoreBoard.getScore().getAwayTeamScoredGoals(), is(0));
    }

    @Test
    public void given_valid_home_score_and_not_positive_away_score_should_throw_exception_and_not_update_score() {
        Match match = new Match(new Team("HOME", "HME"), new Team("AWAY", "AWY"), new Score(), LocalDateTime.now());
        liveScoreBoard.getScoreBoard().add(match);
        var scoreBoard = liveScoreBoard.getScoreBoard().stream().iterator().next();

        var exception = assertThrows(BadParameterException.class, () -> liveScoreBoard.updateScore(match, 1, -2));

        assertThat(exception.getMessage(), is("Home team score and away team score must be positive numbers"));
        assertThat(scoreBoard.getScore().getHomeTeamScoredGoals(), is(0));
        assertThat(scoreBoard.getScore().getAwayTeamScoredGoals(), is(0));
    }

    @Test
    public void given_minor_home_score_than_the_one_recorded_and_valid_away_score_should_throw_exception_and_not_update_score() {
        Score score = new Score();
        score.setHomeTeamScoredGoals(1);
        Match match = new Match(new Team("HOME", "HME"), new Team("AWAY", "AWY"), score, LocalDateTime.now());
        liveScoreBoard.getScoreBoard().add(match);
        var scoreBoard = liveScoreBoard.getScoreBoard().stream().iterator().next();

        var exception = assertThrows(BadParameterException.class, () -> liveScoreBoard.updateScore(match, 0, 0));

        assertThat(exception.getMessage(), is("Scores to update must be greater than the ones recorded. Recorded scores( home 1, away: 0 ), new scores ( home 0, away: 0 )"));
        assertThat(scoreBoard.getScore().getHomeTeamScoredGoals(), is(1));
        assertThat(scoreBoard.getScore().getAwayTeamScoredGoals(), is(0));
    }

    @Test
    public void given_valid_home_score_than_the_one_recorded_and_minor_away_score_should_throw_exception_and_not_update_score() {
        Score score = new Score();
        score.setAwayTeamScoredGoals(1);
        Match match = new Match(new Team("HOME", "HME"), new Team("AWAY", "AWY"), score, LocalDateTime.now());
        liveScoreBoard.getScoreBoard().add(match);
        var scoreBoard = liveScoreBoard.getScoreBoard().stream().iterator().next();

        var exception = assertThrows(BadParameterException.class, () -> liveScoreBoard.updateScore(match, 0, 0));

        assertThat(exception.getMessage(), is("Scores to update must be greater than the ones recorded. Recorded scores( home 0, away: 1 ), new scores ( home 0, away: 0 )"));
        assertThat(scoreBoard.getScore().getHomeTeamScoredGoals(), is(0));
        assertThat(scoreBoard.getScore().getAwayTeamScoredGoals(), is(1));
    }

    @Test
    public void given_home_score_and_away_score_of_added_to_score_match_without_same_memory_reference_should_update_match() throws BadParameterException {
        var time = LocalDateTime.now();
        Match match = new Match(new Team("HOME", "HME"), new Team("AWAY", "AWY"), new Score(), time);
        Match matchWithAnotherMemoryPosition = new Match(new Team("HOME", "HME"), new Team("AWAY", "AWY"), new Score(), time);
        liveScoreBoard.getScoreBoard().add(match);
        var scoreBoard = liveScoreBoard.getScoreBoard().stream().iterator().next();

        liveScoreBoard.updateScore(matchWithAnotherMemoryPosition, 1, 2);

        assertThat(scoreBoard.getScore().getHomeTeamScoredGoals(), is(1));
        assertThat(scoreBoard.getScore().getAwayTeamScoredGoals(), is(2));
    }

    @Test
    public void given_valid_home_score_and_valid_away_score_and_not_added_match_should_throw_exception_and_not_update_score() {
        Match match = new Match(new Team("HOME", "HME"), new Team("AWAY", "AWY"), new Score(), LocalDateTime.now());
        Match distinctMatch = new Match(new Team("HOME2", "HM2"), new Team("AWAY2", "AW2"), new Score(), LocalDateTime.now());
        liveScoreBoard.getScoreBoard().add(match);
        var scoreBoard = liveScoreBoard.getScoreBoard().stream().iterator().next();

        var exception = assertThrows(BadParameterException.class, () -> liveScoreBoard.updateScore(distinctMatch, 1, 2));

        assertThat(exception.getMessage(), is("The match was not added in the scoreboard"));
        assertThat(scoreBoard.getScore().getHomeTeamScoredGoals(), is(0));
        assertThat(scoreBoard.getScore().getAwayTeamScoredGoals(), is(0));
    }

    @Test
    public void given_home_score_and_away_score_should_correct_match() throws BadParameterException {
        Score score = new Score();
        score.setHomeTeamScoredGoals(2);
        score.setAwayTeamScoredGoals(0);
        Match match = new Match(new Team("HOME", "HME"), new Team("AWAY", "AWY"), score, LocalDateTime.now());
        liveScoreBoard.getScoreBoard().add(match);
        var scoreBoard = liveScoreBoard.getScoreBoard().stream().iterator().next();

        liveScoreBoard.correctScore(match, 1, 0);

        assertThat(scoreBoard.getScore().getHomeTeamScoredGoals(), is(1));
        assertThat(scoreBoard.getScore().getAwayTeamScoredGoals(), is(0));
    }

    @Test
    public void given_valid_home_score_and_valid_away_score_and_not_added_match_should_throw_exception_and_not_correct_score() {
        Score score = new Score();
        score.setHomeTeamScoredGoals(2);
        score.setAwayTeamScoredGoals(0);
        Match match = new Match(new Team("HOME", "HME"), new Team("AWAY", "AWY"), score, LocalDateTime.now());
        Match distinctMatch = new Match(new Team("HOME2", "HM2"), new Team("AWAY2", "AW2"), score, LocalDateTime.now());
        liveScoreBoard.getScoreBoard().add(match);
        var scoreBoard = liveScoreBoard.getScoreBoard().stream().iterator().next();

        var exception = assertThrows(BadParameterException.class, () -> liveScoreBoard.correctScore(distinctMatch, 1, 0));

        assertThat(exception.getMessage(), is("The match was not added in the scoreboard"));
        assertThat(scoreBoard.getScore().getHomeTeamScoredGoals(), is(2));
        assertThat(scoreBoard.getScore().getAwayTeamScoredGoals(), is(0));
    }
}
