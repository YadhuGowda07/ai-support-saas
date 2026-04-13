# 🚀 AI-Powered Intelligent Support Platform (SaaS)

## 📌 Overview

This project is a **multi-tenant AI-powered support platform** that enables organizations to upload their knowledge base documents and interact with them using an intelligent chatbot.

It leverages **Retrieval-Augmented Generation (RAG)** to provide accurate, context-aware answers based on company-specific data.

---

## 🎯 Key Features

### 🔐 Authentication & Authorization

* User registration and login
* JWT-based authentication
* Role-based access control (ADMIN, USER, SUPPORT)

---

### 🏢 Multi-Tenant Architecture

* Supports multiple organizations (tenants)
* Data isolation per tenant
* Users belong to specific tenants

---

### 📄 Document Processing Pipeline

* Upload PDF documents
* Extract text from PDFs
* Split text into manageable chunks
* Store chunks in the database

---

### 🤖 AI Chat (RAG Pipeline)

* Convert text chunks into embeddings
* Store embeddings using PostgreSQL (pgvector)
* Perform similarity search
* Retrieve relevant document context
* Generate AI responses using LLM

---

## ⚙️ Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Spring AI

### Database

* PostgreSQL
* pgvector (vector database support)

### AI / LLM

* OpenAI (via Spring AI)

### Tools

* Maven
* Git & GitHub
* IntelliJ IDEA

---

## 🧠 Architecture

```
User Query
   ↓
Embedding Generation
   ↓
Vector Search (pgvector)
   ↓
Retrieve Relevant Chunks
   ↓
Prompt Construction
   ↓
LLM (OpenAI)
   ↓
AI Response
```

---

## 📂 Project Structure

```
com.example.aisaas
│
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── security
├── rag
├── util
├── exception
```

---

## 🔌 API Endpoints

### 🔐 Authentication

* `POST /api/auth/register`
* `POST /api/auth/login`
* `GET /api/auth/me`

---

### 👤 Users

* `GET /api/users`

---

### 📄 Documents

* `POST /api/documents/upload`

---

### 🤖 AI Chat

* `POST /api/ai/chat`

---

## 🧪 How It Works

1. User uploads a document (PDF)
2. System extracts text and splits into chunks
3. Each chunk is converted into embeddings
4. Embeddings are stored in PostgreSQL (pgvector)
5. User asks a question
6. System retrieves relevant chunks using similarity search
7. LLM generates answer using retrieved context

---

## 🚧 Current Status

✅ Multi-tenant base structure
✅ JWT authentication & role-based access
✅ Document upload and processing
✅ RAG pipeline with embeddings and vector search
✅ AI chat API

🔄 In Progress:

* Support ticket system
* Dockerization
* AWS deployment

---

## 🚀 Future Enhancements

* Tenant-level data isolation (advanced)
* Redis caching for performance
* Async processing for embeddings
* Frontend (React)
* Deployment using Docker & AWS

---

## 💡 Use Cases

* Customer support automation
* Internal knowledge base assistant
* Enterprise AI chatbot
* Helpdesk automation

---

## 👨‍💻 Author

**Yadhu Gowda**

GitHub: https://github.com/YadhuGowda07

---

## ⭐ If you like this project

Give it a star ⭐ and feel free to contribute!
