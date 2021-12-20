#Author: Anant Sahitya
#Keywords Summary :

@Regression
Feature: GG Timer Counter Feature

  @test1
  Scenario Outline: Title of your scenario
    Given Open the Egg Timer App
    Given User enter the time "<time>"
    When Click on start button
    Then User navigates to Timer page 
	  Then Verify that time is decreasing per second
	  
 Examples: 
 	| time 			|
 	| 15    		|
 	| 3 minutes |

