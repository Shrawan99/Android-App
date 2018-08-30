package np.gov.thorimun.www.thorimun4;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
//import android.widget.LinearLayout;
//import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

public class jsonTest extends AppCompatActivity {
    ListAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> listNoticeHeader;
    HashMap<String, List<String>> listNoticeBody;
    String data;
    JSONArray jsonArray;
    Boolean dataReady = false;
    final String documentString = "डाउनलोड गर्नुहोस";

    private static boolean internetConnectivityFlag = false;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    //private static final Integer[] IMAGES= {R.drawable.ic_launcher_foreground,R.mipmap.ic_launcher,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground};
    //private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    public void print(String value) {
        Log.i("TestIO", value);
    }

    public void print(int value) {
        Log.i("TestIO", String.valueOf(value));
    }

    Runnable dataRunnable = new Runnable() {

        @Override
        public void run() {
            try {
                String path = "http://thorimun.gov.np/newsfeed";

                URL url = new URL(path);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                data = bufferedReader.readLine();
                jsonArray = new JSONArray(data);
                print(jsonArray.getJSONObject(0).getString("Title"));

                dataReady = true;
                internetConnectivityFlag = true;


            } catch (Exception e) {
                internetConnectivityFlag = false;
                Toast.makeText(jsonTest.this,"No Internet Connection, Directing to Cached Mode", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(jsonTest.this, New6Activity.class);
                e.printStackTrace();
            }
        }
    };

    Thread dataThread = new Thread(dataRunnable);

  //*  private void init() {
    //    for(int i=0;i<IMAGES.length;i++)
      //      ImagesArray.add(IMAGES[i]);

       // mPager = (ViewPager) findViewById(R.id.pager);
        //mPager.setAdapter(new ImageAdapter(jsonTest.this,ImagesArray));

        //NUM_PAGES =IMAGES.length;

        //final Handler handler = new Handler();
     //   final Runnable Update = new Runnable() {
         //   public void run() {
             //   if (currentPage == NUM_PAGES) {
          //          currentPage = 0;
              //  }
            //    mPager.setCurrentItem(currentPage++, true);
            //}
       // };
       // Timer swipeTimer = new Timer();
      //  swipeTimer.schedule(new TimerTask() {
      //      @Override
        //    public void run() {
       //         handler.post(Update);
       //     }
      //  }, 3000, 3000);
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_test);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        dataThread.start();

       // init();

        while (!dataReady);

        try {
            listNoticeHeader = new ArrayList<String>();
            listNoticeBody = new HashMap<String, List<String>>();
            List<String> child;
            SharedPreferences NoticesPreferences = getSharedPreferences("msettings", MODE_PRIVATE);

            if (internetConnectivityFlag) {
                SharedPreferences.Editor editor = NoticesPreferences.edit();
                editor.putInt("listLength", jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    child = new ArrayList<String>();
                    listNoticeHeader.add(jsonArray.getJSONObject(i).getString("Title") + "\n");
                    editor.putString("Title" + i, jsonArray.getJSONObject(i).getString("Title"));
                    if (jsonArray.getJSONObject(i).getString("Body").length() != 0) {
                        child.add(jsonArray.getJSONObject(i).getString("Body"));
                        editor.putString("Body" + i, jsonArray.getJSONObject(i).getString("Body"));
                    }
                    if (jsonArray.getJSONObject(i).getString("Documents").length() != 0) {
                        //   child.add(jsonArray.getJSONObject(i).getString("Document"));
                        child.add(documentString);
                        editor.putString("Documents" + i, documentString);
                    }
                    listNoticeBody.put(listNoticeHeader.get(i), child);

                }
                editor.commit();
            } else {
                Integer listLength = NoticesPreferences.getInt("listLenght", 0);
                for (int i = 0; i < listLength; i++) {
                    child = new ArrayList<String>();
//                    listNoticeHeader.add(jsonArray.getJSONObject(i).getString("Title") + "\n");
                    listNoticeHeader.add(NoticesPreferences.getString("Title" + i, "NA"));
                    if (!NoticesPreferences.getString("Body" + i, "NA").contentEquals("NA")) {
//                        child.add(jsonArray.getJSONObject(i).getString("Body"));
                        child.add(NoticesPreferences.getString("Body" + i, "NA"));
                    }
                    if (!NoticesPreferences.getString("Documents" + i, "NA").contentEquals("NA")) {
                        //   child.add(jsonArray.getJSONObject(i).getString("Document"));
                        child.add(NoticesPreferences.getString("Documents" + i, "NA"));
                    }
                    listNoticeBody.put(listNoticeHeader.get(i), child);
                }
            }
            ArrayList<String> map2=(ArrayList<String>) NoticesPreferences.getAll();
            HashMap<String, List<String>> map=(HashMap<String, List<String>>)NoticesPreferences.getAll();

            listAdapter = new ListAdapter(this, listNoticeHeader, listNoticeBody);

            expandableListView.setAdapter(listAdapter);

        } catch (Exception e) {
                 e.printStackTrace();
        }

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (listAdapter.getChild(groupPosition, childPosition).toString().contentEquals(documentString)) {
                    try {
//                        print(jsonArray.getJSONObject(groupPosition).getString("Document"));
                        String urlLink = jsonArray.getJSONObject(groupPosition).getString("Documents");
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(urlLink));
                        startActivity(intent);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
    }
}
