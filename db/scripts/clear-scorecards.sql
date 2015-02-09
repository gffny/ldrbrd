DELETE FROM ldrbrdv2.ldrbrd_competition_round_score WHERE id>0;
DELETE FROM ldrbrdv2.ldrbrd_hole_score WHERE scorecard_id>=0;
DELETE FROM ldrbrdv2.ldrbrd_scorecard WHERE id>0;