package io.futz;

import io.futz.JavaRustFFI.getLibraryPath
import jnr.ffi.LibraryLoader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class JavaRustFFITests {

  @Test
  fun succeeds() {
    val dylib = "double_input"
    System.setProperty("jnr.ffi.library.path", dylib.getLibraryPath())

    val rlib = LibraryLoader.create(JavaRustFFI.RustLib::class.java).load(dylib)
    val result = rlib.double_input(2)
    println("Result from rust double_input:  $result")

    assertEquals(4, result)
  }
}
