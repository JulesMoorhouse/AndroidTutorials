package com.example.advancedwidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Random;

public class NewsService extends Service {

    ArrayList<String> descriptions;
    ArrayList<String> links;
    ArrayList<String> titles;
    Intent intent;

    public NewsService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        descriptions = new ArrayList<String>();
        links = new ArrayList<String>();
        titles = new ArrayList<String>();

        this.intent = intent;

        if (ApplicationClass.connectionAvailable(getApplicationContext()))
        {
            RemoteViews views = new RemoteViews("com.example.advancedwidget", R.layout.new_app_widget);
            views.setTextViewText(R.id.tvTitle, "Busy retrieving data...");
            views.setTextViewText(R.id.tvDescription, "Busy retrieving data...");

            AppWidgetManager.getInstance(getApplicationContext()).updateAppWidget(intent.getIntExtra("appWidgetId", 0), views);

            new GetStoriesInBackground().execute();
        }
        else
        {
            Toast.makeText(this, "Please makes sure your phone has an active internet connection!",
                    Toast.LENGTH_SHORT).show();
            stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();

        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        decoder.onMalformedInput(CodingErrorAction.IGNORE);
        decoder.onUnmappableCharacter(CodingErrorAction.IGNORE);

        InputStreamReader isr = new InputStreamReader(is, decoder);

        BufferedReader r = new BufferedReader(isr,1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();

        String data = sb.toString();
        data = data.replaceAll("[^\\x20-\\x7e]", "");
        return data;
    }

    public class GetStoriesInBackground extends AsyncTask<Integer, Integer, String>
    {

        @Override
        protected String doInBackground(Integer... params) {

            try {

                URL url = new URL("https://feeds.news24.com/articles/news24/TopStories/rss");
                InputStream is = url.openStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();

                String content = readStream(is);

                // We will get the XML from an input stream
                xpp.setInput(new StringReader(content.replace("&","&amp;")));

                /* We will parse the XML content looking for the "<title>" tag which appears inside the "<item>" tag.
                 * However, we should take in consideration that the rss feed name also is enclosed in a "<title>" tag.
                 * As we know, every feed begins with these lines: "<channel><title>Feed_Name</title>...."
                 * so we should skip the "<title>" tag which is a child of "<channel>" tag,
                 * and take in consideration only "<title>" tag which is a child of "<item>"
                 *
                 * In order to achieve this, we will make use of a boolean variable.
                 */
                boolean insideItem = false;

                // Returns the type of current event: START_TAG, END_TAG, etc..
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {

                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem)
                                titles.add(xpp.nextText()); //extract the headline
                        } else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem)
                                links.add(xpp.nextText()); //extract the link of article
                        }
                        else if (xpp.getName().equalsIgnoreCase("description")) {
                            if (insideItem)
                                descriptions.add(xpp.nextText()); //extract the link of article
                        }
                    }else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                        insideItem=false;
                    }

                    eventType = xpp.next(); //move to next element
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }


        @Override
        protected void onPostExecute(String result) {

            Random random = new Random();
            int randomValue = random.nextInt(titles.size());

            // Set text values from rss feed array data
            RemoteViews views = new RemoteViews("com.example.advancedwidget", R.layout.new_app_widget);
            views.setTextViewText(R.id.tvTitle, titles.get(randomValue));
            views.setTextViewText(R.id.tvDescription, descriptions.get(randomValue));

            // Set link so that it will open in a web page.
            Uri uri = Uri.parse(links.get(randomValue));
            Intent linkIntent = new Intent(Intent.ACTION_VIEW, uri);

            // setup tvDescription link
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                    linkIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.tvDescription, pendingIntent);

            // Setup sync button to restart
            PendingIntent pendingIntentSync = PendingIntent.getService(getApplicationContext(), 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.ivSync, pendingIntentSync);

            AppWidgetManager.getInstance(getApplicationContext()).updateAppWidget(intent.getIntExtra("appWidgetId", 0), views);
        }

    }
}