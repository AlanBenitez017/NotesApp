# Notes App

## Project Overview

This is a full-stack note-taking web application built with:

- **Backend**: Spring Boot 3.4.2 (Java 17)
- **Frontend**: React.js (19.0.0)
- **Database**: PostgreSQL

## Runtimes, Engines and Tools used

- **Java 17**
- **Gradle**
- **PostgreSQL**
- **Node.js 18.17+** and **npm** (11.1.0)
- **React DOM** (19.0.0)
- **React Router DOM:** (7.1.5)
- **React Scripts:** (5.0.1)
- **Axios:** (1.7.9)

## Setup Instructions

### 1. Clone the Repository

```sh
git clone <the-repo-url>
cd Benitez-4c9a4f
```

### 2. Configure Database

Update database credentials in `run.sh`:

```sh
export DB_USER=your_db_user
export DB_PASSWORD=your_db_password
```

**Backend:** Update the database credentials in src/main/resources/application.properties:
```sh
spring.datasource.url=jdbc:postgresql://localhost:5432/notesApp
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
```

### 3. Run the Application

Make the script executable and run it:

```sh
chmod +x run.sh
./run.sh
```

This will:

- Ensure PostgreSQL is running and the database exists.
- Start the Spring Boot backend.
- Start the React frontend.

### 4. Access the App

- **Frontend**: `http://localhost:3000`
- **Backend API**: `http://localhost:8080`

## Development

- **Backend**: `cd backend && ./gradlew bootRun`
- **Frontend**: `cd frontend && npm start`

## Build and Deployment

- Backend: `./gradlew build`
- Frontend: `npm run build`

## Troubleshooting

- Ensure PostgreSQL is running and accessible.
- Check `run.sh` for correct database credentials.
- If ports 3000/8080 are occupied, change them in `application.properties` (backend) and `.env` (frontend).

