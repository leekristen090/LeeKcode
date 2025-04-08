# Kristen Lee

library(DBI)

# promp for user input
username <- readline(prompt = "Enter MySQL username: ")
password <- readline(prompt = "Enter MySQL password: ")

#myDB <- dbConnect(drv=RMySQL::MySQL(), username = username, password = password, host="127.0.0.1" ,port = 3306, dbname="bus_schema_leek")
myDB <- tryCatch({
  dbConnect(drv = RMySQL::MySQL(), username = username, password = password, host = "127.0.0.1", port = 3306, dbname = "bus_schema_leek")
}, error = function(e) {
  cat("Error in connecting to the database:", e$message, "\n")
  NULL
})

if (!is.null(myDB)) {
  cat("Successful connection!")
  
  repeat {
    cat("\nMenu: \n")
    cat("1: generate a list of journeys for a specific user\n")
    cat("2: disconnect from the database and close the application")
    # Prompt the user for a menu choice
    
    choice <- as.integer(readline(prompt = "Enter your choice: "))
    
    # Validate the choice input
    if (is.na(choice) || !(choice %in% c(1, 2))) {
      cat("Invalid choice. Please enter 1 or 2.\n")
      next  # Skip to the next iteration of the loop
    }
    
    if (choice == 1) {
      cust_query <- "SELECT DISTINCT username FROM customer"
      cust_list <- dbGetQuery(myDB, cust_query)
      
      if (nrow(cust_list) > 0) {
        # cat("\nList of available usernames:\n")
        print(cust_list)
        cat("\nList of available usernames:\n")
        cat(paste(cust_list$username, collapse = "\n"))  # Print usernames line by line
        cat("\n")
      } else {
        cat("No usernames found in the database.\n")
        next
      }
      
      # Prompt for a username, ignoring case
      selected_username <- readline(prompt = "Enter a username from the list above: ")
      
      # Call the get_customer_journeys() procedure with the selected username
      journeys_query <- paste0("CALL get_customer_journeys('", selected_username, "')")
      journeys <- dbGetQuery(myDB, journeys_query)
      
      # Display the journeys in tabular format
      if (nrow(journeys) > 0) {
        cat("\nJourneys for user:", selected_username, "\n")
        print(journeys, row.names = FALSE)  # Print without row names for cleaner output
      } else {
        cat("No journeys found for the username:", selected_username, "\n")
      }
      
      
    } else if (choice == 2) {
      # Disconnect from the database and exit the loop
      dbDisconnect(myDB)
      cat("Disconnected from the database. Goodbye!\n")
      break
    } else {
      # Handle invalid input
      cat("Invalid choice. Please try again.\n")
    }
    
  }
  
} else {
  cat("Failed to connect to the database. Please check your credentials.\n")
} 
