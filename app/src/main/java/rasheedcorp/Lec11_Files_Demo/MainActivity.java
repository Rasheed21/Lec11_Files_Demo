package rasheedcorp.Lec11_Files_Demo;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText titleNote;
    private EditText bodyNote;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleNote=(EditText)findViewById((R.id.note_body_ma));
        bodyNote=(EditText)findViewById(R.id.note_body_ma);
        tv=(TextView)findViewById(R.id.tv_ma);
    }

    public void saveNote(View view) {

        try {
            FileOutputStream outputStream = openFileOutput(titleNote.getText().toString().replace(" ",""),MODE_APPEND);
            outputStream.write(bodyNote.getText().toString().getBytes());
            outputStream.close();
            Snackbar.make(view, "File Saved", Snackbar.LENGTH_INDEFINITE).show();

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

    }

    public void loadNote(View view){
        String tempStr = "";
        String lstOfFile_Str="";
        ArrayList<String> lstOfNotes=new ArrayList<>();

        File filesDir=getCacheDir();
        long x=filesDir.getFreeSpace()/1_000_000;// this is bytes converted to MB
        File[] filesList=filesDir.listFiles();
        for (File fl:filesList)
            lstOfFile_Str+=fl.getName()+"\n";


        try {

            FileInputStream inputStream=openFileInput(filesList[2].getName());
            int c;
            while((c=inputStream.read())!=-1){
                tempStr+=Character.toString((char)c);
            }
            inputStream.close();

        }catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }
        tv.setText(tempStr + "\n Files stored are: \n"+lstOfFile_Str + "\n Free space available is : \n" +x+" MB");

    }

}
