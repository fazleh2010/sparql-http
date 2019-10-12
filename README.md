
## downloadApp

This is a download application. 
The program takes a text file containing urls. It then downloads all the images and stores them in local machine.
The program is written in java.


## Input and output

## sample Input file
https://www.technicalkeeda.com/img/images/article/spring-file-upload-eclipse-setup.png
https://farm3.static.flickr.com/2531/4094333885_e8462a8338.jpg
http://eeweb.poly.edu/~yao/EL5123/image/barbara_gray.bmp

The program downloads all the images and stores them in the same location 


For example, there is sample input file (links.txt) in the following location

/data

After running the program the images will be stored

/data

## pom.xml
A simple artifact is used.

## dependencies
The program is written using core java. No external library is added to pom.xml.


## How To Run 

git clone 

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

