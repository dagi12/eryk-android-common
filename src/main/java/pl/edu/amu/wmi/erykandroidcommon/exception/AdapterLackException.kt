package pl.edu.amu.wmi.erykandroidcommon.exception

import android.content.Context

class AdapterLackException(context: Context, adapter: Class<*>) : RuntimeException(context.toString() + MESSAGE_PART + adapter.name) {

    companion object {
        private val MESSAGE_PART = " must implement "
    }
}
