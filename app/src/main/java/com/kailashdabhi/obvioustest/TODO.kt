package com.kailashdabhi.obvioustest

/**
Obvious Take Home Exercise
Instructions

Your task is to create an Android app that will let the user create and view notes.
The screens to be created are explained below.

Make a git repository for the project and send this to us once you are done.
Write code incrementally, make multiple commits, just like you would in a real project.


Screens

1. List Notes Screen

This is the home screen. When it launches, list all the notes the user has created in
reverse chronological order (latest created note first).

This screen must contain an action (either a button or an action bar menu option)
that opens screen 2. Tapping on a note should open screen 3.


2. Create Note Screen

Show the following text fields along with a button to “Save” the note:

Title: Free text, max 100 characters
Content: Free text, no limit

The only validation on these fields is that they must not be blank.
The user must be able to enter values for both these fields.
Hitting the “Save” button should save the note and take the user to screen 3.


3. Note Detail Screen

Show the note title and content, along with the timestamp when the note was created
(ex: ’13 January 2018, 5:30 PM’).
This screen does not have any interaction and just displays static content.

If the user has arrived on this screen after immediately creating a note,
pressing back on this screen should take them to screen 1.


Misc.

Store the notes in memory. It is not necessary to persist them on disk.
The criteria we use to score your submission are detailed on our Playbook at
https://playbook.obvious.in/hiring/hiring-process/engineering-hiring/scoring-a-take-home-exercise
Host the solution as a public repository on any service (GitHub, Bitbucket, or Gitlab are all good options)
and send us the link. We will evaluate the master branch unless otherwise specified.
The app must be built with the Android SDK in either Kotlin or Java.
Cross-platform framework solutions (React Native, Flutter, etc) will not be accepted.
 **/