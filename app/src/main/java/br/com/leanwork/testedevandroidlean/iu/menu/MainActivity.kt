package br.com.leanwork.testedevandroidlean.iu.menu

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import br.com.leanwork.testedevandroidlean.R
import br.com.leanwork.testedevandroidlean.iu.endereco.FormEnderecoFragment
import br.com.leanwork.testedevandroidlean.iu.endereco.ListaEnderecoFragment
import br.com.leanwork.testedevandroidlean.iu.pessoa.FormPessoaFragment
import br.com.leanwork.testedevandroidlean.iu.pessoa.ListaPessoasFragment
import br.com.leanwork.testedevandroidlean.util.replaceFragment

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.nav_lista_enderecos -> {
                replaceFragment(R.id.framePrincipal, ListaEnderecoFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_lista_pessoas -> {
                replaceFragment(R.id.framePrincipal, ListaPessoasFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_nova_pessoa -> {
                replaceFragment(R.id.framePrincipal, FormPessoaFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_novo_endereco -> {
                replaceFragment(R.id.framePrincipal, FormEnderecoFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        replaceFragment(R.id.framePrincipal, ListaEnderecoFragment.newInstance())

    }

}
