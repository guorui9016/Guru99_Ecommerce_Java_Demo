# Guru99_Ecommerce_Java_Demo
This is an automated test project for guru99 live ecommerce demo using the page factory framework.

Test case:
Please see here: [Guru99_Live_Project_Demo_TestCase](https://github.com/guorui9016/Guru99_Ecommerce_Java_Demo/blob/main/Guru99_Live_Project_Demo_TestCase.xlsx)

Development environment:
```
    Selenium 3.141.59
    TestNG 7.4.0
    Log4j 2.14.1
    Extentreports 5.0.8
    Apache Commons IO 2.8.0  
```

### Before Start

- #### Web driver version
    The defult web driver is **Chrome**. Ensure your Chromedriver is matches your chrome version. You may have to update the chrome from time to time.
    Webdriver path:
    ` src/main/resources/driver `
    
### Start test
   Execute testng.xml using command `“mvn test”` from command prompt.

### Demo
   

### Test Report


### Project Structure
```
    Guru99_Ecommerce_Java_Demo
    │                    
    │  Guru99_Live_Project_Demo_TestCase.xlsx       #Test Case(From Guru99)
    │  testng.xml                                   #TestNG configuration file 
    │
    └─src
       ├─main
       │  ├─java
       │  │  ├─listener                             #TestNG listener
       │  │  ├─pagerepository                       #All of the page objects
       │  │  ├─basePage                             #Page base and test case base
       │  │  └─Helper                               #Config loader, screen shot and others
       │  └─resources                               #Webdrivers, test data files and log4j2 configuation file
       └─test
           └─java                                   #Test case 
``` 

