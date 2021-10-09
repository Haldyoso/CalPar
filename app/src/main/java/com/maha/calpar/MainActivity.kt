package com.maha.calpar

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.*


class MainActivity : AppCompatActivity() {


    private val soundPool: SoundPool by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build()
        } else {
            SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        }
    }

    private var soundWhite = 0
    private var soundPink = 0
    private var soundHearBeat = 0
    private var soundBrown = 0
    private var soundStream = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        soundWhite = soundPool.load(this, R.raw.snd_white_noise_01, 1)
        soundPink = soundPool.load(this, R.raw.snd_pink_noise_01, 1)
        soundHearBeat = soundPool.load(this, R.raw.snd_heart_beat_01, 1)
        soundBrown = soundPool.load(this, R.raw.snd_brown_noise_01, 1)

        btnStop.setOnClickListener {
            stopSounds()
        }
        BtnClearchecked.setOnClickListener {
            toggleButtonGroup.uncheck()
        }

        toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->
            stopSounds()



            if (isChecked) {



                soundStream = when (checkedId) {
                    R.id.btnWhiteNoise -> {
                        loadSnack("White noise")
                        soundPool.play(soundPink, 1f, 1f, 0, -1, 1f)

                    }

                    R.id.btnPinkNoise -> {
                        loadSnack("Pink noise")
                        soundPool.play(soundHearBeat, 1f, 1f, 0, -1, 1f)
                    }

                    R.id.btnBrownNoise -> {

                        loadSnack("Brown noise")
                        soundPool.play(soundBrown, 1f, 1f, 0, -1, 1f)
                    }
                    else -> {
                        error("Unknown noise")
                    }
                }
            } else {
                if (toggleButtonGroup.checkedButtonId == View.NO_ID) {
                    loadSnack("Nothing Selected")
                }
            }
        }

    }




    private fun clearAllID1() {
        if (toggleButtonGroup != null) {
            toggleButtonGroup.clearOnButtonCheckedListeners()
        }
    }
    private fun clearAllID2() {
        if (toggleButtonGroup != null) {
            toggleButtonGroup.clearChecked()
        }
    }
    private fun clearAllID3() {
        if (toggleButtonGroup != null) {
            toggleButtonGroup.clearFindViewByIdCache()
        }
    }
    private fun clearAllID4() {

    }
    private fun clearAllID5() {

            if (toggleButtonGroup != null) {
                toggleButtonGroup.uncheck(R.id.toggleButtonGroup)
            }
        }


        fun stopSounds() {

            soundPool.stop(soundStream)





        }


        fun showToast(str: String) {
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
        }


        private fun loadSnack(str: String) {


            Snackbar.make(root, str, 1500)
                .apply {
                    (view.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.TOP
                }
                .show()
        }

        private fun clrCheckedButtons() {

                toggleButtonGroup.clearChecked()

        }


//    private fun btnDefaultDesign(v: View) {
//
//        btnWhiteNoise.setBackgroundResource(R.drawable.bg_button_2)
//        btnBrownNoise.setBackgroundResource(R.drawable.bg_button_2)
//        btnHeartBeat.setBackgroundResource(R.drawable.bg_button_2)
//        btnPinkNoise.setBackgroundResource(R.drawable.bg_button_2)
//        btnSnackBar.setBackgroundResource(R.drawable.bg_button_2)
//        btnWhiteNoise.setBackgroundResource(R.drawable.bg_button_2)
//
//



}
        
        
        
        
        









