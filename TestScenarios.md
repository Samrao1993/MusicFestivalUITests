Total Test Cases Covered-12
Total Scenarios Covered-6

Test Scenario 1: To verify the total number of unique bands in all festivals 
	 	TC_01
Test Scenario 2:To verify the total number of bands for a particular festival
     	TC_2(Positive test case), TC_03 (Positive test case), TC_04 (Negative test case)
Test Scenario 3: To verify the total Count of festivals
	 	TC_05
Test Scenario 4: To verify for how many festivals a particular band is played 
	 	TC_6(Positive test case), TC_07 (Positive test case), TC_08 (Negative test case)
Test Scenario 5: To verify the list of bands which are played in more than one festival
	 	TC_09
Test Scenario 6: To verify the music festival for a particular band is correct or not
		TC_10(Positive test case), TC_11 (Positive test case), TC_12 (Negative test case)
		
Folder: src/test/java
	Package: testCases(2 classes)
		APITest: API validations.
		UITest: All the tests for Web QE Role. UI Automation.
	Package: testComponents(3 classes)
		BaseTest: Driver Initialization code for various browsers
		LandingPage: First Landing page and opening Url
		CommonUtilities_UI: All the common utilities. 
		
Folder: src/main/java
	Package: resources(No class)
		Driver executable files for different browsers
		GlobalData.properties file

How to run:	
UITest class:6 Functions
Can run any single function at a time by double clicking at the function Name and running as a TestNG test.
 OR
Run entire class as TestNG test and see the results of all 12 test cases.
 OR
Run from TestNG XML file
 OR 
Run from Maven POM.XML file
 OR
Run the project as TestNG test
 
