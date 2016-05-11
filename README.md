# **Dotted ProgressBar**
![]({{site.baseurl}}//dpb.gif)
Animated customizable progress bar, made of dots.

# **Usage**
For a working implementation of this widget see example in  _dottedprogressbarexample_ folder.

## Step 1
Add dependency to your project:

## **Gradle**
```
dependencies {
    compile 'com.antonzorin:dottedprogressbar:0.2.3'
}
```
        
## Maven
```
<dependency>
  <groupId>com.antonzorin</groupId>
  <artifactId>dottedprogressbar</artifactId>
  <version>0.2.3</version>
  <type>pom</type>
</dependency>
```


## Step 2
Set widget to layout:

```android
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
<com.antonzorin.dottedprogressbar.DottedProgressBar
        android:id="@+id/progress"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        app:dpArrowHourPaddingPercent="40"
        app:dpArrowHourSpeed="5"
        app:dpArrowMinutePaddingPercent="30"
        app:dpArrowMinuteSpeed="20"
        app:dpArrowWidthPercent="12"
        app:dpClockwiseDots="true"
        app:dpDotsCount="12"
        app:dpMaxDotsSizePercent="14"
        app:dpMinDotsSizePercent="5"
        app:dpRotationSpeed="5" />
</LinearLayout>
       ```

# **Styling**

- **dpBaseColor** - _base widget color_
- **dpArrowsColor** - _color of arrows_
- **dpCenterColor** - _color of center dot_
- **dpClockwiseDots** - _set dots spin clockwise_
- **dpCounterClockwiseArrows** - _set arrows spin counter clockwise_
- **dpHideArrows** - _make arrows invisible_
- **dpDotsCount** - _count of dots in progressBar_
- **dpMaxDotsSizePercent** - _largest dot in percents relative to widget size_
- **dpMinDotsSizePercent**  - _smallest dot in percents relative to widget size_
- **dpArrowWidthPercent** - _width of arrows relative to widget size_
- **dpArrowHourPaddingPercent** - _hour (short) arrow padding relative to widget size_
- **dpArrowMinutePaddingPercent** - _minutes (long) arrow padding relative to widget size_
- **dpArrowHourSpeed** - _hour arrow rotation speed_
- **dpArrowMinuteSpeed** - _minute arrow rotation speed_
- **dpRotationSpeed** - _dots rotation speed_

