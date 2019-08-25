@file:Suppress("FunctionName")

package io.futz

import java.io.File
import java.lang.System.mapLibraryName

object JavaRustFFI {

  interface RustLib {
    fun double_input(i: Int): Int
  }

  fun String.getLibraryPath(): String =
    File(JavaRustFFI::class.java.classLoader.getResource(mapLibraryName(this))!!.file).parent
}
