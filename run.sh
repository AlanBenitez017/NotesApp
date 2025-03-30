#!/bin/bash

# Set environment variables
export DB_NAME=notesApp
export DB_USER=your_db_user
export DB_PASSWORD=your_db_password
export DB_HOST=localhost
export DB_PORT=5432

# Check if PostgreSQL is running
if ! pg_isready -h $DB_HOST -p $DB_PORT -U $DB_USER; then
    echo "PostgreSQL is not running. Please start the database first."
    exit 1
fi

# Create database if it doesn't exist
psql -U $DB_USER -h $DB_HOST -tc "SELECT 1 FROM pg_database WHERE datname = '$DB_NAME'" | grep -q 1 || psql -U $DB_USER -h $DB_HOST -c "CREATE DATABASE $DB_NAME;"

echo "Database is ready."

# Navigate to backend and start Spring Boot server
cd backend || exit
echo "Starting backend..."
./gradlew bootRun &
BACKEND_PID=$!
cd ..

# Navigate to frontend and start React app
cd frontend || exit
echo "Starting frontend..."
npm install
npm start &
FRONTEND_PID=$!

# Wait for processes to end
wait $BACKEND_PID $FRONTEND_PID
