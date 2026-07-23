# AI-Based Pest Detection and Crop Protection System

A Java web application (MVC) where farmers upload a crop-leaf photo, an AI model
(Google Teachable Machine / TensorFlow.js) detects the pest in the browser, and
the result plus treatment advice is shown and saved to MySQL.

## Tech stack
- **Backend:** Java Servlets, JDBC
- **Frontend:** HTML5, CSS3, JavaScript, JSP (with JSTL)
- **Database:** MySQL
- **AI:** Google Teachable Machine (TensorFlow.js, runs in the browser)
- **Server:** Apache Tomcat 9
- **Build:** Maven

## How the AI part works
Teachable Machine exports a TensorFlow.js model. The browser (see
`webapp/js/teachable.js`) loads that model, classifies the uploaded leaf, and
sends the predicted pest name + confidence to `DetectionServlet`, which looks up
the pest details and stores the detection. This is the standard, working way to
use Teachable Machine with a Java app — no Java ML runtime needed.

## Project structure
```
src/main/java/com/pestdetect/
  model/       POJOs (Farmer, Admin, Pest, Detection)
  util/        DBConnection, PasswordUtil
  dao/         Database access objects
  controller/  Servlets (login, register, detect, admin, reports...)
  filter/      AuthFilter (protects /farmer/* and /admin/*)
src/main/webapp/
  *.html *.jsp css/ js/ images/ uploads/   (uploads = saved leaf images)
database/schema.sql
```

## Setup (step by step)

### 1. Database
Install MySQL, then run:
```
mysql -u root -p < database/schema.sql
```
This creates the `pestdb` database, all tables, and sample data.

### 2. Configure the DB connection
Edit `src/main/java/com/pestdetect/util/DBConnection.java` and set your MySQL
username and password (default is root / root).

### 3. Build the WAR
Install Maven, then from the project root:
```
mvn clean package
```
This produces `target/pest-detection-system.war`.

### 4. Deploy on Tomcat 9
Copy the WAR into `TOMCAT_HOME/webapps/` and start Tomcat, **or** run it straight
from IntelliJ IDEA (Run > Edit Configurations > Tomcat Server > add the WAR
artifact). Requires **Tomcat 9** (this project uses the `javax.servlet` API).

Open: http://localhost:8080/pest-detection-system/

### 5. Plug in your own AI model
1. Build an image model at https://teachablemachine.withgoogle.com/
2. Name each class the same as a `pest_name` in the `pest` table.
3. Export > "Upload (shareable link)" and copy the model URL.
4. Paste it into `MODEL_URL` at the top of `webapp/js/teachable.js`.

## Demo logins
- **Farmer:** ravi@farm.com / farmer123
- **Admin:** admin / admin123

## Using IntelliJ without Maven
You can also mark `src/main/java` as Sources, add the three JARs (servlet-api,
jstl, mysql-connector-j) to the module, set `webapp` as the web resource
directory, and deploy to Tomcat 9. The Maven route above is simpler.

## Notes
- Passwords are stored as SHA-256 hashes (see `PasswordUtil`).
- Uploaded images are saved under `webapp/uploads/`.
- If you redeploy, the `uploads` folder may be recreated empty — that's expected.
