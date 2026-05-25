# 🚴 Parisara Cycle — Android App
**Project #38 | Android App Development using GenAI**

A community-driven, GPS-enabled cycling app for safe, eco-friendly commuting in tier-2 and tier-3 towns.

---

## 📱 Features
| Feature | Description |
|---|---|
| **Safe Route Map** | Google Maps with bicycle-friendly layer, avoiding highways |
| **Danger Zone Reporting** | Report & view Potholes, Blockages, Dangerous Intersections |
| **Pit Stop Finder** | Nearby cycle repair shops, water points, rest areas |
| **Buddy System** | Real-time Firebase location sharing with nearby cyclists |
| **Eco Stats** | CO₂ savings tracker (1 km = 120g CO₂), monthly totals |
| **Ride Logger** | Log rides and track cumulative environmental impact |

---

## 🛠️ Tech Stack
- **Language**: Kotlin
- **Architecture**: MVVM (ViewModel + LiveData + Repository)
- **Database**: Room (local) + Firebase Realtime DB (buddy system)
- **Maps**: Google Maps SDK + Fused Location Provider
- **DI**: Manual (no Hilt/Dagger for simplicity)
- **UI**: Material Design 3, Navigation Component, ViewBinding

---

## ⚙️ Setup Instructions

### Step 1: Firebase Setup
1. Go to [Firebase Console](https://console.firebase.google.com)
2. Create a new project: `ParisaraCycle`
3. Add Android app → Package: `com.parisara.cycle`
4. Download `google-services.json`
5. **Replace** `app/google-services.json` with the downloaded file
6. Enable **Realtime Database** (start in test mode)
7. Enable **Anonymous Authentication**

### Step 2: Google Maps API Key
1. Go to [Google Cloud Console](https://console.cloud.google.com)
2. Enable: **Maps SDK for Android**, **Places API**, **Directions API**
3. Create an API key
4. Open `app/build.gradle`
5. Replace `YOUR_GOOGLE_MAPS_API_KEY_HERE` with your key

### Step 3: Open in Android Studio
1. Open Android Studio (Hedgehog or newer)
2. File → Open → Select the `ParisaraCycle` folder
3. Wait for Gradle sync to complete
4. Run on emulator or physical device (API 24+)

---

## 📁 Project Structure
```
app/src/main/java/com/parisara/cycle/
├── model/          → Data classes (Route, DangerZone, PitStop, EcoStat, BuddyUser)
├── data/
│   ├── local/      → Room DB, DAOs
│   └── repository/ → AppRepository (single source of truth)
├── service/        → LocationTrackingService (foreground)
└── ui/
    ├── home/       → HomeFragment
    ├── map/        → MapFragment (Google Maps)
    ├── buddy/      → BuddyFragment + Adapter
    ├── stats/      → StatsFragment
    ├── report/     → ReportFragment
    ├── MainActivity.kt
    ├── MainViewModel.kt
    └── SplashActivity.kt
```

---

## 🌱 CO₂ Calculation
- 1 km cycling = 120g CO₂ saved (vs car)
- 1 km cycling = ~40 kcal burned
- 21,000g CO₂ = 1 tree equivalent

---

## 📝 Notes
- The app seeds 3 sample pit stops in Bengaluru on first launch
- Buddy system requires internet; it uses Firebase Realtime DB
- Location updates every 5 seconds when app is in foreground
- All danger zone reports are stored locally + synced to Firebase
