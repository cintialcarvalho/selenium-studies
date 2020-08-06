# selenium-studies
This is my first course of testing automation using Selenium. These projects include the use of Selenium Webdriver, Maven, POM Model, DDT Methodology, GRID (Maven's plugin), Cloud.


Running project CursoSelenium:
- Download the entire code and make sure the files componestes.html and frame.html are in src/main/resources directory. These two files are necessary to run the tests.
- Certify you have installed: Java SE 8 (Library 1.8.0_144), Junit 4.12, Selenium 3.4.0, Maven 4.0, Browser Chrome or Firefox
- You can run the test by the suite classes or class by class using the Junit.


Running project BasicProject:
- Download the entire code and make sure that the URL are online: http://seubarriga.wcaquino.me/login
- Certify you have installed: Java SE 8 (Library 1.8.0_144), Junit 4.12, Selenium 3.11.0, Maven 4.0, maven-surefire-plugin 2.18.1, Browser Chrome or Firefox.
- You can run the tests in three ways: Local, Grid, Cloud. Read below how to run each one.
  - Local: Open Properties.java and change the parameter TIPO_EXECUCAO to LOCAL.
           Open SuiteGeral.java and uncomment the methods inicializa() and finalizar() and comment the method reset().
  
  - GRID: Open Properties.java and change the parameter TIPO_EXECUCAO to GRID.
          Open SuiteGeral.java and comment the methods inicializa() and finalizar() and uncomment the method reset().
          Open DriveFactory.java and change IP (in the option GRID) and port in the method initDriver() according to your GRID settings.
          Make sure you are running the service (hub and node) on your machine.
  
  - CLOUD: Open Properties.java and change the parameter TIPO_EXECUCAO to CLOUD.
           Open SuiteGeral.java and comment the methods inicializa() and finalizar() and uncomment the method reset().
           You must have a testing cloud service account to run the suite. The information set in this test is a free trial account.
           Open DriveFactory.java and change the URL (in the option CLOUD) given by your testing cloud service.


Running project ECommerceTraining:
- Download the entire code.
- Certify you have installed: Java SE 8 (Library 1.8.0_144), Junit 4.12, Selenium 3.4.0, Maven 4.0, Browser Chrome or Firefox
- You can run the test by the suite classes or class by class using the Junit.
  - ATTENTION: This project, that uses a real e-Commerce website, has the porpose of praticing what I had been learning during the selenium testing automation       course. In any moment the Website's integrity was put on risk as well as its data and its service. For those who want to run or analyse this project, please,     use it with responsability due to not causing any damage to the online service.  
