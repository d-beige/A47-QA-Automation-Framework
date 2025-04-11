Feature: Login Features
  Background:
    Given I am on the Login Page

  Scenario: Unsuccessful Login - Invalid Email
    When I enter email "invalid@email.com"
    And I enter password "$om3th!ng"
    And I click the submit button
    Then my login is rejected and I am still on the Login Page

  Scenario: Successful Login
    When I enter email "daviyontae.floyd@testpro.io"
    And I enter password "te$t$tudent"
    And I click the submit button
    Then I am logged in and my user avatar should be displayed