package com.codemagic.powerhour;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.spotify.sdk.android.Spotify;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.authentication.SpotifyAuthentication;
import com.spotify.sdk.android.playback.ConnectionStateCallback;
import com.spotify.sdk.android.playback.Player;
import com.spotify.sdk.android.playback.PlayerNotificationCallback;
import com.spotify.sdk.android.playback.PlayerState;


public class Dashboard extends Activity implements PlayerNotificationCallback, ConnectionStateCallback {

    Preferences myPrefs;
    Player mPlayer;

    private int numSongs = 0;
    private long lenghtOfSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        myPrefs = new Preferences(this);

        Log.d("MainActivity", "OnCreate()");

        authSpotifyUser();
    }

    private void authSpotifyUser() {
        Log.d("MainActivity", "authSpotifyUser called");
        SpotifyAuthentication.openAuthWindow(SpotifyUser.CLIENT_ID, "token", SpotifyUser.REDIRECT_URI,
                new String[]{"user-read-private", "streaming"}, null, this);
    }

    // method to start playing random songs -- maybe add timer info here
    public void startPlaying(View v) {
        Toast.makeText(v.getContext(), "Clicked!", Toast.LENGTH_SHORT).show();
        EditText numSongsET = (EditText) findViewById(R.id.numSongsET);
        EditText lengthOfSongsET = (EditText) findViewById(R.id.sessionTime);

        numSongs = (!"".equals(numSongsET.getText().toString())) ? Integer.parseInt(numSongsET.getText().toString())
                                            : 3;
        lenghtOfSession = (!"".equals(lengthOfSongsET.getText().toString())) ? Integer.parseInt(lengthOfSongsET.getText().toString())
                                                        : 60000;
        Timer timer = new Timer(v.getContext());
        timer.setNewTimer(v.getContext(), lenghtOfSession, numSongs);
    }

    // will save to the preferences or send directly to timer class
    public void saveOpts(View v) {
        Toast.makeText(v.getContext(), "Options clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");
    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(Throwable throwable) {
        Log.d("MainActivity", "Login failed!");
    }

    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error");
    }

    @Override
    public void onNewCredentials(String s) {
        Log.d("MainActivity", "On new credentials: " + s);
    }

    @Override
    public void onConnectionMessage(String s) {
        Log.d("MainActivity", "OnConnectionMessage: " + s);
    }

    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("MainActivity", "OnNewIntent called");
        Uri uri = intent.getData();
        if (uri != null) {
            Log.d("MainActivity", "URI: " + uri.toString());
            AuthenticationResponse response = SpotifyAuthentication.parseOauthResponse(uri);
            Log.d("MainActivity", "Response: " + response.toString());
            Spotify spotify = new Spotify(response.getAccessToken());
            mPlayer = spotify.getPlayer(this, "My Company Name", this, new Player.InitializationObserver() {
                @Override
                public void onInitialized() {
                    Log.d("MainActivity", "OnInitialized()");
                    mPlayer.addConnectionStateCallback(Dashboard.this);
                    mPlayer.addPlayerNotificationCallback(Dashboard.this);
                    mPlayer.play("spotify:track:2TpxZ7JUBn3uw46aR7qd6V");
                }

                @Override
                public void onError(Throwable throwable) {
                    Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
                }
            });
            Log.d("MainActivity", "MPlayer: " + mPlayer);
        }
    }
}
