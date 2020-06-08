# Charity

This is Java-based web application project for a charity whose main purpose is to enable its users to make donations to the institution of their choice and the administrators to manage the data stored in the database. 

## Prerequisites
**Before installing you need:**
* Java 8 or later
* Apache Maven (or [IDE that supports an integration with Maven](https://stackoverflow.com/questions/7025229/free-java-ide-with-the-best-maven-integration) e.g. IntelliJ IDEA)
* MySQL

**Useful but not necessary:**
* IDE that supports an integration with Maven
* Git or Subversion - to benefit from the advantages and functionality of version control systems

## Installation

**Clone or download**

* Clone or download this repository to your local machine using `https://github.com/jakubde/Charity.git`


**Create database**
* Database console query:

```sql
CREATE DATABASE charity_donation
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
```
**Set the connection to the database**
* Change values of `spring.datasource.username` and `spring.datasource.password` keys to yours in [application.properties](https://github.com/jakubde/Charity/blob/master/src/main/resources/application.properties) file to be able to connect to the database. **Remember that it is better not to give your database credentials directly.** The way how to hide them in properties file can be found e.g. [here](https://stackoverflow.com/questions/35531661/using-env-variable-in-spring-boots-application-properties) or [here](https://stackoverflow.com/questions/37404703/spring-boot-how-to-hide-passwords-in-properties-file).

* Run the application so that the database tables are created automatically.

* To ensure the correct operation of the application, add data to the database using the inserts from the following files:
   * [charity_donation_categories.sql](https://github.com/jakubde/Charity/blob/master/src/main/resources/database/charity_donation_categories.sql)
   * [charity_donation_donation_status.sql](https://github.com/jakubde/Charity/blob/master/src/main/resources/database/charity_donation_donation_status.sql)
   * [charity_donation_institutions.sql](https://github.com/jakubde/Charity/blob/master/src/main/resources/database/charity_donation_institutions.sql)

* To have sample data to see how the application works from the admin side you can also add data from files:
   * [charity_donation_users.sql](https://github.com/jakubde/Charity/blob/master/src/main/resources/database/charity_donation_users.sql)
   * [charity_donation_user_authorities.sql](https://github.com/jakubde/Charity/blob/master/src/main/resources/database/charity_donation_user_authorities.sql)
   * [charity_donation_donations.sql](https://github.com/jakubde/Charity/blob/master/src/main/resources/database/charity_donation_donations.sql)

**Set up an email account**  

1. Create [Gmail](https://www.google.com/gmail/) account from which application will send emails with a link to verify the user or reset the password. Currently, Google may require a phone number to create an email account. There are 3 ways to get around this:
   * If you have a smartphone with android you can work around this by adding a new account in the Gmail application [(first option)](https://i.insider.com/5cd1d0e3021b4c08ea1ea3f8?width=1061&format=jpeg) - there you can skip the step of giving the phone number, and then remove this account from the list.
   * If you don't have a smartphone with android, you can download a free emulator (e.g. [BlueStacks](https://www.bluestacks.com/) for Windows or macOS, [Android-x86](https://www.android-x86.org/) for Linux) of android device and create this account there.
   * If these methods do not work, look for a solution on google or on youtube.

1. After creating this email, set values of `spring.mail.username` and `spring.mail.password` keys  in [application.properties](https://github.com/jakubde/Charity/blob/master/src/main/resources/application.properties) file. **Remember that it is better not to give your email password directly.** The way how to hide password in properties file can be found e.g. [here](https://stackoverflow.com/questions/35531661/using-env-variable-in-spring-boots-application-properties) or [here](https://stackoverflow.com/questions/37404703/spring-boot-how-to-hide-passwords-in-properties-file).

## Usage



## Licence

[MIT](https://choosealicense.com/licenses/mit/)
