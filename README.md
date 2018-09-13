# BookStore

![](http://www.reynodigital.com/etc/1.PNG)

## Objective

Program implements an event-driven application with a graphical user interface that helps simplify the process of checking book availability, place orders and log transactions to a text file.

## Description

The program should simulate an e-store which allows users to add items (books) to a shopping car and once all items are included, total all costs (including tax), produces an invoice, and append a transaction log file.

### Specifications

1. Create a main GUI containing the following components:
  * An area that allows the user to input data into the application along with the descriptive text that describes each input area.
  * A total of six buttons as shown below with functionality as described below.
  * Various buttons on the interface are only accessible at certain points during a user’s interaction with the e-store.
2. An input file named “inventory.txt”. This is a comma separated file which contains the data that will be read by the 
application when the user makes a selection. 
3. An output file named “transactions.txt” must be created that uniquely logs each user transaction with the e-store. The unique transaction id will be generated as a permutation of the date/time string when the transaction occurred.

![](http://www.reynodigital.com/etc/3.PNG)
