package edu.itsligo.gaaappmain.APIFetch;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import edu.itsligo.gaaappmain.HomeFragmentHome;
import edu.itsligo.gaaappmain.MainActivity;

public class fetchData extends AsyncTask<Void, Void, Void> {
    String mName = "";
    String Pos1 = "", Pos2 = "", Pos3 = "", Club1 = "", Club2 = "", Club3 = "", Wins1 = "", Games1 = "", Games2 = "", Games3 = "", Wins2 = "", Wins3 = "", GD1 = "", GD2 = "", GD3 = "", Pts1 = "", Pts2 = "", Pts3 = "";
    private JSONObject jo;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://my-teams-api.herokuapp.com/Teams");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                mName = mName + line;
            }

//

            JSONArray jsonArray = new JSONArray(mName);
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    jo = jsonArray.getJSONObject(i);

                    int id = jo.getInt("id");

                    if (id == 0) {
                        Pos1 = "" + jo.get("pos");
                        Club1 = "" + jo.get("name");
                        Wins1 = "" + jo.get("wins");
                        GD1 = "" + jo.get("gd");
                        Pts1 = "" + jo.get("points");
                    }
                    if (id == 1) {
                        Pos2 = "" + jo.get("pos");
                        Club2 = "" + jo.get("name");
                        Wins2 = "" + jo.get("wins");
                        GD2 = "" + jo.get("gd");
                        Pts2 = "" + jo.get("points");
                    }
                    if (id == 2) {
                        Pos3 = "" + jo.get("pos");
                        Club3 = "" + jo.get("name");
                        Wins3 = "" + jo.get("wins");
                        GD3 = "" + jo.get("gd");
                        Pts3 = "" + jo.get("points");
                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;


    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        try {

            JSONArray jsonArray = new JSONArray(mName);
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    jo = jsonArray.getJSONObject(i);

                    int p1 = Integer.parseInt(Pos1);
                    int p2 = Integer.parseInt(Pos2);

                    if (p1 == 1) {
                        HomeFragmentHome.mPos1.setText(this.Pos1);
                        HomeFragmentHome.mClub1.setText(this.Club1);
                        HomeFragmentHome.mGD1.setText(this.GD1);
                        HomeFragmentHome.mWins1.setText(this.Wins1);
                        HomeFragmentHome.mPts1.setText(this.Pts1);


                    }
                    if (p1 == 2) {
                        HomeFragmentHome.mPos2.setText(this.Pos1);
                        HomeFragmentHome.mClub2.setText(this.Club1);
                        HomeFragmentHome.mGD2.setText(this.GD1);
                        HomeFragmentHome.mWins2.setText(this.Wins1);
                        HomeFragmentHome.mPts2.setText(this.Pts1);


                    }
                    if (p2 == 1) {
                        HomeFragmentHome.mPos1.setText(this.Pos2);
                        HomeFragmentHome.mClub1.setText(this.Club2);
                        HomeFragmentHome.mGD1.setText(this.GD2);
                        HomeFragmentHome.mWins1.setText(this.Wins2);
                        HomeFragmentHome.mPts1.setText(this.Pts2);


                    }
                    if (p2 == 2) {
                        HomeFragmentHome.mPos2.setText(this.Pos2);
                        HomeFragmentHome.mClub2.setText(this.Club2);
                        HomeFragmentHome.mGD2.setText(this.GD2);
                        HomeFragmentHome.mWins2.setText(this.Wins2);
                        HomeFragmentHome.mPts2.setText(this.Pts2);
                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

