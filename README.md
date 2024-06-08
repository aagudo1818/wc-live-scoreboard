We will assume goals are updated after they have been confirmed, so the user should not remove them.

So, what if the user miss types a wrong score on the update operation ? For example Mexico - Colombia are playing a match 
were no one has scored a goal yet, and then Mexico scores, the Score would be 1 - 0, but the user updates it with 2 - 0
wrongly, how should they correct this error? Well, here is where the correctScore enters, letting correct the scoreboard
and also reassuring that if there's a reduction of an score is because a correction, and not a introduction error.