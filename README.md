# CincinnatiWeatherForecastAPI
API service with JSON developed in SpringBoot using AWS EC2 and RDS(MySQL), through which user can get past ﬁve years Cincinnati weather data and also forecast prediction. 

# Rest-API      
This Api gives Weather information of cincinnati, ohio, US from Jan 2013 to FEB 2018   
    
All Dates                 - **http://ec2-18-218-234-253.us-east-2.compute.amazonaws.com:8080/weather/historical/**     
See about Particular date - **http://ec2-18-218-234-253.us-east-2.compute.amazonaws.com:8080/weather/historical/20150101**      
Insert info. about date   - **http://ec2-18-218-234-253.us-east-2.compute.amazonaws.com:8080/weather/historical/{"DATE": "20180101", "TMAX": 65, "TMIN": 24}**       
Delete info. about date   - **http://ec2-18-218-234-253.us-east-2.compute.amazonaws.com:8080/weather/historical/20180101**     
Forecasr info about date  - **http://ec2-18-218-234-253.us-east-2.compute.amazonaws.com:8080/weather/forecast/20190101**


*Information about images.    
**All historical**   - All data of Dates.     
**Specific historical**   - Gives the maximium and minimum temparature of a specific date.     
**date not found**  - Return 404 error when the date searched is not present in the database    
**deletedvalue**    - Deletes the entire information about a particular date     
**Post Value**        - Inserts given object date into the database     
**insertedvalue**   - Once the object is inserted, the information about the values can be found out searching by date.*     
**Forecast Value** - given a date it will return the next 7 days data of Tmax and Tmin(including the given date).

**Tools needed:**    
  Java,Restful,Spring,Hibernates,JPA and Mysql Database      
       
**Steps to do:**     
1. run demo.jar file on you host using "java -jar demo.jar"      
2. Port is 8080     
3. End points:      
  a.  **/historical/**                - all dates and their information.     
  b.  **/historical/date**            - GET method will return information about that particular date.       
  c.  **/historical/date**            - Delete method will delete the information about that particular date.          
  d.  **/historical/{"DATE":<string>, "TMAX": <int>, "TMIN": <int>}**  - Inserts information about a new date into database.
  f. **//forecast/date**              -  given a date it will return the next 7 days data of Tmax and Tmin(including the given date).
  
**UI Implementation(Used Ajax):**
Link - http://ec2-18-188-50-17.us-east-2.compute.amazonaws.com:8080/WeatherVisual.html
In this HTML page user can find two forecast details 
1. Forecast from CincinnatiWeather API(My Project)
2. Forecast Details from wunderground API(External)
