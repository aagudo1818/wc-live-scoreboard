
# LIVE FOOTBALL WORLD CUP SCORE BOARD

A maven library to manage a live score board


## Authors

- [@aagudo1818](https://github.com/aagudo1818)


## Deployment

To compile and run all tests execute:

```bash
  mvn clean install
```

## How it works

You can create, update, correct or end matches through the different methods.

```bash
  startNewMatch(Team homeTeam, Team awayTeam)
  updateScore(Match match, int homeTeamScore, int awayTeamScore)
  correctScore(Match match, int homeTeamScore, int awayTeamScore)
  endMatch(Match match)
```
A Match is composed by

```bash
    Team homeTeam;
    Team awayTeam;
    Score score;
    LocalDateTime startTime;
```

A Team is composed by

```bash
    String name;
    String abbreviation;
```

A Score is composed by

```bash
    int homeTeamScoredGoals;
    int awayTeamScoredGoals;
```

All matches can throw a BadParameterException if the introduced parameters are not valid.


## Aclarations

- We will assume goals are updated after they have been confirmed, so the user should not remove them.
- Names and abbreviations of teams will be trimmed and capitalized
- Scores can not be reduced. So, what if the user miss types a wrong score on the update operation ? For example Mexico - Colombia are playing a match were no one has scored a goal yet, and then Mexico scores, the Score would be 1 - 0, but the user updates it with 2 - 0 wrongly, how should they correct this error? Well, here is where the correctScore enters, letting correct the scoreboard and also reassuring that if there's a reduction of an score is because a correction, and not a introduction error.

