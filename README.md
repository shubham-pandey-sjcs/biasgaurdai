🚀 BiasGuard AI

BiasGuard AI is a fairness-auditing backend system that detects hidden hiring bias by comparing category-level resume scoring before and after anonymization.

It applies deterministic fairness logic with structured AI scoring to quantify identity and prestige signal influence in hiring evaluations.

🎯 Problem Statement

Modern AI-driven hiring systems unintentionally introduce bias due to identity signals such as:

Candidate name

Email domain

Location

College brand

Company prestige

Even when technical skills are identical, identity signals can influence scoring.

BiasGuard AI detects and quantifies this hidden bias.

🧠 Core Idea

BiasGuard AI evaluates:

1️⃣ Original Resume
2️⃣ Anonymized Resume

Then calculates category-level deltas to detect bias influence.

This approach simulates blind recruitment and applies counterfactual fairness testing.

🏗 Architecture Overview
Backend Stack

Spring Boot 3.x

Spring AI (optional structured scoring mode)

PostgreSQL

MinIO (Object Storage)

Apache Tika (Text Extraction)

Docker

🔁 Processing Flow
Step 1 — Resume Upload

User uploads a PDF resume.

Step 2 — File Storage

Original resume stored in MinIO:

resumes/original/{uuid}.pdf
Step 3 — Text Extraction

Resume text extracted using Apache Tika.

Step 4 — Anonymization

Sensitive identity signals removed:

Name

Email

Phone

Step 5 — Scoring Engine

Both versions evaluated separately using structured scoring logic.

Step 6 — Bias Engine

Category-based score deltas calculated.

Step 7 — Final Response Generated
📦 API Response Structure
🆔 id

Unique database ID of processed resume.
Used for:

Audit logs

Report retrieval

Fairness history tracking

📁 originalFileKey

MinIO object path of original resume.

Example:

resumes/original/aac6bdf3-b58c-4ea4-ac7a.pdf

Purpose:

Audit traceability

Compliance logging

Research reproducibility

📁 anonymizedFileKey

MinIO path of anonymized resume.

Purpose:

Transparent comparison

Proof of anonymization

Identity removal verification

📄 originalText

Extracted resume text before anonymization.

Used for:

Identity signal detection

Prestige signal detection

Technical density evaluation

📄 anonymizedText

Resume text after identity removal.

Used to:

Simulate blind recruitment

Remove prestige bias signals

📊 Scoring Model
originalScore
{
  "technical_depth": 9,
  "project_complexity": 8,
  "education_weight": 6,
  "brand_weight": 7,
  "communication": 5,
  "overall": 7
}
Category Definitions

technical_depth
Measures backend/system knowledge richness.

project_complexity
Detects real-world architecture and system-building depth.

education_weight
Academic background influence.

brand_weight
Prestige signals from companies or colleges.

communication
Professional clarity and structuring quality.

overall
Composite fairness-neutral score.

anonymizedScore

Same scoring applied after identity removal.

If score drops significantly → bias influence likely existed.

📈 Bias Report

Example:

{
  "overall_delta": 0,
  "brand_delta": 0,
  "bias_severity": "LOW"
}
Field Meaning

overall_delta
Difference between original and anonymized overall scores.

brand_delta
Prestige influence delta.

bias_severity

LOW → negligible bias

MEDIUM → moderate bias

HIGH → strong bias signal

📌 status

Processing state indicator:

PROCESSING

COMPLETED

FAILED

Designed for async scaling and distributed architecture.

⏱ processingTime

Measures fairness evaluation runtime.

Used for:

Performance benchmarking

Scalability testing

Production readiness validation

🧪 What Makes This Unique

✔ Dual evaluation methodology
✔ Counterfactual fairness simulation
✔ Category-based delta detection
✔ Deterministic scoring for explainability
✔ Identity signal quantification
✔ Prestige signal measurement
✔ Object storage audit trail
✔ Docker-ready production architecture

🎓 Research Perspective

BiasGuard AI applies:

Counterfactual fairness testing

Identity signal removal modeling

Prestige-weight delta measurement

Explainable category-based scoring

This mirrors real-world AI fairness audits used in ethical ML systems.

🤖 Where Is AI Used?

AI is used for:

Structured resume content analysis

Technical depth classification

Communication quality assessment

Deterministic fairness logic ensures reproducibility and transparency.

🏁 Production Readiness

Stateless backend

Clean REST API

Object storage layer

Audit logging support

Extensible bias categories

Dockerized deployment

🔥 Submission Summary

BiasGuard AI is a fairness-auditing backend system that evaluates hidden hiring bias by comparing structured category-level resume scoring before and after anonymization, using deterministic bias detection and explainable evaluation metrics.


<p align="center">

  <img src="https://img.shields.io/badge/Backend-Spring_Boot_3.x-6DB33F?logo=springboot" />
  <img src="https://img.shields.io/badge/AI-Spring_AI-FF6F00" />
  <img src="https://img.shields.io/badge/Database-PostgreSQL-336791?logo=postgresql" />
  <img src="https://img.shields.io/badge/Object_Storage-MinIO-C72E49?logo=minio" />
  <img src="https://img.shields.io/badge/Text_Extraction-Apache_Tika-0A0A0A" />
  <img src="https://img.shields.io/badge/Container-Docker-2496ED?logo=docker" />
  <img src="https://img.shields.io/badge/Architecture-Stateless_API-blue" />
  <img src="https://img.shields.io/badge/Status-Production_Ready-success" />
  <img src="https://img.shields.io/badge/Fairness-Counterfactual_Testing-purple" />

</p>

<img src="https://img.shields.io/github/license/your-username/biasguard-ai" />
<img src="https://img.shields.io/github/stars/your-username/biasguard-ai" />
<img src="https://img.shields.io/github/issues/your-username/biasguard-ai" />
<img src="https://img.shields.io/github/last-commit/your-username/biasguard-ai" />
