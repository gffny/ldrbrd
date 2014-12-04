HoleScore = new Mongo.Collection("HoleScore");
RoundScore = new Mongo.Collection("RoundScore");

if (Meteor.isClient) {
  // This code only runs on the client
  Template.body.helpers({
    holeScoreList: function (competitionId, roundNumber) {
      console.log(competitionId);
      return HoleScore.find({"competitionId":competitionId,"roundNumber":roundNumber});
    },
    roundScoreList: function (competitionId, roundNumber) {

    	return RoundScore.find({"competitionId":competitionId,"roundNumber":roundNumber}, {sort: { hole2Score: 1 }});
    }
  });
}