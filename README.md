<img width="950" height="914" alt="Screenshot 2025-08-06 201238" src="https://github.com/user-attachments/assets/8e0b0fdd-a7e5-4371-b4bd-ab911eac8c12" />
<img width="949" height="913" alt="Screenshot 2025-08-06 201058" src="https://github.com/user-attachments/assets/bfed571a-4dc5-4514-a785-433dd5966c51" />
<img width="933" height="910" alt="Screenshot 2025-08-06 201037" src="https://github.com/user-attachments/assets/f429e930-7ec2-4044-92e2-d1d918b2d423" />
<img width="682" height="667" alt="Screenshot 2025-08-06 201018" src="https://github.com/user-attachments/assets/8279cb92-32fb-467e-b3f9-59a7f6a22997" />
<img width="925" height="914" alt="Screenshot 2025-08-06 200955" src="https://github.com/user-attachments/assets/9c541829-c5c3-4ecd-b78f-601030165464" />
<img width="946" height="911" alt="Screenshot 2025-08-06 200716" src="https://github.com/user-attachments/assets/a8043cc2-17f2-499f-bd82-0608e0cd6332" />
# 🪔 Ask_Lord_Krishna.ai

> **An AI-powered spiritual guide delivering personalized, Bhagavad Gita-inspired wisdom through advanced LLM orchestrations.**

[![React](https://img.shields.io/badge/Frontend-React-61DAFB?style=flat-square&logo=react)](https://reactjs.org/)
[![Spring Boot](https://img.shields.io/badge/Backend-Spring%20Boot-6DB33F?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![MongoDB](https://img.shields.io/badge/Database-MongoDB-47A248?style=flat-square&logo=mongodb)](https://www.mongodb.com/)
[![Gemini API](https://img.shields.io/badge/AI%20Engine-Google%20Gemini%20API-8E75B2?style=flat-square&logo=googlegemini)](https://ai.google.dev/)

---

## 📌 Problem Description & Assessment

In today's fast-paced digital era, individuals frequently face personal, ethical, and mental dilemmas. While ancient scriptures—specifically the **Bhagavad Gita**—offer timeless philosophical frameworks and actionable guidance, accessing and contextualizing these teachings to modern life situations can be challenging.

### The Problem
* **Accessibility & Language Barriers:** Ancient Sanskrit verses and traditional commentaries can be difficult to interpret and apply directly to contemporary problems.
* **Lack of Contextual Interaction:** Standard search engines or traditional static digital Gita apps provide rigid verse lookups rather than personalized, conversational guidance.

### The Solution
**Ask_Lord_Krishna.ai** bridges ancient wisdom with modern technology. By combining **Google's Gemini API** with prompt orchestration techniques, the application parses user dilemmas and responds dynamically in the divine, compassionate, and authoritative persona of Lord Krishna. Users receive well-formatted, actionable advice grounded in Gita philosophy to navigate their modern challenges.

---

## 🏗 System Architecture

The application adopts a decoupled, multi-tier architecture ensuring scalability, separation of concerns, and clean API integration:

```
+------------------+         REST API         +------------------------+
|                  |  --------------------->  |                        |
|  React Frontend  |                          |   Spring Boot Backend  |
|  (User UI / Chat)|  <---------------------  |   (Core Logic Layer)   |
+------------------+                          +-----------+------------+
                                                           |
                                       +-------------------+-------------------+
                                       |                                       |
                                       v                                       v
                             +------------------+                   +--------------------+
                             |  MongoDB Database|                   | Google Gemini API  |
                             | (Logs & Context) |                   |  (Persona & LLM)   |
                             +------------------+                   +--------------------+
```

### Key Architectural Flow
1. **Client Interaction:** User submits a prompt or dilemma via the React frontend.
2. **Backend Processing:** Spring Boot validates the input, attaches persona context instructions, and manages user interaction histories.
3. **AI Inference:** Spring Boot communicates securely via REST APIs with Google's Gemini LLM engine.
4. **Data Persistence:** User prompts, AI responses, and contextual session histories are stored in **MongoDB** for seamless retrieval.
5. **Formatted Output:** The response is returned to the React client and rendered with rich typography, bullet points, and verse references.

---

## 🛠 Tech Stack

| Layer | Technology Used | Description |
| :--- | :--- | :--- |
| **Frontend** | React.js, HTML5, CSS3 | Dynamic interactive UI built for conversational flow and responsive layout. |
| **Backend** | Java, Spring Boot, RESTful APIs | Robust micro-framework managing API routes, prompt execution, and payload handling. |
| **Database** | MongoDB | NoSQL database storing user chat sessions and analytical logs. |
| **AI Integration** | Google Gemini API | Advanced LLM providing contextual dialog synthesis tuned with custom persona framing. |
| **Tools & Build** | Git, GitHub, Maven, npm | Version control, dependency management, and build orchestration. |

---

## 🚀 Getting Started (Run Locally)

Follow these instructions to clone and run **Ask_Lord_Krishna.ai** on your local machine.

### Prerequisites
Make sure you have the following installed on your system:
* **Java Development Kit (JDK 17 or higher)**
* **Node.js (v18.x or higher)** & **npm**
* **MongoDB** running locally or a **MongoDB Atlas URI**
* **Google Gemini API Key** (obtain from [Google AI Studio](https://aistudio.google.com/))

---

### Step 1: Clone the Repository

```bash
git clone https://github.com/Vinland17/Ask_Lord_Krishna.ai.git
cd Ask_Lord_Krishna.ai
```

### Step 2: Configure Backend (Spring Boot)

Navigate to the backend directory:

```bash
cd backend
```

Open `src/main/resources/application.properties` (or `application.yml`) and update your configuration settings:

```properties
server.port=8080

# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/krishna_ai_db

# Gemini API Key
gemini.api.key=YOUR_GEMINI_API_KEY_HERE
```

Build and run the Spring Boot application:

```bash
# Using Maven wrapper
./mvnw spring-boot:run
```

The backend server will start on **http://localhost:8080**.

### Step 3: Configure Frontend (React)

Open a new terminal tab and navigate to the frontend directory:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the React development server:

```bash
npm start
```

The React app will open automatically in your browser at **http://localhost:3000**.

---

## 📜 Example Interaction

**User:**
> "I feel overwhelmed by failure in my career. How do I move forward?"

**Lord Krishna:**
> "O Arjuna of modern times, hear this truth: You have a right to perform your prescribed duty, but you are not entitled to the fruits of your actions. Never consider yourself the cause of the results of your activities, nor be attached to inaction. (Bhagavad Gita 2.47)
>
> Focus your energy on your effort, learning, and resilience, rather than anxiety over outcome..."

---

## 🤝 Contributing

Contributions, feedback, and feature suggestions are welcome!

1. Fork the project repository.
2. Create your Feature Branch:
   ```bash
   git checkout -b feature/NewWisdomFeature
   ```
3. Commit your changes:
   ```bash
   git commit -m 'Add new prompt tuning logic'
   ```
4. Push to the branch:
   ```bash
   git push origin feature/NewWisdomFeature
   ```
5. Open a Pull Request.

---

## 📄 License

This project is open-source and available under the **MIT License**.
