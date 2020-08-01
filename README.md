# SpringBoot-JPA-Blog


# BootStrap
1. w3schools.com  
2. materializecss.com
3. https://summernote.org/


# Ajax
I use Ajax for sign up function.  
The reason of using ajax, 
  - ajax can define only one server to be able to return data or html  
    - client request by browser. 
    - server response by html.  
    - browser read html and show to client.  
    - server connect to DB and execute sign up function.
    - Data can't return to browser  
  - Asynchronous Communication

 # csrf, xss
  - xss : javascript attack -> for depend, use lucy filter in naver site
  - csrf : cross site request forgery -> csrf token
  
  
 # Mysql setting in CentOS 8.x
    yum -y install mysqld*  
    yum -y install git  
    yum -y install java-1.8.0-openjdk-devel.x86_64  
      
    java -version //check jdk 1.8  
    ps -ef | grep mysqld  
    service mysqld start // if there is no PID of mysqld  
    /usr/bin/mysql_secure_installation  //setting root password  
    
    mysql -u root -p  
    create user '[userid]'@'%' identified by '[pw]';  
    grant all privileges on *.* to '[userid]'@'%';  
    create database [dbname] character set utf8 default collate utf8_general_ci;  
    use [dbname];  
      
    mkdir ~/git  
    cd ~/git  
    git clone [githuburl]  
    cd [github directory]  
    vi ./src/main/resources/application.yml // check db uri, user info
    cd ~/git/[github directory]  
    ./mvnw clean package  
    cd target // check jar file
    
    java -jar [jar file name] &
 
