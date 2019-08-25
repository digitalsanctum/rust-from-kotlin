@file:Suppress("FunctionName")

package io.futz

import jnr.ffi.LibraryLoader
import java.io.File
import java.lang.System.mapLibraryName

object JavaRustFFI {

  interface RustLib {
    fun double_input(i: Int): Int
  }

  private fun String.getLibraryPath(): String =
    File(JavaRustFFI::class.java.classLoader.getResource(mapLibraryName(this))!!.file).parent

  @JvmStatic
  fun main(args: Array<String>) {
    val dylib = "double_input"
    System.setProperty("jnr.ffi.library.path", dylib.getLibraryPath())

    val rlib = LibraryLoader.create(RustLib::class.java).load(dylib)
    val result = rlib.double_input(2)

    println("Result from rust double_input:  $result")
  }
}
