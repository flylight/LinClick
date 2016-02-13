##ALL CALCULATION EXCEPT GOE MAP WORKS! 
NOTICE: Project still in development. Documentation about build and run will be later!


# LinClick
Link click - analytic data based on collected HTTP headers retrieved from redirection flow via provided short URL instead of original. 

Project developed in scope of Big Data training at Lohika Systems company.

Technologies :

- Google Guice;
- Apache Cassandra;
- Apache Spark;
- Spark Java

- D3.js;
- Bootsrap.js.

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
