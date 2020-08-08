Steps
-------

Please do the following steps to run the application
1. Download Mongo DB Server
2. Open a command prompt. Go to directary where pom.xml file is there.
3. Execute mvn clean package
4. Import the project into eclipse
5. Run the project as java application
6. Check in the Browser. You can create, view and update article

Create a Article
http://localhost:8080/api/articles

JSON Body
---------
{
  "id":"10",
  "title":"MongoDb ",
  "description":"NoSql Database"
}

{
  "id":"11",
  "title":"",
  "description":"NoSql Database"
}

Update a article
-----------------

http://localhost:8080/api/articles/10

{
  "id":"10",
  "title":"MongoDB Tutorial",
  "description":"NoSql Database Management"
}

View a articles
---------------

http://localhost:8080/api/articles

[{"id":"0","title":"IOC4","description":"IOC Container4","createdAt":"2020-08-06T05:24:01.587+0000","updatedAt":"2020-08-06T05:24:01.587+0000"},{"id":"2","title":"IOC2","description":"IOC Container2","createdAt":"2020-08-06T05:36:42.445+0000","updatedAt":"2020-08-06T05:36:42.445+0000"},{"id":"3","title":"IOC Updated","description":"IOC Container3 updated","createdAt":"2020-08-06T05:37:05.093+0000","updatedAt":"2020-08-07T15:12:01.557+0000"},{"id":"344","title":"IOC","description":"IOC Container3","createdAt":"2020-08-07T15:54:20.635+0000","updatedAt":"2020-08-07T15:54:20.635+0000"},{"id":"345","title":"IOC new","description":"IOC Container new","createdAt":"2020-08-07T15:55:24.871+0000","updatedAt":"2020-08-07T15:55:24.871+0000"},{"id":"10","title":"MongoDb ","description":"NoSql Database","createdAt":"2020-08-08T06:25:26.871+0000","updatedAt":"2020-08-08T06:25:26.871+0000"}]

View a article by id
---------------------

http://localhost:8080/api/articles/10

{"id":"10","title":"MongoDb ","description":"NoSql Database","createdAt":"2020-08-08T06:25:26.871+0000","updatedAt":"2020-08-08T06:25:26.871+0000"}

View a article by id which is not there
----------------------------------------
Feedback looks like

<error>
<details>Invalid article id : 111</details>
<message>Record Not Found</message>
</error>


7. For any help, you can call me@ 6370622949
