
## company-download-application

This is in general download application. The image download application is implemented. 
The program takes a text file containing urls and downloads and save all the images.


## Input and output
The sample inputs are in the location
/sampleData

The output files will be stored in same location
/sampleData

## pom.xml
The program was written using simple artifact.

## dependencies
The program is written using core java. The external library as added and used.


## How To Run And Test

git clone <project url>
```
java -jar <generated jar> <input file>

Example:
java -jar target/company-download-application-1.2.jar sampleData/links.txt
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

