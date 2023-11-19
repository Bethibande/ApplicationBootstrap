package com.bethibande.bootstrap.misc

import java.lang.invoke.MethodHandles
import sun.misc.Unsafe

object Unsafe {

    private val HANDLE_THE_UNSAFE = MethodHandles.privateLookupIn(Unsafe::class.java, MethodHandles.lookup())
        .findStaticVarHandle(Unsafe::class.java, "theUnsafe", Unsafe::class.java)

    val INSTANCE by lazy { HANDLE_THE_UNSAFE.get() }

}