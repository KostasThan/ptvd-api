--only to be used manually, while changing the absolute path. THIS WILL LOAD ALL INDICATORS INITIAL DATA
set global local_infile=true;
LOAD DATA LOCAL INFILE 'C:\\absolute\\path\\MySQL Server 8.0\\Uploads\\indicators.txt' INTO TABLE indicators
           FIELDS TERMINATED BY ',' ENCLOSED BY '"'
           LINES TERMINATED BY ',\n';

set global local_infile=false;