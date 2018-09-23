package np.gov.thorimun.www.thorimun4;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class facebookActivity extends AppCompatActivity {
    WebView webView;
    String Url = "https://www.facebook.com/Thorimunicipality";
    private Context mContext;
    private Activity mActivity;
    private static final int MY_PERMISSION_REQUEST_CODE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mContext = getApplicationContext();
        mActivity = facebookActivity.this;
        setContentView(R.layout.activity_facebook);
        webView = (WebView) findViewById(R.id.webView);
        checkConnection();
    }

    protected boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {

            return false;
        }
    }

    public void checkConnection() {

        if (isOnline()) {

            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(Url);
            checkPermission();
            webView.setDownloadListener(new DownloadListener() {
                @Override
                public void onDownloadStart(String url, String userAgent, String contentDescription, String mimetype, long contentLength) {
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    String fileName = URLUtil.guessFileName(url, contentDescription, mimetype);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
                    DownloadManager dManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    dManager.enqueue(request);
                    Toast.makeText(getApplicationContext(), "कृपया पर्खनु होस, फाइल डाउनलोड हुँदै छ", //To notify the Client that the file is being downloaded
                            Toast.LENGTH_LONG).show();
                }
            });


        } else {
            Toast.makeText(this, "No Internet Connection, Directing to Cached Mode", Toast.LENGTH_SHORT).show();
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(Url);
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }


    }

    protected void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // show an alert dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                    builder.setMessage("Write external storage permission is required.");
                    builder.setTitle("Please grant permission");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(
                                    mActivity,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_CODE
                            );
                        }
                    });
                    builder.setNeutralButton("Cancel", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    // Request permission
                    ActivityCompat.requestPermissions(
                            mActivity,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_CODE
                    );
                }
            } else {
                // Permission already granted
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                } else {
                    // Permission denied
                }
            }
        }

    }
}

