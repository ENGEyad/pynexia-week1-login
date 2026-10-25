# Simple Login

A native Android login exercise with required-field feedback, fixed demo credential checks, explicit Intent navigation and sign out.

Pynexia Android training / Week 1. Java 17 + XML.

[Hosted showcase](https://engeyad.github.io/pynexia-week1-login/) · [Report PDF](https://engeyad.github.io/pynexia-week1-login/downloads/Simple-Login-App-Report.pdf) · [APK](https://engeyad.github.io/pynexia-week1-login/downloads/Simple-Login-App.apk) · [Submission ZIP](https://engeyad.github.io/pynexia-week1-login/downloads/Simple-Login-App.zip)

## Run

Open this repository root in Android Studio. Install SDK 35 / Build Tools 35.0.0; use JDK 17 or 21. Sync Gradle and run `login` on Android 8.0 / API 26 or later. Initial sync needs internet. AGP 8.9.1; Gradle Wrapper 8.11.1.

```powershell
.\gradlew.bat :login:assembleDebug :login:testDebugUnitTest :login:lintDebug
.\gradlew.bat :login:connectedDebugAndroidTest
```

The second command needs an emulator/device. On macOS/Linux, use `chmod +x gradlew` then `./gradlew`. APK output: `login/build/outputs/apk/debug/login-debug.apk`.

## Implementation

### Form and feedback

EditText fields accept username/email and password. Blank fields show errors; incorrect credentials display feedback.

### Credential validation

Public demo username: learner. Alternative email: learner@example.com. Password: Learn@123. Username/email ignores case and surrounding spaces; password matches exactly.

### Navigation and sign out

An explicit Intent opens HomeActivity. Sign out clears the navigation stack and returns to empty input fields.

## Review walkthrough

1. Submit empty fields, then incorrect credentials, to check feedback.
2. Sign in using learner and Learn@123.
3. Sign out; confirm the fields are empty and Back does not reopen the authenticated home.
4. Repeat using learner@example.com and the same password.

## Verification

2 JVM tests and 1 device tests passed, with zero failures, errors or skips. Lint: 0 errors and 5 warnings, without suppression. Two JVM tests cover credential rules. One device test exercises empty/incorrect fields, login by username and email, navigation and sign out.

Tested 25 October 2026 on a Pixel 8 emulator, Android 17 / API 37 (37.1 image), x86_64. No physical-device or API 26 run. See `verification/` for executed reports; local filesystem prefixes are redacted. Debug APK signature verified.

## Scope

Fixed, deliberately public demo credentials. No production authentication or persistent session. Accounts created in the separate Sign-Up App do not work here. Autofill is disabled.

APKs are debug-signed builds for assessment. The hosted page is a project showcase with actual screenshots and downloads, not an in-browser Android runtime. Publication does not submit the assignment to Pynexia.

## شرح عربي

يعرض التمرين نموذج تسجيل دخول يتحقق من الحقول الفارغة ومن بيانات حساب تجريبي ثابت. عند نجاح التحقق ينتقل إلى الصفحة الرئيسية، وعند تسجيل الخروج يمسح مسار التنقل ويعيد النموذج فارغًا. لا توجد مصادقة على خادم أو جلسة دخول دائمة.

للتجربة: استخدم اسم المستخدم وكلمة المرور المعروضين أدناه. اختبر أيضًا الحقول الفارغة والبيانات الخاطئة وتسجيل الخروج. الحسابات التي تنشئها في تمرين التسجيل مستقلة ولا تعمل في هذا التمرين.

## Submission

Copy `SUBMISSION-NOTES.txt` into the notes field. Repository URL: https://github.com/ENGEyad/pynexia-week1-login

Hosted URL: https://engeyad.github.io/pynexia-week1-login/

Attach the ZIP or PDF. The APK is inside the ZIP, since the supplied form does not accept direct APK attachments.

Reference: https://developer.android.com/guide/components/intents-filters
