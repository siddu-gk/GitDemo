@tag
Feature: Error Validation

@ErrorValidation
Scenario Outline: varifying the error message
Given I Landed on Ecommerce page
Given Logged in with userName <username> and passWord <password>
Then "Incorrect email or password." is displayed on login page
Examples:
	|username				|password		|
	|siddugk123@gmail.com	|Satish199		|
