if (Meteor.isClient) {

  //helper methods for the templates
  Template.roundscore.helpers({
    "isHolePlayed": function(holeNumber, courseHoleList) {
      return (courseHoleList != null && courseHoleList.length  >= holeNumber);
    },
    "holeScore": function(holeScore) {
      return (holeScore != null && holeScore > 0) ? holeScore : "-";
    },
    "isFirstRoundOrSingleRoundCompetition" : function() {

      return _competitionRound.roundNumber > 1;
    }
  });
}

if (Meteor.isServer) {

}
