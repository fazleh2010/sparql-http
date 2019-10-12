
## downloadApp

This is a download application. 
The program takes a text file containing urls. It then downloads all the images and stores them in local machine.
The program is written in java.


## Input and output

The Input file is txt file containing urls.

Sample input: data/links.txt

https://www.technicalkeeda.com/img/images/article/spring-file-upload-eclipse-setup.png
https://farm3.static.flickr.com/2531/4094333885_e8462a8338.jpg
http://eeweb.poly.edu/~yao/EL5123/image/barbara_gray.bmp

The program downloads all the images and stores them in the same location of input file

Sample output:
article/spring-file-upload-eclipse-setup.png
4094333885_e8462a8338.jpg
barbara_gray.bmp
links_Report.xml


The links_Report.xml reports the result of download application. That is whether images are downloaded or not (SUCCESS or FAIL). It also includes a note that mentions the reason of failure. For example:

<report>
    <inputFileName>../downloadApp/data/links.txt</inputFileName>
    <downloadReportFileName>../downloadApp/data/links_Report.xml</downloadReportFileName>
    <downloadLocation>..downloadApp/data</downloadLocation>
    <element url="https://farm3.static.flickr.com/2531/4094333885_e8462a8338.jpg">
        <note>The image is successfully stored!! </note>
        <status>SUCCESS</status>
    </element>
    <element url="http://mywebserver.com/images/24174.jpg">
        <note>No image found to download!!</note>
        <status>FAIL</status>
    </element>
    ....
</report>


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

