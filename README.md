# java-web-shop-parser

  This app receives a keyword as first argument in the main method and starts searching related offers on the site "https://www.aboutyou.de".
  Found parsed offers storing in the /target/offers.xml

  Parser can try to cheat server bot detection by sleeping each thread with random sleep time.

# Run this application
  git clone https://github.com/Besandr/java-web-shop-parser.git

  ./mvn clean package

  cd target

  java -jar java-webshop-parser-0.1-jar-with-dependencies.jar "keyword"