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


//        btnWhiteNoise.setOnClickListener {
//            // if button is already in selected state and now it is pressed
//            // again,then it will reach in not selected state and vice versa
//            btnWhiteNoise.isSelected != btnWhiteNoise.isSelected
//        }

//        buttonSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
//            // if the switch is checked,then enable the button,else not
//            button.isEnabled = isChecked
//        }


        soundWhite = soundPool.load(this, R.raw.snd_white_noise_01, 1)
        soundPink = soundPool.load(this, R.raw.snd_pink_noise_01, 1)
        soundHearBeat = soundPool.load(this, R.raw.snd_heart_beat_01, 1)
        soundBrown = soundPool.load(this, R.raw.snd_brown_noise_01, 1)

        btnStop.setOnClickListener {
            stopSounds()
        }

        toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->

            stopSounds()
            if (isChecked) {
                soundStream = when (checkedId) {
                    R.id.btnWhiteNoise -> {
                        showToast("White toast")
                        soundPool.play(soundPink, 1f, 1f, 0, -1, 1f)
                    }

                    R.id.btnPinkNoise -> {
                        showToast("Pink toast.")
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
                    showToast("Nothing Selected")
                }
            }
        }

    }

    private fun stopSounds() {
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
//    }


}
        
        
        
        
        









