package mg.studio.android.survey;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Intent Re_intent = getIntent();

        String Jstr = readAnswer();
        try {
            JSONArray jArr = new JSONArray(Jstr);
            JSONObject current=(JSONObject) jArr.get(jArr.length()-1);
            System.out.println("The Length of jArr is"+jArr.length());
            String str = "These are answers saved in File saveAnswer.json: "+current.getString("question 01") + "，";
            str += current.getString("question 02") + "，";
            str += current.getString("question 03") + "，";
            str += current.getString("question 04") + "，";
            str += current.getString("question 05") + "，";
            str += current.getString("question 06") + "，";
            str += current.getString("question 07") + "，";
            str += current.getString("question 08") + "，";
            str += current.getString("question 09") + "，";
            str += current.getString("question 10") + "，";
            str += current.getString("question 11") + "，";
            str += current.getString("question 12");
            AlertDialog accept = new AlertDialog.Builder(this)
                    .setMessage(str)
                    .setPositiveButton("OK", null)
                    .create();
            accept.show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String ans01 = "Question 1: " + Re_intent.getStringExtra("ans01");
        String ans02 = "Question 2: " + Re_intent.getStringExtra("ans02");
        String ans03 = "Question 3: " + Re_intent.getStringExtra("ans03");
        String ans04 = "Question 4: " + Re_intent.getStringExtra("ans04");
        String ans05 = "Question 5: " + Re_intent.getStringExtra("ans05");
        String ans06 = "Question 6: " + Re_intent.getStringExtra("ans06");
        String ans07 = "Question 7: " + Re_intent.getStringExtra("ans07");
        String ans08 = "Question 8: " + Re_intent.getStringExtra("ans08");
        String ans09 = "Question 9: " + Re_intent.getStringExtra("ans09");
        String ans10 = "Question 10: " + Re_intent.getStringExtra("ans10");
        String ans11 = "Question 11: " + Re_intent.getStringExtra("ans11");
        String ans12 = "Question 12: " + Re_intent.getStringExtra("ans12");
        TextView tv_ans01 = (TextView) findViewById(R.id.answer01);
        TextView tv_ans02 = (TextView) findViewById(R.id.answer02);
        TextView tv_ans03 = (TextView) findViewById(R.id.answer03);
        TextView tv_ans04 = (TextView) findViewById(R.id.answer04);
        TextView tv_ans05 = (TextView) findViewById(R.id.answer05);
        TextView tv_ans06 = (TextView) findViewById(R.id.answer06);
        TextView tv_ans07 = (TextView) findViewById(R.id.answer07);
        TextView tv_ans08 = (TextView) findViewById(R.id.answer08);
        TextView tv_ans09 = (TextView) findViewById(R.id.answer09);
        TextView tv_ans10 = (TextView) findViewById(R.id.answer10);
        TextView tv_ans11 = (TextView) findViewById(R.id.answer11);
        TextView tv_ans12 = (TextView) findViewById(R.id.answer12);
        tv_ans01.setText(ans01);
        tv_ans02.setText(ans02);
        tv_ans03.setText(ans03);
        tv_ans04.setText(ans04);
        tv_ans05.setText(ans05);
        tv_ans06.setText(ans06);
        tv_ans07.setText(ans07);
        tv_ans08.setText(ans08);
        tv_ans09.setText(ans09);
        tv_ans10.setText(ans10);
        tv_ans11.setText(ans11);
        tv_ans12.setText(ans12);
    }

    public String readAnswer() {
        String result="";
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File sdFile = Environment.getExternalStorageDirectory();
                File file = new File(sdFile, "saveAnswer.json");
                byte[] buffer = new byte[32 * 1024];
                FileInputStream fis = new FileInputStream(file);
                int len = 0;
                StringBuffer sb = new StringBuffer("");
                while ((len = fis.read(buffer)) > 0) {
                    sb.append(new String(buffer, 0, len));
                }
                fis.close();
                result=sb.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
