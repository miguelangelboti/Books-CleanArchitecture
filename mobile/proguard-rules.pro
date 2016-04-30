# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/miguelangel/Documents/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

################################## Gson ###################################
# Gson uses generic type information stored in a class file when working
# with fields. Proguard removes such information by default, so configure
# it to keep all of it.
-keepattributes Signature

# Gson specific classes.
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson.
-keep class com.miguelangelboti.books.domain.entities.** { *; }
################################## Gson ###################################
