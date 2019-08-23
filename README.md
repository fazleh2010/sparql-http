
## downloadApp

This is a download application. The image download part is implemented. 
The program takes a text file containing urls and downloads all the images.
The program is written in java.


## Input and output

The downloaded files will be stored in same of input file.


For example, there is sample input file in the following location

/data

The downloaded files will be stored in same of input file

/data

## pom.xml
A simple artifact is used.

## dependencies
The program is written using core java. No external library is added to pom.xml.


## How To Run 

git clone <project url>
```
Build the program
```
mvn clean package
```

Run the application
```
java -jar <generated jar> <input file>


Example:
java -jar target/downloadApp-1.3.jar data/links.txt
```

## Author

* **Mohammad Fazleh Elahi**

