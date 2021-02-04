# Charity

This is a project of an web application based on Java language designed for a charity. Its main purpose is to enable users to make donations to the institution of their choice. The application also enables administrators to manage data stored in the database.

## Table of contents

  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Usage](#usage)
  - [Features](#features)
  - [Built with](#built-with)
  - [Examples of use](#examples-of-use)
  - [Other information](#other-information)
  - [Licence](#licence)

## Prerequisites
### Before installing you need:
* [Java 8 or later](https://www.java.com/download/)
* [Maven](https://maven.apache.org/download.html) 
* IDE that supports an integration with Maven (e.g. [Netbeans](https://netbeans.apache.org/download/index.html))
* [MySQL](https://dev.mysql.com/doc/mysql-getting-started/en/#mysql-getting-started-installing)

## Installation

### Prequisities

Make sure you have installed all the programs listed in the [Prerequisites](#prerequisites) section.

### Clone or download

[Clone](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository#cloning-a-repository-using-the-command-line) repository to your local machine using `https://github.com/jakubde/Charity.git` or [download zip file](https://github.com/jakubde/Charity/archive/master.zip).


### Create database

You can create database using your [system CLI](https://www.w3schools.com/whatis/whatis_cli.asp) in two ways:

  * Execute a single MySQL command directly from the CLI of your system and enter the password for the root user when asked (this is the password you specified during the installation of MySQL):
 
    ```sh
    mysql -uroot -p -e "CREATE DATABASE charity_donation CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci"
    ```
  
  * Or perform the above operation in two steps:
  
    Access the MySQL command line client from system CLI with following command:    

    
    ```sh
    mysql -u root -p
    ```
    
    While in the MySQL shell, run the following command to create the database:    

    
    ```sql
    CREATE DATABASE charity_donation
        CHARACTER SET utf8mb4
        COLLATE utf8mb4_unicode_ci;
    ```
    
    To quit MySQL shell type `quit` and confirm.

### Fill the database with data

The database must be populated with some data in order for the application to work correctly (e.g. it would be pointless if we wanted to donate, and there would be no institutions to choose from).

  * First open the CLI of your system. Then if you use Windows [change directory](https://www.howtogeek.com/659411/how-to-change-directories-in-command-prompt-on-windows-10/) to 

    ```<path_to_project>\Charity\src\main\resources\database```

    else if you use GNU/Linux [change directory](https://www.cyberciti.biz/faq/how-to-change-directory-in-linux-terminal/) to 

    ```<path_to_project>/Charity/src/main/resources/database```

    Where:

    * <path_to_project> is path to directory where you cloned or unzipped this application e.g. `C:\Users\John\Projects` for Windows or `/home/john/Projects` for       GNU/Linux

  * If you want to populate the database with only the basic data necessary for the application to work, use the command:
  
    ```sh
    mysql -uroot -p charity_donation < basic_database.sql
    ```
    otherwise, if you want to fill database with a lot of specially generated data (e.g. dummy users, donations etc.) to see how the application interacts with the data, use the command:
    
    ```sh
    mysql -uroot -p charity_donation < completed_database.sql
    ```
    Both of the above options add to the database (among other data) one ordinary user and one admin with passwords given in an explicit manner. These users were added in order to be able to use the application from both the user and admin side immediately after installation. The data of these users can be edited or deleted later (in the case of the admin it is necessary to create a new admin before deleting the only admin).

    <table>
      <thead>
          <tr>
              <th colspan = 3><a name="credentials-of-added-users">Credentials of added users</a></th>
          </tr>
      </thead>
      <tbody>        
          <tr>
              <th></th>
              <th>E-mail</th>
              <th>Password</th>
          </tr>
          <tr>
              <th>Regular User</th>
              <td>john.doe@mail.com</td>
              <td>Password</td>
          </tr>
          <tr>
              <th>Admin</th>
              <td>admin@mail.com</td>
              <td>Admin123</td>
          </tr>
      </tbody>
    </table>


### Configure the connection of the application to the database
If you intend to use the root user to connect to the database, change value of `spring.datasource.password` key to your MySQL root password in [application.properties](https://github.com/jakubde/Charity/blob/master/src/main/resources/application.properties) file (which can be found in `<path_to_project>/Charity/src/main/resources/`) to be able to connect to the database.

If you don't want to use MySQL root user to perform operations on the database, you can [create new user and grand him all privileges](https://linuxize.com/post/how-to-create-mysql-user-accounts-and-grant-privileges/) to charity_donation database. Then you need to change both values of both `spring.datasource.password` and `spring.datasource.username` keys in application.properties file.

**Remember that it is important not to give your database credentials directly.** The way how to hide them in properties file can be found e.g. [here](https://stackoverflow.com/questions/35531661/using-env-variable-in-spring-boots-application-properties) or [here](https://stackoverflow.com/questions/37404703/spring-boot-how-to-hide-passwords-in-properties-file).

### Configure application email

1. Create a [Gmail](https://www.google.com/gmail/) account for your app. This will be the account from which the application will send verification link emails when you create a new account, or password reset link emails.

1. Allow less secure apps to access your Gmail account. Google may block login attempts from this application and you should therefore enable this option in your account settings:

    * Log in to your newly created email and click on Google apps icon   ![Step one](https://i.ibb.co/09WNCLq/google-apps.png)
    * Then click on the "Account" button   ![Step two](https://i.ibb.co/KrZWFM4/account.png)
    * Choose the "Security" tab and scroll down to the "Less secure app access" section   ![Step three](https://i.ibb.co/9stcYc2/security.png)
    * Toggle switch to "on"   ![Step four](https://i.ibb.co/Zf6qpgt/less-secure-app-access.png)
    
    
    


1. As with configuring the database, set value of `spring.mail.username` key with your newly created email address and `spring.mail.password` with the password for this email in application.properties file. **Remember that it is important not to give your email password directly.** The way how to hide password in properties file can be found e.g. [here](https://stackoverflow.com/questions/35531661/using-env-variable-in-spring-boots-application-properties) or [here](https://stackoverflow.com/questions/37404703/spring-boot-how-to-hide-passwords-in-properties-file).

## Usage

1. If you have followed the steps outlined in the [Installation](#installation) section, you can now run the application. You need to start your IDE and open an existing project. Here are some tutorials on how to do this for the most popular Java IDEs:
    - [IntelliJ IDEA](https://www.jetbrains.com/help/idea/maven-support.html#maven_import_project_start)
    - [Netbeans](http://wiki.netbeans.org/MavenBestPractices#Open_existing_project)
    - [Eclipse](https://javabydeveloper.com/import-maven-project-eclipse/) (section "1. Import Maven project into eclipse")

1. Build imported project:
    - [IntelliJIDEA](https://www.jetbrains.com/help/idea/delegate-build-and-run-actions-to-maven.html#build_through_maven)
    - Netbeans - Right-click the project node of the app and choose Build with Dependencies
    - [Eclipse](https://javabydeveloper.com/import-maven-project-eclipse/) (section "2. Build Maven Project")

1. As this is a [Spring Boot](https://spring.io/projects/spring-boot) application, it contains a special class with the `@SpringBootApplication` annotation and `main()` method. In this application the name of this class is `CharityApplication` and it can be found in `<path_to_project>/Charity/src/main/java/pl/coderslab/charity/`. To run the application you need to run this class.

1. Once the application is up and running, open a web browser and go to http://localhost:8080/ to get to the landing page.

If you want to use app as regular logged-in user or admin log in with credentials given in [Credentials of added users table](#credentials-of-added-users).

## Features

## Examples of use

## Built with

## Other information
- The [generatedata.com](https://www.generatedata.com/) website was used to generate [dummy data](https://en.wikipedia.org/wiki/Dummy_data) for the database.

## Licence


This project is licensed under the terms and conditions of the [MIT License](https://choosealicense.com/licenses/mit/) - for more details see the LICENSE file.
