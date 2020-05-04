# Elo rating system

This repository is a simple Java implementation of the world-famous [Elo rating system](https://en.wikipedia.org/wiki/Elo_rating_system) used for calculating relative skill levels in chess and other competitive multiplayer games.

## How does it work

In the Elo rating system a player is assigned a rating that reflects their skill in relative to other players. New players are assigned a rating of 1000. Having the rating of any given two players one can get the probability of player A beating player B or in other terms the expected score using the following formula:

![Formula for counting the expected score](documentation/images/elo-probability.png)

Here R<sub>A</sub> describes the rating of player A and subsequently R<sub>B</sub> describes the rating of player B.

Players' ratings are updated after every game using the following formula:

![Formula for new ratings](documentation/images/elo-rating.png)

Here the score of the game is 1 if the player wins, 0 if the player loses and 0.5 if there is a draw.

The change in rating is always the same for both players. For example if a player with a rating of 1337 beats a player with a rating of 1690 using the formula above we notice that the new ratings for the players are 1365 and 1662, both changing by 28.
