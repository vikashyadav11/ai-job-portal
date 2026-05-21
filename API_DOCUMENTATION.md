# API Documentation

Base URL:

```http
http://localhost:9090
```

---

# 🔐 Authentication APIs

## Register User

```http
POST /auth/register
```

### Request Body

```json
{
  "name": "Vikash",
  "email": "vikash@test.com",
  "password": "1234",
  "role": "CANDIDATE"
}
```

### Response

```json
"User registered successfully"
```

---

## Login User

```http
POST /auth/login
```

### Request Body

```json
{
  "email": "vikash@test.com",
  "password": "1234"
}
```

### Response

```json
"jwt-token"
```

---

# 💼 Job APIs

## Create Job

```http
POST /jobs/create
```

### Authorization

Bearer Token Required

### Request Body

```json
{
  "title": "Java Developer",
  "description": "Spring Boot Developer",
  "company": "TCS",
  "location": "Pune",
  "requiredSkills": "Java, Spring Boot, SQL"
}
```

### Response

```json
"Job created successfully"
```

---

## Get All Jobs

```http
GET /jobs
```

### Authorization

Bearer Token Required

### Response

```json
[
  {
    "id": 1,
    "title": "Java Developer",
    "company": "TCS",
    "location": "Pune"
  }
]
```

---

# 📄 Application APIs

## Apply To Job

```http
POST /applications/apply/{jobId}
```

### Authorization

Bearer Token Required

### Response

```json
"Application submitted successfully"
```

---

## Apply With Resume Upload

```http
POST /applications/apply/{jobId}/resume
```

### Authorization

Bearer Token Required

### Form Data

```text
file : PDF Resume
```

### Response

```json
{
  "message": "Application submitted successfully",
  "resumeData": {
    "name": "Vikash Yadav",
    "email": "vikash@test.com",
    "skills": [
      "Java",
      "Spring Boot",
      "SQL"
    ]
  },
  "matchResponse": {
    "matchScore": 80,
    "matchedSkills": [
      "Java",
      "SQL"
    ],
    "missingSkills": [
      "React"
    ]
  }
}
```

---

# 🏢 Recruiter APIs

## View Applicants

```http
GET /recruiter/applications
```

### Authorization

Bearer Token Required

### Response

```json
[
  {
    "id": 1,
    "applicantEmail": "user@test.com",
    "status": "APPLIED",
    "matchScore": 80
  }
]
```

---

## Shortlist Candidate

```http
PUT /recruiter/shortlist/{applicationId}
```

### Authorization

Bearer Token Required

### Response

```json
"Candidate shortlisted"
```

---

## Reject Candidate

```http
PUT /recruiter/reject/{applicationId}
```

### Authorization

Bearer Token Required

### Response

```json
"Candidate rejected"
```

---

# 🔒 Authorization

Protected APIs require JWT token:

```http
Authorization: Bearer your-jwt-token
```

---

# 🤖 AI Features

- Resume Parsing using OpenAI API
- Skill Extraction
- Resume Scoring
- Job Skill Matching
- Match Percentage Generation

---

# 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- OpenAI API
- Hibernate
- Apache PDFBox