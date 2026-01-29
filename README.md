# Console-based Knowledge Map Builder

A **Java console application** built using the **MVC (Model–View–Controller) architecture** that allows users to create, store, traverse, and study **topic-wise mind maps** using a MySQL database.
The system supports **graph-based knowledge representation**, **path finding**, and **spaced repetition–based study mode** to enhance learning efficiency.

---

## 📌 Project Motivation & Importance

Traditional note-taking is linear and inefficient for complex subjects.
This project solves that by enabling:

* 📚 **Conceptual learning** using mind maps (nodes & links)
* 🔗 **Relationship-based understanding** between topics
* 🔁 **Spaced repetition** for long-term memory retention
* 🧠 **Graph traversal & path finding** for structured study

---

### ✔ MVC Separation

* **Model** → Data structures (Node, Link, UserNote)
* **View** → Console-based UI
* **Controller** → Business logic, traversal, search, study mode
* **DB Layer** → JDBC-based persistence

This separation improves:

* Maintainability
* Scalability
* Testability

---

## ✨ Features

* 🧩 Create topic-wise **mind map nodes**
* 🔗 Link nodes with relationships
* 🔍 Search nodes by topic
* 🧭 Traverse mind maps (graph traversal)
* 🛣️ Find learning paths between concepts
* 📖 Study Mode with **spaced repetition**
* ⏰ Show due reviews for revision
* 💾 Persistent storage using **MySQL**

---

## 🛠️ Tech Stack

| Component       | Technology                     |
| --------------- | ------------------------------ |
| Language        | Java                           |
| Architecture    | MVC                            |
| Database        | MySQL                          |
| DB Access       | JDBC                           |
| Build           | Command-line (`javac`, `java`) |
| Version Control | Git / GitHub                   |

---

## 📂 Project Structure

```
MindMap-Builder-MVC/
│
├── src/
│   ├── main/
│   │   └── App.java
│   │
│   ├── controller/
│   │   └── MapController.java
│   │
│   ├── model/
│   │   ├── Node.java
│   │   ├── Link.java
│   │   └── UserNote.java
│   │
│   ├── view/
│   │   └── ConsoleView.java
│   │
│   └── db/
│       ├── DBConnection.java
│       ├── NodeDAO.java
│       ├── LinkDAO.java
│       └── UserNoteDAO.java
│
├── lib/
│   └── mysql-connector-j-9.5.0.jar
│
├── bin/        (compiled .class files)
│
├── config.properties
├── README.md
└── .gitignore
```

---

## 🗄️ Database Setup

### 1️⃣ Create Database

```sql
CREATE DATABASE knowledgemap;
USE knowledgemap;
```

### 2️⃣ Create Tables

```sql
CREATE TABLE nodes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100),
  topic VARCHAR(100),
  content TEXT,
  created_at DATE,
  next_review_date DATE
);

CREATE TABLE links (
  id INT PRIMARY KEY AUTO_INCREMENT,
  from_node INT,
  to_node INT,
  relation VARCHAR(50),
  FOREIGN KEY (from_node) REFERENCES nodes(id),
  FOREIGN KEY (to_node) REFERENCES nodes(id)
);

CREATE TABLE user_notes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  node_id INT,
  note TEXT,
  created_at TIMESTAMP,
  FOREIGN KEY (node_id) REFERENCES nodes(id)
);
```

---

## ⚙️ Configuration

Edit `config.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/knowledgemap?useSSL=false&serverTimezone=UTC
db.user=root
db.password=YOUR_PASSWORD
```

---

## ▶️ How to Compile & Run

### 1️⃣ Compile

```bash
javac -cp "lib/mysql-connector-j-9.5.0.jar" -d bin $(find src -name "*.java")
```

### 2️⃣ Run

```bash
java -cp "bin;lib/mysql-connector-j-9.5.0.jar" main.App
```

---

## 🧪 Sample Usage

```
=== Knowledge Map Builder ===
1. Create Node
2. Create Link
3. Traverse Map
4. Search Nodes
5. Find Path
6. Study Mode
7. Show Due Reviews
0. Exit
```

Example:

* Topic: Data Structures
* Nodes: Arrays → Prefix Sum
* Path: Data Structures → Arrays → Prefix Sum

---

## 📈 Learning Outcomes

* Practical understanding of **MVC architecture**
* JDBC and database integration
* Graph-based problem solving
* Defensive programming
* Real-world console application design
