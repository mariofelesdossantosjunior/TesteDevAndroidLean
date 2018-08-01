package br.com.leanwork.testedevandroidlean.util

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import br.com.leanwork.testedevandroidlean.R
import br.com.leanwork.testedevandroidlean.iu.detalhe.DetalheCadastroActivity
import br.com.leanwork.testedevandroidlean.iu.detalhe.DetalheCadastroActivity.Companion.ExtraIdCadastro
import br.com.leanwork.testedevandroidlean.iu.detalhe.DetalheCadastroActivity.Companion.ExtraTipoCadastro


object PushCadastro {

    fun disparar(context: Context?, tipoCadastro: String, idCadastro: Long) {
        val intent = Intent(context, DetalheCadastroActivity::class.java).apply {
            putExtra(ExtraTipoCadastro, tipoCadastro)
            putExtra(ExtraIdCadastro, idCadastro)
        }

        val pIntent = PendingIntent.getActivity(context, System.currentTimeMillis().toInt(), intent, 0)

        val notification = Notification.Builder(context)
                .setContentTitle("Novo Cadastro!")
                .setContentText("Um novo Cadastro de $tipoCadastro foi realizado\n seu codigo é $idCadastro!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .build()

        val notificationManager = context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
        notificationManager?.notify(0, notification)
    }
}
