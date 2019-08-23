
## downloadApp

This is in general a download application. The image download part is implemented. 
The program takes a text file containing urls and downloads and save all the images.


## Input and output
The downloaded files will be stored in same of input file

For example
The sample input files in the following location
/data

The downloaded files will be stored in same location
/data

## pom.xml
The program was written using simple artifact.

## dependencies
The program is written using core java. No external library as added and used.


## How To Run And Test

git clone <project url>
```
java -jar <generated jar> <input file>

Example:
java -jar target/downloadApp-1.2.jar data/links.txt
```

## Contributing

The code can be changed. After making any change in the code do the following:

Make a runnable jar
```
mvn clean package
```

Run the application
```
java -jar <generated jar> <input file>
```

## Author

* **Mohammad Fazleh Elahi**

