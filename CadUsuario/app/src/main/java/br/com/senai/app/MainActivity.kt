package br.com.senai.app

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cadastro.setOnClickListener {
            //Sem biblioteca Anko
            //val intent = Intent(this, CadastroActivity::class.java)
            //startActivity(intent)

            //Com biblioteca Anko
            startActivity<CadastroActivity>()
        }

        btnLog.setOnClickListener {

            val usuario = txtUsuario.text.toString();
            val senha = txtPass.text.toString();

            doAsync{
                val login = "http://10.0.2.2/inf4m/api.php"

                val map: HashMap<String, String> = hashMapOf("user" to usuario, "pass" to senha)

                val resultado = HttpConnection.post(login, map)

                val retornoJson = JSONObject(resultado)

                val logado = retornoJson.getBoolean("mensagem")
                val user = retornoJson.getJSONObject("usuario")

                uiThread {
                    if (logado){
                        startActivity<PrincipalActivity>()
                        finish()

                    }else{
                        toast("Usuário ou Senha incorreto!")
                    }
                }
            }

        }
    }
}
