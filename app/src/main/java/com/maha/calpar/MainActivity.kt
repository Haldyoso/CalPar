package com.maha.calpar

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar.make

class MainActivity : AppCompatActivity() {


    private var soundPool: SoundPool? = null
    private var sound1 = 0
    private var sound2 = 0
    private var sound3 = 0
    private var sound4 = 0
    private var sound3StreamId = 0
    lateinit var it: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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

    }

    fun playSound(v: View) {
        when (v.id) {
            R.id.btnWhiteNoise -> {
                sound3StreamId = soundPool!!.play(sound1, 1f, 1f, 0, -1, 1f)
                Toast.makeText(this, "playing: White noise", Toast.LENGTH_SHORT).show()
            }

            R.id.btnPinkNoise -> {
                sound3StreamId = soundPool!!.play(sound2, 1f, 1f, 0, -1, 1f)
                Toast.makeText(this, "playing: Pink noise", Toast.LENGTH_SHORT).show()
            }

            R.id.btnHeartBeat -> {
                sound3StreamId = soundPool!!.play(sound3, 1f, 1f, 0, -1, 1f)
                Toast.makeText(this, "playing: Heart beat", Toast.LENGTH_SHORT).show()
            }

            R.id.btnBrownNoise -> {
                sound3StreamId = soundPool!!.play(sound4, 1f, 1f, 0, -1, 1f)
                Toast.makeText(this, "playing: Brown noise", Toast.LENGTH_SHORT).show()
            }

            R.id.btnToastTest -> {

                Toast.makeText(this, "playing: toast test", Toast.LENGTH_SHORT).show()
            }

            R.id.btnSnackBar -> {
                val snackbar = make(it, "Snackbar test", LENGTH_SHORT)
                snackbar.show()
            }
        }
    }


    fun stopSounds(v: View) {


        soundPool?.stop(sound3StreamId)
    }


}
        
        
        
        
        









