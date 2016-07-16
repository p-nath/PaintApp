package com.example.artemis.paintapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.artemis.mypaint.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private PaintView paintview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        paintview = (PaintView)findViewById(R.id.pv_id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id._eraser) {
            Toast.makeText(this, "Eraser Chosen", Toast.LENGTH_SHORT).show();
            paintview.Erase();

            return true;
        }
        else if (id == R.id.red) {
            paintview.ChangeColorRed();
            Toast.makeText(this, "color: red", Toast.LENGTH_SHORT).show();

            return true;
        }
        else if (id == R.id.blue) {
            paintview.ChangeColorBlue();
            Toast.makeText(this, "color: blue", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.black) {
            paintview.ChangeColorBlack();
            Toast.makeText(this, "color: black", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.green) {
            paintview.ChangeColorGreen();
            Toast.makeText(this, "color: green", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.save) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            paintview.setDrawingCacheEnabled(true);
            paintview.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            Bitmap b = paintview.getDrawingCache();
            File f = Environment.getExternalStorageDirectory();
            File dir = new File(f, "priyanka_img");
            dir.mkdir();
            File file = new File(dir, "IMG_" + System.currentTimeMillis() + ".png");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                b.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();
                Toast.makeText(MainActivity.this, "File saved successfully", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
