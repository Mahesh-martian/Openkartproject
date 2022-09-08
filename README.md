# Openkartproject

# Test Automation with Java
Web test automation example project using Eclipse, Java, Maven, TestNG, Selenium , Listners, Extent Report and Allure Report and Page Object Model (POM) using Data driven framework [DDF]

## Test Subject
I've picked Openkart E-commerce website  as a test subject for no particular reason.
It's is a open source software  and does have variety of features that we can play around with.

## Libraries and Frameworks
Version for some of these can be found in the [POM](https://github.com/Mahesh-martian/Openkartproject/blob/main/pom.xml) file.
* Selenium - Web automation
* Maven - Build and package management
* TestNG - Test execution 
* ExtentReports and Allure -  Test Reporting

# Project Structure

```
->src/main/java
  -> com.Opk.constant
      ->projConstants.java
  -> com.Opk.fact
      -> DriverFactory.java
      -> OptionsManager.java
  -> com.Opk.listeners
      -> ListnerAllure.java
      -> ListnerExtentReport.java
      -> testretry.java
      -> TransformAnnotation.java
  -> com.Opk.Pages
      -> AccountsPage.java
      -> LoginPage.java
      -> ProductInfoPage.java
      -> RegisterPage.java
      -> SearchResultsPage.java
  -> com.Opk.utils
      -> ElementUtil.java
      -> ExcelUtil.java
-> src/test/java
  -> com.qa.opencart.base
          -> BaseTest.java
  -> com.qa.opencart.tests
      -> AccountsPageTest.java
      -> LoginPageTest.java
      -> ProductSearchTest.java
      -> RegisterTest.java
  -> log4j.properties
-> src/test/resources
  -> config
      -> config.properties
      -> dev.config.properties
      -> qa.config.properties
      -> stage.config.properties
      -> uat.config.properties
  -> testdata
      -> OpenCarTestData.xlsx
  -> testrunner
      -> testng_PDsearch.xml
      -> testng_regression.xml
      -> testng_sanity.xml
-> Jenkinsfile
-> Jenkinsfile_sample
-> opencart.log
-> pom.xml
```


## Tools
Using [IntelliJ IDEA Community](https://www.jetbrains.com/idea/) version.
I've previously used Eclipse and found it to be a little bit slower and not as intuitive, you're free to try both and decide for yourself. Also there are a lot of comparison articles out there already. 

## Programming Language
I'm using [Java SE](http://www.oracle.com/technetwork/java/javase/downloads/index.html) version jdk1.8.0_111 and it works very well.
There are a couple of good reasons to use Java:
* It's been around for a while so there are a lot of solutions for most problems and if not then somebody from the community will be able to suggest an answer quickly.
* It's OpenSource - there are large, well supported communities sharing free libraries.
* Because of the above two points it's possible to automate (testing) checking for iOS (Web and Native), Android (Web and Native) and Web platforms.

