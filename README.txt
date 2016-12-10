#Compatibility
Java version 1.8_45
Tomcat version 8.0.30 x64
Maven version 3.3.3

#To Run the application
Make sure maven is installed and referenced in system path (for more info on maven visit https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
Once ready on a terminal run the following commands
1. mvn clean
(to clean previous generated files)
2. mvn package
(to generate the war file)
3. mvn jetty:run
(this by default runs the application on the port 8080
#To Open Application
1. Open a web browser and go to url http://localhost:8080/
(this should open the Magic Pizza website)

#Assumptions
1. The only required fields to order a pizza are "Type"  and "Quantity"
2. If the pizza quantity is no greater than zero no order will be sent.

#Mock Pizzas File
On the folder "\src\main\resources\mocks" there is a file "pizzas_mock.txt
That file contains the pizzas available on the website.
The file structure is a file separated by commas where each line represents a pizza as follows:
PizzaName, PizzaPrice
If by any means there is an error reading such file the following default pizzas
will be available
Defaults:
   Pizza 1, price 5 $
   Pizza 2, price 6 $
   Pizza 3, price 7 $