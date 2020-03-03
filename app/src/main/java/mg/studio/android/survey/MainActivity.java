package mg.studio.android.survey;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Intent AtoB;
    RadioGroup ques;
    String Jstr;
    private static String[] PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static int PERMISSION_CODE = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        AtoB = new Intent(MainActivity.this, ReportActivity.class);
        Jstr = "{";

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSION, PERMISSION_CODE);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "permission to apply：" + permissions[i] + "result：" + grantResults[i]);
            }
        }
    }

    public void start(View view) {
        CheckBox ac = (CheckBox) findViewById(R.id.accept);
        if (ac.isChecked())
            setContentView(R.layout.question_one);
        else {
            AlertDialog accept = new AlertDialog.Builder(this)
                    .setMessage("Please confirm that you accept the requests before answering questions")
                    .setPositiveButton("OK", null)
                    .create();
            accept.show();
        }

    }

    public void next1(View view) {
        ques = (RadioGroup) findViewById(R.id.question01);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans01", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 01\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_two);
                break;
            }
        }
    }

    public void next2(View view) {
        ques = (RadioGroup) findViewById(R.id.question02);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans02", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 02\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_three);
                break;
            }
        }
    }

    public void next3(View view) {
        ques = (RadioGroup) findViewById(R.id.question03);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans03", rb.getText());
                setContentView(R.layout.question_four);

                //设置Jstr的值
                Jstr += "\"question 03\":\"" + rb.getText().toString() + "\",";
                break;
            }
        }
    }

    public void next4(View view) {
        ques = (RadioGroup) findViewById(R.id.question04);
        String str = "";
        int count = 0;
        for (int i = 0; i < ques.getChildCount(); i++) {
            CheckBox cb = (CheckBox) ques.getChildAt(i);
            if (cb.isChecked()) {
                count++;
                str += cb.getText() + ";";
            }
        }
        AtoB.putExtra("ans04", str);

        //设置Jstr的值
        Jstr += "\"question 04\": \"" + str + "\",";

        if (count != 0)
            setContentView(R.layout.question_five);
    }

    public void next5(View view) {
        ques = (RadioGroup) findViewById(R.id.question05);
        String str = "";
        int count = 0;
        for (int i = 0; i < ques.getChildCount(); i++) {
            CheckBox cb = (CheckBox) ques.getChildAt(i);
            if (cb.isChecked()) {
                count++;
                str += cb.getText() + ";";
            }
        }
        AtoB.putExtra("ans05", str);

        //设置Jstr的值
        Jstr += "\"question 05\": \"" + str + "\",";
        if (count != 0)
            setContentView(R.layout.question_six);
    }

    public void next6(View view) {
        EditText et = (EditText) findViewById(R.id.question06);
        String str = et.getText().toString();
        if (str.length() != 0) {
            AtoB.putExtra("ans06", str);

            //设置Jstr的值
            Jstr += "\"question 06\": \"" + str + "\",";

            setContentView(R.layout.question_seven);
        } else
            Toast.makeText(MainActivity.this, "please enter your answer", Toast.LENGTH_SHORT).show();
    }

    public void next7(View view) {
        ques = (RadioGroup) findViewById(R.id.question07);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans07", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 07\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_eight);
                break;
            }
        }
    }

    public void next8(View view) {
        ques = (RadioGroup) findViewById(R.id.question08);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans08", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 08\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_nine);
                break;
            }
        }
    }

    public void next9(View view) {
        ques = (RadioGroup) findViewById(R.id.question09);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans09", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 09\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_ten);
                break;
            }
        }
    }

    public void next10(View view) {
        ques = (RadioGroup) findViewById(R.id.question10);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans10", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 10\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_eleven);
                break;
            }
        }
    }

    public void next11(View view) {
        ques = (RadioGroup) findViewById(R.id.question11);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans11", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 11\":\"" + rb.getText().toString() + "\",";
                setContentView(R.layout.question_twelve);
                break;
            }
        }
    }

    public void finish(View view) {
        ques = (RadioGroup) findViewById(R.id.question12);
        for (int i = 0; i < ques.getChildCount(); i++) {
            RadioButton rb = (RadioButton) ques.getChildAt(i);
            if (rb.isChecked()) {
                AtoB.putExtra("ans12", rb.getText());

                //设置Jstr的值
                Jstr += "\"question 12\":\"" + rb.getText().toString() + "\"}";
                setContentView(R.layout.finish_survey);
                break;
            }
        }
    }

    public void report(View view) {
        System.out.println(Jstr);
        try {
            saveFile(Jstr);
        } catch (IOException e) {

            e.printStackTrace();
        }
        startActivity(AtoB);
    }

    public void saveFile(String msg) throws IOException {
        File sdFile = Environment.getExternalStorageDirectory();
        File saveAnswer = new File(sdFile, "saveAnswer.json");
        if (saveAnswer.exists()) {
            System.out.println("saveAnswer exists");
            byte[] buffer = new byte[32 * 1024];
            FileInputStream fis = new FileInputStream(saveAnswer);
            int len = 0;
            StringBuffer sb = new StringBuffer("");
            while ((len = fis.read(buffer)) > 0) {
                sb.append(new String(buffer, 0, len));
            }
            fis.close();
            String temp = sb.toString();
            int length = temp.length();
            temp = temp.substring(0, length - 1);
            temp += ",";
            temp += msg + "]";
            msg=temp;
        } else {
            System.out.println("saveAnswer doesn't exists");
            msg = "["+msg+"]";
            FileOutputStream fout = new FileOutputStream(saveAnswer);
            fout.write(msg.getBytes());
            fout.flush();
            fout.close();
        }
        try {
            FileOutputStream fout = openFileOutput("saveAnswer.json",MODE_PRIVATE);
            fout.write(msg.getBytes());
            fout.flush();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
