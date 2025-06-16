# Exam-Portal-App 🎓📱

An Android application designed for colleges and universities to conduct online exams and daily quizzes. Built using **Kotlin**, **XML**, and **Firebase**, this app ensures smooth exam management and real-time data syncing.

---

## 📚 Features

- User authentication via **Firebase Authentication** (email and password)
- Student registration and login functionality
- Exam and quiz setup by **admin**
- Real-time synchronization of **subjects and questions** using **Cloud Firestore**
- Automatic result saving with **timestamp** and **date**
- Intuitive and responsive UI using **Android Studio** and **XML** layouts

---

## 🧰 Tech Stack

- **Language**: Kotlin
- **UI**: XML
- **IDE**: Android Studio
- **Backend Services**:
  - Firebase Authentication
  - Cloud Firestore

---

## 📝 How It Works

1. **Student Registration**: Students sign up using email and password.
2. **Login**: Users log in with their registered credentials.
3. **Exam Access**: Students can access the list of available quizzes or exams.
4. **Real-time Sync**: Questions and subjects are fetched in real-time from Firestore.
5. **Submission & Result**: After submission, the result is stored in Firestore with timestamp and date.

---

## 🔐 Authentication

- Implemented using **Firebase Auth**
- Secure login/registration flow
- Password reset and validation included

---

## ☁️ Cloud Firestore

- Stores student details, exams, questions, and results
- Real-time updates ensure all users get latest data instantly

---

## 📦 Installation

1. Clone the repository:
```bash
git clone https://github.com/YourUsername/Exam-Portal-App.git
```
2. Open the project in **Android Studio**
3. Connect Firebase to the project via Tools > Firebase
4. Set up Firestore rules and Authentication in Firebase Console
5. Build and run on emulator or Android device

---

## 🔧 Future Enhancements

- Role-based access (Student vs Admin Dashboard)
- Timer and auto-submit functionality for exams
- Analytics and performance charts
- Email notifications for results

---
