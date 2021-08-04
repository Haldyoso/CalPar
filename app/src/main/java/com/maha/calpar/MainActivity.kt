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
import androidx.appcompat.widget.ActionBarContextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private var soundPool: SoundPool? = null
    private var sound1 = 0
    private var sound2 = 0
    private var sound3 = 0
    private var sound4 = 0
    private var sound3StreamId = 0



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


        soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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

        sound1 = soundPool!!.load(this, R.raw.snd_white_noise_01, 1)
        sound2 = soundPool!!.load(this, R.raw.snd_pink_noise_01, 1)
        sound3 = soundPool!!.load(this, R.raw.snd_heart_beat_01, 1)
        sound4 = soundPool!!.load(this, R.raw.snd_brown_noise_01, 1)



        toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->

            if (isChecked) {
                when (checkedId) {
                    R.id.btnWhiteNoise -> {

                        sound3StreamId = soundPool!!.play(sound2, 1f, 1f, 0, -1, 1f)
                        showToast("White toast")
                    }

                    R.id.btnPinkNoise -> {
                        stopSounds(v = View(this))
                        sound3StreamId = soundPool!!.play(sound3, 1f, 1f, 0, -1, 1f)
                        showToast("Pink toast.")
                    }

                    R.id.btnBrownNoise -> {
                        sound3StreamId = soundPool!!.play(sound4, 1f, 1f, 0, -1, 1f)
                        loadSnack("2")

                    }
                }
            } else {
                if (toggleButtonGroup.checkedButtonId == View.NO_ID) {
                    showToast("Nothing Selected")
                }
            }
        }

    }





    private fun stopSounds(v: View) {

//        btnDefaultDesign(v)
        soundPool?.stop(sound3StreamId)
    }

    fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }





    fun loadSnack(str: String) {

//        var snackText = "NEED TO FIGURE IT OUT, HOW TO CHANGE ACCORDING TO CLICKED BUTTON"

        val snackbar = make(ConstraintLayout(this), str, 1500)


        val view: View = snackbar.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params


                snackbar.show()
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
        
        
        
        
        









