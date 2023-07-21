Feature: Features for Smoke Test Suite

  Scenario: Successful Login
    Given I am on the Login Page
    When I enter email "daviyontae.floyd@testpro.io"
    And I enter password "te$t$tudent"
    And I click the submit button
    Then I am logged in and my user avatar should be displayed

  Scenario: Change Username
    Given I am logged in and my user avatar is displayed
    And I click my username in the top right corner
    And I enter my current password "te$t$tudent"
    And I enter a new username "Bruce Lee Roy"
    When I click the Save button
    Then the username I entered should match the username displayed "Bruce Lee Roy"

  Scenario: Click Play Button Without Selecting a Song
    Given I am logged in and my user avatar is displayed
    And I click the Play button located at the bottom of the screen
    When I hover over the Play button at the bottom of the screen, it should now display the Pause symbol

  Scenario: Create a New Playlist From the Home Page
    Given I am logged in and my user avatar is displayed
    When I click the Add Playlist button
    And I click the New Playlist button
    And I enter the new playlist name "Another Home Page Playlist"
    Then I should see a green success message in the top right corner of the home page

  Scenario: Typing into the Searchbar in the Top Left Corner
    Given I am logged in and my user avatar is displayed
    When I type into the searchbar the song title "dark"
    Then I should see the search results and orange 'View All' button