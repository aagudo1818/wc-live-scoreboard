import exception.BadParameterException;
import model.Team;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LiveScoreBoardIT {
    private final LiveScoreBoard liveScoreBoard = new LiveScoreBoard();

    @Test
    public void testWholeFlow() throws BadParameterException, InterruptedException {
        Team mexico = new Team("MEXICO", "MEX");
        Team colombia = new Team("COLOMBIA", "COL");
        Team england = new Team("ENGLAND", "ENG");
        Team austria = new Team("AUSTRIA", "AUS");
        Team spain = new Team("SPAIN", "ESP");
        Team france = new Team("FRANCE", "FRA");
        Team peru = new Team("PERU", "PER");
        Team guatemala = new Team("GUATEMALA", "GUA");
        Team germany = new Team("GERMANY", "GER");
        Team switzerland = new Team("SWITZERLAND", "SWI");
        liveScoreBoard.startNewMatch(mexico, colombia);
        liveScoreBoard.startNewMatch(england, austria);
        liveScoreBoard.startNewMatch(spain, france);
        TimeUnit.SECONDS.sleep(1);
        liveScoreBoard.startNewMatch(peru, guatemala);
        liveScoreBoard.startNewMatch(germany, switzerland);
        var retrievedMatchSpainFrance = liveScoreBoard.getScoreBoard().stream().filter(match -> match.getHomeTeam().equals(spain)).findFirst();
        if (retrievedMatchSpainFrance.isPresent()) {
            liveScoreBoard.updateScore(retrievedMatchSpainFrance.get(), 5, 2);
        }
        var retrievedMatchEnglandAustria = liveScoreBoard.getScoreBoard().stream().filter(match -> match.getHomeTeam().equals(england)).findFirst();
        if (retrievedMatchEnglandAustria.isPresent()) {
            liveScoreBoard.updateScore(retrievedMatchEnglandAustria.get(), 3, 2);
        }
        var retrievedMatchEnglandAustria2 = liveScoreBoard.getScoreBoard().stream().filter(match -> match.getHomeTeam().equals(england)).findFirst();
        if (retrievedMatchEnglandAustria2.isPresent()) {
            liveScoreBoard.correctScore(retrievedMatchEnglandAustria2.get(), 0, 3);
        }
        var retrievedMatchGermanySwitzerland = liveScoreBoard.getScoreBoard().stream().filter(match -> match.getHomeTeam().equals(germany)).findFirst();
        if (retrievedMatchGermanySwitzerland.isPresent()) {
            liveScoreBoard.endMatch(retrievedMatchGermanySwitzerland.get());
        }
        assertPositionOfMatch(0, spain, france, 5, 2);
        assertPositionOfMatch(1, england, austria, 0, 3);
        assertPositionOfMatch(2, peru, guatemala, 0, 0);
        assertPositionOfMatch(3, mexico, colombia, 0, 0);

    }

    private void assertPositionOfMatch(int index, Team homeTeam, Team awayTeam, int homeScoredGoals, int awayScoredGoals){
        assertThat(liveScoreBoard.getScoreBoardSorted().get(index).getHomeTeam(), is(homeTeam));
        assertThat(liveScoreBoard.getScoreBoardSorted().get(index).getAwayTeam(), is(awayTeam));
        assertThat(liveScoreBoard.getScoreBoardSorted().get(index).getScore().getHomeTeamScoredGoals(), is(homeScoredGoals));
        assertThat(liveScoreBoard.getScoreBoardSorted().get(index).getScore().getAwayTeamScoredGoals(), is(awayScoredGoals));
    }
}
