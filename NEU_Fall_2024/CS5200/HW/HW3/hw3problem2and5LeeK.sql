/* Problems 2 and 5 in homework 3 
2a. Three databases were created from this script
2b. Database ap has table names general_ledger_accounts, invoice_archive, invoice_line_items, invoices, terms, vendor_contacts, and vendors
Database ex has tables active_invoice, color_sample, customers, date_sample, departments, employees, float_sample, null_sample, paid_invoices, projects, and string_example
Database om has tables cusotmers, items, order_details, and orders
2c. om.order_detials has 68 records
2d. ap.invoices has 114 records
2e. ap vendors has 122 records
2f. yes, it references terms_id
2g. ap.vendors has 2 foreign keys 
2h. om.customers has a primary key of customer_id  
*/
-- 2i. write a SQL command that will retrieve all fields from ap.vendors
SELECT * 
FROM ap.vendors
;

-- 2j. write a SQL command that will retrieve vendor_id and invoice_id from ap.invoices
SELECT vendor_id, invoice_id
FROM ap.invoices
;

/*
5a. there are 11 tables 
5b. the table names are Album, Artist, Customer, Employee, Genre, Invoice, InvoiceLine, MediaType, Playlist, PlaylistTrack, and Track
5c. there are 347 records in the Album table
5d. AlbumId is the primary key for Album table
5e. there is no foreign keey in the Artist table to reference the Album table but Album references Artist with ArtistId
5f. there is no FK to Artist in Track
5g. there are 3505 rows in the Track table
5h. there are 347 rows in the Album table 
*/
-- 5i. write a SQL SELECT command that retrieves all fields from chinook.artist
SELECT * 
FROM Chinook.Artist;

-- 5j.write a SQL SELECT commant that retrieves FirstName, LastName, and Title from Chinook.employee
SELECT FirstName, LastName, Title
FROM Chinook.Employee;


