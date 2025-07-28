
@tag
Feature: Purchasing the order from Ecommerce website

Background:
Given I Landed on Ecommerce page

@Regression
Scenario Outline: Positive Test of Submitting the order
Given Logged in with userName <username> and passWord <password>
When I add productName <productname> to cart
And go to cartPage
Then I verify the product <productname> from cartPage
Examples:
	|username				|password		|productname	|
	|siddugk123@gmail.com	|Satish1998		|ZARA COAT 3	|



   