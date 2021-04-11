#Author: s.md.suhailahamed@gmail.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Problem1
  This is to verify the values of the form and validate the sum of the values are correct

  @test1
  Scenario: This scenario is to Verify that the values of the form and validate the sum of the values are correct
    Given I am on the Application UnderTest
    When  I verify the count of values appear on the screen
    Then  I verify the values on the screen are greater than zero
    And   I verify the values are formatted as currencies
    And   I verify the total balance is matching with sum of the values on the screen
