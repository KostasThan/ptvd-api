--only to be used manually, while changing the absolute path. THIS WILL LOAD ALL INDICATORS INITIAL DATA
set global local_infile=true;

LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\countries.txt' INTO TABLE countries
           FIELDS TERMINATED BY ',' ENCLOSED BY '"'
           LINES TERMINATED BY ',\r';

LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\indicators.txt' INTO TABLE indicators
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';

LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Armenia.txt' INTO TABLE metrics_armenia
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Belgium.txt' INTO TABLE metrics_belgium
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Bulgaria.txt' INTO TABLE metrics_bulgaria
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Bosnia and Herzegovina.txt' INTO TABLE metrics_bosnia_and_herzegovina
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Cyprus.txt' INTO TABLE metrics_cyprus
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Germany.txt' INTO TABLE metrics_germany
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Denmark.txt' INTO TABLE metrics_denmark
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Spain.txt' INTO TABLE metrics_spain
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Estonia.txt' INTO TABLE metrics_estonia
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_France.txt' INTO TABLE metrics_france
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_United Kingdom.txt' INTO TABLE metrics_united_kingdom
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Georgia.txt' INTO TABLE metrics_georgia
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Greece.txt' INTO TABLE metrics_greece
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Croatia.txt' INTO TABLE metrics_croatia
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Hungary.txt' INTO TABLE metrics_hungary
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Italy.txt' INTO TABLE metrics_italy
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Lithuania.txt' INTO TABLE metrics_lithuania
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Moldova.txt' INTO TABLE metrics_moldova
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Netherlands.txt' INTO TABLE metrics_netherlands
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Norway.txt' INTO TABLE metrics_norway
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Poland.txt' INTO TABLE metrics_poland
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Portugal.txt' INTO TABLE metrics_portugal
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Romania.txt' INTO TABLE metrics_romania
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Russian Federation.txt' INTO TABLE metrics_russian_federation
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Sweden.txt' INTO TABLE metrics_sweden
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';


LOAD DATA  INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\metrics_Turkey.txt' INTO TABLE metrics_turkey
			FIELDS TERMINATED BY ',' ENCLOSED BY '"'
			LINES TERMINATED BY ',\r';

set global local_infile=false;