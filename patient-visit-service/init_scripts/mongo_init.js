 
 var db = connect("mongodb://admin:14292@127.0.0.1:27017/admin");

db = db.getSiblingDB('patient_db'); /* 'use' statement doesn't support here to switch db */

db.createUser(
    {
        user: "patient_db",
        pwd: "14292",
        roles: [ { role: "readWrite", db: "patient_db" } ]
    }
);


db.createCollection("holiday");

db.holiday.insert({holiday_date:'25-12-2021',holiday_name:'Christmas',created_by:'John Wick',modified_by:'John_Wick',created_date_time:'2021-04-25 04:23:55',modified_date_time:'2021-04-25 04:23:55'});

