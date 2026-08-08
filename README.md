# 📐 Math Blast! - Accessible Android Educational Application

Built using **Kotlin**, **Jetpack Compose (Material Design 3)**, and **Modern Android Architecture Principles** as part of the JCU CP3406 Core Mobile Development curriculum.

---

## 📱 Core Application Features
* **Landing Page (Main Menu)**: Serves as the central hub of the application layout graph, featuring dynamic outbound network connectivity utilizing the **Retrofit API Framework**.
* **Math Quiz Gameplay Screen**: A dynamic, interactive 5-question arithmetic testing engine configured with scale-independent layouts to protect readability.
* **App Preferences (Settings Screen)**: Persistently saves individual student learning configurations, including a **High-Contrast Monochrome Theme Toggle** and a **Quiz Difficulty Selector** (Easy/Medium/Hard).
* **Progress Analytics (Statistics Screen)**: Reads live database streams to compute total attempts and overall student accuracy metrics.

---

## 🛠️ Technical Architectural Scope
This project utilizes a robust, clean architecture separation-of-concerns layout strategy to ensure optimal maintainability, scalability, and accessibility:
1. **Data Layer (Room Database)**: Manages local data persistence via high-performance SQLite abstraction. Built cleanly using native **Kotlin Symbol Processing (KSP)** to run type-safe structural database table queries.
2. **Network Layer (Retrofit Client)**: Manages background network serialization using Gson converters to parse JSON payloads without blocking the main rendering threat.
3. **UI & Navigation Graph Layer**: Centralizes multi-viewport routing using a decoupled state-holder paradigm, ensuring components cleanly pass required Data Access Objects (DAOs) down the composition tree.
4. **Local Unit Testing**: Employs **JUnit 4** to execute non-GUI automated behavioral assertions verifying score evaluation logic bounds.

---

## ♿ Ethical Design & Accessibility Integration (ACS Compliance)
In direct alignment with the **Australian Computer Society (ACS) Code of Ethics**, this application was built from the ground up to prevent digital exclusion for low-vision and motor-impaired learners:
* **The Primacy of the Public Interest**: Abandoned aggressive, countdown gameplay timers that induce unnecessary cognitive stress for neurodivergent students.
* **Enhancement of Quality of Life**: Implemented absolute `dp` and `sp` scaling across all typography nodes, allowing the app layout to respect device system font scaling smoothly.
* **Universal Tap Targets**: Enforced a strict minimum touch target bound of **52dp** (exceeding the standard 48dp platform baseline) across all interactive choices to safely accommodate motor-impaired children.
* **Screen Reader Semantic Content**: Configured layout containers with custom `contentDescription` attributes and explicit `heading()` properties, enabling **Google TalkBack** screen readers to read the interfaces logically.

---

## 🚀 Installation & Build Requirements
* **Development Environment**: Android Studio (Ladybug 2024.2.1 or newer)
* **Minimum Android SDK**: API 24 (Android 7.0 Nougat)
* **Target Android SDK**: API 37 (Android 15)
* **Data Storage Engine**: Jetpack Room Database v2.8.4
* **Compiler Processing Tool**: Modern KSP (Kotlin Symbol Processing)
* **Networking Client Engine**: Retrofit v2.11.0 with OkHttp v4.12.0 core layer
