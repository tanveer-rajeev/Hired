# Hired
## Features
- There have two roles employee and company. An employee will create a profile along with a resume and the company will also create a profile.<br/>
- An employee can find out the company by job category and subscribe it.so when a company will create a job opening or some features update 
then the employee will be notified.Observer pattern used  here for notification service.<br/>
- Employees can able to search for a job by job title, skill, technology, company name. There will be such a feature by which an employee can able not only apply for a job by searching himself but also from the latest company notification of which an employee has subscribed.<br/> 
- When an employee applies for a job, the company will get an employee profile along with a resume. The company will be able to search an employee more precisely by filtering such as job title, expert skill, secondary skill, university, experience, and others.<br/>
- After getting a list of employees at that moment employee profile will be visualized not resume. This profile consists of some peak information that the company is concerned about hiring an employee such as expert skill, secondary skill,university, experience.

In a short time company can find out their expected employees by searching for some peak requirement keyword.so that a company can segregate easily as their expectations and can offer those particular employees for the interview.

## Entity Relationship
![Hired Entity Diagram](https://user-images.githubusercontent.com/39630470/136983004-e5939dbe-edb7-440b-a19e-d0a166e58988.PNG)

## How to run
Prerequisite
* JDK 1.8
* Maven 4.0.0
```
mvn install
```
Run
```
mvn spring-boot:run
```
## Api Documantation
- Admin APIs endpoints
  ```
  localhost:8080/api/jobCategories
  ```
  Request Body
  ```
  {
  "category":"computer engineering"	
  }
  ```
- Employee APIs endpoints
   ```
   localhost:8080/api/employees/signUp 
   ```
   Request Body
   ```
   {
    "email":"abcd@gmail.com",
    "password":"123",
    "jobCategory":{
         "category":"computer engineering "
    }
   }
   ```
   >Default log in url provieded by spring security
   ```
   localhost:8080/login
   ```
   
   > After login, the Json Web Token will be created into the "Authorization" header section.
   > Then after each GET request must carry this token otherwise  403 forbidden error will showed.
   
   > API endpoint for creating portfolio into CSE department
   ```
   localhost:8080/api/cseEmployees
   ```
   Request Body
   ```
   {
    "name":"ronnie",
    "universityBsc":{
  	"universityName":"uits"
    },
    "jobField":{
  	"field":"software engineer"
    },
    "expertSkills":[{
  	"skill":"java script"
    },{
  	"skill":"java"
    }],
    "secondarySkills":[{
  		"skill":"python"
    }],
  	"availableForJob":"yes",
  	"yearOfExperience":"2 year 1 month"
  }
    ```
    > Api endpoint for upload resume (PDF file)
    Post Request must be contains with param type File that controller handle as a Multipartfile 
    ```
    
    ```
    > APIs for a job search in several ways
    ```
    localhost:8080/api/search/jobs/findByJobCategory
    localhost:8080/api/search/jobs/findByLocation
    localhost:8080/api/search/jobs/findByExpertSkill
    localhost:8080/api/search/jobs/findBySecondarySkill
    localhost:8080/api/search/jobs/company/{companyId}/job/{jobId}
    ```
    > This 2 API endpoints for an employee subscribe to a company and apply for a job by employee id and company id.
    > Observer pattern has used for subscription  relationship between employee and company
    ```
    Post Request for subscription
    localhost:8080/api/employees/subscribe/3/employeeId/2
    ```
    ```
    Post request for apply a job
    localhost:8080/api/employees/27/jobApply/jobId/40
    ```
- Company APIs
   ```
   localhost:8080/api/companies
   ```
   Request Body
   ```
   {
	"email":"amazon@gmail.com",
	"password":"123",
	"companyName":"amazon",
	"address":"usa",
	"companyActivities":"we created largest e-commerce web site",
	"companyWebsiteLink":"www.amazon.com"
  }
  ```
  > API for post a job circular.
   > Subscriber will get notification 
  ```
  localhost:8080/api/jobs/{companyId}/createJob
  {
	"jobCategory":{
		"category":"java developer"
	},
	"companyJobTitle":{
		"jobTitle":"Software Engineer"
	},
	"vacancy":4,
	"jobResponsibility":"Excellent understanding of OOP.Strong programming and problem-solving skills.",
	"employmentStatus":"Full-time",
	"educationRequirements":"B.Sc in Computer Science & Engineering from a reputed university",
	"experienceRequirements":"At least 5 year(s)",
	"additionalRequirements":" Spring frameworks is preferred.Excellent knowledge on Java language.Must have good knowledge and experience in standard SQL.",
	"jobLocation":"Dhaka",
	"salary":230000,
	"compensationOtherBenefits":"Mobile bill, Weekly 2 holidays.Salary Review.Yearly.Festival Bonus: 2",
	"applicationDeadline":"2020-05-10",
	"enable":true
  }
  ```
  > Post request for diactivate a job circular 
  ```
  localhost:8080/api/companies/diactivate/{jobId}
  ```
  > Post request for unsubscribe an employee
  ```
  localhost:8080/api/companies//unsubscribe/{companyId}/{employeeId}
  ```

