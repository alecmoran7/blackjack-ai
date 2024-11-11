# Blackjack AI
A Multi-purpose Blackjack Simulator

This program does not use a transformer model or any machine learning currently, it uses blackjack's basic strategy tables, which are available to view here: https://www.888casino.com/blog/blackjack-strategy-guide/blackjack-charts.
There are plans in the future to implement advanced analysis methods to enable the ability to counting cards and extract even further valuable information from games, but incorporating machine learning is not currently planned yet. 

## What does it do? 
- Currently, it's primary purpose is to generate analytical data on blackjack win/loss statistics through simulated blackjack games, which can either be played manually by the user against the artificial casino or by enabling automatic play (letting the AI play) which utilizes strategy books to play many games at incredibly high speeds for generation of large datasets of win/loss statistics.

- It's secondary purpose is to allow users to refine and their blackjack playing skills with optional hints being provided by the AI for every game, and it also serves as a playground to test new strategies and get statistical feedback on the effectiveness of them.

## How to run
- After cloning the repo, the program can be built and ran using the command ```gradle run```.
- Note: If the program attempts to run without compiling first, try ```gradle build```, this is likely a one-time thing and subsequent calls to ```gradle run``` will call the build command automatically. 
