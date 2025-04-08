HW 8
CS 5200
Kristen Lee

This is my application program written in R and connects to a MySQL database named bus_schema_leek.
It prompts a user to give a username and password and offers options to retrieve journey details
for a specific customer or to disconnect from the database.

I have R and RStudio downloaded on my computer which helped me to build and run this application.
This program uses DBI and RMySQL packages to allow R to interact with the MySQL database.

In RStudio I installed and loaded those packages using the following syntax:

install.packages("DBI")
install.packages("RMySQL")

library(DBI)
library(RMySQL)

I created a new .Rmd file to hold my application logic/implementation. To run, click "Run all"
option in the RStudio editor, and in the console it will prompt the user for their username then
their password. If their username or password is invalid, they console will throw an error, and then
ask the user if they would like to try again. After they have successfully connected to the database
they will be prompted with 2 menu options and asked to enter their choice. If the choice is not a
valid one, the user will be prompted to enter another choice. Choice 1 will produce a list of
valid customer usernames. The user will be prompted to enter a customer username from the given
list. If the customer username typed does not exist a table will not be displayed and will return
no journeys for <username>. When a valid customer is entered, the procedure will be run and a table
will be returned with journey information accordingly. The user can then find journeys for another
customer or choose 2 to exit. Journey tables will be found in Environment panel of RStudio.
