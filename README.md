# java-web-shop-parser

This app is a parser which extracts product information from the following web shop:
http://www.aboutyou.de

To do so the parser must be executed with a keywords as an argument. It extracts data which can be found on the shop website by typing the keywords into the
search bar. 
The app extract all of the following properties for every offer (if applicable): Name, Brand, Color, Price, Initial Price, Currency, Description, ArticleID, Shipping Costs.

Also it extract all available color and size combinations for one product as offer (every color and every size is a separate offer)

Founded parsed offers after parsing all offer pages are stored in the /target/offers.xml

App can try to cheat server bot detection by delaying the start of each thread with
random sleep time. Sleep time range sets in Utils.sleepTime() method. It also uses
only that type of requests to server which can be used by buyer.
This settings can be changed in a *Settings.ini* file.
By default app starts in multi-thread mode with start delay in a 500-1200 millis range

## Notice 

The parser's work strongly depends on internal structure of wep page. So if the web-site designers changes an important for parser tags the parser won't work correctly
Last revision of parser was made **13/02/2019**

#### Run this application
````
    git clone https://github.com/Besandr/java-web-shop-parser.git
  
    cd java-web-shop-parser

    mvnw clean package

    cd target

    java -jar java-webshop-parser.jar "keywords"
    

 