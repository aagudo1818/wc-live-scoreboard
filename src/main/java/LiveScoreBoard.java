import exception.BadParameterException;
import model.Match;
import model.Score;
import model.Team;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

public class LiveScoreBoard {
    private final HashSet<Match> scoreBoard = new HashSet<>(); //TODO make it thread safe

    public void startNewMatch(Team homeTeam, Team awayTeam) throws BadParameterException {
        if (scoreBoardContainsMatchWithRepeatedTeams(homeTeam, awayTeam)) {
            throw new BadParameterException("One of the specified teams is already playing a match");
        }
        scoreBoard.add(new Match(homeTeam, awayTeam, new Score(), LocalDateTime.now())); //TODO make it timezone safe
    }

    public void updateScore(Match match, int homeTeamScore, int awayTeamScore) throws BadParameterException {
        if (homeTeamScore < 0 || awayTeamScore < 0) {
            throw new BadParameterException("Home team score and away team score must be positive numbers");
        }
        var retrievedMatch = retrieveMatchFromScoreBoard(match);
        if (retrievedMatch.isPresent()) {
            var matchScore = retrievedMatch.get().getScore();
            if (matchScore.isMinorHomeScoreThanRecorded(homeTeamScore) || matchScore.isMinorAwayScoreThanRecorded(awayTeamScore)) {
                throw new BadParameterException("Scores to update must be greater than the ones recorded. Recorded scores( home %s, away: %s ), new scores ( home %s, away: %s )".formatted(matchScore.getHomeTeamScoredGoals(), matchScore.getAwayTeamScoredGoals(), homeTeamScore, awayTeamScore));
            }
            matchScore.setHomeTeamScoredGoals(homeTeamScore);
            matchScore.setAwayTeamScoredGoals(awayTeamScore);
        } else {
            throw new BadParameterException("The match was not added in the scoreboard");
        }
    }

    public void correctScore(Match match, int homeTeamScore, int awayTeamScore) throws BadParameterException{
        var retrievedMatch = retrieveMatchFromScoreBoard(match);
        if (retrievedMatch.isPresent()) {
            retrievedMatch.get().setScore(new Score());
            updateScore(retrievedMatch.get(), homeTeamScore, awayTeamScore);
        } else {
            throw new BadParameterException("The match was not added in the scoreboard");
        }
    }

    private boolean scoreBoardContainsMatchWithRepeatedTeams(Team homeTeam, Team awayTeam) {
        return scoreBoard.stream().anyMatch(match -> match.getHomeTeam().equals(homeTeam) || match.getAwayTeam().equals(awayTeam));
    }

    private Optional<Match> retrieveMatchFromScoreBoard(Match match){
        return scoreBoard.stream().filter(matchFromScoreBoard -> matchFromScoreBoard.equals(match)).findFirst();
    }

    public HashSet<Match> getScoreBoard() {
        return scoreBoard;
    }
}
