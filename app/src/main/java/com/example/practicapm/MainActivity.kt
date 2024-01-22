package com.example.practicapm
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var imagen1_1: ImageView
    lateinit var imagen1_2: ImageView
    lateinit var imagen1_3: ImageView
    lateinit var imagen1_4: ImageView
    lateinit var imagen2_1: ImageView
    lateinit var imagen2_2: ImageView
    lateinit var imagen2_3: ImageView
    lateinit var imagen2_4: ImageView
    lateinit var imagen3_1: ImageView
    lateinit var imagen3_2: ImageView
    lateinit var imagen3_3: ImageView
    lateinit var imagen3_4: ImageView

    lateinit var txtJugador1: TextView
    lateinit var txtJugador2: TextView

    lateinit var btnSonido: ImageButton

    lateinit var mp: MediaPlayer
    lateinit var mpFondo: MediaPlayer

    lateinit var imagen1: ImageView
    lateinit var imagen2: ImageView

    var imagenesArray = arrayOf(1_1, 1_2, 1_3, 1_4, 1_5, 1_6, 2_1, 2_2, 2_3, 2_4, 2_5, 2_6)
    var aston_villa = 0
    var chelsea = 0
    var crystal_palace = 0
    var luton = 0
    var newcastle = 0
    var wolves = 0
    var turno = 1
    var puntosj1 = 0
    var puntosj2 = 0
    var numeroImagen = 1
    var escuchar = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enlazar()
    }
    private fun enlazar() {
        imagen1_1 = findViewById(R.id.iv_11)
        imagen1_2 = findViewById(R.id.iv_12)
        imagen1_3 = findViewById(R.id.iv_13)
        imagen1_4 = findViewById(R.id.iv_14)
        imagen2_1 = findViewById(R.id.iv_21)
        imagen2_2 = findViewById(R.id.iv_22)
        imagen2_3 = findViewById(R.id.iv_23)
        imagen2_4 = findViewById(R.id.iv_24)
        imagen3_1 = findViewById(R.id.iv_31)
        imagen3_2 = findViewById(R.id.iv_32)
        imagen3_3 = findViewById(R.id.iv_33)
        imagen3_4 = findViewById(R.id.iv_34)
        btnSonido = findViewById(R.id.sonido)
        btnSonido.setColorFilter(Color.DKGRAY)
        sonido("background", true)
        imagen1_1.tag = "0"
        imagen1_2.tag = "1"
        imagen1_3.tag = "2"
        imagen1_4.tag = "3"
        imagen2_1.tag = "4"
        imagen2_2.tag = "5"
        imagen2_3.tag = "6"
        imagen2_4.tag = "7"
        imagen3_1.tag = "8"
        imagen3_2.tag = "9"
        imagen3_3.tag = "10"
        imagen3_4.tag = "11"
        aston_villa = R.drawable.aston_villa
        chelsea = R.drawable.chelsea
        crystal_palace = R.drawable.crystal_palace
        luton = R.drawable.luton
        newcastle = R.drawable.newcastle
        wolves = R.drawable.wolves
        imagenesArray.shuffle()
        txtJugador1 = findViewById(R.id.jugador1)
        txtJugador2 = findViewById(R.id.jugador2)
        txtJugador1.setTextColor(Color.GRAY)
        txtJugador2.setTextColor(Color.WHITE)
    }
    private fun sonido(sonidoN: String, repetir: Boolean = false) {
        val resID = resources.getIdentifier(sonidoN, "raw", packageName)
        if (sonidoN == "background") {
            mpFondo = MediaPlayer.create(this, resID)
            mpFondo.isLooping = repetir
            mpFondo.setVolume(0.5F, 0.5F)
            if (!mpFondo.isPlaying) {
                mpFondo.start()
            }
        } else {
            mp = MediaPlayer.create(this, resID)
            mp.setOnCompletionListener(MediaPlayer.OnCompletionListener { mediaPlayer ->
                mediaPlayer.stop()
                mediaPlayer.release()
            })
            if (!mp.isPlaying) {
                mp.start()
            }
        }
    }
    fun musica(v: View) {
        if (escuchar) {
            mpFondo.pause()
            btnSonido.setImageResource(R.drawable.ic_volumen_off)
            btnSonido.setColorFilter((Color.DKGRAY))
        } else {
            mpFondo.start()
            btnSonido.setImageResource(R.drawable.ic_volumen_on)
            btnSonido.setColorFilter((Color.DKGRAY))
        }
        escuchar = !escuchar
    }
    fun seleccionar(imagen: View) {
        sonido("touch")
        verificar(imagen)
    }
    private fun verificar(imagen: View) {
        val tag = imagen.tag.toString().toInt()
        val iv = (imagen as ImageView)
        if (imagenesArray[tag] == 1_1) {
            iv.setImageResource(aston_villa)
        } else if (imagenesArray[tag] == 1_2) {
            iv.setImageResource(chelsea)
        } else if (imagenesArray[tag] == 1_3) {
            iv.setImageResource(crystal_palace)
        } else if (imagenesArray[tag] == 1_4) {
            iv.setImageResource(luton)
        } else if (imagenesArray[tag] == 1_5) {
            iv.setImageResource(newcastle)
        } else if (imagenesArray[tag] == 1_6) {
            iv.setImageResource(wolves)
        } else if (imagenesArray[tag] == 2_1) {
            iv.setImageResource(aston_villa)
        } else if (imagenesArray[tag] == 2_2) {
            iv.setImageResource(chelsea)
        } else if (imagenesArray[tag] == 2_3) {
            iv.setImageResource(crystal_palace)
        } else if (imagenesArray[tag] == 2_4) {
            iv.setImageResource(luton)
        } else if (imagenesArray[tag] == 2_5) {
            iv.setImageResource(newcastle)
        } else if (imagenesArray[tag] == 2_6) {
            iv.setImageResource(wolves)
        }

        // guardar img
        if (numeroImagen == 1) {
            imagen1 = iv
            numeroImagen = 2
            iv.isEnabled = false
        } else if (numeroImagen == 2) {
            imagen2 = iv
            numeroImagen = 1
            iv.isEnabled = false
            deshabilitarImagenes()
            val h = Handler(Looper.getMainLooper())
            h.postDelayed({ imagenesIguales() }, 1000)
        }
    }
    private fun imagenesIguales() {
        if (imagen1.drawable.constantState == imagen2.drawable.constantState) {
            sonido("success")
            if (turno == 1) {
                puntosj1++
                txtJugador1.text = "J1: $puntosj1"
            } else if (turno == 2) {
                puntosj2++
                txtJugador2.text = "J2: $puntosj2"
            }
            imagen1.isEnabled = false
            imagen2.isEnabled = false
            imagen1.tag = ""
            imagen2.tag = ""
        } else {
            sonido("no")
            imagen1.setImageResource(R.drawable.premier)
            imagen2.setImageResource(R.drawable.premier)
            if (turno == 1) {
                turno = 2
                txtJugador1.setTextColor(Color.WHITE)
                txtJugador2.setTextColor(Color.GRAY)
            } else if (turno == 2) {
                turno = 1
                txtJugador1.setTextColor(Color.GRAY)
                txtJugador2.setTextColor(Color.WHITE)
            }
        }
        imagen1_1.isEnabled = !imagen1_1.tag.toString().isEmpty()
        imagen1_2.isEnabled = !imagen1_2.tag.toString().isEmpty()
        imagen1_3.isEnabled = !imagen1_3.tag.toString().isEmpty()
        imagen1_4.isEnabled = !imagen1_4.tag.toString().isEmpty()
        imagen2_1.isEnabled = !imagen2_1.tag.toString().isEmpty()
        imagen2_2.isEnabled = !imagen2_2.tag.toString().isEmpty()
        imagen2_3.isEnabled = !imagen2_3.tag.toString().isEmpty()
        imagen2_4.isEnabled = !imagen2_4.tag.toString().isEmpty()
        imagen3_1.isEnabled = !imagen3_1.tag.toString().isEmpty()
        imagen3_2.isEnabled = !imagen3_2.tag.toString().isEmpty()
        imagen3_3.isEnabled = !imagen3_3.tag.toString().isEmpty()
        imagen3_4.isEnabled = !imagen3_4.tag.toString().isEmpty()
        finJuego()
    }
    private fun silenciar(){
        if(::mp.isInitialized && mp.isPlaying){
            mp.stop()
            mp.release()
        }
        if(::mpFondo.isInitialized && mpFondo.isPlaying){
            mpFondo.stop()
            mpFondo.release()
        }
    }
    private fun reiniciarJuego(){
        silenciar()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun finJuego() {
        if (imagen1_1.tag.toString().isEmpty() && imagen1_2.tag.toString()
                .isEmpty() && imagen1_3.tag.toString().isEmpty() && imagen1_4.tag.toString().isEmpty() &&
            imagen2_1.tag.toString().isEmpty() && imagen2_2.tag.toString()
                .isEmpty() && imagen2_3.tag.toString().isEmpty() && imagen2_4.tag.toString().isEmpty() &&
            imagen3_1.tag.toString().isEmpty() && imagen3_2.tag.toString()
                .isEmpty() && imagen3_3.tag.toString().isEmpty() && imagen3_4.tag.toString().isEmpty()
        ) {
            mp.stop()
            mp.release()
            sonido("win")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Fin del juego")
                .setMessage("Puntos \nJ1: " + puntosj1 + "\nJ2: " + puntosj2).setCancelable(false)
                .setPositiveButton("Jugar de nuevo",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        reiniciarJuego()
                    })
                .setNegativeButton("Salir", DialogInterface.OnClickListener { dialogInterface, i ->
                    finish()
                })
            val ad = builder.create()
            ad.show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        silenciar()
    }
    private fun deshabilitarImagenes() {
        imagen1_1.isEnabled = false
        imagen1_2.isEnabled = false
        imagen1_3.isEnabled = false
        imagen1_4.isEnabled = false
        imagen2_1.isEnabled = false
        imagen2_2.isEnabled = false
        imagen2_3.isEnabled = false
        imagen2_4.isEnabled = false
        imagen3_1.isEnabled = false
        imagen3_2.isEnabled = false
        imagen3_3.isEnabled = false
        imagen3_4.isEnabled = false
    }
}