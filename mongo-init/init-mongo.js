//use admin
//db.createUser({
//  user: "root",
//  pwd: "root",
//  roles: [ { role: "root", db: "admin" } ]
//});

db = db.getSiblingDB('tasktrackerdb');

db.createCollection('user');
db.createCollection('task');

