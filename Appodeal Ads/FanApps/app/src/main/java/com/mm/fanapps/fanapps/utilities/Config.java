package com.mm.fanapps.fanapps.utilities;

import android.content.SharedPreferences;

import com.mm.fanapps.fanapps.R;
import com.mm.fanapps.fanapps.activities.youtube.domain.Video;

import java.util.ArrayList;

/**
 * Created by Ali Sher on 1/28/2016.
 */
public class Config {

    public static final String YOUTUBE_API_KEY = "AIzaSyAifZV-W6KbE1TPazu0X0I61lPxauyxTqE";
    public static final String YOUTUBE_API_KEY_URLS = "AIzaSyBy1B9vYblsDWNlUbrN6fK6aFA8Aj5T-84";

    public static final String APPODEAL_APP_KEY = "0891529897def56632307af2a67774358e9bef8bc55485f9";

    public static final boolean ADS_VERSION = true;

    public static  int ICONS[] = {R.drawable.ic_cheats,R.drawable.ic_walkthrough, R.drawable.ic_live_streaming, R.drawable.ic_recorded_games,
            R.drawable.ic_rss, R.drawable.ic_facebook, R.drawable.ic_twiter};

    public static int ICONS_SELECTED[] = {R.drawable.ic_cheats_check,R.drawable.ic_walkthrough_check, R.drawable.ic_live_streaming_check, R.drawable.ic_recorded_games_check,
            R.drawable.ic_rss_check, R.drawable.ic_facebook_check, R.drawable.ic_twiter_check};

    public static String titles[] = {"Cheats", "Walkthrough", "Live Streaming", "Recorded Games", "RSS", "Facebook", "Twitter" };

    //
    public static final String APP_PREFS_NAME = "AppPrefs";
    public static SharedPreferences.Editor editor;
    public static SharedPreferences prefs;

    // Changes are made here

    public static String gameCheats = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo.\n\nNullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi.\n\n Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.\n\nDonec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo.";

    public static String walkthroughChannelID = "UCaP8FIBzhvpNg6TFGqiX-qg";
    public static String recordedGameChannelID = "UCaP8FIBzhvpNg6TFGqiX-qg";
    public static String liveGameChannelID = "UCaP8FIBzhvpNg6TFGqiX-qg";

    public static String facebookLink = "https://www.facebook.com/candycrushsaga/";
    public static String twitterLink = "https://twitter.com/CandyCrushSaga?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor";
    public static String rssLink = "UCaP8FIBzhvpNg6TFGqiX-qg";
    public static String searchNameToken = "jelly";


    public static String recordedGamesURL = "https://www.googleapis.com/youtube/v3/search?key="+YOUTUBE_API_KEY_URLS+"&channelId="+recordedGameChannelID+"&part=snippet,id&order=date&maxResults=20";
    //https://www.googleapis.com/youtube/v3/search?part=id%2C+snippet&q=Zelda &type=video&eventType=live&key=AIzaSyDTf2JxlzGz5OGFNVHSgbcksvKe-bftzVI"
    public static String liveGamesURL = "https://www.googleapis.com/youtube/v3/search?part=id%2C+snippet&q="+searchNameToken+"&type=video&eventType=live&key="+YOUTUBE_API_KEY_URLS;
}
