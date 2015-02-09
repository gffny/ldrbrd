INSERT INTO ldrbrdv2.ldrbrd_golfer VALUES('1','John','Gaffney','gffny','16','john@gffny.com');
INSERT INTO ldrbrdv2.ldrbrd_golfer VALUES('2','Colm','Caffrey','ccaffrey','8','ccaffrey@gmail.com');
INSERT INTO ldrbrdv2.ldrbrd_golfer VALUES('3','Eoin','DeBarra','eoindbe','18','eoindeb@gmail.com');

INSERT INTO ldrbrdv2.ldrbrd_society VALUES('1','Morning Golf Champs','1');

INSERT INTO ldrbrdv2.ldrbrd_society_membership VALUES('1','1','1',NULL,NULL);

INSERT INTO ldrbrdv2.ldrbrd_scoring_format VALUES('1','STABLEFORD','com.gffny.ldrbrd.common.scoring.impl.Stableford');

INSERT INTO ldrbrdv2.ldrbrd_competition VALUES('1', 'Test Competition', '2014-12-31 23:59:59', '0', '1', NULL);
INSERT INTO ldrbrdv2.ldrbrd_competition VALUES('2', 'Test Competition 2', '2014-12-31 23:59:59', '0', '1', '1');

INSERT INTO ldrbrdv2.ldrbrd_competition_round VALUES('1','1','1','2014-12-31','5480615e30040a226307c959','1','23:59:59','0');
INSERT INTO ldrbrdv2.ldrbrd_competition_round VALUES('2','1','2','2015-01-01','5480615e30040a226307c959','1','23:59:59','0');
INSERT INTO ldrbrdv2.ldrbrd_competition_round VALUES('3','1','3','2015-01-02','5480615e30040a226307c959','1','23:59:59','0');
INSERT INTO ldrbrdv2.ldrbrd_competition_round VALUES('4','1','4','2015-01-03','5480615e30040a226307c959','1','23:59:59','0');

INSERT INTO ldrbrdv2.ldrbrd_competition_entry VALUES('1','1','1','2014-12-13 17:05:00');
INSERT INTO ldrbrdv2.ldrbrd_competition_entry VALUES('2','1','2','2014-12-13 17:05:00');
INSERT INTO ldrbrdv2.ldrbrd_competition_entry VALUES('3','1','3','2014-12-13 17:05:00');

