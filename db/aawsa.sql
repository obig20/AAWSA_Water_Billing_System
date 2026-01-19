CREATE TABLE users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username TEXT UNIQUE,
  password TEXT,
  role TEXT
);

CREATE TABLE customers (
  id TEXT PRIMARY KEY,
  customer_code TEXT UNIQUE,
  name TEXT,
  address TEXT,
  meter_number TEXT UNIQUE,
  customer_type TEXT,
  previous_reading REAL DEFAULT 0,
  balance REAL DEFAULT 0
);

CREATE TABLE meter_readings (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  customer_id TEXT,
  reading_date TEXT,
  current_reading REAL,
  consumption REAL,
  FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE bills (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  customer_id TEXT,
  bill_date TEXT,
  consumption REAL,
  total_amount REAL,
  status TEXT DEFAULT 'Unpaid',
  FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE payments (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  bill_id INTEGER,
  amount REAL,
  payment_date TEXT,
  payment_method TEXT,
  FOREIGN KEY (bill_id) REFERENCES bills(id)
);

-- sample admin
INSERT INTO users VALUES (1,'admin','admin123','ADMIN');
