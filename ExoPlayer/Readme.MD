# Exoplayer Library

Exoplayer is an media player for android.It provdes alternate to Android's MediaPlayer API for playing both Audio and Video both locally and over the internet.
It includes features such as DASH and SmoothStreaming adaptive playbacks. 

# Configuration
1.Add Exoplayer as dependancy to your project :

The first step is to make sure you have the jcenter repository included in the `build.gradle` file in the root of your project.
```
repositories {
    jcenter()
}
```
Next add a gradle compile dependency to the `build.gradle` file of your app module.
```
compile 'com.google.android.exoplayer:exoplayer:r2.X.X'
```
where 2.X.X is your prefered version

2.Creating the player :

You can create an `ExoPlayer` instance using `ExoPlayerFactory`.For the vast majority of use cases one of the `ExoPlayerFactory.newSimpleInstance` methods should be used. These methods return `SimpleExoPlayer`, which extends `ExoPlayer` to add additional high level player functionality.
```
// 1. Create a default TrackSelector
Handler mainHandler = new Handler();
BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
TrackSelection.Factory videoTrackSelectionFactory =
    new AdaptiveTrackSelection.Factory(bandwidthMeter);
TrackSelector trackSelector =
    new DefaultTrackSelector(videoTrackSelectionFactory);

// 2. Create the player
SimpleExoPlayer player =
    ExoPlayerFactory.newSimpleInstance(context, trackSelector);
```

3.Attacing player to view :

The ExoPlayer library provides a `SimpleExoPlayerView`, which encapsulates a `PlaybackControlView` and a `Surface` onto which video is rendered. A `SimpleExoPlayerView` can be included in your application’s layout xml.
```
simpleExoPlayerView.setPlayer(player);
```

4.Preparing the view :

```
// Measures bandwidth during playback. Can be null if not required.
DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
// Produces DataSource instances through which media data is loaded.
DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
    Util.getUserAgent(context, "yourApplicationName"), bandwidthMeter);
// Produces Extractor instances for parsing the media data.
ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
// This is the MediaSource representing the media to be played.
MediaSource videoSource = new ExtractorMediaSource(mp4VideoUri,
    dataSourceFactory, extractorsFactory, null, null);
// Prepare the player with the source.
player.prepare(videoSource);
```
Once the player has been prepared, playback can be controlled by calling methods on the player. For example `setPlayWhenReady` can be used to start and pause playback.

5.Releasing the player :

It’s important to release the player when it’s no longer needed, so as to free up limited resources such as video decoders for use by other applications. This can be done by calling `ExoPlayer.release`.
