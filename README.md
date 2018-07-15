# java-web-shop-parser

This app receives a keyword as first argument in the main method
and starts searching related offers on the site "https://www.aboutyou.de".
Found parsed offers after parsing all offer pages stored in the /target/offers.xml

App can try to cheat server bot detection by delaying the start of each thread with
random sleep time. Sleep time range sets in Utils.sleepTime() method. It also uses
only that type of requests to server which can be used by buyer.
By default app starts in multi-thread mode with start delay in a 500-1200 millis range

# Run this application
    git clone https://github.com/Besandr/java-web-shop-parser.git
  
    cd java-web-shop-parser

    mvn clean package

    cd target

    java -jar java-webshop-parser-0.5-jar-with-dependencies.jar "keyword"
    
# Release notes

Previous version of program(from wednesday 11/07) can be found by tag "v.0.3"
This is the new release with found bugs fixed, added app settings and few comments.
 