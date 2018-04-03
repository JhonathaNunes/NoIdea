package br.com.senai.app

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.content_cadastro.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnCadastro.setOnClickListener {
            val usuario = txtUsuario.text.toString()
            val senha = txtPass.text.toString()
            val sexo:String
            if (rdMasc.isChecked){
                sexo = "m"
            }else{
                sexo = "f"
            }

            doAsync {
                val url = "http://10.0.2.2/inf4m/apiInserir.php"
                val map:HashMap<String, String> = hashMapOf("user" to usuario, "pass" to senha, "sexo" to sexo)
                val resultado = HttpConnection.post(url, map)
                val resultadoJson = JSONObject(resultado)
                val sucesso = resultadoJson.getBoolean("mensagem")

                uiThread {
                    if (sucesso){
                        toast("Usuário cadastrado com sucesso!")
                        finish()
                    }else{
                        toast("Falha no cadastro")
                    }
                }
            }


        }


    }

}
