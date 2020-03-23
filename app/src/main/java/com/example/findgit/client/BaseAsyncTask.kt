package com.example.findgit.client

import android.os.AsyncTask

class BaseAsyncTask<T>(
    private val executar: () -> T,
    private val finaliza: (resultado: T) -> Unit
) : AsyncTask<Void, Void, T>() {

    override fun doInBackground(vararg params: Void?) = executar()

    override fun onPostExecute(resultado: T) {
        super.onPostExecute(resultado)
        finaliza(resultado)
    }

}