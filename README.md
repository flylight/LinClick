##This project still in development, it save but return only stabby data for now (release date 07/01/2016)

# LinClick
Link click - analytic data based on collected HTTP headers retrieved from redirection flow via provided short URL instead of original. 

Project developed in scope of Big Data training at Lohika Systems company.

Technologies :

- Spring boot;
- Apache Cassandra;
- Apache Spark;
- D3.js;
- Bootsrap.js.

## How to build it

First of all you need installed Java 8 and Apache Maven build tool , then you should get project sources and execute next commant inside project folder:

<code>
mvn clean install
</code>

When this command finish their execution you should see next message in your terminal output :

<code>
[INFO] BUILD SUCCESS
</code>

Then go into "target" folder and find executable jar file called "LinClick-0.0.1-SNAPSHOT.jar" .

This is your compiled and ready to use application.

## How to run it

To run and use this application you need to download and execute Apache Cassandra Database. Please, go to [original](http://cassandra.apache.org/) web site to get more information.

When you have runned Apache Cassandra then open default database client that is distributed with cassandra server, called "cqlsh" . Inside this application execute all queries by same order from file "DataBaseChema.sql" that located in project sources by next path "src/main/resources/static/sql/". 

This steps will create keyspace and database schema.

P.S. You can just copy all content from file and paste into terminal where "sqlsh" is opened.

Then just execute jar file that you have build in previous section. (You can do it from terminal when execute next command on the same location with jar file <code>java -jar LinClick-0.0.1-SNAPSHOT.jar</code>)

## How it works
Bu default this applciation deploy itself on 8080 port, to see application content go into "localhost:8080/" from your browser.

You should see main window as next image :
![Main UI](http://s12.postimg.org/6i6jrvb25/main.png) 

To create new short URL you should provide original URL and press "Get short Link". All short links are unique even you provide same original URL.

![Create new Short Link](http://s10.postimg.org/t1rxtz8jt/create_link.png) 

Use your short URL to collect Click statistic. 

When you need to see short URL statistic , just paste it into place holder and press "Show statistic"

![Get statistic](http://s13.postimg.org/5fh7o0ld3/get_statistic.png) 

This application provide total count if clicks and world map where you can see how much clicks was done from each coutnry. 

![Link clicks statistic on world map](http://s24.postimg.org/l8jd27fqt/world_map.png) 

If you scroll down you will see detailed statistic about user devices:

![Link clicks detailed statistic](http://s11.postimg.org/bxuala203/detailed_map.png) 


NOTICE: All data in this documentation was hardcoded on backend just to provide correct view, but calculation can be not correctly.
