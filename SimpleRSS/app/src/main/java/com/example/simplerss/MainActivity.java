package com.example.simplerss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvRss;
    ArrayList<String> titles;
    ArrayList<String> links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvRss = (ListView)findViewById(R.id.lvRss);

        titles = new ArrayList<String>();
        links = new ArrayList<String>();

        lvRss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Uri uri = Uri.parse(links.get(position));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        new ProcessInBackground().execute();
    }

    public InputStream getInputStream(URL url)
    {
        try
        {
            return url.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            return null;
        }
    }

    // Parameters can be changed here... Exception must be String
    public class ProcessInBackground extends AsyncTask<Integer, Void, Exception>
    {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Busy loading RSS feed... please wait...");
            progressDialog.show();
        }

        @Override
        protected Exception doInBackground(Integer... integers) {

            try
            {
                URL url = new URL("https://feeds.news24.com/articles/fin24/tech/rss");
                InputStream is = url.openStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

                factory.setNamespaceAware(false); // will not provide support for xml namesspaces
                //factory.setNamespaceAware(true);

                XmlPullParser xpp = factory.newPullParser();

                String content = readStream(is);

                //xpp.setInput(getInputStream(url), "UTF_8");
                //xpp.setInput(new InputStreamReader(is, "utf-8"));
                xpp.setInput(new StringReader(content.replace("&","&amp;")));

                boolean insideItem = false;

                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT)
                {
                    if (eventType == XmlPullParser.START_TAG)
                    {
                        String name = xpp.getName();
                        if (name.equalsIgnoreCase("item"))
                        {
                            insideItem = true;
                        }
                        else if (name.equalsIgnoreCase("title"))
                        {
                            // ensure you're inside an item tag rather than an document / channel title tag.
                            if (insideItem)
                            {
                                titles.add(xpp.nextText()); // return text inside title tag
                            }
                        }
                        else if (name.equalsIgnoreCase("link"))
                        {
                            if (insideItem)
                            {
                                links.add(xpp.nextText());
                            }
                        }
                        else if (eventType == XmlPullParser.END_TAG && name.equalsIgnoreCase("item"))
                        {
                            insideItem = false;
                        }
                    }

                    eventType = xpp.next();

                }
            }
            catch (MalformedURLException e)
            {
                exception = e;
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            catch (XmlPullParserException e)
            {
                exception = e;
            }
            catch (IOException e)
            {
                exception = e;
            }

            return exception;
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

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, titles);
            lvRss.setAdapter(adapter);

            progressDialog.dismiss();
        }
    }
}