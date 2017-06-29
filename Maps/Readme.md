# Google Maps API to show marker
This is a sample code to show a marker for a particular place using the Google Maps API

### Gradle Configuration
In your app's `build.gradle` file add the google play services dependancy
```
apply plugin: 'com.android.application'
...

dependencies {
    compile 'com.google.android.gms:play-services:11.0.1'
}
```
### Layout Configuration
In your app's layout file add a `<fragment>` element.This element defines a `SupportMapFragment` to act as a container for the map and to provide access to the `GoogleMap object`.
```
<fragment xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/map"
    android:name="com.google.android.gms.maps.SupportMapFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.mapwithmarker.MapsMarkerActivity" />
```
### MainActivity Configuration
1. In your activity's `onCreate()` method obtain a handle to map fragment by calling `FragmentManager.findFragmentById()`. Then use `getMapAsync()` to register for the map callback:
```
SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
mapFragment.getMapAsync(this);
```
2. Implement `OnMapReadyCallback` interface and Override the `onMapRady()` method to setup the map when `GoogleMap` object is available.
```
@Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in India,
        // and move the map's camera to the same location.
        LatLng india = new LatLng(20.593684, 78.962880);
        googleMap.addMarker(new MarkerOptions().position(india)
                .title("Marker in India"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(india));
    }
```
