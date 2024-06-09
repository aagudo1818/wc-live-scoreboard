import exception.BadParameterException;
import model.Match;
import model.Score;
import model.Team;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LiveScoreBoard {
    private final HashSet<Match> scoreBoard = new HashSet<>(); //TODO make it thread safe

    public void startNewMatch(Team homeTeam, Team awayTeam) throws BadParameterException {
        if (isNullEmptyOrBlank(homeTeam.getName()) || isNullEmptyOrBlank(awayTeam.getName())) {
            throw new BadParameterException("Team name not blank value is required");
        }
        if (isNullEmptyOrBlank(homeTeam.getAbbreviation()) || isNullEmptyOrBlank(awayTeam.getAbbreviation())) {
            throw new BadParameterException("Team abbreviation not blank value is required");
        }
        homeTeam.trimProperties();
        awayTeam.trimProperties();
        if (homeTeam.getAbbreviation().length() != 3 || awayTeam.getAbbreviation().length() != 3) {
            throw new BadParameterException("Team abbreviations must be three characters long");
        }
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
        var matchScore = retrievedMatch.getScore();
        if (matchScore.isMinorHomeScoreThanRecorded(homeTeamScore) || matchScore.isMinorAwayScoreThanRecorded(awayTeamScore)) {
            throw new BadParameterException("Scores to update must be greater than the ones recorded. Recorded scores( home %s, away: %s ), new scores ( home %s, away: %s )".formatted(matchScore.getHomeTeamScoredGoals(), matchScore.getAwayTeamScoredGoals(), homeTeamScore, awayTeamScore));
        }
        matchScore.setHomeTeamScoredGoals(homeTeamScore);
        matchScore.setAwayTeamScoredGoals(awayTeamScore);
    }

    public void correctScore(Match match, int homeTeamScore, int awayTeamScore) throws BadParameterException {
        var retrievedMatch = retrieveMatchFromScoreBoard(match);
        retrievedMatch.setScore(new Score());
        updateScore(retrievedMatch, homeTeamScore, awayTeamScore);
    }

    public void endMatch(Match match) throws BadParameterException {
        var retrievedMatch = retrieveMatchFromScoreBoard(match);
        scoreBoard.remove(retrievedMatch);
    }

    private boolean scoreBoardContainsMatchWithRepeatedTeams(Team homeTeam, Team awayTeam) {
        return scoreBoard.stream().anyMatch(match -> match.getHomeTeam().equals(homeTeam) || match.getAwayTeam().equals(awayTeam));
    }

    private Match retrieveMatchFromScoreBoard(Match match) throws BadParameterException {
        var retrievedOptionalMatch = scoreBoard.stream().filter(matchFromScoreBoard -> matchFromScoreBoard.equals(match)).findFirst();
        if (retrievedOptionalMatch.isPresent()) {
            return retrievedOptionalMatch.get();
        } else {
            throw new BadParameterException("The match was not added in the scoreboard");
        }
    }

    private boolean isNullEmptyOrBlank(String value) {
        return value == null || value.isBlank();
    }

    public List<Match> getScoreBoardSorted() {
        return scoreBoard.stream().sorted(
                        Comparator.comparingInt(Match::getTotalScore)
                                .thenComparing(Match::getStartTime).reversed())
                .collect(Collectors.toList());
    }

    public HashSet<Match> getScoreBoard() {
        return scoreBoard;
    }
}
