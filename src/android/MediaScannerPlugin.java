package org.sfl.MediaScannerPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * MediaScannerPlugin.java
 *
 * Inspirated by Joseph's "Saving Image to Android device’s Gallery – Phonegap Android" plugin
 * https://jbkflex.wordpress.com/2012/12/23/saving-image-to-android-devices-gallery-phonegap-android/
 *
 * @author Peter Gao <peter@spacefluxlabs.com>
 */
public class MediaScannerPlugin extends CordovaPlugin {
    public static final String ACTION = "scanFile";

    @Override
    public boolean execute(String action, String filePath,
            CallbackContext callbackContext) throws JSONException {

        if (action.equals(ACTION)) {
            if (filePath == null) {
                callbackContext.error("A filepath was not provided.");
                Log.w("A filepath was not provided!");
            } else {
                Log.w("scanFile Action");
                // Update image gallery
                scanPhoto(filePath);

                Log.w("scanFile Successful");
                callbackContext.success(filePath);
            }

            return true;
        } else {
            Log.w("Wrong action was provided: "+action);
            return false;
        }
    }

    /* Invoke the system's media scanner to add your photo to the Media Provider's database,
     * making it available in the Android Gallery application and to other apps. */
    private void scanPhoto(String filePath)
    {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromParts("file",filePath);
        Log.w("URI constructed");
        mediaScanIntent.setData(contentUri);
        cordova.getActivity().sendBroadcast(mediaScanIntent);
    }
}