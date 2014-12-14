// given a url like "/competition/5/round/100"
Router.map(function() {
    this.route('roundscore', { 
        path: '/competition/:_competitionId/round/:_roundNumber',
        template: 'roundscore', // <-- to be explicit
        data: function() {
            // competitionId : this.params._competitionId,
            // roundNumber : this.params._roundNumber,
            // blah : RoundScoreCollection.findOne({"competitionId" : competitionId, "roundNumber":roundNumber});
            _competitionId  = this.params._competitionId;
            _roundNumber  = this.params._roundNumber;
            roundScore = RoundScoreCollection.find({"competitionId":1, "roundNumber":1});
            templateData = {
              _competitionId : _competitionId,
              _roundScore: roundScore,
            };
            return templateData;
        }
    });
});