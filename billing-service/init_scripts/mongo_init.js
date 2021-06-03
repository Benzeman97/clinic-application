
var db = connect("mongodb://admin:14292@mongo-db/admin");

db = db.getSiblingDB('billing_db');

db.createUser(
     {
        user: "billing_db",
        pwd: "14292",
        roles: [ { role: "readWrite", db: "billing_db" } ]
    }
);

db.createCollection('billing');

db.billing.insert({id:1006,billed_date_time:'23-09-2021 18:23:23',visited_id:'COL9823'}); //mongodb does not support foreign key