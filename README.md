# AI-Powered Smart Job Portal Backend

An AI-powered backend system for a smart job portal built using Spring Boot, Spring Security, JWT Authentication, PostgreSQL, and OpenAI APIs.

The project focuses on backend architecture, AI integration, recruiter workflow management, resume parsing, and intelligent candidate-job matching.

---

# 🚀 Features

## 🔐 Authentication & Security
- JWT-based authentication
- Role-based authorization
- Candidate and Recruiter roles
- Protected REST APIs using Spring Security

---

## 👤 Candidate Features
- Register/Login
- View available jobs
- Apply to jobs
- Upload resume in PDF format
- Prevent duplicate applications

---

## 🏢 Recruiter Features
- Create job postings
- View applicants for jobs
- View AI-generated match score
- Shortlist candidates
- Reject candidates

---

# 🤖 AI Features

## 📄 Resume Parsing
- Extracts text from uploaded PDF resumes
- Uses OpenAI APIs to parse:
    - Name
    - Email
    - Skills
    - Experience

---

## 🎯 Skill Matching Engine

Compares:
- Candidate resume skills
- Required job skills

Returns:
- Match percentage
- Matched skills
- Missing skills

---

## 🧠 AI Resume Scoring

Analyzes resume quality using OpenAI APIs and returns:
- Resume score
- Strengths
- Weaknesses
- Improvement suggestions

---

# 🛠️ Tech Stack

## Backend
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate

## Database
- PostgreSQL

## Authentication
- JWT (JSON Web Token)

## AI Integration
- OpenAI API

## Utilities
- Apache PDFBox
- Lombok

---

# 🗄️ Database Modules
- Users
- Jobs
- Applications

---

# 📌 Core APIs

## Authentication APIs
- Register user
- Login user

## Job APIs
- Create job
- Get all jobs

## Application APIs
- Apply to job
- Upload resume
- Resume parsing
- Match score generation

## Recruiter APIs
- View applicants
- Shortlist candidate
- Reject candidate

---

# 🧠 Project Workflow

```text
Candidate uploads resume
          ↓
PDF text extraction
          ↓
OpenAI resume parsing
          ↓
Skill extraction
          ↓
Job skill comparison
          ↓
Match score generation
          ↓
Recruiter views ranked applicants

```
---
