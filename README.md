# CreditCardValidator_Java
## Table of contents
* Project Description
* Technologies used
* Design Patterns used
* Features implemented
* Class diagram
* SetUp
* References

### General info
This project is developed in order to extract data of credit card information from files of different fileformats such as csv, xml,json and reading and validating card type and error and returning the same information into another file as output in the same format. I have used Strategy design pattern for finding file formats and reading and writing data, and Chain of responsibility to find the type of credir card issuer and error type. This approach is designed in such a way that it accomodates other file types and card issuers in future.
### Technologies used
* Java 
* JUnitTesting
### Design Patterns used
* Strategy pattern
* Chain of responsibility
### Features implemented
* File reading, parsing and writing
* Card validation and identification of card issuer and returning data to file for writing.
### Class diagram
![Credit Card Problem Class diagram](/images/CreditCardProblem%20Class%20diagram.png)

### Setup
* Main file is TaskRunner.java

### How To Run
* Clone the project. 
* Inside the project directory ```java -jar CreditCardTaskRunner.jar <inputfilePath> <outputFilePath>```

### Alternate way to run the project without the compiled JAR
* To run this project, json simple external library needs to be installed. One can install it locally using below link:
* [json simple library](#general-info)
http://www.java2s.com/Code/Jar/j/Downloadjsonsimple111jar.htm

* Once it is installed, one can build the project and compile to pass the command line arguments with file location of input and required location of output as args[0] and args[1].


### References
* https://www.baeldung.com/java-file-mime-type
* https://stackoverflow.com/questions/40591270/strategy-pattern-to-read-different-file-formats
* https://dzone.com/articles/determining-file-types-java
* https://www.journaldev.com/1112/how-to-write-xml-file-in-java-dom-parser
