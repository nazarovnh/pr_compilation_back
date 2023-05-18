#!/bin/bash

# set the database user and password
DB_USER="pr_compilation"
DB_PASSWORD="compilation"

# set the database name and owner
DB_NAME="pr_compilation"
DB_OWNER="pr_compilation"

# create the user and grant privileges
echo "Creating user..."
sudo -u postgres psql -c "CREATE USER $DB_USER WITH PASSWORD '$DB_PASSWORD';"

# create the database
echo "Creating database..."
sudo -u postgres psql -c "CREATE DATABASE $DB_NAME WITH OWNER=$DB_OWNER ENCODING='UTF8';"

echo "Done."
