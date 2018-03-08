// Got help from Sasha
package com.example.angel.flashbeepshake

import android.content.Context
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toneGen:ToneGenerator
    lateinit var vib:Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val camManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cam = camManager.cameraIdList[0]
        toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            camManager.setTorchMode(cam, isChecked)
        }

        toneGen = ToneGenerator(AudioManager.STREAM_MUSIC, 50)
        toggleButton2.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked)
            {
                toneGen.startTone(ToneGenerator.TONE_DTMF_3)	//play specific tone for 600ms
            }
            else
            {
                toneGen.stopTone()
            }
        }

        vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        toggleButton3.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
            {
                vib.vibrate(100000)
            }
            else
            {
                vib.cancel()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}