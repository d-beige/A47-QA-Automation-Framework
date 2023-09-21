@Ignore
Feature: Smoke Test Suite and Failing Features

# Consists of Smoke Test Scenarios and Scenarios that I need to Troubleshoot


  Scenario: Playing Song from the Search Results Page
    Given I am logged in and my user avatar is displayed
    When I type into the searchbar the song title "birthday"
    And I click the View All button
    And I click the song on row 1
    And I click the Add To button
    And I add the song to the list in row 3
    And I click the Play button located at the bottom of the screen
    Then the song playing matches the song selected above


  Scenario: Successful Login
    Given I am on the Login Page
    When I enter email "daviyontae.floyd@testpro.io"
    And I enter password "te$t$tudent"
    And I click the submit button
    Then I am logged in and my user avatar should be displayed

  Scenario: Play a Song from the All Songs Page
    Given I am logged in and my user avatar is displayed
    And I click the All Songs button in the left tab
    And I right-click a song in the list 1
    Then I click the Playback button
    When I hover over the Play button at the bottom of the screen, it should now display the Pause symbol