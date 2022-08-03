# Reddit Clone API

<h2>Project Live Link (Swagger 3.0 Documentation): https://reddit-clone-sb.herokuapp.com/api/swagger-ui/index.html</h2>

<h2>Working Demonstration Video Link: https://youtu.be/QhsgrZdpsGw</h2>

<h2>Frontend (UI Part) Project Link: https://github.com/Anjaniy2000/reddit-clone-frontend-angular</h2>

<h2>Project Summery:</h2>

- Prepared A Clone of Famous Discussion Forum Called Reddit, With Spring boot & Angular.
- It Has Features Such as User Can Login/Logout, Create Post, Create Subreddit, Use Subreddit, Comments on Various Posts & Upvote/Downvote Various Posts.

<h2>Tech-Stack / Technologies:</h2>

- Backend:
  - Java 1.8
  - Spring Boot
  - PostgreSQL
  - Heroku (For Deployment)
  - Mailtrap (For Email Testing / Fake SMTP Server)
  - Swagger 3.0

<h2>Dependencies / Dev-Dependencies:</h2>

- Maven - Dependencies (Backend):
  - Spring Web
  - Spring Security
  - Lombok
  - Spring Data JPA
  - PostgreSQL Driver
  - Spring Boot Dev Tools
  - MapStruct
  - Spring Boot Starter Validation
  - Java Mail Sender
  - JSON Web Token / JWT - Authentication (jjwt-api, jjwt-impl, jjwt-jackson)
  - Thymeleaf
  - Springdoc OpenApi
  
You Can Access The API Documentation [Here](https://reddit-clone-spring-boot.herokuapp.com/swagger-ui.html).

You Can Configure Your Database, Hibernate And Email Functionality By Adding Properties Into [application.properties](https://github.com/amycardoso/spring-reddit-clone/blob/master/src/main/resources/application.properties).

In This Application, Users Receive Account Activation Emails And Comment Notification Emails, For That Reason, We Need An SMTP Server To Send The Emails, We Can Use A Fake SMTP Server Called [MailTrap](https://mailtrap.io/).

<h2>Application Screenshots: </h2>

<h3>Home : </h3>

![Screenshot](/src/main/resources/screenshots/home.PNG)

<h3>Register User : </h3>

![Screenshot](/src/main/resources/screenshots/register.PNG)

<h3>Login User : </h3>

![Screenshot](/src/main/resources/screenshots/login.PNG)

<h3>Posts / Feeds : </h3>

![Screenshot](/src/main/resources/screenshots/posts-list.PNG)

<h3>Create Subreddit : </h3>

![Screenshot](/src/main/resources/screenshots/create-subreddit.PNG)

<h3>Create Post : </h3>

![Screenshot](/src/main/resources/screenshots/create-post.PNG)

<h3>Create Comment : </h3>

![Screenshot](/src/main/resources/screenshots/create-comment.PNG)

<h3>View Post : </h3>

![Screenshot](/src/main/resources/screenshots/view-post.PNG)

<h3>View Profile : </h3>

![Screenshot](/src/main/resources/screenshots/view-profile.PNG)

<h3>Home (Without Login) : </h3>

![Screenshot](/src/main/resources/screenshots/home.PNG)
