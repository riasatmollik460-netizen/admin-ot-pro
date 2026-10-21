# OT Tracker Pro Admin — Android starter

এই ZIP-এ Android Studio/Gradle project ও GitHub Actions workflow আছে। এটি dashboard/navigation UI demo; production backend নয়।

## এখনকার স্ক্রিন
- Admin sign-in placeholder
- Dashboard metric placeholders
- User management, OT records, subscription, payments, admin access, audit log navigation
- GitHub Actions দিয়ে বিনামূল্যে debug APK build

## গুরুত্বপূর্ণ নিরাপত্তা সীমা
Login screen-এ “Open demo dashboard” শুধু UI test-এর জন্য। এটি প্রকৃত authentication নয় এবং ডেটা সুরক্ষা দেয় না। এই APK দিয়ে real users/payment manage করবেন না। Production-এর আগে অবশ্যই:
1. Firebase Authentication বা নিরাপদ backend login যুক্ত করুন।
2. Owner/Admin role server-side যাচাই করুন; Android app থেকে role পরিবর্তন করার সুযোগ দেবেন না।
3. OT Tracker Pro main app এবং Admin app-কে একই authenticated backend/database-এর সঙ্গে যুক্ত করুন।
4. Subscription entitlement ও payment confirmation server-side যাচাই করুন; secret/API private keys APK-তে রাখবেন না।
5. Audit log, database security rules, backups ও privacy policy সেট করুন।

## বিনামূল্যে APK build (GitHub Actions)
1. GitHub-এ নতুন repository তৈরি করুন, যেমন `ot-tracker-pro-admin`।
2. এই ZIP extract করে এর ভেতরের সব file/folder repository-র root-এ upload করুন। `.github` folder-সহ upload করতে হবে।
3. Commit/push করুন।
4. GitHub repository → **Actions** → **Build OT Tracker Pro Admin APK** → **Run workflow**।
5. Build সফল হলে run খুলে নিচের **Artifacts** থেকে `OT-Tracker-Pro-Admin-APK` ডাউনলোড করুন।
6. ZIP খুলে APK Android ফোনে পাঠিয়ে install করুন। ফোনে “Install unknown apps” অনুমতি চাইতে পারে।

## Local build
JDK 17 + Android SDK install করে project root-এ:
`gradle assembleDebug`
APK: `app/build/outputs/apk/debug/app-debug.apk`

## Play Store
Debug APK testing-এর জন্য। Play Store-এর জন্য release signing, privacy policy, app listing, target API compliance এবং Play Console testing/review লাগবে। এই starter-কে production admin app বলার আগে backend integration ও security testing সম্পন্ন করুন.
