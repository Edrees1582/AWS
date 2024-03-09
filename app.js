const express = require('express');
const app = express();

const mysql = require('mysql2');

const connection = mysql.createConnection({
  host: 'database-1.c7u4s8q2kq4k.us-east-1.rds.amazonaws.com',
  user: 'admin',
  password: 'AtyponRoot#1',
  database: 'todos'
});

connection.connect((err) => {
  if (err) {
    console.error('Error connecting to MySQL database:', err);
    return;
  }
  console.log('Connected to MySQL database');
});

app.get('/', (req, res) => {
  connection.query('SELECT * FROM todos', (err, results, fields) => {
    if (err) {
      console.error('Error executing query:', err);
      return;
    }
    const data = results.map(result => {
                 return result.todo; });
    res.status(200).send(`Server IP:${req.connection.localAddress}<br />Todos from MySQL: ${data}`);
  });
});

const port = 3000;
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
