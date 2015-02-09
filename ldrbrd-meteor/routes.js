// given a url like "/competition/5/round/100"
// to start the mongo server, export the following system variable
// MONGO_URL=mongodb://localhost:27017/ldrbrd meteor
Router.map(function() {
    this.route('roundscore', { 
        path: '/competition/:_competitionId/round/:_roundNumber',
        template: 'roundscore', // <-- to be explicit
        data: function() {
            _competitionId  = this.params._competitionId;
            _roundNumber  = this.params._roundNumber;
            roundScore = RoundScoreCollection.find({"competitionId":_competitionId, "roundNumber":parseInt(_roundNumber)}, {sort: {"roundCompetitionScore": -1}});
            course = CourseCollection.find({ "name" : "Test GC Blue" });
            competitionRound = CompetitionRoundCollection.find({"competitionId": _competitionId, "roundNumber": parseInt(_roundNumber)});
            //_courseId = ObjectId("5480615e30040a226307c959");
            //otherCourse = CourseCollection.findOne({"_id":_courseId});
            templateData = {
              _competitionId : _competitionId,
              _competitionRound : competitionRound,
              _roundScore: roundScore,
              _course: course.fetch()[0],
            };
            return templateData;
        }
    });
});