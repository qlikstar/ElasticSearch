# ElasticSearch
AWS Challenge

Write an micro service that invokes AWS elastic search and make it available
using API gateway.  Put your code in github.



1. Test Data -
<http://askebsa.dol.gov/FOIA%20Files/2016/Latest/F_5500_2016_Latest.zip>
http://askebsa.dol.gov/FOIA%20Files/2016/Latest/F_5500_2016_Latest.zip

2. Search should be allowed by Plan name, Sponsor name and Sponsor State

3. Use AWS best practices



## What does it do:

1. Load the data into Elastic Search at : search-dol-2dcbis626hy5vivrb5nmejh2x4.us-west-2.es.amazonaws.com
2. Queries the data from the Elastic Search based on Plan name, Sponsor name and Sponsor State

## How to run:

1. Download the project
2. $ cd into the downloaded folder
3. $ mvn clean install
4. java -jar target/ElasticSearch-0.0.1-SNAPSHOT.jar

You should see :

```
============= WELCOME TO ELASTIC SEARCH ============

Please select any of the options :
1. Please enter File Download URL 
2. Fetch data with parameters
3. Exit
```

