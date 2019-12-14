# Fastercar
## Android Studio 3.5.3 & Latest gradle

### Instal firebase plug-in 
File -> Setting -> Plugin -> Search(Firebase) -> instal Firebase App Indexing

### How To Active Firebase
```
- Tools -> Firebase 
- Authentication -> Connect your app to firebase -> just click connect 
```

### Dependencies
```

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation 'androidx.appcompat:appcompat:1.0.1'
    implementation 'androidx.core:core-ktx:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'com.google.firebase:firebase-auth:19.1.0'
    implementation 'com.google.firebase:firebase-database:19.1.0'
    implementation 'com.android.support:design:29.0.0'
    implementation 'com.android.support:gridlayout-v7:28.0.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    implementation 'de.hdodenhof:circleimageview:3.0.1'
    implementation 'androidx.gridlayout:gridlayout:1.0.0'
}
```
 
